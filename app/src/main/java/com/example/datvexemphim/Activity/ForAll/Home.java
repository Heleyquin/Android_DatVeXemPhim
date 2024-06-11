package com.example.datvexemphim.Activity.ForAll;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.datvexemphim.Activity.PhimFragment.HomeFragment;
import com.example.datvexemphim.Activity.RapFragment.RapFragment;
import com.example.datvexemphim.Activity.SettingFragment.SettingFragment;
import com.example.datvexemphim.R;
import com.example.datvexemphim.databinding.ActivityHomeBinding;

public class Home extends AppCompatActivity {

    ActivityHomeBinding binding;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        replaceFragment(new HomeFragment());
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if(itemId == R.id.home){
                replaceFragment(new HomeFragment());
            } else if (itemId == R.id.phim) {
                replaceFragment(new RapFragment());

            }else if (itemId == R.id.setting) {
                replaceFragment(new SettingFragment());
            }
//            switch (item.getItemId()) {
//                case R.id.home:
//                    replaceFragment(new HomeFragment());
//                    break;
//                case R.id.phim:
//                    replaceFragment(new RapFragment());
//                    break;
//                case R.id.history:
//                    replaceFragment(new HistoryFragment());
//                    break;
//                case R.id.setting:
//                    replaceFragment(new SettingFragment());
//                    break;
//            }
            return true;
        });


    }
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameNav, fragment);
        fragmentTransaction.commit();
    }
}