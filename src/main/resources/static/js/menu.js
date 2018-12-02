function loadMenu() {
	$.ajax({
		url: "/loadMenu",
		success: function (res) {
			var data = res.data;
			var menuList = data.menuList;
			var navList = data.navList;
			showNav(navList);
			showMenu(menuList);
		}
	});
}

function showNav(navList) {
	var arr = [];
	$.each(navList, function (i) {
		arr.push("<li role=\"presentation\"");
		if (i == 0) {
			arr.push("class=\"active\"");
		}
		arr.push(">");
		arr.push("<a data-toggle=\"tab\" href=\"#navTabsItem-" + i + "\" role=\"tab\" aria-expanded=\"false\">");
		arr.push("<i class=\"icon wb-settings\"></i>");
		arr.push("<span>" + this.text + "</span></a></li>");
	});
	$("#steven-navMenu>ul").html(arr.join(""));
}

function showMenu(menuList) {
	var arr = [];
	$.each(menuList, function (i) {
		arr.push("<div class=\"tab-pane animation-fade height-full ");
		if (i == 0) {
			arr.push("active");
		}
		arr.push("\" id=\"navTabsItem-" + i + "\" role=\"tabpanel\">");
		arr.push("<div><ul class=\"site-menu\">");
		arr.push("<li class=\"site-menu-category\">" + this.text + "</li>");
		if (this.nodes && this.nodes.length > 0) {
			$.each(this.nodes, function () {
				arr.push("<li class=\"site-menu-item has-sub\">");
				arr.push("<a href=\"javascript:\">");
				arr.push("<i class=\"site-menu-icon fa-laptop\" aria-hidden=\"true\"></i>");
				arr.push("<span class=\"site-menu-title\">" + this.text + "</span>");
				arr.push("<span class=\"site-menu-arrow\"></span></a>");
				if (this.nodes && this.nodes.length > 0) {
					arr.push("<ul class=\"site-menu-sub\">");
					$.each(this.nodes, function () {
						arr.push("<li class=\"site-menu-item \">");
                        arr.push("<a href=\"" + this.url + "\">");
						arr.push("<span class=\"site-menu-title\">" + this.text + "</span>");
						arr.push("</a></li>");
					});
					arr.push("</ul>");
				}
				arr.push("</li>");
			});
		}
		arr.push("</ul></div></div>");
	});
	$("#steven-navTabs").html(arr.join(""));
	$("#steven-navTabs li.has-sub").on("click", function () {
		$(this).addClass('open').siblings().removeClass('open');
	});
	$("#steven-navTabs ul.site-menu-sub a").on("click", function () {
		openTab(this.href);
		return false;
	});
}
