package it.apulia.Es01;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookRowMapper implements RowMapper<Book> {

    @Override
    public Book mapRow(ResultSet rs, int arg1) throws SQLException {
        Book lbr = new Book();
      //  usr.setUserId(rs.getString("userId"));
        lbr.setTitolo(rs.getString("titolo"));
        lbr.setAutore(rs.getString("autore"));
        lbr.setAnnoPB(rs.getString("annoPB"));
        lbr.setLink(rs.getString("link"));

        return lbr;
    }
    //commento
}
