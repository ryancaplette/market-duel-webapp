<#import "masterTemplate.ftl" as layout />
<@layout.masterTemplate title="Portfolios">

<div class="row">
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


    <!-- todo: implement for each portfolio wil display a row for each portfolio that the user (or all?)-->
    <div class="col-md-8">
        <h3>${pageTitle}</h3>
        <h6>${matchStart}</h6>
        <h6>${matchEnd}</h6>
        <p>Portfolio comparison of players in this match: </p>
    </div>
    <div class="col-md-8">
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-12">
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th>
                                Portfolio ID
                            </th>
                            <th>
                                Player ID
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
                                        ${portfolio.playerId}
                                    </td>
                                    <td>
	                                    <#assign currentValue = portfolio.getCurrentValue()>
	                                    ${currentValue}
	                                </td>
	                                <td>
	                                    <#assign initialValue = portfolio.getInitialValue()>
	                                    ${currentValue - initialValue}
	                                </td>
	                                <td>
	                                    <#if initialValue==0>
	                                		INF
	                                	<#else>
	                                    	${currentValue*100/initialValue - 100}%
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