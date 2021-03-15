package com.cheekupeeku.testnotepad;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import com.cheekupeeku.testnotepad.databinding.FileItemListBinding;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class FileAdapter extends RecyclerView.Adapter<FileAdapter.FileViewHolder> {
    Context context;
    List<String> al;
    public FileAdapter(Context context,List<String>al){
        this.context = context;
        this.al = al;
    }
    @NonNull
    @Override
    public FileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        FileItemListBinding binding = FileItemListBinding.inflate(LayoutInflater.from(context),parent,false);
        return new FileViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull FileViewHolder holder, int position) {
       String fileName = al.get(position);
       holder.binding.tvFileName.setText(fileName);
    }

    @Override
    public int getItemCount() {
        return al.size();
    }

    public class FileViewHolder extends RecyclerView.ViewHolder{
       FileItemListBinding binding;
       public FileViewHolder(FileItemListBinding binding){
           super(binding.getRoot());
           this.binding = binding;
           binding.ivMenu.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   PopupMenu popupMenu = new PopupMenu(context,binding.ivMenu);
                   Menu menu = popupMenu.getMenu();
                   menu.add("View");
                   menu.add("Edit");
                   menu.add("Delete");
                   popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                       @Override
                       public boolean onMenuItemClick(MenuItem item) {
                           String title = item.getTitle().toString();
                           if(title.equals("View")){
                               int position = getAdapterPosition();
                               String fileName = al.get(position);
                               String filePath = context.getApplicationInfo().dataDir+"/notes/"+fileName;
                               try{
                                   FileReader fr = new FileReader(filePath);
                                   String data = "";
                                   while(true){
                                       int x = fr.read();
                                       if(x == -1)
                                           break;
                                       data = data +(char)x;
                                   }
                                   Toast.makeText(context, data, Toast.LENGTH_SHORT).show();
                               }
                               catch (Exception e){
                                   Toast.makeText(context, ""+e, Toast.LENGTH_SHORT).show();
                               }
                           }
                           return false;
                       }
                   });
                   popupMenu.show();
               }
           });
       }
   }
}
