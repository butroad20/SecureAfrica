<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class User extends CI_Controller{
    public function __construct(){
        parent::__construct();
        $this->load->library(array('session','form_validation'));
        $this->load->model('user_model');
        $this->load->helper('date');
    }

    public function index(){
        if (!isset($_SESSION['logged_in']) || $_SESSION['logged_in'] !== true){
            $this->session->set_flashdata('url', $this->uri->uri_string());
            redirect('login');
        }
        $data = new stdClass();
        $data->user = $this->user_model->get_user($_SESSION['user_id']);
        $this->load->view('templates/header');
        $this->load->view('user/profile', $data);
        $this->load->view('templates/footer');

    }

    public function confirm_account($user_id=false, $hash=false){
        $data = new stdClass();
        // find the email for the given user

        if ($user_id == false || $hash == false){
            show_404();
        }
        $data->user = $this->user_model->get_user($user_id);

        if (empty($data->user)) {
            show_404();
        }


        if (sha1("Csk#cOnfirm1" . $data->user->email . $data->user->hash) == $hash) {

            // values from the url are good, we can validate the account
            $user = array(
                'is_confirmed' => '1',
                'hash'=> '',
            );
            $data->message = "Account confirmed, Please Login";
            $data->messagetype = "alert-success";
            if($this->user_model->super_update($user->id, $user)){
                $this->load->view('templates/header');
                $this->load->view('login', $data);
                $this->load->view('templates/footer');
            }else{
                //account confirmation failed, should not happen
                $data->message = "Confirmation Failed, Please Try Again";
                $this->load->view('templates/header');
                $this->load->view('login', $data);
                $this->load->view('templates/footer');
            }


        }else{
            $data->message = "Account not found, Please Register";
            $this->load->view('templates/header');
            $this->load->view('user/login', $data);
            $this->load->view('templates/footer');
        }


    }

    public function edit(){
        // create the data object
        $data = new stdClass();
        if ( !isset($_SESSION['logged_in']) || $_SESSION['logged_in'] == false){
            $this->session->set_flashdata('url', $this->uri->uri_string());
            redirect('login');
        }
        $data->user = $this->user_model->get_user($_SESSION['user_id']);

        $this->load->helper('form');


        $this->form_validation->set_rules('fullname', 'Full Name', 'trim|required|alpha_numeric_spaces|min_length[4]|max_length[30]');

        $email = $this->input->post('email');
        if ($email != $data->user->email){
            $this->form_validation->set_rules('email', 'Email', 'trim|required|valid_email|is_unique[users.email]');
        }





        if ($this->form_validation->run() === false){


            $this->load->view('templates/header');
            $this->load->view('user/edit_profile', $data);
            $this->load->view('templates/footer');
        }else{
            $user = array(
                'fullname' => $this->input->post('fullname'),
                'email'    => $this->input->post('email'),
            );
            $password = $this->input->post('password');
            if ($this->user_model->update($_SESSION['user_id'], $user, $password)){
                redirect('user');
            }


            $data->error = 'Incorrect Password, Profile update failed';

            // send error to the view
            $this->load->view('templates/header');
            $this->load->view('user/edit_profile', $data);
            $this->load->view('templates/footer');
        }



    }

    public function forgot_password(){
        $data = new stdClass();

        $this->load->helper('form');
        $this->load->library('form_validation');

        $this->form_validation->set_rules('userdetail', 'Email or Username', 'trim|required|min_length[2]|max_length[30]');

        if ($this->form_validation->run() == false) {

            // validation not ok, send validation errors to the view
            $this->load->view('templates/header');
            $this->load->view('user/request_password', $data);
            $this->load->view('templates/footer');

        } else {

            // set variables from the form
            $email = $this->input->post('userdetail');


            if ($this->user_model->send_password_reset_link($email)) {

                $data->message = "Password Reset link sent, Check your Email";
                $data->messagetype = "alert-success";
                $this->load->view('templates/header');
                $this->load->view('user/request_password', $data);
                $this->load->view('templates/footer');

            } else {

                $data->message = 'Unknown username or email.';

                $this->load->view('templates/header');
                $this->load->view('user/request_password', $data);
                $this->load->view('templates/footer');

            }

        }
    }

    public function login(){
        if (!empty($this->session->flashdata('url') )){
            //reset the flashdata
            $this->session->set_flashdata($this->session->flashdata());
        }
        $data = new stdClass();

        $this->load->helper('form');
        $this->load->library('form_validation');

        $this->form_validation->set_rules('email', 'Email', 'trim|required|valid_email');
        $this->form_validation->set_rules('password', 'Password', 'required');

        if ($this->form_validation->run() == false) {

            // validation not ok, send validation errors to the view
            $this->load->view('templates/header');
            $this->load->view('user/login', $data);
            $this->load->view('templates/footer');

        } else {

            // set variables from the form
            $email = $this->input->post('email');
            $password = $this->input->post('password');

            if ($this->user_model->resolve_user_login($email, $password)) {

                $user_id = $this->user_model->get_user_id_from_email($email);
                $user    = $this->user_model->get_user($user_id);

                // set session user datas
                $_SESSION['user_id']      = (int)$user->id;
                $_SESSION['logged_in']    = (bool)true;


                // user login ok
                // $this->load->view('header');
                // $this->load->view('user/dashboard', $data);
                // $this->load->view('footer');
                if (!empty($this->session->flashdata('url'))){
                    redirect($this->session->flashdata('url'));
                }
                redirect('user/');

            } else {

                // login failed
                $data->message = 'Wrong username or password.';

                // send error to the view
                $this->load->view('templates/header');
                $this->load->view('user/login', $data);
                $this->load->view('templates/footer');

            }

        }
    }

    public function logout(){

        if (isset($_SESSION['logged_in']) && $_SESSION['logged_in'] === true) {

            // remove session datas
            foreach ($_SESSION as $key => $value) {
                unset($_SESSION[$key]);
            }

            // user logout ok
        }
        redirect('login');

    }

    public function register(){
        if (!empty($this->session->flashdata('url') )){
            //reset the flashdata
            $this->session->set_flashdata($this->session->flashdata());
        }
        $data = new stdClass();

        $this->load->helper('form');

        $data->title = "Signup";

        $this->form_validation->set_rules('fullname', 'Full Name', 'trim|required|alpha_numeric_spaces|min_length[4]|max_length[30]');
        $this->form_validation->set_rules('email', 'Email', 'trim|required|valid_email|is_unique[users.email]');
        $this->form_validation->set_rules('password', 'Password', 'trim|required|min_length[6]');
        $this->form_validation->set_rules('password_confirm', 'Confirm Password', 'trim|required|min_length[6]|matches[password]');

        if ($this->form_validation->run() === false){

            // validation not ok, send validation errors to the view
            $this->load->view('templates/header');
            $this->load->view('user/register', $data);
            $this->load->view('templates/footer');

        }else{

            // set variables from the form
            $user = array(
                'fullname' => $this->input->post('fullname'),
                'email'    => $this->input->post('email'),
            );
            $password = $this->input->post('password');

            if ($this->user_model->create_user($user, $password)){

                $data->messagetitle = "Registration Successful";
                $data->messagedetails = "Please Login to continue";
                // user creation ok
                if (!empty($this->session->flashdata('url'))){
                    redirect($this->session->flashdata('url'));
                }
                $this->load->view('templates/header');
                $this->load->view('message_view', $data);
                $this->load->view('templates/footer');

            }else{

                // user creation failed, this should never happen
                $data->error = 'There was a problem creating your new account. Please try again.';

                // send error to the view
                $this->load->view('templates/header');
                $this->load->view('user/register', $data);
                $this->load->view('templates/footer');

            }

        }
    }

    public function reset_password($user_id = false, $hash=false){
        if ( $user_id == false ){
            show_404();
        }
        $data = new stdClass();
        $data->user = $this->user_model->get_user($user_id);
        if ( empty($data->user) ){
            show_404();
        }
        if ($hash != md5("reset#Csk".$data->user->hash)){
            show_404();
        }
        $this->form_validation->set_rules('password', 'Password', 'trim|required|min_length[4]|max_length[30]');
        $this->form_validation->set_rules('password_conf', 'Confirm new password', 'trim|required|min_length[4]|max_length[30]|matches[password]');
        if ($this->form_validation->run == false){
            $this->load->view('change_password', $data);
        }else{

            $update_data = array(
                'password' => $this->input->post('password'),
            );


            if ($this->user_model->update_password($data->user->id, $update_data)){

                $data->message = "Password Successfully changed, please login";
                $data->messagetype = "alert-success";
                $this->load->view('change_password', $data);
            }

            $data->message = 'Please try again';

            // send error to the view

            $this->load->view('change_password', $data);

        }



    }


}