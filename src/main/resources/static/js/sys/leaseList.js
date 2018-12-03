var currPage = 1, totalPage = 0, totalRow = 0;
var E = window.wangEditor;
var action, editor;

$(function () {

	freshList();
	editor = new E('#ggnr');
	editor.customConfig.menus = ['head', 'bold', 'italic', 'underline', 'foreColor', 'link', 'justify', 'image'];// 设置显示的菜单
	editor.create();
});

function setFrom(jsonVal) {
    $.each(jsonVal, function (name, ival) {
        var $oinput = $("#leaseForm").find("input[name=" + name + "]").val(ival);
    })
}

function add() {
	$("#leaseForm")[0].reset();
    $("#leaseForm").find("input[name='boxCode']").removeAttr("readonly");
    $("#leaseForm").find("input[name='airCode']").removeAttr("readonly");
    $("#leaseForm").find("input[name='bedCode']").removeAttr("readonly");
	$("#leaseWin").modal("show");
}

function edit(obj) {
	$("#leaseForm")[0].reset();
    var id = $(obj).val();
    $.ajax({
        type: "post",
        url: "/api/lease/getByid",
        data : {"id":id},
        success : function (res) {
            if (res.code == 0) {
                setFrom(res.data);
                $("#leaseForm").find("input[name='boxCode']").attr("readonly",true);
                $("#leaseForm").find("input[name='airCode']").attr("readonly",true);
                $("#leaseForm").find("input[name='bedCode']").attr("readonly",true);
            } else if (res.code == -1000) {
                layer.msg(res.msg, {icon: 1});
                setTimeout(function(){ location.href="/"; }, 2000);
            }
        }
    })
	$("#leaseWin").modal("show");

}
function settlement(obj) {
    layer.confirm("确认结算该条租赁信息？", {btn: ['确定', '取消']}, function () {
    	debugger
		var id = $(obj).val();

    	$.ajax({
            type: "post",
            url: "/api/lease/settlement",
			data : {"id":id},
			success : function (res) {
				if (res.code == 0) {
                    layer.msg("操作成功，结算信息已发送至您的邮箱", {icon: 1});
                    freshList();
				} else if (res.code == -1000) {
                    layer.msg(res.msg, {icon: 1});
                    setTimeout(function(){ location.href="/"; }, 2000);
                }
            }
		})
    });
}
function del(obj) {
    layer.confirm("确认删除该条租赁信息？", {btn: ['确定', '取消']}, function () {
        debugger
        var id = $(obj).val();

        $.ajax({
            type: "post",
            url: "/api/lease/del",
            data : {"id":id},
            success : function (res) {
                if (res.code == 0) {
                    layer.msg("操作成功", {icon: 1});
                    freshList();
                } else if (res.code == -1000) {
                    layer.msg(res.msg, {icon: 1});
                    setTimeout(function(){ location.href="/"; }, 2000);
                }
            }
        })
    });
}

function freshList() {
	$.ajax({
		type: "post",
		contentType: "application/json",
		url: "/api/lease/list",
		data: JSON.stringify($.extend($(".relForm").serializeJSON(), {pageNo: currPage})),
		success: function (res) {
			if (0 == res.code) {
                var data = res.data;
                var arr = [];
                $.each(data, function (i) {
                    arr.push("<tr>");
                    arr.push("<td>" + this.lesseeName + "</td>");
                    arr.push("<td>" + this.lesseeTel + "</td>");
                    arr.push("<td>" + this.lesseeCompany + "</td>");
                    arr.push("<td>" + this.boxCode + "</td>");
                    arr.push("<td>" + this.airCode + "</td>");
                    arr.push("<td>" + this.bedCode + "</td>");
                    arr.push("<td>" + this.cashPledge + "</td>");
                    arr.push("<td>" + this.startDate + "</td>");
                    arr.push("<td>" + this.endDate + "</td>");
                    arr.push("<td>" + this.currentCost + "</td>");
                    arr.push("<td>" + this.statusName + "</td>");
                    arr.push("<td class=\"text-nowrap\">");
                    arr.push("<button type=\"button\"  class=\"btn btn-sm btn-icon btn-flat btn-default\" onclick=\"edit(this)\" value=\"" + this.id + "\">");
                    arr.push("<a>修改</a></button>");
                    if (this.status == 0 || this.status == 1) {
                        arr.push("<button type=\"button\" class=\"btn btn-sm btn-icon btn-flat btn-default\" onclick=\"settlement(this)\" value=\"" + this.id + "\">");
                        arr.push("<a>结算</a></button>");
                    }
                    if (this.status == 2) {
                        arr.push("<button type=\"button\" class=\"btn btn-sm btn-icon btn-flat btn-default\" onclick=\"del(this)\" value=\"" + this.id + "\">");
                        arr.push("<a>删除</a></button>");
                    }
                    arr.push("</td></tr>");
                });
                $(".panel-body table.table>tbody").html(arr.join(""));
                $('[data-toggle="tooltip"]').tooltip();
			} else if (res.code == -1000) {
                layer.msg(res.msg, {icon: 1});
                setTimeout(function(){ location.href="/"; }, 2000);
            }
		}
	});
}

function save() {
	debugger
	var form = $("#leaseForm");

    var errorMsg = validFrom();
    if(errorMsg.length > 0) {
        layer.alert(errorMsg, {icon: 2});
        return;
	}
    var isExist = false;
    var id = form.find("input[name='id']").val();
	if(id == null || id.length == 0) { // 修改不校验
        $.ajax({
            type: "post",
            contentType: "application/json",
            url: "/api/product/verify",
            async : false,
            data: JSON.stringify(form.serializeJSON()),
            success : function (res) {
                if (0 != res.code) {
                    isExist = true;
                    layer.alert(res.msg, {icon: 2});
                } else if (res.code == -1000) {
                    layer.msg(res.msg, {icon: 1});
                    setTimeout(function(){ location.href="/"; }, 2000);
                }
            }
        })
	}
	if (isExist) {
       return;
	}

	$.ajax({
		type: "post",
		contentType: "application/json",
		url: "/api/lease/save",
		data: JSON.stringify(form.serializeJSON()),
		success: function (res) {
			if (0 == res.code) {
				$("#leaseWin").modal("hide");
				layer.msg("操作成功", {icon: 1});
				freshList();
			} else if (res.code == -1000) {
                layer.msg(res.msg, {icon: 1});
                setTimeout(function(){ location.href="/"; }, 2000);
            } else {
				layer.alert(res.msg, {icon: 2});
			}
		}
	});
}

function validFrom() {
    if($("#leaseForm input[name='lesseeName']").val().length == 0){
        return "承租人姓名为必填项";
    }
    if($("#leaseForm input[name='lesseeTel']").val().length == 0){
        return "联系电话为必填项";
    }
    if($("#leaseForm input[name='lesseeCompany']").val().length == 0){
        return "所属公司为必填项";
    }
    if ($("#leaseForm input[name='boxCode']").val().length == 0
		&& $("#leaseForm input[name='airCode']").val().length == 0
        && $("#leaseForm input[name='bedCode']").val().length == 0) {
    	return "请至少录入一个产品编号"
	}
    if($("#leaseForm input[name='cashPledge']").val().length == 0){
        return "押金为必填项";
    }
    if($("#leaseForm input[name='startDate']").val().length == 0){
        return "租赁时间为必填项";
    }
    if($("#leaseForm input[name='endDate']").val().length == 0){
        return "结束时间为必填项";
    }
	return "";
}
