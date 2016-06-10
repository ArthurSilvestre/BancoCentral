package br.unipe.cc.mlpIII.modelo;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;

import br.unipe.cc.mlpIII.util.ErroLog;

public class Email {
    private String from;
    private String to;
    private String subject;
    private String content;
    private Multipart multipart = new MimeMultipart();
    private BodyPart attachment = new MimeBodyPart();
    private BodyPart textBody = new MimeBodyPart();
    private DataSource file = new FileDataSource("erro.log");
	
	//Constructors
    public Email(){
    	
    }
    		
    public Email(String from, String to, String subject, String content, String urlFile) {
		this.from = from;
		this.to = to;
		this.subject = subject;
		this.content = content;
	    this.multipart = new MimeMultipart();
	    this.attachment = new MimeBodyPart();
	    this.file = new FileDataSource(urlFile);
	    try {
			this.attachment.setDataHandler(new DataHandler(this.file));
			this.attachment.setFileName(this.file.getName());
			this.attachment.setDisposition(Part.ATTACHMENT);
			this.textBody.setText(this.content);
			this.multipart.addBodyPart(textBody);
			this.multipart.addBodyPart(this.attachment);
		} catch (MessagingException e1) {
			ErroLog.gravarErroLog(e1.toString(), e1.getStackTrace());
		} 
	}

	//Methods
	//Get's and set's methods
    public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public Multipart getMultipart() {
		return multipart;
	}

	public void setMultipart(Multipart multipart) {
		this.multipart = multipart;
	}

	public BodyPart getAttachment() {
		return attachment;
	}

	public void setAttachment(BodyPart attachment) {
		this.attachment = attachment;
	}

	public DataSource getFile() {
		return file;
	}

	public void setFile(DataSource file) {
		this.file = file;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	//Override methods
	@Override
	public String toString() {
		return "Email [from=" + from + ", to=" + to + ", subject=" + subject + ", content=" + content + "]";
	}
}
