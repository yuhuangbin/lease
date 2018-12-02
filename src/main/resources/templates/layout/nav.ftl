<nav class="site-navbar navbar navbar-default navbar-fixed-top navbar-inverse " role="navigation">
	<div class="navbar-header">
		<div class="navbar-brand navbar-brand-center site-gridmenu-toggle" data-toggle="gridmenu">
			<span class="navbar-brand-logo visible-lg visible-xs navbar-logo">xx系统</span>
		</div>
	</div>
	<div class="navbar-container container-fluid">
		<div class="collapse navbar-collapse navbar-collapse-toolbar" id="steven-navbarCollapse">
			<ul class="nav navbar-toolbar navbar-left">
				<li class="navbar-menu nav-tabs-horizontal nav-tabs-animate" id="steven-navMenu">
					<ul class="nav navbar-toolbar nav-tabs" role="tablist">
						<li role="presentation"></li>
					</ul>
				</li>
			</ul>
			<ul class="nav navbar-toolbar navbar-right navbar-toolbar-right">
				<li data-toggle="tooltip" data-placement="bottom" data-original-title="退出">
					<a class="icon fa-sign-out" href="javascript:logout()">
						<span class="sr-only">退出</span>
					</a>
				</li>
			</ul>
		</div>
	</div>
</nav>
<script type="text/javascript">
	function logout() {
		$.ajax({
			type: "post",
			url: "/loginOut",
			dataType: "json",
			success: function (res) {
				if (res.code == 0) {
					location.href = "/";
				}
			}
		});
	}
</script>