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

        vibrator = (Vibrator)getSystemService(getApplicationContext().VIBRATOR_SERVICE);
        vibrator.vibrate(pattern,0);
        vibrator.cancel();

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
                } else if(clicked<goal) {
                    infoTextView.setText(R.string.clicked_less);
                    goal = (int)(Math.random()*RANGE_CLICK)+MINIMUM_CLICK;
                    clicked = 0;
                } else {
                    infoTextView.setText(R.string.clicked_more);
                    goal = (int)(Math.random()*RANGE_CLICK)+MINIMUM_CLICK;
                    clicked = 0;
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_annoying, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
