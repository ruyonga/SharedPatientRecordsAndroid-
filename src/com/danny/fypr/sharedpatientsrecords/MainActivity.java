package com.danny.fypr.sharedpatientsrecords;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.danny.fypr.sharedpatientsrecords.auth.DatabaseHandler;
import com.danny.fypr.sharedpatientsrecords.auth.Details;

public class MainActivity extends ActionBarActivity {
	EditText password, patientid;
	Button login;
	TextView createAcc;
	DatabaseHandler db = new DatabaseHandler(this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		password = (EditText) findViewById(R.id.password);
		patientid = (EditText) findViewById(R.id.username);
		createAcc = (TextView) findViewById(R.id.signuplink);
		login = (Button) findViewById(R.id.login);
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

				

				if ((id != "") && (pass != "")) {
					Log.d("Reading: ", "Reading all contacts..");
					try {
						/*List<Details> details = db.getAllContacts();
						Log.d("Reading: ", "got contact list..");
						for (Details cn : details) {																															
							String log = "Id: " + cn.getID() + " ,Name: "
									+ cn.getUsername() + " ,Phone: "
									+ cn.getPassword();
							Log.d("results ",  log);
							String storedUsername = cn.getUsername();
							Log.d("saved username ",  storedUsername);
							Log.d("patientid", id);
							if (id.trim() != storedUsername.trim()) {
								Toast.makeText(getApplicationContext(),
										"Wrong Username or Password", Toast.LENGTH_LONG)
										.show();
							}else{
								
								Toast.makeText(getApplicationContext(),
										"Successful login", Toast.LENGTH_LONG)
										.show();
								Intent securedpage = new Intent(
										MainActivity.this, Records.class);
								startActivity(securedpage);
								
							}*/
						if(db.findProduct(id, pass) != null){

							Toast.makeText(getApplicationContext(),
									"Successful login", Toast.LENGTH_LONG)
									.show();
							Intent securedpage = new Intent(
									MainActivity.this, Records.class);
							startActivity(securedpage);
						}else{
							Toast.makeText(getApplicationContext(),
									"Wrong Username or Password", Toast.LENGTH_LONG)
									.show();
						}
						}
				    catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
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
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
