package it.apulia.Es01;

import lombok.Data;


@Data
public class Book {

	Integer bookId;
	String titolo;
	String autore;
	String annoPB;
	String link;


	public Book(String titolo, String autore, String annoPB, String link) {
		this.titolo = titolo;
		this.autore = autore;
		this.annoPB = annoPB;
		this.link = link;
	}

	public Book(){

	}
}
