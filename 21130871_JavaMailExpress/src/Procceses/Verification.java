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
import java.util.regex.Pattern;
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
    
    /** getters y setters*/
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
    
    /**
     * Checa si el correo proveido es valido o no,
     * a través de la expresión dada por el 
     * OWASP Validation Regex repository, regex es una 
     * secuencia de caracteres que conforman un patrón.
     * Para más información sobre regex checar:
     * https://cheatsheetseries.owasp.org/cheatsheets/Input_Validation_Cheat_Sheet.html
     * 
     * TODO: Agregar EmailChecker para más seguridad
     * @param email el email para checar su validez
     * @return si el email es valido o no
     */
    public static boolean isEmailValid(String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                            "[a-zA-Z0-9_+&*-]+)*@" +
                            "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                            "A-Z]{2,7}$";
                              
        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }
    
    /**
     * Método para iniciar la sesión dentro del login, utiliza el
     * correo y contraseña proveidos por el usuario para conectarse con
     * la cuenta, si la cuenta y contraseña no coinciden lanza una excepción.
     */
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
