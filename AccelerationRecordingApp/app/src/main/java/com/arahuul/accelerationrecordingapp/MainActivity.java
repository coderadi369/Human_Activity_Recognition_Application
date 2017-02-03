package com.arahuul.accelerationrecordingapp;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.io.File;
import java.io.FileWriter;
import java.util.Calendar;

public class MainActivity extends Activity implements SensorEventListener {
    /*Button walk;
    Button jog;
    Button run;
    Button sleep;
    Button up;
    Button down;
    Button walky;
    Button stand;*/
    Sensor accelerometer;
    SensorManager sm;
    public String filePath;
    public static String activity_type;
    public TextView sensordisplay;
    public TextView magnetdisplay;
    public TextView lineardisplay;
    private float accel[] = new float[3];
    private float magn[] = new float[3];
    private float linear[] = new float[4];
    private float[] inv = new float[16];
    private float[] earthacc = new float[4];
    private float[] relativacc = new float[4];

    public static int Insert(String filePath, String recordRow) {
        try {
            FileWriter f = new FileWriter(filePath, true);
            f.write(recordRow);
            f.flush();
            f.close();
            return 1;
        } catch (Exception e) {
            return -1;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //buttons
        /*walk=(Button)findViewById(R.id.butwalk);
        jog=(Button)findViewById(R.id.butjog);
        run=(Button)findViewById(R.id.butrun);
        sleep=(Button)findViewById(R.id.butsleep);
        up=(Button)findViewById(R.id.butup);
        down=(Button)findViewById(R.id.butdown);
        walky=(Button)findViewById(R.id.walk);
        stand=(Button)findViewById(R.id.butstand);*/
        //accelerometer
        sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        /*accelerometer=sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sm.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        // Magnetic sensor
        sm.registerListener(this,sm.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD),SensorManager.SENSOR_DELAY_NORMAL);
        //linear acceleration
        sm.registerListener(this,sm.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION),SensorManager.SENSOR_DELAY_NORMAL);*/
        accelerometer = sm.getDefaultSensor(Sensor.TYPE_GRAVITY);
        sm.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        // Magnetic sensor
        sm.registerListener(this, sm.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD), SensorManager.SENSOR_DELAY_NORMAL);
        //linear acceleration
        sm.registerListener(this, sm.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION), SensorManager.SENSOR_DELAY_NORMAL);

        sensordisplay = (TextView) findViewById(R.id.display);
        // magnetdisplay = (TextView) findViewById(R.id.magnetometer);
        //lineardisplay = (TextView) findViewById(R.id.linear);
        try {

            Log.d("Starting", "Checking up directory!!");
            File media = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "activityReadings");
            if (!media.exists()) {
                if (!media.mkdir()) {
                    Log.e("Failed", media.toString());

                } else {
                    Log.i("Direction creation", "Success");
                }
            } else {
                filePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
                filePath = filePath + "/" + "activityReadings/out.txt";
                /*int i=Insert(filePath,"4\t5\t6\n");
                Insert(filePath,"78\t354\t4564\n");*/
                /*sensordisplay.setText("aur");
                magnetdisplay.setText("Bhai");
                lineardisplay.setText("sab Badiyaan ?? ");*/
            }
        } catch (Exception e) {
            Log.e("Direction creation", e.getMessage());
        }

    }
    public void sleep1(View v)
    {
        accelerometer = sm.getDefaultSensor(Sensor.TYPE_GRAVITY);
        sm.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        // Magnetic sensor
        sm.registerListener(this, sm.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD), SensorManager.SENSOR_DELAY_NORMAL);
        //linear acceleration
        sm.registerListener(this, sm.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION), SensorManager.SENSOR_DELAY_NORMAL);
        sensordisplay.setText("Lift Up data recording...");
        int y=Insert(filePath,"Lift up data :: \n");
        activity_type="Lift up";
    }
    public void jogging(View v)
    {
        accelerometer = sm.getDefaultSensor(Sensor.TYPE_GRAVITY);
        sm.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        // Magnetic sensor
        sm.registerListener(this, sm.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD), SensorManager.SENSOR_DELAY_NORMAL);
        //linear acceleration
        sm.registerListener(this, sm.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION), SensorManager.SENSOR_DELAY_NORMAL);
        sensordisplay.setText("Lift Down recording...");
        int y=Insert(filePath,"Lift down data :: \n");
        activity_type="Lift down";
    }
    public void walk2(View v)
    {
        accelerometer = sm.getDefaultSensor(Sensor.TYPE_GRAVITY);
        sm.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        // Magnetic sensor
        sm.registerListener(this, sm.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD), SensorManager.SENSOR_DELAY_NORMAL);
        //linear acceleration
        sm.registerListener(this, sm.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION), SensorManager.SENSOR_DELAY_NORMAL);
        sensordisplay.setText("Walking data recording...");
        int y=Insert(filePath,"Walking data :: \n");
        activity_type="Walking";
    }
    public void standing(View v)
    {
        accelerometer = sm.getDefaultSensor(Sensor.TYPE_GRAVITY);
        sm.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        // Magnetic sensor
        sm.registerListener(this, sm.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD), SensorManager.SENSOR_DELAY_NORMAL);
        //linear acceleration
        sm.registerListener(this, sm.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION), SensorManager.SENSOR_DELAY_NORMAL);
        sensordisplay.setText("Standing data recording...");
        int y=Insert(filePath,"standing data :: \n");
        activity_type="Standing";
    }
    public void walk(View v)
    {
        accelerometer = sm.getDefaultSensor(Sensor.TYPE_GRAVITY);
        sm.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        // Magnetic sensor
        sm.registerListener(this, sm.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD), SensorManager.SENSOR_DELAY_NORMAL);
        //linear acceleration
        sm.registerListener(this, sm.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION), SensorManager.SENSOR_DELAY_NORMAL);
        sensordisplay.setText("Escalater up recording...");
        int y=Insert(filePath,"Escalator up data :: \n");
        activity_type="Esclater up";
    }
    public void running(View v)
    {
        accelerometer = sm.getDefaultSensor(Sensor.TYPE_GRAVITY);
        sm.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        // Magnetic sensor
        sm.registerListener(this, sm.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD), SensorManager.SENSOR_DELAY_NORMAL);
        //linear acceleration
        sm.registerListener(this, sm.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION), SensorManager.SENSOR_DELAY_NORMAL);
        sensordisplay.setText("Escalater down recording...");
        int y=Insert(filePath,"Escalater down data :: \n");
        activity_type="Escalater down";
    }
    public void upstairs(View v)
    {
        accelerometer = sm.getDefaultSensor(Sensor.TYPE_GRAVITY);
        sm.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        // Magnetic sensor
        sm.registerListener(this, sm.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD), SensorManager.SENSOR_DELAY_NORMAL);
        //linear acceleration
        sm.registerListener(this, sm.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION), SensorManager.SENSOR_DELAY_NORMAL);
        sensordisplay.setText("upstairs data recording...");
        int y=Insert(filePath,"upstairs data :: \n");
        activity_type="upstairs ";
    }
    public void downstairs(View v)
    {
        accelerometer = sm.getDefaultSensor(Sensor.TYPE_GRAVITY);
        sm.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        // Magnetic sensor
        sm.registerListener(this, sm.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD), SensorManager.SENSOR_DELAY_NORMAL);
        //linear acceleration
        sm.registerListener(this, sm.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION), SensorManager.SENSOR_DELAY_NORMAL);
        sensordisplay.setText("downstairs data recording...");
        int y=Insert(filePath,"downstairs data :: \n");
        activity_type="downstairs";
    }
    public void stoprec(View v)
    {
        accelerometer = sm.getDefaultSensor(Sensor.TYPE_GRAVITY);
        sm.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        // Magnetic sensor
        sm.registerListener(this, sm.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD), SensorManager.SENSOR_DELAY_NORMAL);
        //linear acceleration
        sm.registerListener(this, sm.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION), SensorManager.SENSOR_DELAY_NORMAL);
        sm.unregisterListener(this);
        sensordisplay.setText("Stopped!!");
    }


    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();
        sm.unregisterListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    @Override
    public void onSensorChanged(SensorEvent event) {
        int t = event.sensor.getType();


        String str = null;

        if (t == Sensor.TYPE_GRAVITY) {
            str = "Accelerometer";
            //sensordisplay.setText("" + str +"\n"+event.values[0]+"\t"+event.values[1]+"\t"+event.values[2]);
            accel[0] = event.values[0];
            accel[1] = event.values[1];
            accel[2] = event.values[2];
        }
        if (t == Sensor.TYPE_LINEAR_ACCELERATION) {
            str = "Linear Acceleration";
            //magnetdisplay.setText("" + str + "\n" + event.values[0]+"\t"+event.values[1]+"\t"+event.values[2]);
            linear[0] = event.values[0];
            linear[1] = event.values[1];
            linear[2] = event.values[2];
            linear[3] = 0;
        }
        if (t == Sensor.TYPE_MAGNETIC_FIELD) {
            str = "Magnetic Sensor";
            //lineardisplay.setText("" + str +"\n"+event.values[0]+"\t"+event.values[1]+"\t"+event.values[2]);
            magn[0] = (magn[0] * 1 + event.values[0]) * 0.5f;
            magn[1] = (magn[1] * 1 + event.values[1]) * 0.5f;
            magn[2] = (magn[2] * 1 + event.values[2]) * 0.5f;

        }
        if ((t == Sensor.TYPE_MAGNETIC_FIELD) || (t == Sensor.TYPE_GRAVITY) || (t == Sensor.TYPE_LINEAR_ACCELERATION)) {
            float rotationMatrix[] = new float[16];
            SensorManager.getRotationMatrix(rotationMatrix, null, accel, magn);
            SensorManager.remapCoordinateSystem(
                    rotationMatrix,
                    SensorManager.AXIS_Y,
                    SensorManager.AXIS_MINUS_X,
                    rotationMatrix);
            android.opengl.Matrix.invertM(inv, 0, rotationMatrix, 0);
            android.opengl.Matrix.multiplyMV(earthacc, 0, inv, 0, linear, 0);
            //adithya.setText(earthacc[0]+" "+earthacc[1]+" "+earthacc[2]);

            Calendar c = Calendar.getInstance();
            int seconds = c.get(Calendar.SECOND);
            int minutes=c.get(Calendar.MINUTE);
            int hours=c.get(Calendar.HOUR_OF_DAY);

            int i = Insert(filePath,"\t"+hours+":"+minutes+":"+seconds+"\t"+ accel[0] + "\t" + accel[1] + "\t" + accel[2] + "\t"
                    + magn[0] + "\t" + magn[1] + "\t" + magn[2] + "\t"
                    + linear[0] + "\t" + linear[1] + "\t" + linear[2] + "\t"+earthacc[0]+"\t"+earthacc[1]+"\t"+earthacc[2]+"\n");
        }


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}