package model;

import java.io.IOException;

public class MoneyModel implements Model {

        private MoneyData moneyData = new MoneyData();

        public MoneyData getMoneyData() {
            return moneyData;
        }

        public void addItem(String currencyName, Integer date) throws IOException {
            moneyData.addItemMoney(currencyName, date); }

        public String print() {
            return moneyData.printMoney();
        }
    }

