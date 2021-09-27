package it.apulia.Es01;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

	private final BookDAOImpl bookRepository;

	@Autowired
	public UserService(BookDAOImpl lrbRep) {
		this.bookRepository = lrbRep;

	}


	public boolean verificaTitolo(RicercaDTO titolo) {

		List<Book> tmp = new ArrayList<Book>(this.bookRepository.findBook(titolo.getTitolo()));

		if(tmp.isEmpty())
			return false;

		return true;
	}


	public void saveBook(BookDTO libro) {

		Book temp = new Book(libro.titolo, libro.autore, libro.annoPB, libro.link);
		this.bookRepository.insertBook(temp);

	}


	public List<Book> getAllBooks(){

		return new ArrayList(this.bookRepository.findAll());
	}



}
