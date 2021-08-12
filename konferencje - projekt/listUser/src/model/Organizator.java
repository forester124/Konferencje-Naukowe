package model;

public class Organizator extends Osoba {
	
	public Organizator() {
		super();
	}

	public Organizator(String imie, String nazwisko, String pesel, String telefon, String email, String username,
			String password) {
		super(imie, nazwisko, pesel, telefon, email, username, password);
	}

	public Organizator(int id, String imie, String nazwisko, String pesel, String telefon, String email,
			String username, String password) {
		super(id, imie, nazwisko, pesel, telefon, email, username, password);
	}
}
