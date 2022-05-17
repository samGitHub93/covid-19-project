package com.example.mvvm_architecture_test.util;

import com.example.mvvm_architecture_test.model.PlagueDay;

import org.threeten.bp.DayOfWeek;
import org.threeten.bp.LocalDateTime;

import java.util.ArrayList;
import java.util.List;

public class TableDataUtils {

    public static List<PlagueDay> toDays(List<PlagueDay> list){
        List<PlagueDay> days = new ArrayList<>();
        if(list == null || list.size() == 0) return days;
        for(int i = 0; i < list.size() - 1; i++){
            PlagueDay day = new PlagueDay(list.get(i));
            days.add(day);
            days.get(days.size() - 1).setDeaths(list.get(i).getDeaths() - list.get(i+1).getDeaths());
            days.get(days.size() - 1).setHealed(list.get(i).getHealed() - list.get(i+1).getHealed());
            days.get(days.size() - 1).setBuffers(list.get(i).getBuffers() - list.get(i+1).getBuffers());
        }
        return days;
    }

    public static List<PlagueDay> toWeeks(List<PlagueDay> list) {
        List<PlagueDay> weeks=new ArrayList<>();
        if(list == null || list.size() == 0) return weeks;
        for(int i = 0; i< list.size(); i++){
            if(list.get(i).getDate().getDayOfWeek() == DayOfWeek.MONDAY) {
                PlagueDay monday = new PlagueDay(list.get(i));
                weeks.add(monday);
                int newContagionsValue = monday.getNewContagions();
                int newContagionVariationValue = monday.getContagionsVariation();
                for(int j = 1; j<7; j++){
                    if(list.size() == (i+j)) break;
                    newContagionsValue += list.get(i+j).getNewContagions();
                    newContagionVariationValue += list.get(i+j).getContagionsVariation();
                }
                modifyValues(weeks, newContagionsValue, newContagionVariationValue);
            }
        }
        return weeks;
    }

    public static List<PlagueDay> toMonths(List<PlagueDay> list) {
        List<PlagueDay> months=new ArrayList<>();
        if(list == null || list.size() == 0) return months;
        for(int i = 0; i< list.size(); i++) {
            if(endOfMonth(list.get(i).getDate())) {
                PlagueDay endOfMonth = new PlagueDay(list.get(i));
                months.add(endOfMonth);
                int newContagionsValue = endOfMonth.getNewContagions();
                int newContagionVariationValue = endOfMonth.getContagionsVariation();
                for(int j = 1; j<list.get(i).getDate().getDayOfMonth(); j++){
                    if(list.size() == (i+j)) break;
                    newContagionsValue += list.get(i+j).getNewContagions();
                    newContagionVariationValue += list.get(i+j).getContagionsVariation();
                }
                modifyValues(months, newContagionsValue, newContagionVariationValue);
            }
        }
        return months;
    }

    private static void modifyValues(List<PlagueDay> list, int newContagionsValue, int newContagionVariationValue){
        list.get(list.size()-1).setNewContagions(newContagionsValue);
        list.get(list.size()-1).setContagionsVariation(newContagionVariationValue);
        if(list.size() > 1){
            list.get(list.size()-2).setDeaths(list.get(list.size()-2).getDeaths() - list.get(list.size()-1).getDeaths());
            list.get(list.size()-2).setHealed(list.get(list.size()-2).getHealed() - list.get(list.size()-1).getHealed());
            list.get(list.size()-2).setBuffers(list.get(list.size()-2).getBuffers() - list.get(list.size()-1).getBuffers());
        }
    }

    private static boolean endOfMonth(LocalDateTime localDateTime){
        return !localDateTime.getMonth().equals(localDateTime.plusDays(1).getMonth());
    }
}
