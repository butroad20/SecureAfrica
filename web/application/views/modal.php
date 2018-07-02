<div id="myModal" class="modal fade" role="dialog">
  <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title"><?= ucwords("$type report")?></h4>
      </div>
      <div class="modal-body">
        <div class="">
            <table class="table table-striped table-responsive">
                <tr>
                    <td>Date</td>
                    <td><?= $timestamp?></td>
                </tr>
                
                <tr>
                    <td>ID</td>
                    <td><?= $id?></td>
                </tr>

                <tr>
                    <td>Source</td>
                    <td><?= $source?></td>
                </tr>
                <tr>
                    <td>Type</td>
                    <td><?= $type?></td>
                </tr>
                <tr>
                    <td>Status</td>
                    <?php if($status == 0): ?><td> <button class="btn btn-danger btn-xs">Active</button></td>
                    <?php elseif($status == 1): ?><td><button class="btn btn-warning btn-xs">Processing</button></td>
                    <?php elseif($status == 2): ?><td><button class="btn btn-success btn-xs">Success</button></td>
                    <?php endif;?>
                </tr>
                <tr>
                    <td>Address</td>
                    <td><?= "<small>address</small>: $address</br> <small>local government</small>: $lga</br> <small>state</small>: $state"?></td>
                </tr>
                <tr>
                    <td>Message</td>
                    <td><?= $message?></td>
                </tr>
                
            </table>
        </div>
      </div>
      <div class="modal-footer">
        <a href="<?= base_url('alert/complete/'.$id)?>"><button type="button" class="btn btn-success">Process</button><a>
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>

  </div>

</div>
