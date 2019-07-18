package com.monster.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

/**
 * @author Administrator
 * @Date 2019/7/18
 * @see
 */
public class EmailUtil {

        public static boolean sendMail(String address, String subject, String htmlMsg, Boolean isSSL) throws EmailException {
                if(DataUtil.isNotEmptyObjs(address,subject,htmlMsg,isSSL)){
                    try {
                        HtmlEmail email = new HtmlEmail();
                        List<String> list = new ArrayList<String>();
                        list.add(address);
                        String[] tos = list.toArray(new String[list.size()]);

                        // 这里是SMTP发送服务器的名字：163的如下："smtp.163.com"
                        email.setHostName("smtp.163.com");
                        if (isSSL) {
                            email.setSSLOnConnect(true);
                            email.setSmtpPort(465);
                        }
                        // 字符编码集的设置
                        email.setCharset("UTF-8");
                        // 收件人的邮箱
                        email.addTo(tos);
                        // 发送人的邮箱以及发件人名称
                        email.setFrom("shuaipitcc@163.com", "tcc");
                        // 如果需要认证信息的话，设置认证：用户名-密码。分别为发件人在邮件服务器上的注册名称和密码
                        email.setAuthentication("shuaipitcc@163.com", "ty203322");
                        // 要发送的邮件主题
                        email.setSubject(subject);
                        // 要发送的信息，由于使用了HtmlEmail，可以在邮件内容中使用HTML标签
                        MimeMultipart multipart=new MimeMultipart();

                        MimeBodyPart contentPart=new MimeBodyPart();
                        MimeMultipart contentMultipart=new MimeMultipart("mixed");
                        //创建用于保存HTML正文的MimeBodyPart对象，并将它保存到MimeMultipart中
                        MimeBodyPart htmlbodypart=new MimeBodyPart();
                        htmlbodypart.setContent(readHtml(),"text/html;charset=UTF-8");
                        MimeBodyPart gifBodyPart=new MimeBodyPart();
                        FileDataSource fds=new FileDataSource("D:\\logo.png");//图片所在的目录的绝对路径
                        gifBodyPart.setDataHandler(new DataHandler(fds));
                        gifBodyPart.setContentID("a00000001");	//cid的值
                        contentMultipart.addBodyPart(gifBodyPart);
                        contentMultipart.addBodyPart(htmlbodypart);
                        contentPart.setContent(contentMultipart);

                        multipart.addBodyPart(contentPart);

                        email.setContent(multipart);

                        String result = email.send();
                        System.out.println(result);

                        return true;
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw new EmailException();
                    }
                }
                return false;
        }

        public static void main(String args[]){

            try {
                sendMail("1475249187@qq.com","测试邮件","<p>你好啊靓仔</p>",false);
            }catch (Exception e){

            }
        }

    public static String readHtml() {
         String resultString = "";
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("D:\\study\\idea\\springboot_monster\\src\\main\\java\\com\\monster\\utils\\module.html"));
            String tempString = null;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                //替换掉占位符
                tempString = tempString.replace("@@@@","");
                resultString += tempString;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
            return resultString;
        }
    }
}
