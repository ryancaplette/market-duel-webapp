<#import "masterTemplate.ftl" as layout />

<@layout.masterTemplate title="Register">

    <#if error??>
        <div class="alert alert-danger">
            ${error}
        </div>
    </#if>

    <h3>Sign Up</h3>

    <form class="form-horizontal" action="/register" role="form" method="post">

        <div class="form-group">
            <label for="username" class="col-sm-2 control-label">Username: </label>
            <div class="col-sm-10">
                <input type="text" class="form-control" name="username" placeholder="Username" value="${username!}" />
            </div>
        </div>

        <div class="form-group">
            <label for="email" class="col-sm-2 control-label">E-Mail: </label>
            <div class="col-sm-10">
                <input type="email" class="form-control" name="email" placeholder="E-Mail" value="${email!}" />
            </div>
        </div>

        <div class="form-group">
            <label for="password" class="col-sm-2 control-label">Password: </label>
            <div class="col-sm-10">
                <input type="password" class="form-control" name="password" placeholder="Password" />
            </div>
        </div>

        <div class="form-group">
            <label for="password2" class="col-sm-2 control-label">Verify Password:</label>
            <div class="col-sm-10">
                <input type="password" class="form-control" name="password2" placeholder="Password"/>
            </div>
        </div>

        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-default">Register Account</button>
            </div>
        </div>
  </form>
</@layout.masterTemplate>