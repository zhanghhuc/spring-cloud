<!DOCTYPE html PUBLIC ""><HTML
xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=UTF-8" language="java"  pageEncoding="UTF-8"  %>
<html>

<script>

</script>

<BODY onload="arw();">
<DIV class="pad_t_50 clearfix" id="content2">

    <!--右侧-->
    <TABLE class="comm_table2 fr" border="0">
        <TBODY>
        <TR>
            <TD>&nbsp;</TD>
            <TD id="imgarea">
                <DIV><A href="javascript:void(0);"><IMG width="120" height="50" id="yzm_img"
                        style="vertical-align: top; cursor: pointer;" src="https://inv-veri.chinatax.gov.cn/国家税务总局全国增值税发票查验平台_files/code.png">
                    <IMG width="120" height="50" id="yzm_unuse_img" style="vertical-align: top; display: none; cursor: pointer;"
                         src="https://inv-veri.chinatax.gov.cn/国家税务总局全国增值税发票查验平台_files/code.png"></A>
                    <DIV style="margin-top: 10px; margin-right: 20px; float: right;"><SPAN
                            class="tip_yzm">点击图片刷新</SPAN></DIV></DIV></TD>
            <TD>&nbsp;</TD></TR>
        <img src="data:image/png;base64,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" />
        </TBODY>
        </TABLE>
    </DIV>
<SCRIPT language="javascript">
    var dalog;
    var dalog2;
    var show_yzm = "";
    var code = new Array('144031539110', '131001570151', '133011501118', '111001571071');
    var code10 = new Array('1440315391', '1310015701', '1330115011', '1110015710');

    var yzmSj = "";
    var jmmy = "";
    var fplx = "99";
    var skxt = 0;
    var xsje = 1;
    var swjgmc = "";
    var delayFlag = "";
    var delayTime = 6;
    var delayMessage = "";
    var iv;
    var salt;


    $('#yzm').focus(function() {
        var value = $('#yzm').val();
        var defaultValue = "请输入验证码";
        if (value == defaultValue) {
            $('#yzm').val('');
            $('#yzm').css('color', '#000000');
        }
    });

    $('#yzm').blur(function() {
        var value = $('#yzm').val();
        if (value == "") {
            $('#yzm').val('请输入验证码');
            $('#yzm').css('color', '#999999');
        }
        acb(fplx);
    });

    $("#yzm").keyup(function() {
        var yzm = $("#yzm").val().trim();
        //console.log("yzm keyup :" + yzm);
        var classInfo = $("#yzmjy").attr('class');
        $("#yzmjy").removeClass(classInfo);
        if (!avym(yzm)) {
            $("#yzmjy").addClass("tip_common_wrong");
            $("#yzmjy").addClass("font_red");
        } else {
            $("#yzmjy").html("&nbsp");
        }
        acb(fplx);
    });

    $("#yzm_img").click(function() {
        console.log("click....");
       getYzmXx();

    });


    $("#checkfp").click(function() {
        var fpdm = $("#fpdm").val().trim();
        var fphm = $("#fphm").val().trim();
        var kprq = $("#kprq").val().trim();
        var dmchek = getSwjg(fpdm, 1);
        if (dmchek.length > 0) {
            var DATE_FORMAT = /^[0-9]{4}[0-1]?[0-9]{1}[0-3]?[0-9]{1}$/;
            if (DATE_FORMAT.test(kprq)) {

            } else {
                jAlert("日期格式错误，为YYYYMMDD格式！", "提示");
                return;
            }
            var kjje = $("#kjje").val().trim();
            if (aur()) {
                $('#checkfp').hide();
                $('#uncheckfp').show();
                //$('#checkfp').attr("disabled", true);
                var date = new Date();

                var yzm = $("#yzm").val().trim();
                var setText = "";

                var param = null;
                var url = "";

                var iterationCount = 100;
                var keySize = 128;
                iv = CryptoJS.lib.WordArray.random(128 / 8).toString(CryptoJS.enc.Hex);
                salt = CryptoJS.lib.WordArray.random(128 / 8).toString(CryptoJS.enc.Hex);
                var aesUtil = new AesUtil(keySize, iterationCount);

                if (avai(fplx)) {

                    if (fplx == "01" || fplx == "02" || fplx == "03") {
                        var index = kjje.indexOf(".");
                        if (index > 0) {
                            var arr = kjje.split(".");
                            if (arr[1] == "00" || arr[1] == "0") {
                                kjje = arr[0];
                            } else if (arr[1].charAt(1) == "0") {
                                kjje = arr[0] + "." + arr[1].charAt(0);
                            }
                        }
                    }

                    param = {
                        'fpdm': fpdm,
                        'fphm': fphm,
                        'kprq': kprq,
                        'fpje': kjje,
                        'fplx': fplx,
                        'yzm': yzm,
                        'yzmSj': yzmSj,
                        'index': jmmy,
                        'iv': iv,
                        'salt': salt
                    };
                    delayMessage = "发票查验请求失败!";
                    showTime();

                    url = ip + "/query"; //省局外网包查验发票的地址
                    $.ajax({
                        type: "post",
                        url: url,
                        dataType: "jsonp",
                        data: param,
                        jsonp: "callback",
                        success: function(jsonData) {
                            delayFlag = "1";
                            var cyjgdm = jsonData.key1;

                            if (cyjgdm == "1") {
                                show_yzm = "";
                                //标志为1，是试运行且代码为非北京，上海，深圳的。防范前台没卡住的情况
                                jAlert("该省尚未开通发票查验功能！", "提示");
                            } else if (cyjgdm == "001") {
                                show_yzm = "";
                                var t = jsonData.key5;
                                //var t = "var aa17=fpdm+\"≡\"+fphm+\"≡\"+swjgmc+\"≡\"+jsonData.key2+\"≡\"+yzmSj";
                                //这里是从后台传来的
                                eval(t);
                                var hwxx = jsonData.key3;
                                var jmbz = "";
                                if (jsonData.key4.trim() != '') {
                                    jmbz = aesUtil.decrypt(jsonData.key8, jsonData.key7, jsonData.key9, jsonData.key4); //解密备注
                                }
                                var jmsort = aesUtil.decrypt(jsonData.key8, jsonData.key7, jsonData.key9, jsonData.key10); //解密排序顺序
                                var tt = jsonData.key6;
                                jsname = jsonData.key11 + ".js"; //用哪个js，从后台传过来。还需要一个配置页面
                                //var tt = "var result={\"template\":3,\"c342\":fplx,\"d64b\":aa17,\"dbd2\":hwxx,\"jmbz\":jmbz,\"sort\":jmsort}";//这个也是后台传的
                                eval(tt);
                                //key7也需要获取，如果未获取到，则用上面定义的jsname，否则，使用key7代入的jsname。
                                if (browser == "edge" || browser == "firefox") {
                                    sessionStorage["jsname"] = jsname;
                                    $.getScript("js/" + jsname,
                                            function() { //加载,并执行回调函数 (回调函数是空的)
                                                bb();
                                                show_dialog(1100, 700, "cyjgedge" + fplx + ".html", result);
                                            });
                                } else if (browser == "ie8") { //ie8不能通过方法参数获取到相应数据，所以特殊处理
                                    sessionStorage["jsname"] = jsname;
                                    sessionStorage["browser"] = "ie8";
                                    var str = JSON.stringify(result);
                                    sessionStorage["result"] = str;
                                    $.getScript("js/" + jsname,
                                            function() { //加载,并执行回调函数 (回调函数是空的)
                                                bb();
                                                show_dialog(1100, 700, "cyjgedge" + fplx + ".html", result);
                                            });
                                } else {
                                    sessionStorage["jsname"] = jsname;
                                    $.getScript("js/" + jsname,
                                            function() { //加载,并执行回调函数
                                                sessionStorage["rule"] = rule;
                                                window.showModalDialog('cyjg' + fplx + '.html', result, "dialogTop:10px;dialogWidth:1100px;dialogHeight:700px;");
                                            });
                                }
                            } else if (cyjgdm == "002") {
                                show_yzm = "";
                                jAlert("超过该张发票当日查验次数(请于次日再次查验)!", "提示",
                                        function(r) {
                                            if (r) {
                                                arw();
                                            }
                                        });
                            } else if (cyjgdm == "003") {
                                show_yzm = "";
                                jAlert("发票查验请求太频繁，请稍后再试！", "提示",
                                        function(r) {
                                            if (r) {
                                                //$('#uncheckfp').hide();
                                                //$('#checkfp').show();
                                            }
                                        });
                            } else if (cyjgdm == "004") {
                                show_yzm = "";
                                jAlert("超过服务器最大请求数，请稍后访问!", "提示",
                                        function(r) {
                                            if (r) {
                                                //$('#uncheckfp').hide();
                                                //$('#checkfp').show();
                                            }
                                        });
                            } else if (cyjgdm == "005") {
                                show_yzm = "";
                                jAlert("请求不合法!", "提示",
                                        function(r) {
                                            if (r) {
                                                //$('#uncheckfp').hide();
                                                //$('#checkfp').show();
                                            }
                                        });
                            } else if (cyjgdm == "020") {
                                show_yzm = "";
                                jAlert("由于查验行为异常，涉嫌违规，当前无法使用查验服务！", "提示",
                                        function(r) {
                                            if (r) {
                                                //$('#uncheckfp').hide();
                                                //$('#checkfp').show();
                                            }
                                        });
                            } else if (cyjgdm == "006") {
                                show_yzm = "";
                                setText = "不一致";
                                param = {
                                    'fplx': fplx,
                                    'swjg': swjgmc,
                                    'fpdm': fpdm,
                                    'fphm': fphm,
                                    'kprq': kprq,
                                    'kjje': kjje,
                                    'cysj': yzmSj,
                                    'setText': setText
                                };
                                if (browser == "edge" || browser == "firefox") {
                                    show_dialog(700, 400, "jgbyz.html", param);
                                } else { //ie8无问题
                                    window.showModalDialog('jgbyz.html', param, "dialogWidth:700px;dialogHeight:400px;center:yes;scroll:no");
                                }
                                //$('#uncheckfp').hide();
                                //$('#checkfp').show();
                            } else if (cyjgdm == "007") {
                                show_yzm = "";
                                jAlert("验证码失效!", "提示",
                                        function(r) {
                                            if (r) {
                                                //$('#uncheckfp').hide();
                                                //$('#checkfp').show();
                                            }
                                        });
                            } else if (cyjgdm == "008") {
                                show_yzm = "";
                                jAlert("验证码错误!", "提示",
                                        function(r) {
                                            if (r) {
                                                //$('#uncheckfp').hide();
                                                //$('#checkfp').show();
                                            }
                                        });
                            } else if (cyjgdm == "009") {
                                show_yzm = "";
                                setText = "查无此票";
                                param = {
                                    'fplx': fplx,
                                    'swjg': swjgmc,
                                    'fpdm': fpdm,
                                    'fphm': fphm,
                                    'kprq': kprq,
                                    'kjje': kjje,
                                    'cysj': yzmSj,
                                    'setText': setText
                                };
                                if (browser == "edge" || browser == "firefox") {
                                    show_dialog(700, 400, "jgbyz.html", param);
                                } else { //ie8无问题
                                    window.showModalDialog('jgbyz.html', param, "dialogWidth:700px;dialogHeight:400px;center:yes;scroll:no");
                                }
                                //$('#uncheckfp').hide();
                                //$('#checkfp').show();
                            } else if (cyjgdm == "rqerr") {
                                show_yzm = "";
                                jAlert("当日开具发票可于次日进行查验！", "警告");
                            } else if (cyjgdm == "010") {
                                show_yzm = "";
                                var etype = jsonData.key2;
                                if (etype == 'inredis') {
                                    etype = "(02)";
                                } else if (etype == 'weberr') {
                                    etype = "(03)";
                                }
                                jAlert("网络超时，请重试！" + etype, "系统错误",
                                        function(r) {
                                            if (r) {
                                                //$('#uncheckfp').hide();
                                                //$('#checkfp').show();
                                            }
                                        });
                            } else if (cyjgdm == "010_") {
                                show_yzm = "";
                                jAlert("网络超时，请重试！(05)", "系统错误",
                                        function(r) {
                                            if (r) {}
                                        });
                            } else {
                                show_yzm = "";
                                jAlert("网络超时，请重试！(04)", "系统错误",
                                        function(r) {
                                            if (r) {
                                                //$('#uncheckfp').hide();
                                                //$('#checkfp').show();
                                            }
                                        });
                            }
                            $('#uncheckfp').hide();
                            $('#checkfp').show();
                            //$('#checkfp').removeAttr("disabled");
                            yzmSj = "";

                        }
                    });
                }
            }
        }

    });
    var retrycount = 0;
    function getYzmXx() {
        show_yzm = "1";
        //if (ip == "") {  //如果设置的是空地址，说明是通过数组获取相应的省局服务器地址，非开发及测试模式时
//        var fpdm = $("#fpdm").val().trim();
        var fpdm = "3200111170";
        var swjginfo = getSwjg(fpdm, 0);
        console.log(swjginfo);
        ip = swjginfo[1];
        //}
        var url = ip + "/yzmQuery";
        delayMessage = "";
        showTime();
        var fpdmyzm = $("#fpdm").val().trim();
        var fphm = $("#fphm").val().trim();
        var kprq = $("#kprq").val().trim();
        var kjje = $("#kjje").val().trim();
        var rad = Math.random();
        var param = {
            'fpdm': fpdmyzm,
            'r': rad
        };
        $.ajaxSetup({
            cache: false
        });

        yzmFlag = 1;

        $.ajax({
            type: "post",
            url: url,
            data: param,
            dataType: "jsonp",
            jsonp: "callback",
            success: function(jsonData) {
                delayFlag = "1";
                var key1 = jsonData.key1;
                var key2 = jsonData.key2;
                var key3 = jsonData.key3;
                var key4 = jsonData.key4;
                if (key1 == "003") {
                    jAlert("验证码请求次数过于频繁，请1分钟后再试！", "警告");
                    $('#yzm_img').hide();
                    for (i = 0; i < lastlinenum; i++) {
                        $("#i" + i).remove();
                    }
                    //setTimeout(showYzm, 60 * 1000);
                } else if (key1 == "005") {
                    jAlert("非法请求!", "警告");
                } else if (key1 == "010") {
                    jAlert("网络超时，请重试！(01)", "警告");
                } else if (key1 == "fpdmerr") {
                    jAlert("请输入合法发票代码!", "警告");
                } else if (key1 == "024") {
                    jAlert("24小时内验证码请求太频繁，请稍后再试！", "警告");
                    $('#yzm_img').hide();
                    for (i = 0; i < lastlinenum; i++) {
                        $("#i" + i).remove();
                    }
                } else if (key1 == "016") {
                    jAlert("服务器接收的请求太频繁，请稍后再试！", "警告");
                    $('#yzm_img').hide();
                    for (i = 0; i < lastlinenum; i++) {
                        $("#i" + i).remove();
                    }
                } else if (key1 == "020") {
                    jAlert("由于查验行为异常，涉嫌违规，当前无法使用查验服务！", "提示",
                            function(r) {
                                if (r) {
                                    //$('#uncheckfp').hide();
                                    //$('#checkfp').show();
                                }
                            });
                } else if (key1 != "") {
                    $("#yzm_img").attr("src", "data:image/png;base64," + key1);
                    $("#yzm_unuse_img").attr("src", "data:image/png;base64," + key1);

                    if (key4 == '00') {
                        $("#yzminfo").text("请输入验证码文字");
                    } else if (key4 == '01') {
                        $("#yzminfo").html("请输入验证码图片中<font color=\"red\" size=\"4\" style=\"background:#C0C0C0\">红色</font>文字");
                    } else if (key4 == '02') {
                        $("#yzminfo").html("请输入验证码图片中<font color=\"yellow\" size=\"4\" style=\"background:#C0C0C0\">黄色</font>文字");
                    } else if (key4 == '03') {
                        $("#yzminfo").html("请输入验证码图片中<font color=\"blue\" size=\"4\" style=\"background:#C0C0C0\">蓝色</font>文字");
                    }
                    drawline();
                    yzmSj = key2;
                    jmmy = key3;
                }

            },
            //3秒后超时
            timeout: 5000,
            error: function(XMLHttpRequest, textStatus, errorThrown) {
                if (retrycount == 9) {
                    //jAlert("根证书未安装或与" + swjginfo[0] + "国税\r\n服务器之间的网络异常。", "警告");
                } else {
                    retrycount = retrycount + 1;
                    //console.log("retrycount: " + retrycount);
                    getYzmXx();
                }
            }
        });

        yzmWait = 2;
        yzmTime($('#yzm_img'));
    }
    window.onerror = function(msg, file, line) {
        return true; //true表示是否需要弹出错误提示。false 不需要。
    };
    function handleErr(msg, url, line) {
        var errorTxt = "";
        errorTxt += "Error: " + msg + "\n";
        errorTxt += "Line: " + line + "\n";
        errorTxt += "Url: " + url + "\n";
        //alert(errorTxt);
        return true;
    }
    var yzmWait = 2;
    function yzmTime(o) {
        if (yzmWait == 0) {
            $('#yzm_unuse_img').hide();
            $('#yzm_img').show();
            yzmWait = 60;
        } else {
            if (yzmWait == 2) {
                $('#yzm_unuse_img').show();
                $('#yzm_img').hide();
            }
            yzmWait--;
            setTimeout(function() {
                        yzmTime(o);
                    },
                    1000)
        }
    }

    function randint(n, m) {
        var c = m - n + 1;
        var num = Math.random() * c + n;
        return Math.floor(num);
    }

    var lastlinenum = 0;



</SCRIPT>
</BODY></HTML>
