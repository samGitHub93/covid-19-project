package com.example.mvvm_architecture_test.web.request;

import android.content.Context;

import com.example.mvvm_architecture_test.model.VaccinatedPerAge;
import com.example.mvvm_architecture_test.util.TaskUtils;

import org.apache.commons.io.FileUtils;
import org.threeten.bp.LocalDate;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VaccinatedPerAgeStorageRequest implements NotWebRequest<List<VaccinatedPerAge>, Boolean, URL>{

    private final Context context;
    private static final String FILE_NAME = "vaccine_age_data";
    private static final String FOLDER_NAME = "backup";
    private static final String SEPARATOR = ",";
    private List<VaccinatedPerAge> vaccinatedPerAgeList;
    private boolean backupSuccess;

    public VaccinatedPerAgeStorageRequest(Context context) {
        this.context = context;
    }

    @Override
    public List<VaccinatedPerAge> getAll() {
        this.vaccinatedPerAgeList = new ArrayList<>();
        TaskUtils.runSynchronizedTask(new Thread(() -> {
            try {
                vaccinatedPerAgeList = getFromStorage();
                if(vaccinatedPerAgeList.isEmpty()) vaccinatedPerAgeList = new ArrayList<>();
            }catch (IOException e){
                vaccinatedPerAgeList = new ArrayList<>();
                e.printStackTrace();
            }
        }),300);
        return vaccinatedPerAgeList;
    }

    @Override
    public Boolean save(URL url) {
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
        }), 300);
        return backupSuccess;
    }

    private List<VaccinatedPerAge> getFromStorage() throws IOException{
        List<VaccinatedPerAge> vaccinatedPerAgeList = new ArrayList<>();
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
                if (i != 0 && !line.equals("")) vaccinatedPerAgeList.add(newVaccinatedPerAge(lines));
                i++;
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        return vaccinatedPerAgeList;
    }

    private String[] prepareArray(String[] lines){
        lines = Arrays.copyOf(lines, 9);
        for(int j=0;j<7;j++) {
            if (lines[j] == null || lines[j].equals(""))
                lines[j] = "0";
        }
        return lines;
    }

    private VaccinatedPerAge newVaccinatedPerAge(String[] lines){
        return new VaccinatedPerAge(
                lines[0],
                Integer.parseInt(lines[1]),
                Integer.parseInt(lines[4]),
                Integer.parseInt(lines[5]),
                Integer.parseInt(lines[7]),
                Integer.parseInt(lines[6]),
                LocalDate.parse(lines[8])
        );
    }
}
