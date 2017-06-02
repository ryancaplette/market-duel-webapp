package com.marketduel.util.stockdata;

/**
 * Created by ryan on 5/29/2017.
 */
public class StockDataItem {
    private String identifier = null;
    private String item = null;
    private String value = null;

    public StockDataItem (String identifier, String item, String value) {
        this.setIdentifier(identifier);
        this.setItem(item);
        this.setValue(value);
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
