package com.djzass.medipoint;

/**
 * Created by Zillion Govin on 4/4/2015.
 */
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Deka on 30/3/2015.
 */
public class AppointmentAdapter extends ArrayAdapter<AppointmentDummy> {
    private static class ViewHolder {
        public ImageView specialtyIcon;
        public TextView appointmentService;
        public TextView appointmentStatus;
        public TextView appointmentDate;
        public TextView appointmentTime;
    }

    public AppointmentAdapter(Context context, ArrayList<AppointmentDummy> appointments) {
        super(context, R.layout.appointment_adapter, appointments);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        // Get the data item for this position
        AppointmentDummy appointment = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.appointment_adapter, parent, false);
            viewHolder = new ViewHolder();
            // Lookup view for data population
            viewHolder.specialtyIcon = (ImageView) convertView.findViewById(R.id.specialty_icon);
            viewHolder.appointmentService = (TextView) convertView.findViewById(R.id.appointment_service);
            viewHolder.appointmentStatus = (TextView) convertView.findViewById(R.id.appointment_status);
            viewHolder.appointmentDate = (TextView) convertView.findViewById(R.id.appointment_date);
            viewHolder.appointmentTime= (TextView) convertView.findViewById(R.id.appointment_time);
            convertView.setTag(viewHolder);
        } else{
            viewHolder = (ViewHolder)convertView.getTag();
        }

        // Populate the data into the template view using the data object
        viewHolder.specialtyIcon.setImageResource(getImageId(specialtyName));
        viewHolder.appointmentService.setText(appointment.getName());
        viewHolder.appointmentStatus.setText(appointment.getStatus());
        viewHolder.appointmentDate.setText(appointment.getDateString());
        viewHolder.appointmentTime.setText(appointment.getTimeString());

        // Return the completed view to render on screen
        return convertView;
    }

    private int getImageId(String specialtyName){
        if (specialtyName.equalsIgnoreCase("ENT"))
            return R.mipmap.ear;
        else if (specialtyName.equalsIgnoreCase("Dental"))
            return R.mipmap.dental;
        else if (specialtyName.equalsIgnoreCase("Women's Health Services"))
            return R.mipmap.female;
        return R.mipmap.icontp_medipoint;
    }

}
