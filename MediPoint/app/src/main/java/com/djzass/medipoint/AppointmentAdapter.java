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

import com.djzass.medipoint.entity.Appointment;
import com.djzass.medipoint.logic_manager.AppointmentManager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Deka on 30/3/2015.
 */
public class AppointmentAdapter extends ArrayAdapter<Appointment> {
    AppointmentManager appointmentManager;
    private static class ViewHolder {
        public ImageView specialtyIcon;
        public TextView appointmentService;
        public TextView appointmentStatus;
        public TextView appointmentDate;
        public TextView appointmentTime;
    }

    public AppointmentAdapter(Context context, ArrayList<Appointment> appointments) throws SQLException {
        super(context, R.layout.appointment_adapter, appointments);
        appointmentManager = new AppointmentManager(context);
    }

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

    public int getImageId(String specialtyName){
        if (specialtyName.equalsIgnoreCase("ENT"))
            return R.mipmap.ear;
        else if (specialtyName.equalsIgnoreCase("Dental"))
            return R.mipmap.dental;
        else if (specialtyName.equalsIgnoreCase("Women's Health Services"))
            return R.mipmap.female;
        return R.mipmap.icontp_medipoint;
    }

    public HashMap<String,String> getAppointmentDetails(int id){
        Appointment appointment = appointmentManager.getAppointmentByID(id);

        String specialtyName = appointmentManager.getSpecialtyNameByAppointment(appointment);
        String serviceName = appointmentManager.getServiceNameByAppointment(appointment);
        String doctorName = appointmentManager.getDoctorNameByAppointment(appointment);
        String clinicName = appointmentManager.getClinicNameByAppointment(appointment);
        String status = appointmentManager.getStatus(appointment);

        HashMap<String,String> appointmentDetails = new HashMap<String, String>();
        appointmentDetails.put("SPECIALTY_NAME",specialtyName);
        appointmentDetails.put("SERVICE_NAME",serviceName);
        appointmentDetails.put("DOCTOR_NAME",doctorName);
        appointmentDetails.put("CLINIC_NAME",clinicName);
        appointmentDetails.put("DATE",appointment.getDateString());
        appointmentDetails.put("TIME",appointment.getTimeString());
        appointmentDetails.put("STATUS",status);

        return appointmentDetails;
    }



}
