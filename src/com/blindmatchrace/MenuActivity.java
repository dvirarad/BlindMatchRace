package com.blindmatchrace;

<<<<<<< HEAD
import java.util.Locale;

=======
>>>>>>> 34f45fc19286b905b48432e132628d67a2c720e8
import com.blindmatchrace.classes.C;
import com.blindmatchrace.classes.SaveKmlTask;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
<<<<<<< HEAD
import android.speech.tts.TextToSpeech;
=======
>>>>>>> 34f45fc19286b905b48432e132628d67a2c720e8
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

/**
 * Menu activity. Shows the menu screen.
 *
 */
public class MenuActivity extends Activity implements OnClickListener {

	// Application variables.
	private String user = "", pass = "", event = "", fullUserName = "";

	// Views.
	private Button bMap, bKml1, bKml2, bLogout, bExit;

<<<<<<< HEAD
	
	private TextToSpeech tts;
=======
>>>>>>> 34f45fc19286b905b48432e132628d67a2c720e8
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		// Disables lock-screen and keeps screen on.
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

		initialize();
	}

	/**
	 * Initialize components.
	 */
	private void initialize() {
		// The user name, password and event number connected to the application.
		user = getIntent().getStringExtra(C.USER_NAME);
		pass = getIntent().getStringExtra(C.USER_PASS);
		event = getIntent().getStringExtra(C.EVENT_NUM);
		fullUserName = user + "_" + pass + "_" + event;

<<<<<<< HEAD
		//DVIR --> text to speck massage
		tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {

			@Override
			public void onInit(int arg0) {
				if(arg0 == TextToSpeech.SUCCESS) 
				{
					tts.setLanguage(Locale.UK);
					tts.speak("Hello  " + user.substring(6) + "  have fun",TextToSpeech.QUEUE_FLUSH,null);
				}
			}
		});
		
		
=======
>>>>>>> 34f45fc19286b905b48432e132628d67a2c720e8
		// Initializing Buttons.
		bMap = (Button) findViewById(R.id.bMap);
		bKml1 = (Button) findViewById(R.id.bKml1);
		bKml2 = (Button) findViewById(R.id.bKml2);
		bLogout = (Button) findViewById(R.id.bLogout);
		bExit = (Button) findViewById(R.id.bExit);

		bMap.setOnClickListener(this);
		bKml1.setOnClickListener(this);
		bKml2.setOnClickListener(this);
		bLogout.setOnClickListener(this);
		bExit.setOnClickListener(this);
	}

	@Override
	public void onBackPressed() {
		/*super.onBackPressed();
		finish();*/
		new AlertDialog.Builder(this)
		.setTitle("Really Exit?")
		.setMessage("Are you sure you want to exit?")
		.setNegativeButton(android.R.string.no, null)
		.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface arg0, int arg1) {
				MenuActivity.super.onBackPressed();
				finish();
			}
		}).create().show();
	}

	@Override
	public void onClick(View v) {
		Intent intent;
		switch (v.getId()) {
		case R.id.bMap:
			intent = new Intent(MenuActivity.this, MainActivity.class);
			intent.putExtra(C.USER_NAME, user);
			intent.putExtra(C.USER_PASS, pass);
			intent.putExtra(C.EVENT_NUM, event);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			break;
		case R.id.bKml1:
			bKml1.setEnabled(false);
			Toast.makeText(this, "Please wait few seconds...", Toast.LENGTH_SHORT).show();

			// AsyncTask for saving the KML file on SD memory (with time stamp).
			SaveKmlTask saveKml1 = new SaveKmlTask(this, "SaveKmlTask", fullUserName, 1);
			saveKml1.execute(C.URL_HISTORY_TABLE, C.URL_CLIENTS_TABLE);
			break;
		case R.id.bKml2:
			bKml2.setEnabled(false);
			Toast.makeText(this, "Please wait few seconds...", Toast.LENGTH_SHORT).show();

			// AsyncTask for saving the KML file on SD memory (only path).
			SaveKmlTask saveKml2 = new SaveKmlTask(this, "SaveKmlTask", fullUserName, 2);
			saveKml2.execute(C.URL_HISTORY_TABLE, C.URL_CLIENTS_TABLE);
			break;
		case R.id.bLogout:
			// Updates the SharedPreferences.
			SharedPreferences sp = getSharedPreferences(C.PREFS_USER, MODE_PRIVATE);
			SharedPreferences.Editor spEdit = sp.edit();
			spEdit.putString(C.PREFS_FULL_USER_NAME, "Anonymous");
			spEdit.commit();
			intent = new Intent(MenuActivity.this, LoginActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			finish();
			break;
		case R.id.bExit:
			//->DVIR alart dialog check if you wnat to exit the program
			  final AlertDialog.Builder builder = new AlertDialog.Builder(this);
			    builder.setMessage("You exit the APP \n Are you sure?")
			           .setCancelable(false)
			           .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			               public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
			                //   startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
			            	   finish();
			               }
			           })
			           .setNegativeButton("No", new DialogInterface.OnClickListener() {
			               public void onClick(final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
			                    dialog.cancel();
			               }
			           });
			    final AlertDialog alert = builder.create();
			    alert.show();
		
			break;
		}
	}
}
