import com.twelvedata.*;
import com.twelvedata.client.ApiClient;
import com.twelvedata.client.ApiException;
import com.twelvedata.client.Twelvedata;
import com.twelvedata.client.api.MarketDataApi;
import com.twelvedata.client.errors.TwelvedataApiException;
import com.twelvedata.client.model.GetTimeSeries200Response;
import com.twelvedata.client.model.IntervalEnum;
import com.twelvedata.client.model.TimeSeriesItem;
import org.example.logic.Stock;

import java.util.List;
import java.util.Scanner;

public class app {
    public static void main(String[] args) throws ApiException {
        System.out.println("Ingrese un ticker");
        Scanner scanner = new Scanner(System.in);
        String ticker1 = scanner.next().toUpperCase();
        Stock stock1 = new Stock(ticker1);
        stock1.history();
        /*
        ApiClient client = Twelvedata.newApiClient(org.example.logic.CONSTANTS.apiKey); // defaults to System.getenv("TWELVEDATA_API_KEY")
        MarketDataApi api = new MarketDataApi(client);

        try {
            GetTimeSeries200Response response = api.getTimeSeries(
                    MarketDataApi.APIGetTimeSeriesRequest.newBuilder()
                            .interval(IntervalEnum._1DAY)
                            .symbol(ticker)
                            .outputsize(30L)
                            .build()
            );
            List<TimeSeriesItem> parsed =response.getValues();
            System.out.println(ticker+": \n");
            for (TimeSeriesItem item : parsed) {
                System.out.println(item.getClose() + " - " + item.getDatetime());
            }

        } catch (TwelvedataApiException e) {
            System.err.println("API error [" + e.getStatusCode() + "]: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e);
        }

    }*/}
}
