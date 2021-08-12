package model;
import java.sql.Blob;
public class Praca {
	private int id;
	private String tytul;
	private int id_uzytkownika;
	private int id_konferencji;
	private int ocena1;
	private int ocena2;
	private int ocena3;
	private String status;
	private String uploadPath;
	private Blob artykul;
	
	private int ocena;
	public Praca() {}

	public Praca(String tytul, int id_uzytkownika, int id_konferencji, String uploadPath) {
		super();
		this.tytul = tytul;
		this.id_uzytkownika = id_uzytkownika;
		this.id_konferencji = id_konferencji;
		this.uploadPath = uploadPath;
	}
	

	public Praca(int id, String tytul, int id_uzytkownika, int id_konferencji, int ocena1, int ocena2, int ocena3,
			String status, Blob artykul) {
		super();
		this.id = id;
		this.tytul = tytul;
		this.id_uzytkownika = id_uzytkownika;
		this.id_konferencji = id_konferencji;
		this.ocena1 = ocena1;
		this.ocena2 = ocena2;
		this.ocena3 = ocena3;
		this.status = status;
		this.artykul = artykul;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTytul() {
		return tytul;
	}

	public void setTytul(String tytul) {
		this.tytul = tytul;
	}

	public int getId_uzytkownika() {
		return id_uzytkownika;
	}

	public void setId_uzytkownika(int id_uzytkownika) {
		this.id_uzytkownika = id_uzytkownika;
	}

	public int getId_konferencji() {
		return id_konferencji;
	}

	public void setId_konferencji(int id_konferencji) {
		this.id_konferencji = id_konferencji;
	}

	public int getOcena1() {
		return ocena1;
	}

	public void setOcena1(int ocena1) {
		this.ocena1 = ocena1;
	}

	public int getOcena2() {
		return ocena2;
	}

	public void setOcena2(int ocena2) {
		this.ocena2 = ocena2;
	}

	public int getOcena3() {
		return ocena3;
	}

	public void setOcena3(int ocena3) {
		this.ocena3 = ocena3;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUploadPath() {
		return uploadPath;
	}

	public void setUploadPath(String uploadPath) {
		this.uploadPath = uploadPath;
	}

	public Blob getArtykul() {
		return artykul;
	}

	public void setArtykul(Blob artykul) {
		this.artykul = artykul;
	}

	public int getOcena() {
		return ocena;
	}

	public void setOcena(int ocena) {
		this.ocena = ocena;
	};
	
	
}
