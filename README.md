<p align="center">
    <img width="200" src="https://www.unisa.it/rescue/img/logo_standard.png" alt="Logo UNISA">
</p>
<h2 align="center">
PROGETTO BASE DI DATI

PORTALE RICETTE
</h2>
<p align="center">
Mattia Maucioni
</p>
<p align="center">
matr. 0512108936
</p>

## 1. DESCRIZIONE DELLA REALTÀ D'INTERESSE, RACCOLTA DELLE SPECIFICHE E PRIMA ANALISI
Si intende progettare una base di dati che gestisca le informazioni relative ad un portale web di ricette.

Il portale ha come obiettivo la pubblicazione e l'interazione con ricette pubblicate dagli utenti, questi ultimi possono quindi pubblicare ricette tramite un form dedicato ed interagire con esse tramite pulsanti per aggiungere ad una lista di preferite e/o aggiungere recensioni.

Gli utenti sono identificati unicamente da un _email_. Di un utente si vogliono conoscere anche tutte le informazioni essenziali come nome, cognome, telefono, indirizzo e data di nascita.

L'email e la password sono necessarie per l'accesso al portale.

Solo una volta effettuato l'accesso gli utenti avranno la possibilità di interagire a pieno col portale, altrimenti potranno solo leggere le ricette presenti.

Ogni ricetta ha come identificatore univoco un _idRicetta_, inoltre servirà conoscere anche il nome, il procedimento, i tempi di preparazione, cottura e totale, le kcal ed eventualmente le foto sotto forma di testo codificato.

Le ricette appartengono a più categorie, queste ultime identificate attraverso un _idCategoria_ ed un nome. Grazie alle categorie sarà possibile filtrare le ricette durante una ricerca sul portale.

Le ricette possono avere recensioni, queste sono identificate da un _idRecensione_ e devono avere un contenuto di testo ed eventualmente una votazione.

Di una recensione vogliamo sapere la ricetta associata, perché un'istanza di recensione appartiene ad una singola ricetta.

Sarà possibile aggiungere, da parte di un utente, una ricetta alla lista delle ricette preferite. Ogni preferita sarà identificata dagli identificatori dell'utente, che sta salvando la ricetta, e della ricetta.

**GLOSSARIO DEI TERMINI PRINCIPALI DEL DOMINIO APPLICATIVO**

| **Termine** | **Descrizione** |
| --- | --- |
| Utente | Utente coinvolto nella realtà di interesse. |
| Ricetta | L'elemento al centro della realtà d'interesse che collega le entità tra di loro. |
| Categoria | Definisce la categoria della ricetta tramite un nome. |
| Preferite | L'insieme delle ricette che un utente può preferire. |
| Recensioni | L'insieme delle recensioni che una ricetta può contenere, scritte dagli utenti visitanti con un breve testo e, eventualmente, una votazione (interi da 1 a 5). |

## 2. PROGETTAZIONE CONCETTUALE

SCHEMA EER

![alt text](https://i.imgur.com/BUVzYZY.png)

**DIZIONARIO DELLE ENTITÀ**

| **Entità** | **Descrizione** | **Attributi** | **Identificatore** |
| --- | --- | --- | --- |
| Utente | Utente coinvolto nella realtà d'interesse. | email, password, nome, cognome, telefono, indirizzo, data di nascita, foto. | email |
| Ricetta | L'elemento al centro della realtà d'interesse che collega le entità tra di loro. | idRicetta, nome, procedimento, foto, tempo di preparazione, tempo di cottura, tempo, kcal. | idRicetta |
| Categoria | Definisce la categoria della ricetta. | idCategoria, nome. | idCategoria |
| Recensione | Breve testo per commentare una ricetta. | idRecensione, testo | idRecensione |
| *Preferito* | Ricetta che un utente salva in una lista di preferite. | idRicetta, email | IDRicetta↑email↑ |

Legenda: *entità debole*, identificatore esterno↑;

**DIZIONARIO DELLE RELAZIONI**

| **Relazione** | **Descrizione** | **Entità coinvolte** | **Attributi** |
| --- | --- | --- | --- |
| Inserito | Un utente inserisce una ricetta. Una ricetta è inserita da uno ed un solo utente. | Utente (0,N)Ricetta (1,1) | / |
| Scrive | Un utente scrive uno o più recensioni. Una recensione è scritta da uno ed un solo utente. | Utente (0,N)Recensione (1,1) | idRicetta |
| Appartiene | Una ricetta appartiene a più categorie. Una categoria appartiene a più ricette. | Ricetta (N,N)Categoria (N,N) | / |

**VINCOLI NON ESPRIMIBILI**

- L'attributo "Procedimento" nell'entità "Ricetta" deve avere al massimo 5000 caratteri.
- L'attributo "Nome" nell'entità "Utente", "Ricetta" e "Categoria" deve avere al massimo 500 caratteri.
- L'attributo "Tempo Preparazione" nell'entità "Ricetta" è espresso in minuti.
- L'attributo "Tempo Cottura" nell'entità "Ricetta" è espresso in minuti.
- L'attributo "Tempo" nell'entità "Ricetta" è espresso in minuti.
- L'attributo "Voto" nell'entità "testoeVoto" è espresso in interi da 1 a 5

## 3. DEFINIZIONE DELLE PROCEDURE PER LA GESTIONE DELLA BASE DATI

**TAVOLA DEI VOLUMI**

| **Concetto** | **Tipo** | **Volume** |
| --- | --- | --- |
| Utente | E | 100 |
| Ricetta | E | 500 |
| Categoria | E | 20 |
| Recensione | E | 5000 |
| Preferito | E | 5000 |
| Inserito | R | 500 |
| Scrive | R | 5000 |
| Appartiene | R | 500 |

**TAVOLA DELLE OPERAZIONI**

| **Operazione** | **Tipo** | **Frequenza** |
| --- | --- | --- |
|1. Aggiungi un nuovo utente.| I | 2/gg |
|2. Aggiungi nuova ricetta.| I | 1/gg |
|3. Aggiungi recensione ad una ricetta (con o senza voto).| I | 5/gg |
|4. Selezionare tutte le ricette di un utente.| B | 5/gg |
|5. Selezionare tutte le ricette di una categoria.| I | 5/gg |
|6.Selezionare le ricette presenti in tutti i preferiti.| B | 1/gg |
|7. Rimuovere una ricetta.| I | 1/mm |
|8. Selezionare lista ricette ordinate per tempo totale di preparazione (preparazione+cottura) crescente nella lista dei preferiti di un certo utente.| B | 2/gg |
|9.Selezionare il numero di ricette inserite da un utente.| B | 5/gg |
|10. Selezionare una lista di email ordinate in ordine alfabetico corrispondenti agli utenti aventi numero di telefono con prefisso Italiano, Francese o Tedesco.| B | 1/mm |

## 4. PROGETTAZIONE LOGICA

**ELIMINAZIONE DELL'ATTRIBUTO MULTIVALORE**

Nello schema iniziale è presente l'attributo multi-valore "telefono" nell'entità "Utente". In fase di progettazione logica questo attributo va rimosso e ristrutturato, trasformandolo in un'entità e poi collegandolo all'entità alla quale apparteneva tramite una relazione. Si otterrà quindi lo schema seguente:

![](RackMultipart20221026-1-i5lohl_html_cc3483eea8c4426a.png)

**ANALISI DELLE RIDONDANZE**

Il dato ridondante è l'attributo "Tempo" dell'entità "Ricetta", ovvero la quantità di tempo che deve trascorrere per la realizzazione della ricetta. Questo è un dato ridondante perché tramite dei calcoli è possibile risalire al valore del dato, ma è da verificare se effettivamente, conviene eliminarlo. Per verificare ciò si calcola la somma tempo di preparazione più il tempo di cottura, per le operazioni che lo coinvolgono, con e senza il dato.

**TAVOLA DEGLI ACCESSI**

Si valutino dunque gli accessi con e senza l'attributo ridondante dell'entità "Ricetta". Ad esempio, osserviamo i dati relativi all'operazione **#2**.

| Calcolo con ridondanza - Operazione #2 |                         |         |      |
|----------------------------------------|-------------------------|---------|------|
| Tabella                                | Tipo                    | Accessi | Tipo |
| Categoria                              | E                       | 1       | L    |
| Utente                                 | E                       | 1       | l    |
| Inserito                               | R                       | 1       | L    |
| Appartiene                             | R                       | 1       | L    |
| Ricetta                                | E                       | 2       | S    |
| Ricetta                                | E                       | 1       | L    |
| TOTALE                                 | (5L+2S) = 7*1/gg = 7/gg |         |      |

| Calcolo con ridondanza - Operazione #2 |                         |         |      |
|----------------------------------------|-------------------------|---------|------|
| Tabella                                | Tipo                    | Accessi | Tipo |
| Categoria                              | E                       | 1       | L    |
| Utente                                 | E                       | 1       | l    |
| Inserito                               | R                       | 1       | L    |
| Appartiene                             | R                       | 1       | L    |
| Ricetta                                | E                       | 1       | S    |
| TOTALE                                 | (4L+1S) = 5*1/gg = 5/gg |         |      |

Osserviamo che, banalmente, il totale degli accessi all'operazione con ridondanza è superiore al totale degli accessi all'operazione senza ridondanza.

Si ottiene un miglioramento quindi, in termini di memoria, rimuovendo l'attributo ridondante.

**ELIMINAZIONE DELLE GERARCHIE**

Nello schema inizialmente elaborato, è presente la seguente specializzazione dell'entità "Recensione":

![alt text](https://i.imgur.com/YgwQXSA.png)

La gerarchia "Recensione" viene risolta eliminando le figlie e creando una nuova entità "Tipologia Recensione" con un attributo "tipoRecensione" che descrive il tipo di recensione scelta.

Lo schema ristrutturato sarà il seguente:

![alt text](https://i.imgur.com/Kakx7Vl.png)

**SCHEMA RELAZIONALE**

È possibile procedere al mapping della base di dati come segue.

- **Utente** (*email*, password, nome, cognome, telefono↑, via↑, data di nascita, foto)
- **Via** (*idVia*, nomeVia, civico↑)
- **Civico** (*idCivico*, numero, cap↑)
- **CAP** (*idCap*, cap)
- **Telefono** (*numTelefono*)
- **Ricetta** (*idRicetta*, nome, foto, procedimento, tempo di cottura, tempo di preparazione, kcal, email↑, idCategoria↑)
- **Categoria** (*idCategoria*, nome)
- **Preferito** (*idRicetta↑*, *email↑*)
- **Recensione** (*idRecensione*, testo, idTipologiaRecensione↑)
- **Tipologia Recensione** (*idTipologiaRecensione*, tipoRecensione)
- **Scrive** (email↑, *idRecensione↑*, *idRicetta↑*)

Legenda: *identificatore*, identificatore esterno↑;

**NORMALIZZAZIONE**

1. È in **prima forma normale** perché, lo schema EER non presenta né attributi composti, né attributi multi valore, rendendo ogni campo fondamentalmente atomico.
2. È in **seconda forma normale** perché, oltre ad essere in prima forma normale, quando è presente una chiave primaria composta da più attributi tutte le dipendenze funzionali che la riguardano sono piene e non parziali.
3. È in **terza forma normale** perché, oltre ad essere in seconda forma normale e in prima forma normale, in tutte le tabelle non sono presenti dipendenze transitive fra attributi non chiave e la chiave primaria.

**SCHEMA EER RISTRUTTURATO**

![alt text](https://i.imgur.com/I8qwmK0.png)

*È stata ristrutturata anche l'entità "indirizzo" per portarla in 2FN, questa entità non rispetta la 2FN perché ci sono delle dipendenze funzionali parziali.*

## 5. REALIZZAZIONE DATABASE CON MYSQL
![alt text](https://i.imgur.com/UKtl0cB.png)
![alt text](https://i.imgur.com/G5rIDvu.png)
## 6. IMPLEMENTAZIONE QUERY SQL
![alt text](https://i.imgur.com/R63AJ1a.png)
## 7. TEST DELL'APPLICAZIONE JAVA

Il codice JAVA per esteso è reperibile nella repository corrente.
