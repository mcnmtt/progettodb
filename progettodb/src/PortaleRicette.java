import java.util.ArrayList;
import java.util.Scanner;

public class PortaleRicette {

    public static void main (String[] args) {

        System.out.println("Benvenuto nel portale di ricette, scegli l'operazione che vuoi svolgere:" +
                "\n1. Aggiungi nuovo utente." +
                "\n2. Aggiungi nuova ricetta." +
                "\n3. Rimuovere una ricetta." +
                "\n4. Selezionare il numero di ricette inserite da un utente." +
                "\n5. Modificare il numero di telefono di un utente." +
                "\n6. Elencare gli utenti che hanno recensito tutte le ricette." +
                "\n\n Seleziona la tua scelta e dai invio.");

        Scanner sc = new Scanner(System.in);
        var service = new OperationsDB();

        switch(sc.nextInt()) {
            case 1:
                System.out.println("Hai scelto 'Aggiungi nuovo utente.'");
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
                System.out.println("Data di nascita (aaaa-mm-gg):");
                utente.setDataNascita(sc.next());
                System.out.println("Foto:");
                utente.setFoto(sc.next());
                System.out.println("Numero ricette pubblicate:");
                utente.setNumRicettePubblicate(sc.nextInt());
                System.out.println("Cap:");
                utente.setCap(sc.nextInt());
                System.out.println("Civico:");
                utente.setCivico(sc.nextInt());
                System.out.println("Via:");
                utente.setVia(sc.next());
                utente.setVia(sc.nextLine());
                sc.close();

                service.operazioneUno(utente);

                System.out.println("Operazione eseguita con successo.\n");

                break;
            case 2:
                System.out.println("Hai scelto 'Aggiungi nuova ricetta.'");
                Ricetta ricetta = new Ricetta();
                System.out.println("Inserisci dati ricetta");
                System.out.println("Nome:");
                ricetta.setNome(sc.next());
                System.out.println("Foto:");
                ricetta.setFoto(sc.next());
                System.out.println("Procedimento:");
                ricetta.setProcedimento(sc.next());
                System.out.println("Tempo cottura:");
                ricetta.setTempoCottura(sc.nextInt());
                System.out.println("Tempo preparazione:");
                ricetta.setTempoPreparazione(sc.nextInt());
                System.out.println("Kcal:");
                ricetta.setKcal(sc.nextInt());
                System.out.println("Email:");
                ricetta.setEmail(sc.next());
                System.out.println("ID Categoria:");
                ricetta.setIdCategoria(sc.nextInt());

                service.operazioneDue(ricetta);

                System.out.println("Operazione eseguita con successo.\n");

                break;
            case 3:
                System.out.println("Hai scelto 'Rimuovere una ricetta.'");

                System.out.println("ID Ricetta:");
                service.deleteRicetta(sc.nextInt());

                System.out.println("Operazione eseguita con successo.");

                break;
            case 4:
                System.out.println("Hai scelto 'Selezionare il numero di ricette inserite da un utente.'");

                ArrayList<Ricetta> ricette;
                System.out.println("Email:");
                ricette = service.operazioneQuattro(sc.next());

                System.out.println("Operazione eseguita con successo.");
                System.out.println(ricette.size());

                break;
            case 5:
                System.out.println("Hai scelto 'Modificare il numero di telefono di un utente.'");

                System.out.println("Inserire email account utente:");
                String email = sc.next();
                System.out.println("Inserire nuovo numero di telefono:");
                String nuovoTel = sc.next();

                service.operazioneCinque(email, nuovoTel);

                System.out.println("Operazione eseguita con successo.");

                break;
            case 6:
                System.out.println("Hai scelto 'Elencare gli utenti che hanno recensito tutte le ricette.'");

                ArrayList<String> utenti;
                utenti = service.operazioneSei();

                System.out.println("Operazione eseguita con successo. Ecco la lista di utenti:");
                System.out.println(utenti);

                break;
            default:
                System.out.println("Errore. Scelta non valida!");
        }
    }
}
