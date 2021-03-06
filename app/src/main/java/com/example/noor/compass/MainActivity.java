package com.example.noor.compass;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    SensorManager sensorManager;
    Sensor sensor;
    ImageView ivCompass;
    float currentDegree=0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivCompass=findViewById(R.id.ivCompass);

        sensorManager= (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor=sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        sensorManager.registerListener(this,sensor,SensorManager.SENSOR_DELAY_GAME);

    }



    @Override
    public void onSensorChanged(SensorEvent event) {
        float degree = Math.round(event.values[0]);
        RotateAnimation angle = new RotateAnimation(
                currentDegree,
                -degree,
                Animation.RELATIVE_TO_SELF,
                0.5f,
                Animation.RELATIVE_TO_SELF,
                0.5f);
        angle.setDuration(100);
        angle.setFillAfter(true);
        ivCompass.startAnimation(angle);
        currentDegree = -degree;


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
