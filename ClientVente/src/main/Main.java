package main;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Main {
	
	private final static String DB_NAME = "zone_preparation_db";
	private final static String SRC_Vente_TB_NAME = "SRC_Vente";
	private final static String SAS_Vente_TB_NAME = "SAS_Vente";
	private final static String REJET_Vente = "REJET_Vente";
	
	private final static String TABLE_SRC_Vente_CREATER_STRING = "CREATE TABLE  SRC_Vente ("+
			"idSrc INT AUTO_INCREMENT PRIMARY KEY," + 
			"CodeClient VARCHAR(255)," + 
			"CodeProduit VARCHAR(255)," + 
			"DateVente VARCHAR(255)," + 
			"PrixUnitaire VARCHAR(255)," + 
			"Quantite VARCHAR(255));" + 
			"";
	
	private final static String TABLE_SAS_Vente_CREATER_STRING = "CREATE TABLE  SAS_Vente (" +
			"idSas INT AUTO_INCREMENT PRIMARY KEY," + 
			"CodeClient VARCHAR(20)," + 
			"CodeProduit VARCHAR(20)," + 
			"DateVente DATETIME,\r\n" + 
			"PrixUnitaire INT," + 
			"Quantite INT);";
	
	
	private final static String TABLE_REJET_Vente_CREATER_STRING = "CREATE TABLE  REJET_Vente (" +
			"idRejet INT AUTO_INCREMENT PRIMARY KEY," + 
			"CodeClient VARCHAR(200)," + 
			"CodeProduit VARCHAR(200)," + 
			" DateVente VARCHAR(200)," + 
			" PrixUnitaire VARCHAR(200)," + 
			" Quantite VARCHAR(200));";
	

	private static Connection connexion;
	//private static ResultSet globalResultSet;
	private static Statement globalStatement;
	
	private static boolean openConnectionToDb() {
		try {
            Class.forName("com.mysql.jdbc.Driver" );
        }catch (Exception e) {
            return false;
        }
        try {
            connexion = DriverManager.getConnection("jdbc:mysql://localhost/"+DB_NAME, "root", "");
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
	}
	
	private static boolean closeConnectionToBd(){
        try {
            connexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
	
	
	public static boolean createDb() {
        openConnectionToDb();
        boolean succededQueryForSRC_VenteTable = dbExecuter(TABLE_SRC_Vente_CREATER_STRING);
        boolean succededQueryForSAS_VenteTable = false;
        if(succededQueryForSRC_VenteTable) succededQueryForSAS_VenteTable = dbExecuter(TABLE_SAS_Vente_CREATER_STRING);
        boolean succededQueryForREJET_VenteTable = false;
        if(succededQueryForSAS_VenteTable) succededQueryForREJET_VenteTable = dbExecuter(TABLE_REJET_Vente_CREATER_STRING);
        closeConnectionToBd();
        if(succededQueryForREJET_VenteTable) {
        	return true;
        }
        return false;
	}
	
	private static boolean dbExecuter(String queryStr) {
		try {
			
            Statement statement = connexion.createStatement();
            statement.execute(queryStr);
            
            statement.close();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
		return true;
	}
	
	
	private static ResultSet dbQueryExecuter(String queryStr) {
		try {
			
            globalStatement = connexion.createStatement();
            ResultSet result = globalStatement.executeQuery(queryStr);
            return result;
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
	}
	
	public static String getInsertSRC_VenteString(String tableName, String[] lineRecord) {
		return "INSERT INTO "+tableName+" (CodeClient, CodeProduit, DateVente, PrixUnitaire, Quantite) VALUES ("
				+ "'"+lineRecord[0]+"',"
				+ "'"+lineRecord[1]+"',"
				+ "'"+lineRecord[2]+"',"
				+ "'"+lineRecord[3]+"',"
				+ "'"+lineRecord[4]+"')";

	}
	
	
	public static String getInsertSAS_VenteString(String tableName, ArrayList<Vente> ventes) {
		String reqStr = "";
		for (int i=0; i<ventes.size(); i++) {
			Vente v = ventes.get(i);
			reqStr = reqStr
					+"('"+v.getCodeClient()+"',"
					+ "'"+v.getCodeProduit()+"',"
					+ "'"+v.getDateVente()+"',"
					     +v.getPrixUnitaire()+","
					     +v.getQuantite()+")";
			if(i < (ventes.size()-1)) reqStr = reqStr+",";
		}
		return "INSERT INTO "+tableName+" (CodeClient, CodeProduit, DateVente, PrixUnitaire, Quantite) VALUES "+reqStr;
	}
	
	
	public static String getInsertREJET_VenteString(String tableName, ArrayList<Vente> ventes) {
		String reqStr = "";
		for (int i=0; i<ventes.size(); i++) {
			Vente v = ventes.get(i);
			reqStr = reqStr
					+ "('"+v.getCodeClient()+"',"
					+ "'"+v.getCodeProduit()+"',"
					+ "'"+v.getDateVente()+"',"
					+ "'"+v.getPrixUnitaire()+"',"
					+ "'"+v.getQuantite()+"')";
			if(i < (ventes.size()-1)) reqStr = reqStr+",";
		}
		return "INSERT INTO "+tableName+" (CodeClient, CodeProduit, DateVente, PrixUnitaire, Quantite) VALUES "+reqStr;
	}
	
	
	
	private static void dumRecords(ArrayList<String[]> records) {
		openConnectionToDb();
		for (String[] record : records) {
			String insertString = getInsertSRC_VenteString(SRC_Vente_TB_NAME, record);
			dbExecuter(insertString);
		}
		closeConnectionToBd();
	}
	
	
	private static ArrayList<Vente> pullVentes() {
		String reqString = "SELECT * FROM "+SRC_Vente_TB_NAME;
		ArrayList<Vente> ventes = null;
		openConnectionToDb();
		ResultSet resultSet = dbQueryExecuter(reqString);
		if (resultSet != null) {
			ventes = new ArrayList<Vente>();
			
			try {
				while(resultSet.next()) {
					Vente v = new Vente(
							resultSet.getString("CodeClient"),
							resultSet.getString("CodeProduit"),
							resultSet.getString("DateVente"),
							resultSet.getString("PrixUnitaire"),
							resultSet.getString("Quantite"));
					ventes.add(v);
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
				
			}
			
		}

		try {
			resultSet.close();
			globalStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		closeConnectionToBd();
		
		return ventes;
	}
	
	
	public static void uploadVentesIntoDB(){
		CsvUtils ventes = null;
		try {
			ventes = new CsvUtils("C:\\Users\\thier\\Downloads\\Ventes.csv", 5);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		ArrayList<String[]> records = ventes.getRecords();
		dumRecords(records);

	}
	
	public static void dispatchData(){
		ArrayList<Vente> ventes = pullVentes();
		ArrayList<Vente> ventesSAS = new ArrayList<Vente>();
		ArrayList<Vente> ventesRejet = new ArrayList<Vente>();
		System.out.println(ventes.size());
		int ok = 0;
		for (Vente vente : ventes) {
			if(valideVente(vente)) {
				vente.setDateVente(converteDate(vente.getDateVente()));
				ventesSAS.add(vente);
				ok++;
			}else {
				ventesRejet.add(vente);
			}
		}
		
		
		String insertInSASStrReq = getInsertSAS_VenteString(SAS_Vente_TB_NAME, ventesSAS);
		String insertInRejetStrReq = getInsertREJET_VenteString(REJET_Vente, ventesRejet);
		openConnectionToDb();
		dbExecuter(insertInSASStrReq);
		dbExecuter(insertInRejetStrReq);
		closeConnectionToBd();
		System.out.println("ok = "+ok);
	}
	
	
	
	
	
	private static String converteDate(String dateVente) {
		String[] beginDateArray = dateVente.split("/");
		String[] endDateArray = beginDateArray[2].split(" ");
		return endDateArray[0]+"-"+beginDateArray[1]+"-"+beginDateArray[0]+" "+endDateArray[1];
	}

	private static boolean valideVente(Vente vente) {
		return valideCodeClient(vente.getCodeClient())
				&& valideCodeProduit(vente.getCodeProduit())
				&& valideDateVente(vente.getDateVente())
				&& validePrixUnitaire(vente.getPrixUnitaire())
				&& valideQuantite(vente.getQuantite());
	}

	

	private static boolean valideQuantite(String quantite) {
		long result;
		try {
			result = Integer.parseInt(quantite);
		} catch (Exception e) {
			return false;
		}
		if (result>Integer.MAX_VALUE || result<=0) return false;
		
		return true;
	}
	
	

	private static boolean validePrixUnitaire(String prixUnitaire) {
		long result;
		try {
			result = Integer.parseInt(prixUnitaire);
		} catch (Exception e) {
			return false;
		}
		if (result>Integer.MAX_VALUE || result<=0) return false;
		
		return true;
	}

	private static boolean valideCodeClient(String codeClient) {
		return codeClient.length()<=20;
	}
	
	private static boolean valideCodeProduit(String codeProduit) {
		return codeProduit.length()<=20;
	}

	private static boolean valideDateVente(String dateVente) {

	    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/uuuu HH:mm");
	    try{
            LocalDate localDate = LocalDate.parse( dateVente , dateFormatter ) ;
            return true;
        } catch ( DateTimeParseException e ) {
            return false; 
        }
		
	}
	
	
	

	public static void main(String[] args) {
		createDb();
//		
		uploadVentesIntoDB();
//		
		dispatchData();
		
	}

	

}
