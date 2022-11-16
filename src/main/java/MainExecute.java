public class MainExecute {
    public static void main(String[] args) {
        ServicoCotacao servicoCotacao = new YahooAdapter();
        System.out.println("YahooFinance - preço: " + servicoCotacao.getCotacao("MGLU3.SA"));

        servicoCotacao = new AlphaVantageAdapter();
        System.out.println("AlphaVantage - preço: " + servicoCotacao.getCotacao("INTC"));

        servicoCotacao = new QuandlAdapter();
        System.out.println("Quandl - preço: " + servicoCotacao.getCotacao("WIKI/AAPL"));
    }
}
