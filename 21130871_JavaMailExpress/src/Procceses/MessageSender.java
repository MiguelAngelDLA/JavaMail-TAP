/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Procceses;

import static Procceses.Verification.mTransport;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JOptionPane;

/**
 *
 * @author migue
 */
public class MessageSender {
    ArrayList<String> correosPara, correosCC, correosCCO;
    ArrayList<File> pathFiles;
    String asunto, mensajePrincipal;
    MimeMessage mime;
    
    public MessageSender(){
        this.correosPara = new ArrayList();
        this.correosCC = new ArrayList();
        this.correosCCO = new ArrayList();
        this.pathFiles = new ArrayList();
        
        this.mime = new MimeMessage(Verification.session);
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getMensajePrincipal() {
        return mensajePrincipal;
    }

    public void setMensajePrincipal(String mensajePrincipal) {
        this.mensajePrincipal = mensajePrincipal;
    }
    
    
    public void addPara(String para){
        correosPara.add(para);
    }
    
    public void addCC(String cc){
        correosCC.add(cc);
    }
    
    public void addCCO(String cco){
        correosCCO.add(cco);
    }
    
    public void addPathFiles(File path){
        pathFiles.add(path);
    }
    
    public void enviarMensaje(){
        try{
            InternetAddress desde = new InternetAddress(Verification.username);
            mime.setFrom(desde);
            for(int i = 0; i < correosPara.size(); i++)
                mime.addRecipient(Message.RecipientType.TO, new InternetAddress(correosPara.get(i)));
 
            if(!correosCC.isEmpty()) for(int i = 0; i < correosCC.size(); i++)
                    mime.addRecipient(Message.RecipientType.CC, new InternetAddress(correosCC.get(i)));

            if(!correosCCO.isEmpty())for(int i = 0; i < correosCCO.size(); i++)
                mime.addRecipient(Message.RecipientType.BCC, new InternetAddress(correosCCO.get(i)));
            mime.setSubject(asunto);
            Multipart multipart = new MimeMultipart();

            // Set text message part

            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(this.getMensajePrincipal());
            multipart.addBodyPart(messageBodyPart);
            

            if(!pathFiles.isEmpty())for(int i = 0; i < pathFiles.size(); i++){
                MimeBodyPart attachments = new MimeBodyPart();
		attachments.attachFile(pathFiles.get(i));
                multipart.addBodyPart(attachments);
            }
            
            mime.setContent(multipart);
            
            mTransport.connect(Verification.username, Verification.password);

            Verification.mTransport.sendMessage(mime, 
                    mime.getAllRecipients());
            
            mTransport.close();
            
            JOptionPane.showMessageDialog(null, "Mensaje enviado con exito!");
        }catch(AddressException e){
            JOptionPane.showMessageDialog(null, e);
        }catch(MessagingException e){
            JOptionPane.showMessageDialog(null, e);
        }catch(IOException e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
}
