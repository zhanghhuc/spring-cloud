/**
 * Created by zhang on 2017/10/11.
 */

var AesUtil = function(keySize, iterationCount) {
    this.keySize = keySize / 32;
    this.iterationCount = iterationCount;
};

/*AesUtil.prototype.generateKey = function(salt, passPhrase) {
 var key = CryptoJS.PBKDF2(
 passPhrase,
 CryptoJS.enc.Hex.parse(salt),
 { keySize: this.keySize, iterations: this.iterationCount });
 return key;
 }*/

function generateKey (salt, passPhrase) {
    var key = CryptoJS.PBKDF2(
        passPhrase,
        CryptoJS.enc.Hex.parse(salt),
        { keySize: 4, iterations: 100 });
    return key;
}

/*AesUtil.prototype.encrypt = function(salt, iv, passPhrase, plainText) {
 var key = this.generateKey(salt, passPhrase);
 var encrypted = CryptoJS.AES.encrypt(
 plainText,
 key,
 { iv: CryptoJS.enc.Hex.parse(iv) });
 return encrypted.ciphertext.toString(CryptoJS.enc.Base64);
 }*/

function encrypt(salt, iv, passPhrase, plainText) {
    var key = this.generateKey(salt, passPhrase);
    var encrypted = CryptoJS.AES.encrypt(
        plainText,
        key,
        { iv: CryptoJS.enc.Hex.parse(iv) });
    return encrypted.ciphertext.toString(CryptoJS.enc.Base64);
}

/*AesUtil.prototype.decrypt = function(salt, iv, passPhrase, cipherText) {
 var key = this.generateKey(salt, passPhrase);
 var cipherParams = CryptoJS.lib.CipherParams.create({
 ciphertext: CryptoJS.enc.Base64.parse(cipherText)
 });
 var decrypted = CryptoJS.AES.decrypt(
 cipherParams,
 key,
 { iv: CryptoJS.enc.Hex.parse(iv) });
 return decrypted.toString(CryptoJS.enc.Utf8);
 }*/
function decrypt(salt, iv, passPhrase, cipherText) {
    var key = this.generateKey(salt, passPhrase);
    var cipherParams = CryptoJS.lib.CipherParams.create({
        ciphertext: CryptoJS.enc.Base64.parse(cipherText)
    });
    var decrypted = CryptoJS.AES.decrypt(
        cipherParams,
        key,
        { iv: CryptoJS.enc.Hex.parse(iv) });
    return decrypted.toString(CryptoJS.enc.Utf8);
}
function FormatDate(time, add){
    var year=time.substring(0,4);
    var month=parseInt(time.substring(4,6), 10);
    var day=parseInt(time.substring(6), 10);
    var d = new Date(year + "/" + month + "/" + day);
    d.setDate(d.getDate() + (0 - add));
    var s = d.getFullYear() + "年" + ((d.getMonth() + 1) > 9 ? (d.getMonth() + 1) : "0" + (d.getMonth() + 1)) + "月" + (d.getDate() > 9 ? d.getDate() : "0" + d.getDate()) + "日";
    return s;
}

function FormatSBH(sbh, str) {
    var s1 = str.split("_");
    for (var i = 0; i < s1.length; i++) {
        sbh = chgchar(sbh, s1[i]);
    }
    return sbh;
}

function chgchar(nsrsbh, ss) {
    var a = ss.charAt(2);
    var b = ss.charAt(0);  //反向替换，所以和java中是相反的
    nsrsbh = nsrsbh.replaceAll(a, '#');
    nsrsbh = nsrsbh.replaceAll(b, '%');
    nsrsbh = nsrsbh.replaceAll('#', b);
    nsrsbh = nsrsbh.replaceAll('%', a);
    return nsrsbh;
}

function GetJeToDot(je){
    if (typeof(je) != "undefined" && je.trim() != ""){
        if (je.trim() == '-') {
            return je;
        }
        je = je.trim() + "";
        if (je.substring(0, 1) == '.') {
            je = '0' + '.' + je.substring(1, je.length);
            return je;
        }
        var index=je.indexOf(".");
        if(index<0){
            je+=".00";
        }else if(je.split(".")[1].length==1){
            je+="0";
        }
        if (je.substring(0,2) == '-.') {
            je = '-0.' + je.substring(2, je.length);
        }
        return je;
    } else {
        return je;
    }

}

function getje(je, ss) {
    if (typeof(je) != "undefined" && je != "") {
        return accAdd(je, ss);
    } else {
        return je;
    }
    //return je;
}
//加法函数，用来得到精确的加法结果
//说明：javascript的加法结果会有误差，在两个浮点数相加的时候会比较明显。这个函数返回较为精确的加法结果。
//调用：accAdd(arg1,arg2)
//返回值：arg1加上arg2的精确结果
function accAdd(arg1, arg2) {
    var r1,r2,m;
    if (arg1.trim() == "") {
        return arg1;
    }
    if(parseInt(arg1, 10)==arg1){
        r1=0;
    }else{
        r1=arg1.toString().split(".")[1].length;
    }
    if(parseInt(arg2, 10)==arg2){
        r2=0;
    }else{
        r2=arg2.toString().split(".")[1].length;
    }
    m = Math.pow(10, Math.max(r1, r2))  ;
    //alert(m);
    var r = (arg1 * m + arg2 * m) / m  ;
    return r.toFixed(2);
}

function NoToChinese(currencyDigits, fplx) {
// Constants:
    var MAXIMUM_NUMBER = 99999999999.99;
    // Predefine the radix characters and currency symbols for output:
    var CN_ZERO = "零";
    var CN_ONE = "壹";
    var CN_TWO = "贰";
    var CN_THREE = "叁";
    var CN_FOUR = "肆";
    var CN_FIVE = "伍";
    var CN_SIX = "陆";
    var CN_SEVEN = "柒";
    var CN_EIGHT = "捌";
    var CN_NINE = "玖";
    var CN_TEN = "拾";
    var CN_HUNDRED = "佰";
    var CN_THOUSAND = "仟";
    var CN_TEN_THOUSAND = "万";
    var CN_HUNDRED_MILLION = "亿";
    var CN_SYMBOL = "";
    var CN_DOLLAR = "圆";
    var CN_TEN_CENT = "角";
    var CN_CENT = "分";
    var CN_INTEGER = "整";
    if (fplx == "02" || fplx == "03") {
        CN_DOLLAR = "元";
    }

// Variables:
    var integral;    // Represent integral part of digit number.
    var decimal;    // Represent decimal part of digit number.
    var outputCharacters;    // The output result.
    var parts;
    var digits, radices, bigRadices, decimals;
    var zeroCount;
    var i, p, d;
    var quotient, modulus;

// Validate input string:
    currencyDigits = currencyDigits.toString();
    if (currencyDigits.trim() == "") {
        //alert("");
        return "请输入小写金额！";
    }
   /* if (currencyDigits.match(/[^,.\d]/) != null) {
        if (currencyDigits.substring(0,1) != '-') {
            return "小写金额含有无效字符！";
        }
    }*/
   /* if ((currencyDigits).match(/^((\d{1,3}(,\d{3})*(.((\d{3},)*\d{1,3}))?)|(\d+(.\d+)?))$/) == null) {
        if (currencyDigits.substring(0,1) != '-') {
            // alert("小写金额的格式不正确！");
            return "小写金额的格式不正确！";
        }
    }*/
    var fushuflag = "";
    if (currencyDigits.substring(0,1) == '-') {
        if (fplx == "01" || fplx == "04") {
            fushuflag = "（负数）";
        } else if (fplx == "02" || fplx == "03" || fplx == "11") {
            fushuflag = "负数：";
        } else if (fplx == "10") {
            fushuflag = "负";
        } else {
            fushuflag = "（负数）";
        }

        currencyDigits = currencyDigits.substring(1, currencyDigits.length);
    }
// Normalize the format of input digits:
    currencyDigits = currencyDigits.replace(/,/g, "");    // Remove comma delimiters.
    currencyDigits = currencyDigits.replace(/^0+/, "");    // Trim zeros at the beginning.
    // Assert the number is not greater than the maximum number.
    if (Number(currencyDigits) > MAXIMUM_NUMBER) {
        // alert("金额过大，应小于1000亿元！");
        return "金额过大，应小于1000亿元！";
    }

// Process the coversion from currency digits to characters:
    // Separate integral and decimal parts before processing coversion:
    parts = currencyDigits.split(".");
    if (parts.length > 1) {
        integral = parts[0];
        decimal = parts[1];
        // Cut down redundant decimal digits that are after the second.
        decimal = decimal.substr(0, 2);
    }
    else {
        integral = parts[0];
        decimal = "";
    }
    // Prepare the characters corresponding to the digits:
    digits = new Array(CN_ZERO, CN_ONE, CN_TWO, CN_THREE, CN_FOUR, CN_FIVE, CN_SIX, CN_SEVEN, CN_EIGHT, CN_NINE);
    radices = new Array("", CN_TEN, CN_HUNDRED, CN_THOUSAND);
    bigRadices = new Array("", CN_TEN_THOUSAND, CN_HUNDRED_MILLION);
    decimals = new Array(CN_TEN_CENT, CN_CENT);
    // Start processing:
    outputCharacters = "";
    // Process integral part if it is larger than 0:
    if (Number(integral) > 0) {
        zeroCount = 0;
        for (i = 0; i < integral.length; i++) {
            p = integral.length - i - 1;
            d = integral.substr(i, 1);
            quotient = p / 4;
            modulus = p % 4;
            if (d == "0") {
                zeroCount++;
            }
            else {
                if (zeroCount > 0)
                {
                    outputCharacters += digits[0];
                }
                zeroCount = 0;
                outputCharacters += digits[Number(d)] + radices[modulus];
            }
            if (modulus == 0 && zeroCount < 4) {
                outputCharacters += bigRadices[quotient];
                zeroCount = 0;
            }
        }
        outputCharacters += CN_DOLLAR;
    }
    // Process decimal part if there is:
    if (decimal != "") {
        for (i = 0; i < decimal.length; i++) {
            d = decimal.substr(i, 1);
            if (d != "0") {
                outputCharacters += digits[Number(d)] + decimals[i];
            }
        }
    }
    // Confirm and return the final output string:
    if (outputCharacters == "") {
        outputCharacters = CN_ZERO + CN_DOLLAR;
    }
    if (decimal == "" || decimal == "00" || decimal == "0" ) {
        outputCharacters += CN_INTEGER;
    }
    outputCharacters = fushuflag + CN_SYMBOL + outputCharacters;
    return outputCharacters;
}

function getzeroDot(je) {
    if (je.substring(0, 2) == "-.") {
        je = "-0." + je.substring(2);
    } else if (je.substring(0, 1) == ".") {
        je = "0." + je.substring(1);
    }
    return je;
}

function FormatSl(data){
    data = data.trim();
    if(data.substring(0,1)=="."){
        data=parseFloat("0"+data)*100;
    }
    if (data.length > 0) {
        return data+"%";
    } else {
        return "";
    }
}