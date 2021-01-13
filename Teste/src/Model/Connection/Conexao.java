package Model.Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
public Connection getConnection() {
		
		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			System.out.println("Banco Conectado");
			return DriverManager.getConnection("jdbc:mysql://localhost/projetos", "root", ""); //Colocar local e nome da base de dados no 1º argumento
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
