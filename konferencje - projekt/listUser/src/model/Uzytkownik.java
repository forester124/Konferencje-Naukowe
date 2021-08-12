package model;

public class Uzytkownik extends Osoba {

	private String status;

	public Uzytkownik() {
		super();
	}
	
	public Uzytkownik(String imie, String nazwisko, String pesel, String telefon, String email, String username,
			String password) {
		super(imie, nazwisko, pesel, telefon, email, username, password);
	}

	public Uzytkownik(int id, String imie, String nazwisko, String pesel, String telefon, String email, String username,
			String password) {
		super(id, imie, nazwisko, pesel, telefon, email, username, password);
	}
	
	public Uzytkownik(int id, String username, String status) {
		super(id, username);
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
