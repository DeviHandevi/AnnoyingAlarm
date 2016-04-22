package proif.annoyingalarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by Devi on 4/18/2016.
 */
public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Alarm received!", Toast.LENGTH_LONG).show();
        Intent annoyingIntent = new Intent(context, ActivityAnnoying.class);
        annoyingIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(annoyingIntent);
    }
}