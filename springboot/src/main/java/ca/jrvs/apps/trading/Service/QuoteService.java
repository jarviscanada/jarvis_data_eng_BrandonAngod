package ca.jrvs.apps.trading.Service;

import ca.jrvs.apps.trading.DAO.MarketDataDAO;
import ca.jrvs.apps.trading.DAO.QuoteDAO;
import ca.jrvs.apps.trading.model.domain.IexQuote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Transactional
@Service
public class QuoteService {
    private static final Logger logger = LoggerFactory.getLogger(QuoteService.class);

    private QuoteDAO quoteDao;
    private MarketDataDAO marketDataDAO;

    @Autowired
    public QuoteService(QuoteDAO quoteDao, MarketDataDAO marketDataDAO){
        this.quoteDao = quoteDao;
        this.marketDataDAO = marketDataDAO;
    }

    public IexQuote findIexQuoteByTicker(String ticker) throws IOException {
        return marketDataDAO.findById(ticker)
                .orElseThrow(() -> new IllegalArgumentException(ticker + " is invalid"));
    }
}
