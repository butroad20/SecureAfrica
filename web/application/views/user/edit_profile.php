<?php defined('BASEPATH') OR exit('No direct script access allowed'); ?>
<div class="container">
    <div class="row col-md-8 col-md-offset-2">
        <?php if (isset($error)) : ?>
            <div class="col-md-12">
                <div class="alert alert-danger" role="alert">
                    <?= $error ?>
                </div>
            </div>
        <?php endif; ?>

        <div class="col-md-12">
            <div class="page-header">
                <h1>Edit Profile</h1>
            </div>
            <?= form_open() ?>
            <div class="form-group">
                <?php if (form_error('fullname') != null): ?>
                    <div class="alert alert-danger " role="alert">
                        <?= form_error('fullname'); ?>
                    </div>
                <?php endif; ?>
                <label for="fullname">Full Name</label>
                <input type="text" class="form-control" id="fullname" name="fullname" value="<?= $user->fullname ?>" placeholder="Your full name" required>
            </div>
            <div class="form-group">
                <?php if (form_error('email') != null): ?>
                    <div class="alert alert-danger" role="alert">
                        <?= form_error('email'); ?>
                    </div>
                <?php endif; ?>

                <label for="email">Email</label>
                <input type="email" class="form-control" id="email" name="email" value="<?= $user->email ?>" placeholder="Enter your email" required>
                <p class="help-block">A valid email address</p>
            </div>

            <div class="form-group">
                <?php if (form_error('password') != null): ?>
                    <div class="alert alert-danger" role="alert">
                        <?= form_error('password'); ?>
                    </div>
                <?php endif; ?>
                <label for="password">Password:</label>
                <input type="password" class="form-control" id="password" name="password"  required>
                <p class="help-block">Enter your password to save</p>
            </div>

            <div class="form-group">
                <input type="submit" class="btn btn-default" value="Save">
            </div>
            </form>
        </div>
    </div><!-- .row -->
</div><!-- .container -->