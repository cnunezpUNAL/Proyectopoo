package org.example.gui;

import com.twelvedata.client.ApiException;
import com.twelvedata.client.model.TimeSeriesItem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.example.logic.Stock;

import java.lang.Math;
import java.util.List;

public class HelloController{

    private static double f(double x){
        return Math.sin(x);
    }

    private static double g(double x){
        return Math.cos(x);
    }

    @FXML
    LineChart<Number,Number> lineChart;

    @FXML
    Label label;

    @FXML
    Button submit;

    @FXML
    TextField textfield;


    @FXML
    private void initialize(){
        lineChart.setTitle("x^2");
        XYChart.Series series = new XYChart.Series();
        XYChart.Series series2 = new XYChart.Series();
        double t =-(Math.PI);
        for( int i = 0; i < 1000; i++ ){
            t=t+0.01;
            series.getData().add(new XYChart.Data(t, f(t)));
            series2.getData().add(new XYChart.Data(t, g(t)));
        }
        lineChart.setCreateSymbols(false);
        lineChart.getData().add(series2);
        lineChart.getData().add(series);
    }

    @FXML
    private void toSubmit(ActionEvent event){
        if(textfield.getText().isEmpty());
        else {
            label.setText(textfield.getText().toUpperCase());
            textfield.clear();
            try {
                Stock stock = new Stock(label.getText());
                List<TimeSeriesItem> list = stock.history();
                lineChart.getData().clear();

            } catch (ApiException e) {
                throw new RuntimeException(e);
            }
        }
    }


}
