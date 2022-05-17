package com.example.mvvm_architecture_test.web.request;

import android.content.Context;

import com.example.mvvm_architecture_test.util.TaskUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SettingRequest implements NotWebRequest<List<String>, Boolean, List<String>> {

    private final Context context;
    private static final String FILE_NAME = "columns";
    private static final String FOLDER_NAME = "settings";
    private static final String SEPARATOR = ",";
    private List<String> columns;
    private boolean saveResult;

    public SettingRequest(Context context){
        this.context = context;
    }

    @Override
    public List<String> getAll() {
        this.columns = new ArrayList<>();
        TaskUtils.runSynchronizedTask(new Thread(() -> columns = getSetting()), 100);
        return columns;
    }

    @Override
    public Boolean save(List<String> columns) {
        TaskUtils.runNotSynchronizedTask(new Thread(() -> {
            File dir = new File(context.getFilesDir(), FOLDER_NAME);
            if (!dir.exists()) dir.mkdir();
            try {
                File file = new File(dir, FILE_NAME);
                FileWriter writer = new FileWriter(file);
                for (String column : columns) {
                    writer.append(column);
                    writer.append(SEPARATOR);
                }
                writer.flush();
                writer.close();
                saveResult = true;
            } catch (IOException e) {
                e.printStackTrace();
                saveResult = false;
            }
        }), 100);
        return saveResult;
    }

    private List<String> getSetting() {
        List<String> columns = new ArrayList<>();
        File dir = new File(context.getFilesDir(), FOLDER_NAME);
        if (!dir.exists()) dir.mkdir();
        try {
            File file = new File(FILE_NAME);
            BufferedReader br = new BufferedReader(new FileReader(dir.getPath() + "/" + file));
            String line;
            while ((line = br.readLine()) != null) {
                String[] lines = line.split(SEPARATOR);
                columns = Arrays.asList(lines);
            }
            br.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return columns;
    }
}
