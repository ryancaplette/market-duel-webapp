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
                <li><a href="/account">Account Dashboard</a></li>
                <li class="active"><a href="/games">Games</a></li>
                <li><a href="/history">History</a></li>
                <li><a href="/portfolios">Portfolios</a></li>
                <li><a href="/players">Players</a></li>
                <li><a href="/alerts">Alerts</a></li>
            </ul>
        </div>

 <div class="col-md-8">
    <div class="container-fluid">
        <div class="row">
            <form class="form-horizontal" action="/game-create" role="form" method="post">

                <div class="form-group">
                    <div class="col-sm-10">
                        
                        <label class="control-label">Match Name: </label>
                        <input type="text" class="form-control" name="matchName" id="matchName" placeholder="Enter match name" required>
                        <label class="control-label">Initial Funds ($): </label>
                        <input type="text" class="form-control" name="budget" id="budget" placeholder="10000" required>
                        <label class="control-label">Draft Date: </label>
                        <input type="text" name="draft" id="draft" data-date-format="yyyy-mm-dd hh:ii:ss" placeholder="yyyy-mm-dd hh:mm:ss" required>
                            <script>
                            $('#draft').datetimepicker();
                            </script>

                        <label class="control-label">Start Date: </label>
                        <input type="text" name="start" id="start" data-date-format="yyyy-mm-dd" placeholder="yyyy-mm-dd" required>
                            <script>
                            $('#start').datetimepicker();
                            </script>

                        <label class="control-label">Duration of Match (Days): </label>
                        <input type="text" class="form-control" name="duration" id="duration" placeholder="7" required>
                        <input type="hidden" class="form-control" name="gameType" id="gameType" value="0" />
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm col-sm-10">
                        <button type="submit" class="btn btn-default">Create Quick Game</button>
                    </div>
                </div>

            </form>
        </div>
    </div>
</div>


</br></br>

</@layout.masterTemplate>