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
                <#if stockData??>
                    <h4>Current Stock Information (as of ${.now?long?number_to_datetime})</h4><br/>
                    <#if stockData.companyName??>
                    <b>Company Name:</b> ${stockData.companyName}<br/>
                    </#if>
                    <#if stockData.ticker??>
                        <b>Ticker:</b> ${stockData.ticker}<br/>
                    </#if>
                    <#if stockData.stockExchange??>
                        <b>Stock Exchange:</b> ${stockData.stockExchange}<br/>
                    </#if>
                    <#if stockData.country??>
                        <b>Country:</b> ${stockData.country}<br/>
                    </#if>
                    <#if stockData.companyUrl??>
                        <b>Company Url:</b> ${stockData.companyUrl}<br/>
                    </#if>
                    <#if stockData.businessAddress??>
                        <b>Business Address:</b> ${stockData.businessAddress}<br/>
                    </#if>
                    <#if stockData.businessPhoneNumber??>
                        <b>Business Phone Number:</b> ${stockData.businessPhoneNumber}<br/>
                    </#if>
                    <#if stockData.ceo??>
                        <b>CEO's Name:</b> ${stockData.ceo}<br/>
                    </#if>
                    <#if (stockData.employees > 0)>
                        <b>Number of Employees:</b> ${stockData.employees}<br/>
                    </#if>
                    <#if open??>
                        <b>Open Price:</b> ${open}<br/>
                    </#if>
                    <#if high??>
                        <b>High Price:</b> ${high}<br/>
                    </#if>
                    <#if low??>
                        <b>Low Price:</b> ${low}<br/>
                    </#if>
                    <#if close??>
                        <b>Close Price:</b> ${close}<br/>
                    </#if>
                    <#if volume??>
                        <b>Volume:</b> ${volume}<br/>
                    </#if>
                    <#if stockData.change??>
                        <b>Change:</b> ${stockData.change}<br/>
                    </#if>
                    <#if stockData.averageDailyVolume??>
                        <b>Average Daily Volume:</b> ${stockData.averageDailyVolume}<br/>
                    </#if>
                    <#if stockData.week52High??>
                        <b>52 Week High:</b> ${stockData.week52High}<br/>
                    </#if>
                    <#if stockData.week52Low??>
                        <b>52 Week Low:</b> ${stockData.week52Low}<br/>
                    </#if>
                    <#if stockData.dividendExDate??>
                        <b>Dividend Ex Date:</b> ${stockData.dividendExDate}<br/>
                    </#if>
                    <#if stockData.dividendPayDate??>
                        <b>Dividend Pay Date:</b> ${stockData.dividendPayDate}<br/>
                    </#if>
                    <#if stockData.dividend??>
                        <b>Dividend:</b> ${stockData.dividend}<br/>
                    </#if>
                    <#if stockData.trailingDividendYield??>
                        <b>Trailing Dividend Yield:</b> ${stockData.trailingDividendYield}<br/>
                    </#if>

                    <br/><br/>
                    <#if player??>
                        <div class="form-group">
                            <div class="">
                                <button type="submit" class="btn btn-default">Buy Stock</button>
                                <button type="submit" class="btn btn-default">Sell Stock</button>
                            </div>
                        </div>
                    </#if>
                

                    <div style="height: 450px">
                    <script type="text/javascript" src="https://s3.tradingview.com/tv.js"></script>
                    <script type="text/javascript">

                    var ticker = "${stockData.ticker}";

                    new TradingView.widget({
                      
                      "autosize": true,
                      "symbol": ticker,
                      "interval": "D",
                      "timezone": "Etc/UTC",
                      "theme": "White",
                      "style": "1",
                      "locale": "en",
                      "toolbar_bg": "#f1f3f6",
                      "enable_publishing": false,
                      "allow_symbol_change": true,
                      "hideideas": true
                    });
                    </script>
                    </div>
            </#if>
     
        </div>
    </div>
</@layout.masterTemplate>