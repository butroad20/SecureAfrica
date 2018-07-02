<?php defined('BASEPATH') OR exit('No direct script access allowed'); ?>
<div class="container">
    <div class="row col-md-10 col-md-offset-1">

        <div class="col-md-12">
            <div class="page-header">
                <h1>Alerts</h1>
            </div>
            <div class="">
            <div class="col-md-8">
                <input class="form-control" id="myInput" placeholder="Search for a category, place, date, etc" type="text">
            </div>
            <div class="form-group col-md-4">
            <label class="sr-only" for="statusFilter">Filter:</label>
                <select name="statusFilter" id="statusFilter" class="form-control">
                    <option value="All">All</option>
                    <option value="Active">Active</option>
                    <option value="Processing">Processing</option>
                    <option value="Completed">Completed</option>
                </select>
            </div>
            </div>
            <table class="table table-striped table-responsive">

                <thead>
                    <tr>
                    <td>S/N</td>
                    <td>Type</td>
                    <td>Status</td>
                    <td>Phone</td>
                    <td>Location</td>
                    <td>GPS</td>
                    <td>Date</td>
                    </tr>
                </thead>
                <tbody id="alertTable">
                <?php $index = 1;
                foreach ($alerts as $alert):?>
                <tr>
                    <td><?= $index?></td>
                    <td>   
                <a href="<?= base_url('alert/view/'.$alert->id)?>" class="modalTrigger">
                    <?= $alert->type?>
                </a>
                    </td>

                    
                    <?php if($alert->status == 0): ?><td> <button class="btn btn-danger btn-xs">Active</button></td>
                    <?php elseif($alert->status == 1): ?><td><button class="btn btn-warning btn-xs">Processing</button></td>
                    <?php elseif($alert->status == 2): ?><td><button class="btn btn-success btn-xs">Success</button></td>
                    <?php endif;?>
                    <td><?= $alert->phone?></td>                    
                    <td><?= "$alert->state"?></td>
                    <td><?= $alert->long_lat?></td>
                    <td><?= $alert->timestamp?></td>
                </tr>
                <?php $index+=1;
                endforeach;?>
                </tbody>
            </table>

        </div>
    </div><!-- .row -->
</div><!-- .container -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
    $(document).ready(function(){
  $("#myInput").on("keyup", function() {
    var value = $(this).val().toLowerCase();
    $("#alertTable tr").filter(function() {
      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
    });
  });
});
     (function(){
        // Create jQuery body object
        var $body = $('body'),

        // Use a tags with 'class="modalTrigger"' as the triggers
        $modalTriggers = $('a.modalTrigger'),

        // Trigger event handler
        openModal = function(evt) {
              var $trigger = $(this),                  // Trigger jQuery object

              modalPath = $trigger.attr('href'),       // Modal path is href of trigger

              $newModal,                               // Declare modal variable

              removeModal = function(evt) {            // Remove modal handler
                    $newModal.off('hidden.bs.modal');  // Turn off 'hide' event
                    $newModal.remove();                // Remove modal from DOM
              },

              showModal = function(data) {             // Ajax complete event handler
                    $body.append(data);                // Add to DOM
                    $newModal = $('.modal').last();    // Modal jQuery object
                    $newModal.modal('show');           // Showtime!
                    $newModal.on('hidden.bs.modal',removeModal); // Remove modal from DOM on hide
              };

              $.get(modalPath,showModal);             // Ajax request

              evt.preventDefault();                   // Prevent default a tag behavior
        };

        $modalTriggers.on('click',openModal);         // Add event handlers
  }());

  var btn = document.getElementById("statusFilter");
	btn.onchange = function() {        
        var value = btn.value.toLowerCase();
        if(value == 'all'){
            value = '';
        }
		$("#alertTable tr").filter(function() {
            $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
        });
	}
</script>