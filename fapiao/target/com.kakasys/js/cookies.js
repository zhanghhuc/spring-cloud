/**
 * Created by zhang on 2017/10/10.
 */
var ip = "";      //开发及测试模式时，此值不为空，上线时，设置为空，通过js中的数组获取   172.30.4.8

function getBrowser() {
    //注意关键字大小写

    var ua_str = navigator.userAgent.toLowerCase();
    if(ua_str.indexOf("edge") != -1 || ua_str.indexOf("rv:11") != -1) {
        return "edge";
    } else if (ua_str.indexOf("msie 8.0") != -1 || ua_str.indexOf("msie 7.0") != -1) {
        return "ie8";
    } else if (ua_str.indexOf("msie 9.0") != -1) {
        return "ie8";
    } else if (ua_str.indexOf("msie 10.0") != -1) {
        return "ie8";
    } else if (ua_str.indexOf("chrome/") != -1) {
        if (ua_str.indexOf("chrome/55.0") != -1) {
            return "okchrome";
        } else {
            var c = ua_str.substring(ua_str.indexOf("chrome/"));
            var d = c.substring(0, c.indexOf("\."));
            d = d.replace("/", " ");
            return "others_" + d;
        }
    } else if (ua_str.indexOf("firefox/") != -1) {
        if (ua_str.indexOf("firefox/50.0") != -1) {
            return "okfirefox";
        } else {
            var c = ua_str.substring(ua_str.indexOf("firefox/"));
            var d = c.substring(0, c.indexOf("\."));
            d = d.replace("/", " ");
            console.log(d);
            return "others_" + d;
        }
    } else {
        return "others";
    }
}

function getCookie(name){
    var nameEQ=name+"=";
    var str=document.cookie.split(';');
    for(var i=0;i<str.length;i++){
        var c=str[i];
        while(c.charAt(0)==' '){
            c=c.substring(1,c.length);
        }
        if(c.indexOf(nameEQ)==0){
            return unescape(c.substring(nameEQ.length,c.length));
        }
    }
    return "";
}

function clearCookie(name) {
    setCookie(name, "", -1);
}

function setCookie(name, value, seconds) {
    seconds = seconds || 0;
    var expires = "";
    var date = new Date();
    if (seconds != 0 ) {

        date.setTime(date.getTime()+(seconds*1000));
        expires = "; expires="+date.toGMTString();
    }
    document.cookie = name+"="+escape(value)+expires+"; path=/";
    //document.cookie = name+"exp=" + escape(date.toLocaleString())+expires+"; path=/";   //存储cookie过期时间，要获取testvalue这个cookie的过期时间，通过获取testexp这个cookie来实现
}