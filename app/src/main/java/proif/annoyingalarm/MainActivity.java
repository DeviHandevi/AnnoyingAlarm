package proif.annoyingalarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {

    protected TimePicker timePicker;
    protected Button buttonAnnoyMe;
    protected TextView textViewInformation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timePicker = (TimePicker)findViewById(R.id.timePicker);
        buttonAnnoyMe = (Button)findViewById(R.id.buttonAnnoyMe);
        textViewInformation = (TextView)findViewById(R.id.textViewInformation);

        Calendar calendarNow = Calendar.getInstance(TimeZone.getDefault());
        timePicker.setCurrentHour(calendarNow.get(Calendar.HOUR_OF_DAY));
        timePicker.setCurrentMinute(calendarNow.get(Calendar.MINUTE));
        timePicker.setIs24HourView(true);

        buttonAnnoyMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            int hour = timePicker.getCurrentHour();
            int minute = timePicker.getCurrentMinute();
            textViewInformation.setText(String.format("Will be annoyed at %s:%s!",
                    String.format("%2d",hour).replaceAll(" ","0"),
                    String.format("%2d",minute).replaceAll(" ","0")));

            Calendar calendarNow = Calendar.getInstance(TimeZone.getDefault());
            Calendar calendarAlarm = (Calendar) calendarNow.clone();
            calendarAlarm.set(Calendar.HOUR_OF_DAY, hour);
            calendarAlarm.set(Calendar.MINUTE, minute);
            calendarAlarm.set(Calendar.SECOND, 0);
            calendarAlarm.set(Calendar.MILLISECOND, 0);

            if(calendarAlarm.compareTo(calendarNow) <= 0){
                calendarAlarm.add(Calendar.DATE, 1);
            }

            Intent intent = new Intent(getBaseContext(), AlarmReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(getBaseContext(), 0, intent, 0);
            AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
            alarmManager.set(AlarmManager.RTC_WAKEUP, calendarAlarm.getTimeInMillis(), pendingIntent);
            }
        });
    }
}
