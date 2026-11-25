package org.example.logic;

import com.twelvedata.client.ApiException;

public class Stock extends Product{
    public Stock(String ticker) throws ApiException {
        this.ticker = ticker;
        upToDate();
    }

}
