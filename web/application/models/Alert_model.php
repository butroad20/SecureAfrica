<?php
defined('BASEPATH') OR exit ('No direct script access allowed');

class Alert_model extends CI_Model{

    public function __construct()
    {
        parent::__construct();
        $this->load->database();

    }
    public function save($data){
        $all_data = array();
        foreach ($data as $phonedata){            
            $prepare_data = array_map(function($num) use($phonedata){ return array('state'=> trim(str_replace('_', ' ',$phonedata['state'])), 'number' => trim($num)); }, array_slice($phonedata['numbers'], 1));
            $all_data = array_merge($all_data, $prepare_data);
            // var_dump($prepare_data);
            // echo "<br>";
            // echo "<br>";
        }
        return $this->db->insert_batch('emergency_numbers', $all_data);
    }

    public function get_numbers($location = false){
        $this->db->select(array('number', 'state'));
        $this->db->from('emergency_numbers');
        if($location){
            $this->db->where('state', $location);
        }
        $this->db->where('status', 0);
        return $this->db->get()->result_array();
    }

    
    public function save_alert($data){
        $this->db->insert('alerts', $data);
        return $this->db->insert_id();
    }
    public function get_by_id($id){
        $this->db->from('alerts');
        $this->db->where('id', $id);
        return $this->db->get()->row();
    }
    
    public function update_alert($id, $data){
        // $this->db->set($data);
        $this->db->where('id', $id);
        return $this->db->update('alerts', $data);
    }
    
}