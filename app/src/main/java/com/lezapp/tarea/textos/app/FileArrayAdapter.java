package com.lezapp.tarea.textos.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Leslie on 14/08/2014.
 */
public class FileArrayAdapter extends ArrayAdapter<Opcion> {

    private Context c;
    private int id;
    private List<Opcion> items;

    public FileArrayAdapter(Context context, int textViewResourceId,
                            List<Opcion> objects) {
        super(context, textViewResourceId, objects);
        c = context;
        id = textViewResourceId;
        items = objects;
    }

    public Opcion getItem(int i)
    {
        return items.get(i);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater vi = (LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(id, null);
        }
        final Opcion o = items.get(position);
        if (o != null) {
            TextView t1 = (TextView) v.findViewById(R.id.textView01);
            TextView t2 = (TextView) v.findViewById(R.id.textView02);

            if(t1!=null)
                t1.setText(o.getName());
            if(t2!=null)
                t2.setText(o.getData());

        }
        return v;
    }
}
