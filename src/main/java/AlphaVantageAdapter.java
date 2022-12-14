import io.github.mainstringargs.alphavantagescraper.AlphaVantageConnector;
import io.github.mainstringargs.alphavantagescraper.StockQuotes;
import io.github.mainstringargs.alphavantagescraper.output.AlphaVantageException;
import io.github.mainstringargs.alphavantagescraper.output.quote.data.StockQuote;

import java.time.format.DateTimeFormatter;

public class AlphaVantageAdapter implements  ServicoCotacao{
    @Override
    public double getCotacao(String codEmpresa) {
        System.out.printf("Cotação da Empresa %s pelo serviço Alpha Vantage: http://www.alphavantage.co%n", codEmpresa);

        /*
        Verifica se existe uma variável de ambiente para a chave da API do serviço Alpha Vantage.
        Você pode se cadastrar e obter uma chave em http://www.alphavantage.co
        */
        final String s = System.getenv("ALPHAVANTAGE_APIKEY");
        final String apiKey = s == null ? "50M3AP1K3Y" : s;
        final int timeout = 3000;
        var apiConnector = new AlphaVantageConnector(apiKey, timeout);

        //Permite obter a cotação (quotes) de ações (stocks).
        var stockQuotes = new StockQuotes(apiConnector);
        var response = stockQuotes.quote(codEmpresa);
        try {
            var stockQuote = response.getStockQuote();
            return stockQuote.getPrice();
        } catch (AlphaVantageException e) {
          throw new RuntimeException("Erro ao solicitar cotação da empresa " + codEmpresa + ": " + e.getMessage(), e);
        }
    }
}
