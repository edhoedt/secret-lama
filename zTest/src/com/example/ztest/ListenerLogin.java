package com.example.ztest;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Toast;


public class ListenerLogin extends Activity{

	static String NUMBER;
		
	private EditText number;
	private EditText password;
	
	private CheckBox client;
	private CheckBox owner;
	
	private Button log_in;
	private Button register;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        
		number = (EditText) findViewById(R.id.editText1);
		password = (EditText) findViewById(R.id.editText2);
		
		client = (CheckBox) findViewById(R.id.checkBox1);
		owner = (CheckBox) findViewById(R.id.checkBox2);
  
		log_in = (Button) findViewById(R.id.button2);
		register = (Button) findViewById(R.id.button1);
		
		// Association Listeners	
		
		client.setOnCheckedChangeListener(new OnCheckedChangeListener() { 

			@Override 
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) { 

			if (buttonView.isChecked()) owner.setChecked(false);
			else owner.setChecked(true);

			}
		});
		
		owner.setOnCheckedChangeListener(new OnCheckedChangeListener() { 

			@Override 
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) { 

			if (buttonView.isChecked()) client.setChecked(false);
			else client.setChecked(true);

			}
		});
		
		
		log_in.setOnClickListener(new View.OnClickListener() {
			@Override	
			public void onClick(View v) {
				if (number.getText().toString().equals("") || password.getText().toString().equals("")) {
					Toast.makeText(getApplicationContext(), "You forgot to fill in all the fields!", Toast.LENGTH_LONG).show();
				}
				else if (client.isChecked()) {
					if(! MainActivity.getUserManager().loadUser(number.getText().toString())) {
						Toast.makeText(getApplicationContext(), "Profile unknown. Please register.", Toast.LENGTH_LONG).show();
					}
					else {
						if(MainActivity.getUserManager().getCurrentUser().isOwner()) {
							Toast.makeText(getApplicationContext(), "You're trying to log in as a user account.", Toast.LENGTH_LONG).show();
						}
						else if(MainActivity.getUserManager().getCurrentUser().getPassword().equals(password.getText().toString())) {
							Intent i = new Intent(ListenerLogin.this, ListenerHome.class);
							i.putExtra(NUMBER, number.getText().toString());
							startActivity(i);
						}
						else Toast.makeText(getApplicationContext(), "Wrong password.", Toast.LENGTH_LONG).show();
					}
				}
				else {
					if(! MainActivity.getUserManager().loadUser(number.getText().toString())) {
						Toast.makeText(getApplicationContext(), "Profile unknown. Please register.", Toast.LENGTH_LONG).show();
					}
					else {
						if(! MainActivity.getUserManager().getCurrentUser().isOwner()) {
							Toast.makeText(getApplicationContext(), "You're trying to log in as a owner account.", Toast.LENGTH_LONG).show();
						}
						else if(MainActivity.getUserManager().getCurrentUser().getPassword().equals(password.getText().toString())) {
							Intent i = new Intent(ListenerLogin.this, ListenerHomeOwner.class);
							i.putExtra(NUMBER, number.getText().toString());
							startActivity(i);
						}
						else Toast.makeText(getApplicationContext(), "Wrong password.", Toast.LENGTH_LONG).show();
					}
				}
						
			}
		});
		

		register.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(ListenerLogin.this, ListenerRegister.class);
				startActivity(i);	
			}
		});
		
	
	}
}
