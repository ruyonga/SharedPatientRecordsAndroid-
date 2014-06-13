package com.danny.fypr.sharedpatientsrecords;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.danny.fypr.sharedpatientsrecords.auth.DatabaseHandler;

public class MainActivity extends ActionBarActivity {
	EditText password, patientid;
	Button login;
	TextView createAcc;
	DatabaseHandler db = new DatabaseHandler(this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		setContentView(R.layout.activity_main);
		getSupportActionBar().setTitle("Login Page");

		password = (EditText) findViewById(R.id.password);
		patientid = (EditText) findViewById(R.id.username);
		createAcc = (TextView) findViewById(R.id.signuplink);
		login = (Button) findViewById(R.id.loginbtn);
		createAcc.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Intent goToSignup = new Intent(MainActivity.this,
						CreateAcc.class);
				startActivity(goToSignup);
			}
		});
		login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String id = patientid.getText().toString();
				String pass = password.getText().toString();
				setProgressBarIndeterminateVisibility(true);
				

				if ((id != "") && (pass != "")) {
					Log.d("Reading: ", "Reading all contacts..");
					try {
						
						if(db.selectUser(id, pass) != null){

							Toast.makeText(getApplicationContext(),
									"Successful login", Toast.LENGTH_LONG)
									.show();
							setProgressBarIndeterminateVisibility(false);
							Intent securedpage = new Intent(
									MainActivity.this, Records.class);
							startActivity(securedpage);
							
						}else{
							Toast.makeText(getApplicationContext(),
									"Wrong Username or Password", Toast.LENGTH_LONG)
									.show();
							setProgressBarIndeterminateVisibility(false);
						}
						}
				    catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						setProgressBarIndeterminateVisibility(false);
					}
				
			}
				else{
					patientid.requestFocus();
					patientid.setBackgroundColor(Color.RED);
				
					Toast.makeText(getApplicationContext(), "Patient id and password missin", Toast.LENGTH_LONG).show();
				}}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
		Intent toSettings = new Intent(MainActivity.this, Settings.class);
		startActivity(toSettings);
		}
		return super.onOptionsItemSelected(item);
	}

}
