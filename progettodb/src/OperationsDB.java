import java.sql.*;
import java.util.ArrayList;

public class OperationsDB {

    //OPERAZIONE #1
    public void operazioneUno(Utente utente) {

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ricette", "root", "admin")) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO utente (" +
                            "email, " +
                            "psw, " +
                            "nome, " +
                            "cognome, " +
                            "numTelefono, " +
                            "dataNascita, " +
                            "foto, " +
                            "numRicettePubblicate, " +
                            "cap, " +
                            "via, " +
                            "civico" +
                            ") VALUES(?,?,?,?,?,?,?,?,?,?,?)");

            ps.setString(1, utente.getEmail());
            ps.setString(2, utente.getPsw());
            ps.setString(3, utente.getNome());
            ps.setString(4, utente.getCognome());
            ps.setString(5, utente.getTelefono());
            ps.setString(6, utente.getDataNascita());
            ps.setString(7, utente.getFoto());
            ps.setInt(8, utente.getNumRicettePubblicate());
            ps.setInt(9, utente.getCap());
            ps.setString(10, utente.getVia());
            ps.setInt(11, utente.getCivico());


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
                    "INSERT INTO ricetta (" +
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

    //OPERAZIONE #3
    public void deleteRicetta(int idRicetta) {

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ricette", "root", "admin")) {

            PreparedStatement ps = con.prepareStatement("DELETE FROM ricetta WHERE idRicetta = ?");

            ps.setInt(1, idRicetta);

            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("DELETE error.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //OPERAZIONE #4
    public ArrayList<Ricetta> operazioneQuattro(String email) {

        ArrayList<Ricetta> listaRicette = new ArrayList<>();

        ResultSet resultSet;

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ricette", "root", "admin")) {

            PreparedStatement ps = con.prepareStatement("SELECT * FROM ricetta WHERE email = ?");

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
                ricetta.setEmail(resultSet.getString(8));
                ricetta.setIdCategoria(resultSet.getInt(9));

                listaRicette.add(ricetta);
            }
            return listaRicette;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //OPERAZIONE #5
    public void operazioneCinque(String email, String nuovoTel){

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ricette", "root", "admin")) {

            PreparedStatement ps = con.prepareStatement("UPDATE utente SET numTelefono = ? WHERE email = ?");

            ps.setString(1, nuovoTel);
            ps.setString(2, email);

            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("UPDATE error.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<String> operazioneSei(){

        ArrayList<String> listaEmail = new ArrayList<>();

        ResultSet resultSet;

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ricette", "root", "admin")) {

            String query = "SELECT email FROM utente u WHERE NOT EXISTS "
                    + "(SELECT * FROM ricetta r WHERE NOT EXISTS "
                    + "(SELECT * FROM recensione rc WHERE u.email = rc.email "
                    + "AND r.idRicetta = rc.idRicetta))";

            PreparedStatement ps = con.prepareStatement(query);

            resultSet = ps.executeQuery();

            while (resultSet.next()) {

                String email = resultSet.getString(1);

                listaEmail.add(email);
            }
            return listaEmail;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}