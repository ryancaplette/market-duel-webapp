<#import "masterTemplate.ftl" as layout />
<@layout.masterTemplate title="Games">
<#if message??>
<div class="alert alert-success">
${message}
</div>
</#if>
<#if error??>
<div class="alert alert-danger">
    <strong>Error:</strong> ${error}
</div>
</#if>
<div class="row">
    <div class="col-xs-11">
        <h3>${pageTitle}</h3>
    </div>
</div>
<div class="row">
    <div class="col-md-3">
        <ul class="nav nav-stacked nav-pills">
            <li>
                <a href="/account">Account Dashboard</a>
            </li>
            <li class="active">
                <a href="/games">Games</a>
            </li>
            <li>
                <a href="/portfolios">Portfolios</a>
            </li>
            <li>
                <a href="/players">Players</a>
            </li>
            <li>
                <a href="/alerts">Alerts</a>
            </li>
        </ul>
    </div>

 <div class="col-md-8">
    <div class="container-fluid">
        <div class="row">
            <form class="form-horizontal" action="/game-create" role="form" method="post">

                <div class="form-group">
                    <div class="col-sm-10">
                        <p>this only works for quick game / quick match right now</p>
                        <label for="ticker" class="control-label">Match Name: </label>
                        <input type="text" class="form-control" name="matchName" id="matchName" value="" />
                        <label for="ticker" class="control-label">Start Budget: </label>
                        <input type="text" class="form-control" name="budget" id="budget" value="0" />
                        <label for="ticker" class="control-label">Draft Date: </label>
                        <input type="text" class="form-control" name="draft" id="draft" value="" />
                        <label for="ticker" class="control-label">Start Date: (expects format yyyy-mm-dd)</label>
                        <input type="text" class="form-control" name="start" id="start" value="" />
                        <label for="ticker" class="control-label">Duration of Match (Number of Days): </label>
                        <input type="text" class="form-control" name="duration" id="duration" value="0" />
                        <input type="hidden" class="form-control" name="gameType" id="gameType" value="0" />
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-default">Create Quick Game</button>
                    </div>
                </div>

            </form>
        </div>
    </div>
</div>


</br></br>

</@layout.masterTemplate>