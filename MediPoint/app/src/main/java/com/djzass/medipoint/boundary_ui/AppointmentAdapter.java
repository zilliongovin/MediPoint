package com.djzass.medipoint.boundary_ui;


import android.content.Context;
import android.util.Log;
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
 * Created by Deka on 30/3/2015.
 * Appointment Adapter is a custom adapter class for displaying appointment item in the appointment
 * list.  This class extends from ArrayAdapter class and accepts {@link Appointment} objects.
 * Each of the Appointment objects that use this adapter will be displayed as individual item in
 * {@link AppointmentListFragment}.
 *
 * <p>Each item in the appointment list will include:
 * <ul>
 *     <li>Specialty Icon</li>
 *     <li>Appointment Service</li>
 *     <li>Appointment Status: Upcoming, Ongoing, Finished</li>
 *     <li>Appointment Date</li>
 *     <li>Appointment Time</li>
 * </ul>
 *</p>
 *
 * @author Deka
 * @version 1.0
 * @since 2015
 *
 * @see android.widget.ArrayAdapter
 */
public class AppointmentAdapter extends ArrayAdapter<Appointment> {
    /**
     * Constructor for ViewHolder class.  This class is used to optimise ListView in android as it makes
     * the data loads faster.
     */
    private static class ViewHolder {
        public ImageView specialtyIcon;
        public TextView appointmentService;
        public TextView appointmentStatus;
        public TextView appointmentDate;
        public TextView appointmentTime;
    }

    /**
     *
     * @param context
     * @param appointments
     * @throws SQLException
     */
    public AppointmentAdapter(Context context, ArrayList<Appointment> appointments) throws SQLException {
        super(context, R.layout.appointment_adapter, appointments);
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

        Log.d("IDno", ""+appointment.getId());
        HashMap<String,String> appointmentDetails = getAppointmentDetails(appointment.getId());

        // Populate the data into the template view using the data object

        viewHolder.specialtyIcon.setImageResource(getImageId(appointmentDetails.get("SPECIALTY_NAME")));
        viewHolder.appointmentService.setText(appointmentDetails.get("SERVICE_NAME"));
        viewHolder.appointmentStatus.setText(appointmentDetails.get("STATUS"));
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
     * @param id
     * @return
     */
    public HashMap<String,String> getAppointmentDetails(int id){
        Appointment appointment = Container.getAppointmentManager().getAppointmentByID(id, getContext());
        HashMap<String, String> appointmentDetails = new HashMap<String, String>();
        appointmentDetails.put("SPECIALTY_NAME",Container.getAppointmentManager().getSpecialtyNameByAppointment(appointment,getContext()));
        appointmentDetails.put("SERVICE_NAME",Container.getAppointmentManager().getServiceNameByAppointment(appointment, getContext()));
        appointmentDetails.put("DOCTOR_NAME",Container.getAppointmentManager().getDoctorNameByAppointment(appointment, getContext()));
        appointmentDetails.put("CLINIC_NAME",Container.getAppointmentManager().getClinicNameByAppointment(appointment, getContext()));
        appointmentDetails.put("DATE",appointment.getDateString());
        appointmentDetails.put("TIME",appointment.getTimeString());
        appointmentDetails.put("STATUS",Container.getAppointmentManager().getStatus(appointment));

        return appointmentDetails;
    }
}
