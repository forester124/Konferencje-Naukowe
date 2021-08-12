package model;

public class Recenzent extends Osoba {

	private String tytul;
	
	public Recenzent() {
		super();
	}

	public Recenzent(String tytul, String imie, String nazwisko, String pesel, String telefon, String email, String username,
			String password) {
		super(imie, nazwisko, pesel, telefon, email, username, password);
		this.tytul = tytul;
	}

	public Recenzent(int id,  String tytul, String imie, String nazwisko, String pesel, String telefon, String email,
			String username, String password) {
		super(id, imie, nazwisko, pesel, telefon, email, username, password);
		this.tytul = tytul;
	}

	
	public String getTytul() {
		return tytul;
	}

	public void setTytul(String tytul) {
		this.tytul = tytul;
	}
}
