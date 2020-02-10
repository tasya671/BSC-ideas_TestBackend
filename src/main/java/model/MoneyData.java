package model;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Currency;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MoneyData {

    private Set<Currency> currencies;
    private Map<Currency, Integer> data;
    private String url = "http://www.vokrugsveta.ru/encyclopedia/index.php?title=%D0%9A%D1%83%D1%80%D1%81%D1%8B_%D0%B2%D1%81%D0%B5%D1%85_%D0%B2%D0%B0%D0%BB%D1%8E%D1%82_%D0%BC%D0%B8%D1%80%D0%B0";
    private static Map<String, Double> dollarRate = new HashMap<>();
    private Set<String> setValid;


    public MoneyData() {
        this.currencies = Currency.getAvailableCurrencies();
        this.data = new HashMap<>();
        initialization();
    }
    // извлечение данных с веб-страницы о текущих курсах валют
    private void initialization(){
        String current;
        String currency;
        String amount;
        Pattern pattern = Pattern.compile("[A-Z]{3}");
        String dollar ="1 доллар США ";
        try {
            Document doc = Jsoup.connect(String.valueOf(url)).get();
            Element table = doc.select("table").get(3);
            Elements rows = table.select("tr");// разбиваем нашу таблицу на строки по тегу
            for (int i = 1; i < rows.size(); i++) {
                Element row = rows.get(i); //по номеру индекса получает строку
                Elements cols = row.select("td");// разбиваем полученную строку по тегу  на столбы
                for (int j = 0; j < cols.size(); j++) {
                    if ((current = cols.get(j).text().trim()).indexOf(dollar) != -1) {
                        int subNumber = current.lastIndexOf(dollar);
                        current = current.substring(subNumber + dollar.length()).replaceAll("= ", "");
                        Matcher matcher = pattern.matcher(current);
                        if (matcher.find()) {
                            amount = current.substring(0, matcher.start()).replace(",", ".");
                            currency = current.substring(matcher.start(), matcher.end());
                            Double amountDouble = Double.parseDouble(amount);
                            dollarRate.put(currency, amountDouble);
                        }
                    }
                }
            }
            setValid = dollarRate.keySet();
        } catch (IOException exp){
            System.out.println("Not find resource");
        }
    }

    public void addItemMoney(String currencyName, Integer date) {
        Currency currency = Currency.getInstance(currencyName);
        if (data.containsKey(currency)) {
            data.put(currency, (data.get(currency) + date));
        } else {
            data.put(currency, date);
        }
    }

    public synchronized String printMoney() {
        StringBuilder builder = new StringBuilder();
        if(data.size()!= 0){
            for (Map.Entry<Currency, Integer> entry:data.entrySet()) {
                if (entry.getValue() != 0)
                {
                    builder.append(entry.getValue() + " ");
                    builder.append(entry.getKey());
                    if (setValid.contains(entry.getKey().toString()) & !entry.getKey().toString().equals("USD")){
                        builder.append(" (USD ");
                        builder.append(String.format("%.2f", entry.getValue()/dollarRate.get(entry.getKey().toString())));
                        builder.append(")\n");
                    } else
                        builder.append("\n");
                }
            }
        }

        return builder.toString();
    }
}

