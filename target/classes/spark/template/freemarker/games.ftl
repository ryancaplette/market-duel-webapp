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

            <div class="form-group">
                <div class="">
                    <button type="submit" class="btn btn-default">Create Game</button>
                </div>
            </div>

<!-- todo: implement for each player, show each player in DB in row-->
 <div class="col-md-8">
        <div class="container-fluid">
    <div class="row">
        <div class="col-md-12">
            <table class="table">
                <thead>
                    <tr>
                        <th>
                            Game ID
                        </th>
                        <th>
                            Start Date
                        </th>
                        <th>
                            End Date
                        </th>
                        <th>
                            Current Players
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
                    <tr>
                        <td>
                            <a href="#">1</a>
                        </td>
                        <td>
                            Jun 29, 2017
                        </td>
                        <td>
                            Jul 7, 2017
                        </td>
                        <td>
                            8
                        </td>
                        <td>
                            Private
                        </td>
                        <td>
                            <button type="submit" class="btn btn-success">Join</button>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <a href="#">2</a>
                        </td>
                        <td>
                            Jun 30, 2017
                        </td>
                        <td>
                            Jul 8, 2017
                        </td>
                        <td>
                            10
                        </td>
                        <td>
                            Public
                        </td>
                        <td>
                            <button type="submit" class="btn btn-success">Join</button>
                        </td>
                    </tr>
                    <tr class="active">
                        <td>
                            <a href="#">3</a>
                        </td>
                        <td>
                            Jun 31, 2017
                        </td>
                        <td>
                            Jul 9, 2017
                        </td>
                        <td>
                            3
                        </td>
                        <td>
                            Public
                        </td>
                        <td>
                            <button type="submit" class="btn btn-danger">Leave</button>
                        </td>
                    </tr>
                     <tr>
                        <td>
                            <a href="#">3</a>
                        </td>
                        <td>
                            Apr 31, 2017
                        </td>
                        <td>
                            Apr 9, 2017
                        </td>
                        <td>
                            10
                        </td>
                        <td>
                            Completed
                        </td>
                        <td>
                            <button type="submit" class="btn btn-info">Results</button>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>           












<!-- will use to implement dynamic table fill
    <div class="col-md-8">
        <div>
        <#if playerGamesList??>
            <b>Games Player Belongs to:</b><br/>
            <#list playerGamesList as game>
                Game ID: ${game.gameId}  Game Start: ${game.firstMatchStart}<br/>
            </#list>
        </#if>
        </div>
        
        <br/>

        <div>
        <#if isJoinableGamesList??>
            <form class="form-horizontal" action="/games" role="form" method="post">

                <div class="form-group">
                    <label for="joinable-games" class="col-sm-2 control-label">Joinable Games: </label>
                    <select name="join-game">
                        <#list joinableGamesList as game>
                            <option value="${game.gameId}">Game ID: ${game.gameId}  |  Game Start: ${game.firstMatchStart}</option>
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
            <b>There are no joinable games at this time.</b><br/>
        </#if>            
        </div>
    </div>-->
</div>

</br></br>

</@layout.masterTemplate>