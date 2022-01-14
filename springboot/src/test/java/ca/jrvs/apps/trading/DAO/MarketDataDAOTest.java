package ca.jrvs.apps.trading.DAO;

import ca.jrvs.apps.trading.model.config.MarketDataConfig;
import ca.jrvs.apps.trading.model.domain.IexQuote;
import junit.framework.TestCase;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.junit.Before;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class MarketDataDAOTest extends TestCase {
    private MarketDataDAO dao;

    @Before
    public void init(){
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(50);
        cm.setDefaultMaxPerRoute(50);
        MarketDataConfig marketDataConfig = new MarketDataConfig();
        marketDataConfig.setHost("https://cloud.iexapis.com/v1/");
        marketDataConfig.setToken(System.getenv("IEX_PUB_TOKEN"));

        dao = new MarketDataDAO(cm,marketDataConfig);
    }
    public void testFindById() throws URISyntaxException, IOException {
        //HP
        List<IexQuote> quoteList = dao.findAllById(Arrays.asList("AAPL", "FB2"));
        assertEquals(2,quoteList.size());
        assertEquals("AAPL",quoteList.get(0).getSymbol());
        /*SP
        try {
            dao.findAllById(Arrays.asList("AAPL", "FB2"));
            fail();
        }catch(IllegalArgumentException e){
            assertTrue(true);
        }catch(Exception e){
            fail();
        }
         */
    }

    public void testFindAllById() throws IOException {
        String ticker = "AAPL";
        Optional<IexQuote> iexQuote = dao.findById(ticker);
        assertEquals(ticker,iexQuote.get().getSymbol());
    }
}