package com.sumo.traffic;

import android.app.AlarmManager;
import android.app.DialogFragment;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.sumo.traffic.AlarmCodes.AlarmPickerFragment;
import com.sumo.traffic.AlarmCodes.AlarmReceiver;
import com.sumo.traffic.model.ApplicationConstants;

import java.util.Calendar;
import java.util.HashMap;

public class poppers extends AppCompatActivity {
    static  EditText destinationName;
    static TextView distance,duration, timetoStay,mins;
    private String array_spinner[];
    private Spinner s;
    static  EditText reminders;
    private  int currentMarkerIndex = 0;
    private int alarmIndex = -1;
static int posit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poppers);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            currentMarkerIndex = extras.getInt("currentMarker");
            alarmIndex = extras.getInt("alarm");
        }


        destinationName = (EditText) findViewById(R.id.editText);
        reminders = (EditText) findViewById(R.id.editText2);
        timetoStay = (TextView) findViewById(R.id.hour_picked);
        distance = (TextView) findViewById(R.id.d1);
        duration = (TextView) findViewById(R.id.t1);
        mins =(TextView) findViewById(R.id.minutes_picked);

        //set current destination attributes

        try {
            MarkerOptions options = (MarkerOptions)traffic.mList.get(currentMarkerIndex - 1);
            destinationName.setText(options.getTitle());
            distance.setText(traffic.distances.get(currentMarkerIndex - 1).toString() + " m");
            duration.setText(traffic.durations.get(currentMarkerIndex - 1));
            timetoStay.setText(traffic.timestoStay.get(currentMarkerIndex - 1).toString());
            mins.setText(traffic.mins.get(currentMarkerIndex - 1).toString());
            reminders.setText(traffic.reminders.get(currentMarkerIndex - 1).toString());
        }
        catch (Exception e) {
            e.printStackTrace();
        }


 /*       array_spinner=new String[9];
        array_spinner[0]="1 Hour";
        array_spinner[1]="2 Hours";
        array_spinner[2]="3 Hours";
        array_spinner[3]="4 Hours";
        array_spinner[4]="5 Hours";
        array_spinner[5]="6 Hours";
        array_spinner[6]="7 Hours";
        array_spinner[7]="8 Hours";
        array_spinner[8]="9 Hours";

        ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, array_spinner);
        s.setAdapter(adapter);

        try {
            s.setSelection(Integer.parseInt(timetoStay.getText().toString().substring(0, 1)) - 1);
        }
        catch(Exception e) {
            s.setSelection(0);
        }*/
        //s.performClick();

/*

        s.setOnItemSelectedListener (new AdapterView.OnItemSelectedListener() {
                                         @Override
                                         public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                             timetoStay.setText(s.getSelectedItem().toString());
                                         }
                                         @Override
                                         public void onNothingSelected(AdapterView<?> adapterView) {

                                         }
                                     }
        );
*/


    }

    public void onTimePickClicked(View view){
        DialogFragment fragment = new AlarmPickerFragment();
        fragment.show(getFragmentManager(),"Time picker");
    }


    public void saveDestination(View view) {
        int hourPicked = AlarmPickerFragment.hourPicked;
        int minutePicked = AlarmPickerFragment.minutePicked;
        EditText destinationEdit = (EditText) findViewById(R.id.editText);
        EditText reminderEdit = (EditText) findViewById(R.id.editText2);
        /*
        you should set a default destination and reminder for the notification and database if the
        user doesn't write anything. TODO if you don't do it, app is probably going to crash. Example
         */
        String destination = destinationEdit.getText().toString();
        if(destination.equals("")) destination = "Notification"; //this will be the title of the notification
        String reminder = reminderEdit.getText().toString();
        if(reminder.equals("")) reminder = "You got a new notification"; //notification's content
        //Intent service = new Intent(this, AlarmService.class);
        //we set the action to create the notification in AlarmService, it's just a final String defined in MainActivity
//        service.setAction(ApplicationConstants.CREATENOTIFICATION);
//        service.putExtra(ApplicationConstants.HOUR, hourPicked);
//        service.putExtra(ApplicationConstants.MINUTE,minutePicked);
//        service.putExtra(ApplicationConstants.REMINDER,reminder);
//        service.putExtra(ApplicationConstants.DESTINATION,destination);
//        startService(service);

        /*
        we'll use the int immediately after the last value in the array list as the notification id
        unless we got the alarmIndex, meaning that the alarm is being edited
         */
        int notificationId = (alarmIndex >= 0) ? alarmIndex : traffic.alarmClocks.size();
        HashMap<String, String> alarm = new HashMap<>();
        alarm.put(ApplicationConstants._ID, String.valueOf(notificationId));
        alarm.put(ApplicationConstants.HOUR, String.valueOf(hourPicked));
        alarm.put(ApplicationConstants.MINUTE, String.valueOf(minutePicked));
        alarm.put(ApplicationConstants.REMINDER, reminder);
        alarm.put(ApplicationConstants.DESTINATION, destination);
        setAlarm(hourPicked, minutePicked, notificationId, reminder, destination); // we set the alarm
        if (posit-1 >= 0 && posit-1 < traffic.alarmClocks.size()) {
            // An entry exists; hey, let's remove it
            Toast.makeText(this, "Meron na", Toast.LENGTH_SHORT).show();
        }
        else {
            traffic.alarmClocks.add(alarm);
            Toast.makeText(this,R.string.notification_set,Toast.LENGTH_SHORT).show();
        }



        //==========================================================================//

        Marker marker = (Marker)traffic.markers.get(currentMarkerIndex - 1);
        marker.setTitle(destinationName.getText().toString());
        MarkerOptions options = (MarkerOptions)traffic.mList.get(currentMarkerIndex - 1);
        options.title(destinationName.getText().toString());

        traffic.reminders.set(currentMarkerIndex - 1, reminders.getText().toString());
        traffic.timestoStay.set(currentMarkerIndex - 1, timetoStay.getText().toString());
        traffic.mins.set(currentMarkerIndex - 1, mins.getText().toString());
        traffic.durations.set(currentMarkerIndex - 1, duration .getText().toString());
        traffic.distances.set(currentMarkerIndex - 1, distance.getText().toString());

        Intent intent = new Intent();
        intent.putExtra("currentMarkerIndex", currentMarkerIndex);
        setResult(109, intent);
        //close the destination activity
        finish();
    }

    private void setAlarm(int hour, int minute, int notificationId,String reminder,String destination){
        Intent i;
        PendingIntent pi;
        Calendar calendar = Calendar.getInstance();
        int currentHour = calendar.get(Calendar.HOUR_OF_DAY);
        int monthDay = calendar.get(Calendar.DAY_OF_YEAR);
        i = new Intent(this,AlarmReceiver.class);
        i.putExtra(ApplicationConstants._ID, notificationId);
        i.putExtra(ApplicationConstants.REMINDER,reminder);
        i.putExtra(ApplicationConstants.DESTINATION,destination);
        pi = PendingIntent.getBroadcast(this,notificationId,i,PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        if (currentHour > hour) {
            calendar.set(Calendar.DAY_OF_YEAR, (monthDay + 1));
            calendar.set(Calendar.HOUR_OF_DAY, hour);
            calendar.set(Calendar.MINUTE, minute);
            calendar.set(Calendar.SECOND, 0);
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pi);
        } else if (currentHour <= hour) {
            calendar.set(Calendar.HOUR_OF_DAY, hour);
            calendar.set(Calendar.MINUTE, minute);
            calendar.set(Calendar.SECOND, 0);
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pi);
        }
    }


}
