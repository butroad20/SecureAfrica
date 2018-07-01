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
        }
        public function index(){
           $data = <<<XML
1 	ABIA 	08035415408 08079210003 08079210004 08079210005
2 	ADAMAWA 	08089671313
3 	AKWA_IBOM 	08039213071 08020913810
4 	ANAMBRA 	07039194332 08024922772 08075390511 08182951257
5 	BAUCHI 	08151849417 08127162434 08084763669 08073794920
6 	BAYELSA 	07034578208
7 	BENUE 	08066006475 08053039936 07075390677
8 	BORNO 	08068075581 08036071667 08123823322
9 	CROSS_RIVER 	08133568456 07053355415
10 	DELTA 	08036684974
11 	EBONYI 	07064515001 08125273721 08084704673
12 	EDO 	08037646272 08077773721 08067551618
13 	EKITI 	08062335577 07089310359
14 	ENUGU 	08032003702 08075390883 08086671202
15 	FCT_ABUJA 	07057337653 08061581938 08032003913
16 	GOMBE 	08150567771 08151855014
17 	IMO 	08034773600 08037037283
18 	JIGAWA 	08075391069 07089846285 08123821598
19 	KADUNA 	08123822284
20 	KANO 	08032419754 08123821575 064977004 064977005
21 	KATSINA 	08075391255 08075391250
22 	KEBBI 	08038797644 08075391307
23 	KOGI 	08075391335 07038329084
24 	KWARA 	07032069501 08125275046
25 	LAGOS 	07055462708 08035963919
26 	NASARAWA 	08123821571 07075391560
27 	NIGER 	08081777498 08127185198
28 	OGUN 	08032136765 08081770416
29 	ONDO 	07034313903 08075391808
30 	OSUN 	08075872433 08039537995 08123823981
31 	OYO 	08081768614 08150777888
32 	PLATEAU 	08126375938 08075391844 08038907662
33 	RIVERS 	08032003514 08073777717
34 	SOKOTO 	07068848035 08075391943
35 	TARABA 	08140089863 08073260267
36 	YOBE 	07039301585 08035067570
37 	ZAMFARA 	08106580123
XML;

        $data_arr = array_map(function($line){
            $linear = explode(' ', $line);
            return array('state'=> $linear[1], 'numbers'=> array_slice($linear, 1));
        }, explode(PHP_EOL, $data));

        $this->alert_model->save($data_arr);
    }

    
}