package com.cheekupeeku.testnotepad;

import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.cheekupeeku.testnotepad.databinding.HistoryBinding;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {
    HistoryBinding binding;
    List<String> al;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = HistoryBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());
        String dirPath = getApplicationInfo().dataDir+"/notes";
        File f = new File(dirPath);
        if(f.exists()){
            String fileName[] = f.list();
            al = Arrays.asList(fileName);
            FileAdapter adapter = new FileAdapter(HistoryActivity.this,al);
            binding.rv.setAdapter(adapter);
            binding.rv.setLayoutManager(new LinearLayoutManager(HistoryActivity.this));
        }
    }
}
