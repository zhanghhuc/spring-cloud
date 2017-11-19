/**
 * Created by zhang on 2017/10/10.
 */
function ahmch(fphm){
    var classInfo=$("#fphmjy").attr('class');
    $("#fphmjy").removeClass(classInfo);
    if(!ahm(fphm)){
        $("#fphmjy").addClass("tip_common_wrong");
        $("#fphmjy").addClass("font_red");
        $("#fphmjy").html("发票号码有误!");
    }else{
        $("#fphmjy").addClass("tip_common_right");
        $("#fphmjy").html("&nbsp;");
        //var fpdm=$("#fpdm").val().trim();
        //if(fpdm != "" && adm(fpdm) && yzmSj=="" && show_yzm == ""){
        //$('#yzm_img').show();
        //getYzmXx();
        //}
    }
}

(function() {
    window.spawn = window.spawn || function(gen) {
            function continuer(verb, arg) {
                var result;
                try {
                    result = generator[verb](arg);
                } catch (err) {
                    return Promise.reject(err);
                }
                if (result.done) {
                    return result.value;
                } else {
                    return Promise.resolve(result.value).then(onFulfilled, onRejected);
                }
            }
            var generator = gen();
            var onFulfilled = continuer.bind(continuer, 'next');
            var onRejected = continuer.bind(continuer, 'throw');
            return onFulfilled();
        };
    window.showModalDialog = window.showModalDialog || function(url, arg, opt, flag) {
            url = url || ''; //URL of a dialog
            arg = arg || null; //arguments to a dialog
            opt = opt || 'dialogWidth:300px;dialogHeight:200px'; //options: dialogTop;dialogLeft;dialogWidth;dialogHeight or CSS styles
            var caller = showModalDialog.caller.toString();
            var dialog = document.body.appendChild(document.createElement('dialog'));
            dialog.setAttribute('style', opt.replace(/dialog/gi, ''));
            if (url == "sms2.html") {
                dialog.innerHTML = '<iframe id="dialog-body" src="' + url + '" style="border: 0; width: 100%; height: 100%;"></iframe>';
                document.getElementById('dialog-body').contentWindow.dialogArguments = arg;
            } else {
                //<a href="#" id="dialog-close" style="position: absolute; top: 0; right: 4px; font-size: 20px; color: #000; text-decoration: none; outline: none;">&times;</a>
                dialog.innerHTML = '<iframe id="dialog-body" src="' + url + '" style="border: 0; width: 100%; height: 100%;"></iframe>';
                document.getElementById('dialog-body').contentWindow.dialogArguments = arg;
                /*document.getElementById('dialog-close').addEventListener('click', function(e) {
                 e.preventDefault();
                 dialog.close();
                 });*/
            }
            dialog.showModal();
            if (flag) {
                var ss = flag + ' = dialog';
                eval(ss);
                var str = JSON.stringify(arg);
                sessionStorage["result"] = str;
            } else {
                dalog = dialog;
            }
            //if using yield
            if(caller.indexOf('yield') >= 0) {
                return new Promise(function(resolve, reject) {
                    dialog.addEventListener('close', function() {
                        var returnValue = document.getElementById('dialog-body').contentWindow.returnValue;
                        document.body.removeChild(dialog);
                        resolve(returnValue);
                    });
                });
            }
            //if using eval
            var isNext = false;
            var nextStmts = caller.split('\n').filter(function(stmt) {
                if(isNext || stmt.indexOf('showModalDialog(') >= 0)
                    return isNext = true;
                return false;
            });
            dialog.addEventListener('close', function() {
                var returnValue = document.getElementById('dialog-body').contentWindow.returnValue;
                document.body.removeChild(dialog);
                if (nextStmts[0].indexOf('cyjg.html') == -1) {
                    nextStmts[0] = nextStmts[0].replace(/(window\.)?showModalDialog\(.*\)/g, JSON.stringify(returnValue));
                    eval('{\n' + nextStmts.join('\n'));
                }
            });
            //throw 'Execution stopped until showModalDialog is closed';
        };

})();

function kprqChange(kprq){
    var classInfo=$("#kprqjy").attr('class');
    if(kprq!="" && kprq != 'YYYYMMDD'){

        if(!acq(kprq)){
            $("#kprqjy").removeClass(classInfo);
            $("#kprqjy").addClass("tip_common_wrong");
            $("#kprqjy").addClass("font_red");
            var g=new Date();
            var h=g.getFullYear();
            var i=g.getMonth()+1;
            var j=g.getDate();
            var dd = h + "" + i + j;
            if (kprq == dd) {
                $("#kprqjy").html("当日开具发票次日查验!");
            } else {
                if (kprq == 'YYYYMMDD') {
                } else {
                    $("#kprqjy").html("开票日期有误!");
                }
            }
        }else{
            $("#kprqjy").removeClass(classInfo);
            if(kprq!=ca(0)){
                $("#kprqjy").addClass("tip_common_right");
                $("#kprqjy").html("&nbsp;");
                skxt=0;

            }else{
                if(fplx=="04"||fplx=="10"||fplx=="11"){
                    $("#kprqjy").addClass("tip_common_right");
                    $("#kprqjy").html("&nbsp;");
                    //skxt=1;
                }else{
                    $("#kprqjy").addClass("tip_common_wrong");
                    $("#kprqjy").addClass("font_red");
                    $("#kprqjy").html("开票日期有误!");
                    skxt=0;
                }
            }
        }
    }else{
        $("#kprqjy").removeClass(classInfo);
        $("#kprqjy").addClass("tip_common");
    }
}


function show_dialog(width, height, url, obj) {

    var main_div ;
    if(width==700){
        $("#floatwin1").width(width);
        $("#floatwin1").height(height);
        main_div = document.getElementById("floatwin1");
    }else{
        $("#floatwin").width(width);
        $("#floatwin").height(height);
        main_div = document.getElementById("floatwin");
    }

    // 获取浏览器窗口
    // var windowScreen = document.documentElement;
    // 获取main的div元素

    // 通过窗口宽高和div宽高计算位置
    // var main_left = (windowScreen.clientWidth - width)/2;
    // var main_top = (windowScreen.clientHeight - height)/2;
    // var main_left = (window.screen.width - width)/2;;//获取body宽度
    // var main_top = (window.screen.height- height)/2;//获取body高度
    // 位置赋值
    // main_div.style.left = main_left + "px";
    // main_div.style.top = main_top + "px";
    if (url.indexOf("?") != -1) {
        var parms = url.substr(url.indexOf("?") + 1);
        var parm = parms.split("&");
        //alert("parmlength: " + parm.length);
        var pname = "";
        var pvalue = "";
        for (i = 0; i < parm.length; i++) {
            pname = parm[i].substr(0, parm[i].indexOf("="));
            pvalue = parm[i].substr(parm[i].indexOf("=") + 1);
            sessionStorage[pname] = pvalue;  //通过这个传递参数
        }
        url = url.substr(0, url.indexOf("?"));
    }
    var str;
    if (obj != "") {
        str = JSON.stringify(obj);
        sessionStorage["result"] = str;
    }
    if(width==700){
        $("#floatwin1").attr("z-index",1);
        $("#floatwin1").load(url);

        $("#cover").show();
        $("#floatwin1").show();
    }else{
        $("#floatwin").attr("z-index",1);
        $("#floatwin").load(url);

        $("#cover").show();
        $("#floatwin").show();
    }
}
