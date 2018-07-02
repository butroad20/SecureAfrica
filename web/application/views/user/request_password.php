<?php defined('BASEPATH') OR exit('No direct script access allowed'); ?>
<div class="container">
    <div class="row col-md-6 col-md-offset-3">
        <div class="col-md-12">
            <div class="page-header">
                <h1>Forgot Password</h1>
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
                    <label for="username">Email</label>
                    <input type="text" class="form-control" id="userdetail" name="userdetail" required>
                    <p class="help-block">We will send you an email that contains the link to reset your password</p>
                </div>

                <div class="form-group">
                    <input type="submit" class="btn btn-default" value="Reset">
                </div>
            </form>

        </div>
    </div><!-- .row -->
</div><!-- .container -->


