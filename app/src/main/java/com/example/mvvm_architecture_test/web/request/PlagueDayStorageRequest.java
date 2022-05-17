package com.example.mvvm_architecture_test.web.request;

import android.content.Context;

import com.example.mvvm_architecture_test.model.PlagueDay;
import com.example.mvvm_architecture_test.util.TaskUtils;

import org.apache.commons.io.FileUtils;
import org.threeten.bp.LocalDateTime;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlagueDayStorageRequest implements NotWebRequest<List<PlagueDay>, Boolean, URL> {

    private final Context context;
    private static final String FILE_NAME = "data";
    private static final String FOLDER_NAME = "backup";
    private static final String SEPARATOR = ",";
    private List<PlagueDay> plagueDays;
    private boolean backupSuccess;

    public PlagueDayStorageRequest(Context context){
        this.context = context;
    }

    @Override
    public List<PlagueDay> getAll() {
        this.plagueDays = new ArrayList<>();
        TaskUtils.runSynchronizedTask(new Thread(() -> {
            try {
                plagueDays = getFromStorage();
                if(plagueDays.isEmpty()) plagueDays = new ArrayList<>();
            }catch (IOException e){
                plagueDays = new ArrayList<>();
                e.printStackTrace();
            }
        }),500);
        return plagueDays;
    }

    @Override
    public Boolean save(URL url){
        TaskUtils.runNotSynchronizedTask(new Thread(() -> {
            File dir = new File(context.getFilesDir(), FOLDER_NAME);
            if (!dir.exists()) dir.mkdir();
            try {
                File newFile = new File(dir, FILE_NAME);
                FileUtils.copyURLToFile(url, newFile);
                backupSuccess = true;
            } catch (IOException e) {
                e.printStackTrace();
                backupSuccess = false;
            }
        }), 500);
        return backupSuccess;
    }

    private List<PlagueDay> getFromStorage() throws IOException{
        List<PlagueDay> plagueDays = new ArrayList<>();
        File dir = new File(context.getFilesDir(), FOLDER_NAME);
        if (!dir.exists()) dir.mkdir();
        try{
            int i = 0;
            String line;
            File file = new File(FILE_NAME);
            BufferedReader br = new BufferedReader(new FileReader(dir.getPath() + "/" + file));
            while ((line = br.readLine()) != null) {
                String[] lines = line.split(SEPARATOR);
                lines = prepareArray(lines);
                if (i != 0 && !line.equals("")) plagueDays.add(0, newDayOfPlague(lines));
                i++;
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        return plagueDays;
    }

    private String[] prepareArray(String[] lines){
        lines = Arrays.copyOf(lines, 24);
        for(int j=0;j<24;j++) {
            if (lines[j] == null || lines[j].equals(""))
                lines[j] = "0";
        }
        return lines;
    }

    private PlagueDay newDayOfPlague(String[] lines){
        return new PlagueDay(
                LocalDateTime.parse(lines[0]),  // date
                Integer.parseInt(lines[14]),    // buffers
                Integer.parseInt(lines[6]),     // contagions
                Integer.parseInt(lines[7]),     // contagions variation
                Integer.parseInt(lines[10]),    // deaths
                Integer.parseInt(lines[2]),     // hospitalized with symptoms
                Integer.parseInt(lines[3]),     // intensive care
                Integer.parseInt(lines[4]),     // total hospitalized
                Integer.parseInt(lines[8]),     // new contagions
                Integer.parseInt(lines[5]),     // home isolation
                Integer.parseInt(lines[9]),     // healed
                Integer.parseInt(lines[20]),    // total positive molecular tests
                Integer.parseInt(lines[21]),    // total positive rapid tests
                Integer.parseInt(lines[22]),    // total molecular tests
                Integer.parseInt(lines[23]));   // total rapid tests
    }
}
