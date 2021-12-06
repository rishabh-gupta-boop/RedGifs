package com.beetleink.redvids.Fragments.PersonFrag.Viewer;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.beetleink.redvids.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class ViewerAdapter extends ArrayAdapter {
    private String[] countryNames;
    private String[] capitalNames;
    private Integer[] imageid;
    private Activity context;


    public ViewerAdapter(Activity context, String[] countryNames, String[] capitalNames, Integer[] imageid) {
        super(context, R.layout.viewer_adapter_listitems, countryNames);
        this.context = context;
        this.countryNames = countryNames;
        this.capitalNames = capitalNames;
        this.imageid = imageid;

    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view=convertView;
        LayoutInflater inflater = context.getLayoutInflater();
        if(convertView==null)
            view = inflater.inflate(R.layout.viewer_adapter_listitems, null, true);
        TextView textViewCountry = (TextView) view.findViewById(R.id.textViewCountry);
        TextView textViewCapital = (TextView) view.findViewById(R.id.textViewCapital);
        CircleImageView imageFlag =  view.findViewById(R.id.imageViewFlag);

        textViewCountry.setText(countryNames[position]);
        textViewCapital.setText(capitalNames[position]);
        imageFlag.setImageResource(imageid[position]);
        return  view;
    }
}
