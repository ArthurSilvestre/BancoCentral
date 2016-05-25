package br.unipe.cc.mlpIII.repositorio;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class DataBaseSettings {
	Map<String, String> dataBaseSettingsMap;
	private String host;
	private String port;
	private String user;
	private String pass;
	private String data;
	
	//Constructor
	public DataBaseSettings() {
		fillDataBaseSettingsMap();
		this.host = dataBaseSettingsMap.get("host");
		this.port = dataBaseSettingsMap.get("port");
		this.user = dataBaseSettingsMap.get("user");
		this.pass = dataBaseSettingsMap.get("pass");
		this.data = dataBaseSettingsMap.get("data");
	}
	
	//Methods
	private void fillDataBaseSettingsMap(){
		this.dataBaseSettingsMap = new LinkedHashMap<String, String>();
		FileReader fileDataBaseSettings;
		BufferedReader bufferDataBaseSettings;
		String linhaFileDataBaseSettings;
		StringTokenizer stringTokenizerDataBaseSettings;
		
		try {
			
			fileDataBaseSettings = new FileReader("database\\DataBase.settings");
			bufferDataBaseSettings = new BufferedReader(fileDataBaseSettings);
			linhaFileDataBaseSettings = bufferDataBaseSettings.readLine();

			while (linhaFileDataBaseSettings != null) {
				stringTokenizerDataBaseSettings = new StringTokenizer(linhaFileDataBaseSettings);
				
				this.dataBaseSettingsMap.put(stringTokenizerDataBaseSettings.nextToken("="), stringTokenizerDataBaseSettings.nextToken());
			
				linhaFileDataBaseSettings = bufferDataBaseSettings.readLine();
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace(); //TODO: Mensagem do catch
		} catch (IOException e) {
			e.printStackTrace(); //TODO: Mensagem do catch
		}
	}
	
	//Get's and set's methods
	public String getHost() {
		return host;
	}
	
	public void setHost(String host) {
		this.host = host;
	}
	
	public String getPort() {
		return port;
	}
	
	public void setPort(String port) {
		this.port = port;
	}
	
	public String getUser() {
		return user;
	}
	
	public void setUser(String user) {
		this.user = user;
	}
	
	public String getPass() {
		return pass;
	}
	
	public void setPass(String pass) {
		this.pass = pass;
	}
	
	public String getData() {
		return data;
	}
	
	public void setData(String data) {
		this.data = data;
	}

	//Override methods
	@Override
	public String toString() {
		return "DataBaseSettings [host=" + host + ", port=" + port + ", user=" + user + ", pass=" + pass + ", data=" + data + "]";
	}
	
}
