package br.unipe.cc.mlpIII.repositorio;

import java.sql.*;
import java.util.List;

import br.unipe.cc.mlpIII.util.ErroLog;

public class DataBase {
	private DataBaseSettings settings;
	private Connection dbConnection;
	private Statement dbStatement;
	private ResultSet dbResultSet;

	//Constructor
	public DataBase(){
		this.settings = new DataBaseSettings();
	}

	//Methods
	public void openConnection(){

		try {
			Class.forName("com.mysql.jdbc.Driver");
			this.dbConnection = DriverManager.getConnection("jdbc:mysql://" + this.settings.getHost() + ":" + this.settings.getPort() + "/" + this.settings.getData(), this.settings.getUser(), this.settings.getPass());
			this.dbStatement = dbConnection.createStatement();
		} catch (SQLException e) {
			ErroLog.gravarErroLog(e.toString(), e.getStackTrace());
		} catch (ClassNotFoundException e) {
			ErroLog.gravarErroLog(e.toString(), e.getStackTrace());
		}
		
	}
	
	public void closeConnection(){

		try {
			this.dbConnection.close();
			this.dbStatement.close();
		} catch (SQLException e) {
			ErroLog.gravarErroLog(e.toString(), e.getStackTrace());
		}
		
		close();
	}

	public void close(){
		
		this.settings = null;
		this.dbConnection = null;
		this.dbStatement = null;
		this.dbResultSet = null;

	}
	
	public ResultSet query(String stringQuery){
		ResultSet resultSetQuery = null;
		
		try {
			resultSetQuery = this.dbStatement.executeQuery(stringQuery);
		} catch (SQLException e) {
			ErroLog.gravarErroLog(e.toString(), e.getStackTrace());
		}
		
		this.dbResultSet = resultSetQuery;
		return resultSetQuery;
	}
	
	public void execute(String stringQuery){
		try {
			this.dbStatement.execute(stringQuery);
		} catch (SQLException e) {
			ErroLog.gravarErroLog(e.toString(), e.getStackTrace());
		}
	}
	
	public void insert(String table, List<String> fields, List<String> values){
		int f = 0, v = 0;
		String sInsertFields = "";
		String sInsertValues = "";

		if (fields.isEmpty() || values.isEmpty() || table.isEmpty() || (fields.size() != values.size())){
			//TODO: Gerar mensagem de que não foi possível inserir os dados no banco.
		} else {
			for	(String	field : fields) sInsertFields += (f++ == 0 ? "" : ",") + field;
			for	(String	value : values) sInsertValues += (v++ == 0 ? "" : ",") + value;

			try {
				this.dbStatement.executeQuery("INSERT INTO " + table + "(" + sInsertFields + ") VALUES (" + sInsertValues + ")");
			} catch (SQLException e) {
				ErroLog.gravarErroLog(e.toString(), e.getStackTrace());
			}
		}
	}

	public void update(String table, List<String> fields, List<String> values, String condition){
		String sUpdates = ""; 
		String[] aInsertFields;
		String[] aInsertValues;
		
		if (fields.isEmpty() || values.isEmpty() || table.isEmpty() || (fields.size() != values.size())){
			//TODO: Gerar mensagem de que não foi possível inserir os dados no banco.
		} else {
			aInsertFields = (String[]) fields.toArray();
			aInsertValues = (String[]) values.toArray();
			
			for(int i = 0; i < aInsertFields.length; i++)
				sUpdates += (i == 0 ? "" : ",") + aInsertFields[i] + " = " + aInsertValues[i];
			
			try {
				this.dbStatement.execute("UPDATE " + table + " SET " + sUpdates + (condition.isEmpty() ? "" : " WHERE " + condition));
			} catch (SQLException e) {
				ErroLog.gravarErroLog(e.toString(), e.getStackTrace());
			}
		}
	}
	
	public void delete(String table, String condition){
		if (!condition.isEmpty()){
			try {
				this.dbStatement.execute("DELETE FROM " + table + " WHERE " + condition);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			//TODO: Mensagem do else
		}
	}
	
	//Get's and set's methods
	public DataBaseSettings getSettings() {
		return settings;
	}

	public void setSettings(DataBaseSettings settings) {
		this.settings = settings;
	}

	public Connection getDbConnection() {
		return dbConnection;
	}

	public void setDbConnection(Connection dbConnection) {
		this.dbConnection = dbConnection;
	}

	public Statement getDbStatement() {
		return dbStatement;
	}

	public void setDbStatement(Statement dbStatement) {
		this.dbStatement = dbStatement;
	}

	public ResultSet getDbResultSet() {
		return dbResultSet;
	}

	public void setDbResultSet(ResultSet dbResultSet) {
		this.dbResultSet = dbResultSet;
	}
	
	//Override methods
	
}
