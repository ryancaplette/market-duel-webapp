<#import "masterTemplate.ftl" as layout />

<@layout.masterTemplate title="Account Dashboard">

<div class="row">
    <div class="col-xs-11">
        <h3>${pageTitle}</h3>
        <div class="panel panel-info">
            <div class="panel-heading">
                <h3 class="panel-title">Welcome back, <strong>${player.username}</strong> (${player.email}).</h3>
            </div>
        </div>
    </div>
</div>

	<div class="row">
		<div class="col-md-3">
			<ul class="nav nav-stacked nav-pills">
				<li class="active">
					<a href="/account">Account Dashboard</a>
				</li>
				<li>
					<a href="/games">Games</a>
				</li>
				<li>
					<a href="/players">Players</a>
				</li>
				<li>
					<a href="/alerts">Alerts</a>
				</li>

			</ul>
		</div>
		<div class="col-md-4">
			<h4>Market News</h4>
			Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
		</div>
		<div class="col-md-4">
			<h4>Movers and Shakers</h4>
			Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
		</div>
	</div>
	<div class="row">
		<div class="col-md-3">
		</div>
		<div class="col-md-4">
			<h4>Top Players</h4>
			Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
		</div>
		<div class="col-md-4">
			<h4>My Games</h4>
			Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
		</div>
	</div>
</br></br>
















</@layout.masterTemplate>