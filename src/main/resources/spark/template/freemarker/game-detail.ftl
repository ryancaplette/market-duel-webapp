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
<div class="row" style="margin-bottom: 30px;">
    <div class="col-xs-11">
        <h3>${pageTitle}</h3>

        <div class="col-xs-11">
            <#if playerGamesList??>
                <b>Games Player Belongs to:</b><br/>
            	<#list playerGamesList as game>
					Game ID: ${game.gameId}  Game Start: ${game.firstMatchStart}<br/>
				</#list>
            </#if>

        </div>

    </div>
</div>
<div class="row">
    <div class="col-xs-11">
    <#if isJoinableGamesList??>
        <form class="form-horizontal" action="/games" role="form" method="post">

            <div class="form-group">
                <label for="available-games" class="col-sm-2 control-label">Available Games: </label>
                <select name="join-game">
                    <#list joinableGamesList as game>
                        <option value="${game.gameId}">Game ID: ${game.gameId}  Game Start: ${game.firstMatchStart}</option>
                    </#list>

                </select>
            </div>

            <div class="form-group">
                <div class="">
                    <button type="submit" class="btn btn-default">Join Game</button>
                </div>
            </div>
            
        </form>
    <#else>
        <b>There are no Available Games at this time.</b><br/>
        <div class="form-group">
            <div class="">
                <button type="submit" class="btn btn-default">Create Game</button>
            </div>
        </div>
    </#if>
    </div>
</div>



</@layout.masterTemplate>