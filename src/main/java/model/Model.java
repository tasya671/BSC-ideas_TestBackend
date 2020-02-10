package model;

import java.io.IOException;

public interface Model {

    MoneyData getMoneyData();
    void addItem(String currencyName, Integer date) throws IOException;
    String print();

}
