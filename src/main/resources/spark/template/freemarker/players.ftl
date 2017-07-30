<#import "masterTemplate.ftl" as layout />
<@layout.masterTemplate title="Players">

<div class="row">
    <div class="col-xs-11">
        <h3>${pageTitle}</h3>
    </div>
</div>
<div class="row">
        <div class="col-md-3">
            <ul class="nav nav-stacked nav-pills">
                <li><a href="/account">Account Dashboard</a></li>
                <li><a href="/your-games">Your Games</a></li>
                <li><a href="/available-games">Available Games</a></li>
                <li><a href="/history">History</a></li>
                <li><a href="/portfolios">Portfolios</a></li>
                <li class="active"><a href="/players">Players</a></li>
                <li><a href="/alerts">Alerts</a></li>
            </ul>
        </div>

    <div class="col-md-8">
        


<!-- todo: implement for each player, show each player in DB in row-->
 <div class="col-md-8">
        <div class="container-fluid">
    <div class="row">
        <div class="col-md-12">
        
        <b>Most Wins Leaderboard: </b>
        
            <table class="table">
                <thead>
                    <tr>
                        <th>
                            Player
                        </th>
                        <th>
                            Gain/Loss Lifetime
                        </th>
                        <th>
                            Wins
                        </th>
                        <th>
                            Losses
                        </th>
                        <th>
                            % Win
                        </th>
                    </tr>
                </thead>
	            <tbody>
	                <#list overallLeaders as player>
                        <tr>
                            <td>
                                <#if player.username??>
                                    ${player.username}
                                </#if>
                            </td>
                            <td>
                                <#if player.totalProfit??>
                                    ${player.totalProfit}
                                </#if>
                            </td>
                            <td>
                                <#if player.numWins??>
                                    ${player.numWins}
                                </#if>
                            </td>
                            <td>
                                ${player.numGamesPlayed-player.numWins}
                            </td>
                            <td>
                            	<#if (player.numGamesPlayed gt 0)>
                            		${player.numWins / player.numGamesPlayed}%
                            	<#else>
                                	0.0%
                            	</#if>
                            </td>
                        </tr>
                    </#list>
                </tbody>
            </table>
        </div>
    </div>
</div>           






    </div>
</div>

</br></br>

</@layout.masterTemplate>