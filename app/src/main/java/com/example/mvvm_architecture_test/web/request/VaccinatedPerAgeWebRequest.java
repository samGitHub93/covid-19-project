package com.example.mvvm_architecture_test.web.request;

import com.example.mvvm_architecture_test.model.VaccinatedPerAge;
import com.example.mvvm_architecture_test.util.TaskUtils;

import org.threeten.bp.LocalDate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VaccinatedPerAgeWebRequest implements WebRequest<List<VaccinatedPerAge>>{

    private List<VaccinatedPerAge> vaccinatedPerAge;
    private static final String URL = "https://raw.githubusercontent.com/italia/covid19-opendata-vaccini/master/dati/anagrafica-vaccini-summary-latest.csv";

    @Override
    public List<VaccinatedPerAge> getAll() {
        TaskUtils.runSynchronizedTask(new Thread(() -> {
            try {
                vaccinatedPerAge = updateData();
                if(vaccinatedPerAge.isEmpty()) vaccinatedPerAge = new ArrayList<>();
            }catch (IOException e){
                vaccinatedPerAge = new ArrayList<>();
                e.printStackTrace();
            }
        }), 250);
        return vaccinatedPerAge;
    }

    public static String getUrl(){
        return URL;
    }

    private List<VaccinatedPerAge> updateData() throws IOException {
        List<VaccinatedPerAge> vaccinatedPerAge = new ArrayList<>();
        URL url = new URL(URL);
        int i = 0;
        String line;
        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
        while ((line = reader.readLine()) != null) {
            String[] lines = line.split(",");
            lines = prepareArray(lines);
            if (i != 0 && !line.equals("")) vaccinatedPerAge.add(newVaccinatedPopulation(lines));
            i++;
        }
        return vaccinatedPerAge;
    }

    private String[] prepareArray(String[] lines){
        lines = Arrays.copyOf(lines, 9);
        for(int j=0;j<9;j++) {
            if (lines[j] == null || lines[j].equals(""))
                lines[j] = "0";
        }
        return lines;
    }

    private VaccinatedPerAge newVaccinatedPopulation(String[] lines){
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
