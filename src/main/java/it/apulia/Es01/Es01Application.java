package it.apulia.Es01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/* Esercitazione Thymeleaf & JDBC
Si gestisca un sistema bibliotecario:
- Dovrà essere presente un metodo sul path /addNewBook che permetterà l’aggiunta di
un nuovo libro all’interno della biblioteca tramite un form. Inoltre dovrà essere restituita
una pagina nuova che ci dia l’esito dell’operazione.
- Dovranno essere implementata una pagina sul path /library nella quale sarà presente
una tabella popolata con i libri presenti all’interno della biblioteca. Se la biblioteca è vuota
sarà necessario visualizzare un opportuno messaggio.
- Dovrà essere possibile consultare le informazioni relative al singolo libro, andando ad
operare sul path /library/{bookId}
- La classe Libro dovrà contenere i seguenti campi:
o titolo;
o autore;
o bookId; (chiave primaria)
o annoPB;
o link; (campo preso da uno store online su dove è possibile acquistarlo)
- pagina form per la ricerca per titolo:
o POST su /search (la get sullo stesso path restituisce il form da compilare)
o Reindirizza su una pagina che restituisce (o meno) il libro cercato

 */

@SpringBootApplication
public class Es01Application {

	public static void main(String[] args) {
		SpringApplication.run(Es01Application.class, args);
	}

}
