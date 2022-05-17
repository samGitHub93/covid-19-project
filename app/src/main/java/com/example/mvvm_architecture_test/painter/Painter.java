package com.example.mvvm_architecture_test.painter;

import android.widget.TextView;

import androidx.annotation.DrawableRes;

import com.example.mvvm_architecture_test.R;
import com.example.mvvm_architecture_test.enumerator.PaintType;
import com.example.mvvm_architecture_test.enumerator.TableType;
import com.example.mvvm_architecture_test.model.PlagueDay;

public class Painter {

    private static TableType tableType = TableType.DAYS;
    private static PaintType paintType = PaintType.NEW_CONTAGIONS;

    public static void paintTextView(TextView textView, PlagueDay today){
        if(paintType == PaintType.NEW_CONTAGIONS) paintRowAccordingNewContagions(textView, today.getNewContagions());
        else if(paintType == PaintType.DEATHS) paintRowAccordingDeaths(textView, today.getDeaths());
    }

    public static void setTableType(TableType tableType) {
        Painter.tableType = tableType;
    }

    public static void setPaintType(PaintType paintType) {
        Painter.paintType = paintType;
    }

    private static void paintRowAccordingNewContagions(TextView textView, int newContagions){
        switch(tableType){
            case WEEKS:
                paintWeekAccordingNewContagions(textView, newContagions);
                break;
            case MONTHS:
                paintMonthAccordingNewContagions(textView, newContagions);
                break;
            default:
                paintDayAccordingNewContagions(textView, newContagions);
        }
    }

    private static void paintRowAccordingDeaths(TextView textView, int deaths) {
        switch (tableType) {
            case WEEKS:
                paintWeekAccordingDeaths(textView, deaths);
                break;
            case MONTHS:
                paintMonthAccordingDeaths(textView, deaths);
                break;
            default:
                paintDayAccordingDeaths(textView, deaths);
        }
    }

    private static void paintDayAccordingNewContagions(TextView textView, int newContagions){
        if (newContagions < 2000 && newContagions >= 0)
            paintRow(textView, R.drawable.dark_green_border);
        else if (newContagions < 4000 && newContagions >= 2000)
            paintRow(textView, R.drawable.green_border);
        else if (newContagions < 6000 && newContagions >= 4000)
            paintRow(textView, R.drawable.yellow_border);
        else if (newContagions < 8000 && newContagions >= 6000)
            paintRow(textView, R.drawable.orange_border);
        else if (newContagions >= 8000)
            paintRow(textView, R.drawable.red_border);
    }

    private static void paintWeekAccordingNewContagions(TextView textView, int newContagions){
        if (newContagions < 10000 && newContagions >= 0)
            paintRow(textView, R.drawable.dark_green_border);
        else if (newContagions < 25000 && newContagions >= 10000)
            paintRow(textView, R.drawable.green_border);
        else if (newContagions < 50000 && newContagions >= 25000)
            paintRow(textView, R.drawable.yellow_border);
        else if (newContagions < 100000 && newContagions >= 50000)
            paintRow(textView, R.drawable.orange_border);
        else if (newContagions >= 100000)
            paintRow(textView, R.drawable.red_border);
    }

    private static void paintMonthAccordingNewContagions(TextView textView, int newContagions){
        if (newContagions < 50000 && newContagions >= 0)
            paintRow(textView, R.drawable.dark_green_border);
        else if (newContagions < 100000 && newContagions >= 50000)
            paintRow(textView, R.drawable.green_border);
        else if (newContagions < 250000 && newContagions >= 100000)
            paintRow(textView, R.drawable.yellow_border);
        else if (newContagions < 500000 && newContagions >= 250000)
            paintRow(textView, R.drawable.orange_border);
        else if (newContagions >= 500000)
            paintRow(textView, R.drawable.red_border);
    }

    private static void paintDayAccordingDeaths(TextView textView, int deaths){
        if (deaths < 50 && deaths >= 0)
            paintRow(textView, R.drawable.dark_green_border);
        else if (deaths < 100 && deaths >= 50)
            paintRow(textView, R.drawable.green_border);
        else if (deaths < 250 && deaths >= 100)
            paintRow(textView, R.drawable.yellow_border);
        else if (deaths < 500 && deaths >= 250)
            paintRow(textView, R.drawable.orange_border);
        else if (deaths >= 500)
            paintRow(textView, R.drawable.red_border);
    }

    private static void paintWeekAccordingDeaths(TextView textView, int deaths){
        if (deaths < 400 && deaths >= 0)
            paintRow(textView, R.drawable.dark_green_border);
        else if (deaths < 1500 && deaths >= 400)
            paintRow(textView, R.drawable.green_border);
        else if (deaths < 2500 && deaths >= 1500)
            paintRow(textView, R.drawable.yellow_border);
        else if (deaths < 4000 && deaths >= 2500)
            paintRow(textView, R.drawable.orange_border);
        else if (deaths >= 4000)
            paintRow(textView, R.drawable.red_border);
    }

    private static void paintMonthAccordingDeaths(TextView textView, int deaths){
        if (deaths < 2000 && deaths >= 0)
            paintRow(textView, R.drawable.dark_green_border);
        else if (deaths < 6000 && deaths >= 2000)
            paintRow(textView, R.drawable.green_border);
        else if (deaths < 10000 && deaths >= 6000)
            paintRow(textView, R.drawable.yellow_border);
        else if (deaths < 14000 && deaths >= 10000)
            paintRow(textView, R.drawable.orange_border);
        else if (deaths >= 14000)
            paintRow(textView, R.drawable.red_border);
    }

    private static void paintRow(TextView textView, @DrawableRes int res){
        textView.setBackgroundResource(res);
        textView.setTextColor(textView.getResources().getColor(R.color.black));
        textView.setTextSize(12);
    }
}
