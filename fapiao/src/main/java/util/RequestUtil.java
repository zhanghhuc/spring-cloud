package util;

import com.alibaba.fastjson.JSONObject;
import entity.CityAndIP;
import entity.fpDetailEntity;
import org.junit.Test;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.*;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by zhang on 2017/10/10.
 * @author zhang
 */
public class RequestUtil {
    private static ScriptEngine engine = null;
    static{
        try {
            ScriptEngineManager manager = new ScriptEngineManager();
            engine = manager.getEngineByName("javascript");

            FileReader reader = new FileReader("F:\\myProject\\fapiao\\src\\main\\resources\\aes.js");   // 执行指定脚本
            FileReader reader1 = new FileReader("F:\\myProject\\fapiao\\src\\main\\resources\\AesUtil.js");   // 执行指定脚本
            FileReader reader2 = new FileReader("F:\\myProject\\fapiao\\src\\main\\resources\\pbkdf2.js");   // 执行指定脚本
            engine.eval(reader);
            engine.eval(reader1);
            engine.eval(reader2);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ScriptException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        String iv = "d27182f6d6d4e3b76436fa396269f55f";
        String salt = "6ef7b6a55d8ca2a22b694e0ba72e60a5";
        int iterationCount = 100;
        int keySize = 128;

//        String fadm = "012001600111";
//        String fphm = "77339284";
//        String kprq = "20170623";
//        String fpje = "510235";

//        String fadm = "011001705111";
//        String fphm = "49263641";
//        String kprq = "20170615";
//        String fpje = "742006";

//        String fadm = "044001505111";
//        String fphm = "21057354";
//        String kprq = "20170618";
//        String fpje = "123609";

//        String fadm = "4200163130";
//        String fphm = "13892522";
//        String kprq = "20171013";
//        String fpje = "308.74";

//        String fadm = "1200172320";
//        String fphm = "09346290";
//        String kprq = "20171016";
//        String fpje = "095153";


        String fadm = "011001700107";
        String fphm = "01553257";
        String kprq = "20170630";
        String fpje = "999326";




        //10-增值税电子普通发票，04-增值税普通发票，01-增值税专用发票，11-增值税普通发票卷式发票
//        String fplx = "10";
//        String fplx = "04";
        String fplx = "11";
//        String fplx = "01";
        String swjgmc = "";
        try {
            //税务机关
            String[] swjgInfo = RequestUtil.getSwjg(fadm);
            String swjg = swjgInfo[1];
            swjgmc = swjgInfo[0];
            String charset = "utf-8";
            //发送获取验证码请求
            Map createMap = getCheckNum(fadm);

            String fayzm = (String) createMap.get("yzm");
            String yzmSj  = (String)createMap.get("key2");
            String jmmy = (String)createMap.get("key3");

            System.out.println("验证码："+fayzm);


            createMap.clear();
            createMap.put("callback","jQuery110205950839634531866_1507941966200");
            createMap.put("fpdm",fadm);//发票代码
            createMap.put("fphm",fphm);//发票号码
            createMap.put("kprq",kprq);//发票日期
            createMap.put("fpje",fpje);//发票金额
            createMap.put("fplx",fplx);//发票类型，常量99  01 02 03
            createMap.put("yzm",fayzm);//验证码
            createMap.put("yzmSj",yzmSj);//常量空串
            createMap.put("index",jmmy);//常量空串
            createMap.put("iv",iv);//随机向量
            createMap.put("salt",salt);//随机盐

            Iterator<Map.Entry<Integer, String>> it2 = createMap.entrySet().iterator();
            while (it2.hasNext()) {
                Map.Entry<Integer, String> entry = it2.next();
                System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
            }

            String url = swjg + "/query";
            HttpClientUtil httpClientUtil = new HttpClientUtil();
            String data = httpClientUtil.doPost(url,createMap,charset);

            if (data == null) {
                System.out.println("无发票详情。。");
                return;
            }

            data =  data.substring(data.indexOf("(")+1,data.length()-1);

            Map map = JSONObject.parseObject(data, Map.class);
            Iterator<Map.Entry<Integer, String>> it = map.entrySet().iterator();
            System.out.println("-------------- 发票详情  --------------");
            while (it.hasNext()) {
                Map.Entry<Integer, String> entry = it.next();
                System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
            }

            Map dataMap = JSONObject.parseObject(data, Map.class);

            String cyjgdm = (String) dataMap.get("key1");
            if("1".equals(cyjgdm)){
                System.out.println("该省尚未开通发票查验功能！");
            }else if("001".equals(cyjgdm)){
                System.out.println("成功.......");

                if("10".equals(fplx)){
                    System.out.println("解析增普电子票.......");
                    parse10Fp(iv, salt, fadm, fphm, swjgmc, charset, yzmSj, dataMap);
                }else if("01".equals(fplx)){
                    System.out.println("解析增专票.......");
                    parse01Fp(iv, salt, fadm, fphm, swjgmc, charset, yzmSj, dataMap);
                }else if("04".equals(fplx)){
                    System.out.println("解析增普票.......");
                    parse04Fp(iv, salt, fadm, fphm, swjgmc, charset, yzmSj, dataMap);
                }else if("11".equals(fplx)){
                    System.out.println("解析增普卷票.......");
                    parse11Fp(iv, salt, fadm, fphm, swjgmc, charset, yzmSj, dataMap);

                }


            }else if("002".equals(cyjgdm)){
                System.out.println("超过该张发票当日查验次数(请于次日再次查验)!");
            }else if("003".equals(cyjgdm)){
                System.out.println("发票查验请求太频繁，请稍后再试！");
            }else if("004".equals(cyjgdm)){
                System.out.println("超过服务器最大请求数，请稍后访问!");
            }else if("005".equals(cyjgdm)){
                System.out.println("请求不合法!");
            }else if("020".equals(cyjgdm)){
                System.out.println("由于查验行为异常，涉嫌违规，当前无法使用查验服务！");
            }else if("006".equals(cyjgdm)){
                //纳税人输入的发票校验信息与税务机关电子信息至少有一项不一致，则显示查验不一致的结果信息
                System.out.println("不一致");
            }else if("007".equals(cyjgdm)){
                System.out.println("验证码失效!");
            }else if("008".equals(cyjgdm)){
                System.out.println("验证码错误!");
            }else if("009".equals(cyjgdm)){
                System.out.println("查无此票");
            }else if ("rqerr".equals(cyjgdm)) {
                System.out.println("当日开具发票可于次日进行查验！");
            } else if ("010".equals(cyjgdm) || "010_".equals(cyjgdm) ) {
                System.out.println("网络超时，请重试！");
            }else{
                System.out.println("网络超时，请重试！");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void parse11Fp(String iv, String salt, String fadm, String fphm, String swjgmc, String charset, String yzmSj, Map dataMap) {
        String url;
        String jmsort = decrypt(salt,iv,(String) dataMap.get("key9"),(String) dataMap.get("key10"));
        String[] sortarray = jmsort.split("_");
        //解密备注
        String jmbz = decrypt(salt,iv,(String) dataMap.get("key9"),(String) dataMap.get("key4"));

        //获取js
        String jsname = (String) dataMap.get("key11");
        url = "https://inv-veri.chinatax.gov.cn/js/"+jsname+".js";
        String rule = HttpClientUtil.sendGetRequest(url, charset);
        rule = rule.substring(10,rule.length()-2);
        String[] rules = rule.split("☺");
        String splitstr = rules[0];

        //货物信息集合
        String hwxxs = (String) dataMap.get("key3");
        hwxxs = hwxxs.replaceAll(splitstr,"≡");
        //发票信息集合
        String fpxxs = fadm+"≡"+fphm+"≡"+swjgmc+"≡"+(String) dataMap.get("key2")+"≡"+yzmSj;
        fpxxs = fpxxs.replaceAll(splitstr,"≡");
        splitstr = "≡";
        String[] tmpfpxx = fpxxs.split("≡");
        String cysj = tmpfpxx[tmpfpxx.length - 1] ;
        String[] tmpfp = new String[tmpfpxx.length - 4];
        for (int i = 3; i < tmpfpxx.length - 1; i++) {
            tmpfp[i - 3] = tmpfpxx[i];
        }
        String[] newfpxx = new String[tmpfpxx.length - 4];
        for (int i = 0; i < tmpfpxx.length - 4; i++) {
            newfpxx[i] = tmpfp[Integer.parseInt(sortarray[i])];
        }
        String newfpxxstr = tmpfpxx[0] + "≡" + tmpfpxx[1] + "≡" + tmpfpxx[2] + "≡";
        for (int i = 0; i < newfpxx.length; i++) {
            newfpxxstr = newfpxxstr + newfpxx[i] + "≡";
        }
        fpxxs = newfpxxstr + cysj;
        int cycs=0;
        String[] fpxx=fpxxs.split("≡");
        cycs=Integer.parseInt(fpxx[3])+1;

        fpDetailEntity fpDetailEntity = new fpDetailEntity();

        //发票代码
        fpDetailEntity.setFpdm_dzfp(fpxx[0]);
        //发票号码
        fpDetailEntity.setFphm_dzfp(fpxx[1]);
        fpDetailEntity.setFpcc_dzfp(fpxx[2]+"增值税普通发票（卷票）");
        fpDetailEntity.setCycs("查验次数：第"+cycs+"次");
        fpDetailEntity.setCysj("查验时间："+fpxx[17]);
        //开票日期
        fpDetailEntity.setKprq_dzfp(formatDate(fpxx[4], rules[3]));
        //售方名称
        fpDetailEntity.setXfmc_dzfp(fpxx[5]);
        //纳税人识别号
        fpDetailEntity.setXfsbh_dzfp(FormatSBH(fpxx[6], rules[1]));
        //购方名称
        fpDetailEntity.setGfmc_dzfp(fpxx[7]);
        //纳税人识别号
        fpDetailEntity.setGfsbh_dzfp(fpxx[8]);
        //机器编号
        fpDetailEntity.setSbbh_dzfp(fpxx[9]);
        //价税合计 小写
        String xx = GetJeToDot(getje(fpxx[11], rules[2]));
        fpDetailEntity.setJshjxx_dzfp("￥" + xx);
        //价税合计 大写
        fpDetailEntity.setJshjdx_dzfp(NumberToCN.number2CNMontrayUnit(new BigDecimal(xx)));
        //校验码
        fpDetailEntity.setJym_dzfp(fpxx[13]);
        fpDetailEntity.setShy(fpxx[15]);


        //货物信息
        String[] hwinfo = hwxxs.split("≡");
        System.out.println("hwxxs  =  "+hwxxs);
        List<String[]> hws = new ArrayList<String[]>();
        for (int i =0; i<hwinfo.length; i++) {
            String[] hw = hwinfo[i].split("█");
            String[] hwResult = new String[4];
            for(int j=0;j<4;j++){
                if(j == 1){
                    hwResult[2] = getzeroDot(hw[j]);
                } else if (j == 2 || j == 3) {
                    if (j == 2) {
                        hwResult[1] = GetJeToDot(hw[j].trim());
                    }else{
                        hwResult[j] = GetJeToDot(hw[j].trim());
                    }
                } else if (j == 0) {
                    hwResult[j] = hw[j].replaceAll(rules[4],"");
                }
            }
            if(hwResult != null){
                hws.add(hwResult);
            }
        }
        fpDetailEntity.setHws(hws);

        System.out.println(fpDetailEntity.toString());
        for (String[] hw : hws) {
            for (String s : hw) {
                System.out.println(s);
            }
            System.out.println("---------------");
        }

    }

    private static void parse04Fp(String iv, String salt, String fadm, String fphm, String swjgmc, String charset, String yzmSj, Map dataMap) {
        String url;
        String jmsort = decrypt(salt,iv,(String) dataMap.get("key9"),(String) dataMap.get("key10"));
        String[] sortarray = jmsort.split("_");
        //解密备注
        String jmbz = decrypt(salt,iv,(String) dataMap.get("key9"),(String) dataMap.get("key4"));

        //获取js
        String jsname = (String) dataMap.get("key11");
        url = "https://inv-veri.chinatax.gov.cn/js/"+jsname+".js";
        String rule = HttpClientUtil.sendGetRequest(url, charset);
        rule = rule.substring(10,rule.length()-2);
        String[] rules = rule.split("☺");
        String splitstr = rules[0];

//                String resultJson = (String) dataMap.get("key6");
//                resultJson = resultJson.substring(resultJson.indexOf("{"));
//                Map resultMap = JSONObject.parseObject(resultJson, Map.class);

        //货物信息集合
        String hwxxs = (String) dataMap.get("key3");
        hwxxs = hwxxs.replaceAll(splitstr,"≡");
        //发票信息集合
        String fpxxs = fadm+"≡"+fphm+"≡"+swjgmc+"≡"+(String) dataMap.get("key2")+"≡"+yzmSj;
        fpxxs = fpxxs.replaceAll(splitstr,"≡");
        splitstr = "≡";
        String[] tmpfpxx = fpxxs.split("≡");
        String cysj = tmpfpxx[tmpfpxx.length - 1] ;
        String[] tmpfp = new String[tmpfpxx.length - 4];
        for (int i = 3; i < tmpfpxx.length - 1; i++) {
            tmpfp[i - 3] = tmpfpxx[i];
        }
        String[] newfpxx = new String[tmpfpxx.length - 4];
        for (int i = 0; i < tmpfpxx.length - 4; i++) {
            newfpxx[i] = tmpfp[Integer.parseInt(sortarray[i])];
        }
        String newfpxxstr = tmpfpxx[0] + "≡" + tmpfpxx[1] + "≡" + tmpfpxx[2] + "≡";
        for (int i = 0; i < newfpxx.length; i++) {
            newfpxxstr = newfpxxstr + newfpxx[i] + "≡";
        }
        fpxxs = newfpxxstr + cysj;
        int cycs=0;
        String[] fpxx=fpxxs.split("≡");
        cycs=Integer.parseInt(fpxx[3])+1;

        fpDetailEntity fpDetailEntity = new fpDetailEntity();

        //发票代码
        fpDetailEntity.setFpdm_dzfp(fpxx[0]);
        //发票号码
        fpDetailEntity.setFphm_dzfp(fpxx[1]);
        fpDetailEntity.setFpcc_dzfp(fpxx[2]+"增值税普通发票");
        fpDetailEntity.setCycs("查验次数：第"+cycs+"次");
        fpDetailEntity.setCysj("查验时间："+fpxx[21]);
        //开票日期
        fpDetailEntity.setKprq_dzfp(formatDate(fpxx[4], rules[3]));
        //售方名称
        fpDetailEntity.setXfmc_dzfp(fpxx[5]);
        //纳税人识别号
        fpDetailEntity.setXfsbh_dzfp(FormatSBH(fpxx[6], rules[1]));
        //地址、电话
        fpDetailEntity.setXfdzdh_dzfp(fpxx[7]);
        //开户行及账号
        fpDetailEntity.setXfyhzh_dzfp(fpxx[8]);
        //购方名称
        fpDetailEntity.setGfmc_dzfp(fpxx[9]);
        //纳税人识别号
        fpDetailEntity.setGfsbh_dzfp(fpxx[10]);
        //地址、电话
        fpDetailEntity.setGfdzdh_dzfp(fpxx[11]);
        //开户行及账号
        fpDetailEntity.setGfyhzh_dzfp(fpxx[12]);
        //金额
        fpDetailEntity.setJe_dzfp("￥" + GetJeToDot(getje(fpxx[19], rules[2])));
        //税额
        fpDetailEntity.setSe_dzfp("￥" + GetJeToDot(getje(fpxx[14], rules[2])));
        //价税合计 小写
        String xx = GetJeToDot(getje(fpxx[15], rules[2]));
        fpDetailEntity.setJshjxx_dzfp("￥" + xx);
        //价税合计 大写
        fpDetailEntity.setJshjdx_dzfp(NumberToCN.number2CNMontrayUnit(new BigDecimal(xx)));
        //备注
        fpDetailEntity.setBz_dzfp(jmbz);
        //机器编号
        fpDetailEntity.setSbbh_dzfp(fpxx[17]);
        //校验码
        fpDetailEntity.setJym_dzfp(fpxx[13]);

        //货物信息
        String[] hwinfo = hwxxs.split("≡");
        System.out.println("hwxxs  =  "+hwxxs);
        List<String[]> hws = new ArrayList<String[]>();
        for (int i =0; i<hwinfo.length; i++) {
            String[] hw = hwinfo[i].split("█");
            String[] hwResult = new String[8];
            for(int j=0;j<8;j++){
                if(j==3){
                    //html+=hw[6];
                    hwResult[j] = getzeroDot(hw[j]);   //这里要好好测一下，电子发票
                } else if (j == 4 || j == 5 || j == 7) {
                    //html+=GetJeToDot(getje(hw[j].trim(), je));
                    hwResult[j] = GetJeToDot(hw[j].trim());
                } else if (j == 0) {
                    hwResult[j] = hw[j].replaceAll(rules[4],"");
                } else if (j == 6) {
                    hwResult[j] = FormatSl(hw[j]);
                }else{
                    hwResult[j] = hw[j];
                }
            }
            if(hwResult != null){
                hws.add(hwResult);
            }
        }
        fpDetailEntity.setHws(hws);

        System.out.println(fpDetailEntity.toString());
        for (String[] hw : hws) {
            for (String s : hw) {
                System.out.println(s);
            }
            System.out.println("---------------");
        }
    }

    private static void parse01Fp(String iv, String salt, String fadm, String fphm, String swjgmc, String charset, String yzmSj, Map dataMap) {
        String url;
        String jmsort = decrypt(salt,iv,(String) dataMap.get("key9"),(String) dataMap.get("key10"));
        String[] sortarray = jmsort.split("_");
        //解密备注
        String jmbz = decrypt(salt,iv,(String) dataMap.get("key9"),(String) dataMap.get("key4"));

        //获取js
        String jsname = (String) dataMap.get("key11");
        url = "https://inv-veri.chinatax.gov.cn/js/"+jsname+".js";
        String rule = HttpClientUtil.sendGetRequest(url, charset);
        rule = rule.substring(10,rule.length()-2);
        String[] rules = rule.split("☺");
        String splitstr = rules[0];

        //货物信息集合
        String hwxxs = (String) dataMap.get("key3");
        hwxxs = hwxxs.replaceAll(splitstr,"≡");
        //发票信息集合
        String fpxxs = fadm+"≡"+fphm+"≡"+swjgmc+"≡"+(String) dataMap.get("key2")+"≡"+yzmSj;
        fpxxs = fpxxs.replaceAll(splitstr,"≡");
        splitstr = "≡";
        String[] tmpfpxx = fpxxs.split("≡");
        String cysj = tmpfpxx[tmpfpxx.length - 1] ;
        String[] tmpfp = new String[tmpfpxx.length - 4];
        for (int i = 3; i < tmpfpxx.length - 1; i++) {
            tmpfp[i - 3] = tmpfpxx[i];
        }
        String[] newfpxx = new String[tmpfpxx.length - 4];
        for (int i = 0; i < tmpfpxx.length - 4; i++) {
            newfpxx[i] = tmpfp[Integer.parseInt(sortarray[i])];
        }
        String newfpxxstr = tmpfpxx[0] + "≡" + tmpfpxx[1] + "≡" + tmpfpxx[2] + "≡";
        for (int i = 0; i < newfpxx.length; i++) {
            newfpxxstr = newfpxxstr + newfpxx[i] + "≡";
        }
        fpxxs = newfpxxstr + cysj;
        int cycs=0;
        String[] fpxx=fpxxs.split("≡");
        cycs=Integer.parseInt(fpxx[3])+1;

        fpDetailEntity fpDetailEntity = new fpDetailEntity();

        //发票代码
        fpDetailEntity.setFpdm_dzfp(fpxx[0]);
        //发票号码
        fpDetailEntity.setFphm_dzfp(fpxx[1]);
        fpDetailEntity.setFpcc_dzfp(fpxx[2]+"增值税专用发票");
        fpDetailEntity.setCycs("查验次数：第"+cycs+"次");
        fpDetailEntity.setCysj("查验时间："+fpxx[21]);
        //开票日期
        fpDetailEntity.setKprq_dzfp(formatDate(fpxx[4], rules[3]));
        //售方名称
        fpDetailEntity.setXfmc_dzfp(fpxx[9]);
        //纳税人识别号
        fpDetailEntity.setXfsbh_dzfp(FormatSBH(fpxx[10], rules[1]));
        //地址、电话
        fpDetailEntity.setXfdzdh_dzfp(fpxx[11]);
        //开户行及账号
        fpDetailEntity.setXfyhzh_dzfp(fpxx[12]);
        //购方名称
        fpDetailEntity.setGfmc_dzfp(fpxx[5]);
        //纳税人识别号
        fpDetailEntity.setGfsbh_dzfp(fpxx[6]);
        //地址、电话
        fpDetailEntity.setGfdzdh_dzfp(fpxx[7]);
        //开户行及账号
        fpDetailEntity.setGfyhzh_dzfp(fpxx[8]);
        //金额
        fpDetailEntity.setJe_dzfp("￥" + GetJeToDot(getje(fpxx[13], rules[2])));
        //税额
        fpDetailEntity.setSe_dzfp("￥" + GetJeToDot(getje(fpxx[14], rules[2])));
        //价税合计 小写
        String xx = GetJeToDot(getje(fpxx[15], rules[2]));
        fpDetailEntity.setJshjxx_dzfp("￥" + xx);
        //价税合计 大写
        fpDetailEntity.setJshjdx_dzfp(NumberToCN.number2CNMontrayUnit(new BigDecimal(xx)));
        //备注
        fpDetailEntity.setBz_dzfp(jmbz);
        //机器编号
        fpDetailEntity.setSbbh_dzfp(fpxx[17]);
        //校验码
        fpDetailEntity.setJym_dzfp(fpxx[19]);

        //货物信息
        String[] hwinfo = hwxxs.split("≡");
        System.out.println("hwxxs  =  "+hwxxs);
        List<String[]> hws = new ArrayList<String[]>();
        for (int i =0; i<hwinfo.length; i++) {
            String[] hw = hwinfo[i].split("█");
            String[] hwResult = new String[8];
            for(int j=0;j<8;j++){
                if(j==3){
                    //html+=hw[6];
                    hwResult[j] = getzeroDot(hw[j]);   //这里要好好测一下，电子发票
                } else if (j == 4 || j == 5 || j == 7) {
                    //html+=GetJeToDot(getje(hw[j].trim(), je));
                    hwResult[j] = GetJeToDot(hw[j].trim());
                } else if (j == 0) {
                    hwResult[j] = hw[j].replaceAll(rules[4],"");
                } else if (j == 6) {
                    hwResult[j] = FormatSl(hw[j]);
                }else{
                    hwResult[j] = hw[j];
                }
            }
            if(hwResult != null){
                hws.add(hwResult);
            }
        }
        fpDetailEntity.setHws(hws);

        System.out.println(fpDetailEntity.toString());
        for (String[] hw : hws) {
            for (String s : hw) {
                System.out.println(s);
            }
            System.out.println("---------------");
        }
    }

    private static void parse10Fp(String iv, String salt, String fadm, String fphm, String swjgmc, String charset, String yzmSj, Map dataMap) {
        String url;//解密排序顺序
        String jmsort = decrypt(salt,iv,(String) dataMap.get("key9"),(String) dataMap.get("key10"));
        String[] sortarray = jmsort.split("_");
        //解密备注
        String jmbz = decrypt(salt,iv,(String) dataMap.get("key9"),(String) dataMap.get("key4"));

        //获取js
        String jsname = (String) dataMap.get("key11");
        url = "https://inv-veri.chinatax.gov.cn/js/"+jsname+".js";
        String rule = HttpClientUtil.sendGetRequest(url, charset);
        rule = rule.substring(10,rule.length()-2);
        String[] rules = rule.split("☺");
        String splitstr = rules[0];

//                String resultJson = (String) dataMap.get("key6");
//                resultJson = resultJson.substring(resultJson.indexOf("{"));
//                Map resultMap = JSONObject.parseObject(resultJson, Map.class);

        //货物信息集合
        String hwxxs = (String) dataMap.get("key3");
        hwxxs = hwxxs.replaceAll(splitstr,"≡");
        //发票信息集合
        String fpxxs = fadm+"≡"+fphm+"≡"+swjgmc+"≡"+(String) dataMap.get("key2")+"≡"+yzmSj;
        fpxxs = fpxxs.replaceAll(splitstr,"≡");
        splitstr = "≡";
        String[] tmpfpxx = fpxxs.split("≡");
        String cysj = tmpfpxx[tmpfpxx.length - 1] ;
        String[] tmpfp = new String[tmpfpxx.length - 4];
        for (int i = 3; i < tmpfpxx.length - 1; i++) {
            tmpfp[i - 3] = tmpfpxx[i];
        }
        String[] newfpxx = new String[tmpfpxx.length - 4];
        for (int i = 0; i < tmpfpxx.length - 4; i++) {
            newfpxx[i] = tmpfp[Integer.parseInt(sortarray[i])];
        }
        String newfpxxstr = tmpfpxx[0] + "≡" + tmpfpxx[1] + "≡" + tmpfpxx[2] + "≡";
        for (int i = 0; i < newfpxx.length; i++) {
            newfpxxstr = newfpxxstr + newfpxx[i] + "≡";
        }
        fpxxs = newfpxxstr + cysj;
        int cycs=0;
        String[] fpxx=fpxxs.split("≡");
        cycs=Integer.parseInt(fpxx[3])+1;

        fpDetailEntity fpDetailEntity = new fpDetailEntity();

        //发票代码
        fpDetailEntity.setFpdm_dzfp(fpxx[0]);
        //发票号码
        fpDetailEntity.setFphm_dzfp(fpxx[1]);
        fpDetailEntity.setFpcc_dzfp(fpxx[2]+"增值税电子普通发票");
        fpDetailEntity.setCycs("查验次数：第"+cycs+"次");
        fpDetailEntity.setCysj("查验时间："+fpxx[20]);
        //开票日期
        fpDetailEntity.setKprq_dzfp(formatDate(fpxx[4], rules[3]));
        //售方名称
        fpDetailEntity.setXfmc_dzfp(fpxx[5]);
        //纳税人识别号
        fpDetailEntity.setXfsbh_dzfp(FormatSBH(fpxx[6], rules[1]));
        //地址、电话
        fpDetailEntity.setXfdzdh_dzfp(fpxx[7]);
        //开户行及账号
        fpDetailEntity.setXfyhzh_dzfp(fpxx[8]);
        //购方名称
        fpDetailEntity.setGfmc_dzfp(fpxx[9]);
        //纳税人识别号
        fpDetailEntity.setGfsbh_dzfp(fpxx[10]);
        //地址、电话
        fpDetailEntity.setGfdzdh_dzfp(fpxx[11]);
        //开户行及账号
        fpDetailEntity.setGfyhzh_dzfp(fpxx[12]);
        //金额
        fpDetailEntity.setJe_dzfp("￥" + GetJeToDot(getje(fpxx[18], rules[2])));
        //税额
        fpDetailEntity.setSe_dzfp("￥" + GetJeToDot(getje(fpxx[14], rules[2])));
        //价税合计 小写
        String xx = GetJeToDot(getje(fpxx[15], rules[2]));
        fpDetailEntity.setJshjxx_dzfp("￥" + xx);
        //价税合计 大写
        fpDetailEntity.setJshjdx_dzfp(NumberToCN.number2CNMontrayUnit(new BigDecimal(xx)));
        //备注
        fpDetailEntity.setBz_dzfp(jmbz);
        //机器编号
        fpDetailEntity.setSbbh_dzfp(fpxx[17]);
        //校验码
        fpDetailEntity.setJym_dzfp(fpxx[13]);

        //货物信息
        String[] hwinfo = hwxxs.split("≡");
        System.out.println("hwxxs  =  "+hwxxs);
        List<String[]> hws = new ArrayList<String[]>();
        for (int i =0; i<hwinfo.length; i++) {
            String[] hw = hwinfo[i].split("█");
//                    for (String s : hw) {
//                        System.out.println("hw item = "+s);
//                    }
            String[] hwResult = new String[8];
            for(int j=0;j<8;j++){
                if(j==3){
                    //html+=hw[6];
                    hwResult[j] = getzeroDot(hw[6]);   //这里要好好测一下，电子发票
                } else if (j == 4 || j == 5 || j == 7) {
                    //html+=GetJeToDot(getje(hw[j].trim(), je));
                    hwResult[j] = GetJeToDot(hw[j].trim());
                } else if (j == 0) {
                    hwResult[j] = hw[j].replaceAll(rules[4],"");
                } else if (j == 6) {
                    hwResult[j] = FormatSl(hw[3]);
                }else{
                    hwResult[j] = hw[j];
                }
            }
            if(hwResult != null){
                hws.add(hwResult);
            }
        }
        fpDetailEntity.setHws(hws);

        System.out.println(fpDetailEntity.toString());
        for (String[] hw : hws) {
            for (String s : hw) {
                System.out.println(s);
            }
            System.out.println("---------------");
        }
    }

    @Test
    public void juesTest(){
        String str = "var rule=\"□☺2>4_7>1☺59☺-23☺▽\";";
        System.out.println(str.substring(10,str.length()-2));
    }

   /* public static void main(String[] args) {
//        AESUtil_generateKey("6ef7b6a55d8ca2a22b694e0ba72e60a5","fc487a2c3b24b42b50ff5decc954bdf7");

        //下载js文件
       *//* util.HttpClientUtil httpClientUtil = new util.HttpClientUtil();
        String charset = "utf-8";
        String data = httpClientUtil.sendGetRequest("https://inv-veri.chinatax.gov.cn/js/582ae.js",charset);
        System.out.println(data);*//*

        //下载发票详情页
        util.HttpClientUtil httpClientUtil = new util.HttpClientUtil();
        String charset = "utf-8";
        String html = httpClientUtil.sendGetRequest("https://inv-veri.chinatax.gov.cn/cyjgedge10.html", charset);
        System.out.println(html);
    }*/


    @Test
    public void Test(){

        String iv = "d27182f6d6d4e3b76436fa396269f55f";
        String salt = "6ef7b6a55d8ca2a22b694e0ba72e60a5";
        String passPhrase = "c41b8707859d50ff451a787d1c57ed64";
        String cipherText = "P1rC74RQDfecfXTE7xmphfCF9CYODz6DC5EWJLucBzLmaLUk/N+t/1vQvt8KxYJ9";

        try {
            if(engine instanceof Invocable) {
                Invocable invoke = (Invocable) engine;    // 调用merge方法，并传入两个参数
                Object key = invoke.invokeFunction("decrypt",salt, iv, passPhrase, cipherText);
                System.out.println(key);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFormat(){
        try {
            Invocable invoke = (Invocable) engine;    // 调用merge方法，并传入两个参数
            Object key = invoke.invokeFunction("FormatDate","20171017", "4");
            System.out.println("FormatDate = "+key);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /*
    *   String iv = "d27182f6d6d4e3b76436fa396269f55f";
        String salt = "6ef7b6a55d8ca2a22b694e0ba72e60a5";
        String passPhrase = "c41b8707859d50ff451a787d1c57ed64";
        String cipherText = "P1rC74RQDfecfXTE7xmphfCF9CYODz6DC5EWJLucBzLmaLUk/N+t/1vQvt8KxYJ9";
   */
    public static String decrypt(String salt, String iv, String passPhrase, String cipherText){
        try {
            if(engine instanceof Invocable) {
                System.out.println("--------------------------------");
                System.out.println(salt);
                System.out.println(iv);
                System.out.println(passPhrase);
                System.out.println(cipherText);
                Invocable invoke = (Invocable) engine;    // 调用merge方法，并传入两个参数
                Object key = invoke.invokeFunction("decrypt",salt, iv, passPhrase, cipherText);
                System.out.println("jmsort = "+key);
                System.out.println("--------------------------------");
                return (String)key;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


    //"FormatDate","20171017", "4"
    public static String formatDate(String time, String add){
        try {
            if(engine instanceof Invocable) {
                System.out.println("-----------------------------");
                System.out.println(time);
                System.out.println(add);
                Invocable invoke = (Invocable) engine;    // 调用merge方法，并传入两个参数
                Object key = invoke.invokeFunction("FormatDate",time, add);
                System.out.println("FormatDate = "+key);
                System.out.println("-----------------------------");
                return (String)key;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String FormatSBH(String sbh, String str){
        try {
            if(engine instanceof Invocable) {
                System.out.println("---------------------------------");
                System.out.println(sbh);
                System.out.println(str);
                Invocable invoke = (Invocable) engine;
                Object key = invoke.invokeFunction("FormatSBH",sbh, str);
                System.out.println("FormatSBH = "+key);
                System.out.println("---------------------------------");
                return (String)key;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getje(String s1,String s2){
        try {
            if(engine instanceof Invocable) {
                System.out.println("------------------------");
                System.out.println(s1);
                System.out.println(s2);
                Invocable invoke = (Invocable) engine;
                Object key = invoke.invokeFunction("getje",s1,s2);
                System.out.println("getje = "+key);
                System.out.println("------------------------");
                return (String)key;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String GetJeToDot(String je){
        try {
            if(engine instanceof Invocable) {
                System.out.println("-------------------------------");
                System.out.println(je);
                Invocable invoke = (Invocable) engine;
                Object key = invoke.invokeFunction("GetJeToDot",je);
                System.out.println("GetJeToDot = "+key);
                System.out.println("-------------------------------");
                return (String)key;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    @Test
    public void testDx(){
        try {
            Invocable invoke = (Invocable) engine;
            String je = (String) invoke.invokeFunction("NoToChinese","48.00","10");
            Object key = invoke.invokeFunction("NoToChinese",je);
            System.out.println("NoToChinese = "+key);
        } catch (ScriptException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public static String getzeroDot(String je){
        try {
            if(engine instanceof Invocable) {
                System.out.println("-------------------------------");
                System.out.println(je);
                Invocable invoke = (Invocable) engine;
                Object key = invoke.invokeFunction("getzeroDot",je);
                System.out.println("getzeroDot = "+key);
                System.out.println("-------------------------------");
                return (String)key;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String FormatSl(String data){
        try {
            if(engine instanceof Invocable) {
                System.out.println("-----------------------------");
                System.out.println(data);
                Invocable invoke = (Invocable) engine;
                Object key = invoke.invokeFunction("FormatSl",data);
                System.out.println("FormatSl = "+key);
                System.out.println("-----------------------------");
                return (String)key;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static OutputStream generateImgFile(OutputStream os, Object key1) throws IOException {
        String data = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Title</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "\n" +
                "<img src=\"data:image/png;base64,"+key1+"\"/>\n" +
                "\n" +
                "</body>\n" +
                "</html>";

        String path = "F:";
        byte[] bs = data.getBytes();
        File tempFile = new File(path);
        if (!tempFile.exists()) {
            tempFile.mkdirs();
        }
        File f = new File(tempFile.getPath() + File.separator + "test.html");
        f.delete();
        os = new FileOutputStream(f);
        os.write(bs, 0, bs.length);
        return os;
    }

    public static String[] getSwjg(String fpdm) {
        String jasonArray = "[{'code':'1100','sfmc':'北京','Ip':'https://zjfpcyweb.bjsat.gov.cn:443','address':'https://zjfpcyweb.bjsat.gov.cn:443'},\n" +
                "        {'code':'1200','sfmc':'天津','Ip':'https://fpcy.tjsat.gov.cn:443','address':'https://fpcy.tjsat.gov.cn:443'},\n" +
                "        {'code':'1300','sfmc':'河北','Ip':'https://fpcy.he-n-tax.gov.cn:82','address':'https://fpcy.he-n-tax.gov.cn:82'},\n" +
                "        {'code':'1400','sfmc':'山西','Ip':'https://fpcy.tax.sx.cn:443','address':'https://fpcy.tax.sx.cn:443'},\n" +
                "        {'code':'1500','sfmc':'内蒙古','Ip':'https://fpcy.nm-n-tax.gov.cn:443','address':'https://fpcy.nm-n-tax.gov.cn:443'},\n" +
                "        {'code':'2100','sfmc':'辽宁','Ip':'https://fpcy.tax.ln.cn:443','address':'https://fpcy.tax.ln.cn:443'},\n" +
                "        {'code':'2102','sfmc':'大连','Ip':'https://fpcy.dlntax.gov.cn:443','address':'https://fpcy.dlntax.gov.cn:443'},\n" +
                "        {'code':'2200','sfmc':'吉林','Ip':'https://fpcy.jl-n-tax.gov.cn:4432','address':'https://fpcy.jl-n-tax.gov.cn:4432'},\n" +
                "        {'code':'2300','sfmc':'黑龙江','Ip':'https://fpcy.hl-n-tax.gov.cn:443','address':'https://fpcy.hl-n-tax.gov.cn:443'},\n" +
                "        {'code':'3100','sfmc':'上海','Ip':'https://fpcyweb.tax.sh.gov.cn:1001','address':'https://fpcyweb.tax.sh.gov.cn:1001'},\n" +
                "        {'code':'3200','sfmc':'江苏','Ip':'https://fpdk.jsgs.gov.cn:80','address':'https://fpdk.jsgs.gov.cn:80'},\n" +
                "        {'code':'3300','sfmc':'浙江','Ip':'https://fpcyweb.zjtax.gov.cn:443','address':'https://fpcyweb.zjtax.gov.cn:443'},\n" +
                "        {'code':'3302','sfmc':'宁波','Ip':'https://fpcy.nb-n-tax.gov.cn:443','address':'https://fpcy.nb-n-tax.gov.cn:443'},\n" +
                "        {'code':'3400','sfmc':'安徽','Ip':'https://fpcy.ah-n-tax.gov.cn:443','address':'https://fpcy.ah-n-tax.gov.cn:443'},\n" +
                "        {'code':'3500','sfmc':'福建','Ip':'https://fpcyweb.fj-n-tax.gov.cn:443','address':'https://fpcyweb.fj-n-tax.gov.cn:443'},\n" +
                "        {'code':'3502','sfmc':'厦门','Ip':'https://fpcy.xm-n-tax.gov.cn','address':'https://fpcy.xm-n-tax.gov.cn'},\n" +
                "        {'code':'3600','sfmc':'江西','Ip':'https://fpcy.jxgs.gov.cn:82','address':'https://fpcy.jxgs.gov.cn:82'},\n" +
                "        {'code':'3700','sfmc':'山东','Ip':'https://fpcy.sd-n-tax.gov.cn:443','address':'https://fpcy.sd-n-tax.gov.cn:443'},\n" +
                "        {'code':'3702','sfmc':'青岛','Ip':'https://fpcy.qd-n-tax.gov.cn:443','address':'https://fpcy.qd-n-tax.gov.cn:443'},\n" +
                "        {'code':'4100','sfmc':'河南','Ip':'https://fpcy.ha-n-tax.gov.cn','address':'https://fpcy.ha-n-tax.gov.cn'},\n" +
                "        {'code':'4200','sfmc':'湖北','Ip':'https://fpcy.hb-n-tax.gov.cn:443','address':'https://fpcy.hb-n-tax.gov.cn:443'},\n" +
                "        {'code':'4300','sfmc':'湖南','Ip':'https://fpcy.hntax.gov.cn:8083','address':'https://fpcy.hntax.gov.cn:8083'},\n" +
                "        {'code':'4400','sfmc':'广东','Ip':'https://fpcy.gd-n-tax.gov.cn:443','address':'https://fpcy.gd-n-tax.gov.cn:443'},\n" +
                "        {'code':'4403','sfmc':'深圳','Ip':'https://fpcy.szgs.gov.cn:443','address':'https://fpcy.szgs.gov.cn:443'},\n" +
                "        {'code':'4500','sfmc':'广西','Ip':'https://fpcy.gxgs.gov.cn:8200','address':'https://fpcy.gxgs.gov.cn:8200'},\n" +
                "        {'code':'4600','sfmc':'海南','Ip':'https://fpcy.hitax.gov.cn:443','address':'https://fpcy.hitax.gov.cn:443'},\n" +
                "        {'code':'5000','sfmc':'重庆','Ip':'https://fpcy.cqsw.gov.cn:80','address':'https://fpcy.cqsw.gov.cn:80'},\n" +
                "        {'code':'5100','sfmc':'四川','Ip':'https://fpcy.sc-n-tax.gov.cn:443','address':'https://fpcy.sc-n-tax.gov.cn:443'},\n" +
                "        {'code':'5200','sfmc':'贵州','Ip':'https://fpcy.gz-n-tax.gov.cn:80','address':'https://fpcy.gz-n-tax.gov.cn:80'},\n" +
                "        {'code':'5300','sfmc':'云南','Ip':'https://fpcy.yngs.gov.cn:443','address':'https://fpcy.yngs.gov.cn:443'},\n" +
                "        {'code':'5400','sfmc':'西藏','Ip':'https://fpcy.xztax.gov.cn:81','address':'https://fpcy.xztax.gov.cn:81'},\n" +
                "        {'code':'6100','sfmc':'陕西','Ip':'https://fpcyweb.sn-n-tax.gov.cn:443','address':'https://fpcyweb.sn-n-tax.gov.cn:443'},\n" +
                "        {'code':'6200','sfmc':'甘肃','Ip':'https://fpcy.gs-n-tax.gov.cn:443','address':'https://fpcy.gs-n-tax.gov.cn:443'},\n" +
                "        {'code':'6300','sfmc':'青海','Ip':'https://fpcy.qh-n-tax.gov.cn:443','address':'https://fpcy.qh-n-tax.gov.cn:443'},\n" +
                "        {'code':'6400','sfmc':'宁夏','Ip':'https://fpcy.nxgs.gov.cn:443','address':'https://fpcy.nxgs.gov.cn:443'},\n" +
                "        {'code':'6500','sfmc':'新疆','Ip':'https://fpcy.xj-n-tax.gov.cn:443','address':'https://fpcy.xj-n-tax.gov.cn:443'}]";
        List<CityAndIP> list = new ArrayList<CityAndIP>();
        list = JSONObject.parseArray(jasonArray, CityAndIP.class);
        String dqdm = "";
        if(fpdm.length() == 12){
            dqdm=fpdm.substring(1,5);
        }else{
            dqdm=fpdm.substring(0,4);
        }
        if(dqdm!="2102"&&dqdm!="3302"&&dqdm!="3502"&&dqdm!="3702"&&dqdm!="4403"){
            dqdm=dqdm.substring(0,2)+"00";
        }
        String[] result = new String[2];
        for (CityAndIP city : list) {
            if (city.getCode().equals(dqdm)) {
                String ip = city.getIp() + "/WebQuery";
                String mc = city.getSfmc();
                result[0] = mc;
                result[1] = ip;
            }
        }
        return result;
    }

    public static Map getCheckNum(String fpdm) {
        Random random = new Random();
        OutputStream os = null;
        String fayzm = null;
        try {
            String[] swjgInfo = RequestUtil.getSwjg(fpdm);
            String swjg = swjgInfo[1];
            String url = swjg + "/yzmQuery";

            System.out.println("url = " + url);
            HttpClientUtil httpClientUtil = new HttpClientUtil();
            String charset = "utf-8";
            Map<String, String> createMap = new HashMap<String, String>();
            createMap.put("fpdm", fpdm);
            createMap.put("r", random.nextFloat() + "");
            String result = httpClientUtil.doPost(url, createMap, charset);
            System.out.println(result);

            final Map map = JSONObject.parseObject(result, Map.class);
            Object key1 = map.get("key1");
            String key4 = (String) map.get("key4");

            if ("003".equals(key1)) {
                System.out.println("验证码请求次数过于频繁，请1分钟后再试！");
            } else if ("005".equals(key1)) {
                System.out.println("非法请求!");
            } else if ("010".equals(key1)) {
                System.out.println("网络超时，请重试！");
            } else if ("fpdmerr".equals(key1)) {
                System.out.println("请输入合法发票代码!");
            } else if ("024".equals(key1)) {
                System.out.println("24小时内验证码请求太频繁，请稍后再试！");
            } else if ("016".equals(key1)) {
                System.out.println("服务器接收的请求太频繁，请稍后再试！");
            } else if (!"".equals(key1)) {

                if (key4.equals("00")) {
                    System.out.println("请输入验证码文字");
                } else if (key4.equals("01")) {
                    System.out.println("请输入验证码图片中***红色***文字");
                } else if (key4.equals("02")) {
                    System.out.println("请输入验证码图片中***黄色***文字");
                } else if (key4.equals("03")) {
                    System.out.println("请输入验证码图片中***蓝色***文字");
                }

                //将图片保存成html，可以在浏览器中查看
                os = RequestUtil.generateImgFile(os, key1);

                //从命令行中获取验证码
                Scanner scanner = new Scanner(System.in);
                System.out.print("请输入验证码:");
                fayzm = scanner.nextLine();
                map.put("yzm",fayzm);
                return map;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 完毕，关闭所有链接
            try {
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new HashMap();
    }
}
