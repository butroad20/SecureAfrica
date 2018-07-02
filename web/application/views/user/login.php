<?php defined('BASEPATH') OR exit('No direct script access allowed'); ?>
<div class="container">
    <div class="row col-md-6 col-md-offset-3">
        <?php if (validation_errors()) : ?>
            <div class="col-md-12">
                <div class="alert alert-danger" role="alert">
                    <?= validation_errors() ?>
                </div>
            </div>
        <?php endif; ?>
        <?php if (isset($message)) : ?>
            <div class="col-md-12">
                <div class="alert <?= isset($messagetype) ? $messagetype : "alert-danger" ?>" role="alert">
                    <?= $message ?>
                </div>
            </div>
        <?php endif; ?>
        <div class="col-md-12">
            <div class="page-header">
                <h1>Login</h1>
            </div>
            <?= form_open() ?>
                <div class="form-group">
                    <label for="email">Email</label>
                    <input type="email" class="form-control" id="email" name="email" placeholder="Your Email" value="<?= set_value('email') ?>" required>
                </div>
                <div class="form-group ">
                    <label for="password">Password</label>
                    <input type="password" class="form-control" id="password" name="password" placeholder="Your password" required>
                </div>



                <div class="form-group">

                    <input type="submit" class="btn btn-default" value="Login">
                </div>
                <div class="form-group col-xs-6 text-right">
                    <a href="<?= base_url('forgot_password') ?>">Forgot Password?</a>

                </div>
            </form>
        </div>
    </div><!-- .row -->
</div><!-- .container -->