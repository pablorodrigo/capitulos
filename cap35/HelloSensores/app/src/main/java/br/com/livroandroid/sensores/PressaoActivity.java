package br.com.livroandroid.sensores;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Pressure in hPa (pressao do ambiente do ar)
 * 
 * Medida em hPa or mbar
 * 
 * @author Ricardo Lecheta
 * 
 */
public class PressaoActivity extends ActionBarActivity implements
		SensorEventListener {

	private static final int TIPO_SENSOR = Sensor.TYPE_PRESSURE;
	private SensorManager sensorManager;
	private Sensor sensor;
	private SeekBar progress;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sensor_seekbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		
		progress = (SeekBar) findViewById(R.id.progress);

		sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

		if (sensorManager.getDefaultSensor(TIPO_SENSOR) != null) {
			sensor = sensorManager.getDefaultSensor(TIPO_SENSOR);
			
			// Define o valor maximo no ProgressBar
			float max = sensor.getMaximumRange();
			progress.setMax((int) max);
			
			Toast.makeText(this, "Sensor TYPE_PRESSURE max " + max, Toast.LENGTH_SHORT).show();
		} else {
			Toast.makeText(this, "Sensor TYPE_PRESSURE não disponível", Toast.LENGTH_SHORT).show();
			
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		if (sensor != null) {
			sensorManager.registerListener(this, sensor,
					SensorManager.SENSOR_DELAY_NORMAL);
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		sensorManager.unregisterListener(this);
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// Mudou o status de precisão do sensor
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		float valor = event.values[0];

		((TextView) findViewById(R.id.tValor)).setText("Pressao: " + valor);
		
		progress.setProgress((int)valor);
	}
}
