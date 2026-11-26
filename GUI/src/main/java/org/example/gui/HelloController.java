package org.example.gui;

import com.twelvedata.client.ApiException;
import com.twelvedata.client.model.IntervalEnum;
import com.twelvedata.client.model.TimeSeriesItem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import org.example.logic.Stock;

import java.lang.Math;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.List;

public class HelloController{

    private static double f(double x){
        return Math.sin(x);
    }

    private static double g(double x){
        return Math.cos(x);
    }

    @FXML
    LineChart<String,Number> lineChart;

    @FXML
    Label label;

    @FXML
    Button submit;

    @FXML
    TextField textfield;

    @FXML
    DatePicker eDate;
    @FXML
    DatePicker sDate;

    @FXML
    ChoiceBox choiceBox;



    @FXML
    private void initialize(){
        choiceBox.getItems().addAll(IntervalEnum.values());
        choiceBox.setValue(IntervalEnum.values()[9]);
        lineChart.setTitle("Testing Area");
        XYChart.Series series = new XYChart.Series();
        XYChart.Series series2 = new XYChart.Series();
        double t =-(Math.PI);
        for( int i = 0; i < 100; i++ ){
            t=t+0.1;
            series.getData().add(new XYChart.Data(String.valueOf(t), f(t)));
            if(t==Math.round(t)){
            series2.getData().add(new XYChart.Data(String.valueOf(t), g(t)));}
        }
        lineChart.setCreateSymbols(false);
        lineChart.getData().add(series2);
        lineChart.getData().add(series);
        NumberAxis yaxis = (NumberAxis) lineChart.getYAxis();
        CategoryAxis xaxis = (CategoryAxis) lineChart.getXAxis();

        yaxis.setTickUnit(10);
        lineChart.setAnimated(false);
    }


    private void submit(ActionEvent event){
        LocalDateTime endate = ((DatePicker)(event.getSource())).getValue().atTime(LocalTime.of(4,0));
    }

    @FXML
    private void toSubmit(ActionEvent event){
        if(textfield.getText().isEmpty());
        else {
            label.setText(textfield.getText().toUpperCase());
            textfield.clear();
            try {
                Stock stock = new Stock(label.getText());
                List<TimeSeriesItem> list = stock.history(sDate.getValue().atTime(LocalTime.of(9,30)).toString(),eDate.getValue().atTime(LocalTime.of(16,0)).toString(),(IntervalEnum) choiceBox.getValue());
                lineChart.getData().clear();
                CategoryAxis xaxis = (CategoryAxis) lineChart.getXAxis();//Debug candidate
                xaxis.getCategories().clear();//D
                XYChart.Series series = new XYChart.Series();
                double min= Double.parseDouble(list.getFirst().getClose());
                double max= Double.parseDouble(list.getFirst().getClose());
                for (int i = list.size()-1; i >=0; i--){
                    TimeSeriesItem item = list.get(i);
                    min = (Double.parseDouble(item.getClose())<min)? Double.parseDouble(item.getClose()): min;
                    max = (Double.parseDouble(item.getClose())>max)? Double.parseDouble(item.getClose()): max;
                    series.getData().add(new XYChart.Data(item.getDatetime().substring(0,item.getDatetime().lastIndexOf(":")), Double.parseDouble(item.getClose())));
                }
                lineChart.getYAxis().autoRangingProperty().setValue(false);
                NumberAxis yaxis = (NumberAxis) lineChart.getYAxis();
                xaxis.setTickLabelRotation(360);
                yaxis.setLowerBound(min-min/10);
                yaxis.setUpperBound(max+max/10);
                yaxis.setTickUnit(10);
                lineChart.getData().add(series);
                lineChart.setTitle(stock.getName());

            } catch (ApiException e) {
                throw new RuntimeException(e);
            }
        }
    }


}
