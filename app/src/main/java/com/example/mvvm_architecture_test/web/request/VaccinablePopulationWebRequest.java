package com.example.mvvm_architecture_test.web.request;

import com.example.mvvm_architecture_test.model.VaccinablePopulation;
import com.example.mvvm_architecture_test.util.TaskUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VaccinablePopulationWebRequest implements WebRequest<List<VaccinablePopulation>>{

    private List<VaccinablePopulation> vaccinablePopulation;
    private static final String URL = "https://raw.githubusercontent.com/italia/covid19-opendata-vaccini/master/dati/platea.csv";

    @Override
    public List<VaccinablePopulation> getAll() {
        TaskUtils.runSynchronizedTask(new Thread(() -> {
            try {
                vaccinablePopulation = updateData();
                if(vaccinablePopulation.isEmpty()) vaccinablePopulation = new ArrayList<>();
            }catch (IOException e){
                vaccinablePopulation = new ArrayList<>();
                e.printStackTrace();
            }
        }), 250);
        return vaccinablePopulation;
    }

    public static String getUrl(){
        return URL;
    }

    private List<VaccinablePopulation> updateData() throws IOException {
        List<VaccinablePopulation> vaccinablePopulation = new ArrayList<>();
        URL url = new URL(URL);
        int i = 0;
        String line;
        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
        while ((line = reader.readLine()) != null) {
            String[] lines = line.split(",");
            lines = prepareArray(lines);
            if (i != 0 && !line.equals("")) vaccinablePopulation.add(newVaccinablePopulation(lines));
            i++;
        }
        return vaccinablePopulation;
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
        return new VaccinablePopulation(
                lines[0],
                lines[1],
                lines[2],
                Integer.parseInt(lines[3])
        );
    }
}
