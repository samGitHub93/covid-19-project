package com.example.mvvm_architecture_test.util;

import android.widget.CheckBox;

import java.util.List;

public class ColumnUtils {

    private static List<CheckBox> checkBoxes;
    private static boolean isChanged;

    public static List<CheckBox> getCheckBoxes() {
        return checkBoxes;
    }

    public static void setCheckBoxes(List<CheckBox> checkBoxes) {
        ColumnUtils.checkBoxes = checkBoxes;
    }

    public static boolean isChanged() {
        return isChanged;
    }

    public static void setChanged(boolean isChanged) {
        ColumnUtils.isChanged = isChanged;
    }
}
