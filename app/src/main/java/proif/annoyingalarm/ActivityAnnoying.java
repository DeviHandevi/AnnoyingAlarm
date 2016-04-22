package proif.annoyingalarm;

import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class ActivityAnnoying extends AppCompatActivity {

    private AppCompatActivity theActivity;

    private Vibrator vibrator;
    private long[] pattern = {0,1000,500};
    //start without a delay, vibrate for 1 sec, sleep for 0.5 sec

    protected Button buttonClick;
    protected Button buttonDone;
    protected EditText textTimes;
    protected TextView infoTextView;

    protected static final int MINIMUM_CLICK = 5;
    protected static final int RANGE_CLICK = 5;
    protected int goal;
    protected int clicked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annoying);
        theActivity = this;

        Toast.makeText(getApplicationContext(), "Alarm received!", Toast.LENGTH_LONG).show();

        vibrator = (Vibrator)getSystemService(getApplicationContext().VIBRATOR_SERVICE);
        vibrator.vibrate(pattern,0);

        buttonClick = (Button) findViewById(R.id.buttonClick);
        buttonDone = (Button)findViewById(R.id.buttonDone);
        textTimes = (EditText)findViewById(R.id.editTextTimes);
        infoTextView = (TextView)findViewById(R.id.infoTextView);

        goal = (int)(Math.random()*RANGE_CLICK)+MINIMUM_CLICK;
        clicked = 0;
        textTimes.setText(Integer.toString(goal));

        buttonClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicked++;
            }
        });

        buttonDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(clicked==goal) {
                    Toast.makeText(getApplicationContext(), R.string.clicked_success, Toast.LENGTH_SHORT).show();
                    vibrator.cancel();
                    theActivity.finish();
                } else {
                    if(clicked<goal) {
                        infoTextView.setText(R.string.clicked_less);
                    } else {
                        infoTextView.setText(R.string.clicked_more);
                    }
                    goal = (int)(Math.random()*RANGE_CLICK)+MINIMUM_CLICK;
                    clicked = 0;
                    textTimes.setText(Integer.toString(goal));
                }
            }
        });
    }
}
