/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Procceses;

import GUI.ErrorPane;
import GUI.MainFrame;
import java.util.Properties;
import java.util.regex.Pattern;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;


/**
 *
 * @author migue
 */
public class Verification {
    
    static String username;
    static String password;
    static Properties properties; 
    static Session session;
    static Transport mTransport;
    
    public Verification(){
                
        properties = new Properties();
        
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


    public Transport getmTransport() {
        return mTransport;
    }
    
    public String getDomain(String email){
        return email.substring(email.indexOf('@') + 1);
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
     * 
     */
    public void startSession(){
        switch(getDomain(username)){
            case "gmail.com":
                properties.put("mail.smtp.host", "smtp.gmail.com");
                properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
                break;
            case "outlook.com":
                properties.put("mail.smtp.host", "smtp.office365.com");
                properties.put("mail.smtp.ssl.trust", "smtp.office365.com");
                break;
            case "hotmail.com":
                properties.put("mail.smtp.host", "smtp.office365.com");
                properties.put("mail.smtp.ssl.trust", "smtp.office365.com");
                break;
            case "aol.com":
                properties.put("mail.smtp.host", "smtp.aol.com");
                properties.put("mail.smtp.ssl.trust", "smtp.aol.com");
            default:
                try{
                    properties.put("mail.smtp.host", "smtp." + getDomain(username));
                    properties.put("mail.smtp.ssl.trust", "smtp." + getDomain(username));
                }
                catch(Exception e){
                    JOptionPane.showMessageDialog(null, "Correo todavía no soportado, contactese con servicios");
                }
        }
        properties.setProperty("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.smtp.port", "587");
        properties.setProperty("mail.smtp.user",username);
        properties.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
        properties.setProperty("mail.smtp.auth", "true");
        
        session = Session.getInstance(properties, null);
        try{
            mTransport = session.getTransport("smtp");
            mTransport.connect(username, password);
            mTransport.close();
            MainFrame mainFrame = new MainFrame();
            mainFrame.setVisible(true);
            
        }catch(Exception e){
            ErrorPane error = new ErrorPane();
            error.setVisible(true);
        }
    }

}
