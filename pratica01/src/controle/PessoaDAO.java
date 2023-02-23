package controle;

import java.awt.image.RescaleOp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.spi.DirStateFactory.Result;

import com.mysql.cj.protocol.NamedPipeSocketFactory;

import modelo.Pessoa;

public class PessoaDAO {
	
	
	private static final Pessoa[] TablePessoa = null;
	private static Conexao conect;
	
	// create
	public Boolean inserir(Pessoa p) {

			// instanciar 
			conect = Conexao.getInstancia();
			
			//abre
			
			Connection c = conect.conectar();
			
			try {
				String query = "insert into pessoa(cpf, nome) values (?, ?);";
				PreparedStatement stm = c.prepareStatement(query);
				
				stm.setInt(1, p.getpCpf());
				stm.setString(2, p.getpNome());
			
				stm.executeUpdate();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			//fechar
			conect.fecharConexao();
			
			return true;
	}
	
	//UPDATE
	public Boolean alterar(Pessoa p) {
			

		return null;
		
		}

	
	//DELETE
	public Boolean deletar(Pessoa p) {
		for (Pessoa p1 : TablePessoa) {
			
				return true;
			}
		return false;
	}
	
	//SELECT
	public ArrayList<Pessoa> listaPessoa(){
		
		ArrayList<Pessoa> pessoas = new ArrayList<>();
		
		
		// instanciar 
		conect = Conexao.getInstancia();
					
		//abre
					
		Connection c = conect.conectar();
					
		try {
			Statement stm = c.createStatement();
			String query = "Select * from pessoa";
			ResultSet rs = stm.executeQuery(query);
			
			while (rs.next()) {
				int cpf = rs.getInt("cpf");
				String nome = rs.getString("nome");
				Pessoa p = new Pessoa();
				p.setpCpf(cpf);
				p.setpNome(nome);
				pessoas.add(p);
			}
			
						
		} catch (Exception e) {
					e.printStackTrace();
			}
					
		//fechar
		conect.fecharConexao();
					
		return pessoas;
	}

	

	

}
