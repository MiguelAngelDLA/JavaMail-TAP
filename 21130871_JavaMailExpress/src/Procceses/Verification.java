/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Procceses;

import GUI.ErrorPane;
import GUI.MainFrame;
import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;


/**
 *
 * @author migue
 */
public final class Verification {
    
    String username;
    String password;
    Properties properties; 
    Session session;
    MimeMessage msg;
    public Verification(){
        properties = new Properties();
        
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        properties.setProperty("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.smtp.port", "587");
        properties.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
        properties.setProperty("mail.smtp.auth", "true");
        
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public MimeMessage getMsg() {
        return msg;
    }

    public void setMsg(MimeMessage msg) {
        this.msg = msg;
    }
    
    
    public void startSession(){
        properties.setProperty("mail.smtp.user",this.username);
        this.session = Session.getInstance(properties, null);
        try{
            Transport mTransport = session.getTransport("smtp");
            mTransport.connect(this.username, this.password);
            MainFrame mainFrame = new MainFrame();
            mainFrame.setVisible(true);
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
            ErrorPane error = new ErrorPane();
            error.setVisible(true);
        }
    }

}
