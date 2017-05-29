<#import "masterTemplate.ftl" as layout />

<@layout.masterTemplate title="Account Dashboard">

<div class="row">
    <div class="col-xs-11">
        <h3>${pageTitle}</h3>
        <div class="panel panel-info">
            <div class="panel-heading">
                <h3 class="panel-title">Welcome back, <strong>${user.username}</strong> (${user.email}).</h3>
            </div>
        </div>
    </div>
</div>

</@layout.masterTemplate>