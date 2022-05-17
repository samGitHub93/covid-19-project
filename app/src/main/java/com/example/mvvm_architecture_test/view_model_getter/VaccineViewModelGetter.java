package com.example.mvvm_architecture_test.view_model_getter;

import static org.threeten.bp.temporal.ChronoUnit.DAYS;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.mvvm_architecture_test.enumerator.AgeRange;
import com.example.mvvm_architecture_test.enumerator.VaccineModelType;
import com.example.mvvm_architecture_test.model.VaccinablePopulation;
import com.example.mvvm_architecture_test.model.VaccinatedPerAge;
import com.example.mvvm_architecture_test.view_model.VaccinablePopulationViewModel;
import com.example.mvvm_architecture_test.view_model.VaccinatedPerAgeViewModel;

import org.threeten.bp.LocalDate;

import java.util.List;
import java.util.Objects;

public class VaccineViewModelGetter {

    private LiveData<List<VaccinablePopulation>> vaccinableLiveData;
    private LiveData<List<VaccinatedPerAge>> vaccinatedPerAgeLiveData;

    public VaccineViewModelGetter(Application application) {
        VaccinablePopulationViewModel vaccinableViewModel = new VaccinablePopulationViewModel(application);
        VaccinatedPerAgeViewModel vaccinatedPerAgeViewModel = new VaccinatedPerAgeViewModel(application);
        initActivity(vaccinableViewModel, vaccinatedPerAgeViewModel);
    }

    public boolean areListsEmpty(){
        return Objects.requireNonNull(vaccinableLiveData.getValue()).isEmpty() || Objects.requireNonNull(vaccinatedPerAgeLiveData.getValue()).isEmpty();
    }

    public LocalDate getVaccineDate() {
        List<VaccinatedPerAge> vaccinatedPerAgeList = vaccinatedPerAgeLiveData.getValue();
        assert vaccinatedPerAgeList != null;
        return vaccinatedPerAgeList.get(0).getDate();
    }

    public int getVaccinablePopulationPerAge(AgeRange ageRange){
        List<VaccinablePopulation> vaccinablePopulation = vaccinableLiveData.getValue();
        int vaccinablePerAge = 0;
        assert vaccinablePopulation != null;
        for(VaccinablePopulation vp : vaccinablePopulation){
            vaccinablePerAge += switchVaccinableAgeRange(vp, ageRange);
        }
        return vaccinablePerAge;
    }

    public VaccinatedPerAge getVaccinatedPerAge(AgeRange ageRange){
        List<VaccinatedPerAge> vaccinatedPerAgeList = vaccinatedPerAgeLiveData.getValue();
        return switchAgeRange(vaccinatedPerAgeList, ageRange);
    }

    private void initActivity(VaccinablePopulationViewModel vaccinableViewModel, VaccinatedPerAgeViewModel vaccinatedPerAgeViewModel){
        this.vaccinableLiveData = vaccinableViewModel.getNotUpgradedData(VaccineModelType.VACCINABLE_POPULATION);
        this.vaccinatedPerAgeLiveData = vaccinatedPerAgeViewModel.getNotUpgradedData(VaccineModelType.VACCINATED_PER_AGE);
//        if(areListsEmpty() || (DAYS.between(getVaccineDate(),LocalDate.now())) != 0) {
//            updateLists(vaccinableViewModel, vaccinatedPerAgeViewModel);
//        }
    }

    private void updateLists(VaccinablePopulationViewModel vaccinableViewModel, VaccinatedPerAgeViewModel vaccinatedPerAgeViewModel){
        this.vaccinableLiveData = vaccinableViewModel.getUpgradedData(VaccineModelType.VACCINABLE_POPULATION);
        this.vaccinatedPerAgeLiveData = vaccinatedPerAgeViewModel.getUpgradedData(VaccineModelType.VACCINATED_PER_AGE);
    }

    private int switchVaccinableAgeRange(VaccinablePopulation vp, AgeRange ageRange){
        switch (ageRange){
            case _05_11:
                if(vp.getAgeRange().equals("05-11")) return vp.getNumberOfPerson();
                else return 0;
            case _12_19:
                if(vp.getAgeRange().equals("12-19")) return vp.getNumberOfPerson();
                else return 0;
            case _20_29:
                if(vp.getAgeRange().equals("20-29")) return vp.getNumberOfPerson();
                else return 0;
            case _30_39:
                if(vp.getAgeRange().equals("30-39")) return vp.getNumberOfPerson();
                else return 0;
            case _40_49:
                if(vp.getAgeRange().equals("40-49")) return vp.getNumberOfPerson();
                else return 0;
            case _50_59:
                if(vp.getAgeRange().equals("50-59")) return vp.getNumberOfPerson();
                else return 0;
            case _60_69:
                if(vp.getAgeRange().equals("60-69")) return vp.getNumberOfPerson();
                else return 0;
            case _70_79:
                if(vp.getAgeRange().equals("70-79")) return vp.getNumberOfPerson();
                else return 0;
            default:
                if(vp.getAgeRange().equals("80+")) return vp.getNumberOfPerson();
                else return 0;
        }
    }

    private VaccinatedPerAge switchAgeRange(List<VaccinatedPerAge> vaccinatedPerAgeList, AgeRange ageRange){
        switch (ageRange){
            case _05_11:
                return vaccinatedPerAgeList.get(0);
            case _12_19:
                return vaccinatedPerAgeList.get(1);
            case _20_29:
                return vaccinatedPerAgeList.get(2);
            case _30_39:
                return vaccinatedPerAgeList.get(3);
            case _40_49:
                return vaccinatedPerAgeList.get(4);
            case _50_59:
                return vaccinatedPerAgeList.get(5);
            case _60_69:
                return vaccinatedPerAgeList.get(6);
            case _70_79:
                return vaccinatedPerAgeList.get(7);
            case _80_89:
                return vaccinatedPerAgeList.get(8);
            default:
                return vaccinatedPerAgeList.get(9);
        }
    }
}
