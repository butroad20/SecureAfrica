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
            $this->load->helper(array('json_output_helper'));
            $this->load->library('form_validation');
        }
        // public function index(){
        //    var_dump( $this->alert_model->get_by_id(2));
        // }
        public function get_numbers(){
            $method = $_SERVER['REQUEST_METHOD'];
            if ($method != 'GET'){
                return json_output(405, array('message'=>'Bad Request'));
            }else{
                $location = $this->input->get('from');
                json_output(200, array('response'=> $this->alert_model->get_numbers($location)));
            }
        }
        
        public function ussd_add(){
            $method = $_SERVER['REQUEST_METHOD'];
            if ($method != 'POST'){
                return json_output(405, array('message'=>'Bad Request'));
            }else{
                
                // $form_data = json_decode(file_get_contents('php://input'), true);
                $this->form_validation->set_error_delimiters('', '');
                $this->form_validation->set_rules('type', 'Type', 'trim|required|callback_type_check');
                $this->form_validation->set_rules('phone', 'Phone', 'trim|required');                
                if(!$this->form_validation->run()){
                    return json_output(403, array('error'=>'Incomplete Fields', 'message'=>validation_errors()));
                }else{
                    $data = array(
                        'source' => 'ussd',
                        'type' => $this->input->post('type'),
                        'phone'=> $this->input->post('phone'),
                    );
                    if($id = $this->alert_model->save_alert($data)){
                        return json_output(200, array('message'=>'success', 'id'=> $id));
                    }else{
                        return json_output(503, array('message'=>'An error occured'));
                    }
                    
                }
                
            }
        }

        public function ussd_update($phone = false){
            $method = $_SERVER['REQUEST_METHOD'];
            if ($method != 'POST'){
                return json_output(405, array('message'=>'Bad Request'));
            }else{
                if($phone == false){
                    return json_output(403, array('error'=>'Incomplete URL', 'message'=>'Missing Phone Number field'));
                }
                // $form_data = json_decode(file_get_contents('php://input'), true);
                $this->form_validation->set_error_delimiters('', '');
                // $this->form_validation->set_rules('type', 'Type', 'trim|required|callback_type_check');
                $this->form_validation->set_rules('state', 'State', 'trim');
                if(!$this->form_validation->run()){
                    return json_output(403, array('error'=>'Incomplete Fields', 'message'=>validation_errors()));
                }else{
                    $alert = $this->alert_model->get_by_phone($phone);                    
                    if($alert){
                        $id = $alert->id;
                        $data = array(
                            'state' => @$this->input->post('state'),
                            'lga'=> @$this->input->post('lga'),
                            'address'=> @$this->input->post('address'),
                            'message'=> @$this->input->post('message'),
                            'callable'=> @$this->input->post('callable'),
                        );

                        $logs = @json_decode($alert->logs, true);
                        if(!($logs && is_array($logs))){
                            $logs = array();
                        }
                        $logs[] = array('activity'=>'user update', 'timestamp'=> time(), 'data'=>array_filter($data,function($value) { return $value !== ''; }));
                        $data['logs'] = json_encode($logs);
                        if($this->alert_model->update_alert($id, $data)){
                            return json_output(200, array('message'=>'success', 'data'=> $this->alert_model->get_by_id($id)));
                        }else{
                            return json_output(503, array('message'=>'An error occured'));
                        }
                    }else{
                        return json_output(403, array('message'=>'Unrecognized ID'));
                    }
                }
                
            }
        }

        public function app_add(){
            $method = $_SERVER['REQUEST_METHOD'];
            if ($method != 'POST'){
                return json_output(405, array('message'=>'Bad Request'));
            }else{
                
                // $form_data = json_decode(file_get_contents('php://input'), true);
                $this->form_validation->set_error_delimiters('', '');
                $this->form_validation->set_rules('type', 'Type', 'trim|required|callback_type_check');
                $this->form_validation->set_rules('long_lat', 'Location', 'trim|required');
                if(!$this->form_validation->run()){
                    return json_output(403, array('error'=>'Incomplete Fields', 'message'=>validation_errors()));
                }else{
                    $data = array(
                        'source' => 'app',
                        'type' => $this->input->post('type'),
                        'long_lat'=> $this->input->post('long_lat'),
                        'state' => @$this->input->post('state'),
                        'lga'=> @$this->input->post('lga'),
                        'address'=> @$this->input->post('address'),
                        'message'=> @$this->input->post('message'),
                        'callable'=> @$this->input->post('callable'),
                        'phone'=> @$this->input->post('phone'),
                    );
                    if($id = $this->alert_model->save_alert($data)){
                        return json_output(200, array('message'=>'success', 'id'=> $id, 'data'=> $data));
                    }else{
                        return json_output(503, array('message'=>'An error occured'));
                    }
                    
                }
                
            }
        }

        public function type_check($input){
            $types = array('fire', 'kidnap','rape', 'robbery', 'violence outbreak', 'accident');
            if (in_array( strtolower($input), $types)){
                return true;
            }else{
                $this->form_validation->set_message('type_check', 'Unrecognized Emergency Type');
                return false;
            }
        }
}
