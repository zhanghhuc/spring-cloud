package htmlunit;

/**
 * Created by zhang on 2017/10/15.
 */
public class Test {
    //    private static ScriptEngine engine = null;
//    static{
//        try {
//            ScriptEngineManager manager = new ScriptEngineManager();
//            engine = manager.getEngineByName("javascript");
//
//            FileReader reader = new FileReader("F:\\myProject\\fapiao\\src\\main\\resources\\aes.js");   // 执行指定脚本
//            FileReader reader1 = new FileReader("F:\\myProject\\fapiao\\src\\main\\resources\\util.AesUtil.js");   // 执行指定脚本
//            FileReader reader2 = new FileReader("F:\\myProject\\fapiao\\src\\main\\resources\\pbkdf2.js");   // 执行指定脚本
//            engine.eval(reader);
//            engine.eval(reader1);
//            engine.eval(reader2);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (ScriptException e) {
//            e.printStackTrace();
//        }
//    }
 /*  public static void main(String[] args) {
        OutputStream os = null;
        Random random = new Random();
        String iv = "d27182f6d6d4e3b76436fa396269f55f";
        String salt = "6ef7b6a55d8ca2a22b694e0ba72e60a5";
        int iterationCount = 100;
        int keySize = 128;
        try {
            //发送获取验证码请求
            String swjg = getSwjg("012001600111");
            String url = swjg + "/yzmQuery";

            System.out.println("url = "+url);
            util.HttpClientUtil httpClientUtil = new util.HttpClientUtil();
            String charset = "utf-8";
            Map<String,String> createMap = new HashMap<String,String>();
            createMap.put("fpdm","012001600111");
            createMap.put("r",random.nextFloat()+"");
            String result = httpClientUtil.doPost(url,createMap,charset);
            System.out.println(result);

            Map map = JSONObject.parseObject(result, Map.class);
            Object key1 = map.get("key1");
            Object key2 = map.get("key2");
            Object key3 = map.get("key3");
            String key4 = (String) map.get("key4");

            if (key1 == "003") {
                System.out.println("验证码请求次数过于频繁，请1分钟后再试！");
            }else if (key1 == "005") {
                System.out.println("非法请求!");
            }else if (key1 == "010") {
                System.out.println("网络超时，请重试！");
            }else if (key1 == "fpdmerr") {
                System.out.println("请输入合法发票代码!");
            }else if (key1 == "024") {
                System.out.println("24小时内验证码请求太频繁，请稍后再试！");
            }else if (key1 == "016") {
                System.out.println("服务器接收的请求太频繁，请稍后再试！");
            }else if (key1 != "") {

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
                os = generateImgFile(os, key1);

                //从命令行中获取验证码
                Scanner scanner = new Scanner(System.in);
                System.out.print("请输入验证码:");
                String fayzm = scanner.nextLine();

                String yzmSj  = (String) key2;
                String jmmy = (String) key3;

                createMap.clear();
                createMap.put("fpdm","012001600111");//发票代码
                createMap.put("fphm","77339284");//发票号码
                createMap.put("kprq","20170623");//发票日期
                createMap.put("fpje","510235");//发票金额
                createMap.put("fplx","10");//发票类型，常量99  01 02 03
                createMap.put("yzm",fayzm);//验证码
                createMap.put("yzmSj",yzmSj);//常量空串
                createMap.put("index",jmmy);//常量空串
                createMap.put("iv",iv);//随机向量
                createMap.put("salt",salt);//随机盐

                Iterator<Map.Entry<String, String>> it2 = createMap.entrySet().iterator();
                while (it2.hasNext()) {
                    Map.Entry entry = it2.next();
                    System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
                }

                url = swjg + "/query";
                String data = httpClientUtil.doPost(url,createMap,charset);
                System.out.println(" data   +  "+data);
                Map dataMap = JSONObject.parseObject(result, Map.class);

                String cyjgdm = (String) dataMap.get("key1");
                if("1".equals(cyjgdm)){
                    System.out.println("该省尚未开通发票查验功能！");
                }else if("001".equals(cyjgdm)){
                    System.out.println("成功.......");
                    String t = (String) dataMap.get("key5");
                    String hwxx = (String) dataMap.get("key3");
                    String jmbz  = "";
                    if(!"".equals((String) dataMap.get("key4"))){

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
                }

            }
        } catch (JSONException e) {
            System.out.println("服务器不可用");
            e.printStackTrace();
        } catch (Exception e) {
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
    }*/

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
//    public static void AESUtil_generateKey(String salt,String passPhrase){
//        try {
//            if(engine instanceof Invocable) {
//                Invocable invoke = (Invocable) engine;    // 调用merge方法，并传入两个参数
//                Object key = invoke.invokeFunction("decrypt","6ef7b6a55d8ca2a22b694e0ba72e60a5",
//                        "d27182f6d6d4e3b76436fa396269f55f","fc487a2c3b24b42b50ff5decc954bdf7","zutH7xOQWmEYbK2TafnaDvR1weGcj2JEozKKyi+1VR4DHz911OWfu6rzuO4Dluqa");
//                System.out.println(key);
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

   /* public static OutputStream generateImgFile(OutputStream os, Object key1) throws IOException {
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

    public static String getSwjg(String fpdm) {
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
        String result = null;
        for (CityAndIP city : list) {
            if (city.getCode().equals(dqdm)) {
                result = city.getIp() + "/WebQuery";
            }
        }
        return result;
    }*/
}
