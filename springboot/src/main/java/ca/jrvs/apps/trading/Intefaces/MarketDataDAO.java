package ca.jrvs.apps.trading.Intefaces;

import ca.jrvs.apps.trading.model.domain.IexQuote;

import java.util.List;
import java.util.Optional;

public interface MarketDataDAO {

    Optional<IexQuote> findById(String ticker);

    List<IexQuote> findAllById(Iterable<String> tickers);

    boolean existsById(String s);

    Iterable<IexQuote> findAll();

    long count();

    void deleteById(String s);

    void delete(IexQuote entity);

    void deleteAll(Iterable<? extends IexQuote> entities);

    void deleteAll();

    <S extends IexQuote> S save(S entity);

    <S extends IexQuote> Iterable<S> saveAll(Iterable<S> entities);
}
