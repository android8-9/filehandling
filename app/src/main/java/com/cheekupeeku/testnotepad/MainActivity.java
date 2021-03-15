package com.cheekupeeku.testnotepad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.cheekupeeku.testnotepad.databinding.HomeBinding;

import java.io.File;
import java.io.FileWriter;

public class MainActivity extends AppCompatActivity {
    HomeBinding binding;
    String dirPath = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = HomeBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());
        ApplicationInfo info = getApplicationInfo();
        // data/data/com.cheekupeeku.testnotepad/notes
        dirPath = info.dataDir+"/notes";
        File file = new File(dirPath);
        if(!file.exists()){
            file.mkdir();
        }
        binding.btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(MainActivity.this,HistoryActivity.class);
                startActivity(in);
            }
        });
        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = binding.etTitle.getText().toString();
                String data = binding.etDescription.getText().toString();
                String filePath = dirPath+"/"+title+".txt";
                try{
                    FileWriter fw = new FileWriter(filePath);
                    fw.write(data);
                    fw.close();
                    Toast.makeText(MainActivity.this, "File saved", Toast.LENGTH_SHORT).show();
                    binding.etDescription.setText("");
                    binding.etTitle.setText("");
                }
                catch (Exception e){
                    Toast.makeText(MainActivity.this, ""+e, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}