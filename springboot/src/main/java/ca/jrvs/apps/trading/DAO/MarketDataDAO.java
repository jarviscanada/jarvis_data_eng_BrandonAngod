package ca.jrvs.apps.trading.DAO;

import ca.jrvs.apps.trading.model.config.MarketDataConfig;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.client.CloseableHttpClient;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Repository;
import org.slf4j.Logger;
import ca.jrvs.apps.trading.model.domain.IexQuote;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class MarketDataDAO implements ca.jrvs.apps.trading.Intefaces.MarketDataDAO {
    private static final String IEX_BATCH_PATH = "/stock/market/batch?symbols=%s&types=quote&token=";
    private final String IEX_BATCH_URL;

    private Logger logger = LoggerFactory.getLogger(MarketDataDAO.class);
    private org.apache.http.conn.HttpClientConnectionManager HttpClientConnectionManager;

    @Autowired
    public MarketDataDAO(HttpClientConnectionManager httpClientConnectManager,
     MarketDataConfig marketDataConfig){
        this.HttpClientConnectionManager = httpClientConnectManager;
        IEX_BATCH_URL = marketDataConfig.getHost() + IEX_BATCH_PATH + marketDataConfig.getToken();
    }

    @Override
    public Optional<IexQuote> findById(String ticker){
        Optional<IexQuote> iexQuote;
        List<IexQuote> quotes = findAllById(Collections.singletonList(ticker));

        if(quotes.size() == 0){
            return Optional.empty();
        } else if (quotes.size() == 1){
            iexQuote = Optional.of(quotes.get(0));
        } else{
            throw new DataRetrievalFailureException("Unexpected number of quotes");
        }
        return iexQuote;
    }

    @Override
    public List<IexQuote> findAllById(Iterable<String> tickers){
        return null;
    }

    public Optional<String> executeHttpGet(String url){
        return null;
    }

    private CloseableHttpClient getHttpClient(){
        return null;
    }
    @Override
    public boolean existsById(String s) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public Iterable<IexQuote> findAll() {
        throw new UnsupportedOperationException("Not implemented");
    }


    @Override
    public long count() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public void deleteById(String s) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public void delete(IexQuote entity) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public void deleteAll(Iterable<? extends IexQuote> entities) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public <S extends IexQuote> S save(S entity) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public <S extends IexQuote> Iterable<S> saveAll(Iterable<S> entities) {
        throw new UnsupportedOperationException("Not implemented");
    }
}
