package br.unipe.cc.mlpIII.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import br.unipe.cc.mlpIII.modelo.Email;
import br.unipe.cc.mlpIII.modelo.EmailSettings;

public class ErroLog {
	protected final static Properties properties = System.getProperties();
	protected final static String urlFile = "erro.log";
	protected final static File file =  new File(urlFile);
	protected static FileWriter fileWriter;
	protected static PrintWriter printWriter;
	protected static int currentErroLog = 0;

	//Constructors
	//Methods
	private static void initComponents(){
		try {
			fileWriter = new FileWriter(urlFile, true);
		} catch (IOException e) {
			e.printStackTrace(); //TODO: Mensagem do catch
		}
		printWriter = new PrintWriter(fileWriter);
	}
	
	private static void endComponents(){
		try {
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace(); //Mensagem do catch
		}
		printWriter.close();
	}

	public final static boolean verificaErroLog(){
		return file.exists(); //{ true: Para quando existir erro , false: Para quando não existir erro }
	}

	public final static void apagarErroLog(){
		if (file.isFile()) {
			file.delete();
		} else {
		    //TODO: Mensagem de que não existe arquivo
		}
	}
	
	public static void gravarErroLog(String mensagem, StackTraceElement[] stackTrace){
		initComponents();
		
		currentErroLog++;
		
		printWriter.println(JFunctions.replicate("*", 83));
		printWriter.println(JFunctions.replicate("*", 30)+"Erro - "+JFunctions.strZero(currentErroLog,12)+JFunctions.replicate("*", 30));
		printWriter.println(JFunctions.replicate("*", 83));
		printWriter.println(JFunctions.replicate("*", 30)+"Informações do Erro"+JFunctions.replicate("*", 31));
		printWriter.println(JFunctions.replicate("*", 83));
		printWriter.println("");
		printWriter.println("Data: " + new Date());
		printWriter.println("Mensagem de erro: " + mensagem + "\n");
		printWriter.println("Rastreamento de pilha (StackTrace):\n");
		
		for(int i = 0; i < stackTrace.length; i++)
			printWriter.println("	Passo " + (stackTrace.length - i) + ": " + stackTrace[i]);

		printWriter.println("");
		printWriter.println(JFunctions.replicate("*", 83));
		printWriter.println(JFunctions.replicate("*", 27)+"Informações do dispositivo"+JFunctions.replicate("*", 27));
		printWriter.println(JFunctions.replicate("*", 83));
		printWriter.println("");
		printWriter.println("    " + properties.toString().replace("\n", "").replace(",", "\n   ").replace("{","").replace("}", "").replace("=", ": "));
		printWriter.println("");
		printWriter.print(JFunctions.replicate("*", 83));
		printWriter.println("");
		printWriter.println("");
		printWriter.println("");
		
		endComponents();
	}
	
	public final static boolean enviarErroLog() throws AddressException, MessagingException {
		try {
	        String subject = new String("Banco Central - Erro.log");
	        String content = new String("Segue anexo o arquivo 'erro.log' que contem dados para que o desenvolvedor analise a integridade das devidas rotinas informadas no arquivo.");

	        EmailSettings emailSettings = new EmailSettings("smtp.gmail.com", 465, "arthursilves@gmail.com", "Silverxwk190");
			Email email = new Email("arthursilves@gmail.com", "arthursilves@gmail.com", subject, content, file.getName());

	        Session session = Session.getDefaultInstance(emailSettings.getProps(),
	                new javax.mail.Authenticator() {
	                    @Override
	                    protected PasswordAuthentication getPasswordAuthentication() {
	                        return new PasswordAuthentication(emailSettings.getUsername(), emailSettings.getPassword());
	                    }
	                });

	        Message message = new MimeMessage(session);
	        message.setFrom(new InternetAddress(email.getFrom()));
	        message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(email.getTo()));
	        message.setSubject(email.getSubject());
	        message.setContent(email.getMultipart()); 

	        Transport.send(message);
	
			return true; //Para quando cosneguir enviar o erro.log
		} catch (Exception e){
			return false; //Para quando não cosneguir enviar o erro.log
		}
	}
	
	//Get's and set's methods
	//Override methods
}
