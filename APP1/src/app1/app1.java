package app1;

import java.awt.EventQueue;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.mysql.jdbc.PreparedStatement;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class app1 {

	private JFrame frame;
	private JTextField textNome;
	private JTextField textCognome;
	private JTextField textNato;
	private JTextField textResidente;
	private JTextField textProf;
	private JTable table;
	private JTable table_1;
	private JTextField textFieldNomeCerca;
	private JTextField textFieldCognomeCerca;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					app1 window = new app1();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws SQLException 
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public app1() throws ClassNotFoundException, IOException, SQLException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws SQLException 
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	private void initialize() throws ClassNotFoundException, IOException, SQLException {
		frame = new JFrame();
		frame.setBounds(100, 100, 860, 547);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(21, 11, 771, 486);
		frame.getContentPane().add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("CERCA", null, panel, null);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 746, 436);
		panel.add(scrollPane);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("AGGIUNGI", null, panel_1, null);
		panel_1.setLayout(null);
		
		JLabel lblNome = new JLabel("NOME");
		lblNome.setBounds(26, 35, 46, 14);
		panel_1.add(lblNome);
		
		textNome = new JTextField();
		textNome.setBounds(89, 32, 126, 20);
		panel_1.add(textNome);
		textNome.setColumns(10);
		
		JLabel lblCognome = new JLabel("COGNOME");
		lblCognome.setBounds(26, 100, 86, 14);
		panel_1.add(lblCognome);
		
		textCognome = new JTextField();
		textCognome.setBounds(89, 97, 126, 20);
		panel_1.add(textCognome);
		textCognome.setColumns(10);
		
		JLabel lblNato = new JLabel("NATO ");
		lblNato.setBounds(26, 168, 46, 14);
		panel_1.add(lblNato);
		
		textNato = new JTextField();
		textNato.setBounds(89, 165, 126, 20);
		panel_1.add(textNato);
		textNato.setColumns(10);
		
		JLabel lblResidente = new JLabel("RESIDENTE");
		lblResidente.setBounds(26, 231, 71, 14);
		panel_1.add(lblResidente);
		
		textResidente = new JTextField();
		textResidente.setBounds(89, 228, 126, 20);
		panel_1.add(textResidente);
		textResidente.setColumns(10);
		
		JLabel lblProfessione = new JLabel("PROFESSIONE");
		lblProfessione.setBounds(26, 286, 71, 14);
		panel_1.add(lblProfessione);
		
		textProf = new JTextField();
		textProf.setBounds(113, 283, 126, 20);
		panel_1.add(textProf);
		textProf.setColumns(10);
		
		JButton btnNewButtonAggiungi = new JButton("INVIA");
		btnNewButtonAggiungi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					utente u = new utente();
					u.setNome(textNome.getText());
					u.setCognome(textCognome.getText());
					u.setNato(textNato.getText());
					u.setResidenza(textResidente.getText());
					u.setProfessione(textProf.getText());
					aggiungicontatto(u);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		btnNewButtonAggiungi.setBounds(456, 124, 89, 81);
		panel_1.add(btnNewButtonAggiungi);
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				
			},
			new String[] {
				"ID", "NOME", "COGNOME", "NATO", "RESIDENZA", "PROFESSIONE"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				true, true, true, true, true, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		ArrayList<utente> listacontatti = new ArrayList<utente>();
		listacontatti = cercacontatto();
		for (utente utente : listacontatti) {
			Vector vec = new Vector();
			vec.add(utente.getId());
			vec.add(utente.getNome());
			vec.add(utente.getCognome());
			vec.add(utente.getNato());
			vec.add(utente.getResidenza());
			vec.add(utente.getProfessione());
			
			dtm.addRow(vec);
		}
		scrollPane.setViewportView(table);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("CERCA PER CRITERI", null, panel_2, null);
		panel_2.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(30, 27, 550, 283);
		panel_2.add(scrollPane_1);
		
		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
				
			},
			new String[] {
				"ID", "NOME", "COGNOME", "NATO", "RESIDENZA", "PROFESSIONE"
			}
		));
		
		scrollPane_1.setViewportView(table_1);
		
		JLabel lblNome_1 = new JLabel("NOME");
		lblNome_1.setBounds(30, 334, 46, 14);
		panel_2.add(lblNome_1);
		
		textFieldNomeCerca = new JTextField();
		textFieldNomeCerca.setBounds(86, 331, 120, 20);
		panel_2.add(textFieldNomeCerca);
		textFieldNomeCerca.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("COGNOME");
		lblNewLabel.setBounds(28, 382, 68, 14);
		panel_2.add(lblNewLabel);
		
		textFieldCognomeCerca = new JTextField();
		textFieldCognomeCerca.setBounds(115, 379, 154, 20);
		panel_2.add(textFieldCognomeCerca);
		textFieldCognomeCerca.setColumns(10);
		
		JButton btnNewButton = new JButton("CERCA");
		btnNewButton.setBounds(430, 334, 89, 52);
		panel_2.add(btnNewButton);
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
		try {
				ArrayList <utente> lista = new ArrayList<utente>();
 			DefaultTableModel dtm1 = (DefaultTableModel) table_1.getModel();
				lista.addAll(cercanome());
 			Vector vtm = new Vector();
				for (utente utente : lista) {
					   vtm.add(utente.getId());
					   vtm.add(utente.getNome());
					   vtm.add(utente.getCognome());
				       vtm.add(utente.getNato());
					   vtm.add(utente.getResidenza());
					   vtm.add(utente.getProfessione());
					   dtm1.addRow(vtm);
			
				
					   
            }
				
			} catch (ClassNotFoundException e) {
				
				e.printStackTrace();
			} catch (IOException e) {
			
              e.printStackTrace();
			} catch (SQLException e) {
				
				e.printStackTrace();
	
			}
		}
		
		});
		
	
	
	}
	
	
	/* AGGIUNGO CONTATTO */
	
	
	public int aggiungicontatto(utente u) throws ClassNotFoundException, IOException, SQLException {
		properties p1 = new properties ();
	    p1.connessione();
		String sql1 = "INSERT INTO info (nome,cognome,nato,residenza,professione) VALUES (?,?,?,?,?)";
		PreparedStatement st = (PreparedStatement) p1.connessione().prepareStatement(sql1);
		st.setString(1, u.getNome());
		st.setString(2, u.getCognome());
		st.setString(3, u.getNato());
		st.setString(4, u.getResidenza());
		st.setString(5, u.getProfessione());
		st.executeUpdate();
		ResultSet rs  = st.getGeneratedKeys();
		rs.next();
	
	return rs.getInt(1);
   }
	
	
	/* CERCA CONTATTO */
	
	public  ArrayList<utente> cercacontatto() throws ClassNotFoundException, IOException, SQLException {
		properties p1 = new properties();
	
		ArrayList<utente> lista = new ArrayList<utente>();
		p1.connessione();
		String sql1= "SELECT id, nome , cognome,nato,residenza,professione FROM info";
		PreparedStatement st = (PreparedStatement) p1.connessione().prepareStatement(sql1);
		ResultSet rs ;
		rs = st.executeQuery();
		while(rs.next()) {
			    utente u = new utente ();
			    u.setId((rs.getInt(1)));
			    u.setNome(rs.getString(2));
			    u.setCognome(rs.getString(3));
			    u.setNato(rs.getString(4));
			    u.setResidenza(rs.getString(4));
			    u.setProfessione(rs.getString(5));
			    lista.add(u);
		}
		return lista;
	}
	 
	/* CERCA CONTATTO PER NOME */
	public ArrayList<utente> cercanome() throws ClassNotFoundException, IOException, SQLException{
		ArrayList<utente> lista = new ArrayList<utente>();
		properties p1 = new properties();
		p1.connessione();
		String nome =  textFieldNomeCerca.getText();
		String nome1 = "nino";
		System.out.println(nome);
		String sql1 = "SELECT id,nome,cognome,nato,residenza,professione  FROM info WHERE  nome ='"+nome+"'";  
		
		PreparedStatement st = (PreparedStatement) p1.connessione().prepareStatement(sql1);
		ResultSet rs ;
		rs = st.executeQuery();
		while(rs.next()) {
			    utente u = new utente();
			    u.setId(rs.getInt(1));
	            u.setNome(rs.getString(2));
			   u.setCognome(rs.getString(3));
			   u.setNato(rs.getString(4));
			   u.setResidenza(rs.getString(5));
			   u.setProfessione(rs.getString(6));
			    lista.add(u);
		}
		
		return lista;
	
	}
}
