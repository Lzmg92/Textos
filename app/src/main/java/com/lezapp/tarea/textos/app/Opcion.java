package com.lezapp.tarea.textos.app;

/**
 * Created by Leslie on 14/08/2014.
 */
public class Opcion implements Comparable<Opcion>{
    private String name;
    private String data;
    private String path;

    public Opcion(String n,String d,String p)
    {
        name = n;
        data = d;
        path = p;
    }
    public String getName()
    {
        return name;
    }
    public String getData()
    {
        return data;
    }
    public String getPath()
    {
        return path;
    }

    public int compareTo(Opcion o) {
        if(this.name != null)
            return this.name.toLowerCase().compareTo(o.getName().toLowerCase());
        else
            throw new IllegalArgumentException();
    }
}
