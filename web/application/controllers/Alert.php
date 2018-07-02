<?php
    defined('BASEPATH') OR exit('No direct script access allowed');
    class Alert extends CI_Controller {
        /**
        * Index Page for this controller.
        *
        * Maps to the following URL
        * 		http://example.com/index.php/welcome
        *	- or -
        * 		http://example.com/index.php/welcome/index
        *	- or -
        * Since this controller is set as the default controller in
        * config/routes.php, it's displayed at http://example.com/
        *
        * So any other public methods not prefixed with an underscore will
        * map to /index.php/welcome/<method_name>
        * @see https://codeigniter.com/user_guide/general/urls.html
        */
        public function __construct(){
            parent::__construct();
            $this->load->model('alert_model');
            $this->load->helper('json_output_helper');
            $this->load->library(array('session'));
            if (!isset($_SESSION['logged_in']) || $_SESSION['logged_in'] !== true){
                $this->session->set_flashdata('url', $this->uri->uri_string());
                redirect('login');
            }
        }

        public function index(){
            
            $data = new stdClass();
            $data->alerts = $this->alert_model->get_all();
            $this->load->view('templates/header', $data);
            $this->load->view('alerts/alert', $data);
            $this->load->view('templates/footer', $data);
            // var_dump($data->alerts);
        }

        public function view($id = false){
            $data = new stdClass();
            if(!$id){
                show_404();
            }
            $results = $this->alert_model->get_by_id($id);
            if($results){
                // $data->results = $results;
                $this->load->view('modal', $results);
            }else{
                show_404();
            }
        }
        public function complete($id = false){
            $data = new stdClass();
            if(!$id){
                show_404();
            }
            $results = $this->alert_model->get_by_id($id);
            if($results){
                $data = array(
                    'status' => 1,
                );
                $logs = @json_decode($results->logs, true);
                if(!($logs && is_array($logs))){
                    $logs = array();
                }
                $logs[] = array('activity'=>'admin submitted', 'admin'=>$_SESSION['user_id'], 'timestamp'=> time());
                $data['logs'] = json_encode($logs);
                $this->alert_model->update_alert($id, $data);
                redirect('alert');
            }else{
                show_404();
            }
        }
    
}