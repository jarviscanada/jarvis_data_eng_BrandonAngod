package ca.jrvs.apps.trading.DAO;

import ca.jrvs.apps.trading.model.domain.Quote;
import org.hibernate.sql.Update;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Repository
public class QuoteDAO implements CrudRepository<Quote, String> {
    private static final String TABLE_NAME = "quote";
    private static final String ID_COLUMN_NAME = "ticker";

    private static final Logger logger = LoggerFactory.getLogger(QuoteDAO.class);
    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert simpleJdbcInsert;

    @Autowired
    public QuoteDAO(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate();
        simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName(TABLE_NAME);
    }

    public Quote save(Quote quote){
        if(existsById(quote.getTicker())){
            int updatedRowNo = updateOne(quote);
            if(updatedRowNo != 1){
                throw new DataRetrievalFailureException("Unable to update quote");
            }
            else{
                addOne(quote);
            }
        }
        return quote;
    }

    private void addOne(Quote quote){
        SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(quote);
        int row = simpleJdbcInsert.execute(parameterSource);
        if(row !=1) {
            throw new IncorrectResultSizeDataAccessException("Failed to insert", 1, row);

        }
    }
    public int updateOne(Quote quote){
        String update_sql = "UPDATE quote SET last_price=?, bid_price=?, "+
                "bid_size=?, ask_price=?, ask_size=? WHERE ticker=";
        return jdbcTemplate.update(update_sql,makeUpdateValues(quote));
    }
    private Object[] makeUpdateValues(Quote quote){
        Object[] UpdateValues = new Object[5];
        UpdateValues[0]=quote.getLastPrice();
        UpdateValues[1]=quote.getBidPrice();
        UpdateValues[2]=quote.getBidSize();
        UpdateValues[3]=quote.getAskPrice();
        UpdateValues[4]=quote.getAskSize();
        return UpdateValues;
    }
    @Override
    public <S extends Quote> List<S> saveAll(Iterable<S> quotes){
        List<S> savedList = new ArrayList<S>();
        quotes.forEach(quote -> {
            save(quote);
            savedList.add(quote);
        });
        return savedList;
    }

    @Override
    public List<Quote> findAll(){
        String query = "SELECT * FROM "+TABLE_NAME;
        List<Quote> quoteList = jdbcTemplate.queryForList(query,Quote.class);
        return quoteList;
    }

    @Override
    public Iterable<Quote> findAllById(Iterable<String> iterable) {
        throw new UnsupportedOperationException("Not Implemented");
    }

    @Override
    public Optional<Quote> findById(String ticker){
        String query = "SELECT id FROM "+TABLE_NAME+" WHERE ID=?";
        Quote result = jdbcTemplate.queryForObject(query,new Object[]{ticker},Quote.class);
        return Optional.ofNullable(result);
    }

    @Override
    public boolean existsById(String ticker){
        String query = "SELECT id FROM "+TABLE_NAME+" WHERE id=?";
        String result = jdbcTemplate.queryForObject(query,new Object[]{ticker},String.class);
        if(result.isEmpty()){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public long count(){
        return jdbcTemplate.getFetchSize();
    }

    @Override
    public void deleteById(String s) {
        throw new UnsupportedOperationException("Not Implemented");
    }
    @Override
    public void delete(Quote quote) {
        throw new UnsupportedOperationException("Not Implemented");
    }
    @Override
    public void deleteAll(Iterable<? extends Quote> iterable) {
        throw new UnsupportedOperationException("Not Implemented");
    }
    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException("Not Implemented");
    }
}
