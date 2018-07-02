<?php defined('BASEPATH') OR exit('No direct script access allowed'); ?>
<div class="container">
    <div class="row col-md-10 col-md-offset-1">

        <div class="col-md-12">
            <div class="page-header">
                <h1>Profile <a href="<?= base_url('user/edit')?>" class="btn btn-xs btn-success pull-right" >Edit</a></h1>

            </div>
            <table class="table table-striped">

                <tr>
                    <td>Full Name: </td>
                    <td><?= $user->fullname ?></td>

                </tr>
                <tr>
                    <td>Email: </td>
                    <td><?= $user->email ?></td>

                </tr>
                <tr>
                    <td>Date Joined: </td>
                    <td><?= $user->date_joined ?></td>

                </tr>
                

            </table>
            <a href="<?= base_url('logout') ?>">Logout</a>
        </div>
    </div><!-- .row -->
</div><!-- .container -->