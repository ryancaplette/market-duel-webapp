<#import "masterTemplate.ftl" as layout />

<@layout.masterTemplate title="Research">
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

            <form class="form-horizontal" action="/research" role="form" method="post">

                <div class="form-group">
                    <label for="ticker" class="col-sm-2 control-label">Ticker Symbol: </label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" name="ticker" id="ticker" placeholder="Ticker Symbol" value="${ticker!}" />
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-default">Search</button>
                    </div>
                </div>

            </form>
        </div>
        <div class="col-xs-11">
            <div style="width: 50%; margin: 0 auto;">
                <#if stockData??>
                    <b>Company Name:</b> ${stockData.companyName}<br/>
                    <b>Ticker:</b> ${stockData.ticker}<br/>
                    <b>Stock Exchange:</b> ${stockData.stockExchange}<br/>
                    <b>Country:</b> ${stockData.country}<br/>
                    <b>Company Url:</b> ${stockData.companyUrl}<br/>
                    <b>Business Address:</b> ${stockData.businessAddress}<br/>
                    <b>Business Phone Number:</b> ${stockData.businessPhoneNumber}<br/>
                    <b>Ceo's Name:</b> ${stockData.ceo}<br/>
                    <b>Number of Employees:</b> ${stockData.employees}<br/>
                    <b>Open Price:</b> ${stockData.openPrice}<br/>
                    <b>High Price:</b> ${stockData.highPrice}<br/>
                    <b>Low Price:</b> ${stockData.lowPrice}<br/>
                    <b>Close Price:</b> ${stockData.closePprice}<br/>
                    <b>Last Price:</b> ${stockData.lastPrice}<br/>
                    <b>Change:</b> ${stockData.change}<br/>
                    <b>Volume:</b> ${stockData.volume}<br/>
                    <b>Average Daily Volume:</b> ${stockData.averageDailyVolume}<br/>
                    <b>52 Week High:</b> ${stockData.week52High}<br/>
                    <b>52 Week Low:</b> ${stockData.week52Low}<br/>
                    <b>Dividend Ex Date:</b> ${stockData.dividendExDate}<br/>
                    <b>Dividend Pay Date:</b> ${stockData.dividendPayDate}<br/>
                    <b>Dividend:</b> ${stockData.dividend}<br/>
                    <b>Trailing Dividend Yield:</b> ${stockData.trailingDividendYield}<br/>
                </#if>
            </div>
        </div>
    </div>
</@layout.masterTemplate>