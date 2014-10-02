package com.lezapp.tarea.textos.app;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class FileChooser extends ListActivity{

    private File direccion;
    private FileArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        direccion = new File("/sdcard/");
        fill(direccion);
    }



    public String leer(String nombre){

        try{

            File f;
            FileReader lectorArchivo;

            f = new File(nombre);
            lectorArchivo = new FileReader(f);
            BufferedReader br = new BufferedReader(lectorArchivo);

            String l="";
            String aux="";

            while(true)
            {
                aux=br.readLine();
                if(aux!=null)
                    l=l+aux+" ";
                else
                    break;
            }

            br.close();
            lectorArchivo.close();
            return l;

        }catch(IOException e){
            System.out.println("Error:"+e.getMessage());
        }
        return null;
    }



    private void fill(File f){

        File[]dirs = f.listFiles();
        this.setTitle("Current Dir: "+f.getName());
        List<Opcion> dir = new ArrayList<Opcion>();
        List<Opcion>fls = new ArrayList<Opcion>();



        try{
            for(File ff: dirs)
            {
                if(ff.isDirectory())
                    dir.add(new Opcion(ff.getName(),"Folder",ff.getAbsolutePath()));
                else
                {
                    fls.add(new Opcion(ff.getName(),"File Size: "+ff.length(),ff.getAbsolutePath()));
                }
            }
        }catch(Exception e)
        {

        }
        Collections.sort(dir);
        Collections.sort(fls);
        dir.addAll(fls);
        if(!f.getName().equalsIgnoreCase("sdcard"))
            dir.add(0,new Opcion("..","Parent Directory",f.getParent()));

        adapter = new FileArrayAdapter(FileChooser.this,R.layout.activity_file_chooser,dir);
        this.setListAdapter(adapter);}

    protected void onListItemClick(ListView l, View v, int position, long id) {
        // TODO Auto-generated method stub
        super.onListItemClick(l, v, position, id);
        Opcion o = adapter.getItem(position);
        if(o.getData().equalsIgnoreCase("folder")||o.getData().equalsIgnoreCase("parent directory")){
            direccion = new File(o.getPath());
            fill(direccion);

        }
      //  else if(){        }
        else
        {
            System.out.println(leer(o.getPath()));

            Intent intent2 = new Intent(FileChooser.this, Principal.class);
            intent2.putExtra("filec", leer(o.getPath()));
            startActivity(intent2);
           // onFileClick(o);
        }
    }


    private void onFileClick(Opcion o)
    {
        Toast.makeText(this, "File Clicked: " + o.getName(), Toast.LENGTH_SHORT).show();
        System.out.println(o.getPath());
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.file_chooser, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
