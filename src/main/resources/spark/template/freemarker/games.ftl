<#import "masterTemplate.ftl" as layout />

<@layout.masterTemplate title="Games">

<div class="row">
    <div class="col-xs-11">
        <h3>${pageTitle}</h3>

        <div class="col-xs-11">
            <#if gamesList??>
                <b>Games for Player:</b><br/>
            	<#list gamesList as game>
					Game ID: ${game.gameId}  Game Start: ${game.firstMatchStart}<br/>
				</#list>
            </#if>
        </div>
    </div>
</div>

</@layout.masterTemplate>