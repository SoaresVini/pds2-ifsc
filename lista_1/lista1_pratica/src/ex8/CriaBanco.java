package ex8;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CriaBanco {

	public static void main(String[] args) {

		try {
			Connection conexao =  DriverManager.getConnection("jdbc:mysql://localhost/","root","aluno");
		
			Statement stm = conexao.createStatement();
			String wQuery = "CREATE DATABASE aluno";
			stm.executeUpdate(wQuery);	
			
			wQuery = "USE aluno";
			stm.executeUpdate(wQuery);
			
			wQuery = "CREATE TABLE matricula ("
					+ "  matricula int primary key,"
					+ "  nome_aluno varchar(40),"
					+ "  idade_aluno int)";
			stm.executeUpdate(wQuery);	
		

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	
	}

}
