import java.util.ArrayList;
import java.util.Scanner;

public class PortaleRicette {

    public static void main (String[] args) {

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
                System.out.println("Indrizzo:");
                utente.setVia(sc.next());
                System.out.println("Data di nascita (aaaa-mm-gg):");
                utente.setDataNascita(sc.next());
                System.out.println("Foto:");
                utente.setFoto(sc.next());

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
                System.out.println("Hai scelto 'Aggiungi recensione ad una ricetta (con o senza voto).'");
                Recensione recensione = new Recensione();
                Scrive scrive = new Scrive();

                System.out.println("Scegli tipologia recensione (1. NO RATING to 6. 5 STELLE): ");
                recensione.setIdTipologiaRecensione(sc.nextInt());
                int ID = (service.retrieveNumberOfRecensioni());
                recensione.setIdRecensione(ID+1);
                System.out.println("Testo:");
                recensione.setTesto(sc.next());
                System.out.println("Email:");
                scrive.setEmail(sc.next());
                System.out.println("ID Ricetta:");
                scrive.setIdRicetta(sc.nextInt());
                scrive.setIdRecensione(ID+1);

                service.operazioneTre(recensione, scrive);

                System.out.println("Operazione eseguita con successo.\n");

                break;
            case 4:
                System.out.println("Hai scelto 'Selezionare tutte le ricette di un utente.'");

                System.out.println("Email:");
                ArrayList<Ricetta> ricette;
                ricette = service.operazioneQuattro(sc.next());

                System.out.println("Operazione eseguita con successo.");
                System.out.println(ricette);

                break;
            case 5:
                System.out.println("Hai scelto 'Selezionare tutte le ricette di una categoria.'");

                System.out.println("IDCategoria:");
                ricette = service.operazioneCinque(sc.nextInt());

                System.out.println("Operazione eseguita con successo.");
                System.out.println(ricette);

                break;
            case 6:
                System.out.println("Hai scelto 'Selezionare le ricette presenti in tutti i preferiti.'");

                ricette = service.operazioneSei();

                System.out.println("Operazione eseguita con successo.");
                System.out.println(ricette);

                break;
            case 7:
                System.out.println("Hai scelto 'Rimuovere una ricetta.'");

                System.out.println("ID Ricetta:");
                service.deleteRicetta(sc.nextInt());

                System.out.println("Operazione eseguita con successo.");

                break;
            case 8:
                System.out.println("Hai scelto 'Selezionare lista ricette ordinate per tempo totale di preparazione (preparazione+cottura)'");

                System.out.println("Email:");
                ricette = service.operazioneOtto(sc.next());

                System.out.println("Operazione eseguita con successo.");
                System.out.println(ricette);

                break;
            case 9:
                System.out.println("Hai scelto 'Selezionare il numero di ricette inserite da un utente.'");

                System.out.println("Email:");
                ricette = service.operazioneOtto(sc.next());

                System.out.println("Operazione eseguita con successo.");
                System.out.println(ricette.size());

                break;
            case 10:
                System.out.println("Hai scelto 'Selezionare una lista di email ordinate in ordine alfabetico corrispondenti agli utenti aventi numero di telefono con prefisso Italiano, Francese o Tedesco'");

                ArrayList<String> listEmail = service.operazioneDieci();

                System.out.println("Operazione eseguita con successo.");
                System.out.println(listEmail);

                break;
            default:
                System.out.println("Errore. Scelta non valida!");
        }
    }
}
