package com.blindmatchrace;

import java.io.IOException;
<<<<<<< HEAD
import java.util.Locale;
=======
>>>>>>> 34f45fc19286b905b48432e132628d67a2c720e8

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

<<<<<<< HEAD
=======
import com.blindmatchrace.classes.C;
import com.blindmatchrace.classes.SendDataHThread;
import com.blindmatchrace.modules.JsonReader;

>>>>>>> 34f45fc19286b905b48432e132628d67a2c720e8
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Process;
<<<<<<< HEAD
import android.speech.tts.TextToSpeech;
=======
>>>>>>> 34f45fc19286b905b48432e132628d67a2c720e8
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

<<<<<<< HEAD
import com.blindmatchrace.classes.C;
import com.blindmatchrace.classes.SendDataHThread;
import com.blindmatchrace.modules.JsonReader;

=======
>>>>>>> 34f45fc19286b905b48432e132628d67a2c720e8
/**
 * Login activity. Allows the user to log in or register to DB.
 *
 */
public class LoginActivity extends Activity {

	/**
	 * Keep track of the login task to ensure we can cancel it if requested.
	 */
	private UserLoginTask mAuthTask = null;

	// Indicates requested login.
	private boolean adminRequest = false;
	private boolean registerRequest = false;

	// SharedPreferences used for loading the latest user.
	private SharedPreferences sp;

	// Values for user, password and event at the time of the login attempt.
	private String mUser;
	private String mPassword;
	private String mEvent;

	// UI references.
	private EditText etUser;
	private EditText etPass;
	private EditText etEvent;
	private View svLoginForm;
	private View llLoginStatus;
	private TextView tvLoginStatusMessage;

<<<<<<< HEAD
	private  TextToSpeech tts;
=======
>>>>>>> 34f45fc19286b905b48432e132628d67a2c720e8
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		// Disables lock-screen and keeps screen on.
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

		loadLastUser();
		initialize();
	}

	/**
	 * Loads the latest user that was connected.
	 */
	public void loadLastUser() {
		sp = getSharedPreferences(C.PREFS_USER, MODE_PRIVATE); 
		String fullUserName = sp.getString(C.PREFS_FULL_USER_NAME, "Anonymous");
		if (fullUserName != null && !fullUserName.equals("Anonymous")) {
			Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
			String user = fullUserName.split("_")[0];
			String pass = fullUserName.split("_")[1];
			String event = fullUserName.split("_")[2];
			Toast.makeText(this, "Hello " + user.substring(6) + "!", Toast.LENGTH_SHORT).show();
			intent.putExtra(C.USER_NAME, user);
			intent.putExtra(C.USER_PASS, pass);
			intent.putExtra(C.EVENT_NUM, event);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			finish();
		}
<<<<<<< HEAD
		else {
			//DVIR --> text to speck massage
			tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {

				@Override
				public void onInit(int arg0) {
					if(arg0 == TextToSpeech.SUCCESS) 
					{
						tts.setLanguage(Locale.UK);
						tts.speak("Welcome to Blind Match Race",TextToSpeech.QUEUE_FLUSH,null);
					}
				}
			});
		}
=======
>>>>>>> 34f45fc19286b905b48432e132628d67a2c720e8
	}

	/**
	 * Initialize components.
	 */
	private void initialize() {
		// Initialize Views.
		etUser = (EditText) findViewById(R.id.etUser);
		etPass = (EditText) findViewById(R.id.etPassword);
		etPass.setOnEditorActionListener(new TextView.OnEditorActionListener() {

			@Override
			public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
				if (id == R.id.login || id == EditorInfo.IME_NULL) {
					attemptLogin();
					return true;
				}
				return false;
			}
		});
		etEvent = (EditText) findViewById(R.id.etEvent);

		svLoginForm = findViewById(R.id.svLoginForm);
		llLoginStatus = findViewById(R.id.llLoginStatus);
		tvLoginStatusMessage = (TextView) findViewById(R.id.tvLoginStatusMessage);

		findViewById(R.id.bSignIn).setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				attemptLogin();
			}
		});

		// Register button.
		findViewById(R.id.bReg).setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				registerRequest = true;
				attemptLogin();
			}
		});
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
				LoginActivity.super.onBackPressed();
				finish();
			}
		}).create().show();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

	/**
	 * Attempts to sign in or register the account specified by the login form.
	 * If there are form errors (missing fields, etc.), the
	 * errors are presented and no actual login attempt is made.
	 */
	public void attemptLogin() {
		if (mAuthTask != null) {
			return;
		}

		// Reset errors.
		etUser.setError(null);
		etPass.setError(null);
		etEvent.setError(null);

		// Store values at the time of the login attempt.
		mUser = C.SAILOR_PREFIX + etUser.getText().toString();
		mPassword = etPass.getText().toString();
		mEvent = etEvent.getText().toString();

		boolean cancel = false;
		View focusView = null;

		// Check for a valid password.
		if (TextUtils.isEmpty(mPassword)) {
			etPass.setError(getString(R.string.error_field_required));
			focusView = etPass;
			cancel = true;
		} else if (mPassword.length() < 4) {
			etPass.setError(getString(R.string.error_invalid_password));
			focusView = etPass;
			cancel = true;
		}

		// Check for a valid user.
		if (mUser.equalsIgnoreCase(C.SAILOR_PREFIX)) {
			etUser.setError(getString(R.string.error_field_required));
			focusView = etUser;
			cancel = true;
		} else if (mUser.contains(" ")) {
			etUser.setError(getString(R.string.error_invalid_user));
			focusView = etUser;
			cancel = true;
		}

		// Check for a valid event.
		if (TextUtils.isEmpty(mEvent)) {
			etEvent.setError(getString(R.string.error_field_required));
			focusView = etEvent;
			cancel = true;
		}

		if (cancel) {
			// There was an error; don't attempt login and focus the first
			// form field with an error.
			focusView.requestFocus();
		}
		else {
			// Show a progress spinner, and kick off a background task to
			// perform the user login attempt.
			tvLoginStatusMessage.setText(R.string.login_progress_signing_in);
			showProgress(true);
			mAuthTask = new UserLoginTask();
			mAuthTask.execute((Void) null);
		}
	}

	/**
	 * Shows the progress UI and hides the login form.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
	private void showProgress(final boolean show) {
		// On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
		// for very easy animations. If available, use these APIs to fade-in
		// the progress spinner.
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
			int shortAnimTime = getResources().getInteger(
					android.R.integer.config_shortAnimTime);

			llLoginStatus.setVisibility(View.VISIBLE);
			llLoginStatus.animate().setDuration(shortAnimTime)
			.alpha(show ? 1 : 0)
			.setListener(new AnimatorListenerAdapter() {

				@Override
				public void onAnimationEnd(Animator animation) {
					llLoginStatus.setVisibility(show ? View.VISIBLE
							: View.GONE);
				}
			});

			svLoginForm.setVisibility(View.VISIBLE);
			svLoginForm.animate().setDuration(shortAnimTime)
			.alpha(show ? 0 : 1)
			.setListener(new AnimatorListenerAdapter() {

				@Override
				public void onAnimationEnd(Animator animation) {
					svLoginForm.setVisibility(show ? View.GONE
							: View.VISIBLE);
				}
			});
		}
		else {
			// The ViewPropertyAnimator APIs are not available, so simply show
			// and hide the relevant UI components.
			llLoginStatus.setVisibility(show ? View.VISIBLE : View.GONE);
			svLoginForm.setVisibility(show ? View.GONE : View.VISIBLE);
		}
	}

	/**
	 * Represents an asynchronous login/registration task used to authenticate
	 * the user.
	 */
	public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

		@Override
		protected Boolean doInBackground(Void... params) {
			if (mUser.equals("Sailoradmin") || mUser.equals("SailorAdmin")) {
				adminRequest = true;
				return mPassword.equals("admin") || mPassword.equals("Admin");
			}

			if (registerRequest) {
				return true;
			}

			String name = "UserLoginTask";
			try {
				// Gets the user data from DB and checks if the user's data match.
				JSONObject json = JsonReader.readJsonFromUrl(C.URL_CLIENTS_TABLE + "&Information=" + mUser + "_" + mPassword + "_" + mEvent);
				JSONArray jsonArray = json.getJSONArray("positions");
				if (jsonArray.length() > 0) {
					JSONObject jsonObj = (JSONObject) jsonArray.get(0);
					if (jsonObj.getString("event").equals(mEvent))
						return true;
				}
			}
			catch (JSONException e) {
				Log.i(name, "JSONException");
				return false;
			}
			catch (IOException e) {
				Log.i(name, "IOException, Ensure Mobile Data is NOT off");
				return false;
			}

			return false;
		}

		@Override
		protected void onPostExecute(final Boolean success) {
			mAuthTask = null;
			showProgress(false);

			if (success) {
				Intent intent;
				if (adminRequest) {
					adminRequest = false;
					intent = new Intent(LoginActivity.this, AdminActivity.class);
				}
				else if (registerRequest) {
					registerRequest = false;

					// HandlerThread for creating a new user in the DB through thread.
					SendDataHThread thread = new SendDataHThread("CreateNewUser");
					thread.setPriority(Process.THREAD_PRIORITY_BACKGROUND);

					thread.setFullUserName(mUser + "_" + mPassword + "_" + mEvent);
					thread.setEvent(mEvent);
					thread.setLat("0");
					thread.setLng("0");
					thread.setSpeed("0");
					thread.setBearing("0");

					thread.start();

					intent = new Intent(LoginActivity.this, MenuActivity.class);
				}
				else {
					intent = new Intent(LoginActivity.this, MenuActivity.class);
				}

				if (!mUser.equals("Sailoradmin") && !mUser.equals("SailorAdmin")) {
					// Updates the SharedPreferences.
					SharedPreferences.Editor spEdit = sp.edit();
					String fullUserName = mUser + "_" + mPassword + "_" + mEvent;
					spEdit.putString(C.PREFS_FULL_USER_NAME, fullUserName);
					spEdit.commit();
				}

				intent.putExtra(C.USER_NAME, mUser);
				intent.putExtra(C.USER_PASS, mPassword);
				intent.putExtra(C.EVENT_NUM, mEvent);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
				finish();
			}
			else {
				etPass.setError(getString(R.string.error_incorrect_pass_event));
				etEvent.setError(getString(R.string.error_incorrect_pass_event));
				etEvent.requestFocus();
			}
		}

		@Override
		protected void onCancelled() {
			mAuthTask = null;
			showProgress(false);
		}
	}

}
