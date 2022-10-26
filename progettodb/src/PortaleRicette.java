import java.util.Scanner;

public class PortaleRicette {

    public static void main (String[] args) {

        var service = new OperationsDB();

        System.out.println("Benvenuto nel portale di ricette, scegli l'operazione che vuoi svolgere:" +
                "\n1. Aggiungi nuovo utente." +
                "\n2. Aggiungi nuova ricetta." +
                "\n3. Aggiungi recensione ad una ricetta (con o senza voto)." +
                "\n4. Selezionare tutte le ricette di un utente." +
                "\n5. Selezionare tutte le ricette di una categoria." +
                "\n6. Selezionare le ricette presenti in tutti i preferiti." +
                "\n7. Rimuovere una ricetta." +
                "\n8. Selezionare lista ricette ordinate per tempo totale di preparazione (preparazione+cottura)" +
                "crescente nella lista dei preferiti di un certo utente." +
                "\n9. Selezionare il numero di ricette inserite da un utente." +
                "\n10. Selezionare una lista di email ordinate in ordine alfabetico corrispondenti agli utenti" +
                "aventi numero di telefono con prefisso Italiano, Francese o Tedesco" +
                "\n\n Seleziona la tua scelta e dai invio.");

        Scanner sc = new Scanner(System.in);
        switch(sc.nextInt()) {
            case 1:
                Utente utente = new Utente();
                System.out.println("Inserisci dati utente");
                System.out.println("Email:");
                utente.setEmail(sc.next());
                System.out.println("Password:");
                utente.setPsw(sc.next());
                System.out.println("Nome:");
                utente.setNome(sc.next());
                System.out.println("Cognome:");
                utente.setCognome(sc.next());
                System.out.println("Telefono:");
                utente.setTelefono(sc.next());
                System.out.println("Indrizzo:");
                utente.setVia(sc.next());
                System.out.println("Data di nascita (aaaa-mm-gg):");
                utente.setDataNascita(sc.next());
                System.out.println("Foto:");
                utente.setFoto(sc.next());

                service.operazioneUno(utente);

                System.out.println("Operazione eseguita con successo. Query eseguita: \n" +
                        "INSERT INTO Utente (\n" +
                        "\temail, \n" +
                        "\tpsw, \n" +
                        "    nome, \n" +
                        "    cognome, \n" +
                        "    numTelefono, \n" +
                        "    idVia, \n" +
                        "    dataNascita, \n" +
                        "    foto\n" +
                        ") VALUES (\n" +
                        "\tvalue_email, \n" +
                        "    value_psw, \n" +
                        "    value_cognome, \n" +
                        "    value_numTelefono, \n" +
                        "    value_idVia, \n" +
                        "    value_dataNascita, \n" +
                        "    value_foto\n" +
                        ");");
                break;
            case 2:
                System.out.println("Prova 2");
                break;
            case 3:
                System.out.println("Prova 3");
                break;
            case 4:
                System.out.println("Prova 4");
                break;
            case 5:
                System.out.println("Prova 5");
                break;
            case 6:
                System.out.println("Prova 6");
                break;
            case 7:
                System.out.println("Prova 7");
                break;
            case 8:
                System.out.println("Prova 8");
                break;
            case 9:
                System.out.println("Prova 9");
                break;
            case 10:
                System.out.println("Prova 10");
                break;
            default:
                System.out.println("Errore. Scelta non valida!");
        }
    }
}
