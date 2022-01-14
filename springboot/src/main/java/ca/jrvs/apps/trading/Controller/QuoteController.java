package ca.jrvs.apps.trading.Controller;

import ca.jrvs.apps.trading.Service.QuoteService;
import ca.jrvs.apps.trading.model.domain.IexQuote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class QuoteController{

    private QuoteService quoteService;

    @Autowired
    public QuoteController(QuoteService quoteService){
        this.quoteService = quoteService;
    }

    @GetMapping(path = "/iex/ticker/{ticker}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public IexQuote getQuote(@PathVariable String ticker){
        try {
            return quoteService.findIexQuoteByTicker(ticker);
        } catch(Exception e){
            throw ResponseExceptionUtil.getResponseStatusException(e);
        }
    }
}

