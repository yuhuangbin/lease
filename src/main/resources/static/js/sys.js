var dict, user, table = {};

$(function () {
	// getDictData();
	// getUserData();
	// ajaxFilter();
	// loadMenu();
	// layerConfig();
});

function freshListQuer() {
    currPage = 1;
    freshList();
}

function ajaxFilter() {
	$.ajaxSetup({
		statusCode: {
			302: function () {
				location.href = "/login";
			}
		},
		error: function () {
			layer.alert("应用程序未知异常", {icon: 2});
		}
	});
}

function commonInit() {
	// 初始化下拉框数据
	initSelectData();
	// 初始化树组件
	initTree();
	// 初始化表单验证
	initValidForm();
	// 全选反选
	$("body").on("click", ".selectable-all", function () {
		if ($(this).is(":checked")) {
			$(".selectable-item").prop("checked", true);
		} else {
			$(".selectable-item").prop("checked", false);
		}
	});
	$(".readonly").find("button").attr('disabled', "true");
	$('[data-plugin="datepicker"]').datepicker({
		autoclose: true,
		format: "yyyymmdd",
		language: "zh-CN"
	});
	// $('[data-plugin="selectpicker"]').selectpicker();
}

function formatDate(src) {
	var arr = [];
	arr.push(src.substring(0, 4));
	arr.push("-");
	arr.push(src.substring(4, 6));
	arr.push("-");
	arr.push(src.substring(6, 8));
	return arr.join("");
}

function formatTime(src) {
	while (src.length < 6) {
		src = "0" + src;
	}
	var arr = [];
	arr.push(src.substring(0, 2));
	arr.push(":");
	arr.push(src.substring(2, 4));
	arr.push(":");
	arr.push(src.substring(4, 6));
	return arr.join("");
}

function getDictData() {
	$.ajax({
		url: ctx + "/getDictData",
		success: function (res) {
			if ("0000" == res.code) {
				dict = res.data;
			}
		}
	});
}

function getDictValue(dictName, key) {
	if (!dictName || (!key && key != false) || isNaN(dictName)) {
		return "";
	}
	return dict[dictName][key];
}

function getUserData() {
	$.ajax({
		url: ctx + "/getUserData",
		success: function (res) {
			if ("0000" == res.code) {
				user = res.data;
				$("small")[0].innerHTML = user.userName;
			}
		}
	});
}

function layerConfig() {
	layer.config({
		success: function (layero, index) {
			$("#layui-layer-shade" + index).appendTo(layero.parent());
		}
	});
}

function initPageBar() {
	$("div.pull-left").html("第" + currPage + "页，共" + totalPage + "页，" + totalRow + "条记录");
	$("div.pull-right li").addClass("disabled");
	if (currPage > 1) {
		$("div.pull-right li.first").removeClass("disabled");
		$("div.pull-right li.previous").removeClass("disabled");
	}
	if (currPage < totalPage) {
		$("div.pull-right li.next").removeClass("disabled");
		$("div.pull-right li.last").removeClass("disabled");
	}
	$("div.pull-right li").off("click");
	$("div.pull-right li").on("click", function () {
		var _this = $(this);
		if (_this.hasClass("disabled")) {
			return;
		}
		if (_this.hasClass("first")) {
			currPage = 1;
		} else if (_this.hasClass("previous")) {
			currPage = currPage - 1;
		} else if (_this.hasClass("next")) {
			currPage = currPage + 1;
		} else {
			currPage = totalPage;
		}
		freshList();
	});
}

function initSelectData(id) {
	var $selects;
	if (id) {
		$selects = $("select#" + id);
	} else {
		$selects = $("select");
	}
	$selects.each(function () {
		var $this = $(this);
		if (!$this.html()) {
			var dictName = $this.attr("dict-name");
			if (dictName) {
				$this.empty();
				if ($this.attr("has-head") != undefined) {
					this.options.add(new Option($this.attr("has-head"), ""));
				}
				for (var k in dict[dictName]) {
					this.options.add(new Option(dict[dictName][k], k));
				}
			}
		}
	});
}

function initTree() {
	if ($("div.listTree").length > 0) {
		$("div.listTree").treeview({
			data: getTree(),
			nodeIcon: "fa-file"
		});
	}
	if ($("div.checkTree").length > 0) {
		$("div.checkTree").treeview({
			data: getTree(),
			checkedIcon: "fa-check-square-o",
			uncheckedIcon: "fa-square-o",
			collapseIcon: "fa-minus",
			expandIcon: "fa-plus",
			levels: 1,
			showCheckbox: true,
			showIcon: false,
			hierarchicalCheck: true
		});
	}
}

function initValidForm() {
	$("form.validForm").validate({
		errorElement: 'div',
		errorPlacement: function (error, element) {
			error.addClass('tooltip tooltip-inner arrow-left');
			if (element.is(":radio")) {
				error.appendTo(element.parent().next().next());
			} else if (element.is(":checkbox")) {
				error.appendTo(element.next());
			} else {
				element.after(error);
			}
			if (!element.nextAll("i")[0]) {
				$("<i class='fa fa-close form-control-feedback'></i>").insertAfter(element);
			}
			if (element.is(":radio")) {//类型为radio的显示如下
				error.css({display: 'block', opacity: '0.6', float: 'left', margin: '-10px 0 0 80px'});
			} else {//其他均为以下显示
				error.css({display: 'block', opacity: '0.6', float: 'left', margin: '-10px 0 0 80px'});
			}
		},
		success: function (label, element) {
			if (!$(element).nextAll("i")[0]) {
				$("<i class='fa fa-check form-control-feedback'></i>").insertAfter($(element));
			}
			$(element).nextAll("div.error").remove();
		},
		highlight: function (element) {
			$(element).parents(".col-lg-4,.col-lg-9,.col-lg-10").addClass("has-error").removeClass("has-success");
			$(element).next("i").addClass("fa-close").removeClass("fa-check");
		},
		unhighlight: function (element) {
			$(element).parents(".col-lg-4,.col-lg-9,.col-lg-10").addClass("has-success").removeClass("has-error");
			$(element).next("i").addClass("fa-check").removeClass("fa-close");
		}
	});
}

function openTab(url, data) {
	$("#steven-pageContent").load(url, data, commonInit);
}

function setFormData(formId, json) {
	var name, value, tagName, type, arr;
	$("#" + formId + " input,#" + formId + " select,#" + formId + " textarea").each(function () {
		name = this.name;
		tagName = this.tagName;
		type = $(this).attr("type");
		value = json[name];
		if (value || value == 0 || value == false) {
			if (tagName == "INPUT") {
				if (type == 'radio') {
					$(this).prop('checked', $(this).val() == value);
				} else if (type == 'checkbox') {
					arr = value.split(',');
					for (var i = 0; i < arr.length; i++) {
						if ($(this).val() == arr[i]) {
							$(this).prop('checked', true);
							break;
						}
					}
				} else {
					$(this).val(value);
				}
			} else if (tagName == "SELECT") {
				_this = $(this);
				var dictName = _this.attr("dict-name");
				_this.val(value);
				_this.prev().prev().prop("title", getDictValue(dictName, value));
				_this.prev().prev().find("span:eq(0)").html(getDictValue(dictName, value));
			} else {
				$(this).val(value);
			}
		}
	});
}

function setLabelData(pId, json) {
	var _this, name, value, dictName;
	$("#" + pId + " label.vv").each(function () {
		_this = $(this);
		name = _this.attr("name");
		value = json[name];
		if (value || value == 0 || value == false) {
			dictName = _this.attr("dict-name");
			if (dictName) {
				_this.text(getDictValue(dictName, value));
			} else {
				_this.text(value);
			}
		}
	});
}
