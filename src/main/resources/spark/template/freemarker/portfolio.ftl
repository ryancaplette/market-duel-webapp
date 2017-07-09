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
                            Match ID
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
                        <th>
                            Actions
                        </th>
                    </tr>
                </thead>
                <tbody>
                    <#if portfolios??>
                        <#list portfolios as portfolio>
                            <tr>
                                <td>
                                    <a href="/portfolio-detail?id=${portfolio.portfolioId}">${portfolio.portfolioId}</a>
                                </td>
                                <td>
                                    <a href="#">${portfolio.matchId}</a>
                                </td>
                                <td>
                                    $9374.34 (placeholder)
                                </td>
                                <td>
                                    -$346.23 (placeholder)
                                </td>
                                <td>
                                    -3.53% (placeholder)
                                </td>
                                <td>
                                    <a href="/portfolio-detail?id=${portfolio.portfolioId}"><button type="submit" class="btn btn-success">View</button></a>
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
</div>

</br></br>

</@layout.masterTemplate>