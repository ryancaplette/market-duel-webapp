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
                <li><a href="/account">Account Dashboard</a></li>
                <li><a href="/your-games">Your Games</a></li>
                <li><a href="/available-games">Available Games</a></li>
                <li><a href="/history">History</a></li>
                <li class="active"><a href="/portfolios">Portfolios</a></li>
                <li><a href="/players">Players</a></li>
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
                            Match Name
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
                                    ${matchNames["" + portfolio.matchId]}
                                </td>
                                <td>
                                    <#assign currentValue = portfolio.getCurrentValue()>
                                    ${currentValue?string.currency}
                                </td>
                                <td>
                                    <#assign initialValue = matchBalances["" + portfolio.matchId]>
                                    <#assign gain = currentValue - initialValue>
                                    <#if (gain > 0)>
                                		<font color=green>${gain?string.currency}</font>
                                	<#elseif (gain < 0)>
                                		<font color=red>${gain?string.currency}</font>
                                	<#else>
                                		${gain?string.currency}
                                	</#if>
                                </td>
                                <td>
                                    <#if initialValue==0>
                                		INF
                                	<#else>
                                    	<#assign gainPercent = ((currentValue/initialValue) - 1)>
                                    	<#if (gainPercent > 0)>
                                    		<font color=green>${gainPercent?string["0.##%"]}</font>
                                    	<#elseif (gainPercent < 0)>
                                    		<font color=red>${gainPercent?string["0.##%"]}</font>
                                    	<#else>
                                    		${gainPercent?string["0.##%"]}
	                                	</#if>
                                	</#if>
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