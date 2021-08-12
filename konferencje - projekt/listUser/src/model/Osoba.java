package model;

public class Osoba {

	private int id;
	private String imie;
	private String nazwisko;
	private String pesel;
	private String telefon;
	private String email;
	private String username;
	private String password;
	
	public Osoba(int id, String imie, String nazwisko, String pesel, String telefon, String email, String username,
			String password) {
		this.id = id;
		this.imie = imie;
		this.nazwisko = nazwisko;
		this.pesel = pesel;
		this.telefon = telefon;
		this.email = email;
		this.username = username;
		this.password = password;
	}
	
	public Osoba(String imie, String nazwisko, String pesel, String telefon, String email, String username,
			String password) {
		this.imie = imie;
		this.nazwisko = nazwisko;
		this.pesel = pesel;
		this.telefon = telefon;
		this.email = email;
		this.username = username;
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "Osoba [id=" + id + ", imie=" + imie + ", nazwisko=" + nazwisko + ", pesel=" + pesel + ", telefon="
				+ telefon + ", email=" + email + ", username=" + username + ", password=" + password + "]";
	}

	public Osoba(int id, String username) {
		this.id = id;
		this.username = username;
	}

	public Osoba() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getImie() {
		return imie;
	}

	public void setImie(String imie) {
		this.imie = imie;
	}

	public String getNazwisko() {
		return nazwisko;
	}

	public void setNazwisko(String nazwisko) {
		this.nazwisko = nazwisko;
	}

	public String getPesel() {
		return pesel;
	}

	public void setPesel(String pesel) {
		this.pesel = pesel;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
