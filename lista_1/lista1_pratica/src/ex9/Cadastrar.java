package ex9;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import ex10.Consultar;

public class Cadastrar extends JFrame {

	private JPanel contentPane;
	private JTextField edMatricula;
	private JTextField edNome;
	private JTextField edIdade;
	Connection conexao;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cadastrar frame = new Cadastrar();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Cadastrar() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 446, 216);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btCons = new JButton("Consultar");
		btCons.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Consultar cons;
				try {
					cons = new Consultar();
					cons.setLocationRelativeTo(null);
					cons.setVisible(true);
					dispose();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
			}
		});
		btCons.setBounds(255, 120, 126, 23);
		contentPane.add(btCons);
		
		JButton btCad = new JButton("Cadastro");
		btCad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {

					conexao = DriverManager.getConnection("jdbc:mysql://localhost/" + "aluno", "root", "");

					String wSQL = "INSERT INTO matricula(matricula,nome_aluno,idade_aluno) VALUES (?,?,?)";
					PreparedStatement stm = conexao.prepareStatement(wSQL);

					Integer matricula = Integer.valueOf(edMatricula.getText());
					Integer idade = Integer.valueOf(edIdade.getText());
					stm.setInt(1, matricula);
					stm.setString(2, edNome.getText());
					stm.setInt(3, idade);

					stm.executeUpdate();

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				edMatricula.setText("");
				edIdade.setText("");
				edNome.setText("");
			}
		});
		btCad.setBounds(94, 121, 126, 23);
		contentPane.add(btCad);

		edMatricula = new JTextField();
		edMatricula.setBounds(155, 27, 173, 20);
		contentPane.add(edMatricula);
		edMatricula.setColumns(10);

		edNome = new JTextField();
		edNome.setColumns(10);
		edNome.setBounds(155, 58, 173, 20);
		contentPane.add(edNome);

		edIdade = new JTextField();
		edIdade.setColumns(10);
		edIdade.setBounds(155, 89, 173, 20);
		contentPane.add(edIdade);

		JLabel lbMatricula = new JLabel("Matricula: ");
		lbMatricula.setHorizontalAlignment(SwingConstants.RIGHT);
		lbMatricula.setBounds(70, 30, 75, 14);
		contentPane.add(lbMatricula);

		JLabel lbNome = new JLabel("Nome:");
		lbNome.setHorizontalAlignment(SwingConstants.RIGHT);
		lbNome.setBounds(84, 61, 46, 14);
		contentPane.add(lbNome);

		JLabel lbIdade = new JLabel("Idade:");
		lbIdade.setHorizontalAlignment(SwingConstants.RIGHT);
		lbIdade.setBounds(84, 92, 46, 14);
		contentPane.add(lbIdade);
		
	}
}
