var currPage = 1, totalPage = 0, totalRow = 0;
var E = window.wangEditor;
var action, editor;

$(function () {

	freshList();
	editor = new E('#ggnr');
	editor.customConfig.menus = ['head', 'bold', 'italic', 'underline', 'foreColor', 'link', 'justify', 'image'];// 设置显示的菜单
	editor.create();

    $("select[name='productType']").change(function() {
        var productType = $(this).children('option:selected').val();
        changeForm(productType);
    });

    var productType = $("select[name='productType']").children('option:selected').val();
    changeForm(productType);
});

function setFrom(jsonVal) {
    $.each(jsonVal, function (name, ival) {
        var $oinput = $("#noticeForm").find("input[name=" + name + "]").val(ival);
        $("#noticeForm").find("select[name=" + name + "]").val(ival);

    })
}
function changeForm(productType) {
    if (productType == 3) {
        $("#length").hide();
        $("#with").hide();
        $("#high").hide();
        $("#power").show();
    } else {
        $("#length").show();
        $("#with").show();
        $("#high").show();
        $("#power").hide();
    }

}
function add() {
	$("#noticeForm")[0].reset();
    var productType = $("select[name='productType']").children('option:selected').val();
    changeForm(productType);
	$("#noticeWin").modal("show");
}

function edit(obj) {
	$("#noticeForm")[0].reset();
    var id = $(obj).val();
    $.ajax({
        type: "post",
        url: "/product/getByid",
        data : {"id":id},
        success : function (res) {
            if (res.code == 0) {
            	changeForm(res.data.productType);
                $("#productType").prop("readonly", true);
                setFrom(res.data);
            }
        }
    })
	$("#noticeWin").modal("show");

}
function del(obj) {
    layer.confirm("确认删除该记录？", {btn: ['确定', '取消']}, function () {
    	debugger
		var id = $(obj).val();

    	$.ajax({
            type: "post",
            url: "/product/del",
			data : {"id":id},
			success : function (res) {
				if (res.code == 0) {
                    layer.msg("操作成功", {icon: 1});
                    freshList();
				}
            }
		})
    });
}

function freshList() {
	$.ajax({
		type: "post",
		contentType: "application/json",
		url: "/product/buyList",
		data: JSON.stringify($.extend($(".relForm").serializeJSON(), {pageNo: currPage})),
		success: function (res) {
			if (0 != res.code) {
				return;
			}
			var data = res.data;
			var arr = [];
			$.each(data, function (i) {
				var productName = buildproductName(this.productType)
				var statusName = buildStatus(this.status);
				arr.push("<tr>");

				arr.push("<td>" + this.productCode + "</td>");
                arr.push("<td>" + productName + "</td>");
				arr.push("<td>" + this.buyTime + "</td>");
				arr.push("<td>" + this.factoryName + "</td>");
				arr.push("<td>" + this.price + "</td>");
				arr.push("<td>" + buildCKG(this.length,this.with,this.high) + "</td>");
				var powerName = "";
				if (this.power != null) {
                    powerName = this.power;
				}
				arr.push("<td>" + powerName + "</td>");
				arr.push("<td>" + statusName + "</td>");
				arr.push("<td class=\"text-nowrap\">");
				arr.push("<button type=\"button\"  class=\"btn btn-sm btn-icon btn-flat btn-default\" onclick=\"edit(this)\" value=\"" + this.id + "\">");
                arr.push("<a>修改</a></button>");
                arr.push("<button type=\"button\" class=\"btn btn-sm btn-icon btn-flat btn-default\" onclick=\"del(this)\" value=\"" + this.id + "\">");
                arr.push("<a>删除</a></button>");
				arr.push("</td></tr>");
			});
			$(".panel-body table.table>tbody").html(arr.join(""));
			$('[data-toggle="tooltip"]').tooltip();
		}
	});
}

function buildCKG(length,witch,high) {
	if (length== null || witch == null || high == null) {
		return "";
	}
    return length +"/"+ witch +"/" + high;

}
function buildproductName (productType) {
    var productName = "-";
    if (productType == 1) {
        productName = "集装箱";
    } else if (productType == 2) {
        productName = "活动床";
    } else if (productType == 3) {
        productName = "空调";
    }
    return productName;
}

function buildStatus(status) {
	var statsName;
	if (status == 1) {
		statsName = "出租中";
	} else if (status == 0) {
        statsName = "未出租";
	} else {
        statsName = "";
	}
	return statsName;
}
function save() {
	debugger
	var form = $("#noticeForm");
    var productType = $("#productType").children('option:selected').val();
    var errorMsg = validFrom(productType);
    if(errorMsg.length > 0) {
        layer.alert(errorMsg, {icon: 2});
        return;
	}
	$.ajax({
		type: "post",
		contentType: "application/json",
		url: "/product/save",
		data: JSON.stringify(form.serializeJSON()),
		success: function (res) {
			if (0 == res.code) {
				$("#noticeWin").modal("hide");
				layer.msg("操作成功", {icon: 1});
				freshList();
			} else {
				layer.alert(res.desc, {icon: 2});
			}
		}
	});
}

function validFrom(productType) {
	debugger
    if($("#noticeForm input[name='productCode']").val().length == 0){
        return "产品编码为必填项";
    }
    if($("#noticeForm input[name='buyTime']").val().length == 0){
        return "购买日期为必填项";
    }
    if($("#noticeForm input[name='factoryName']").val().length == 0){
        return "制作商为必填项";
    }if($("#noticeForm input[name='price']").val().length == 0){
        return "购买价格为必填项";
    }
	if(productType != 3) {
        if($("#noticeForm input[name='length']").val().length == 0){
            return "物品长度为必填项";
        }
        if($("#noticeForm input[name='with']").val().length == 0){
            return "物品宽度为必填项";
        }
        if($("#noticeForm input[name='high']").val().length == 0){
            return "物品高度为必填项";
        }
	} else {
        if($("#noticeForm input[name='power']").val().length == 0){
            return "功率为必填项";
        }
	}
	return "";
}
