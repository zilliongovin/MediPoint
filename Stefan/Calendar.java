
package com.android.notification;


import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CalendarContract;

import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * Created by Z480 on 3/30/2015.
 */
public class Calendar {

    //Beta phase, crash but work ?

   /*String[] projection =
    new String[]{
        CalendarContract.Calendars._ID,
        CalendarContract.Calendars.NAME,
        CalendarContract.Calendars.ACCOUNT_NAME,
        CalendarContract.Calendars.ACCOUNT_TYPE};
    Cursor calCursor =
    getContentResolver().
    query(CalendarContract.Calendars.CONTENT_URI,
          projection,
          CalendarContract.Calendars.VISIBLE + " = 1",
                  null,
          CalendarContract.Calendars._ID + " ASC");
            if (calCursor.moveToFirst()) {
        do {
            long id = calCursor.getLong(0);
            String displayName = calCursor.getString(1);
        } while (calCursor.moveToNext());
    }

    ContentValues values = new ContentValues();
    values.put(
    CalendarContract.Calendars.ACCOUNT_NAME,
    MY_ACCOUNT_NAME);
    values.put(
    CalendarContract.Calendars.ACCOUNT_TYPE,
    CalendarContract.ACCOUNT_TYPE_LOCAL);
    values.put(
    CalendarContract.Calendars.NAME,
            "GrokkingAndroid Calendar");
    values.put(
    CalendarContract.Calendars.CALENDAR_DISPLAY_NAME,
            "GrokkingAndroid Calendar");
    values.put(
    Calendars.CALENDAR_COLOR,
            0xffff0000);
    values.put(
    CalendarContract.Calendars.CALENDAR_ACCESS_LEVEL,
    CalendarContract.Calendars.CAL_ACCESS_OWNER);
    values.put(
    CalendarContract.Calendars.OWNER_ACCOUNT,
            "some.account@googlemail.com");
    values.put(
    CalendarContract.Calendars.CALENDAR_TIME_ZONE,
            "Europe/Berlin");
    values.put(
    CalendarContract.Calendars.SYNC_EVENTS,
            1);
    Uri.Builder builder =
            CalendarContract.Calendars.CONTENT_URI.buildUpon();
    builder.appendQueryParameter(
    CalendarContract.Calendars.ACCOUNT_NAME,
            "com.grokkingandroid");
    builder.appendQueryParameter(
    Calendars.ACCOUNT_TYPE,
    CalendarContract.ACCOUNT_TYPE_LOCAL);
    builder.appendQueryParameter(
    CalendarContract.CALLER_IS_SYNCADAPTER,
            "true");
    Uri uri =
            getContentResolver().insert(builder.build(), values);*/

    /*Calendar cal = new GregorianCalendar(2015, 3, 30);
    cal.setTimeZone(TimeZone.getTimeZone("UTC"));
    cal.set(Calendar.HOUR, 0);
    cal.set(Calendar.MINUTE, 0);
    cal.set(Calendar.SECOND, 0);
    cal.set(Calendar.MILLISECOND, 0);
    long start = cal.getTimeInMillis();
    ContentValues values = new ContentValues();
    values.put(CalendarContract.Events.DTSTART, start);
    values.put(CalendarContract.Events.DTEND, start);
    values.put(CalendarContract.Events.RRULE,   "FREQ=DAILY;COUNT=20;BYDAY=MO,TU,WE,TH,FR;WKST=MO");
    values.put(CalendarContract.Events.TITLE, "Some title");
    values.put(CalendarContract.Events.EVENT_LOCATION, "Münster");
    values.put(CalendarContract.Events.CALENDAR_ID, calId);
    values.put(CalendarContract.Events.EVENT_TIMEZONE, "Europe/Berlin");
    values.put(CalendarContract.Events.DESCRIPTION,
            "The agenda or some description of the event");
// reasonable defaults exist:
    values.put(CalendarContract.Events.ACCESS_LEVEL, CalendarContract.Events.ACCESS_PRIVATE);
    values.put(CalendarContract.Events.SELF_ATTENDEE_STATUS,
    CalendarContract.Events.STATUS_CONFIRMED);
    values.put(CalendarContract.Events.ALL_DAY, 1);
    values.put(CalendarContract.Events.ORGANIZER, "some.mail@some.address.com");
    values.put(CalendarContract.Events.GUESTS_CAN_INVITE_OTHERS, 1);
    values.put(CalendarContract.Events.GUESTS_CAN_MODIFY, 1);
    values.put(CalendarContract.Events.AVAILABILITY, CalendarContract.Events.AVAILABILITY_BUSY);
    Uri uri =
            getContentResolver().
                    insert(CalendarContract.Events.CONTENT_URI, values);
    long eventId = new Long(uri.getLastPathSegment());
// adding an attendee:
    values.clear();
    values.put(CalendarContract.Attendees.EVENT_ID, eventId);
    values.put(CalendarContract.Attendees.ATTENDEE_TYPE, CalendarContract.Attendees.TYPE_REQUIRED);
    values.put(CalendarContract.Attendees.ATTENDEE_NAME, "Douglas Adams");
    values.put(CalendarContract.Attendees.ATTENDEE_EMAIL, "d.adams@zaphod-b.com");
    getContentResolver().insert(CalendarContract.Attendees.CONTENT_URI, values);
// adding a reminder:
     values.clear();
     values.put(CalendarContract.Reminders.EVENT_ID, eventId);
     values.put(CalendarContract.Reminders.METHOD, Reminders.METHOD_ALERT);
     values.put(CalendarContract.Reminders.MINUTES, 30);
    getContentResolver().insert(CalendarContract.Reminders.CONTENT_URI, values);*/


}
