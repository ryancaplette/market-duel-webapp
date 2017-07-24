<#import "masterTemplate.ftl" as layout />

<@layout.masterTemplate title="Account Dashboard">

<script src="//widgetcdn.chartiq.com/js/iframeResizer.min.js"></script>


<div class="row">
    <div class="col-xs-11">
        <h3>${pageTitle}</h3>
        <div class="panel panel-info">
            <div class="panel-heading">
                <h3 class="panel-title">Welcome back, <strong>${player.username}</strong> (${player.email})</h3>
            </div>
        </div>
    </div>
</div>

	<div class="row">
		<div class="col-md-3">
			<ul class="nav nav-stacked nav-pills">
				<li class="active"><a href="/account">Account Dashboard</a></li>
				<li><a href="/games">Games</a></li>
				<li><a href="/history">History</a></li>
				<li><a href="/portfolios">Portfolios</a></li>
				<li><a href="/players">Players</a></li>
				<li><a href="/alerts">Alerts</a></li>
			</ul>
		</div>
		<div class="col-md-8">
			<iframe src="https://globalwidgets.xignite.com/ChartIQ/Widgets2Test/widget.html?widget=marketsnews" frameborder="0" scrolling="no" style="width:100%"></iframe>
		</div>
	</div>
	<div class="row">
		<div class="col-md-3">
		</div>
		<div class="col-md-4">
			<iframe src="https://globalwidgets.xignite.com/ChartIQ/Widgets2Test/widget.html?widget=topmovers" frameborder="0" scrolling="no" style="width:100%"></iframe>
		</div>
		<div class="col-md-4">
			<iframe src="https://globalwidgets.xignite.com/ChartIQ/Widgets2Test/widget.html?widget=sectorperformance" frameborder="0" scrolling="no" style="width:100%"></iframe>
		</div>
	</div>
	<script>iFrameResize({heightCalculationMethod: 'taggedElement'});</script>
</br></br>

</@layout.masterTemplate>