/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Procceses;

import java.util.ArrayList;
import javax.mail.Message;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author migue
 */
public class MessageSender {
    ArrayList<String> correosPara, correosCC, correosCCO, pathFiles;
    String asunto, mensajePrincipal;
    MimeMessage mime;
    Verification verification;
    
    public MessageSender(Verification verification){
        this.correosPara = new ArrayList();
        this.correosCC = new ArrayList();
        this.correosCCO = new ArrayList();
        this.pathFiles = new ArrayList();
        
        this.verification = verification;
        this.mime = new MimeMessage(verification.getSession());
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
    
    public void pathFiles(String cco){
        pathFiles.add(cco);
    }
    
    public void enviarMensaje(){
        try {
            mime = new MimeMessage(verification.getSession());
            mime.setFrom(new InternetAddress(verification.getUsername()));
            for(int i = 0; i < correosPara.size(); i++){
                mime.addRecipient(Message.RecipientType.TO, new InternetAddress(correosPara.get(i)));
            }
            
            for(int i = 0; i < correosCC.size(); i++){
                mime.addRecipient(Message.RecipientType.CC, new InternetAddress(correosCC.get(i)));
            }
            
            for(int i = 0; i < correosCCO.size(); i++){
                mime.addRecipient(Message.RecipientType.BCC, new InternetAddress(correosCCO.get(i)));
            }
            mime.setSubject(asunto);
            mime.setText(mensajePrincipal, "ISO-8859-1", "html");
                     
            
        } catch (Exception ex) {
            
        }
    }
}
