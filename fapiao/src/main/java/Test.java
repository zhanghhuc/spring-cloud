import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by zhang on 2017/10/31.
 */
public class Test {
    public static void main(String[] args) {
//        AesUtil aesUtil = new AesUtil(128, 100);
//        String decrypt = aesUtil.decrypt("6ef7b6a55d8ca2a22b694e0ba72e60a5", "d27182f6d6d4e3b76436fa396269f55f", "bd1bf03f1dfac4d6312a74cc7cf5d724", "F+kfGV8mCuJdAuTkQtdMRvkxQ69136WBUWlEVj8YV9Y+NkHGdrdMW+MvazpZHcGP");
//        System.out.println(decrypt);

        System.out.println("￥5799.00".startsWith("￥"));
        System.out.println("￥5799.00".substring(1));
    }

    public static void FormatSl(){
         /* -1, 0, or 1
          less than, equal to, or greater than
       */
        BigDecimal a = new BigDecimal("");
        int i = a.compareTo(new BigDecimal("1"));
        System.out.println(i);
        if(i == -1){
            a = a.multiply(new BigDecimal("100"));
        }
        String result = a.toString()+"%";
        System.out.println(result);

    }
    public static void GetJeToDot (){
        BigDecimal a = new BigDecimal("-.2");
        a = a.setScale(2,BigDecimal.ROUND_HALF_UP);
        System.out.println(a.toString());
    }
    public static void getje(){
        BigDecimal a = new BigDecimal("-51.51");
        a = a.setScale(2, BigDecimal.ROUND_HALF_UP);
        BigDecimal b = new BigDecimal("59");
        b = b.setScale(2, BigDecimal.ROUND_HALF_UP);

        System.out.println(a);
        System.out.println(b);

        String result = a.add(b).toString();
        System.out.println(result);
    }

    public static void FormatDate(){
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
            Date date = format.parse("20170923");
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            c.add(Calendar.DAY_OF_MONTH,23);

            SimpleDateFormat f = new SimpleDateFormat("yyyy年MM月dd日");
            System.out.println(f.format(c.getTime()));

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static void formatSBH (){
        String sbh = "977407763209833301";
        String[] split = "2>4_7>1".split("_");
        for (int n=0; n<split.length; n++) {
            String a = String.valueOf(split[n].charAt(0));
            String b = String.valueOf(split[n].charAt(2));
            sbh = sbh.replaceAll(a,"#");
            sbh = sbh.replaceAll(b,"%");
            sbh =  sbh.replaceAll("%",a);
            sbh = sbh.replaceAll("#",b);
        }
        System.out.println("FormatSBH  = "+ sbh);
    }
}
