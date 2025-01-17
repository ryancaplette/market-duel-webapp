<#import "masterTemplate.ftl" as layout />
<@layout.masterTemplate title="Games">

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

    <form class="form-horizontal" action="/game-create" role="form" method="get">
        <button type="submit" class="btn btn-default">Create Game</button>
    </form>

<!-- todo: implement for each player, show each player in DB in row-->
 <div class="col-md-8">
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-12">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>
                                Game ID
                            </th>
                            <th>
                                Game Name
                            </th>
                            <th>
                                Draft Time
                            </th>
                            <th>
                                Start Date
                            </th>
                            <th>
                                End Date
                            </th>
                            <th>
                                Players
                            </th>
                            <th>
                                Type
                            </th>
                            <th>
                                Actions
                            </th>
                        </tr>
                    </thead>
                    <tbody>
                        <#if playerGamesList??>
                            <#list playerGamesList as game>
                                <tr class="active">
                                    <td>
                                        <a href="/game-detail?id=${game.gameId}">${game.gameId}</a>
                                    </td>
                                    <td>
                                        <#if game.gameName??>
                                            <a href="/game-detail?id=${game.gameId}">${game.gameName}</a>
                                        </#if>
                                    </td>
                                    <td>
                                        <#if game.draftTime??>
                                        ${game.draftTime}
                                        </#if>
                                    </td>
                                    <td>
                                        ${game.firstMatchStart}
                                    </td>
                                    <td>
                                        ${game.getGameEndDate()?date}
                                    </td>
                                    <td>
                                    	${game.getPlayersInGame()}/10
                                    </td>
                                    <td>
                                        ${game.type}
                                    </td>
                                    <td>
                                        <form class="form-horizontal" action="/games" role="form" method="post">
                                            <div class="form-group">
                                                    <input type="hidden" class="form-control" name="leave-game" id="leave-game" value="${game.gameId!}" />
                                                    <button type="submit" class="btn btn-danger">Leave</button>
                                            </div>
                                        </form>
                                    </td>
                                </tr>
                            </#list>
                        </#if>

                        <#if joinableGamesList??>
                            <#list joinableGamesList as game>
                                <tr>
                                    <td>
                                        <a href="/game-detail?id=${game.gameId}">${game.gameId}</a>
                                    </td>
                                    <td>
                                        <#if game.gameName??>
                                            <a href="/game-detail?id=${game.gameId}">${game.gameName}</a>
                                        </#if>
                                    </td>
                                    <td>
                                        <#if game.draftTime??>
                                        ${game.draftTime}
                                        </#if>
                                    </td>
                                    <td>
                                        ${game.firstMatchStart}
                                    </td>
                                    <td>
                                        ${game.getGameEndDate()?date}
                                    </td>
                                    <td>
                                        ${game.getPlayersInGame()}/10
                                    </td>
                                    <td>
                                        ${game.type}
                                    </td>
                                    <td>
                                        <form class="form-horizontal" action="/games" role="form" method="post">
                                            <div class="form-group">
                                                    <input type="hidden" class="form-control" name="join-game" id="join-game" value="${game.gameId!}" />
                                                    <button type="submit" class="btn btn-success">Join</button>
                                            </div>
                                        </form>
                                    </td>
                                </tr>
                            </#list>
                        </#if>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>


</br></br>

</@layout.masterTemplate>