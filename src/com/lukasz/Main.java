package com.lukasz;

import java.time.YearMonth;
import java.util.Calendar;

public class Main {

    public static void main(String[] args) {
        MatchList matchList = LoadData.load();
        System.out.println(matchList);
    }

}
