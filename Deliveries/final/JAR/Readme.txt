



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
	-)Command Line Interface;
	-)Graphic User Interface con JavaFX;
	-)Connettività Socket;
	-)Connettività Remote Method Invocation;
	-)Funzionalità avanzata Terminator.


2)ISTRUZIONI ALL'AVVIO:

La cartella Adrenalina (path git) contiene il file Jar eseguibile unico per client e 
Server e una cartella assets per le risorse grafiche.

Il gioco è ottimizzato per essere eseguito tramite Windows PowerShell nella sua parte
di grafica, tuttavia per la corretta visualizzazione e completa esperienza tramite Command 
Line è consigliabile che il gioco venga eseguito tramite Shell Linux.
Nella cartella principale sono inseriti dei Launcher che si occupano di eseguire il jar 
attraverso la shell corretta in tutte le sue modalità di Server e Client e con le due 
interfacce utente CLI e GUI.

Di seguito le istruzioni nel caso si preferisca l'avvio manuale del Client e del Server.


	2.1)Avvio del Server:

	Per avviare il server di gioco:

		-)Navigare nella cartella Adrenalina;
		-)Premere Shift + Tasto destro del Mouse all' interno della cartella;
		-)Avviare la Windows PowerShell;
		-)Digitare    java -jar ./nomeFileJar.jar server  
		-)Il server si avvia, e attende che l' utente interagisca;

	2.2)Avvio del Client

	L' avvio del client è del tutto analogo all' avvio del server, l'unica discrepanza
	sta nel comando da inserire nella powershell.

	Digitare    java -jar ./nomeFileJar.jar 
	
	Il client si avvia senza passare nessun parametro al comando da inserire a PowerShell.



3)NOTE


	3.1)La connessione del client al server avviene su rete locale, percui avviata la finestra del Client 
	    viene richiesto di inserire il nome della macchina Host su cui è in esecuzione il server. Tale 
	    nome è il nome che il dispositivo assume nella rete (ad esempio DESKTOP-A1B2CD3)  e non il 
            nome dell' utente di windows o il nickname del player.
		
	    Per ottenere tale nome:

		-)Cliccare su Esplora File;
		-)Navigare fino alla cartella "Questo PC"
		-)Destra mouse su Questo Pc;
		-)Proprietà;

	    Su Win10 il nome dell' host è quello denominato "Nome Computer" sotto alla sezione
	 
    	    "Impostazioni Relative a Nome Computer, Dominio e Gruppo di Lavoro"

	    Il server non è case-sensitive, percui il nome può essere inserito con qualsiasi combinazione di lettere
	    maiuscole e/o minuscole.

	
	3.2)Per una corretta visualizzazione della grafica di gioco (GUI) è necessario non estrarre o rinominare il contenuto 
	    della cartella Adrenalina, bensì è sufficiente limitarsi ad eseguire i passi descritti sopra ai punti 2.1 e 2.2.
	    
	
	
	3.3)Per i limiti imposti da GitHub sulla dimensione del singolo File, non è stato possibile creare una cartella .zip unica
	    in cui mettere asset e JAR. Sarà sufficiente scaricare la cartella Adrenalina e il JAR sarà al suo interno.	

	






