package app1;

public class utente {
   private int id;
   private String nome;
   private String cognome;
   private String nato;
   private String professione;
   private String residenza;
  
   public utente () {
	   super();
   }
   public utente(String nome, String cognome, String nato, String residenza, String professione) {
		super();
	
		this.nome = nome;
		this.cognome = cognome;
		this.nato = nato;
		this.residenza = residenza;
     	this.professione = professione;
	}
public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getNome() {
	return nome;
}

public void setNome(String nome) {
	this.nome = nome;
}

public String getCognome() {
	return cognome;
}

public void setCognome(String cognome) {
	this.cognome = cognome;
}

public String getNato() {
	return nato;
}

public void setNato(String nato) {
	this.nato = nato;
}

public String getResidenza() {
	return residenza;
}

public void setResidenza(String residenza) {
	this.residenza = residenza;
}

public String getProfessione() {
	return professione;
}

public void setProfessione(String professione) {
	this.professione = professione;
}


}
