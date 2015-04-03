package com.djzass.medipoint;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.djzass.medipoint.entity.Timeframe;
import com.djzass.medipoint.logic_database.DbDAO;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Deka on 2/4/2015.
 */
public class AppointmentCursorAdapter extends CursorAdapter {
    private LayoutInflater mInflater;
    DbDAO dbHandler;
    private Context context;
    private static class ViewHolder {
        public ImageView specialtyIcon;
        public TextView appointmentService;
        public TextView appointmentStatus;
        public TextView appointmentDate;
        public TextView appointmentTime;
    }

    public AppointmentCursorAdapter(Context context, Cursor c, int flags) throws SQLException {
        super(context, c, flags);
        this.context = context;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        dbHandler = new DbDAO(context);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        ViewHolder viewHolder;
        // Get the data item for this position
        //Appointment appointment = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (view == null) {
            viewHolder = new ViewHolder();
            // Lookup view for data population
            viewHolder.specialtyIcon = (ImageView) view.findViewById(R.id.specialty_icon);
            viewHolder.appointmentService = (TextView) view.findViewById(R.id.appointment_service);
            viewHolder.appointmentStatus = (TextView) view.findViewById(R.id.appointment_status);
            viewHolder.appointmentDate = (TextView) view.findViewById(R.id.appointment_date);
            viewHolder.appointmentTime= (TextView) view.findViewById(R.id.appointment_time);
            view.setTag(viewHolder);
        } else{
            viewHolder = (ViewHolder)view.getTag();
        }

        // Populate the data into the template view using the data object
        /*viewHolder.specialtyIcon.setImageResource(R.mipmap.ic_launcher);
        viewHolder.appointmentService.setText(appointment.getName());
        viewHolder.appointmentStatus.setText(appointment.getStatus());
        viewHolder.appointmentDate.setText(appointment.getDateString());
        viewHolder.appointmentTime.setText(appointment.getTimeString());*/

        // Populate the data into the template view using cursor
        /*viewHolder.specialtyIcon.setImageResource(R.mipmap.ic_launcher);
        viewHolder.appointmentService.setText(appointment.getName());
        viewHolder.appointmentStatus.setText(appointment.getStatus());
        viewHolder.appointmentDate.setText(appointment.getDateString());
        viewHolder.appointmentTime.setText(appointment.getTimeString());*/
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        // Return the completed view to render on screen
        return mInflater.inflate(R.layout.appointment_adapter, parent, false);
    }

    public int getSpecialtyImage(int id) throws SQLException {
        // TodoDatabaseHandler is a SQLiteOpenHelper class connecting to SQLite
        DbDAO dbHandler = new DbDAO(this.context);

        // Query for items from the database and get a cursor back
        String selectQuery = "SELECT  * FROM " + DbContract.SpecialtyEntry.TABLE_NAME + " WHERE "
                + DbContract.SpecialtyEntry.COLUMN_NAME_SPECIALTY_ID + " = " + id;

        Cursor itemCursor = dbHandler.database.rawQuery(selectQuery, null);
        /*
        if (s.getName().equalsIgnoreCase("ENT")){
            return //id of ENT;
        }
        else if (s.getName().equalsIgnoreCase("ENT"){
            return //id of Women Health Services;
        }
        else if (s.getName().equalsIgnoreCase("ENT"){
            return
        }*/
        return R.mipmap.ic_launcher;
    }

    public String getAppointmentService(int id) throws SQLException {
        // TodoDatabaseHandler is a SQLiteOpenHelper class connecting to SQLite

        // Query for items from the database and get a cursor back
        String selectQuery = "SELECT  * FROM " + DbContract.ServiceEntry.TABLE_NAME + " WHERE "
                + DbContract.ServiceEntry.COLUMN_NAME_SERVICE_ID + " = " + id;

        Cursor c = dbHandler.database.rawQuery(selectQuery, null);

        return c.getString(c.getColumnIndex(DbContract.ServiceEntry.COLUMN_NAME_SERVICE_NAME));
    }

    public String getAppointmentStatus(Calendar cal){
        String status="None";
        //if less than the appointment time:
        /*
        if (cal > Calendar.getInstance()){
            return "Ongoing";
        else{
            return "Done";
        }*/

        return status;
    }

    public String getAppointmentDate(Calendar cal){
        SimpleDateFormat sdfDate = new SimpleDateFormat("EEEE, dd MMMM yyyy");
        return sdfDate.format(cal.getTime());
    }

    public String getAppointmentTime(Timeframe timeFrame){
        return timeFrame.toString();
    }

}