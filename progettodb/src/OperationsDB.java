import java.sql.*;
import java.util.ArrayList;

public class OperationsDB {

    public ArrayList<Categoria> doRetrieveAllCategoria() {

        ResultSet resultSet;

        ArrayList<Categoria> lista = new ArrayList<>();

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ricette", "root", "admin")) {

            PreparedStatement ps = con.prepareStatement("SELECT * FROM Categoria;");
            resultSet = ps.executeQuery();

            while (resultSet.next()) {

                Categoria categoria = new Categoria();
                categoria.setIdCategoria(resultSet.getInt(1));
                categoria.setNome(resultSet.getString(2));

                lista.add(categoria);
            }
            return lista;

        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public ArrayList<Telefono> doRetrieveAllTelefono() {

        ResultSet resultSet;

        ArrayList<Telefono> lista = new ArrayList<>();

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ricette", "root", "admin")) {

            PreparedStatement ps = con.prepareStatement("SELECT * FROM Telefono;");
            resultSet = ps.executeQuery();

            while (resultSet.next()) {

                Telefono telefono = new Telefono();
                telefono.setNumero(resultSet.getString(1));
                telefono.setEmail(resultSet.getString(2));

                lista.add(telefono);
            }
            return lista;

        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public ArrayList<Cap> doRetrieveAllCap() {

        ResultSet resultSet;

        ArrayList<Cap> lista = new ArrayList<>();

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ricette", "root", "admin")) {

            PreparedStatement ps = con.prepareStatement("SELECT * FROM Cap;");
            resultSet = ps.executeQuery();

            while (resultSet.next()) {

                Cap cap = new Cap();
                cap.setIdCap(resultSet.getInt(1));
                cap.setCap(resultSet.getInt(2));

                lista.add(cap);
            }
            return lista;

        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public ArrayList<Civico> doRetrieveAllCivico() {

        ResultSet resultSet;

        ArrayList<Civico> lista = new ArrayList<>();

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ricette", "root", "admin")) {

            PreparedStatement ps = con.prepareStatement("SELECT * FROM Civico;");
            resultSet = ps.executeQuery();

            while (resultSet.next()) {

                Civico civico = new Civico();
                civico.setIdCivico(resultSet.getInt(1));
                civico.setNumero(resultSet.getInt(2));
                civico.setCap(resultSet.getInt(3));

                lista.add(civico);
            }
            return lista;

        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public ArrayList<Via> doRetrieveAllVia() {

        ResultSet resultSet;

        ArrayList<Via> lista = new ArrayList<>();

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ricette", "root", "admin")) {

            PreparedStatement ps = con.prepareStatement("SELECT * FROM Via;");
            resultSet = ps.executeQuery();

            while (resultSet.next()) {

                Via via = new Via();
                via.setIdVia(resultSet.getInt(1));
                via.setNome(resultSet.getString(2));
                via.setCivico(resultSet.getInt(3));

                lista.add(via);
            }
            return lista;

        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public ArrayList<Utente> doRetrieveAllUtente() {

        ResultSet resultSet;

        ArrayList<Utente> lista = new ArrayList<>();

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ricette", "root", "admin")) {

            PreparedStatement ps = con.prepareStatement("SELECT * FROM Utente;");
            resultSet = ps.executeQuery();

            while (resultSet.next()) {

                Utente utente = new Utente();
                utente.setEmail(resultSet.getString(1));
                utente.setPsw(resultSet.getString(2));
                utente.setNome(resultSet.getString(3));
                utente.setCognome(resultSet.getString(4));
                utente.setTelefono(resultSet.getString(5));
                utente.setVia(resultSet.getString(6));
                utente.setDataNascita(resultSet.getString(7));
                utente.setFoto(resultSet.getString(8));

                lista.add(utente);
            }
            return lista;

        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public ArrayList<Ricetta> doRetrieveAllRicetta() {

        ResultSet resultSet;

        ArrayList<Ricetta> lista = new ArrayList<>();

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ricette", "root", "admin")) {

            PreparedStatement ps = con.prepareStatement("SELECT * FROM Ricetta;");
            resultSet = ps.executeQuery();

            while (resultSet.next()) {

                Ricetta ricetta = new Ricetta();
                ricetta.setIdRicetta(resultSet.getInt(1));
                ricetta.setNome(resultSet.getString(2));
                ricetta.setFoto(resultSet.getString(3));
                ricetta.setProcedimento(resultSet.getString(4));
                ricetta.setTempoCottura(resultSet.getInt(5));
                ricetta.setTempoPreparazione(resultSet.getInt(6));
                ricetta.setKcal(resultSet.getInt(7));
                ricetta.setEmail(resultSet.getString(8));
                ricetta.setIdCategoria((resultSet.getInt(9)));

                lista.add(ricetta);
            }
            return lista;

        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public ArrayList<Preferito> doRetrieveAllPreferito() {

        ResultSet resultSet;

        ArrayList<Preferito> lista = new ArrayList<>();

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ricette", "root", "admin")) {

            PreparedStatement ps = con.prepareStatement("SELECT * FROM Preferito;");
            resultSet = ps.executeQuery();

            while (resultSet.next()) {

                Preferito preferito = new Preferito();
                preferito.setIdRicetta(resultSet.getInt(1));
                preferito.setEmail(resultSet.getString(2));

                lista.add(preferito);
            }
            return lista;

        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public ArrayList<TipologiaRecensione> doRetrieveAllTipologiaRecensione() {

        ResultSet resultSet;

        ArrayList<TipologiaRecensione> lista = new ArrayList<>();

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ricette", "root", "admin")) {

            PreparedStatement ps = con.prepareStatement("SELECT * FROM TipologiaRecensione;");
            resultSet = ps.executeQuery();

            while (resultSet.next()) {

                TipologiaRecensione tipologiaRecensione = new TipologiaRecensione();
                tipologiaRecensione.setIdTipologiaRecensione(resultSet.getInt(1));
                tipologiaRecensione.setTipoRecensione(resultSet.getString(2));

                lista.add(tipologiaRecensione);
            }
            return lista;

        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public ArrayList<Recensione> doRetrieveAllRecensione() {

        ResultSet resultSet;

        ArrayList<Recensione> lista = new ArrayList<>();

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ricette", "root", "admin")) {

            PreparedStatement ps = con.prepareStatement("SELECT * FROM Recensione;");
            resultSet = ps.executeQuery();

            while (resultSet.next()) {

                Recensione recensione = new Recensione();
                recensione.setIdRecensione(resultSet.getInt(1));
                recensione.setTesto(resultSet.getString(2));
                recensione.setIdTipologiaRecensione(resultSet.getInt(3));

                lista.add(recensione);
            }
            return lista;

        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public ArrayList<Scrive> doRetrieveAllScrive() {

        ResultSet resultSet;

        ArrayList<Scrive> lista = new ArrayList<>();

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ricette", "root", "admin")) {

            PreparedStatement ps = con.prepareStatement("SELECT * FROM Scrive;");
            resultSet = ps.executeQuery();

            while (resultSet.next()) {

                Scrive scrive = new Scrive();
                scrive.setEmail(resultSet.getString(1));
                scrive.setIdRecensione(resultSet.getInt(2));
                scrive.setIdRicetta(resultSet.getInt(3));

                lista.add(scrive);
            }
            return lista;

        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    //OPERAZIONE #1
    public void operazioneUno(Utente utente) {

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ricette", "root", "admin")) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO Utente (" +
                            "email, " +
                            "psw, " +
                            "nome, " +
                            "cognome, " +
                            "numTelefono, " +
                            "idVia, " +
                            "dataNascita, " +
                            "foto" +
                            ") VALUES(?,?,?,?,?,?,?,?)");

            ps.setString(1, utente.getEmail());
            ps.setString(2, utente.getPsw());
            ps.setString(3, utente.getNome());
            ps.setString(4, utente.getCognome());
            ps.setString(5, utente.getTelefono());
            ps.setString(6, utente.getVia());
            ps.setString(7, utente.getDataNascita());
            ps.setString(8, utente.getFoto());

            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //OPERAZIONE #2
    public void operazioneDue(Ricetta ricetta) {

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ricette", "root", "admin")) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO Ricetta (" +
                            "idRicetta, " +
                            "nome, " +
                            "foto, " +
                            "procedimento, " +
                            "tempoCottura, " +
                            "tempoPreparazione, " +
                            "kcal, " +
                            "email, " +
                            "idCategoria" +
                            ") VALUES(?,?,?,?,?,?,?,?,?)");

            ps.setInt(1, ricetta.getIdRicetta());
            ps.setString(2, ricetta.getNome());
            ps.setString(3, ricetta.getFoto());
            ps.setString(4, ricetta.getProcedimento());
            ps.setInt(5, ricetta.getTempoCottura());
            ps.setInt(6, ricetta.getTempoPreparazione());
            ps.setInt(7, ricetta.getKcal());
            ps.setString(8, ricetta.getEmail());
            ps.setInt(9, ricetta.getIdCategoria());

            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //OPERAZIONE 3
    public void operazioneTre(Recensione recensione, Scrive scrive) {

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ricette", "root", "admin")) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO Recensione (" +
                            "idRecensione, " +
                            "testo, " +
                            "idTipologiaRecensione" +
                            ") VALUES(?,?,?)");

            ps.setInt(1, recensione.getIdRecensione());
            ps.setString(2, recensione.getTesto());
            ps.setInt(3, recensione.getIdTipologiaRecensione());

            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ricette", "root", "admin")) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO Scrive (" +
                            "email, " +
                            "idRecensione, " +
                            "idRicetta" +
                            ") VALUES(?,?,?)");

            ps.setString(1, scrive.getEmail());
            ps.setInt(2, scrive.getIdRecensione());
            ps.setInt(3, scrive.getIdRicetta());

            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //OPERAZIONE 4
    public ArrayList<Ricetta> operazioneQuattro(String email) {

        ArrayList<Ricetta> listaRicette = new ArrayList<>();

        ResultSet resultSet;

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ricette", "root", "admin")) {

            PreparedStatement ps = con.prepareStatement("SELECT * FROM ricetta WHERE email=?");

            ps.setString(1, email);

            resultSet = ps.executeQuery();

            while (resultSet.next()) {

                Ricetta ricetta = new Ricetta();

                ricetta.setIdRicetta(resultSet.getInt(1));
                ricetta.setNome(resultSet.getString(2));
                ricetta.setFoto(resultSet.getString(3));
                ricetta.setProcedimento(resultSet.getString(4));
                ricetta.setTempoCottura(resultSet.getInt(5));
                ricetta.setTempoPreparazione(resultSet.getInt(6));
                ricetta.setKcal(resultSet.getInt(7));
                ricetta.setEmail(email);
                ricetta.setIdCategoria(resultSet.getInt(9));

                listaRicette.add(ricetta);
            }
            return listaRicette;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //OPERAZIONE 5
    public ArrayList<Ricetta> operazioneCinque(int idCategoria) {

        ArrayList<Ricetta> listaRicette = new ArrayList<>();

        ResultSet resultSet;

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ricette", "root", "admin")) {

            PreparedStatement ps = con.prepareStatement("SELECT * FROM ricetta WHERE idCategoria=?");

            ps.setInt(1, idCategoria);

            resultSet = ps.executeQuery();

            while (resultSet.next()) {

                Ricetta ricetta = new Ricetta();

                ricetta.setIdRicetta(resultSet.getInt(1));
                ricetta.setNome(resultSet.getString(2));
                ricetta.setFoto(resultSet.getString(3));
                ricetta.setProcedimento(resultSet.getString(4));
                ricetta.setTempoCottura(resultSet.getInt(5));
                ricetta.setTempoPreparazione(resultSet.getInt(6));
                ricetta.setKcal(resultSet.getInt(7));
                ricetta.setEmail(resultSet.getString(8));
                ricetta.setIdCategoria(idCategoria);

                listaRicette.add(ricetta);
            }
            return listaRicette;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //OPERAZIONE 6
    public ArrayList<Ricetta> operazioneSei() {

        ArrayList<Ricetta> listaRicette = new ArrayList<>();

        ResultSet resultSet;

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ricette", "root", "admin")) {

            PreparedStatement ps = con.prepareStatement("SELECT *" +
                    "FROM Ricetta INNER JOIN Preferito ON Ricetta.idRicetta = Preferito.idRicetta");

            resultSet = ps.executeQuery();

            while (resultSet.next()) {

                Ricetta ricetta = new Ricetta();

                ricetta.setIdRicetta(resultSet.getInt(1));
                ricetta.setNome(resultSet.getString(2));
                ricetta.setFoto(resultSet.getString(3));
                ricetta.setProcedimento(resultSet.getString(4));
                ricetta.setTempoCottura(resultSet.getInt(5));
                ricetta.setTempoPreparazione(resultSet.getInt(6));
                ricetta.setKcal(resultSet.getInt(7));
                ricetta.setEmail(resultSet.getString(8));
                ricetta.setIdCategoria(resultSet.getInt(9));

                listaRicette.add(ricetta);
            }
            return listaRicette;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //OPERAZIONE 7
    public void deleteRicetta(int idRicetta) {

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ricette", "root", "admin")) {

            PreparedStatement ps = con.prepareStatement("DELETE FROM ricetta WHERE idRicetta=?");

            ps.setInt(1, idRicetta);

            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("DELETE error.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //OPERAZIONE 8
    public ArrayList<Ricetta> operazioneOtto(String email) {

        ArrayList<Ricetta> listaRicette = new ArrayList<>();

        ResultSet resultSet;

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ricette", "root", "admin")) {

            PreparedStatement ps = con.prepareStatement("SELECT *" +
                    "FROM Ricetta" +
                    "WHERE Preferito.email = ? AND Ricetta.email = ?" +
                    "ORDER BY SUM(Ricetta.tempoPreparazione * Ricetta.tempoCottura) ASC;");

            ps.setString(1, email);
            ps.setString(2, email);

            resultSet = ps.executeQuery();

            while (resultSet.next()) {

                Ricetta ricetta = new Ricetta();

                ricetta.setIdRicetta(resultSet.getInt(1));
                ricetta.setNome(resultSet.getString(2));
                ricetta.setFoto(resultSet.getString(3));
                ricetta.setProcedimento(resultSet.getString(4));
                ricetta.setTempoCottura(resultSet.getInt(5));
                ricetta.setTempoPreparazione(resultSet.getInt(6));
                ricetta.setKcal(resultSet.getInt(7));
                ricetta.setEmail(resultSet.getString(8));
                ricetta.setIdCategoria(resultSet.getInt(9));

                listaRicette.add(ricetta);
            }
            return listaRicette;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //OPERAZIONE 9
    public int operazioneNove(String email) {

        int i = 0;

        ResultSet resultSet;

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ricette", "root", "admin")) {

            PreparedStatement ps = con.prepareStatement("" +
                    "SELECT COUNT(DISTINCT idRicetta)" +
                    "FROM Ricetta" +
                    "WHERE email LIKE ?;");

            ps.setString(1, email);

            resultSet = ps.executeQuery();

            while (resultSet.next()) {
                i = resultSet.getInt(1);
            }
            return i;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //OPERAZIONE 10
    public ArrayList<String> operazioneDieci() {

        ResultSet resultSet;

        ArrayList<String> lista = new ArrayList<>();

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ricette", "root", "admin")) {

            PreparedStatement ps = con.prepareStatement("SELECT email" +
                    "FROM Utente" +
                    "WHERE Utente.telefono IN (\"+39%\",\"+33%\",\"+49%\")" +
                    "ORDER BY email ASC;");

            resultSet = ps.executeQuery();

            while (resultSet.next()) {

                String email;
                email = resultSet.getString(1);

                lista.add(email);
            }
            return lista;

        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    //PRENDERE ULTIMO INDICE RECENSIONI
    public int retrieveNumberOfRecensioni() {

        int num = 0;

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ricette", "root", "admin")) {

            PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) FROM recensione");

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                num = rs.getInt(1);

            }
            return num;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}