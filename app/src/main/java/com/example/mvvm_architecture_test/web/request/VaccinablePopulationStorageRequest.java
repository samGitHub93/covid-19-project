package com.example.mvvm_architecture_test.web.request;

import android.content.Context;

import com.example.mvvm_architecture_test.model.VaccinablePopulation;
import com.example.mvvm_architecture_test.util.TaskUtils;

import org.apache.commons.io.FileUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VaccinablePopulationStorageRequest implements NotWebRequest<List<VaccinablePopulation>, Boolean, URL>{

    private final Context context;
    private static final String FILE_NAME = "vaccine_vaccinable_data";
    private static final String FOLDER_NAME = "backup";
    private static final String SEPARATOR = ",";
    private List<VaccinablePopulation> vaccinablePopulationList;
    private boolean backupSuccess;

    public VaccinablePopulationStorageRequest(Context context) {
        this.context = context;
    }

    @Override
    public List<VaccinablePopulation> getAll() {
        this.vaccinablePopulationList = new ArrayList<>();
        TaskUtils.runSynchronizedTask(new Thread(() -> {
            try {
                vaccinablePopulationList = getFromStorage();
                if(vaccinablePopulationList.isEmpty()) vaccinablePopulationList = new ArrayList<>();
            }catch (IOException e){
                vaccinablePopulationList = new ArrayList<>();
                e.printStackTrace();
            }
        }),300);
        return vaccinablePopulationList;
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

    private List<VaccinablePopulation> getFromStorage() throws IOException{
        List<VaccinablePopulation> vaccinablePopulationList = new ArrayList<>();
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
                if (i != 0 && !line.equals("")) vaccinablePopulationList.add(newVaccinablePopulation(lines));
                i++;
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        return vaccinablePopulationList;
    }

    private String[] prepareArray(String[] lines){
        lines = Arrays.copyOf(lines, 4);
        for(int j=0;j<4;j++) {
            if (lines[j] == null || lines[j].equals(""))
                lines[j] = "0";
        }
        return lines;
    }

    private VaccinablePopulation newVaccinablePopulation(String[] lines){
        return new VaccinablePopulation(lines[0],lines[1],lines[2],Integer.parseInt(lines[3]));
    }
}
