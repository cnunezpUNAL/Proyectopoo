package org.example.logic;
import com.twelvedata.client.ApiClient;
import com.twelvedata.client.ApiException;
import com.twelvedata.client.Twelvedata;
import com.twelvedata.client.api.MarketDataApi;
import com.twelvedata.client.model.GetQuote200Response;
import com.twelvedata.client.model.GetTimeSeries200Response;
import com.twelvedata.client.model.IntervalEnum;
import com.twelvedata.client.model.TimeSeriesItem;

import java.util.List;

abstract class Product {
    protected double price;
    protected double volume;
    protected String name;
    protected String description;
    protected String ticker;

    public double getPrice() {
        return price;
    }

    public double getVolume() {
        return volume;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getTicker() {
        return ticker;
    }

    public void upToDate() throws ApiException {
        ApiClient apiClient = Twelvedata.newApiClient(CONSTANTS.apiKey);
        MarketDataApi api = new MarketDataApi(apiClient);
        try{
        GetQuote200Response response = api.getQuote(MarketDataApi.APIGetQuoteRequest.newBuilder()
                .symbol(this.ticker)
                .build());
        this.price = Double.parseDouble(response.getClose());
        this.volume = Double.parseDouble(response.getVolume());
        this.name = response.getName();
        this.description = response.getName();

    } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public List<TimeSeriesItem> history() throws ApiException {
        ApiClient apiClient = Twelvedata.newApiClient(CONSTANTS.apiKey);
        MarketDataApi api = new MarketDataApi(apiClient);
        try{
            GetTimeSeries200Response response = api.getTimeSeries(MarketDataApi.APIGetTimeSeriesRequest.newBuilder()
                    .symbol(this.ticker)
                    .startDate("2026-10-01")
                    .interval(IntervalEnum._1DAY)
                    .endDate("2026-11-25")
                    .build());
            return response.getValues();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



}
