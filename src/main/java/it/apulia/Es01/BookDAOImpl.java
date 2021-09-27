package it.apulia.Es01;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;



import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BookDAOImpl implements BookDAO {

    NamedParameterJdbcTemplate template;


    public BookDAOImpl(NamedParameterJdbcTemplate template) {

        this.template = template;
    }


    @Override
    public List<Book> findAll() {
        return template.query("select * from books", new BookRowMapper());
    }


    public List<Book> findBook(String titolo) {
        final String sql ="select * from books where titolo=:titolo";
        SqlParameterSource param = new MapSqlParameterSource().addValue("titolo", titolo);
        return template.query(sql, param, new ResultSetExtractor<List<Book>>() {
            @Override
            public List<Book> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<Book> tempList = new ArrayList<Book>();
                while(rs.next()){

                    Book lbr = new Book(rs.getString("titolo"), rs.getString("autore"),
                            rs.getString("annoPB"),rs.getString("link"));
                    tempList.add(lbr);
                    break;
                }
                return tempList;
            }
        });
    }


    @Override
    public void insertBook(Book lbr) {

        final String sql = "insert into books(titolo , autore, annoPB, link) values(:titolo,:autore,:annoPB,:link)";

        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource param = new MapSqlParameterSource()

                .addValue("titolo",lbr.getTitolo())
                .addValue("autore",lbr.getAutore() )
                .addValue("annoPB",lbr.getAnnoPB())
                .addValue("link",lbr.getLink());
        template.update(sql,param, holder);
    }

    @Override
    public void updateBook(Book lbr) {

        final String sql = "update books set titolo=:titolo, autore=:autore, annoPB=:annoPB, link=:link, where bookId=:bookId";

        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource param = new MapSqlParameterSource()
                //.addValue("userId", usr.getUserId())
                .addValue("titolo",lbr.getTitolo())
                .addValue("autore",lbr.getAutore() )
                .addValue("annoPB",lbr.getAnnoPB())
                .addValue("link",lbr.getLink());
        template.update(sql,param, holder);
    }

    @Override
    public void executeUpdateBook(Book lbr) {

        final String sql = "update books set titolo=:titolo, autore=:autore, annoPB=:annoPB, link=:link, where bookId=:bookId";

        Map<String,Object> map = new HashMap<String,Object>();

        map.put("titolo", lbr.getTitolo());
        map.put("autore", lbr.getAutore());
        map.put("annoPB", lbr.getAnnoPB());
        map.put("link", lbr.getLink());

        template.execute(sql,map,new PreparedStatementCallback<Object>() {

            @Override
            public Object doInPreparedStatement(PreparedStatement ps)
                    throws SQLException, DataAccessException {
                return ps.executeUpdate();
            }
        });

    }

    @Override
    public void deleteBook(Book lbr) {

        final String sql = "delete from books where bookId=:bookId";

        Map<String,Object> map = new HashMap<String,Object>();

        map.put("bookId",lbr.getBookId());

        template.execute(sql,map,new PreparedStatementCallback<Object>() {
            @Override
            public Object doInPreparedStatement(PreparedStatement ps)
                    throws SQLException, DataAccessException {
                return ps.executeUpdate();
            }
        });

    }

}
