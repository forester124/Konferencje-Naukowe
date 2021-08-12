package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Uzytkownik;
import model.Organizator;
import model.Recenzent;

public class LoginDao extends MainDao {
	static private Uzytkownik currentUser = null;
	static private Organizator currentOrganizer = null;
	static private Recenzent currentRecenzent = null;

	static public Uzytkownik getCurrentUser() {
		return currentUser;
	}

	static public Organizator getCurrentOrganizer() {
		return currentOrganizer;
	}

	static public Recenzent getCurrentRecenzent() {
		return currentRecenzent;
	}

	public boolean validate(Uzytkownik user) throws ClassNotFoundException {
		boolean status = false;

		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection
						.prepareStatement("select * from Uzytkownik where username = ? and password = ? ")) {
			preparedStatement.setString(1, user.getUsername());
			preparedStatement.setString(2, user.getPassword());

			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			int id = -1;
			String imie = "";
			String nazwisko = "";
			String pesel = "";
			String telefon = "";
			String email = "";
			while (rs.next()) {
				status = true;
				id = rs.getInt("id");
				imie = rs.getString("imie");
				nazwisko = rs.getString("nazwisko");
				pesel = rs.getString("pesel");
				telefon = rs.getString("telefon");
				email = rs.getString("email");
			}
			user.setId(id);
			user.setImie(imie);
			user.setNazwisko(nazwisko);
			user.setPesel(pesel);
			user.setTelefon(telefon);
			user.setEmail(email);
			currentUser = user;

		} catch (SQLException e) {
			printSQLException(e);
		}
		return status;
	}

	public boolean validate(Organizator organizer) throws ClassNotFoundException {
		boolean status = false;

		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection
						.prepareStatement("select * from Organizator where username = ? and password = ? ")) {
			preparedStatement.setString(1, organizer.getUsername());
			preparedStatement.setString(2, organizer.getPassword());

			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			int id = -1;
			String imie = "";
			String nazwisko = "";
			String pesel = "";
			String telefon = "";
			String email = "";
			while (rs.next()) {
				status = true;
				id = rs.getInt("id");
				imie = rs.getString("imie");
				nazwisko = rs.getString("nazwisko");
				pesel = rs.getString("pesel");
				telefon = rs.getString("telefon");
				email = rs.getString("email");
			}
			organizer.setId(id);
			organizer.setImie(imie);
			organizer.setNazwisko(nazwisko);
			organizer.setPesel(pesel);
			organizer.setTelefon(telefon);
			organizer.setEmail(email);
			currentOrganizer = organizer;

		} catch (SQLException e) {
			printSQLException(e);
		}
		return status;
	}

	public boolean validate(Recenzent recenzent) throws ClassNotFoundException {
		boolean status = false;

		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection
						.prepareStatement("select * from Recenzent where username = ? and password = ? ")) {
			preparedStatement.setString(1, recenzent.getUsername());
			preparedStatement.setString(2, recenzent.getPassword());

			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			int id = -1;
			String imie = "";
			String nazwisko = "";
			String pesel = "";
			String telefon = "";
			String email = "";
			while (rs.next()) {
				status = true;
				id = rs.getInt("id");
				imie = rs.getString("imie");
				nazwisko = rs.getString("nazwisko");
				pesel = rs.getString("pesel");
				telefon = rs.getString("telefon");
				email = rs.getString("email");
			}
			recenzent.setId(id);
			recenzent.setImie(imie);
			recenzent.setNazwisko(nazwisko);
			recenzent.setPesel(pesel);
			recenzent.setTelefon(telefon);
			recenzent.setEmail(email);
			currentRecenzent = recenzent;

		} catch (SQLException e) {
			printSQLException(e);
		}
		return status;
	}

	public static void wyloguj() {
		currentOrganizer = null;
		currentRecenzent = null;
		currentUser = null;
	}

	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}
}
