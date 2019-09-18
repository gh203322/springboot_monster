package com.monster.utils;

import java.io.*;
import java.net.URL;
import java.util.*;
import javax.activation.*;
import javax.mail.*;
import javax.mail.Message.RecipientType;
import javax.mail.internet.*;
import org.apache.commons.mail.*;
import org.apache.commons.mail.resolver.*;

/**
 * @author Administrator
 * @Date 2019/7/18
 * @see
 */
public class EmailUtil {

        public static boolean sendMail(String address, String subject, String htmlMsg, Boolean isSSL) throws EmailException {
                if(DataUtil.isNotEmptyObjs(address,subject,htmlMsg,isSSL)){
                    try {
                        ImageHtmlEmail email = new ImageHtmlEmail();
                        //email.setDebug(true);//可以看到执行过程的debug信息

                        DataSourceResolver[] dataSourceResolvers =
                                new DataSourceResolver[]{new DataSourceFileResolver(),//添加DataSourceFileResolver用于解析本地图片
                                        new DataSourceUrlResolver(new URL("http://"))};//添加DataSourceUrlResolver用于解析网络图片，注意：new URL("http://")
                        email.setDataSourceResolver(new DataSourceCompositeResolver(dataSourceResolvers));

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

                        email.setSSLCheckServerIdentity(true);
                        // 收件人的邮箱
                        email.addTo(tos);
                        // 发送人的邮箱以及发件人名称
                        email.setFrom("shuaipitcc@163.com", "tcc");
                        // 如果需要认证信息的话，设置认证：用户名-密码。分别为发件人在邮件服务器上的注册名称和密码
                        email.setAuthentication("shuaipitcc@163.com", "ty203322");
                        // 要发送的邮件主题
                        email.setSubject(subject);
                        // 要发送的信息，由于使用了HtmlEmail，可以在邮件内容中使用HTML标签
                        email.setHtmlMsg(htmlMsg);

                        MimeMultipart multipart = new MimeMultipart("related");
                        BodyPart messageBodyPart = new MimeBodyPart();
                      //  必须明确指定字体为UTF-8，避免中文乱码
                        messageBodyPart.setContent(htmlMsg, "text/html;charset=utf-8;");
                        multipart.addBodyPart(messageBodyPart);
                       //  添加图片
                        MimeBodyPart imagePart = new MimeBodyPart();
                        DataSource fds = new FileDataSource("logo.jpg");
                        imagePart.setDataHandler(new DataHandler(fds));
                        //  设置ID
                        imagePart.setHeader("Content-ID","<logoimg>");
                        imagePart.setHeader("Content-Type", "image/png");
                        imagePart.setDisposition(MimeBodyPart.INLINE);
                        imagePart.setFileName("logoimg.jpg");
                        //  添加内容
                        multipart.addBodyPart(imagePart);
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

        public static void main(String args[]) {

            try {
                // sendMail("1475249187@qq.com","测试邮件",readHtml(),false);
                sendHtmlWithInnerImageEmail();
            } catch (Exception e) {
                e.printStackTrace();
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
                return resultString;
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e1) {
                    }
                }
            }
            return resultString;
    }

    public static void sendHtmlWithInnerImageEmail() throws Exception {
        Properties properties = new Properties();
        properties.put("mail.transport.protocol", "smtp");
        properties.put("mail.smtp.host", "smtp.163.com");
        properties.put("mail.smtp.auth", true);
        properties.put("mail.smtp.ssl.enable", true);
        properties.put("mail.smtp.socketFactory.fallback", false);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.socketFactory.port", "465");

        MyAuthenticator myAuthenticator = new MyAuthenticator();
        Session session = Session.getInstance( properties, myAuthenticator);

        MimeMessage message = createMimeMessage(session);

        Transport transport = session.getTransport();
        transport.connect();;
        transport.sendMessage(message, message.getRecipients(RecipientType.TO));
        transport.close();
    }

    public static MimeMessage createMimeMessage(Session session) throws Exception{
        // 创建邮件内容
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress("shuaipitcc@163.com"));
        message.setRecipients(RecipientType.TO, InternetAddress.parse("1475249187@qq.com"));
        message.setSubject("测试图片邮件");

        MimeMultipart multipart = new MimeMultipart();
        MimeBodyPart mimeBodyPart = createMimeMessageBoDy(session);
        multipart.addBodyPart(mimeBodyPart);

        message.setContent(multipart);
        message.saveChanges();

        return message;
    }

    public static MimeBodyPart createMimeMessageBoDy(Session session) throws Exception {
            MimeBodyPart content = new MimeBodyPart();
            MimeMultipart multipart = new MimeMultipart("alternative");

            MimeBodyPart bodyPart = new MimeBodyPart();
            bodyPart.setContent(readHtml(), "text/html;charset=UTF-8");
            multipart.addBodyPart(bodyPart);

            MimeBodyPart imgPart = new MimeBodyPart();
            FileDataSource fds = new FileDataSource("D:\\study\\idea\\springboot_monster\\src\\main\\java\\com\\monster\\utils\\logo.jpg");
            imgPart.setDataHandler(new DataHandler(fds));
            imgPart.setContentID("logo.jpg");
            multipart.addBodyPart(imgPart);

            content.setContent(multipart);

            return content;
    }

    /**
     * 向邮件服务器提交认证信息
     */
    static class MyAuthenticator extends Authenticator {

        private String username = "shuaipitcc@163.com";

        private String password = "ty203322";

        public MyAuthenticator() {
            super();
        }

        public MyAuthenticator(String username, String password) {
            super();
            this.username = username;
            this.password = password;
        }

        @Override
        protected PasswordAuthentication getPasswordAuthentication() {

            return new PasswordAuthentication(username, password);
        }
    }
}
