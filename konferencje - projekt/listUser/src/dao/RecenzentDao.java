package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Recenzent;
import model.Praca;

public class RecenzentDao extends MainDao {

	public int registerRecenzent(Recenzent recenzent) {
		String INSERT_REC_SQL = "INSERT INTO Recenzent"
				+ "  (tytul, imie, nazwisko, pesel, telefon, email, username, password) VALUES "
				+ " (?, ?, ?, ?, ?, ?, ?, ?);";

		int result = 0;

		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_REC_SQL)) {
			preparedStatement.setString(1, recenzent.getTytul());
			preparedStatement.setString(2, recenzent.getImie());
			preparedStatement.setString(3, recenzent.getNazwisko());
			preparedStatement.setString(4, recenzent.getPesel());
			preparedStatement.setString(5, recenzent.getTelefon());
			preparedStatement.setString(6, recenzent.getEmail());
			preparedStatement.setString(7, recenzent.getUsername());
			preparedStatement.setString(8, recenzent.getPassword());

			System.out.println(preparedStatement);

			result = preparedStatement.executeUpdate();

		} catch (SQLException e) {

			printSQLException(e);
		}
		return result;
	}

	public List<Recenzent> listaRecenzentow() {

		String SELECT_ALL_REC = "select * from Recenzent";
		List<Recenzent> recenzenci = new ArrayList<Recenzent>();

		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_REC);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();
			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("id");
				String tytul = rs.getString("tytul");
				String imie = rs.getString("imie");
				String nazwisko = rs.getString("nazwisko");
				String pesel = rs.getString("pesel");
				String telefon = rs.getString("telefon");
				String email = rs.getString("email");
				String username = rs.getString("username");
				String password = rs.getString("password");
				recenzenci.add(new Recenzent(id, tytul, imie, nazwisko, pesel, telefon, email, username, password));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return recenzenci;

	}

	public boolean deleteRecenzent(int id) {
		String DELETE_REC_SQL = "delete from Recenzent where id = ?;";
		boolean rowDeleted = false;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_REC_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		} catch (SQLException e) {
			printSQLException(e);
			;
		}
		return rowDeleted;
	}

	public Recenzent selectRecenzent(int id) {
		String SELECT_REC_BY_ID = "select * from Recenzent where id =?";
		Recenzent recenzent = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_REC_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String tytul = rs.getString("tytul");
				String imie = rs.getString("imie");
				String nazwisko = rs.getString("nazwisko");
				String pesel = rs.getString("pesel");
				String telefon = rs.getString("telefon");
				String email = rs.getString("email");
				String username = rs.getString("username");
				String password = rs.getString("password");
				recenzent = new Recenzent(id, tytul, imie, nazwisko, pesel, telefon, email, username, password);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return recenzent;
	}

	public boolean updateRecenzent(Recenzent rec) throws SQLException {
		String UPDATE_REC_SQL = "update Recenzent set tytul = ?, imie = ?,nazwisko= ?, pesel= ?, telefon =?,email= ?,username= ?,password= ? where id = ?;";
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_REC_SQL);) {
			statement.setString(1, rec.getTytul());
			statement.setString(2, rec.getImie());
			statement.setString(3, rec.getNazwisko());
			statement.setString(4, rec.getPesel());
			statement.setString(5, rec.getTelefon());
			statement.setString(6, rec.getEmail());
			statement.setString(7, rec.getUsername());
			statement.setString(8, rec.getPassword());
			statement.setInt(9, rec.getId());

			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}

	public boolean czyOceniono(int id_recenzenta, int id_pracy) throws SQLException {
		String SELECT_REC_PRACA_SQL = "select * from Recenzent_Praca where id_recenzenta = ? and id_pracy = ?;";
		boolean czyOceniono = false;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(SELECT_REC_PRACA_SQL);) {
			statement.setInt(1, id_recenzenta);
			statement.setInt(2, id_pracy);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				czyOceniono = true;
			}
		}
		return czyOceniono;
	}

	public List<Praca> listaOcen(int id_recenzenta) throws SQLException {
		String SELECT_REC_PRACA_SQL = "select * from Recenzent_Praca where id_recenzenta = ? ;";
		List<Praca> ocenyPrac = new ArrayList<Praca>();
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(SELECT_REC_PRACA_SQL);) {
			statement.setInt(1, id_recenzenta);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				Praca praca = new Praca();
				int id_pracy = rs.getInt("id_pracy");
				int ocena = rs.getInt("ocena");
				praca.setId(id_pracy);
				praca.setOcena(ocena);
				ocenyPrac.add(praca);
			}

		}
		return ocenyPrac;
	}

	public void usunRec(int id) {
		String DEL_REC_SQL = "delete from Recenzent_Praca where id_recenzenta = ?;";
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DEL_REC_SQL);) {
			statement.setInt(1, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
			;
		}
	}

	public boolean validateUsername(String username) {
		String VALIDATE = "select * from Recenzent where username = ?";
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
