package web;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;
import java.io.File;
import java.io.FileInputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.KonferencjaDao;
import dao.LoginDao;
import dao.RecenzentDao;
import dao.UzytkownikDao;
import dao.PracaDao;
import model.Dane;
import model.Konferencja;
import model.Organizator;
import model.Recenzent;
import model.Uzytkownik;
import model.Praca;

@WebServlet("/")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1;
	private KonferencjaDao konfDao;
	private UzytkownikDao uzytkownikDao;
	private RecenzentDao recenzentDao;
	private PracaDao pracaDao;

	public void init() {
		konfDao = new KonferencjaDao();
		uzytkownikDao = new UzytkownikDao();
		recenzentDao = new RecenzentDao();
		pracaDao = new PracaDao();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		try {
			switch (action) {
			case "/register":
				registerUser(request, response);
				break;
			case "/wyloguj":
				wyloguj(request, response);
				break;
			case "/newKonf":
				showNewKonf(request, response);
				break;
			case "/insert":
				insertKonf(request, response);
				break;
			case "/deleteKonf":
				deleteKonf(request, response);
				break;
			case "/editKonf":
				showEditKonf(request, response);
				break;
			case "/update":
				updateKonf(request, response);
				break;
			case "/seeOrg":
				showSeeFormOrganizator(request, response);
				break;
			case "/seeUser":
				showSeeFormUser(request, response);
				break;
			case "/seeRec":
				showSeeFormRec(request, response);
				break;
			case "/zapisz":
				zapiszNaKonf(request, response);
				break;
			case "/deleteU":
				deleteUserKonf(request, response);
				break;
			case "/status":
				setStatus(request, response);
				break;
			case "/editUser":
				showUserEditForm(request, response);
				break;
			case "/editOrg":
				formularzEdycjiOrganizatora(request, response);
				break;
			case "/editRec":
				formularzEdycjiRecenzenta(request, response);
				break;
			case "/edytujOrganizatora":
				edytujOrganizatora(request, response);
				break;
			case "/deleteUser":
				deleteUser(request, response);
				break;
			case "/updateUser":
				updateUser(request, response);
				break;
			case "/listaUzytkownik":
				listUser(request, response);
				break;
			case "/listaRecenzent":
				listaRecenzentow(request, response);
				break;
			case "/formularzEdycjiRecenzenta":
				formularzEdycjiRecenzenta(request, response);
				break;
			case "/usunRecenzenta":
				usunRecenzenta(request, response);
				break;
			case "/formularzDodaniaRecenzenta":
				formularzDodaniaRecenzenta(request, response);
				break;
			case "/dodajRecenzenta":
				dodajRecenzentow(request, response);
				break;
			case "/edytujRecenzenta":
				edytujRecenzentow(request, response);
				break;
			case "/mojePrace":
				listaPracUzytkownika(request, response);
				break;
			case "/formularzNowejPracy":
				showNewPraca(request, response);
				break;
			case "/dodajPrace":
				dodajPrace(request, response);
				break;
			case "/pobierz":
				pobierzPrace(request, response);
				break;
			case "/formularzOceny":
				showFormularzOceny(request, response);
				break;
			case "/usunPrace":
				usunPrace(request, response);
				break;
			case "/ocen":
				ocenPrace(request, response);
				break;
			case "/mojeOceny":
				listaOcen(request, response);
				break;
			case "/listKonfOrg":
				listKonfOrg(request, response);
				break;
			case "/listKonfUser":
				listKonfUser(request, response);
				break;
			case "/listKonfRec":
				listKonfRec(request, response);
				break;
			case "/wyswietlPrace":
				listaPrac(request, response);
				break;
			case "/kontakt":
				kontakt(request, response);
				break;
			case"/rezygnuj":
				rezygnujKonf(request, response);
				break;
			default:
			}
		} catch (SQLException | ClassNotFoundException ex) {
			throw new ServletException(ex);
		}
	}

	private void registerUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String imie = request.getParameter("imie");
		String nazwisko = request.getParameter("nazwisko");
		String pesel = request.getParameter("pesel");
		String telefon = request.getParameter("telefon");
		String email = request.getParameter("email");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Uzytkownik user = new Uzytkownik(imie, nazwisko, pesel, telefon, email, username, password);
		if (username.charAt(0) != '#' && username.charAt(0) != '^'
				&& uzytkownikDao.validateUsername(username) == true) {
			try {
				uzytkownikDao.registerUser(user);
				response.sendRedirect("login.jsp");
			} catch (Exception e) {

				e.printStackTrace();
			}
		} else {
			String komentarz = "Podana nazwa u¿ytkownika jest niepoprawna lub zajêta!";
			RequestDispatcher dispatcher = request.getRequestDispatcher("user-registration.jsp");
			request.setAttribute("user", user);
			request.setAttribute("komentarz", komentarz);
			dispatcher.forward(request, response);
		}
	}

	private void listKonfOrg(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Konferencja> listKonf = konfDao.selectAllKonf();
		request.setAttribute("listKonf", listKonf);
		Organizator org = LoginDao.getCurrentOrganizer();
		request.setAttribute("uzyt", org);
		RequestDispatcher dispatcher = request.getRequestDispatcher("lista-konferencji-organizator.jsp");
		dispatcher.forward(request, response);
	}

	private void listKonfUser(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Konferencja> listKonf = konfDao.selectAllKonf();
		request.setAttribute("listKonf", listKonf);
		Uzytkownik user = LoginDao.getCurrentUser();
		request.setAttribute("uzyt", user);
		RequestDispatcher dispatcher = request.getRequestDispatcher("lista-konferencji-uzytkownik.jsp");
		dispatcher.forward(request, response);
	}

	private void listKonfRec(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Konferencja> listKonf = konfDao.selectAllKonf();
		request.setAttribute("listKonf", listKonf);
		Recenzent rec = LoginDao.getCurrentRecenzent();
		request.setAttribute("uzyt", rec);
		RequestDispatcher dispatcher = request.getRequestDispatcher("lista-konferencji-recenzent.jsp");
		dispatcher.forward(request, response);
	}

	private void listUser(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Uzytkownik> listUser = uzytkownikDao.listAllUsers();
		request.setAttribute("listUser", listUser);
		Organizator org = LoginDao.getCurrentOrganizer();
		request.setAttribute("uzyt", org);
		RequestDispatcher dispatcher = request.getRequestDispatcher("list-user.jsp");
		dispatcher.forward(request, response);
	}

	private void listaRecenzentow(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Recenzent> recenzenci = recenzentDao.listaRecenzentow();
		request.setAttribute("listaRecenzentow", recenzenci);
		Organizator org = LoginDao.getCurrentOrganizer();
		request.setAttribute("uzyt", org);
		RequestDispatcher dispatcher = request.getRequestDispatcher("lista-recenzentow.jsp");
		dispatcher.forward(request, response);

	}

	private void showSeeFormOrganizator(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id;
		if (request.getParameter("id") != null) {
			id = Integer.parseInt(request.getParameter("id"));
		} else {
			id = KonferencjaDao.CURRENT_KONFERENCJA.getId();
		}
		Konferencja k = konfDao.selectKonf(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("Konf.jsp");
		request.setAttribute("konf", k);
		List<Uzytkownik> users = konfDao.selectAllUczesnik(id);
		request.setAttribute("listUser", users);
		Organizator org = LoginDao.getCurrentOrganizer();
		request.setAttribute("uzyt", org);
		dispatcher.forward(request, response);
	}

	private void showSeeFormUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id;
		if (request.getParameter("id") != null) {
			id = Integer.parseInt(request.getParameter("id"));
		} else {
			id = KonferencjaDao.CURRENT_KONFERENCJA.getId();
		}
		Konferencja k = konfDao.selectKonf(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("konferencja-uzytkownik.jsp");
		request.setAttribute("konf", k);
		List<Uzytkownik> users = konfDao.selectAllUczesnik(id);
		request.setAttribute("listUser", users);
		Uzytkownik user = LoginDao.getCurrentUser();
		request.setAttribute("uzyt", user);
		dispatcher.forward(request, response);
	}

	private void showSeeFormRec(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id;
		if (request.getParameter("id") != null) {
			id = Integer.parseInt(request.getParameter("id"));
		} else {
			id = KonferencjaDao.CURRENT_KONFERENCJA.getId();
		}
		Konferencja k = konfDao.selectKonf(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("konferencja-recenzent.jsp");
		request.setAttribute("konf", k);
		List<Uzytkownik> users = konfDao.selectAllUczesnik(id);
		request.setAttribute("listUser", users);
		Recenzent rec = LoginDao.getCurrentRecenzent();
		request.setAttribute("uzyt", rec);
		dispatcher.forward(request, response);
	}

	private void showNewKonf(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("formularz-konferencji.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditKonf(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Konferencja k = konfDao.selectKonf(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("formularz-konferencji.jsp");
		request.setAttribute("konferencja", k);
		dispatcher.forward(request, response);

	}

	private void insertKonf(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		String nazwa = request.getParameter("nazwa");
		String koszt = request.getParameter("koszt");
		String opis = request.getParameter("opis");
		String adres = request.getParameter("adres");
		String organizator = request.getParameter("organizator");
		String dataRoz = request.getParameter("dataRoz");
		String dataZap = request.getParameter("dataZap");
		String godzina = request.getParameter("godzina");
		String harmonogram = request.getParameter("harmonogram");
		Konferencja konf = new Konferencja(nazwa, opis, Integer.valueOf(koszt), adres, organizator, dataRoz, dataZap,
				godzina, harmonogram);
		konfDao.insertKonf(konf);
		response.sendRedirect("listKonfOrg");
	}

	private void updateKonf(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String nazwa = request.getParameter("nazwa");
		String koszt = request.getParameter("koszt");
		String opis = request.getParameter("opis");
		String adres = request.getParameter("adres");
		String organizator = request.getParameter("organizator");
		String dataRoz = request.getParameter("dataRoz");
		String dataZap = request.getParameter("dataZap");
		String godzina = request.getParameter("godzina");
		String harmonogram = request.getParameter("harmonogram");
		Konferencja k = new Konferencja(id, nazwa, opis, Integer.valueOf(koszt), adres, organizator, dataRoz, dataZap,
				godzina, harmonogram);
		konfDao.updateKonf(k);
		response.sendRedirect("listKonfOrg");
	}

	private void setStatus(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int idU = Integer.parseInt(request.getParameter("id"));
		int idK = KonferencjaDao.CURRENT_KONFERENCJA.getId();
		konfDao.updateStatus(idU, idK);
		response.sendRedirect("seeOrg");
	}

	private void zapiszNaKonf(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Uzytkownik user = LoginDao.getCurrentUser();
		konfDao.zapiszNaKonferencje(id, user.getId());
		response.sendRedirect("seeUser");
	}

	private void rezygnujKonf(HttpServletRequest request, HttpServletResponse response)
			throws IOException, SQLException {
		int idU = LoginDao.getCurrentUser().getId();
		int idK = KonferencjaDao.CURRENT_KONFERENCJA.getId();
		konfDao.deleteUserKonf(idU, idK);
		response.sendRedirect("seeUser");
	}

	private void deleteKonf(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		konfDao.deleteKonf(id);
		response.sendRedirect("listKonfOrg");
	}

	private void deleteUserKonf(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		int idU = Integer.parseInt(request.getParameter("id"));
		int idK = KonferencjaDao.CURRENT_KONFERENCJA.getId();
		konfDao.deleteUserKonf(idU, idK);
		response.sendRedirect("seeOrg");
	}

	private void showUserEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Uzytkownik existingUser = uzytkownikDao.selectUser(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("formularz-edycji-uzytkownika.jsp");
		request.setAttribute("user", existingUser);
		dispatcher.forward(request, response);
	}

	private void updateUser(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		int id = Integer.parseInt(request.getParameter("id"));
		String imie = request.getParameter("imie");
		String nazwisko = request.getParameter("nazwisko");
		String pesel = request.getParameter("pesel");
		String telefon = request.getParameter("telefon");
		String email = request.getParameter("email");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Uzytkownik user = new Uzytkownik(id, imie, nazwisko, pesel, telefon, email, username, password);
		Uzytkownik pom = uzytkownikDao.selectUser(id);
		if (username.equals(pom.getUsername())) {
			uzytkownikDao.updateUser(user);
		} else {
			if (username.charAt(0) != '#' && username.charAt(0) != '^' && uzytkownikDao.validateUsername(username)) {
				uzytkownikDao.updateUser(user);
			} else {
				String komentarz = "Podany username jest niepoprawny lub zajêty!";
				RequestDispatcher dispatcher = request.getRequestDispatcher("formularz-edycji-uzytkownika.jsp");
				request.setAttribute("user", user);
				request.setAttribute("komentarz", komentarz);
				dispatcher.forward(request, response);
			}
		}
		if (LoginDao.getCurrentUser() != null) {
			response.sendRedirect("listKonfUser");
		} else if (LoginDao.getCurrentOrganizer() != null) {
			response.sendRedirect("listaUzytkownik");
		}
	}

	private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		uzytkownikDao.deleteUser(id);
		response.sendRedirect("listaUzytkownik");
	}

	private void dodajRecenzentow(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ClassNotFoundException, ServletException {
		String tytul = request.getParameter("tytul");
		String imie = request.getParameter("imie");
		String nazwisko = request.getParameter("nazwisko");
		String pesel = request.getParameter("pesel");
		String telefon = request.getParameter("telefon");
		String email = request.getParameter("email");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Recenzent rec = new Recenzent(tytul, imie, nazwisko, pesel, telefon, email, username, password);
		if (username.charAt(0) == '^' && recenzentDao.validateUsername(username)) {
			recenzentDao.registerRecenzent(rec);
			response.sendRedirect("listaRecenzent");
		} else {
			String komentarz = "Podany username jest niepoprawny lub zajêty!";
			RequestDispatcher dispatcher = request.getRequestDispatcher("formularz-nowego-recenzenta.jsp");
			request.setAttribute("rec", rec);
			request.setAttribute("komentarz", komentarz);
			dispatcher.forward(request, response);
		}
	}

	private void edytujRecenzentow(HttpServletRequest request, HttpServletResponse response)
			throws IOException, SQLException, ServletException {
		int id = Integer.parseInt(request.getParameter("id"));
		String tytul = request.getParameter("tytul");
		String imie = request.getParameter("imie");
		String nazwisko = request.getParameter("nazwisko");
		String pesel = request.getParameter("pesel");
		String telefon = request.getParameter("telefon");
		String email = request.getParameter("email");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Recenzent rec = new Recenzent(id, tytul, imie, nazwisko, pesel, telefon, email, username, password);
		Recenzent pom = recenzentDao.selectRecenzent(id);
		if (pom.getUsername().equals(username)) {
			recenzentDao.updateRecenzent(rec);
		} else {
			if (username.charAt(0) == '^' && recenzentDao.validateUsername(username)) {
				recenzentDao.updateRecenzent(rec);
			} else {
				String komentarz = "Podany username jest niepoprawny lub zajêty!";
				RequestDispatcher dispatcher = request.getRequestDispatcher("formularz-edycji-recenzenta.jsp");
				request.setAttribute("rec", rec);
				request.setAttribute("komentarz", komentarz);
				dispatcher.forward(request, response);
			}
		}
		if (LoginDao.getCurrentRecenzent() != null) {
			response.sendRedirect("listKonfRec");
		} else if (LoginDao.getCurrentOrganizer() != null) {
			response.sendRedirect("listaRecenzent");
		}

	}

	private void usunRecenzenta(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		recenzentDao.usunRec(id);
		recenzentDao.deleteRecenzent(id);
		response.sendRedirect("listaRecenzent");
	}

	private void formularzDodaniaRecenzenta(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("formularz-nowego-recenzenta.jsp");
		dispatcher.forward(request, response);
	}

	private void formularzEdycjiRecenzenta(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Recenzent rec = recenzentDao.selectRecenzent(id);
		request.setAttribute("rec", rec);
		RequestDispatcher dispatcher = request.getRequestDispatcher("formularz-edycji-recenzenta.jsp");
		dispatcher.forward(request, response);
	}

	private void listaPracUzytkownika(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id_uzytkownika = LoginDao.getCurrentUser().getId();
		List<Praca> prace = pracaDao.praceUzytkownika(id_uzytkownika);
		request.setAttribute("listaPrac", prace);
		Uzytkownik user = LoginDao.getCurrentUser();
		request.setAttribute("uzyt", user);
		RequestDispatcher dispatcher = request.getRequestDispatcher("lista-prac-uzytkownika.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewPraca(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("formularz-nowej-pracy.jsp");
		dispatcher.forward(request, response);
	}

	private void dodajPrace(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String tytul = request.getParameter("tytul");
		int id_uzytkownika = LoginDao.getCurrentUser().getId();
		int id_konferencji = Integer.parseInt(request.getParameter("id_konferencji"));
		String uploadPath = request.getParameter("uploadPath");
		Praca praca = new Praca(tytul, id_uzytkownika, id_konferencji, uploadPath);
		// Character [] s = ".pdf";
		if (uploadPath.contains(".pdf")) {
			InputStream artykul = null;
			try {
				artykul = new FileInputStream(new File(uploadPath));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			if (artykul != null) {
				pracaDao.dodajPrace(praca);
				response.sendRedirect("mojePrace");
			} else {
				String komentarz = "Podana œcie¿ka pliku jest niew³aœciwa!";
				request.setAttribute("komentarz", komentarz);
				RequestDispatcher dispatcher = request.getRequestDispatcher("formularz-nowej-pracy.jsp");
				dispatcher.forward(request, response);
			}
		} else {
			String komentarz = "Podanu plik nie jest w formacie pdf!";
			request.setAttribute("komentarz", komentarz);
			RequestDispatcher dispatcher = request.getRequestDispatcher("formularz-nowej-pracy.jsp");
			dispatcher.forward(request, response);
		}
	}

	private void listaPrac(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Praca> prace = pracaDao.listaPrac();
		request.setAttribute("listaPrac", prace);
		Recenzent rec = LoginDao.getCurrentRecenzent();
		request.setAttribute("uzyt", rec);
		RequestDispatcher dispatcher = request.getRequestDispatcher("lista-prac.jsp");
		dispatcher.forward(request, response);
	}

	private void listaOcen(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id_recenzenta = LoginDao.getCurrentRecenzent().getId();
		List<Praca> ocenyPrac = recenzentDao.listaOcen(id_recenzenta);
		request.setAttribute("listaOcen", ocenyPrac);
		Recenzent rec = LoginDao.getCurrentRecenzent();
		request.setAttribute("uzyt", rec);
		RequestDispatcher dispatcher = request.getRequestDispatcher("lista-ocen.jsp");
		dispatcher.forward(request, response);
	}

	private void pobierzPrace(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		pracaDao.pobierzPrace(id);
		if (LoginDao.getCurrentRecenzent() != null) {
			response.sendRedirect("wyswietlPrace");
		} else if (LoginDao.getCurrentUser() != null) {
			response.sendRedirect("mojePrace");
		}
	}

	private void ocenPrace(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		int id_recenzenta = LoginDao.getCurrentRecenzent().getId();
		int id = Integer.parseInt(request.getParameter("id"));
		int ocena = Integer.parseInt(request.getParameter("ocena"));
		if (ocena == 0 || ocena == 1) {
			pracaDao.ocenPrace(id, ocena);
			pracaDao.zapiszOcene(id_recenzenta, id, ocena);
			response.sendRedirect("wyswietlPrace");
		} else {
			String uwaga = "Podano niepoprawn¹ ocene!";
			Praca wybranaPraca = pracaDao.selectPraca(id);
			request.setAttribute("praca", wybranaPraca);
			request.setAttribute("komentarz", uwaga);
			RequestDispatcher dispatcher = request.getRequestDispatcher("formularz-oceny-pracy.jsp");
			dispatcher.forward(request, response);
		}
	}

	private void showFormularzOceny(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		int id = Integer.parseInt(request.getParameter("id"));
		int id_recenzenta = LoginDao.getCurrentRecenzent().getId();

		if (recenzentDao.czyOceniono(id_recenzenta, id) == false) {
			Praca wybranaPraca = pracaDao.selectPraca(id);
			RequestDispatcher dispatcher = request.getRequestDispatcher("formularz-oceny-pracy.jsp");
			request.setAttribute("praca", wybranaPraca);
			dispatcher.forward(request, response);
		} else {
			response.sendRedirect("wyswietlPrace");
		}
	}

	private void usunPrace(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		pracaDao.delPraca(id);
		pracaDao.usunPrace(id);
		if (LoginDao.getCurrentRecenzent() != null) {
			response.sendRedirect("wyswietlPrace");
		} else if (LoginDao.getCurrentUser() != null) {
			response.sendRedirect("mojePrace");
		}
	}

	private void edytujOrganizatora(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		int id = Integer.parseInt(request.getParameter("id"));
		String imie = request.getParameter("imie");
		String nazwisko = request.getParameter("nazwisko");
		String pesel = request.getParameter("pesel");
		String telefon = request.getParameter("telefon");
		String email = request.getParameter("email");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Organizator org = new Organizator(id, imie, nazwisko, pesel, telefon, email, username, password);
		Organizator pom = uzytkownikDao.selectOrganizator(id);
		if (pom.getUsername().equals(username)) {
			uzytkownikDao.updateOrganizer(org);
			response.sendRedirect("listKonfOrg");
		} else {
			if (uzytkownikDao.validateOrganizatorUsername(username) == true && username.charAt(0) == '#') {
				uzytkownikDao.updateOrganizer(org);
				response.sendRedirect("listKonfOrg");
			} else {
				String komentarz = "Podany username jest niepoprawny lub zajêty!";
				RequestDispatcher dispatcher = request.getRequestDispatcher("formularz-edycji-organizatora.jsp");
				request.setAttribute("org", org);
				request.setAttribute("komentarz", komentarz);
				dispatcher.forward(request, response);
			}
		}
	}

	private void formularzEdycjiOrganizatora(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = LoginDao.getCurrentOrganizer().getId();
		Organizator org = uzytkownikDao.selectOrganizator(id);
		request.setAttribute("org", org);
		RequestDispatcher dispatcher = request.getRequestDispatcher("formularz-edycji-organizatora.jsp");
		dispatcher.forward(request, response);

	}

	private void wyloguj(HttpServletRequest request, HttpServletResponse response) throws IOException {
		LoginDao.wyloguj();
		response.sendRedirect("login.jsp");
	}

	private void kontakt(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Organizator> listOrg = uzytkownikDao.listaOrganizatorow();
		request.setAttribute("listOrg", listOrg);
		Dane dane = Dane.wczytajPlik();
		request.setAttribute("dane", dane);
		RequestDispatcher dispatcher = request.getRequestDispatcher("kontakt.jsp");
		dispatcher.forward(request, response);
	}

}