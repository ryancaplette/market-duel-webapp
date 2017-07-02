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
                <form class="form-horizontal" action="/stock-order" role="form" method="post">

                    <div class="form-group">
                        <div class="col-sm-10">
                            <label for="ticker" class="control-label">Ticker Symbol: </label>
                            <input type="text" class="form-control" name="ticker" id="ticker" placeholder="Ticker Symbol" value="${ticker!}" />
                            <label for="quantity" class="control-label">Quantity: </label>
                            <input type="text" class="form-control" name="quantity" id="quantity" placeholder="Quantity" value="0" />
                            <input type="hidden" class="form-control" name="pfId" id="pfId" value="${pfId!}" />
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button type="submit" class="btn btn-default">Buy Stock</button>
                        </div>
                    </div>

                </form>

                <div class="col-md-12">
                    <table class="table">
                        <thead>
                        <tr>
                            <th>
                                Stock Symbol
                            </th>
                            <th>
                                Share Quantity
                            </th>
                            <th>
                                Initial Share Price
                            </th>
                            <th>
                                Current Share Price
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
                            <#if stockHoldings??>
                            <b>Portfolio for match: </b><br/>
                                <#list stockHoldings as stock>
                                    <tr>
                                        <td>
                                            <#if stock.ticker??>
                                                ${stock.ticker}
                                            </#if>
                                        </td>
                                        <td>
                                            <#if stock.shares??>
                                                ${stock.shares}
                                            </#if>
                                        </td>
                                        <td>
                                            <#if stock.purchasePrice??>
                                                ${stock.purchasePrice}
                                            </#if>
                                        </td>
                                        <td>
                                            $200.21 <- example
                                        </td>
                                        <td>
                                            +$65.83 <- example
                                        </td>
                                        <td>
                                            0.00% <- example
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