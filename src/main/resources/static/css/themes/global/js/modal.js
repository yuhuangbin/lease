(function (window,undefined){

    var popModal = function (opt) {
        this.opt = opt || {};
    };
    //warnModal显示
    popModal.prototype.show = function () {
        var that = this;

        var modalHtml = [];
        modalHtml.push("<div class=\"modal fade modal-fade-in-scale-up componentmodal\" id=" + (this.opt['id'] || '')+ ">");
        modalHtml.push("<div class=\"sweet-alert showSweetAlert\" style=\"z-index:999;display: block; margin-top: -165px;\"><div class='confirmcontent'>");
        if(this.opt['template'] == "confirm" || this.opt['template'] == "question") {
            modalHtml.push("<div class=\"icon question\"><span class='font-size-80'>?</span></div>");
        }else if(this.opt['template'] == "warning") {
            modalHtml.push("<div class=\"icon warning\"><span class=\"body\"></span><span class=\"dot\"></span></div>");
        }else if(this.opt['template'] == "danger"){
            modalHtml.push("<div class=\"icon error animateErrorIcon\"><span class=\"x-mark animateXMark\"><span class=\"line left\"></span><span class=\"line right\"></span></span></div>")
        }else if(this.opt['template'] == "success"){
            modalHtml.push("<div class=\"icon success animate\"><span class=\"line tip animateSuccessTip\"></span><span class=\"line long animateSuccessLong\"></span><div class=\"placeholder\"></div><div class=\"fix\"></div></div>")
        }
        modalHtml.push("<h2>" + (this.opt['content'] || '请输入内容') + "</h2>");
        modalHtml.push("<p class=\"lead text-muted\">" + (this.opt['remark'] || '请输入备注') + "</p>");
        if(this.opt['template'] == "confirm") {
            modalHtml.push("<p><button class=\"cancel btn btn-lg btn-default margin-right-5\" style=\"display: inline-block;\" data-dismiss='modal'>取消</button><button class=\"continue btn btn-question btn-lg\" style=\"display: inline-block;\">确定</button></p>");
            modalHtml.push("</div>");
            modalHtml.push("<div class='successcontent' style='display: none'><div class=\"icon success animate\"><span class=\"line tip animateSuccessTip\"></span><span class=\"line long animateSuccessLong\"></span><div class=\"placeholder\"></div><div class=\"fix\"></div></div>");
            modalHtml.push("<h2>" + (this.opt['successcontent'] || '请输入内容') + "</h2>");
            modalHtml.push("<p class=\"lead text-muted\">" + (this.opt['successremark'] || '请输入备注') + "</p>");
            modalHtml.push("<p><button class=\"confirm btn btn-success btn-lg\" style=\"display: inline-block;\" data-dismiss='modal'>确定</button></p>");
            modalHtml.push("</div>");
        }else{
            modalHtml.push("<p><button class=\"confirm btn btn-" + this.opt['template'] + " btn-lg\" style=\"display: inline-block;\" data-dismiss='modal'>确定</button></p>");
            modalHtml.push("</div>");
        }
        modalHtml.push("</div></div>");

        //输出到body
        $("body").append(modalHtml.join(""));

        var oClose = document.querySelectorAll("button[data-dismiss='modal']");
        oClose.forEach(function (item) {
            item.addEventListener("click", function () {
                that.hide(this);
            });
        });


        var oconti = document.querySelector(".continue");
        if(oconti){
            oconti.addEventListener("click", function () {
                var callback = that.opt["callback"];
                document.querySelector(".confirmcontent").style.display = "none";
                document.querySelector(".successcontent").style.display = "block";
                if(callback){
                    callback ();
                }
            });
        }


    //绑定显示事件
    var oModal = document.querySelector(".componentmodal");
    oModal.style.display = 'block';
    oModal.classList.add('in');
    //遮罩层
    var backdrop = document.createElement("div"); //新建一个div
    backdrop.setAttribute('class', 'modal-backdrop in');    //为div添加类名
    document.body.appendChild(backdrop);

};

    popModal.prototype.hide = function (obj) {
        var objParents = findNode(obj, "modal"); //当前.close的父辈元素 .modal
        // console.log(objParents);
        // objParents.style.display = 'none';//隐藏模态
        document.body.removeChild(objParents);
        document.body.removeChild(document.querySelector(".modal-backdrop"));
        // document.querySelector(".modal-backdrop").style.display = "none";
    };


    //公共的方法:
    //找到关闭按钮的指定父节点
    function findNode(obj, classname) {
        var aClass;
        var regExp = new RegExp(classname);
        while (obj = obj.parentNode) {
            aClass = Array.prototype.slice.call(obj.classList); //类数组--->数组
            if (aClass.length && regExp.test(aClass[0]) && aClass[0].length == 5) {
                break;
            }
        }
        return obj;
    }

    function a(){
        alert(1);
    }

    window.popModal = popModal;
})(window,undefined);


