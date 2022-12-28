import com.CodingApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StopWatch;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import java.io.File;
import java.io.UnsupportedEncodingException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CodingApplication.class)
@SpringBootConfiguration
public class MailTest {

    @Autowired
    private JavaMailSender javaMailSender;

    @Test
    public void testSendSimpleMail() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("w18800434802@163.com");
        message.setTo("18800434802@163.com");
        message.setSubject("邮箱开发");
        message.setText("秋林");
        javaMailSender.send(message);
        stopWatch.stop();
        System.out.println("服务耗时" + stopWatch.getTotalTimeSeconds() + "s");
    }

    @Test
    public void testSendHtmlMail() throws MessagingException {
        String context = "<p style=\"background-color:red\"> 我是汪玉龙</p>";
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setFrom("w18800434802@163.com");
        mimeMessageHelper.setTo("18800434802@163.com");
        mimeMessageHelper.setSubject("邮箱开发");
        mimeMessageHelper.setText(context, true);
        javaMailSender.send(mimeMessage);
        stopWatch.stop();
        System.out.println("服务耗时" + stopWatch.getTotalTimeSeconds() + "s");
    }

    @Test
    public void testSendAttachmentMail() throws MessagingException, UnsupportedEncodingException {
        String context = "<p style=\"background-color:red\"> 我是汪玉龙</p>";
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setFrom("w18800434802@163.com");
        mimeMessageHelper.setTo("18800434802@163.com");
        mimeMessageHelper.setSubject("邮箱开发");
        mimeMessageHelper.setText(context, true);
        mimeMessageHelper.addAttachment(MimeUtility.encodeText("文件名称"), new File("C:\\Users\\wyl\\Desktop\\vue.min.js"));
        javaMailSender.send(mimeMessage);
        stopWatch.stop();
        System.out.println("服务耗时" + stopWatch.getTotalTimeSeconds() + "s");
    }
}
