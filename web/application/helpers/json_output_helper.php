<?php
defined('BASEPATH') OR exit('No direct script access allow');

    if(!function_exists('json_output')) {


        function json_output($statusHeader, $response)
        {
            $ci =& get_instance();
            $ci->output->set_content_type('application/json');
            $ci->output->set_status_header($statusHeader);
            $response = array('status'=> $statusHeader) + $response;
            return $ci->output->set_output(json_encode($response));
        }

    }