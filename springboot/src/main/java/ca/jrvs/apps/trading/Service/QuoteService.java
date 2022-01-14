package ca.jrvs.apps.trading.Service;

import ca.jrvs.apps.trading.DAO.MarketDataDAO;
import ca.jrvs.apps.trading.DAO.QuoteDAO;
import ca.jrvs.apps.trading.model.domain.IexQuote;
import ca.jrvs.apps.trading.model.domain.Quote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public List<Quote> updateMarketData(){
        //Get Quotes
        List<Quote> quoteList = findAllQuotes();
        //Get IEXQuote
        List<IexQuote> iexQuoteList= new ArrayList<IexQuote>();
        quoteList.forEach(quote -> {
            try {
                iexQuoteList.add((marketDataDAO.findById(quote.getTicker())).orElseGet(null));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        //Convert IEX -> Quote
        List<Quote> builtQuoteList = new ArrayList<Quote>();
        iexQuoteList.forEach(iexQuote -> {
            builtQuoteList.add(buildQuoteFromIexQuote(iexQuote));
        });
        //Save
        return quoteDao.saveAll(builtQuoteList);
    }

    protected static Quote buildQuoteFromIexQuote(IexQuote iexQuote){
        Quote builtQuote = new Quote(iexQuote.getId());
        builtQuote.setAskPrice(iexQuote.getUpdated());
        builtQuote.setAskSize((int) iexQuote.getuVolume());
        builtQuote.setBidPrice(iexQuote.getfClose());
        builtQuote.setBidSize(Math.toIntExact(iexQuote.getfVolume()));
        builtQuote.setLastPrice(iexQuote.getfOpen());
        builtQuote.setTicker(iexQuote.getId());
        builtQuote.setId(iexQuote.getId());
        return builtQuote;
    }

    public List<Quote> saveQuotes(List<String> tickers){
        List<Quote> quoteList = new ArrayList<>();
        tickers.forEach(tick ->{
            Optional<Quote> found = quoteDao.findById(tick);
            quoteList.add(found.orElseGet(null));
        });
        return quoteDao.saveAll(quoteList);
    }

    public Quote saveQuote(String ticker){
        Optional<Quote> found = quoteDao.findById(ticker);
        return quoteDao.save(found.orElseGet(null));
    }

    public Quote saveQuote(Quote quote){
        return quoteDao.save(quote);
    }

    public List<Quote> findAllQuotes(){
        return quoteDao.findAll();
    }
}
