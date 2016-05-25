package br.unipe.cc.mlpIII.modelo;

import java.util.Properties;

public class EmailSettings {
	private String host;
	private int port;
	private String username;
	private String password;
	private Properties props;
	
	//Constructors
	public EmailSettings(){
		
	}

	public EmailSettings(String host, int port, String username, String password) {
		this.host = host;
		this.port = port;
		this.username = username;
		this.password = password;

		this.configureProperties();
	}

	//Methods
	private void configureProperties(){
		this.props = new Properties();
        this.props.put("mail.smtp.host", this.host);
        this.props.put("mail.smtp.socketFactory.port", this.port);
        this.props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        this.props.put("mail.smtp.auth", "true");
        this.props.put("mail.smtp.port", this.port);
	}
	
	//Get's and set's methods
	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
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

	public Properties getProps() {
		return props;
	}

	public void setProps(Properties props) {
		this.props = props;
	}

	//Override methods
	@Override
	public String toString() {
		return "EmailSettings [host=" + host + ", port=" + port + ", username=" + username + ", password=" + password + ", props=" + props + "]";
	}

}
