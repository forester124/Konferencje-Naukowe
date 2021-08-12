package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Organizator;
import model.Uzytkownik;

public class UzytkownikDao extends MainDao {

	public int registerUser(Uzytkownik user) throws ClassNotFoundException {
		String INSERT_USERS_SQL = "INSERT INTO Uzytkownik"
				+ "  (imie, nazwisko, pesel, telefon, email, username, password) VALUES " + " (?, ?, ?, ?, ?, ?, ?);";

		int result = 0;

		Class.forName("com.mysql.jdbc.Driver");

		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
			preparedStatement.setString(1, user.getImie());
			preparedStatement.setString(2, user.getNazwisko());
			preparedStatement.setString(3, user.getPesel());
			preparedStatement.setString(4, user.getTelefon());
			preparedStatement.setString(5, user.getEmail());
			preparedStatement.setString(6, user.getUsername());
			preparedStatement.setString(7, user.getPassword());

			System.out.println(preparedStatement);

			result = preparedStatement.executeUpdate();

		} catch (SQLException e) {

			printSQLException(e);
		}
		return result;
	}

	public List<Uzytkownik> listAllUsers() {

		String SELECT_ALL_USERS = "select * from Uzytkownik";
		List<Uzytkownik> users = new ArrayList<Uzytkownik>();

		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();
			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("id");
				String imie = rs.getString("imie");
				String nazwisko = rs.getString("nazwisko");
				String pesel = rs.getString("pesel");
				String telefon = rs.getString("telefon");
				String email = rs.getString("email");
				String username = rs.getString("username");
				String password = rs.getString("password");
				users.add(new Uzytkownik(id, imie, nazwisko, pesel, telefon, email, username, password));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return users;

	}

	public boolean deleteUser(int id) {
		String DELETE_USERS_SQL = "delete from Uzytkownik where id = ?;";
		boolean rowDeleted = false;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_USERS_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		} catch (SQLException e) {
			printSQLException(e);
			;
		}
		return rowDeleted;
	}

	public Uzytkownik selectUser(int id) {
		String SELECT_USER_BY_ID = "select * from Uzytkownik where id =?";
		Uzytkownik user = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String imie = rs.getString("imie");
				String nazwisko = rs.getString("nazwisko");
				String pesel = rs.getString("pesel");
				String telefon = rs.getString("telefon");
				String email = rs.getString("email");
				String username = rs.getString("username");
				String password = rs.getString("password");
				user = new Uzytkownik(id, imie, nazwisko, pesel, telefon, email, username, password);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return user;
	}

	public boolean updateUser(Uzytkownik user) throws SQLException {
		String UPDATE_USERS_SQL = "update Uzytkownik set imie = ?,nazwisko= ?, pesel= ?, telefon =?,email= ?,username= ?,password= ? where id = ?;";
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_USERS_SQL);) {
			statement.setString(1, user.getImie());
			statement.setString(2, user.getNazwisko());
			statement.setString(3, user.getPesel());
			statement.setString(4, user.getTelefon());
			statement.setString(5, user.getEmail());
			statement.setString(6, user.getUsername());
			statement.setString(7, user.getPassword());
			statement.setInt(8, user.getId());

			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}

	public boolean updateOrganizer(Organizator organizer) throws SQLException {
		String UPDATE_ORGANIZATOR_SQL = "update Organizator set imie = ?,nazwisko= ?, pesel= ?, telefon =?,email= ?,username= ?,password= ? where id = ?;";
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_ORGANIZATOR_SQL);) {
			statement.setString(1, organizer.getImie());
			statement.setString(2, organizer.getNazwisko());
			statement.setString(3, organizer.getPesel());
			statement.setString(4, organizer.getTelefon());
			statement.setString(5, organizer.getEmail());
			statement.setString(6, organizer.getUsername());
			statement.setString(7, organizer.getPassword());
			statement.setInt(8, organizer.getId());

			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}

	public Organizator selectOrganizator(int id) {
		String SELECT_ORGANIZATOR_BY_ID = "select * from Organizator where id =?";
		Organizator organizer = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ORGANIZATOR_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String imie = rs.getString("imie");
				String nazwisko = rs.getString("nazwisko");
				String pesel = rs.getString("pesel");
				String telefon = rs.getString("telefon");
				String email = rs.getString("email");
				String username = rs.getString("username");
				String password = rs.getString("password");
				organizer = new Organizator(id, imie, nazwisko, pesel, telefon, email, username, password);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return organizer;
	}

	public List<Organizator> listaOrganizatorow() {

		String SELECT_ALL_ORG = "select * from Organizator";
		List<Organizator> organizatorzy = new ArrayList<Organizator>();

		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ORG);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();
			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("id");
				String imie = rs.getString("imie");
				String nazwisko = rs.getString("nazwisko");
				String pesel = rs.getString("pesel");
				String telefon = rs.getString("telefon");
				String email = rs.getString("email");
				String username = rs.getString("username");
				String password = rs.getString("password");
				organizatorzy.add(new Organizator(id, imie, nazwisko, pesel, telefon, email, username, password));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return organizatorzy;
	}

	public boolean validateOrganizatorUsername(String username) {
		String VALIDATE = "select * from Organizator where username = ?";
		boolean czyWolne = true;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(VALIDATE);) {
			preparedStatement.setString(1, username);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				czyWolne = false;
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return czyWolne;
	}

	public boolean validateUsername(String username) {
		String VALIDATE = "select * from Uzytkownik where username = ?";
		boolean czyWolne = true;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(VALIDATE);) {
			preparedStatement.setString(1, username);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				czyWolne = false;
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return czyWolne;
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
