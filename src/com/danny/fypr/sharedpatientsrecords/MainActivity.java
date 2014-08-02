package com.danny.fypr.sharedpatientsrecords;

import android.app.ProgressDialog;
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

public class MainActivity extends ActionBarActivity implements OnClickListener {
	
	
	EditText password, patientid;
	Button login;
	TextView createAcc,pswd;
	//create an instance of the Database handler class
	DatabaseHandler db = new DatabaseHandler(this);
	ProgressDialog pDiag;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//loading spinner in the actionbar
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		setContentView(R.layout.activity_main);
		//call for the actionbar
		getSupportActionBar().setTitle("Login Page");
		setProgressBarIndeterminateVisibility(false);
		//create an instace of the progress dialogue
		pDiag = new ProgressDialog(MainActivity.this);
		pDiag.setIndeterminate(true);
		pDiag.setMessage("Logging you in...");
		//create a ference to the items in the xml file
		password = (EditText) findViewById(R.id.password);
		patientid = (EditText) findViewById(R.id.username);
		createAcc = (TextView) findViewById(R.id.signuplink);
		pswd = (TextView) findViewById(R.id.pswdchage);
		login = (Button) findViewById(R.id.loginbtn);
		
		//assign onclick event to the button and links 
		//on the xml file
		createAcc.setOnClickListener(this);
		pswd.setOnClickListener(this);
		login.setOnClickListener(this);

	}

	
	
	//handles users click to the menue
	
	
	//this is the logic for the user click events

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		//if the password change link is clicked
		if(v.getId() == R.id.pswdchage){
			Intent pswdchange = new Intent(MainActivity.this,
					ChangePassword.class);
			startActivity(pswdchange);
		}
		
		//handles the login process
		else if(v.getId() == R.id.loginbtn){
			//call the progress dialog to show
			//then get the values from the username and password editext fields
			pDiag.show();
			String id = patientid.getText().toString();
			String pass = password.getText().toString();
			setProgressBarIndeterminateVisibility(true);
			
//check if the username and password aint empty
			if ((id != "") && (pass != "")) {
				Log.d("Reading: ", "Reading all contacts..");
				try {
					/*call the database object to access the selectUser method in the Database 
					handler class
					if the returned value aint empty continue to the Records
					
					*/
					if(db.selectUser(id, pass) != null){

						Toast.makeText(getApplicationContext(),
								"Successful login", Toast.LENGTH_LONG)
								.show();
						setProgressBarIndeterminateVisibility(false);
						Intent securedpage = new Intent(
								MainActivity.this, RecordsPage.class);
						patientid.setText("");
						password.setText("");
						//get the username and take to the Records activity
						Bundle bundle =  new Bundle();
						bundle.putString("patId", id);
						securedpage.putExtras(bundle);
						pDiag.dismiss();
						startActivity(securedpage);
						finish();
						
					}else{
						Toast.makeText(getApplicationContext(),
								"Wrong Username or Password", Toast.LENGTH_LONG)
								.show();
						pDiag.dismiss();
						//setProgressBarIndeterminateVisibility(false);
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
			}
		}
		else if(v.getId() == R.id.signuplink){
			Intent goToSignup = new Intent(MainActivity.this,
					CreateAcc.class);
			startActivity(goToSignup);
		}
	}

}
