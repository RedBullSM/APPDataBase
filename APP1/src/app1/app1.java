package app1;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.LayoutManager;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.mysql.jdbc.PreparedStatement;

import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.AddressException;
import javax.mail.internet.MimeMultipart;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;

import java.awt.Color;
import javax.swing.JTextArea;
import java.awt.Button;

public class app1  {
	
	query q = new query();
	
	maildatabase md = new maildatabase();
	public JFrame frame;
	public JTextField textNome;
	public JTextField textCognome;
	public JTextField textField_1Mail;
	 
	public JTextField textNato;
	public JTextField textResidente;
	public JTextField textProf;
	public JTable table;
	public JTable table_1;
	public JTextField textFieldNomeCerca;
	public JTextField textFieldCognomeCerca;
    public JTextField textFieldElimina;
	private JTextField textField;


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
		scrollPane.setBounds(-25, 11, 852, 436);
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
					q.aggiungicontatto(u);
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
		listacontatti = q.cercacontatto();
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
		
		JLabel label = new JLabel("New label");
		scrollPane.setColumnHeaderView(label);
		
		JPanel panel_4 = new JPanel();
		tabbedPane.addTab("ELIMINA CLIENTE", null, panel_4, null);
		panel_4.setLayout(null);
		
		textFieldElimina = new JTextField();
		textFieldElimina.setBounds(299, 77, 159, 32);
		panel_4.add(textFieldElimina);
		textFieldElimina.setColumns(10);
		
		JLabel lblCognome_2 = new JLabel("COGNOME");
		lblCognome_2.setBounds(351, 34, 65, 32);
		panel_4.add(lblCognome_2);
		JButton btnElimina_1 = new JButton("ELIMINA");
		btnElimina_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String cognome = textFieldElimina.getText();
					q.eliminacontatto(cognome);
					JOptionPane.showMessageDialog(frame, "CANCELLATO");
				} catch (ClassNotFoundException e1) {
					
					e1.printStackTrace();
				} catch (IOException e1) {
					
					e1.printStackTrace();
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
			}
		});
		btnElimina_1.setBounds(336, 189, 89, 32);
		panel_4.add(btnElimina_1);
		
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
		textFieldCognomeCerca.setBounds(86, 379, 154, 20);
		panel_2.add(textFieldCognomeCerca);
		textFieldCognomeCerca.setColumns(10);
		
		JButton btnNewButton = new JButton("CERCA");
		btnNewButton.setBounds(430, 334, 89, 52);
		panel_2.add(btnNewButton);
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("INVIA EMAIL", null, panel_3, null);
		String cognome = "panico";
		q.updateinfo(cognome);
		panel_3.setLayout(null);
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(382, 5, 2, 2);
		panel_3.add(scrollPane_2);
		
		JLabel lblEmailCliente = new JLabel("EMAIL CLIENTE");
		lblEmailCliente.setBounds(152, 11, 318, 36);
		lblEmailCliente.setFont(new Font("Ravie", Font.BOLD, 27));
		panel_3.add(lblEmailCliente);
		
		textField_1Mail = new JTextField();
		textField_1Mail.setBounds(183, 52, 257, 27);
		textField_1Mail.setBackground(Color.WHITE);
		panel_3.add(textField_1Mail);
		textField_1Mail.setColumns(10);
		JButton btnScegliFile = new JButton("SCEGLI FILE");
		JTextArea textArea12 = new JTextArea();
		textArea12.setBounds(10, 192, 218, 36);
		panel_3.add(textArea12);
		JLabel lblNewLabel_1 = new JLabel("MESSAGGIO");
		lblNewLabel_1.setBounds(266, 239, 118, 20);
		lblNewLabel_1.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 16));
		panel_3.add(lblNewLabel_1);
		JTextArea textAreaMail = new JTextArea();
		textAreaMail.setBounds(98, 271, 482, 121);
		panel_3.add(textAreaMail);
		JButton btnInviaMail = new JButton("INVIA");
		JTextArea textAreaMail1 = new JTextArea();
		textAreaMail1.setBounds(238, 145, 146, 55);
		
		panel_3.add(textAreaMail1);
		
		btnScegliFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 JFileChooser fileChooser = new JFileChooser();
				 int n = fileChooser.showOpenDialog(fileChooser);
				 if (n == JFileChooser.APPROVE_OPTION) {
			          File f = fileChooser.getSelectedFile();
			          String nomefile = f.getName();
			          textArea12.setText(nomefile);
			          try {
				        	String mail = textField_1Mail.getText();
				      		String messaggio = textAreaMail.getText();
				      		String oggetto = textAreaMail1.getText();
				      		
							md.sendEmail( "panicosebastiano@hotmail.it", messaggio, oggetto, f,mail);
						} catch (MessagingException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					    catch (FileNotFoundException e2) {
						
						e2.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			         
			    
			       
				 }
				  
			}
		});
		/*
		btnInviaMail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String mail = textField_1Mail.getText();
				String messaggio = textAreaMail.getText();
				String oggetto = textAreaMail1.getText();
			    try {
				//	md.sendEmail(mail, oggetto, messaggio,f);
				} catch (AddressException e) {
					
					e.printStackTrace();
				} catch (MessagingException e) {
					
					e.printStackTrace();
				} catch (IOException e) {
										e.printStackTrace();
				}
				
			}
		});
		*/
		btnInviaMail.setBounds(295, 408, 108, 39);
		btnInviaMail.setFont(new Font("Snap ITC", Font.PLAIN, 15));
		panel_3.add(btnInviaMail);
		
		JLabel lblOggetto = new JLabel("OGGETTO");
		lblOggetto.setFont(new Font("Gill Sans Ultra Bold", Font.BOLD, 10));
		lblOggetto.setBounds(264, 100, 108, 36);
		panel_3.add(lblOggetto);
		
		
		
	
		btnScegliFile.setBounds(49, 146, 108, 23);
		panel_3.add(btnScegliFile);
		
		
		
		
		
		JPanel panel_5 = new JPanel();
		tabbedPane.addTab("New tab", null, panel_5, null);
		panel_5.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(10, 45, 643, 402);
		panel_5.add(textField);
		textField.setColumns(10);
		
		
	    btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
		try {
			    ArrayList <utente> listacognome = new ArrayList<utente>();
				List <utente> listanome = new ArrayList<utente>();
				DefaultTableModel dtm1 = (DefaultTableModel) table_1.getModel();
				String nome = textFieldNomeCerca.getText();
				String cognome = textFieldCognomeCerca.getText();
				listanome.addAll(q.cercanome(nome));
				listacognome.addAll(q.cercacognome(cognome));
				
				
				/*RICERCA PER NOME*/
				
				if(textFieldNomeCerca.getText()!=null) {
					    JOptionPane.showMessageDialog(frame, "RICERCA IN CORSO");
						for (utente utente : listanome) {
							Vector vtm = new Vector();
								vtm.add(utente.getId());
								vtm.add(utente.getNome());
								vtm.add(utente.getCognome());
								vtm.add(utente.getNato());
								vtm.add(utente.getResidenza());
								vtm.add(utente.getProfessione());
					            dtm1.addRow(vtm);
			         }
				}			
				
				/*RICERCA PER COGNOME*/	
				
			
			
			//	JOptionPane.showMessageDialog(frame, "RICERCA IN CORSO");
                if(textFieldCognomeCerca.getText()!=null) {
                JOptionPane.showMessageDialog(frame, "RICERCA IN CORSO");
				for (utente utente : listacognome) {
						Vector vtm1 = new Vector();
						vtm1.add(utente.getId());
						vtm1.add(utente.getNome());
						vtm1.add(utente.getCognome());
						vtm1.add(utente.getNato());
						vtm1.add(utente.getResidenza());
						vtm1.add(utente.getProfessione());
						dtm1.addRow(vtm1);
				}		
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
	}