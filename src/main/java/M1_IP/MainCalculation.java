package M1_IP;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class MainCalculation {
    private Document document;
    private Elements names;
    private Elements prices;
    public static int updateTime;
    private static ArrayList<Currency> currencies;

    public MainCalculation() {
        updateTime = 1000;
        updateAll();

    }

    public void updateAll(){
        new Thread(()->{
            while (true){

                try {
                    updateDocument();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                updateNames();
                updatePrices();

                updateCurrencies();

                try {
                    Thread.sleep(updateTime);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }
    public void updateDocument() throws IOException {
        this.document = Jsoup.connect("https://www.google.com/finance/markets/currencies").get();

    }
    public void updateNames(){
        this.names = this.document.select(".ZvmM7");
    }
    public void updatePrices(){
        this.prices = document.select(".Bu4oXd");
    }

    public void updateCurrencies(){
        currencies = new ArrayList<>();
        for (int i = 0 ; i < this.names.size() ; i++) {
            currencies.add(new Currency(this.names.get(i).text() , this.prices.get(i).text()));
        }
    }

    public static void setUpdateTime(int requiredFrequency){
        if (requiredFrequency >= 250){
             updateTime = requiredFrequency;
        }
        else {
            updateTime = requiredFrequency * 1000;
        }
    }

    public synchronized static ArrayList<Currency> getCurrencies() {
        return currencies;
    }

}