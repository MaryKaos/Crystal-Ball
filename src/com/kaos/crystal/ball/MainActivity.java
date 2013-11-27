package com.kaos.crystal.ball;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

import com.kaos.crystal.ball.ShakeDetector.OnShakeListener;

public class MainActivity extends Activity {

	// Declare variables
	public static final String TAG = MainActivity.class.getSimpleName();
	
	private CrystalBall mCrystalBall = new CrystalBall();
	private ImageView mCrystalBallImage;
	private TextView mAnswerLabel;
	private SensorManager mSensorManager;
	private Sensor mAccelerometer;
	private ShakeDetector mShakeDetector;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // Assign the Views from the layout file
        mAnswerLabel = (TextView) findViewById(R.id.textView1);
    	mCrystalBallImage = (ImageView) findViewById(R.id.imageView1);
    	
    	// Assign fields for Shake tech
    	mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
    	mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    	mShakeDetector = new ShakeDetector(new OnShakeListener() {
			public void onShake() {
				handleNewAnswer();
			}
		});
    	
    	// Create a toast object for debugging/testing
    	//Toast.makeText(this, "Woot!", Toast.LENGTH_LONG).show();
    	
    	//Toast welcomeToast = Toast.makeText(this, "Look at me up here", Toast.LENGTH_LONG);
    	//welcomeToast.setGravity(Gravity.TOP, 0, 0);
    	//welcomeToast.show();
    	
    	// Write to the log class
    	//Log.d(TAG, "We're logging from the onCreate() method!");
    }
    	
    @Override
    public void onResume() {
    	super.onResume();
    	mSensorManager.registerListener(mShakeDetector, mAccelerometer, SensorManager.SENSOR_DELAY_UI);
    }
    	
    @Override
    public void onPause() {
    	super.onPause();
    	mSensorManager.unregisterListener(mShakeDetector);
    }
    
    private void playSound() {
    	MediaPlayer player = MediaPlayer.create(this, R.raw.crystal_ball);
    	player.start();
    	
    	player.setOnCompletionListener(new OnCompletionListener() {
			public void onCompletion(MediaPlayer mp) {
				mp.release();
			}
		});
    }

	private void handleNewAnswer() {
		// Randomly pick an answer from getAnAnswer method in CrystalBall class
		String answer = mCrystalBall.getAnAnswer();
		
		// Update label with our dynamic answer
		mAnswerLabel.setText(answer);
		
		// Animate the crystal ball
		mCrystalBall.animateCrystalBall(mCrystalBallImage);
		
		// Animate the answer (fade in)
		mCrystalBall.animateAnswer(mAnswerLabel);
		
		// Play a sound
		playSound();
	}
	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
