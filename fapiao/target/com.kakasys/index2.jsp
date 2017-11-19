<!DOCTYPE HTML>
<!-- saved from url=(0043)https://inv-veri.chinatax.gov.cn/index.html -->
<!DOCTYPE html PUBLIC ""><HTML
xmlns="http://www.w3.org/1999/xhtml"><HEAD><META content="IE=11.0000"
http-equiv="X-UA-Compatible">

<META http-equiv="Content-Type" content="text/html; charset=utf-8">
<META http-equiv="X-UA-Compatible" content="IE=edge">
<TITLE>国家税务总局全国增值税发票查验平台</TITLE>
<META name="keywords" content="">
<META http-equiv="pragma" content="no-cache">
<META http-equiv="Cache-Control" content="no-cache, must-revalidate">
<META http-equiv="expires" content="0">
<META name="format-detection" content="telephone=no">
<META content="" property="wb:webmaster">
<SCRIPT language="javascript">
    document.write("<l" + "ink rel='stylesheet' type='text/css' href='css/common.css?"+Math.random()+"' />");
    document.write("<l" + "ink rel='stylesheet' type='text/css' href='css/jquery.alerts.css?"+Math.random()+"' media='screen' />");
    document.write("<l" + "ink rel='stylesheet' type='text/css' href='css/bootstrap-datepicker.min.css' />");



    if(top != self){
        location.href = "about:blank";
    }


    document.write("<s"+"cript charset='utf-8' type='text/javascript' src='js/jquery-1.10.2.min.js'></scr"+"ipt>");
    document.write("<s"+"cript charset='utf-8' type='text/javascript' src='js/jquery.PrintArea.js'></scr"+"ipt>");
    document.write("<s"+"cript charset='utf-8' type='text/javascript' src='js/jquery.alerts.js?"+Math.random()+"'></scr"+"ipt>");
    document.write("<s"+"cript charset='utf-8' type='text/javascript' src='js/jquery.md5.js'></scr"+"ipt>");
    document.write("<s"+"cript charset='utf-8' type='text/javascript' src='js/showModalDialog.js?"+Math.random()+"'></scr"+"ipt>");
    document.write("<s"+"cript charset='utf-8' type='text/javascript' src='js/aes.js'></scr"+"ipt>");
    document.write("<s"+"cript charset='utf-8' type='text/javascript' src='js/pbkdf2.js'></scr"+"ipt>");
    document.write("<s"+"cript charset='utf-8' type='text/javascript' src='js/AesUtil.js'></scr"+"ipt>");
    document.write("<s"+"cript charset='utf-8' type='text/javascript' src='js/cookies.js?"+Math.random()+"'></scr"+"ipt>");
    document.write("<s"+"cript charset='utf-8' type='text/javascript' src='js/common.js?"+Math.random()+"'></scr"+"ipt>");
    document.write("<s"+"cript charset='utf-8' type='text/javascript' src='js/validate.js?"+Math.random()+"'></scr"+"ipt>");
    document.write("<s"+"cript charset='utf-8' type='text/javascript' src='js/result.js?"+Math.random()+"'></scr"+"ipt>");
    document.write("<s"+"cript type='text/javascript' language='javascript' src='js/bootstrap-datepicker.js'></scr"+"ipt>");
    document.write("<s"+"cript type='text/javascript' language='javascript' src='js/bootstrap-datepicker.zh-CN.min.js'></scr"+"ipt>");

</SCRIPT>

<SCRIPT>
    var browser = "";
    browser = getBrowser();
    function pad(num, n) {
        var len = num.toString().length;
        while(len < n) {
            num = "0" + num;
            len++;
        }
        return num;
    }

    var jsname = "";
</SCRIPT>

<META name="GENERATOR" content="MSHTML 11.00.10570.1001"></HEAD>
<BODY onload="arw();">
<DIV id="top"></DIV><!--content begin-->
<DIV class="pad_t_50 clearfix" id="content2">
    <DIV class="ktsm" id="ktsm_tip">
        <P><B>提示：</B>                您当前使用的是非推荐版本浏览器，可能会影响查验结果展示，请使用推荐版本浏览器进行查验！
        </P><SPAN class="close_ktsm"></SPAN>       </DIV><!--左侧-->
    <DIV class="sm_box"><A class="sm_btn sm_btn1" href="https://inv-veri.chinatax.gov.cn/fpcysm.html">发票查验说明<SPAN
            class="arrow-right"></SPAN></A>             <A class="sm_btn sm_btn2" href="https://inv-veri.chinatax.gov.cn/cjwt/fpjgsm.html">查验结果说明<SPAN
            class="arrow-right"></SPAN></A>             <A class="sm_btn sm_btn3" href="https://inv-veri.chinatax.gov.cn/fpcs/fpzwsbff.html">发票真伪识别方法<SPAN
            class="arrow-right"></SPAN></A>
        <DIV class="cont_ps">
            <P><SPAN style="color: red;">1、<A href="https://inv-veri.chinatax.gov.cn/zdaz.html">首次查验前请点此安装根证书。</A></SPAN><BR>2、当日开具发票最快可于次日进行查验。
                <BR>3、每份发票每天最多可查验5次。<BR>4、可查验最近1年内增值税发票管理新系统开具的发票。<BR>5、纳税咨询服务，可拨打<span style="color:red">12366</span>。&nbsp;</P></DIV></DIV><!--左侧-->
    <!--右侧-->
    <TABLE class="comm_table2 fr" border="0">
        <TBODY>
        <TR>
            <TD class="align_right2"><SPAN class="font_red">*</SPAN>发票代码：</TD>
            <TD><INPUT id="fpdm" type="text" maxlength="12" autocomplete="off"></TD>
            <TD width="200">
                <DIV class="tip_common" id="fpdmjy">请输入发票代码</DIV></TD></TR>
        <TR>
            <TD class="align_right2"><SPAN class="font_red">*</SPAN>发票号码：</TD>
            <TD><INPUT id="fphm" type="text" maxlength="8" autocomplete="off"></TD>
            <TD>
                <DIV class="tip_common" id="fphmjy">请输入发票号码</DIV></TD></TR>
        <TR>
            <TD class="align_right2"><SPAN class="font_red">*</SPAN>开票日期：</TD>
            <TD><LABEL><INPUT class="form-control" id="kprq" style="color: rgb(153, 153, 153);" type="text" maxlength="8" value="YYYYMMDD" autocomplete="off">
            </LABEL>                 <!-- <input type="text" id="kprq" maxlength="8" value="YYYYMMDD" style="color:#999999"/> -->
            </TD>
            <TD>
                <DIV class="tip_common" id="kprqjy">请输入开票日期</DIV></TD></TR>
        <TR>
            <TD class="align_right2"><SPAN class="font_red">*</SPAN><SPAN
                    id="context">开具金额(不含税)：</SPAN></TD>
            <TD><INPUT id="kjje" type="text" autocomplete="off"></TD>
            <TD>
                <DIV class="tip_common" id="kjjejy">请输入开具金额</DIV></TD></TR>
        <TR>
            <TD class="align_right2"><SPAN class="font_red">*</SPAN>验证码：</TD>
            <TD><INPUT id="yzm" style="color: rgb(153, 153, 153);" type="text" value="请输入验证码" autocomplete="off">
                <!--<span class="con_img" id="imgarea"><a href="#"><input type="button" value="获取验证码" id="yzm_img" /></a></span> code.png -->
            </TD>
            <TD id="yzminfo" style="font-size: 14px;">&nbsp;</TD><!-- <td><div id="yzmjy"class="tip_common">请输入验证码</div></td> -->
        </TR>
        <TR>
            <TD>&nbsp;</TD>
            <TD id="imgarea">
                <DIV><A href="javascript:void(0);"><IMG width="120" height="50" id="yzm_img"
                                                                                                                                                  style="vertical-align: top; cursor: pointer;" src="国家税务总局全国增值税发票查验平台_files/code.png">
                    <IMG width="120" height="50" id="yzm_unuse_img" style="vertical-align: top; display: none; cursor: pointer;"
                         src="国家税务总局全国增值税发票查验平台_files/code.png"></A>
                    <DIV style="margin-top: 10px; margin-right: 20px; float: right;"><SPAN
                            class="tip_yzm">点击图片刷新</SPAN></DIV></DIV></TD>
            <TD>&nbsp;</TD></TR>


        <TR>
           <td>获取验证码</td>
           <td>
               <A href="javascript:void(0);"><IMG width="120" height="50" id="myyzm"
                                                  style="vertical-align: top; cursor: pointer;" src="国家税务总局全国增值税发票查验平台_files/code.png">
                   <IMG width="120" height="50" id="myimg" style="vertical-align: top; display: none; cursor: pointer;"
                        src="国家税务总局全国增值税发票查验平台_files/code.png"></A>
           </td>
           <td></td>
        </TR>


        <TR>
            <TD colspan="3">
                <DIV class="comm_btn_div2 clearfix"><BUTTON disabled="true" class="gray_button"
                                                            id="uncheckfp" style="display: none; cursor: default;">查 验</BUTTON>
                    <BUTTON class="blue_button" id="checkfp" onmouseout="this.className='blue_button';"
                            onmousemove="this.className='green_button';">查 验</BUTTON>
                    &nbsp;&nbsp;&nbsp;&nbsp;          <BUTTON class="blue_button" id="reset"
                                                              onmouseout="this.className='blue_button';" onmousemove="this.className='green_button';">重
                        置</BUTTON></DIV></TD></TR></TBODY></TABLE><!--右侧--> </DIV><!--content end--> <!--footer begin-->
<DIV id="foot"></DIV><!--footer end--> <!--
<dl style="position:absolute; right:5px; top:190px;"><dt><a href="download/fpcy_android.apk"><img src="images/android.gif" /></a></dt><dd><a href="download/fpcy_android.apk"><img src="images/android_btn.gif" /></a></dd></dl>
<dl style="position:absolute; right:5px; top:380px;"><dt><a href="upload/fpcy_ios.rar"><img src="images/ios.gif" /></a></dt><dd><a href="upload/fpcy_ios.rar"><img src="images/ios_btn.gif" /></a></dd></dl>
-->
<DIV id="cover"></DIV><!-- <div id="cls"  style="display: none;"> -->
<DIV id="floatwin"></DIV>
<DIV id="floatwin1"></DIV><!-- </div> -->
<SCRIPT language="javascript">
    /*
     if (browser == "error") {
     jAlert("建议使用IE8及以上版本浏览器，使用其它\r\n浏览器可能会导致显示异常。", "警告");
     }
     */
    if (browser == "ie6") {
        jAlert("您正在使用的浏览器版本过低，请升级至IE8及以上版本！", "警告");
    } else if (browser.indexOf("others") != -1 || browser.indexOf("ok") != -1) {
        if (browser.indexOf("_chrome") != -1) {
            browser = browser.substring(browser.indexOf("_") + 1);
            browser = browser.toUpperCase();
            browser = "谷歌" + browser.substring(browser.indexOf(" "));
            $('#ktsm_tip').html("<p><b>提示：</b>您使用的是内核为" + browser + "版本浏览器，建议使用内核为谷歌 55版本浏览器。同时，请参照操作说明安装根证书。</p>" + "<span class='close_ktsm'></span>");
            $('#ktsm_tip').show();
            browser = "chrome";
        } else if (browser.indexOf("_firefox") != -1) {
            browser = browser.substring(browser.indexOf("_") + 1);
            browser = browser.toUpperCase();
            browser = "火狐" + browser.substring(browser.indexOf(" "));
            $('#ktsm_tip').html("<p><b>提示：</b>您使用的是" + browser + "版本浏览器，建议使用50版本。同时，请参照操作说明安装根证书。</p>" + "<span class='close_ktsm'></span>");
            $('#ktsm_tip').show();
            browser = "firefox";
        } else if (browser.indexOf("chrome") != -1) {
            $('#ktsm_tip').html("<p><b>提示：</b>您使用的是谷歌 55版本浏览器，请参照操作说明安装根证书再进行发票查验操作！</p>" + "<span class='close_ktsm'></span>");
            $('#ktsm_tip').show();
            browser = "chrome";
        } else if (browser.indexOf("firefox") != -1) {
            $('#ktsm_tip').html("<p><b>提示：</b>您使用的是火狐 50版本浏览器，请参照操作说明安装根证书再进行发票查验操作！</p>" + "<span class='close_ktsm'></span>");
            $('#ktsm_tip').show();
            browser = "firefox";
        }
    } else if (browser == "others") {
        $('#ktsm_tip').show();
    }

    var dalog;
    var dalog2;
    var show_yzm = "";
    var code = new Array('144031539110', '131001570151', '133011501118', '111001571071');
    var code10 = new Array('1440315391', '1310015701', '1330115011', '1110015710');
    $(document).ready(function() {

        // var MyStore = new ActiveXObject("CAPICOM.Store");
        // var Certificate = new ActiveXObject("CAPICOM.Certificate");
        // alert(MyStore);
        cover_width = document.body.clientWidth; //获取body宽度
        cover_height = window.screen.height; //获取body高度
        document.getElementById("cover").style.width = cover_width + "px";
        document.getElementById("cover").style.height = cover_height + "px";

        $("#top").load("top.html?" + Math.random());
        $("#foot").load("footer.html?" + Math.random());

        if (browser == 'ie8') {
            sessionStorage["browser"] = "ie8";
        } else if (browser == 'edge' || browser == 'firefox') {
            sessionStorage["browser"] = "edge";
        } else {
            sessionStorage["browser"] = "";
        }

        //关闭开通说明
        $(".close_ktsm").click(function() {
            $(this).parent().hide();
        });

        var endTime = new Date();
        var yesterday_miliseconds = endTime.getTime() - 1000 * 60 * 60 * 24;
        var Yesterday = new Date();
        Yesterday.setTime(yesterday_miliseconds);

        $('#kprq').datepicker({
            format: "yyyymmdd",
            autoclose: true,
            endDate: Yesterday,
            language: "zh-CN"
        }).on('changeDate',
                function(ev) {
                    var kprq = $("#kprq").val().trim();
                    $('#kprq').css('color', '#555');
                    if (kprq.length != "") {
                        kprqChange(kprq);
                    } else {
                        //$("#kprqjy").removeClass();
                        // $("#kprqjy").addClass("tip_common");
                        //$("#kprqjy").html("请输入开票日期");
                    }

                    acb(fplx);
                });
    });
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

    $("#fpdm").keyup(function() {
        var fpdm = $("#fpdm").val().trim();
        // if (fpdm.length == 10 || fpdm.length >= 12) {
        afcdm(fpdm);
        // } else {
        //    $("#fpdmjy").removeClass();
        //    $("#fpdmjy").addClass("tip_common");
        //    $("#fpdmjy").html("请输入发票代码");
        // }
        acb(fplx);
    });

    $('#fpdm').blur(function() {
        var fpdm = $("#fpdm").val().trim();
        if (fpdm.length == 10 || fpdm.length == 12) {
            afcdm(fpdm);
        } else {
            $("#fpdmjy").addClass("tip_common_wrong");
            $("#fpdmjy").addClass("font_red");
            $("#fpdmjy").html("发票代码有误!");
        }
        acb(fplx);
    });

    $("#fphm").keyup(function() {
        var fphm = $("#fphm").val().trim();
        if (fphm.length >= 8) {
            ahmch(fphm);
        } else {
            $("#fphmjy").removeClass();
            $("#fphmjy").addClass("tip_common");
            $("#fphmjy").html("请输入发票号码");
        }
        acb(fplx);
    });

    $('#fphm').blur(function() {
        var fphm = $("#fphm").val().trim();
        if (fphm.length != 0 && fphm.length < 8) {
            ahmch(fphm);
        }
        acb(fplx);
    });
    $("#kjje").keyup(function() {
        var kjje = "";
        var fpdm = $("#fpdm").val().trim();

        var classInfo = $("#kjjejy").attr('class');
        $("#kjjejy").removeClass(classInfo);

        if (fpdm == "" || fplx == "01" || fplx == "02" || fplx == "03" || fplx == "99") {
            $("#kjje").attr('maxlength', '20');
            kjje = $("#kjje").val().trim();
            if (!aje(fpdm, kjje)) {
                $("#kjjejy").addClass("tip_common_wrong");
                $("#kjjejy").addClass("font_red");
                if (fplx == "02") {
                    $("#kjjejy").html("合计金额有误!");
                } else if (fplx == "03") {
                    $("#kjjejy").html("不含税价有误!");
                } else {
                    $("#kjjejy").html("开票金额有误!");
                }
            } else {
                $("#kjjejy").addClass("tip_common_right");
                $("#kjjejy").html("&nbsp;");
            }
        } else if (fplx == "04" || fplx == "10" || fplx == "11") {
            $("#kjje").attr('maxlength', '6');
            kjje = $("#kjje").val().trim();
            if (kjje.length >= 6) {

                if (!ajy(kjje)) {
                    $("#kjjejy").addClass("tip_common_wrong");
                    $("#kjjejy").addClass("font_red");
                    $("#kjjejy").html("校验码有误!");
                } else {
                    $("#kjjejy").addClass("tip_common_right");
                    $("#kjjejy").html("&nbsp;");
                }
            } else {
                $("#kjjejy").removeClass();
                $("#kjjejy").addClass("tip_common");
                $("#kjjejy").html("请输入校验码<font color=\"red\" size=\"4\">后六位</font>");
            }

        }
        acb(fplx);
    });
    $("#kjje").blur(function() {
        var kjje = $("#kjje").val().trim();
        var fpdm = $("#fpdm").val().trim();

        var classInfo = $("#kjjejy").attr('class');
        $("#kjjejy").removeClass(classInfo);

        if (fpdm == "" || fplx == "01" || fplx == "02" || fplx == "03" || fplx == "99") {
            if (kjje.length != 0) {
                if (!aje(fpdm, kjje)) {
                    $("#kjjejy").addClass("tip_common_wrong");
                    $("#kjjejy").addClass("font_red");
                    if (fplx == "02") {
                        $("#kjjejy").html("合计金额有误!");
                    } else if (fplx == "03") {
                        $("#kjjejy").html("不含税价有误!");
                    } else {
                        $("#kjjejy").html("开票金额有误!");
                    }
                } else {
                    $("#kjjejy").addClass("tip_common_right");
                    $("#kjjejy").html("&nbsp;");
                }
            } else {
                $("#kjjejy").removeClass();
                $("#kjjejy").addClass("tip_common");
                if (fplx == "02") {
                    $("#kjjejy").html("请输入合计金额");
                } else if (fplx == "03") {
                    $("#kjjejy").html("请输入不含税价");
                } else {
                    $("#kjjejy").html("请输入开具金额");
                }
            }
        } else if (fplx == "04" || fplx == "10" || fplx == "11") {
            if (kjje.length != 0) {
                if (!ajy(kjje)) {
                    $("#kjjejy").addClass("tip_common_wrong");
                    $("#kjjejy").addClass("font_red");
                    $("#kjjejy").html("校验码有误!");
                } else {
                    $("#kjjejy").addClass("tip_common_right");
                    $("#kjjejy").html("&nbsp;");
                }
            } else {
                $("#kjjejy").removeClass();
                $("#kjjejy").addClass("tip_common");
                $("#kjjejy").html("请输入校验码<font color=\"red\" size=\"4\">后六位</font>");
            }
        }
        acb(fplx);
    });

    $("#kprq").keyup(function() {
        var kprq = $("#kprq").val().trim();
        if (kprq.length >= 8) {
            kprqChange(kprq);
        } else {
            $("#kprqjy").removeClass();
            $("#kprqjy").addClass("tip_common");
            $("#kprqjy").html("请输入开票日期");
        }
        acb(fplx);
    });
    /*   $('#kprq').focus(function(){
     var value= $('#kprq').val();
     var defaultValue="YYYYMMDD";
     if(value==defaultValue){
     $('#kprq').val('');
     $('#kprq').css('color','#000000');
     }
     });
     */
    $("#kprq").blur(function() {
        var kprq = $("#kprq").val().trim();
        if (kprq == "") {
            $('#kprq').val("YYYYMMDD");
            $('#kprq').css('color', '#999999');
        }
        if (kprq.length != 0) {
            kprqChange(kprq);
        } else {
            $("#kprqjy").removeClass();
            $("#kprqjy").addClass("tip_common");
            $("#kprqjy").html("请输入开票日期");
        }
        acb(fplx);
    });

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
        var fpdm = $("#fpdm").val().trim();
        //var fphm = $("#fphm").val().trim();
        if (fpdm == "" ) { //&& fphm == "") {
            jAlert('请先输入发票代码!', '提示');
        } else {
            getYzmXx();
        }
    });

    $("#myyzm").click(function() {
            getYzmXx();
    });




    $('#reset').click(function() {
        arw();
        //location.reload();
    });

    function loadScript(url, callback) {
        var script = document.createElement("script");
        script.type = "text/javascript";
        if (typeof(callback) != "undefined") {
            if (script.readyState) {
                script.onreadystatechange = function() {
                    if (script.readyState == "loaded" || script.readyState == "complete") {
                        script.onreadystatechange = null;
                        callback();
                    }
                };
            } else {
                script.onload = function() {
                    callback();
                };
            }
        }
        script.src = url;
        document.body.appendChild(script);
    }

    function bb() {
        //alert(rule);
    }

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
        var fpdm = $("#fpdm").val().trim();
        var swjginfo = getSwjg(fpdm, 0);
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
    function drawline() {/*
     var lineWidthMin = 1;
     var lineWidthMax = 70;
     var lineHeightMin = 1;
     var lineHeightMax = 2;
     var imgwidth = 73;
     var imgheight = 28;
     var lineColors = ["#888888", "#FF7744", "#888800", "#008888"];
     var linenum = randint(5, 10);
     for (i = 0; i < lastlinenum; i++) {
     $("#i" + i).remove();
     }
     lastlinenum = linenum;
     for (i = 0; i < linenum; i++) {
     var btn = $('<div></div>');
     btn.attr("id", "i" + i);
     btn.css("position", "absolute");
     btn.css("opacity", randint(3, 8) / 10);
     btn.css("width", randint(lineWidthMin, lineWidthMax) + "px");
     btn.css("height", randint(lineHeightMin, lineHeightMax) + "px");
     btn.css("left", randint(0, (imgwidth / 2 - 10)) + "px");
     btn.css("top", randint(0, (imgheight / 2 - 10)) + "px");
     btn.css("transform", "rotate(" + randint( - 90, 90) + "deg)");
     btn.css("-ms-transform", "rotate(" + randint( - 90, 90) + "deg)");
     btn.css("-moz-transform", "rotate(" + randint( - 90, 90) + "deg)");
     btn.css("-webkit-transform", "rotate(" + randint( - 90, 90) + "deg)");
     btn.css("-o-transform", "rotate(" + randint( - 90, 90) + "deg)");
     btn.css("z-index", "1");
     btn.css("background", lineColors[randint(0, lineColors.length - 1)]);
     $("#imgarea").append(btn);
     }*/
    }

    function showTime() {
        /*
         delayTime -= 1;
         if (delayTime == 0) {
         if (delayFlag == "" && delayMessage != "") {
         jAlert(delayMessage, "提示");
         delayTime = 6;
         }
         delayFlag = "";
         } else {
         setTimeout("showTime()", 1000);
         }*/
    }


</SCRIPT>
</BODY></HTML>
