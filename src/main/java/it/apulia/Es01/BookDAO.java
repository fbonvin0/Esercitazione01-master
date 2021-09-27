package it.apulia.Es01;

import java.util.List;

public interface BookDAO {

        List<Book> findAll();

        void insertBook(Book usr);

        void updateBook(Book usr);

        void executeUpdateBook(Book usr);

        public void deleteBook(Book usr);

}
