



 █████╗ ██████╗ ██████╗ ███████╗███╗   ██╗ █████╗ ██╗     ██╗███╗   ██╗ █████╗ 
██╔══██╗██╔══██╗██╔══██╗██╔════╝████╗  ██║██╔══██╗██║     ██║████╗  ██║██╔══██╗
███████║██║  ██║██████╔╝█████╗  ██╔██╗ ██║███████║██║     ██║██╔██╗ ██║███████║
██╔══██║██║  ██║██╔══██╗██╔══╝  ██║╚██╗██║██╔══██║██║     ██║██║╚██╗██║██╔══██║
██║  ██║██████╔╝██║  ██║███████╗██║ ╚████║██║  ██║███████╗██║██║ ╚████║██║  ██║
╚═╝  ╚═╝╚═════╝ ╚═╝  ╚═╝╚══════╝╚═╝  ╚═══╝╚═╝  ╚═╝╚══════╝╚═╝╚═╝  ╚═══╝╚═╝  ╚═╝
                                                                               
-------------------------------------------------------------------------------

Indice:

1.FUNZIONALITA' IMPLEMENTATE.

2.ISTRUZIONI ALL'AVVIO:
|
|_____2.1. Avvio del Server.
|
|_____2.2. Avvio dei Client.

3)NOTE
|
|_____3.1 Note sul Nome Host del Server nella rete
|
|_____3.2 Note sul Nome File e percorso.
|
|_____3.3 Note su GitHub e .zip
-------------------------------------------------------------------------------

1)FUNZIONALITA' IMPLEMENTATE:

Il progetto implementa secondo il documento di specifiche le seguenti funzionalità:

	-)Regole complete di gioco:
	-)Command Line Interface (CLI);
	-)Graphical User Interface con JavaFX (GUI);
	-)Connettività Socket;
	-)Connettività Remote Method Invocation (RMI);
	-)Funzionalità avanzata Terminator.


2)ISTRUZIONI ALL'AVVIO:

Scaricare la cartella Adrenalina (ing-sw-2019-Gadolini-Mombelli-Nicolo/Deliveries/final/JAR/Adrenalina), 
contenente il file .jar eseguibile, unico per client e server, e una cartella "assets" per le risorse grafiche.

In tale cartella sono inclusi degli script launcher, per il client e per il server, che si occupano di eseguire il .jar 
attraverso la shell più performante in base alla modalità scelta (CLI o GUI).

Si consiglia infatti di eseguire l'applicativo tramite Windows PowerShell. 
Tuttavia, nel caso si volesse utilizzare l'interfaccia tramite riga di comando (CLI), 
al fine di avere un'esperienza utente più completa è consigliabile eseguire l'applicativo
in un terminale che garantisca la corretta visualizzazione di colori codificati tramite ANSI escape codes. 
A tal proposito, si suggerisce l'utilizzo della shell Linux, disponibile oggi anche su sistemi
operativi Windows.

Di seguito le istruzioni nel caso si preferisca l'avvio manuale del Client e del Server.


	2.1)Avvio del Server:

	Per avviare il server di gioco:

		-)Navigare nella cartella Adrenalina;
		-)Premere Shift + Tasto destro del Mouse all' interno della cartella Adrenalina;
		-)Avviare la Windows PowerShell;
		-)Digitare    java -jar ./adrenalina-1.0-SNAPSHOT.jar server  
		-)Il server si avvia, e attende che l' utente interagisca;

	2.2)Avvio del Client

	La procedura di avvio del client è del tutto analoga a quella di avvio del server.

		-)Navigare nella cartella Adrenalina;
		-)Premere Shift + Tasto destro del Mouse all' interno della cartella Adrenalina;
		-)Avviare la Windows PowerShell;
		-)Digitare    java -jar ./adrenalina-1.0-SNAPSHOT.jar
		-)Il client è ora avviato.
	
	(Il client si avvia senza passare nessun parametro al file .jar)

Una volta che sono presenti cinque giocatori (o alla scadenza del timer del server quando sono presenti più di tre giocatori)
la partita si avvia. Da questo momento è possibile giocare.

3)NOTE


	3.1) - La connessione del client al server avviene su rete locale. Una volta avviata la finestra del Client, 
	    viene richiesto di inserire il nome della macchina Host su cui è in esecuzione il server. Tale 
	    nome è il nome che il dispositivo assume nella rete (ad esempio DESKTOP-A1B2CD3), e non il 
            nome dell' utente di windows o il nickname del player.
		
	    Per ottenere tale nome:

		-)Cliccare su Esplora File;
		-)Navigare fino alla cartella "Questo PC"
		-)Destra mouse su Questo Pc;
		-)Proprietà;

	    Su Windows 10 il nome dell' host è quello denominato "Nome Computer" sotto alla sezione
	 
    	    "Impostazioni Relative a Nome Computer, Dominio e Gruppo di Lavoro"

	    Il server non è case-sensitive, per cui il nome può essere inserito con qualsiasi combinazione di lettere
	    maiuscole e/o minuscole.

	
	3.2) - Per una corretta visualizzazione della grafica di gioco (GUI) è necessario non estrarre o rinominare il contenuto 
	    della cartella Adrenalina. Tale cartella può però essere posizionata in qualsiasi directory si preferisca.
	    
	
	
	3.3) - Per i limiti imposti da GitHub sulla dimensione del singolo file, non è stato possibile creare una cartella .zip unica
	    in cui mettere assets e JAR. La cartella "Adrenalina" può comunque essere scaricata, convertita 
	    in un file .zip e distribuita.
	






