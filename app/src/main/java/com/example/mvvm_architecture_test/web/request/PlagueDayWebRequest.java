package com.example.mvvm_architecture_test.web.request;

import com.example.mvvm_architecture_test.model.PlagueDay;
import com.example.mvvm_architecture_test.util.TaskUtils;

import org.threeten.bp.LocalDateTime;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlagueDayWebRequest implements WebRequest<List<PlagueDay>> {

    private List<PlagueDay> plagueDays;
    private static final String URL = "https://raw.githubusercontent.com/pcm-dpc/COVID-19/master/dati-andamento-nazionale/dpc-covid19-ita-andamento-nazionale.csv";

    public PlagueDayWebRequest(){
        this.plagueDays = new ArrayList<>();
    }

    @Override
    public List<PlagueDay> getAll() {
        TaskUtils.runSynchronizedTask(new Thread(() -> {
            try {
                plagueDays = updateData();
                if(plagueDays.isEmpty()) plagueDays = new ArrayList<>();
            }catch (IOException e){
                plagueDays = new ArrayList<>();
                e.printStackTrace();
            }
        }), 1000);
        return plagueDays;
    }

    public static String getURL() {
        return URL;
    }

    private List<PlagueDay> updateData() throws IOException {
        List<PlagueDay> plagueDays = new ArrayList<>();
        URL url = new URL(URL);
        int i = 0;
        String line;
        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
        while ((line = reader.readLine()) != null) {
            String[] lines = line.split(",");
            lines = prepareArray(lines);
            if (i != 0 && !line.equals("")) plagueDays.add(0, newDayOfPlague(lines));
            i++;
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
