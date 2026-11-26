package org.example.logic;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalTime;

public enum Exchange {
    NASDAQ("nasdaq",LocalTime.of(16,0),LocalTime.of(9,30)),
    NYSE("nyse",LocalTime.of(16,0),LocalTime.of(9,30)),
    CRYPTOGENERIC("cryptogeneric" );
    final String name;
    final LocalTime closingTime;
    final LocalTime openingTime;

    Exchange(String name, LocalTime closingTime, LocalTime openingTime) {
        this.name = name;
        this.closingTime = closingTime;
        this.openingTime = openingTime;
    }
    Exchange(String name) {
        this(name ,LocalTime.MAX,LocalTime.MIN);
    }
    static String valueof(String value){//????????????????????????????????????????????????
        List<String> list = new ArrayList<>();
        list.add("nsye");
        list.add("nasdaq");
        if (list.contains(value))
            return list.get(list.indexOf(value));
        else
            return value+" is not present in the list";

    }

    public String getName() {
        return name;
    }

    public LocalTime getClosingTime() {
        return closingTime;
    }

    public LocalTime getOpeningTime() {
        return openingTime;
    }
}

