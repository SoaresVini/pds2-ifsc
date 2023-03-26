package ex10;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import ex9.Cadastrar;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Consultar extends JFrame {

	private JPanel contentPane;
	Connection conexao;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Consultar frame = new Consultar();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	
	public Consultar() throws SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(70, 12, 325, 160);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		DefaultTableModel modelo = new DefaultTableModel(new Object[][] {},
				new String[] { "Nome", "Idade", "Matricula"});
		
		ArrayList<Pessoa> List = new ArrayList<>();
	    
		conexao = DriverManager.getConnection("jdbc:mysql://localhost/" + "aluno", "root", "");
		
			String wSql = "SELECT * FROM matricula";
			Statement stm = conexao.prepareStatement(wSql);
			ResultSet rs = stm.executeQuery(wSql);
			
			
			while (rs.next()) {
				
				int matricula = rs.getInt("matricula");
				int idade = rs.getInt("idade_aluno");
				String Nome = rs.getString("nome_aluno");
				
				Pessoa p = new Pessoa(Nome, idade, matricula);
				
				List.add(p);
			}

			for (Pessoa pessoa : List) {
				modelo.addRow(new Object[] {
						pessoa.getNome(), pessoa.getIdade(), pessoa.getMatricula()});
			};
				
			table.setModel(modelo);
			scrollPane.setViewportView(table);
			
			JButton btnNewButton = new JButton("Cadastrar");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					Cadastrar cad = new Cadastrar();
					cad.setLocationRelativeTo(null);
					cad.setVisible(true);
					dispose();
					
				}
			});
			btnNewButton.setBounds(161, 200, 117, 25);
			contentPane.add(btnNewButton);
			
			
	}


	public class Pessoa {
		
		private String nome;
	    private int idade;
	    private int matricula;
	    
		public Pessoa(String nome, int idade, int matricula) {
			super();
			this.nome = nome;
			this.idade = idade;
			this.matricula = matricula;
		}

		public String getNome() {
			return nome;
		}

		public void setNome(String nome) {
			this.nome = nome;
		}

		public int getIdade() {
			return idade;
		}

		public void setIdade(int idade) {
			this.idade = idade;
		}

		public int getMatricula() {
			return matricula;
		}

		public void setMatricula(int matricula) {
			this.matricula = matricula;
		}

	    
		
	}
}
