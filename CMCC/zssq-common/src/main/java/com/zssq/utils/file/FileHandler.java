package com.zssq.utils.file;

import org.apache.log4j.Logger;

import java.io.*;

/**
 * @author SharlaCheung
 * @version 1.0
 * @ClassName FileHandler
 * @Description 文件处理：增删改，文件内容操作
 * @date 2017年6月13日 下午15:55:00
 * @since JDK 1.7
 */
public class FileHandler {

    private static Logger log = Logger.getLogger(FileHandler.class);
    //文件路径+名称
    private static String filenameTemp;

    /**
     * 创建文件
     *
     * @param path    生成文件路径
     * @param fileName    文件名称
     * @param filecontent 文件内容
     * @param suffix 后缀名
     * @return 是否创建成功，成功则返回true
     */
    public static boolean createFile( String path,String fileName,String suffix, String filecontent) {
        Boolean bool = false;
        filenameTemp = path + fileName + suffix;//文件路径+名称+文件类型
        File file = new File(filenameTemp);
        try {
            //如果文件不存在，则创建新的文件
            if (!file.exists()) {
                file.createNewFile();
                bool = true;
                System.out.println("success create file,the file is " + filenameTemp);
                //创建文件成功后，写入内容到文件里
//                writeFileContent(filenameTemp, filecontent);
            }
            writeContentUtf8(filenameTemp,filecontent);
        } catch (Exception e) {
            log.info("创建文件出错", e);
        }
        return bool;
    }

    /**
     * 向文件中写入内容
     *
     * @param filepath 文件路径与名称
     * @param newstr   写入的内容
     * @return
     * @throws IOException
     */
    public static boolean writeContentUtf8(String filepath, String newstr) throws IOException {
        Boolean bool = false;
        try{
            FileOutputStream writerStream = new FileOutputStream(filepath);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(writerStream, "UTF-8"));
            writer.write(newstr);
            writer.close();
            bool = true ;
        }catch (Exception e){
            e.printStackTrace();
        }
        return  bool ;

    }

    /**
     * 向文件中写入内容
     *
     * @param filepath 文件路径与名称
     * @param newstr   写入的内容
     * @return
     * @throws IOException
     */
    public static boolean writeFileContent(String filepath, String newstr) throws IOException {
        Boolean bool = false;
        String filein = newstr + "\r\n";//新写入的行，换行
        String temp = "";

        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        FileOutputStream fos = null;
        PrintWriter pw = null;
        try {
            File file = new File(filepath);//文件路径(包括文件名称)
            //将文件读入输入流
            fis = new FileInputStream(file);
            isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);
            StringBuffer buffer = new StringBuffer();

            //文件原有内容
            for (int i = 0; (temp = br.readLine()) != null; i++) {
                buffer.append(temp);
                // 行与行之间的分隔符 相当于“\n”
                buffer = buffer.append(System.getProperty("line.separator"));
            }
            buffer.append(filein);

            fos = new FileOutputStream(file);
            pw = new PrintWriter(fos);
            pw.write(buffer.toString().toCharArray());
            pw.flush();
            bool = true;
        } catch (Exception e) {
            log.info("向文件中写入内容出错", e);
        } finally {
            //不要忘记关闭
            if (pw != null) {
                pw.close();
            }
            if (fos != null) {
                fos.close();
            }
            if (br != null) {
                br.close();
            }
            if (isr != null) {
                isr.close();
            }
            if (fis != null) {
                fis.close();
            }
        }
        return bool;
    }

    /**
     * 删除文件
     *
     * @param fileName 文件名称
     * @return
     */
    public static boolean delFile(String path,String fileName,String suffix) {
        Boolean bool = false;
        filenameTemp = path + fileName +suffix;
        File file = new File(filenameTemp);
        try {
            if (file.exists()) {
                file.delete();
                bool = true;
            }
        } catch (Exception e) {
            log.info("删除文件出错", e);
        }
        return bool;
    }

    public static void main(String[] args) {
        createFile("E:\\out\\test\\js\\", "data",".js", "我的梦说别停留等待,就让光芒折射泪湿的瞳孔,映出心中最想拥有的彩虹," +
                "带我奔向那片有你的天空,因为你是我的梦 我的梦");
    }

}