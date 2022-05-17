package com.example.mvvm_architecture_test;

import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.mvvm_architecture_test.fragment.fragment_info.NewCasesFragment;
import com.example.mvvm_architecture_test.fragment.fragment_info.NewDeathsFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.Objects;

public class InfoActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;
    private Fragment currentFragment;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        fragmentManager = getSupportFragmentManager();
        tabLayout = findViewById(R.id.tabs_info);
        addTabLayoutAction();
        initFragment();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("tab_number", tabLayout.getSelectedTabPosition());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        TabLayout.Tab tab = tabLayout.getTabAt(savedInstanceState.getInt("tab_number"));
        assert tab != null;
        tab.select();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.info_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initFragment(){
        FragmentTransaction ft = fragmentManager.beginTransaction();
        currentFragment = new NewCasesFragment();
        ft.replace(R.id.fragment_info, currentFragment);
        ft.commit();
    }

    private void addTabLayoutAction(){
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(Objects.equals(tab.getText(), getString(R.string.new_contagions_title))){
                    setCurrentFragment(new NewCasesFragment());
                }else if(Objects.equals(tab.getText(), getString(R.string.death_title))){
                    setCurrentFragment(new NewDeathsFragment());
                }
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}
            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });
    }

    public void setCurrentFragment(Fragment fragment) {
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.fragment_info, fragment);
        ft.commit();
        currentFragment = fragment;
    }
}
