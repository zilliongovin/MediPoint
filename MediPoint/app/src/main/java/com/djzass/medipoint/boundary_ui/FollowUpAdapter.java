package com.djzass.medipoint.boundary_ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.djzass.medipoint.R;
import com.djzass.medipoint.entity.Appointment;
import com.djzass.medipoint.logic_manager.Container;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Deka on 12/4/2015.
 *
 * @author Deka
 * @version 1.0
 * @since 2015
 *
 * @see android.widget.ArrayAdapter
 */
public class FollowUpAdapter extends ArrayAdapter<Appointment> {

    /**
     *
     */
    private static class ViewHolder {
        public ImageView specialtyIcon;
        public TextView appointmentService;
        public TextView appointmentDate;
        public TextView appointmentTime;
    }

    /**
     *
     * @param context
     * @param appointments
     * @throws SQLException
     */
    public FollowUpAdapter(Context context, ArrayList<Appointment> appointments) throws SQLException {
        super(context, R.layout.followup_adapter, appointments);
    }

    /**
     *
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        // Get the data item for this position
        Appointment appointment = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.followup_adapter, parent, false);
            viewHolder = new ViewHolder();
            // Lookup view for data population
            viewHolder.specialtyIcon = (ImageView) convertView.findViewById(R.id.followup_icon);
            viewHolder.appointmentService = (TextView) convertView.findViewById(R.id.followup_service);
            viewHolder.appointmentDate = (TextView) convertView.findViewById(R.id.followup_date);
            viewHolder.appointmentTime= (TextView) convertView.findViewById(R.id.followup_time);
            convertView.setTag(viewHolder);
        } else{
            viewHolder = (ViewHolder)convertView.getTag();
        }

        HashMap<String,String> appointmentDetails = getAppointmentDetails(appointment);

        // Populate the data into the template view using the data object
        viewHolder.specialtyIcon.setImageResource(getImageId(appointmentDetails.get("SPECIALTY_NAME")));
        viewHolder.appointmentService.setText(appointmentDetails.get("SERVICE_NAME"));
        viewHolder.appointmentDate.setText(appointmentDetails.get("DATE"));
        viewHolder.appointmentTime.setText(appointmentDetails.get("TIME"));

        // Return the completed view to render on screen
        return convertView;
    }

    /**
     *
     * @param specialtyName
     * @return
     */
    public int getImageId(String specialtyName){
        if (specialtyName.equalsIgnoreCase("ENT"))
            return R.mipmap.ear;
        else if (specialtyName.equalsIgnoreCase("Dental Services"))
            return R.mipmap.dental;
        else if (specialtyName.equalsIgnoreCase("Women's Health"))
            return R.mipmap.female;
        return R.mipmap.icontp_medipoint;
    }

    /**
     *
     * @param appointment
     * @return
     */
    public HashMap<String,String> getAppointmentDetails(Appointment appointment){
        HashMap<String, String> appointmentDetails = new HashMap<String, String>();
        appointmentDetails.put("SPECIALTY_NAME",Container.getAppointmentManager().getSpecialtyNameByAppointment(appointment,getContext()));
        appointmentDetails.put("SERVICE_NAME",Container.getAppointmentManager().getServiceNameByAppointment(appointment, getContext()));
        appointmentDetails.put("DOCTOR_NAME",Container.getAppointmentManager().getDoctorNameByAppointment(appointment, getContext()));
        appointmentDetails.put("CLINIC_NAME",Container.getAppointmentManager().getClinicNameByAppointment(appointment, getContext()));
        appointmentDetails.put("DATE",appointment.getDateString());
        appointmentDetails.put("TIME",appointment.getTimeString());

        return appointmentDetails;
    }
}


