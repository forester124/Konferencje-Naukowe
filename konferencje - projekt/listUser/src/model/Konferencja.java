package model;

public class Konferencja {

	private int id;
	private String nazwa;
	private String opis;
	private int koszt;
	private String adres;
	private String organizator;
	private String dataKonf;
	private String dataZapis;
	private String godzina;
	private String harmonogram;

	public Konferencja(String nazwa, String opis, int koszt, String adres, String organizator, String dataKonf,
			String dataZapis, String godzina, String harmonogram) {
		this.nazwa = nazwa;
		this.opis = opis;
		this.koszt = koszt;
		this.adres = adres;
		this.dataKonf = dataKonf;
		this.dataZapis = dataZapis;
		this.organizator = organizator;
		this.godzina = godzina;
		this.harmonogram = harmonogram;
	}

	public Konferencja(int id, String nazwa, String opis, int koszt, String adres, String organizator, String dataKonf,
			String dataZapis, String godzina, String harmonogram) {
		this.id = id;
		this.nazwa = nazwa;
		this.opis = opis;
		this.koszt = koszt;
		this.adres = adres;
		this.dataKonf = dataKonf;
		this.dataZapis = dataZapis;
		this.organizator = organizator;
		this.godzina = godzina;
		this.harmonogram = harmonogram;
	}

	public Konferencja(int id, String nazwa, String data) {
		this.id = id;
		this.nazwa = nazwa;
		this.dataKonf = data;
	}
	
	@Override
	public String toString() {
		return "Konferencja [id=" + id + ", nazwa=" + nazwa + ", opis=" + opis + ", koszt=" + koszt + ", adres=" + adres
				+ ", organizator=" + organizator + ", dataKonf=" + dataKonf + ", dataZapis=" + dataZapis + ", godzina="
				+ godzina + ", harmonogram=" + harmonogram + "]";
	}

	public String getHarmonogram() {
		return harmonogram;
	}

	public void setHarmonogram(String harmonogram) {
		this.harmonogram = harmonogram;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNazwa() {
		return this.nazwa;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public int getKoszt() {
		return koszt;
	}

	public void setKoszt(int koszt) {
		this.koszt = koszt;
	}

	public String getAdres() {
		return adres;
	}

	public void setAdres(String adres) {
		this.adres = adres;
	}

	public String getOrganizator() {
		return organizator;
	}

	public void setOrganizator(String organizator) {
		this.organizator = organizator;
	}

	public String getDataKonf() {
		return dataKonf;
	}

	public void setDataKonf(String dataKonf) {
		this.dataKonf = dataKonf;
	}

	public String getDataZapis() {
		return dataZapis;
	}

	public void setDataZapis(String dataZapis) {
		this.dataZapis = dataZapis;
	}

	public String getGodzina() {
		return godzina;
	}

	public void setGodzina(String godzina) {
		this.godzina = godzina;
	}

	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}

}
