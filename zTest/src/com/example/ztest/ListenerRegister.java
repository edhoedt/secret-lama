package com.example.ztest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Toast;

public class ListenerRegister extends Activity {
	
	static String NUMBER;
	
	private EditText number;
	private EditText name;
	private EditText password;
		
	private CheckBox client;
	private CheckBox owner;
		
	private Button register;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        
		number = (EditText) findViewById(R.id.editText3);
		name = (EditText) findViewById(R.id.editText1);
		password = (EditText) findViewById(R.id.editText2);
		
		client = (CheckBox) findViewById(R.id.checkBox1);
		owner = (CheckBox) findViewById(R.id.checkBox2);
  
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
		
		
		register.setOnClickListener(new View.OnClickListener() {
			@Override	
			public void onClick(View v) {
				
				if (number.getText().toString().equals("") || name.getText().toString().equals("") || password.getText().toString().equals("")) {
					Toast.makeText(getApplicationContext(), "You forgot to fill in all the fields!", Toast.LENGTH_LONG).show();
				}
				// Client
				else if (client.isChecked()) {
					if(MainActivity.getDbManager().getUser(number.getText().toString())==null) {
						MainActivity.getUserManager().addUser(number.getText().toString(), name.getText().toString(), password.getText().toString(), false);
						Intent i = new Intent(ListenerRegister.this, ListenerHome.class);
						i.putExtra(NUMBER, number.getText().toString());
						startActivity(i);
					}
					else Toast.makeText(getApplicationContext(), "This number is already used.", Toast.LENGTH_LONG).show();
				}
				// Proprio restaurant.
				else {
					if(MainActivity.getDbManager().getUser(number.getText().toString())==null) {
						MainActivity.getUserManager().addUser(number.getText().toString(), name.getText().toString(), password.getText().toString(), true);
						Intent i = new Intent(ListenerRegister.this, ListenerHomeOwner.class);
						i.putExtra(NUMBER, number.getText().toString());
						startActivity(i);
					}
					else Toast.makeText(getApplicationContext(), "This number is already used.", Toast.LENGTH_LONG).show();
				}
			}
		});
	}
}
