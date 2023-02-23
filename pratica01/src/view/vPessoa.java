package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controle.PessoaDAO;
import modelo.Pessoa;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class vPessoa extends JFrame {

	private JPanel contentPane;
	private JTextField tNome;
	private JTextField tCpf;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vPessoa frame = new vPessoa();
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
	public vPessoa() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tCpf = new JTextField();
		tCpf.setBounds(133, 58, 248, 19);
		tCpf.setColumns(10);
		contentPane.add(tCpf);
		
		tNome = new JTextField();
		tNome.setBounds(133, 129, 248, 19);
		contentPane.add(tNome);
		tNome.setColumns(10);
		
		JLabel lbCPF = new JLabel("CPF:");
		lbCPF.setBounds(66, 60, 40, 15);
		contentPane.add(lbCPF);
		
		JLabel lbNome = new JLabel("Nome: ");
		lbNome.setBounds(66, 131, 49, 15);
		contentPane.add(lbNome);
		
		JButton btGravar = new JButton("Gravar");
		btGravar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				PessoaDAO psD = new PessoaDAO();
				Pessoa ps = new Pessoa();
				
				String sNome = tNome.getText();
				String sCpf = String.valueOf(tCpf.getText());		
				Integer sCpfn = Integer.valueOf(sCpf);
				
				ps.setpCpf(sCpfn);
				ps.setpNome(sNome);
				
				psD.inserir(ps);
				
			}
		});
		btGravar.setBounds(170, 194, 117, 25);
		contentPane.add(btGravar);
	}
}
