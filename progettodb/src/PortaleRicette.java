import java.util.ArrayList;
import java.util.Scanner;

public class PortaleRicette {

    public static void main (String[] args) {

        System.out.println("Benvenuto nel portale di ricette, scegli l'operazione che vuoi svolgere:" +
                "\n1. Aggiungi nuovo utente." +
                "\n2. Aggiungi nuova ricetta." +
                "\n3. Rimuovere una ricetta." +
                "\n4. Selezionare il numero di ricette inserite da un utente." +
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
            default:
                System.out.println("Errore. Scelta non valida!");
        }
    }
}
