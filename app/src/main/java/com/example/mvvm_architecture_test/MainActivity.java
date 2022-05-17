package com.example.mvvm_architecture_test;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LiveData;

import com.example.mvvm_architecture_test.auto_complete_text_view.TextInputDropDownMenu;
import com.example.mvvm_architecture_test.enumerator.PaintType;
import com.example.mvvm_architecture_test.enumerator.TableType;
import com.example.mvvm_architecture_test.fragment.fragment_main.DayFragment;
import com.example.mvvm_architecture_test.fragment.fragment_main.ErrorFragment;
import com.example.mvvm_architecture_test.fragment.fragment_main.IFragment;
import com.example.mvvm_architecture_test.fragment.fragment_main.MonthFragment;
import com.example.mvvm_architecture_test.fragment.fragment_main.WeekFragment;
import com.example.mvvm_architecture_test.model.PlagueDay;
import com.example.mvvm_architecture_test.painter.Painter;
import com.example.mvvm_architecture_test.service.SettingService;
import com.example.mvvm_architecture_test.util.ColumnUtils;
import com.example.mvvm_architecture_test.view_model.PlagueDayViewModel;
import com.google.android.material.tabs.TabLayout;

import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private static FragmentManager fragmentManager;
    private static IFragment currentFragment;
    private static TabLayout tabLayout;
    private TextInputDropDownMenu autoCompleteTextView;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SettingService settingService = new SettingService(getApplication());
        fragmentManager = getSupportFragmentManager();
        tabLayout = findViewById(R.id.tabs);
        addTabLayoutAction();
        autoCompleteTextView = findViewById(R.id.auto_complete_text_view);
        addItemsToTextView();
        addTextViewAction();
        ColumnUtils.setCheckBoxes(settingService.getSetting());
        if(currentFragment == null) initFragment();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("tab_number", tabLayout.getSelectedTabPosition());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.refresh) {
            refreshFragment();
        } else if(item.getItemId() == R.id.vaccine){
            Intent vaccineActivity = new Intent(MainActivity.this, VaccineActivity.class);
            startActivity(vaccineActivity);
        } else if (item.getItemId() == R.id.edit_columns) {
            Intent columnActivity = new Intent(MainActivity.this, ColumnSettingActivity.class);
            columnActivity.putExtra("tab_number", tabLayout.getSelectedTabPosition());
            startActivity(columnActivity);
        } else if (item.getItemId() == R.id.legends) {
            Intent infoActivity = new Intent(MainActivity.this, InfoActivity.class);
            startActivity(infoActivity);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (ColumnUtils.isChanged()) {
            int position = tabLayout.getSelectedTabPosition();
            if (position == 0) setCurrentFragment(new DayFragment());
            if (position == 1) setCurrentFragment(new WeekFragment());
            if (position == 2) setCurrentFragment(new MonthFragment());
            ColumnUtils.setChanged(false);
        }
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        TabLayout.Tab tab = tabLayout.getTabAt(savedInstanceState.getInt("tab_number"));
        assert tab != null;
        tab.select();
    }

    public void initFragment(){
        FragmentTransaction ft = fragmentManager.beginTransaction();
        currentFragment = new DayFragment();
        ft.replace(R.id.fragment, (DayFragment) currentFragment);
        ft.commit();
    }

    public void addTabLayoutAction(){
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(Objects.equals(tab.getText(), getString(R.string.days))){
                    Painter.setTableType(TableType.DAYS);
                    switchFragment(TableType.DAYS);
                }else if(Objects.equals(tab.getText(), getString(R.string.weeks))){
                    Painter.setTableType(TableType.WEEKS);
                    switchFragment(TableType.WEEKS);
                }else if(Objects.equals(tab.getText(), getString(R.string.months))){
                    Painter.setTableType(TableType.MONTHS);
                    switchFragment(TableType.MONTHS);
                }
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}
            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });
    }

    public void switchFragment(TableType tableType) {
        if(currentFragment == null) initFragment();
        else {
            switch (tableType) {
                case DAYS:
                    currentFragment = new DayFragment();
                    break;
                case WEEKS:
                    currentFragment = new WeekFragment();
                    break;
                case MONTHS:
                    currentFragment = new MonthFragment();
                    break;
            }
            setCurrentFragment(currentFragment);
        }
    }

    public static void setCurrentFragment(IFragment fragment) {
        FragmentTransaction ft = fragmentManager.beginTransaction();
        if(fragment instanceof DayFragment){
            ft.replace(R.id.fragment, (DayFragment) fragment);
        }else if(fragment instanceof WeekFragment){
            ft.replace(R.id.fragment, (WeekFragment) fragment);
        }else if(fragment instanceof MonthFragment){
            ft.replace(R.id.fragment, (MonthFragment) fragment);
        }else {
            ft.replace(R.id.fragment, (ErrorFragment) fragment);
        }
        ft.commit();
        currentFragment = fragment;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void addItemsToTextView(){
        String[] items = new String[] {getResources().getString(R.string.new_contagions_theme),getResources().getString(R.string.deaths_theme)};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, items);
        autoCompleteTextView.setAdapter(adapter);
        autoCompleteTextView.setText(getResources().getString(R.string.new_contagions_theme), false);
        autoCompleteTextView.setHintTextColor(getResources().getColor(R.color.white));
    }

    public void addTextViewAction(){
        autoCompleteTextView.setOnItemClickListener((parent, view, position, id) -> {
            if(id == 0){
                Painter.setPaintType(PaintType.NEW_CONTAGIONS);
                if(currentFragment instanceof DayFragment) setCurrentFragment(new DayFragment());
                if(currentFragment instanceof WeekFragment) setCurrentFragment(new WeekFragment());
                if(currentFragment instanceof MonthFragment) setCurrentFragment(new MonthFragment());
            }else if(id == 1){
                Painter.setPaintType(PaintType.DEATHS);
                if(currentFragment instanceof DayFragment) setCurrentFragment(new DayFragment());
                if(currentFragment instanceof WeekFragment) setCurrentFragment(new WeekFragment());
                if(currentFragment instanceof MonthFragment) setCurrentFragment(new MonthFragment());
            }
        });
    }

    public static void refreshFragment() {
        if (tabLayout.getSelectedTabPosition() == 0) {
            LiveData<List<PlagueDay>> liveData = ((PlagueDayViewModel) currentFragment.getViewModel()).getUpgradedData(TableType.DAYS);
            if (liveData.getValue() != null && !liveData.getValue().isEmpty())
                setCurrentFragment(new DayFragment());
            else setCurrentFragment(new ErrorFragment());
        } else if (tabLayout.getSelectedTabPosition() == 1) {
            LiveData<List<PlagueDay>> liveData = ((PlagueDayViewModel) currentFragment.getViewModel()).getUpgradedData(TableType.WEEKS);
            if (liveData.getValue() != null && !liveData.getValue().isEmpty())
                setCurrentFragment(new WeekFragment());
            else setCurrentFragment(new ErrorFragment());
        } else if (tabLayout.getSelectedTabPosition() == 2) {
            LiveData<List<PlagueDay>> liveData = ((PlagueDayViewModel) currentFragment.getViewModel()).getUpgradedData(TableType.MONTHS);
            if (liveData.getValue() != null && !liveData.getValue().isEmpty())
                setCurrentFragment(new MonthFragment());
            else setCurrentFragment(new ErrorFragment());
        }
    }
}
