<#import "masterTemplate.ftl" as layout />
<@layout.masterTemplate title="Portfolios">

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
            <li>
                <a href="/games">Games</a>
            </li>
            <li class="active">
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



    <!-- todo: implement for each portfolio wil display a row for each portfolio that the user (or all?)-->
    <div class="col-md-8">
        <div class="container-fluid">
    <div class="row">
        <div class="col-md-12">
            <table class="table">
                <thead>
                    <tr>
                        <th>
                            Portfolio ID
                        </th>
                        <th>
                            Game
                        </th>
                        <th>
                            Current Value
                        </th>
                        <th>
                            Gain/Loss
                        </th>
                        <th>
                            % Gain/Loss
                        </th>
                    </tr>
                </thead>
                <tbody>
                    <tr class="danger">
                        <td>
                            <a href="#">1</a>
                        </td>
                        <td>
                            <a href="#">Game 3</a>
                        </td>
                        <td>
                            $9374.34
                        </td>
                        <td>
                            -$346.23
                        </td>
                        <td>
                            -3.53%
                        </td>
                    </tr>
                    <tr class="success">
                        <td>
                            <a href="#">2</a>
                        </td>
                        <td>
                            <a href="#">Game 7</a>
                        </td>
                        <td>
                            $2353.34
                        </td>
                        <td>
                            +$854.44
                        </td>
                        <td>
                            +34.45%
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <a href="/portfolio-detail?id=3">3</a>
                        </td>
                        <td>
                            <a href="#">Game 8</a>
                        </td>
                        <td>
                            $10000.00
                        </td>
                        <td>
                            $0.00
                        </td>
                        <td>
                            0.00%
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