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
|2. Aggiungi nuova ricetta.| I | 2/gg |
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
| TOTALE                                 | (5L+2S) = 7*2/gg = 14/gg |         |      |

| Calcolo con ridondanza - Operazione #2 |                         |         |      |
|----------------------------------------|-------------------------|---------|------|
| Tabella                                | Tipo                    | Accessi | Tipo |
| Categoria                              | E                       | 1       | L    |
| Utente                                 | E                       | 1       | l    |
| Inserito                               | R                       | 1       | L    |
| Appartiene                             | R                       | 1       | L    |
| Ricetta                                | E                       | 1       | S    |
| TOTALE                                 | (4L+1S) = 5*2/gg = 10/gg |         |      |

Osserviamo che, banalmente, il totale degli accessi all'operazione con ridondanza è superiore al totale degli accessi all'operazione senza ridondanza.

Si ottiene un miglioramento quindi, in termini di acesso alla memoria, rimuovendo l'attributo ridondante.

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
    	nome VARCHAR(20) NOT NULL,
    	PRIMARY KEY (idCategoria)
    );

DROP TABLE IF EXISTS Telefono;
CREATE TABLE Telefono(
	numTelefono VARCHAR(16) NOT NULL,
    	PRIMARY KEY (numTelefono)
    );

DROP TABLE IF EXISTS Cap;
CREATE TABLE Cap(
	idCap SMALLINT NOT NULL AUTO_INCREMENT,
    	cap INT NOT NULL,
    	PRIMARY KEY (idCap)
    );

DROP TABLE IF EXISTS Civico;
CREATE TABLE Civico(
	idCivico SMALLINT NOT NULL AUTO_INCREMENT,
    	numero INT NOT NULL,
    	idCap SMALLINT NOT NULL,
    	PRIMARY KEY (idCivico),
    	FOREIGN KEY (idCap) REFERENCES Cap(idCap)
    );

DROP TABLE IF EXISTS Via;
CREATE TABLE Via(
	idVia SMALLINT NOT NULL AUTO_INCREMENT,
    	nomeVia VARCHAR(20) NOT NULL,
    	idCivico SMALLINT NOT NULL,
    	PRIMARY KEY (idVia),
    	FOREIGN KEY (idCivico) REFERENCES Civico(idCivico)
    );

DROP TABLE IF EXISTS Utente;
CREATE TABLE Utente(
	email VARCHAR(20) NOT NULL,
    	psw VARCHAR(20) NOT NULL,
    	nome VARCHAR(20) NOT NULL,
    	cognome VARCHAR(20) NOT NULL,
    	numTelefono VARCHAR(16),
    	idVia SMALLINT NOT NULL AUTO_INCREMENT,
    	dataNascita DATE NOT NULL,
    	foto VARCHAR(20),
    	PRIMARY KEY (email),
    	FOREIGN KEY (numTelefono) REFERENCES Telefono(numTelefono),
    	FOREIGN KEY (idVia) REFERENCES Via(idVia)
    );

ALTER TABLE Telefono
ADD email VARCHAR(20) NOT NULL;

ALTER TABLE Telefono
ADD FOREIGN KEY (email) REFERENCES Utente(email);

DROP TABLE IF EXISTS Ricetta;
CREATE TABLE Ricetta(
	idRicetta SMALLINT NOT NULL AUTO_INCREMENT,
    	nome VARCHAR(20) NOT NULL,
	foto VARCHAR(20) NOT NULL,
    	procedimento VARCHAR(500) NOT NULL,
    	tempoCottura SMALLINT NOT NULL,
    	tempoPreparazione SMALLINT NOT NULL,
    	kcal SMALLINT NOT NULL,
    	email VARCHAR(20) NOT NULL,
    	idCategoria SMALLINT NOT NULL,
    	PRIMARY KEY (idRicetta),
    	FOREIGN KEY (email) REFERENCES Utente(email),
    	FOREIGN KEY (idCategoria) REFERENCES Categoria(idCategoria)
    );
    
DROP TABLE IF EXISTS Preferito;
CREATE TABLE Preferito(
	idRicetta SMALLINT NOT NULL AUTO_INCREMENT,
        email VARCHAR(20) NOT NULL,
        PRIMARY KEY (idRicetta, email),
        FOREIGN KEY (idRicetta) REFERENCES Ricetta(idRicetta),
        FOREIGN KEY (email) REFERENCES Utente(email)
    );
    
DROP TABLE IF EXISTS TipologiaRecensione;
CREATE TABLE TipologiaRecensione(
	idTipologiaRecensione SMALLINT NOT NULL AUTO_INCREMENT,
        tipoRecensione VARCHAR(20) NOT NULL,
        PRIMARY KEY (idTipologiaRecensione)
    );
    
DROP TABLE IF EXISTS Recensione;
CREATE TABLE Recensione(
	idRecensione SMALLINT NOT NULL AUTO_INCREMENT,
        testo VARCHAR(200) NOT NULL,
        idTipologiaRecensione SMALLINT NOT NULL,
        PRIMARY KEY (idRecensione),
        FOREIGN KEY (idTipologiaRecensione) REFERENCES TipologiaRecensione(idTipologiaRecensione)
    );
    
DROP TABLE IF EXISTS Scrive;
CREATE TABLE Scrive(
	email VARCHAR(20) NOT NULL,
        idRecensione SMALLINT NOT NULL,
        idRicetta SMALLINT NOT NULL,
        PRIMARY KEY (idRecensione, idRicetta),
        FOREIGN KEY (email) REFERENCES Utente(email),
        FOREIGN KEY (idRecensione) REFERENCES Recensione(idRecensione),
        FOREIGN KEY (idRicetta) REFERENCES Ricetta(idRicetta)
    );
```
Script per popolare il database:
```MySQL
SET SQL_SAFE_UPDATES = 0;

DELETE FROM Categoria;
INSERT INTO Categoria VALUES
(1, "Pranzo"),
(2, "Antipasto"),
(3, "Cena"),
(4, "Dolci");

DELETE FROM Cap;
INSERT INTO Cap VALUES
(1, 84036),
(2, 40200),
(3, 17506);

DELETE FROM Civico;
INSERT INTO Civico VALUES
(1, 20, 1),
(2, 37, 2),
(3, 19, 3);

DELETE FROM Via;
INSERT INTO Via VALUES
(1, "via Roma", 1),
(2, "via Matteotti", 2),
(3, "via Cavour", 3);

DELETE FROM Utente;
INSERT INTO Utente VALUES
("m.rossi@gmail.com", "pass1234", "Mario", "Rossi", NULL, 3, "1962-12-12", "base64,iVBORw"),
("p.verdi@gmail.com", "pass1234", "Paolo", "Verdi", NULL, 2, "1992-02-17", "base64,pRYFw"),
("s.bianchi@gmail.com", "pass1234", "Simona", "Bianchi", NULL, 1, "1956-11-09", "base64,mTerP");

DELETE FROM Telefono;
INSERT INTO Telefono VALUES
("3661959894", "m.rossi@gmail.com"),
("3200120242","p.verdi@gmail.com"),
("3397209757","s.bianchi@gmail.com");

UPDATE Utente
SET numTelefono = "3661959894"
WHERE email = "m.rossi@gmail.com";

UPDATE Utente
SET numTelefono = "3200120242"
WHERE email = "p.verdi@gmail.com";

UPDATE Utente
SET numTelefono = "3397209757"
WHERE email = "s.bianchi@gmail.com";

DELETE FROM Ricetta;
INSERT INTO Ricetta VALUES
(1, "Polpettone", "base64,iVBORw", "testotesttesto", 30, 30, 1500, "s.bianchi@gmail.com", 3),
(2, "Tenerina", "base64,mTerP", "provaprovaprova", 60, 20, 800, "m.rossi@gmail.com", 4),
(3, "Pasta al sugo", "base64,pRYFw", "testoprovatesto", 10, 10, 800, "m.rossi@gmail.com", 1);

DELETE FROM Preferito;
INSERT INTO Preferito VALUES
(2, "m.rossi@gmail.com"),
(1, "p.verdi@gmail.com"),
(2, "s.bianchi@gmail.com");

DELETE FROM TipologiaRecensione;
INSERT INTO TipologiaRecensione VALUES
(1, "NO VOTO"),
(2, "1 STELLA"),
(3, "2 STELLE"),
(4, "3 STELLE"),
(5, "4 STELLE"),
(6, "5 STELLE");

DELETE FROM Recensione;
INSERT INTO Recensione VALUES
(1, "Molto buono!", 5),
(2, "Mi piace pero...", 4),
(3, "Che schifo!!!!", 2);

DELETE FROM Scrive;
INSERT INTO Scrive VALUES
("s.bianchi@gmail.com", 1, 3),
("m.rossi@gmail.com", 3, 1),
("p.verdi@gmail.com", 2, 2);

```
## 6. IMPLEMENTAZIONE QUERY SQL
```MySQL
# OPERAZIONE 1
INSERT INTO Utente (
	email, 
	psw, 
    nome, 
    cognome, 
    numTelefono, 
    idVia, 
    dataNascita, 
    foto
) VALUES (
	value_email, 
    value_psw, 
    value_cognome, 
    value_numTelefono, 
    value_idVia, 
    value_dataNascita, 
    value_foto
);

# OPERAZIONE 2
INSERT INTO Ricetta (
	idRicetta,
    nome,
    foto,
    procedimento,
    tempoCottura,
    tempoPreparazione,
    kcal,
    email,
    idCategoria
) VALUES (
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
INSERT INTO Recensione (
	idRecensione,
    testo,
    idTipologiaRecensione
) VALUES (
	value_idRecensione,
    value_testo,
    value_idTipologiaRecensione
);

INSERT INTO Scrive (
	email,
    idRecensione,
    idRicetta
) VALUES (
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
SELECT COUNT(DISTINCT idRicetta) 
FROM Ricetta
WHERE email LIKE value_email;

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
