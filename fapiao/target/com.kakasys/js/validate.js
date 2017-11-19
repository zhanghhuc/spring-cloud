/**
 * Created by zhang on 2017/10/10.
 */
var flag = "";

var adm=function(a){
    var b=/^1|0\d{11}$|^\d{6}[1-9]\d{2}0$/;
    var c=/^0{8}[1-9]?\w[1-9]\d*$/;

    var e=b.test(a);
    var f=c.test(a);

    if(e == true && bc(a)  && alxd(a) != "99") {
        return true;
    }else{
        return false;
    }
};

var bc=function(a){
    var b;
    var d=new Date();
    var e=d.getFullYear();
    var f=e.toString();
    var g=f.substring(2);
    if(a.length==12){
        b=a.substring(5,7);
    }else{
        b=a.substring(4,6);
    }
    //console.log(b + " " + g);
    if(b<=00||b>g){
        return false;
    }
    return true;
};

var ahm=function(a){
    var b=/^\d{8}$/;
    var c=/^0{8}$/;
    var d=/^0{11}-?[1-9]*\w\d*$/;
    var e=b.test(a);
    var f=c.test(a);
    if(e==true&&f==false){
        return true;
    }else{
        return false;
    }
};

var acq=function(a){
    var b=/^\d{8}$/;
    var c=/^0{8}$/;
    var d=/^0{11}-?[1-9]*\w\d*$/;
    var e=b.test(a);
    if(e==true){
        var g=new Date();
        var h=g.getFullYear();
        var i=g.getMonth()+1;
        var j=g.getDate();
        var k=a.substring(0,4);
        var l=parseInt(a.substring(4,6), 10);
        var m=parseInt(a.substring(6), 10);
        var n=ca(0);
        var t=ca(1);
        if((h!=k&&h-1!=k)||l == 0 || l>12|| m == 0 || m>31 || a>n||!cb(k,l,m)||(h==k && i== l && j==m)){
            return false;
        }
        if(h-1==k&&a<=t){
            return false;
        }
        return true;
    }else{
        return false;
    }
};


function ca(i){
    var a = new Date();
    var b = 0;
    var c = 0;
    var d = 0;
    var e = "";
    b= a.getFullYear()-i;
    c= a.getMonth()+1;
    d = a.getDate();
    e += b;
    if (c >= 10 ){
        e += c;
    }else{
        e += "0" + c;
    }
    if (d >= 10 ){
        e += d ;
    }else{
        e += "0" + d ;
    }
    return e;
}
/*
 function cb(a,b,c){
 if(b==2){
 if(c>29){
 return false;
 }
 if (((a % 4)==0) && ((a % 100)!=0) || ((a % 400)==0)) {
 if(c>28){
 return false;
 }
 }
 }else if((b==4||b==6||b==9||b==11)&&c>30){
 return false;
 }
 return true;
 }
 */
function cb(a,b,c){
    if(c>31){
        return false;
    }else if(c<1){
        return false;
    }else{
        if(b==2){
            if(c>29){//2月不会超过29
                return false;
            }
            if (((a % 4)==0) && ((a % 100)!=0) || ((a % 400)==0)) {//闰年1--29
                return true;
            }else{//平年1--28
                if(c>28){
                    return false;
                }
            }
        }else if((b==4||b==6||b==9||b==11)&&c>30){
            return false;
        }
    }
    return true;
}
var alxd=function(a){
    var b;
    var c="99";

    if(a.length==12){
        b=a.substring(7,8);
        for(var i=0;i<code.length;i++){
            if(a==code[i]){
                c="10";
                break;
            }
        }
        if (c == "99") {  //增加判断，判断是否为新版电子票
            if (a.charAt(0) == '0' && a.substring(10,12) == '11') {
                c="10";
            }
            if (a.charAt(0) == '0' && (a.substring(10,12) == '06' || a.substring(10,12) == '07')) {  //判断是否为卷式发票  第1位为0且第11-12位为06或07
                c="11";
            }
        }
        if(c=="99"){ //如果还是99，且第8位是2，则是机动车发票
            if (b==2 && a.charAt(0) != '0') {
                c="03";
            }
        }

    }else if(a.length==10){
        b=a.substring(7,8);
        if(b==1||b==5){
            c="01";
        }else if(b==6||b==3){
            c="04";
        }else if(b==7||b==2){
            c="02";
        }
    }
    return c;
};

var aje=function(a,b){
    var c=alxd(a);
    if(c=="01"||c=="02"||c=="03"){
        return ea(b);
    }else{
        return eb(b);
    }
};

var ea=function(a){
    var b=/(^-?\d{1,11}$)|(^-?\d{1,11}\.\d{1,2}$)/;
    var c=b.test(a);
    return c;
};

var eb=function(a){
    var b=/^-?(\d+$)|(\d+\.\d{1,2})$/;
    var c=b.test(a);
    return c;
};

var ajy=function(a){
    var b=/^\d{6}$/;
    var e=b.test(a);
    return e;
};

function avym(yzm){
    var code;
    for(var i=0;i<yzm.length;i++){
        code=yzm.charCodeAt(i);
        if((code>65248)||(code==12288)){
            //console.log(code);
            return false;
        }
    }
    return true;
}

function aept(fpdm,fphm,kprq,kjje,yzm){

    if(fpdm==""||fphm==""||kprq==""||kjje==""){ //||yzm==""){
        return false;
    } else {
        if (yzm == "" || yzm == "请输入验证码") {
            //console.log("yzmm null ");
            return false;
        } else {
            //console.log("yzmm : " + yzm);
            return true;
        }
    }
    //return true;
}

function arw(){

    $('#fpdm').val("");
    $('#fphm').val("");
    $('#kprq').val("YYYYMMDD");
    $('#kjje').val("");
    $("#yzm_img").attr("src", "images/code.png");
    $("#yzm_unuse_img").attr("src", "images/code.png");
    $("#context").text("开具金额(不含税)：");
    $("#fpdmjy").text("请输入发票代码");
    $("#fpdmjy").removeClass().addClass("tip_common");
    $("#fphmjy").text("请输入发票号码");
    $("#fphmjy").removeClass().addClass("tip_common");
    $("#kprqjy").text("请输入开票日期");
    $("#kprqjy").removeClass().addClass("tip_common");
    $("#kjjejy").text("请输入开具金额");
    $("#kjjejy").removeClass().addClass("tip_common");
    $('#yzm').val("请输入验证码");
    $('#yzm').css('color','#999999');
    $('#kprq').css('color','#999999');
    $("#yzminfo").text("");
    xsje=1;
    yzmSj="";
    show_yzm = "";
    $("#fpdm").focus();
    //removeGrzx();
}
function avai(fplx){
    var fpdm=$("#fpdm").val().trim();
    var fphm=$("#fphm").val().trim();
    var kprq=$("#kprq").val().trim();
    var kjje=$("#kjje").val().trim();
    var yzm=$("#yzm").val().trim();
    var jqbm="";
    var fpskm="";
    var flag=1;

    if(kprq=="YYMMDD"){
        kprq="";
    }
    /*if(yzm=="请输入验证码"){
     yzm="";
     }*/

    if(!aept(fpdm,fphm,kprq,kjje,yzm)){
        //console.log('avai falseeee');
        return false;
    }

    if(fplx=="99"){
        return false;
    }

    if(fplx=="01"||fplx=="02"||fplx=="03"){
        flag=0;
    }

    var c=/^[0-9]*$/;
    var f=c.test(fpdm);
    if(f==false){
        return false;
    }

    if(adm(fpdm) && ahm(fphm) && acq(kprq) && ((flag == 0 && aje(fpdm, kjje))||(flag == 1 && ajy(kjje))) && avym(yzm)) {
        return true;
    }


    //console.log('avai false');
    return false;

}
//lirongchun 增加 aur() 是判断用户是否登录，如果未登录则返回false，前台转向至登录页面
function aur() {
    var fpdm=$("#fpdm").val().trim();
    var fphm=$("#fphm").val().trim();
    var kprq=$("#kprq").val().trim();
    var kjje=$("#kjje").val().trim();
    var yzm=$("#yzm").val().trim();
    if(fpdm==""||fphm==""||kprq==""||kjje=="" ||yzm=="" || yzm=="请输入验证码"){
        jAlert('请先输入各项信息！', '提示');
        return false;
    } else {
        return true;
    }
    /*
     var username = getCookie('username');
     if (username == "") { //未登录
     return false;
     } else {
     */
    return true;
    //}
}

function acb(fplx){

    if(avai(fplx)){
        $('#uncheckfp').hide();
        $('#checkfp').show();
    }else{
        $('#checkfp').hide();
        $('#uncheckfp').show();
    }
}


function getSwjg(fpdm, ckflag){
    var citys=[{'code':'1100','sfmc':'北京','Ip':'https://zjfpcyweb.bjsat.gov.cn:443','address':'https://zjfpcyweb.bjsat.gov.cn:443'},
        {'code':'1200','sfmc':'天津','Ip':'https://fpcy.tjsat.gov.cn:443','address':'https://fpcy.tjsat.gov.cn:443'},
        {'code':'1300','sfmc':'河北','Ip':'https://fpcy.he-n-tax.gov.cn:82','address':'https://fpcy.he-n-tax.gov.cn:82'},
        {'code':'1400','sfmc':'山西','Ip':'https://fpcy.tax.sx.cn:443','address':'https://fpcy.tax.sx.cn:443'},
        {'code':'1500','sfmc':'内蒙古','Ip':'https://fpcy.nm-n-tax.gov.cn:443','address':'https://fpcy.nm-n-tax.gov.cn:443'},
        {'code':'2100','sfmc':'辽宁','Ip':'https://fpcy.tax.ln.cn:443','address':'https://fpcy.tax.ln.cn:443'},
        {'code':'2102','sfmc':'大连','Ip':'https://fpcy.dlntax.gov.cn:443','address':'https://fpcy.dlntax.gov.cn:443'},
        {'code':'2200','sfmc':'吉林','Ip':'https://fpcy.jl-n-tax.gov.cn:4432','address':'https://fpcy.jl-n-tax.gov.cn:4432'},
        {'code':'2300','sfmc':'黑龙江','Ip':'https://fpcy.hl-n-tax.gov.cn:443','address':'https://fpcy.hl-n-tax.gov.cn:443'},
        {'code':'3100','sfmc':'上海','Ip':'https://fpcyweb.tax.sh.gov.cn:1001','address':'https://fpcyweb.tax.sh.gov.cn:1001'},
        {'code':'3200','sfmc':'江苏','Ip':'https://fpdk.jsgs.gov.cn:80','address':'https://fpdk.jsgs.gov.cn:80'},
        {'code':'3300','sfmc':'浙江','Ip':'https://fpcyweb.zjtax.gov.cn:443','address':'https://fpcyweb.zjtax.gov.cn:443'},
        {'code':'3302','sfmc':'宁波','Ip':'https://fpcy.nb-n-tax.gov.cn:443','address':'https://fpcy.nb-n-tax.gov.cn:443'},
        {'code':'3400','sfmc':'安徽','Ip':'https://fpcy.ah-n-tax.gov.cn:443','address':'https://fpcy.ah-n-tax.gov.cn:443'},
        {'code':'3500','sfmc':'福建','Ip':'https://fpcyweb.fj-n-tax.gov.cn:443','address':'https://fpcyweb.fj-n-tax.gov.cn:443'},
        {'code':'3502','sfmc':'厦门','Ip':'https://fpcy.xm-n-tax.gov.cn','address':'https://fpcy.xm-n-tax.gov.cn'},
        {'code':'3600','sfmc':'江西','Ip':'https://fpcy.jxgs.gov.cn:82','address':'https://fpcy.jxgs.gov.cn:82'},
        {'code':'3700','sfmc':'山东','Ip':'https://fpcy.sd-n-tax.gov.cn:443','address':'https://fpcy.sd-n-tax.gov.cn:443'},
        {'code':'3702','sfmc':'青岛','Ip':'https://fpcy.qd-n-tax.gov.cn:443','address':'https://fpcy.qd-n-tax.gov.cn:443'},
        {'code':'4100','sfmc':'河南','Ip':'https://fpcy.ha-n-tax.gov.cn','address':'https://fpcy.ha-n-tax.gov.cn'},
        {'code':'4200','sfmc':'湖北','Ip':'https://fpcy.hb-n-tax.gov.cn:443','address':'https://fpcy.hb-n-tax.gov.cn:443'},
        {'code':'4300','sfmc':'湖南','Ip':'https://fpcy.hntax.gov.cn:8083','address':'https://fpcy.hntax.gov.cn:8083'},
        {'code':'4400','sfmc':'广东','Ip':'https://fpcy.gd-n-tax.gov.cn:443','address':'https://fpcy.gd-n-tax.gov.cn:443'},
        {'code':'4403','sfmc':'深圳','Ip':'https://fpcy.szgs.gov.cn:443','address':'https://fpcy.szgs.gov.cn:443'},
        {'code':'4500','sfmc':'广西','Ip':'https://fpcy.gxgs.gov.cn:8200','address':'https://fpcy.gxgs.gov.cn:8200'},
        {'code':'4600','sfmc':'海南','Ip':'https://fpcy.hitax.gov.cn:443','address':'https://fpcy.hitax.gov.cn:443'},
        {'code':'5000','sfmc':'重庆','Ip':'https://fpcy.cqsw.gov.cn:80','address':'https://fpcy.cqsw.gov.cn:80'},
        {'code':'5100','sfmc':'四川','Ip':'https://fpcy.sc-n-tax.gov.cn:443','address':'https://fpcy.sc-n-tax.gov.cn:443'},
        {'code':'5200','sfmc':'贵州','Ip':'https://fpcy.gz-n-tax.gov.cn:80','address':'https://fpcy.gz-n-tax.gov.cn:80'},
        {'code':'5300','sfmc':'云南','Ip':'https://fpcy.yngs.gov.cn:443','address':'https://fpcy.yngs.gov.cn:443'},
        {'code':'5400','sfmc':'西藏','Ip':'https://fpcy.xztax.gov.cn:81','address':'https://fpcy.xztax.gov.cn:81'},
        {'code':'6100','sfmc':'陕西','Ip':'https://fpcyweb.sn-n-tax.gov.cn:443','address':'https://fpcyweb.sn-n-tax.gov.cn:443'},
        {'code':'6200','sfmc':'甘肃','Ip':'https://fpcy.gs-n-tax.gov.cn:443','address':'https://fpcy.gs-n-tax.gov.cn:443'},
        {'code':'6300','sfmc':'青海','Ip':'https://fpcy.qh-n-tax.gov.cn:443','address':'https://fpcy.qh-n-tax.gov.cn:443'},
        {'code':'6400','sfmc':'宁夏','Ip':'https://fpcy.nxgs.gov.cn:443','address':'https://fpcy.nxgs.gov.cn:443'},
        {'code':'6500','sfmc':'新疆','Ip':'https://fpcy.xj-n-tax.gov.cn:443','address':'https://fpcy.xj-n-tax.gov.cn:443'}];
    var dqdm=null;
    var swjginfo=new Array();

    if(fpdm.length==12){
        dqdm=fpdm.substring(1,5);
    }else{
        dqdm=fpdm.substring(0,4);
    }
    if(dqdm!="2102"&&dqdm!="3302"&&dqdm!="3502"&&dqdm!="3702"&&dqdm!="4403"){
        dqdm=dqdm.substring(0,2)+"00";
    }
    for(var i=0;i<citys.length;i++){
        if(dqdm==citys[i].code){
            swjginfo[0]=citys[i].sfmc;
            if (flag == 'debug') {   //如果是开发调试模式或测试模式
                //swjginfo[1] = "http://172.30.11.88:7010/WebQuery";  //这里是省局服务器的外网地址，开发/测试时填写相应值
                swjginfo[1] = "http://127.0.0.1:7001/WebQuery";
            } else {
                swjginfo[1]=citys[i].Ip+"/WebQuery";
            }
            break;
        }
    }
    //只有北京，上海，深圳的发票可以查询  如果全国开放，此处加注释
    /*
     if ((fpdm.length == 10 && fpdm.substring(0,1) != '0' && $.inArray(fpdm, code10) == -1) || fpdm.length == 12) {
     if (fpdm.substring(0,1) == '1' && (fpdm.substring(1,5) == '1100' || fpdm.substring(1,5) == '3100' || fpdm.substring(1,5) == '4403')) {
     } else {
     if (dqdm != "1100" && dqdm != "3100" && dqdm != "4403") {
     swjginfo = new Array();
     jAlert("该省尚未开通发票查验功能！","提示");
     }
     }
     }*/

    return swjginfo;
}
