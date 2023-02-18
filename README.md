<p align="center">
    <img width="200" src="https://www.unisa.it/rescue/img/logo_standard.png" alt="Logo UNISA">
</p>
<h2 align="center">
PROGETTO BASE DI DATI

prof.ssa Sebillo

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

Gli utenti sono identificati unicamente da un _email_. Di un utente si vogliono conoscere anche tutte le informazioni essenziali come nome, cognome, telefono eventualmente, indirizzo e data di nascita.

L'email e la password sono necessarie per l'accesso al portale.

Solo una volta effettuato l'accesso gli utenti avranno la possibilità di interagire a pieno col portale, altrimenti potranno solo leggere le ricette presenti.

Ogni ricetta ha come identificatore univoco un _idRicetta_, inoltre servirà conoscere anche il nome, il procedimento, i tempi di preparazione e cottura, le kcal ed eventualmente le foto sotto forma di testo codificato.

Le ricette appartengono a una o più categorie, queste ultime identificate attraverso un _idCategoria_, un nome ed una tipologia. Grazie alle categorie sarà possibile filtrare le ricette durante una ricerca sul portale.

Le ricette possono avere recensioni, queste sono identificate da un _idRecensione_ e devono avere un contenuto di testo e una votazione.

Di una recensione vogliamo sapere la ricetta associata, perché un'istanza di recensione appartiene ad una singola ricetta.

Sarà possibile aggiungere, da parte di un utente, una ricetta alla lista delle ricette preferite. Ogni preferita sarà identificata dagli identificatori dell'utente, che sta salvando la ricetta, e della ricetta.

**GLOSSARIO DEI TERMINI PRINCIPALI DEL DOMINIO APPLICATIVO**

| **Termine** | **Descrizione** |
| --- | --- |
| Utente | Utente coinvolto nella realtà di interesse. |
| Ricetta | L'elemento al centro della realtà d'interesse che collega le entità tra di loro. |
| Categoria | Definisce la categoria della ricetta tramite un nome ed una tipologia. |
| Preferite | L'insieme delle ricette che un utente può preferire. |
| Recensioni | L'insieme delle recensioni che una ricetta può contenere, scritte dagli utenti visitanti con un breve testo e, eventualmente, una votazione (interi da 1 a 5). |

## 2. PROGETTAZIONE CONCETTUALE

SCHEMA EER

![alt text](https://i.imgur.com/5ZENpjp.png)

**DIZIONARIO DELLE ENTITÀ**

| **Entità** | **Descrizione** | **Attributi** | **Identificatore** |
| --- | --- | --- | --- |
| Utente | Utente coinvolto nella realtà d'interesse. | email, password, nome, cognome, telefono, indirizzo, data di nascita, foto, numRicettePubblicate. | email |
| Ricetta | L'elemento al centro della realtà d'interesse che collega le entità tra di loro. | idRicetta, nome, procedimento, foto, tempo di preparazione, tempo di cottura, kcal. | idRicetta |
| Categoria | Definisce la categoria della ricetta. | idCategoria, nome, tipologia. | idCategoria |
| Recensione | Breve testo per commentare una ricetta. | idRecensione, testo, voto. | idRecensione |
| Preferito | Ricetta che un utente salva in una lista di preferite. | idRicetta, email. | IDRicetta↑, email↑ |

Legenda: *entità debole*, identificatore esterno↑;

**DIZIONARIO DELLE RELAZIONI**

| **Relazione** | **Descrizione** | **Entità coinvolte** | **Attributi** |
| --- | --- | --- | --- |
| Inserito | Un utente inserisce una ricetta. Una ricetta è inserita da uno ed un solo utente. | Utente (0,N), Ricetta (1,1) | / |
| Scrive | Un utente scrive uno o più recensioni. Una recensione è scritta da uno ed un solo utente. | Utente (0,N), Recensione (1,1) | idRicetta |
| Appartiene | Una ricetta appartiene a più categorie. Una categoria appartiene a più ricette. | Ricetta (1,N), Categoria (0,N) | / |

**VINCOLI NON ESPRIMIBILI**

- L'attributo "Procedimento" nell'entità "Ricetta" deve avere al massimo 5000 caratteri.
- L'attributo "Nome" nell'entità "Utente", "Ricetta" e "Categoria" deve avere al massimo 500 caratteri.
- L'attributo "Tempo Preparazione" nell'entità "Ricetta" è espresso in minuti.
- L'attributo "Tempo Cottura" nell'entità "Ricetta" è espresso in minuti.
- L'attributo "Voto" nell'entità "Recensione" è espresso in interi da 1 a 5.

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
|1. Aggiungi un nuovo utente.| I | 60/mm |
|2. Aggiungi nuova ricetta.| I | 60/mm |
|3. Rimuovere una ricetta.| I | 1/mm |
|4. Selezionare il numero di ricette inserite da un utente.| I | 150/mm |
|5. Modificare il numero di telefono di un utente.| I | 2/mm |
|6. Elencare gli utenti che hanno recensito tutte le ricette. | I | 1/mm |

## 4. PROGETTAZIONE LOGICA

**ELIMINAZIONE DELL'ATTRIBUTO MULTIVALORE**

Nello schema iniziale è presente l'attributo multi-valore "telefono" nell'entità "Utente". In fase di progettazione logica questo attributo va rimosso e ristrutturato, trasformandolo in un'entità e poi collegandolo all'entità alla quale apparteneva tramite una relazione. Si otterrà quindi lo schema seguente:

![alt text](https://i.imgur.com/1ByiBVD.png)

**ANALISI DELLE RIDONDANZE**

Il dato ridondante è l'attributo "#ricettePubblicate" dell'entità "Utente", ovvero la quantità di ricette che un utente ha inserito nel portale. Questo è un dato ridondante perché tramite il conto delle istanze della relazione "Inserito" per un dato "Utente" è possibile risalire al valore del dato, ma è da verificare se effettivamente, conviene eliminarlo.

**TAVOLA DEGLI ACCESSI**

Si valutino dunque gli accessi con e senza l'attributo ridondante dell'entità "Utente". Ad esempio, osserviamo i dati relativi all'operazione **#2** e **#4**.

| Calcolo con ridondanza - Operazione #2 |                         |         |      |
|----------------------------------------|-------------------------|---------|------|
| Tabella                                | Tipo                    | Accessi | Tipo |
| Ricetta                                | E                       | 1       | S    |
| Inserito                               | R                       | 1       | S    |
| Appartiene                             | R                       | 1       | S    |
| Utente                                 | E                       | 1       | L    |
| Utente                                 | E                       | 1       | S    |
| TOTALE                                 | 4S+1L = 9L * 60/mm = 540L/mm + 1kb|         |      |

| Calcolo senza ridondanza - Operazione #2 |                         |         |      |
|----------------------------------------|-------------------------|---------|------|
| Tabella                                | Tipo                    | Accessi | Tipo |
| Ricetta                                | E                       | 1       | S    |
| Inserito                               | R                       | 1       | S    |
| Appartiene                             | R                       | 1       | S    |
| TOTALE                                 | 3S = 6L * 60/mm = 360L/mm|         |      |

| Calcolo con ridondanza - Operazione #4 |                         |         |      |
|----------------------------------------|-------------------------|---------|------|
| Tabella                                | Tipo                    | Accessi | Tipo |
| Utente                                 | E                       | 1       | L    |
| TOTALE                                 | 1L * 150/mm = 150L/mm * 100 utenti = 15000L/mm|         |      |

| Calcolo senza ridondanza - Operazione #4 |                         |         |      |
|----------------------------------------|-------------------------|---------|------|
| Tabella                                | Tipo                    | Accessi | Tipo |
| Utente                                 | E                       | 1       | L    |
| Inserito                               | R                       | 5       | L    |
| TOTALE                                 | 6L * 150/mm = 900L/mm * 100 utenti = 90000L/mm|         |      |

Totale accessi alle operazioni con ridondanza = 15540 L/mm + 1kb;

Totale accessi alle operazioni senza ridondanza = 90360 L/mm;

Osserviamo che, il totale degli accessi all'operazione con ridondanza è inferiore al totale degli accessi all'operazione senza ridondanza.

Si ottiene un miglioramento quindi, in termini di acesso alla memoria, mantenendo l'attributo ridondante.

**ELIMINAZIONE DELLE GERARCHIE**

Nello schema inizialmente elaborato, è presente la seguente specializzazione dell'entità "Categoria":

![alt text](https://i.imgur.com/er5EERS.png)

La gerarchia "Categoria" viene risolta accorpando le entità figlie (specializzazioni) nell'entità padre.

Lo schema ristrutturato sarà il seguente:

![alt text](https://i.imgur.com/Adt6poz.png)

**SCHEMA RELAZIONALE**

Legenda: **chiave primaria**, chiave esterna↑, opzionale*;

È possibile procedere al mapping della base di dati come segue.

- **Utente** (**email**, password, nome, cognome, foto, data nascita, #ricettePubblicate, telefono↑*, cap↑, via↑, civico↑)
- **Indirizzo** (**cap**, **via**, **civico**);
- **Recensione** (**idRecensione**, testo, voto, ricetta↑, utente↑)
- **Ricetta** (**idRicetta**, nome, procedimento, foto, kcal, tempoCottura, tempoPreparazione, categoria↑, utente↑)
- **Categoria** (**idCategoria**, nome, tipologia)
- **Preferito** (**ricetta↑**, **utente↑**)

**CHIAVI ESTERNE:**

- **Utente**(*telefono*) referenzia **Telefono**(*numero*)
- **Utente**(*cap, via, civico*) referenzia **Indirizzo**(*cap, via, civico*)
- **Recensione**(*ricetta*) referenzia **Ricetta**(*idRicetta*)
- **Recensione**(*utente*) referenzia **Utente**(*email*)
- **Ricetta**(*categoria*) referenzia **Categoria**(*idCategoria*)
- **Ricetta**(*utente*) referenzia **Utente**(*email*)
- **Preferito**(*ricetta*) referenzia **Ricetta**(*idRicetta*)
- **Preferito**(*utente*) referenzia **Utente**(*email*)

**NORMALIZZAZIONE**

1. È in **prima forma normale** perché, lo schema EER non presenta né attributi composti, né attributi multi valore, rendendo ogni campo fondamentalmente atomico.
2. È in **seconda forma normale** perché, oltre ad essere in prima forma normale, quando è presente una chiave primaria composta da più attributi tutte le dipendenze funzionali che la riguardano sono piene e non parziali.
3. È in **terza forma normale** perché, oltre ad essere in seconda forma normale e in prima forma normale, in tutte le tabelle non sono presenti dipendenze transitive fra attributi non chiave e la chiave primaria.

**SCHEMA EER RISTRUTTURATO**

![alt text](https://i.imgur.com/38Qx29H.png)

## 5. REALIZZAZIONE DATABASE CON MYSQL
Script per la creazione del database:
```MySQL
USE mysql;
DROP USER IF EXISTS "ricetteuser"@"localhost";
CREATE USER "ricetteuser"@"localhost" IDENTIFIED BY "ricetteuser";
DROP DATABASE IF EXISTS ricette;
CREATE DATABASE ricette;
GRANT ALL ON ricette.* TO "ricetteuser"@"localhost";

USE ricette;

DROP TABLE IF EXISTS Categoria;
CREATE TABLE Categoria(
	idCategoria SMALLINT NOT NULL AUTO_INCREMENT,
    	nome VARCHAR(50) NOT NULL,
	tipologia VARCHAR(50) NOT NULL,
    	PRIMARY KEY (idCategoria)
    );

DROP TABLE IF EXISTS Indirizzo;
CREATE TABLE Indirizzo(
	cap INT(6) NOT NULL,
    	via CHAR(50) NOT NULL,
	civico INT(2) NOT NULL,
    	PRIMARY KEY (cap, via, civico)
    );

DROP TABLE IF EXISTS Utente;
CREATE TABLE Utente(
	email VARCHAR(50) NOT NULL,
    	psw VARCHAR(50) NOT NULL,
    	nome VARCHAR(50) NOT NULL,
    	cognome VARCHAR(50) NOT NULL,
    	numTelefono VARCHAR(16),
    	dataNascita DATE NOT NULL,
    	foto VARCHAR(250),
	numRicettePubblicate INT,
	cap INT(6) NOT NULL,
    	via CHAR(50) NOT NULL,
	civico INT(2) NOT NULL,
    	PRIMARY KEY (email),
    	FOREIGN KEY (cap, via, civico) REFERENCES Indirizzo(cap, via, civico)
    );

DROP TABLE IF EXISTS Ricetta;
CREATE TABLE Ricetta(
	idRicetta SMALLINT NOT NULL AUTO_INCREMENT,
    	nome VARCHAR(50) NOT NULL,
	foto VARCHAR(250) NOT NULL,
    	procedimento VARCHAR(500) NOT NULL,
    	tempoCottura SMALLINT NOT NULL,
    	tempoPreparazione SMALLINT NOT NULL,
    	kcal SMALLINT NOT NULL,
    	email VARCHAR(50) NOT NULL,
    	idCategoria SMALLINT NOT NULL,
    	PRIMARY KEY (idRicetta),
    	FOREIGN KEY (email) REFERENCES Utente(email),
    	FOREIGN KEY (idCategoria) REFERENCES Categoria(idCategoria)
    );
    
DROP TABLE IF EXISTS Preferito;
CREATE TABLE Preferito(
	idRicetta SMALLINT NOT NULL AUTO_INCREMENT,
        email VARCHAR(50) NOT NULL,
        PRIMARY KEY (idRicetta, email),
        FOREIGN KEY (idRicetta) REFERENCES Ricetta(idRicetta),
        FOREIGN KEY (email) REFERENCES Utente(email)
    );
    
DROP TABLE IF EXISTS Recensione;
CREATE TABLE Recensione(
	idRecensione SMALLINT NOT NULL AUTO_INCREMENT,
        testo VARCHAR(200) NOT NULL,
        voto INT(1),
	idRicetta SMALLINT NOT NULL,
    	email VARCHAR(50) NOT NULL,
        PRIMARY KEY (idRecensione),
	FOREIGN KEY (idRicetta) REFERENCES Ricetta(idRicetta),
    	FOREIGN KEY (email) REFERENCES Utente(email)
    );
    
```
Script per popolare il database:
```MySQL
SET SQL_SAFE_UPDATES = 0;

DELETE FROM Categoria;
INSERT INTO Categoria VALUES
(1, "Stuzzichini", "Aperitivo"),
(2, "Semifreddo", "Dolce"),
(3, "Bevande analcoliche", "Bevanda");

DELETE FROM Indirizzo;
INSERT INTO Indirizzo VALUES
(84036, 'via Matteotti', 5),
(84035, 'via Sturzo', 4),
(44121, 'via Manzoni', 3),
(84036, 'via Luigi Sturzo', 21);

DELETE FROM Utente;
INSERT INTO Utente VALUES
("m.rossi@gmail.com", "pass1234", "Mario", "Rossi", 3661959895, "1962-12-12", "base64,iVBORw", 2, 84036, "via Matteotti", 5),
("p.verdi@gmail.com", "pass4321", "Paolo", "Verdi", 3200120242, "1992-02-17", "base64,pRYFw", 0, 84035, "via Sturzo", 4),
("s.bianchi@gmail.com", "pass3412", "Simona", "Bianchi", "3397209757", "1956-11-09", "base64,mTerP", 1, 44121, "via Manzoni", 3);


DELETE FROM Ricetta;
INSERT INTO Ricetta VALUES
(1, "Polpettone", "base64,iVBORw", "testotesttesto", 30, 30, 1500, "s.bianchi@gmail.com", 1),
(2, "Tenerina", "base64,mTerP", "provaprovaprova", 60, 20, 800, "m.rossi@gmail.com", 3),
(3, "Pasta al sugo", "base64,pRYFw", "testoprovatesto", 10, 10, 800, "m.rossi@gmail.com", 2);

DELETE FROM Preferito;
INSERT INTO Preferito VALUES
(2, "m.rossi@gmail.com"),
(1, "p.verdi@gmail.com"),
(2, "s.bianchi@gmail.com");

DELETE FROM Recensione;
INSERT INTO Recensione VALUES
(1, "Molto buono!", 5, 1, "m.rossi@gmail.com"),
(2, "Mi piace pero...", 4, 2, "m.rossi@gmail.com"),
(3, "Che schifo!!!!", 2, 3, "m.rossi@gmail.com");

```
## 6. IMPLEMENTAZIONE QUERY SQL
```MySQL
# OPERAZIONE 1
INSERT INTO Utente (email, psw, nome, cognome, numTelefono, idVia, dataNascita, foto) 
VALUES (
    value_email, 
    value_psw, 
    value_cognome, 
    value_numTelefono, 
    value_idVia, 
    value_dataNascita, 
    value_foto
);

# OPERAZIONE 2
INSERT INTO Ricetta (idRicetta, nome, foto, procedimento, tempoCottura, tempoPreparazione, kcal, email, idCategoria) 
VALUES (
    value_idRicetta,
    value_nome,
    value_foto,
    value_procedimento,
    value_tempoCottura,
    value_tempoPreparazione,
    value_kcal,
    value_email,
    value_idCategoria
);

# OPERAZIONE 3
INSERT INTO Recensione (idRecensione, testo, idTipologiaRecensione) 
VALUES (
    value_idRecensione,
    value_testo,
    value_idTipologiaRecensione
);

INSERT INTO Scrive (email, idRecensione, idRicetta) 
VALUES (
    value_email,
    value_idRecensione,
    value_idRicetta
);

#OPERAZIONE 4
SELECT * 
FROM Ricetta
WHERE Ricetta.email = value_email;

#OPERAZIONE 5
SELECT * 
FROM Ricetta
WHERE Ricetta.idCategoria = value_idCategoria;

#OPERAZIONE 6
SELECT *
FROM Ricetta
INNER JOIN Preferito ON Ricetta.idRicetta = Preferito.idRicetta
GROUP BY Preferito.idRicetta
HAVING COUNT(DISTINCT idRicetta) > 0;

#OPERAZIONE 7
DELETE FROM Ricetta
WHERE idRicetta = value_idRicetta;

#OPERAZIONE 8
SELECT *
FROM Ricetta
WHERE Preferito.email = ? AND Ricetta.email = ?
ORDER BY SUM(Ricetta.tempoPreparazione * Ricetta.tempoCottura) ASC;

#OPERAZIONE 9
SELECT numRicettePubblicate 
FROM Utente
WHERE email = value_email;

#OPERAZIONE 10
SELECT email
FROM Utente
WHERE Utente.telefono IN ("+39%","+33%","+49%")
ORDER BY email ASC;
```
## 7. TEST DELL'APPLICAZIONE JAVA
L’applicazione si presenta con un interfaccia semplice ed intuitiva. È divisa in due parti. Una parte dedicata al Menù dove è possibile visualizzare tutte le operazioni che si possono eseguire, ed una parte interattiva dove viene inserito il numero dell’operazione che si intende eseguire e tutte le altre eventuali operazioni necessarie al fine di completare l’operazione eseguita. 

Esempio d'esecuzione dell'operazione 1 (Aggiungi utente):

![alt text](https://i.imgur.com/ZhGIGoD.gif)

Tabella utente prima dell'operazione:

![alt text](https://i.imgur.com/RInQEIv.png)

Tabella utente dopo l'operazione:

![alt text](https://i.imgur.com/vNFAca5.png)

La stampa avviene solo per le operazioni di tipo SELECT e può essere visualizzata nell’ambiente di sviluppo. 

Il programma è perfettamente funzionante ed è testabile utilizzando la repository corrente, in cui ovviamente poter reperire il codice Java per esteso.
