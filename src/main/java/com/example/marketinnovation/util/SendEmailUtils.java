package com.example.marketinnovation.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
public class SendEmailUtils {
    private final static Logger logger = LoggerFactory.getLogger(SendEmailUtils.class);

    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private TemplateEngine templateEngine;

    public void thymeleafEmail(String from,String[] to, String subject,EmailParams emailParam,String template) throws MessagingException {
        MimeMessage mimeMessage =javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setFrom(from);
        mimeMessageHelper.setTo(to);
        mimeMessageHelper.setSubject(subject);
        Context ctx = new Context();
        // El contexto de los parámetros para la plantilla
        ctx.setVariable("emailParam", emailParam);
        // Motor de plantillas de ejecución, el motor de plantillas de ejecución debe pasar el nombre de la plantilla y el objeto de contexto
        // La configuración predeterminada de Thymeleaf espera que todos los archivos HTML se coloquen en el directorio ** resources / templates **, terminando en la extensión .html.
        // String emailText = templateEngine.process("email/template", ctx);
        // La ruta final de emailText es: ** resources / templates / XXX.html, el valor de templatePath reemplaza XXX
        String emailText = templateEngine.process(template, ctx);
        mimeMessageHelper.setText(emailText, true);
        // Agregue un archivo adjunto, el primer parámetro indica el nombre del archivo adjunto agregado al correo electrónico, el segundo parámetro es el recurso de imagen
        // FileSystemResource logoImage= new FileSystemResource("D:\\image\\logo.jpg");
        //FileSystemResource logoImage = new FileSystemResource(imagePath);
        // Imagen general llama a este método
        //mimeMessageHelper.addInline("logoImage");
        // Los archivos adjuntos generales llaman a este método
        //mimeMessageHelper.addAttachment("logoImage", logoImage);
        javaMailSender.send(mimeMessage);

    }
}
