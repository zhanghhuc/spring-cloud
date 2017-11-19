package htmlunit;

/**
 * Created by zhang on 2017/10/12.
 */
public class HtmlunitTest {

   /* public static void main(String[] args) {
        try {
            // 创建webclient
            WebClient webClient = new WebClient(BrowserVersion.CHROME);
            //2.设置连接的相关选项
            webClient.getOptions().setCssEnabled(false);
            webClient.getOptions().setJavaScriptEnabled(true);  //需要解析js
            webClient.getOptions().setThrowExceptionOnScriptError(false);  //解析js出错时不抛异常
            webClient.getOptions().setTimeout(10000);  //超时时间  ms
            webClient.getOptions().setUseInsecureSSL(true);
            HtmlPage index = (HtmlPage) webClient.getPage("https://inv-veri.chinatax.gov.cn/");

            HtmlInput fpdm = (HtmlInput) index.getElementById("fpdm");
            HtmlInput fphm = (HtmlInput) index.getElementById("fphm");
            HtmlInput kprq = (HtmlInput) index.getElementById("kprq");
            HtmlInput kjje = (HtmlInput) index.getElementById("kjje");
            HtmlInput yzm = (HtmlInput) index.getElementById("yzm");
            fpdm.setValueAttribute("011001705111");
            fphm.setValueAttribute("49263641");
            kprq.setValueAttribute("20170615");
            kjje.setValueAttribute("742006");

            //获取验证码
            String checkNum = getCheckNum();
            yzm.setValueAttribute(checkNum);

            //获得查询按钮
            HtmlButton checkfp = index.getHtmlElementById("checkfp");
            HtmlPage page = checkfp.click();
            System.out.println(page.getElementById("floatwin").getTagName());
            System.out.println(page.getElementById("floatwin").getChildElementCount());
            System.out.println(page.getElementById("floatwin1").getTagName());
            System.out.println(page.getElementById("floatwin1").getChildElementCount());




            webClient.closeAllWindows();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }*/


    /*public static Map getCheckNum(String fpdm) {
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

            if (key1 == "003") {
                System.out.println("验证码请求次数过于频繁，请1分钟后再试！");
            } else if (key1 == "005") {
                System.out.println("非法请求!");
            } else if (key1 == "010") {
                System.out.println("网络超时，请重试！");
            } else if (key1 == "fpdmerr") {
                System.out.println("请输入合法发票代码!");
            } else if (key1 == "024") {
                System.out.println("24小时内验证码请求太频繁，请稍后再试！");
            } else if (key1 == "016") {
                System.out.println("服务器接收的请求太频繁，请稍后再试！");
            } else if (key1 != "") {

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
    }*/

}
