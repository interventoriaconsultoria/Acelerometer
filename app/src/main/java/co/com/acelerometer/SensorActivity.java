package co.com.acelerometer;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Date;

public class SensorActivity extends Activity implements SensorEventListener {
    private  SensorManager mSensorManager;
    private  Sensor mAccelerometer;
    TextView textView;
    TextView textView1;
    TextView textView2;
    TextView textView3;
    TextView textView7;
    float ax,ay,az=0;
    private static final String ttf_file = "DS-DIGIT.TTF";
    private static final String path = "fonts/monospace/";
    Typeface face;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        face = Typeface.createFromAsset(getAssets(),
                path + ttf_file);

        textView = findViewById(R.id.textview);
        textView2 = findViewById(R.id.textview2);
        textView3 = findViewById(R.id.textview3);
        textView7 = findViewById(R.id.textview7);
        textView.setTextColor(Color.BLUE);
        textView2.setTextColor(Color.GREEN);
        textView3.setTextColor(Color.YELLOW);
        textView.setTypeface(face);
        textView2.setTypeface(face);
        textView3.setTypeface(face);
        textView.setTypeface(face, Typeface.BOLD);
        textView2.setTypeface(face, Typeface.BOLD);
        textView3.setTypeface(face, Typeface.BOLD);
        textView7.setTypeface(face, Typeface.BOLD);
        mSensorManager=(SensorManager) getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }


    public SensorActivity() {
        //mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        //mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    public void onSensorChanged(SensorEvent event) {

        if (event.sensor.getType()==Sensor.TYPE_ACCELEROMETER){
            ax=event.values[0];
            ay=event.values[1];
            az=event.values[2];
        }

        try {

            //textView.setText("X=" + ax + "\nY=" + ay + "\nZ=" + az + "\n\n\n\n\n" + getDateTime());
            textView2.setText("Y=" + ay );
            textView.setText("X=" + ax );
            //textView1.setText("Y=" + ay );
            textView3.setText("Z=" + az );
            textView7.setText(getDateTime());

        }catch (Exception e){};


    }



    private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }
}