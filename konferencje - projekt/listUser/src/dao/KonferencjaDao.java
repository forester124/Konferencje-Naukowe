package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Konferencja;
import model.Uzytkownik;

public class KonferencjaDao extends MainDao {

	private static final String INSERT_KONF_SQL = "insert into konferencja(nazwa, koszt, opis, adres, organizatorzy, data_rozpoczecia, data_rejestracji, godzina, harmonogram)"
			+ "values (?, ?, ?, ?, ?, ?, ?, ?, ?);";
	private static final String SELECT_KONF_BY_ID = "select * from Konferencja where id =?";
	private static final String SELECT_ALL_KONF = "SELECT id, nazwa, data_rozpoczecia FROM Konferencja;";
	private static final String DELETE_KONF_SQL = "delete from konferencja where id = ?;";
	private static final String UPDATE_KONF_SQL = "update konferencja set nazwa = ?,koszt= ?, opis = ?, adres = ?, organizatorzy = ?,"
			+ " data_rozpoczecia = ?, data_rejestracji = ?, godzina = ?, harmonogram = ? where id = ?;";
	private static final String SELECT_KONF_UCZESTNIK = "Select u.id, u.username, ku.status from konferencja_uczestnik ku join"
			+ " uzytkownik u on ku.id_uczestnik = u.id where ku.id_konferencja = ?;";
	private static final String INSERT_KONF_UCZESTNIK = "insert into konferencja_uczestnik values (?, ?, ?);";
	private static final String DELETE_KONF_UCZESTNIK = "delete from konferencja_uczestnik where id_uczestnik = ? and id_konferencja = ?;";
	private static final String UPDATE_STATUS = "update konferencja_uczestnik set status = \"op³acono\" where id_uczestnik = ? and id_konferencja = ?;";

	public static Konferencja CURRENT_KONFERENCJA;
	

	public KonferencjaDao() {
		super();
	}

	public void insertKonf(Konferencja k) throws SQLException {
		System.out.println(INSERT_KONF_SQL);
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_KONF_SQL)) {
			preparedStatement.setString(1, k.getNazwa());
			preparedStatement.setInt(2, k.getKoszt());
			preparedStatement.setString(3, k.getOpis());
			preparedStatement.setString(4, k.getAdres());
			preparedStatement.setString(5, k.getOrganizator());
			preparedStatement.setString(6, k.getDataKonf());
			preparedStatement.setString(7, k.getDataZapis());
			preparedStatement.setString(8, k.getGodzina());
			preparedStatement.setString(9,  k.getHarmonogram());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public Konferencja selectKonf(int id) {
		Konferencja k = null;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_KONF_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int ids = rs.getInt("id");
				String nazwa = rs.getString("nazwa");
				int koszt = rs.getInt("koszt");
				String opis = rs.getString("opis");
				String adres = rs.getString("adres");
				String org = rs.getString("organizatorzy");
				String dataRoz = rs.getString("data_rozpoczecia");
				String dataZap = rs.getString("data_rejestracji");
				String godzina = rs.getString("godzina");
				String harmonogram = rs.getString("harmonogram");
				k = new Konferencja(ids, nazwa, opis, koszt, adres, org, dataRoz, dataZap, godzina, harmonogram);
			}
			CURRENT_KONFERENCJA = k;
		} catch (SQLException e) {
			printSQLException(e);
		}
		return k;
	}

	public List<Konferencja> selectAllKonf() {

		List<Konferencja> users = new ArrayList<>();
		try (Connection connection = getConnection();

				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_KONF);) {
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String nazwa = rs.getString("nazwa");
				String data = rs.getString("data_rozpoczecia");
				users.add(new Konferencja(id, nazwa, data));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return users;
	}

	public boolean deleteKonf(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_KONF_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}
	
	public boolean deleteUserKonf(int idU, int idK) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_KONF_UCZESTNIK);) {
			statement.setInt(1, idU);
			statement.setInt(2, idK);
			System.out.println(statement);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean updateKonf(Konferencja k) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_KONF_SQL);) {
			statement.setString(1, k.getNazwa());
			statement.setInt(2, k.getKoszt());
			statement.setString(3, k.getOpis());
			statement.setString(4, k.getAdres());
			statement.setString(5, k.getOrganizator());
			statement.setString(6, k.getDataKonf());
			statement.setString(7, k.getDataZapis());
			statement.setString(8, k.getGodzina());
			statement.setString(9, k.getHarmonogram());
			statement.setInt(10, k.getId());
			
			System.out.println(k);

			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}
	
	public boolean updateStatus(int id1, int id2) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_STATUS);) {
			statement.setInt(1, id1);
			statement.setInt(2, id2);
			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}
	
	public List<Uzytkownik> selectAllUczesnik(int id) {
		List<Uzytkownik> users = new ArrayList<>();
		try (Connection connection = getConnection();

				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_KONF_UCZESTNIK);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int ids = rs.getInt("id");
				String nazwa = rs.getString("username");
				String status = rs.getString("status");
				users.add(new Uzytkownik(ids, nazwa, status));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		
		return users;
	}
	

	public boolean zapiszNaKonferencje(int id1, int id2) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(INSERT_KONF_UCZESTNIK);) {
			statement.setInt(1, id1);
			statement.setInt(2, id2);
			statement.setString(3, "nie op³acono");
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
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