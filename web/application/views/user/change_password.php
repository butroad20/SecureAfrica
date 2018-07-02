<?php defined('BASEPATH') OR exit('No direct script access allowed'); ?>
<div class="container">
    <div class="row col-md-8 col-md-offset-2">
        <div class="col-md-12">
            <div class="page-header">
                <h1>Reset Password</h1>
            </div>
            <?php if (validation_errors()) : ?>
                <div class="col-md-12">
                    <div class="alert alert-danger" role="alert">
                        <?= validation_errors() ?>
                    </div>
                </div>
            <?php endif; ?>
            <?php if (isset($message)) : ?>
                <div class="col-md-12">
                    <div class="alert <?= isset($messagetype) ? $messagetype : 'alert-danger'?>" role="alert">
                        <?= $message ?>
                    </div>
                </div>
            <?php endif; ?>

            <form name="form" method="post">
                <div class="form-group">
                    <label for="password">New Password</label>
                    <input type="password" class="form-control" id="password" name="password" required>

                </div>
                <div class="form-group">
                    <label for="password_conf">Confirm Password</label>
                    <input type="password" class="form-control" id="password_conf" name="password_conf" required>

                </div>

                <div class="form-group">
                    <input type="submit" class="btn btn-default" value="Save">
                </div>
            </form>

        </div>
    </div><!-- .row -->
</div><!-- .container -->


