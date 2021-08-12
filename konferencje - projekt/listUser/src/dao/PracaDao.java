package dao;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Praca;

public class PracaDao extends MainDao {

	public int dodajPrace(Praca praca) {
		String INSERT_PRACA_SQL = "INSERT INTO Praca"
				+ "  (tytul, id_uzytkownika, id_konferencji, ocena1, ocena2, ocena3, status, artykul) VALUES "
				+ " (?, ?, ?, ?, ?, ?, ?, ?);";

		int result = 0;

		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PRACA_SQL)) {
			preparedStatement.setString(1, praca.getTytul());
			preparedStatement.setInt(2, praca.getId_uzytkownika());
			preparedStatement.setInt(3, praca.getId_konferencji());
			preparedStatement.setInt(4, -1);
			preparedStatement.setInt(5, -1);
			preparedStatement.setInt(6, -1);
			preparedStatement.setString(7, "oceniana");

			String filePath = praca.getUploadPath();
			InputStream artykul = new FileInputStream(new File(filePath));
			preparedStatement.setBlob(8, artykul);
			System.out.println(preparedStatement);

			result = preparedStatement.executeUpdate();

		} catch (SQLException e) {

			printSQLException(e);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return result;
	}

	public Praca selectPraca(int id) {
		String SELECT_PRACA_BY_ID = "select * from Praca where id =?";
		Praca praca = null;
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRACA_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String tytul = rs.getString("tytul");
				int id_uzytkownika = rs.getInt("id_uzytkownika");
				int id_konferencji = rs.getInt("id_konferencji");
				int ocena1 = rs.getInt("ocena1");
				int ocena2 = rs.getInt("ocena2");
				int ocena3 = rs.getInt("ocena3");
				String status = rs.getString("status");
				Blob artykul = rs.getBlob("artykul");
				praca = new Praca(id, tytul, id_uzytkownika, id_konferencji, ocena1, ocena2, ocena3, status, artykul);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return praca;
	}

	public List<Praca> listaPrac() {

		String SELECT_ALL_PRACA = "select * from Praca";
		List<Praca> prace = new ArrayList<Praca>();

		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRACA);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();
			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("id");
				String tytul = rs.getString("tytul");
				int id_uzytkownika = rs.getInt("id_uzytkownika");
				int id_konferencji = rs.getInt("id_konferencji");
				int ocena1 = rs.getInt("ocena1");
				int ocena2 = rs.getInt("ocena2");
				int ocena3 = rs.getInt("ocena3");
				String status = rs.getString("status");
				Blob artykul = rs.getBlob("artykul");
				prace.add(
						new Praca(id, tytul, id_uzytkownika, id_konferencji, ocena1, ocena2, ocena3, status, artykul));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return prace;

	}

	public boolean delPraca(int id) {
		String DELETE_Praca_SQL = "delete from Recenzent_Praca where id_pracy = ?;";
		boolean rowDeleted = false;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_Praca_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		} catch (SQLException e) {
			printSQLException(e);
			;
		}
		return rowDeleted;
	}

	public List<Praca> praceUzytkownika(int id_uzyt) {

		String SELECT_ALL_PRACA = "select * from Praca where id_uzytkownika = ?;";
		List<Praca> prace = new ArrayList<Praca>();

		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRACA);) {
			preparedStatement.setInt(1, id_uzyt);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();
			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("id");
				String tytul = rs.getString("tytul");
				int id_uzytkownika = rs.getInt("id_uzytkownika");
				int id_konferencji = rs.getInt("id_konferencji");
				int ocena1 = rs.getInt("ocena1");
				int ocena2 = rs.getInt("ocena2");
				int ocena3 = rs.getInt("ocena3");
				String status = rs.getString("status");
				Blob artykul = rs.getBlob("artykul");
				prace.add(
						new Praca(id, tytul, id_uzytkownika, id_konferencji, ocena1, ocena2, ocena3, status, artykul));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return prace;

	}

	public void pobierzPrace(int id) {
		int BUFFER_SIZE = 4096 * 8;
		Praca praca = selectPraca(id);
		String downloadFilePath = praca.getTytul() + ".pdf";
		try {
			InputStream inputStream = praca.getArtykul().getBinaryStream();
			OutputStream outputStream = new FileOutputStream(downloadFilePath);

			int bytesRead = -1;
			byte[] buffer = new byte[BUFFER_SIZE];
			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, bytesRead);
			}

			inputStream.close();
			outputStream.close();
			System.out.println("File saved");
		} catch (SQLException e) {
			printSQLException(e);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean ocenPrace(int id, int ocena) {
		boolean czyOceniono = false;
		String UPDATE_PRACA_OCENA1 = "update Praca set ocena1 = ? where id = ?;";
		String UPDATE_PRACA_OCENA2 = "update Praca set ocena2 = ? where id = ?;";
		String UPDATE_PRACA_OCENA3 = "update Praca set ocena3 = ?, status = ? where id = ?;";

		Praca praca = selectPraca(id);
		if (praca.getOcena1() == -1) {
			try (Connection connection = getConnection();
					PreparedStatement statement = connection.prepareStatement(UPDATE_PRACA_OCENA1);) {
				statement.setInt(1, ocena);
				statement.setInt(2, praca.getId());
				statement.executeUpdate();
				czyOceniono = true;

			} catch (SQLException e) {
				printSQLException(e);
			}
		} else if (praca.getOcena2() == -1) {
			try (Connection connection = getConnection();
					PreparedStatement statement = connection.prepareStatement(UPDATE_PRACA_OCENA2);) {
				statement.setInt(1, ocena);
				statement.setInt(2, praca.getId());
				statement.executeUpdate();
				czyOceniono = true;
			} catch (SQLException e) {
				printSQLException(e);
			}
		} else if (praca.getOcena3() == -1) {
			int wynik_ocen = praca.getOcena1() + praca.getOcena2() + ocena;
			if (wynik_ocen >= 2) {
				try (Connection connection = getConnection();
						PreparedStatement statement = connection.prepareStatement(UPDATE_PRACA_OCENA3);) {
					statement.setInt(1, ocena);
					statement.setString(2, "zaakceptowana");
					statement.setInt(3, praca.getId());
					statement.executeUpdate();
					czyOceniono = true;
				} catch (SQLException e) {
					printSQLException(e);
				}
			} else {
				try (Connection connection = getConnection();
						PreparedStatement statement = connection.prepareStatement(UPDATE_PRACA_OCENA3);) {
					statement.setInt(1, ocena);
					statement.setString(2, "odrzucona");
					statement.setInt(3, praca.getId());
					statement.executeUpdate();
					czyOceniono = true;
				} catch (SQLException e) {
					printSQLException(e);
				}
			}
		}
		return czyOceniono;

	}

	public boolean usunPrace(int id) {
		String DELETE_USERS_SQL = "delete from Praca where id = ?;";
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

	public void zapiszOcene(int id_recenzenta, int id_pracy, int ocena) {
		String INSERT_REC_PRACA = "INSERT INTO Recenzent_Praca" + "  (id_recenzenta, id_pracy, ocena) VALUES "
				+ " (?, ?, ?);";
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(INSERT_REC_PRACA);) {
			statement.setInt(1, id_recenzenta);
			statement.setInt(2, id_pracy);
			statement.setInt(3, ocena);
			statement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
			;
		}
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
