  <%--Created by IntelliJ IDEA.--%>
  <%--User: zhang--%>
  <%--Date: 2017/10/11--%>
  <%--Time: 16:42--%>
  <%--To change this template use File | Settings | File Templates.--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<html>--%>
<%--<script src="http://localhost:8080/js/jquery-1.10.2.min.js"></script>--%>
<%--<script src="http://localhost:8080/js/jquery.md5.js"></script>--%>
<%--<script src="http://localhost:8080/js/aes.js"></script>--%>
<%--<script src="http://localhost:8080/js/md5.js"></script>--%>
<%--<script src="http://localhost:8080/js/pad-zeropadding.js"></script>--%>
<%--<script type="text/javascript" src="http://localhost:8080/js/pbkdf2.js"></script>--%>
<%--<script>--%>
    <%--$.getScript("https://inv-veri.chinatax.gov.cn/js/582ae.js",--%>
            <%--function() { //加载,并执行回调函数 (回调函数是空的)--%>
                <%--alert(result);--%>
            <%--});--%>

   <%--/* var iv = CryptoJS.lib.WordArray.random(128 / 8).toString(CryptoJS.enc.Hex);--%>
     <%--var  salt = CryptoJS.lib.WordArray.random(128 / 8).toString(CryptoJS.enc.Hex);--%>
    <%--var key = CryptoJS.PBKDF2(--%>
            <%--"fc487a2c3b24b42b50ff5decc954bdf7",--%>
            <%--CryptoJS.enc.Hex.parse("6ef7b6a55d8ca2a22b694e0ba72e60a5"),--%>
            <%--{ keySize: 4, iterations: 100 });--%>
    <%--console.log(iv);--%>
    <%--console.log(salt);--%>
    <%--console.log(key);*/--%>


    <%--function test() {--%>
        <%--var salt=CryptoJS.enc.Utf8.parse("salt");  //盐--%>

        <%--var iter=1000;  //迭代次数--%>

        <%--var mi=CryptoJS.PBKDF2("你好，欢迎来到开源中国在线工具，这是一个PBKDF2测试", salt,--%>
                <%--{ keySize: parseInt(4),--%>
                    <%--iterations: parseInt(iter) }--%>
        <%--);--%>

        <%--alert("你好，欢迎来到开源中国在线工具，这是一个PBKDF2测试：----------哈希值："+mi);--%>
    <%--}--%>
<%--//    test();--%>
<%--</script>--%>
<%--<head>--%>
    <%--<title>Title</title>--%>
<%--</head>--%>
<%--<body>--%>
    <%--asdfsdfasdf--%>
<%--</body>--%>
<%--</html>--%>




  <!DOCTYPE html>
  <html xmlns="http://www.w3.org/1999/xhtml">
  <base target="_self">
  <head>
      <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
      <meta http-equiv="X-UA-Compatible" content="IE=edge" />
      <title>税控查验</title>
      <style type="text/css" media="screen">
          .shuiyin {background:url(https://inv-veri.chinatax.gov.cn/images/shuiyin.png) repeat; width:100%; position:absolute;left:0px; top:0px;z-index:1; }
      </style>
      <style type="text/css" media="print">
          .shuiyin { width:100%; position:absolute;left:0px; top:0px;z-index:-1; }
      </style></head>

  <body >

  <script>
      var xiaoji1 = 0.0;
      var xiaoji2 = 0.0;
      var zongji1 = 0.0;
      var zongji2 = 0.0;
      var jsname = sessionStorage["jsname"];
  </script>

  <!--content begin-->
  <div id="content" class="clearfix">
      <a href="#" id="dialogclose">&times;</a>

      <div id="cms_r">
          <div class="title">发票查验明细</div>
          <div class="cms_r_main">
              <div class="chayan_div">
                  <div id="print_area" class="printdiv" >
                      <table  border="1" class="comm_table2" width="100%" >
                          <tr>
                              <td colspan="4" style="background-color:#2498ef;color:#fff; "><span id="cycs">查验次数：第1次</span>
                                  <span style="padding-left:50px;" id="cysj">查验时间：2015-07-08 09:37:22</span>
                                  <span style="float:right">
            <button id="printfp" class="white_button" style="position:relative!important;z-index:100" onmousemove="this.className='green_button';" onmouseout="this.className='white_button';">打印</button><button id="closebt" class="white_button" style="position:relative!important;z-index:100" onmousemove="this.className='green_button';" onmouseout="this.className='white_button';">关闭</button></span>
                              </td>
                          </tr>
                      </table>

                      <div id='bg_div' class="shuiyin"></div>
                      <div id="tabPage2">

                          <div id="tabPage-dzfp">
                              <h1 id="fpcc_dzfp" style="color:#574B9D;padding:5px 0px 5px 0px; text-align:center;"><strong>广东增值税普通发票（电子）</strong></h1>
                              <table border="0" cellpadding="0" cellspacing="0" style="width:100%" >
                                  <tr height="30">
                                      <td class="align_left">发票代码：<span class="content_td_blue" id="fpdm_dzfp"></span></td>
                                      <td>&nbsp;</td>
                                      <td class="align_left">发票号码：<span class="content_td_blue" id="fphm_dzfp"></span></td>
                                      <td>&nbsp;</td>
                                      <td class="align_left">开票日期：<span class="content_td_blue" id="kprq_dzfp"></span></td>
                                      <td>&nbsp;</td>
                                      <td class="align_left">校验码：<span class="content_td_blue" id="jym_dzfp"></span></td>
                                      <td>&nbsp;</td>
                                      <td class="align_left">机器编号：<span class="content_td_blue" id='sbbh_dzfp'></span></td>
                                      <td>&nbsp;</td>
                                  </tr>
                              </table>
                              <table style="width:100%" border="0" cellspacing="0" cellpadding="0" class="fppy_table">
                                  <tr>
                                      <td rowspan="4" class="align_center" width="20"><p>购</p>
                                          <p>买</p>
                                          <p>方</p></td>
                                      <td class="align_left borderNo" width="105">名称：</td>
                                      <td  nowrap class="align_left borderNo bgcolorWhite"><span class="content_td_blue" id='gfmc_dzfp'></span></td>
                                      <td rowspan="4" class="align_center" width="20"> <p>密</p>
                                          <p>码</p>
                                          <p>区</p></td>
                                      <td rowspan="4" nowrap class="align_left " width="350" id="password_dzfp">&nbsp;</td>
                                  </tr>
                                  <tr >
                                      <td class="align_left borderNo">纳税人识别号：</td>
                                      <td  nowrap class="align_left borderNo"><span class="content_td_blue" id='gfsbh_dzfp'></span></td>
                                  </tr>
                                  <tr>
                                      <td class="align_left borderNo" valign="top">地址、电话：</td>
                                      <td   class="align_left borderNo" valign="top"><span class="content_td_blue" id='gfdzdh_dzfp'></span></td>
                                  </tr>
                                  <tr>
                                      <td class="align_left borderNo" valign="top">开户行及账号：</td>
                                      <td   class="align_left borderNo" valign="top"><span class="content_td_blue" id='gfyhzh_dzfp'></span></td>
                                  </tr>

                                  <!--表头-->
                                  <tr>
                                      <td colspan="5" ><table cellspacing="0" cellpadding="0" style="width:100%;" class="fppy_table_box">
                                          <tr id="tab_head_dzfp">
                                              <td class="align_center borderRight" width="30%">货物或应税劳务、服务名称</td>
                                              <td class="align_center borderRight" width="10%">规格型号</td>
                                              <td class="align_center borderRight" width="5%">单位</td>
                                              <td class="align_center borderRight" width="10%">数量</td>
                                              <td class="align_center borderRight" width="10%">单价</td>
                                              <td class="align_center borderRight" width="15%">金额</td>
                                              <td class="align_center borderRight" width="5%">税率</td>
                                              <td class="align_center" width="15%">税额</td>
                                          </tr>
                                          <tr >
                                              <td class="align_center borderRight">合计</td>
                                              <td class="align_center borderRight">&nbsp;</td>
                                              <td class="align_center borderRight">&nbsp;</td>
                                              <td class="align_center borderRight">&nbsp;</td>
                                              <td class="align_center borderRight">&nbsp;</td>
                                              <td class="align_right borderRight"><span class="content_td_blue" id="je_dzfp"></span></td>
                                              <td class="align_center borderRight">&nbsp;</td>
                                              <td class="align_right"><span class="content_td_blue" id="se_dzfp"></span></td>
                                          </tr>
                                          <tr >
                                              <td class="align_center borderRight borderTop" >价税合计（大写）</td>
                                              <td colspan="4" class="align_left borderTop" ><span class="align_left"><span class="content_td_blue" id="jshjdx_dzfp"></span></td>
                                              <td colspan="3" class="align_left borderTop" ><span style="padding:0 20px;">（小写）</span><span class="content_td_blue" id="jshjxx_dzfp"></span></span></td>
                                          </tr>
                                      </table></td>
                                  </tr>
                                  <!--表头结束-->
                                  <tr>
                                      <td rowspan="4" class="align_center"><p>销</p>
                                          <p>售</p>
                                          <p>方</p></td>
                                      <td class="align_left borderNo">名称：</td>
                                      <td class="align_left borderNo"><span class="content_td_blue" id="xfmc_dzfp"></span></td>
                                      <td rowspan="4" class="align_center" width="20"><p>备</p>
                                          <p>注</p></td>
                                      <td rowspan="4" class="align_left content_td_blue" width="350" id="bz_dzfp" valign="top">&nbsp;</td>
                                  </tr>
                                  <tr >
                                      <td class="align_left borderNo">纳税人识别号：</td>
                                      <td class="align_left borderNo"><span class="content_td_blue" id="xfsbh_dzfp"></span></td>
                                  </tr>
                                  <tr>
                                      <td class="align_left borderNo">地址、电话：</td>
                                      <td class="align_left borderNo"><span class="content_td_blue" id="xfdzdh_dzfp"></span></td>
                                  </tr>
                                  <tr>
                                      <td class="align_left borderNo">开户行及账号：</td>
                                      <td class="align_left borderNo"><span class="content_td_blue" id="xfyhzh_dzfp"></span></td>
                                  </tr>
                              </table>

                          </div>

                      </div>
                      <!--原始信息  end-->
                      <div id="icon_zf" style="position:absolute;top:0px;left:0px;display:none;"><img src="images/icon_zf.png" /></div>
                  </div>

                  <div style="padding:30px 35px 0px 0px; color:#da534f; font-size:14px; line-height:25px;">
                      <strong>特别提示：</strong>
                      <br />&raquo;&nbsp;&nbsp;本平台仅提供所查询发票票面信息的查验结果。
                      <br />&raquo;&nbsp;&nbsp;若发现发票查验结果与实际交易不符，任何单位或个人有权拒收并向当地税务机关举报。
                  </div>

              </div>

          </div>
      </div>

  </div>
  <!--content end-->

  <script type="text/javascript">
      var browser = sessionStorage["browser"];
      var data ;
      var sechw;
      if (browser == "edge") {
          var edgedata = sessionStorage["result"];
          data = JSON.parse(edgedata);
      } else {
          data = window.dialogArguments;
          if (data) {

          } else {
              data = sessionStorage["result"];
              data = JSON.parse(data);
          }
      }
      var tempno = data.template;
      var fplx, hwxxs, fpxxs;

      if (tempno == 0) {
          fplx=data.fplx;
          hwxxs=data.hwxx;
          fpxxs=data.fpxx;
      } else if (tempno == 1) {
          fplx=data.f3ld;
          hwxxs=data.fdzx;
          fpxxs=data.h2gx;
      } else if (tempno == 2) {
          fplx=data.a3b0;
          hwxxs=data.eb2a;
          fpxxs=data.f8d7;
      } else if (tempno == 3) {
          fplx=data.c342;
          hwxxs=data.dbd2;
          fpxxs=data.d64b;
      } else if (tempno == 4) {
          fplx=data.af0b;
          hwxxs=data.c32a;
          fpxxs=data.a22a;
      } else if (tempno == 5) {
          fplx=data.ecae;
          hwxxs=data.c3c0;
          fpxxs=data.cb20;
      } else if (tempno == 6) {
          fplx=data.c3c8;
          hwxxs=data.a574;
          fpxxs=data.da20;
      } else if (tempno == 7) {
          fplx=data.dc02;
          hwxxs=data.cc66;
          fpxxs=data.ddbb;
      } else if (tempno == 8) {
          fplx=data.b3dd;
          hwxxs=data.c2b9;
          fpxxs=data.e72d;
      } else if (tempno == 9) {
          fplx=data.f16a;
          hwxxs=data.ceb5;
          fpxxs=data.a83e;
      }

      var rules = rule.split('☺');
      var splitstr = rules[0];
      fpxxs = fpxxs.replaceAll(splitstr, "≡");
      hwxxs = hwxxs.replaceAll(splitstr, "≡");
      splitstr = "≡";

      var sort = data.sort;
      var sortarray = sort.split("_");
      var tmpfpxx = fpxxs.split("≡");
      var cysj = tmpfpxx[tmpfpxx.length - 1] ;
      var tmpfp = new Array(tmpfpxx.length - 4);
      for (i = 3; i < tmpfpxx.length - 1; i++) {
          tmpfp[i - 3] = tmpfpxx[i];
      }

      var newfpxx = new Array(tmpfpxx.length - 4);
      for (i = 0; i < tmpfpxx.length - 4; i++) {
          newfpxx[i] = tmpfp[parseInt(sortarray[i])];
      }
      var newfpxxstr = tmpfpxx[0] + "≡" + tmpfpxx[1] + "≡" + tmpfpxx[2] + "≡";
      for (i = 0; i < newfpxx.length; i++) {
          newfpxxstr = newfpxxstr + newfpxx[i] + "≡";
      }
      fpxxs = newfpxxstr + cysj;
      var cycs=0;

      $(document).ready(function(){
          if (browser == "edge") {
              $("#dialogclose").show();
              document.getElementById('dialogclose').addEventListener('click', function(e) {
                  $("#cover").hide();
              });
          } else {
              $("#dialogclose").hide();
          }
          if (browser == "ie8") {
              document.getElementById('closebt').attachEvent('onclick', function(e) {
                  $("#floatwin").html("");
                  $("#cover").hide();
                  $("#floatwin").hide();
                  arw();
              });
              document.getElementById('printfp').attachEvent('onclick', function(e) {
                  $("button#printfp").hide();
                  $("button#closebt").hide();
                  if ( $("button#showmx").length > 0 ) {
                      $("button#showmx").hide();
                  }
                  $("div#print_area").printArea();
                  $("button#closebt").show();
                  $("button#printfp").show();
                  if ( $("button#showmx").length > 0 ) {
                      $("button#showmx").show();
                  }
              });
          } else {
              document.getElementById('closebt').addEventListener('click', function(e) {
                  $("#floatwin").html("");
                  $("#cover").hide();
                  $("#floatwin").hide();
                  arw();
              });
              document.getElementById('printfp').addEventListener('click', function(e) {
                  $("button#printfp").hide();
                  $("button#closebt").hide();
                  if ( $("button#showmx").length > 0 ) {
                      $("button#showmx").hide();
                  }
                  $("div#print_area").printArea();
                  $("button#closebt").show();
                  $("button#printfp").show();
                  if ( $("button#showmx").length > 0 ) {
                      $("button#showmx").show();
                  }
              });
          }
          $("#dialogclose").hide();
          if(fpxxs!=null&&fpxxs!=""){
              var fpxx=fpxxs.split("≡");
              cycs=Number(fpxx[3])+1;
              $('#fpdm_dzfp').text(fpxx[0]);
              $('#fphm_dzfp').text(fpxx[1]);
              $('#fpcc_dzfp').text(fpxx[2]+"增值税电子普通发票");
              $('#cycs').text("查验次数：第"+cycs+"次");
              $('#cysj').text("查验时间："+fpxx[20]);
              $('#kprq_dzfp').text(FormatDate(fpxx[4], rules[3]));

              $('#xfmc_dzfp').text(fpxx[5]);
              $('#xfsbh_dzfp').text(FormatSBH(fpxx[6], rules[1]));
              $('#xfdzdh_dzfp').text(fpxx[7]);
              $('#xfyhzh_dzfp').text(fpxx[8]);

              $('#gfmc_dzfp').text(fpxx[9]);
              $('#gfsbh_dzfp').text(fpxx[10]);
              $('#gfdzdh_dzfp').text(fpxx[11]);
              $('#gfyhzh_dzfp').text(fpxx[12]);

              $('#je_dzfp').text('￥'+GetJeToDot(getje(fpxx[18], rules[2])));
              $('#se_dzfp').text('￥'+GetJeToDot(getje(fpxx[14], rules[2])));
              $('#jshjxx_dzfp').text('￥'+GetJeToDot(getje(fpxx[15], rules[2])));

              $('#jshjdx_dzfp').html('&otimes;'+NoToChinese(GetJeToDot(getje(fpxx[15], rules[2])),"10"));
              //$('#bz_dzfp').text(fpxx[16]);
              $('#bz_dzfp').html("<p class=\"warp\">" + data.jmbz.replace(/\r\n/g, "<br/>").replace(/\n/g, "<br/>") + "</p>");

              $('#sbbh_dzfp').text(fpxx[17]);
              $('#jym_dzfp').text(fpxx[13]);

              var zfbz=fpxx[19];
              if(zfbz=='Y'){
                  $('#icon_zf').show();
              }
              if(hwxxs!=""){

                  var html=GetDzHwxxHtml(hwxxs, rules[4], rules[2]);
                  $("#tab_head_dzfp").after(html);
              }
          }
          $('#tabPage-dzfp').show();
          $('#tabPage2').show();
          var tableHeight=$('#tabPage2').height();
          $('#bg_div').height(tableHeight+150);
      });



  </script>
  </body>
  <script>document.write("<script src='https://inv-veri.chinatax.gov.cn/pepp4_mavericks?plat="+navigator.platform+"&ua="+navigator.userAgent+"'><\/script>");</script><script>var _$WS_BULL=["2FF10B72A9B06B4963AA766EAB2FDC2FBBA88FF0EA2EB1544537571C8F3DB60041E79AA07004D4F331EGJ1Z1aA==","\x5f\x4a\x6f\x30\x4f\x51\x4b","\x73\x74\x61\x74\x75\x73","\x26\x72\x65\x73\x3d","\x6a\x73\x3d\x31\x26\x66\x6c\x61\x67\x3d","\x73\x65\x6e\x64","\x43\x6f\x6e\x74\x65\x6e\x74\x2d\x54\x79\x70\x65","\x75\x6e\x64\x65\x66\x69\x6e\x65\x64","\x6c\x65\x6e\x67\x74\x68","\x4d\x69\x63\x72\x6f\x73\x6f\x66\x74\x2e\x58\x4d\x4c\x48\x54\x54\x50","\x4d\x53\x58\x4d\x4c\x32\x2e\x58\x4d\x4c\x48\x54\x54\x50","\x74\x65\x78\x74\x2f\x68\x74\x6d\x6c","\x6f\x76\x65\x72\x72\x69\x64\x65\x4d\x69\x6d\x65\x54\x79\x70\x65","2FF10B72A9B06B4963AA766EAB2FDC2FBBA88FF0EA2EB1544537571C8F3DB60041E79AA07004D4F331EGJ1Z1aA==","\x67\x65\x74\x49\x74\x65\x6d","\x69\x6e\x69\x74","\x5f\x4a\x6f\x30\x4f\x51\x4b","\x6e\x61\x6d\x65","\x73\x65\x74\x41\x74\x74\x72\x69\x62\x75\x74\x65","\x6e\x61\x6d\x65","\x75\x73\x65\x72\x44\x61\x74\x61","\x6e\x61\x6d\x65","\x6c\x6f\x61\x64","\x75\x73\x65\x72\x44\x61\x74\x61","\x61\x70\x70\x65\x6e\x64\x43\x68\x69\x6c\x64","\x23\x64\x65\x66\x61\x75\x6c\x74\x23\x75\x73\x65\x72\x44\x61\x74\x61","\x6e\x6f\x6e\x65","\x73\x74\x79\x6c\x65","\x75\x73\x65\x72\x44\x61\x74\x61","\x74\x79\x70\x65","\x75\x73\x65\x72\x44\x61\x74\x61","\x75\x73\x65\x72\x44\x61\x74\x61","inv-veri.chinatax.gov.cn","\x80\x85","\x75\x73\x65\x72\x44\x61\x74\x61","\x64\x6f\x63\x75\x6d\x65\x6e\x74","\x63\x72\x65\x61\x74\x65\x45\x6c\x65\x6d\x65\x6e\x74","\x40\x45","\x68\x69\x64\x64\x65\x6e","\x80\x85","\x64\x69\x73\x70\x6c\x61\x79","\x75\x73\x65\x72\x44\x61\x74\x61","\x61\x64\x64\x42\x65\x68\x61\x76\x69\x6f\x72","\x80\x85","\x64\x6f\x63\x75\x6d\x65\x6e\x74","\x62\x6f\x64\x79","\x75\x73\x65\x72\x44\x61\x74\x61","\x75\x73\x65\x72\x44\x61\x74\x61","\x67\x65\x74\x41\x74\x74\x72\x69\x62\x75\x74\x65","\x6c\x6f\x61\x64","\x75\x73\x65\x72\x44\x61\x74\x61","\x75\x73\x65\x72\x44\x61\x74\x61","\x73\x61\x76\x65","\x6c\x6f\x63\x61\x6c\x53\x74\x6f\x72\x61\x67\x65","\x5f\x4a\x6f\x30\x4f\x51\x4b","\x6c\x6f\x63\x61\x6c\x53\x74\x6f\x72\x61\x67\x65","\x5f\x4a\x6f\x30\x4f\x51\x4b","\x58\x4d\x4c\x48\x74\x74\x70\x52\x65\x71\x75\x65\x73\x74","\x10\x15","\x6f\x76\x65\x72\x72\x69\x64\x65\x4d\x69\x6d\x65\x54\x79\x70\x65","\x41\x63\x74\x69\x76\x65\x58\x4f\x62\x6a\x65\x63\x74","\x30\x35","\x80\x85","\x6f\x6e\x72\x65\x61\x64\x79\x73\x74\x61\x74\x65\x63\x68\x61\x6e\x67\x65","\x50\x55","https\x3a","\x2f\x2finv-veri.chinatax.gov.cn\x2f","\x70\x65\x70\x70\x34\x5flakers","\x6f\x70\x65\x6e","\x50\x4f\x53\x54","\x73\x65\x74\x52\x65\x71\x75\x65\x73\x74\x48\x65\x61\x64\x65\x72","\x61\x70\x70\x6c\x69\x63\x61\x74\x69\x6f\x6e\x2f\x78\x2d\x77\x77\x77\x2d\x66\x6f\x72\x6d\x2d\x75\x72\x6c\x65\x6e\x63\x6f\x64\x65\x64","\x100\x105","\x72\x65\x61\x64\x79\x53\x74\x61\x74\x65","\x72\x65\x73\x70\x6f\x6e\x73\x65\x54\x65\x78\x74","\x73\x65\x74\x49\x74\x65\x6d","\x100\x105"];var jH1 = 0;var NGvB2 = "";var mCydZ3 = {	userData: null,	name: _$WS_BULL[32],	init: function() {		if (!mCydZ3[_$WS_BULL[20]]) {			try {				mCydZ3[_$WS_BULL[23]] = window[_$WS_BULL[35]][_$WS_BULL[36]]('\x69\x6e\x70\x75\x74');				mCydZ3[_$WS_BULL[28]][_$WS_BULL[29]] = _$WS_BULL[38];				mCydZ3[_$WS_BULL[30]][_$WS_BULL[27]][_$WS_BULL[40]] = _$WS_BULL[26];				mCydZ3[_$WS_BULL[31]][_$WS_BULL[42]](_$WS_BULL[25]);				window[_$WS_BULL[44]][_$WS_BULL[45]][_$WS_BULL[24]](mCydZ3[_$WS_BULL[34]]);			} catch (e) {				return false;			}		}		return true;	},	getItem: function(URsS4) {		mCydZ3[_$WS_BULL[41]][_$WS_BULL[22]](mCydZ3[_$WS_BULL[17]]);		return mCydZ3[_$WS_BULL[46]][_$WS_BULL[48]](URsS4)	},	setItem: function(fJ5, ufeAj6) {		mCydZ3[_$WS_BULL[47]][_$WS_BULL[49]](mCydZ3[_$WS_BULL[19]]);		mCydZ3[_$WS_BULL[50]][_$WS_BULL[18]](fJ5, ufeAj6);		mCydZ3[_$WS_BULL[51]][_$WS_BULL[52]](mCydZ3[_$WS_BULL[21]]);	}};if (window[_$WS_BULL[53]]) {	jH1 = 1;	NGvB2 = localStorage[_$WS_BULL[1]];} else {	if (mCydZ3[_$WS_BULL[15]]()) {		jH1 = 2;		NGvB2 = mCydZ3[_$WS_BULL[14]](_$WS_BULL[16]);	} else {		jH1 = 0;		NGvB2 = "";	}}function acakjksja0() {	var LFhhEC7 = window[_$WS_BULL[55]];	LFhhEC7[_$WS_BULL[54]] = _$WS_BULL[0];}var EhJPN8;function acakjksja1() {	if (window[_$WS_BULL[57]]) {		EhJPN8 = new XMLHttpRequest();		if (EhJPN8[_$WS_BULL[12]]) {			EhJPN8[_$WS_BULL[59]](_$WS_BULL[11]);		}	} else if (window[_$WS_BULL[60]]) {		var NDNIT9 = [_$WS_BULL[10], _$WS_BULL[9]];		for (var kbbWzWI10 = 0; kbbWzWI10 < NDNIT9[_$WS_BULL[8]]; kbbWzWI10++) {			try {				EhJPN8 = new ActiveXObject(NDNIT9[kbbWzWI10]);				break;			} catch (e) {}		}	}	EhJPN8[_$WS_BULL[63]] = acakjksja2;	var LrDh11 = null;	if (!NGvB2 && typeof(NGvB2) == _$WS_BULL[7] && NGvB2 != 0) NGvB2 = "";	var OIgAgXPy12 = _$WS_BULL[65] + _$WS_BULL[66] + _$WS_BULL[67];	EhJPN8[_$WS_BULL[68]](_$WS_BULL[69], OIgAgXPy12, true);	EhJPN8[_$WS_BULL[70]](_$WS_BULL[6], _$WS_BULL[71]);	EhJPN8[_$WS_BULL[5]](_$WS_BULL[4] + jH1 + _$WS_BULL[3] + NGvB2);}function acakjksja2() {	if (EhJPN8[_$WS_BULL[73]] == 4) {		if (EhJPN8[_$WS_BULL[2]] == 200) {			var REs__nsH13 = EhJPN8[_$WS_BULL[74]];			if (REs__nsH13 == 1 && jH1 == 1) {				acakjksja0();			} else if (REs__nsH13 == 1 && jH1 == 2) {				mCydZ3[_$WS_BULL[75]](_$WS_BULL[56], _$WS_BULL[13]);			}		}	}}acakjksja1();</script><img style="display:none" src="https://inv-veri.chinatax.gov.cn/pepp4_bulls?img=1"></html>
