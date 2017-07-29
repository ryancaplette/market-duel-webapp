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
                    <tr>
                        <td>
                            <a href="#">Boyd Mitchell</a>
                        </td>
                        <td>
                            +$34838.45
                        </td>
                        <td>
                            345
                        </td>
                        <td>
                            12
                        </td>
                        <td>
                            76.54%
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <a href="#">Lesley Kenzie</a>
                        </td>
                        <td>
                            +$2353.34
                        </td>
                        <td>
                            45
                        </td>
                        <td>
                            23
                        </td>
                        <td>
                            50.34%
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <a href="#">Woody Johnie</a>
                        </td>
                        <td>
                            -$3334.34
                        </td>
                        <td>
                            6
                        </td>
                        <td>
                            343
                        </td>
                        <td>
                            2.45%
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>           






    </div>
</div>

</br></br>

</@layout.masterTemplate>