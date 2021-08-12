package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;


public class Dane {
	
	private String nrKonta = "";
	private String tytulPrzelew = "";
	private String nazwaOdbiorcy = "";
	
	public Dane(String nrKonta, String tytulPrzelew, String nazwaOdbiorcy) {
		this.nrKonta = nrKonta;
		this.tytulPrzelew = tytulPrzelew;
		this.nazwaOdbiorcy = nazwaOdbiorcy;
	}

	public String getNrKonta() {
		return nrKonta;
	}

	public void setNrKonta(String nrKonta) {
		this.nrKonta = nrKonta;
	}

	public String getTytulPrzelew() {
		return tytulPrzelew;
	}

	public void setTytulPrzelew(String tytulPrzelew) {
		this.tytulPrzelew = tytulPrzelew;
	}

	public String getNazwaOdbiorcy() {
		return nazwaOdbiorcy;
	}

	public void setNazwaOdbiorcy(String nazwaOdbiorcy) {
		this.nazwaOdbiorcy = nazwaOdbiorcy;
	}
	
	@Override
	public String toString() {
		return this.nrKonta + "\n" + this.tytulPrzelew + "\n" + this.nazwaOdbiorcy;
	}
	
	public static void zapiszPlik(Dane dane) throws FileNotFoundException {
		PrintWriter out = new PrintWriter("C:\\Users\\Damian\\Desktop\\java ee procjets\\listUser\\dane.txt");
		out.println(dane);
		out.close();
	}
	
	public static Dane wczytajPlik() throws FileNotFoundException {
		Scanner in = new Scanner(new File("C:\\Users\\Damian\\Desktop\\java ee procjets\\listUser\\dane.txt"));
		Dane dane = new Dane(in.nextLine(), in.nextLine(), in.nextLine());
		return dane;
	}
}
