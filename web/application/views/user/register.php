<?php defined('BASEPATH') OR exit('No direct script access allowed'); ?>
<div class="container">
    <div class="row col-md-6 col-md-offset-3">
        <?php if (isset($error)) : ?>
            <div class="col-md-12">
                <div class="alert alert-danger" role="alert">
                    <?= $error ?>
                </div>
            </div>
        <?php endif; ?>

        <div class="col-md-12">
            <div class="page-header">
                <h1>Register</h1>
            </div>
            <?= form_open() ?>
            <div class="form-group">
                <?php if (form_error('fullname') != null): ?>
                    <div class="alert alert-danger " role="alert">
                        <?= form_error('fullname'); ?>
                    </div>
                <?php endif; ?>
                <label for="fullname">Full Name</label>
                <input type="text" class="form-control" id="fullname" name="fullname" value="<?= set_value('fullname') ?>" placeholder="Your full name" required>
            </div>
            <div class="form-group">
                <?php if (form_error('email') != null): ?>
                    <div class="alert alert-danger" role="alert">
                        <?= form_error('email'); ?>
                    </div>
                <?php endif; ?>

                <label for="email">Email</label>
                <input type="email" class="form-control" id="email" name="email" value="<?= set_value('email') ?>" placeholder="Enter your email" required>
                <p class="help-block">A valid email address</p>
            </div>


            <div class="form-group">
                <?php if (form_error('password') != null): ?>
                <div class="alert alert-danger" role="alert">
                    <?= form_error('password'); ?>
                </div>
                <?php endif; ?>
                <label for="password">Password</label>
                <input type="password" class="form-control" id="password" name="password" placeholder="Enter a password" required>
                <p class="help-block">At least 6 characters</p>
            </div>
            <div class="form-group">
                <?php if (form_error('password_confirm') != null): ?>
                <div class="alert alert-danger" role="alert">
                    <?= form_error('password_confirm'); ?>
                </div>
                <?php endif; ?>
                <label for="password_confirm">Confirm password</label>
                <input type="password" class="form-control" id="password_confirm" name="password_confirm" placeholder="Confirm your password" required>
                <p class="help-block">Must match your password</p>
            </div>
            <div class="form-group">
                <input type="submit" class="btn btn-default" value="Register">
            </div>
            </form>
        </div>
    </div><!-- .row -->
</div><!-- .container -->