<?php defined('BASEPATH') OR exit('No direct script access allowed'); ?>
<div class="container">
    <div class="row">


        <div class="col-md-12">
            <?php if (isset($messagetitle)) : ?>
            <div class="page-header">
                <h1><?= $messagetitle ?></h1>
            </div>
            <?php endif; ?>

            <?php if (isset($error)) : ?>

                    <div class="alert alert-danger" role="alert">
                        <?= $error ?>
                    </div>

            <?php endif; ?>

            <?php if (isset($messagedetails)) : ?>
                <div class="col-md-12">
                    <div class="alert alert-success" role="success">
                        <?= $messagedetails ?>
                    </div>
                </div>
            <?php endif; ?>
        </div>
    </div><!-- .row -->
</div><!-- .container -->