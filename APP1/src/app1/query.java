package app1;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.LayoutManager;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JOptionPane;

import com.mysql.jdbc.PreparedStatement;

public class query  {
       properties p = new properties();

    
    
    /* CERCA PER COGNOME*/
   
    
   public ArrayList<utente> cercacognome(String cognome) throws ClassNotFoundException, IOException, SQLException{
	   
		ArrayList<utente> lista = new ArrayList<utente>();
		
		p.connessione();
	//	String cognome = textFieldCognomeCerca.getText().toString();
	
		String sql1 = "SELECT id,nome,cognome,nato,residenza,professione  FROM info WHERE  cognome ='"+cognome+"'";
		PreparedStatement st = (PreparedStatement) p.connessione().prepareStatement(sql1);
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
   
   /* CERCA PER NOME */
public ArrayList<utente> cercanome(String nome) throws ClassNotFoundException, IOException, SQLException {
	   p.connessione();
	   ArrayList<utente> lista = new ArrayList<utente>();
	   String sql1 = "SELECT id,nome,cognome,nato,residenza,professione  FROM info WHERE  nome = ? ";  
	   PreparedStatement st = (PreparedStatement) p.connessione().prepareStatement(sql1);
		st.setString(1, nome);
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

 /* ELIMINA CONTATTO*/

public void  eliminacontatto(String cognome) throws ClassNotFoundException, IOException, SQLException {
	  
	  p.connessione();
	 
	  String sql1 = "DELETE FROM info WHERE cognome = ? ";
	  PreparedStatement st = (PreparedStatement) p.connessione().prepareStatement(sql1);
	  st.setString(1, cognome);
	  st.executeUpdate();
	
  }


/* CERCA CONTATTO*/

public  ArrayList<utente> cercacontatto() throws ClassNotFoundException, IOException, SQLException {


	ArrayList<utente> lista = new ArrayList<utente>();
	p.connessione();
	String sql1= "SELECT id, nome , cognome,nato,residenza,professione FROM info";
	PreparedStatement st = (PreparedStatement) p.connessione().prepareStatement(sql1);
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

/*AGGIUNGI CONTATTO */

public void aggiungicontatto(utente u) throws ClassNotFoundException, IOException, SQLException {
	
	    p.connessione();
		String sql1 = "INSERT INTO info (nome,cognome,nato,residenza,professione) VALUES (?,?,?,?,?)";
		PreparedStatement st = (PreparedStatement) p.connessione().prepareStatement(sql1);
		st.setString(1, u.getNome());
		st.setString(2, u.getCognome());
		st.setString(3, u.getNato());
		st.setString(4, u.getResidenza());
		st.setString(5, u.getProfessione());
		st.executeUpdate();
   }

/* UPDATE DI UN RECORD */

public void updatecontatto(String set,String cognome) throws ClassNotFoundException, IOException, SQLException {
	 
	 p.connessione();
	 String sql1 = "UPDATE info SET '"+set+"' WHERE cognome = ? ";
	 PreparedStatement st= (PreparedStatement) p.connessione().prepareStatement(sql1);
	 st.setString(1, cognome);
	 st.executeUpdate();
 }



	public void updateinfo(String cognome) throws ClassNotFoundException, IOException, SQLException {
			
			p.connessione();
			ArrayList<utente> lista = new ArrayList<utente>();
			String sql1= "SELECT id,nome,cognome,nato,residenza,professione  FROM info WHERE  cognome = ? ";
			PreparedStatement st= (PreparedStatement) p.connessione().prepareStatement(sql1);
			st.setString(1, cognome);
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
			
	}
}