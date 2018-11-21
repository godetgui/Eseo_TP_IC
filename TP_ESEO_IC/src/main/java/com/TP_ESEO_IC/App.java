package com.TP_ESEO_IC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class App{ /* Connexion à la base de données */
	String url = "jdbc:mysql://localhost/VillesFrance?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	String driver = "com.mysql.cj.jdbc.Driver";
	String utilisateur = "user";
	String motDePasse = "user";
	Connection connexion = null;
	Statement statement = null;
	ResultSet resultat = null;

    public static void main( String[] args ) throws IOException, SQLException
    {
        App app = new App();
        app.readCSV("sources/laposte_hexasmal.csv");
    }
    
    
    
    public void readCSV(String pathFile) throws IOException, SQLException {
    		BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ";";
        
        /* Chargement du driver JDBC pour MySQL */
		try {
		    Class.forName( driver );
		} catch ( ClassNotFoundException e ) {
		    /* Gérer les éventuelles erreurs ici. */
		}
		
        connexion = DriverManager.getConnection( url, utilisateur, motDePasse );
	    statement = connexion.createStatement();
        
        br = new BufferedReader(new FileReader(pathFile));
        while ((line = br.readLine()) != null) {
            String[] cities = line.split(cvsSplitBy);

            System.out.println("INSERT INTO `ville_france`(`Code_commune_INSEE`, `Nom_commune`) VALUES('"+cities[0]+"','"+cities[1]+");");                
            statement.executeUpdate("INSERT INTO `ville_france`(`Code_commune_INSEE`, `Nom_commune`) VALUES('"+cities[0]+"','"+cities[1]+"');");                
        }
        
        if (br != null) {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        
    		
    		
    		
    	
    }
    
    
}
