package com.example.ztest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class ListenerProfile extends Activity{

		static String NUMBER;
	
		private TextView number;
		private TextView name;
		
		private EditText password;
		
		private CheckBox disabled;
		
		private Button change;
		private Button favrest;
		private Button favtype;
		
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			
			super.onCreate(savedInstanceState);
	        setContentView(R.layout.profile);
			
	        Intent i = getIntent();
	        NUMBER = i.getStringExtra(ListenerHome.NUMBER);
	        
			number = (TextView) findViewById(R.id.textView2);
			number.setText(NUMBER);
			
			name = (TextView) findViewById(R.id.textView3);
			password = (EditText) findViewById(R.id.editText3);
			disabled = (CheckBox) findViewById(R.id.checkBox1);
			if(MainActivity.getUserManager().loadUser(NUMBER)) {
				name.setText(MainActivity.getUserManager().getCurrentUser().getName());
				password.setText(MainActivity.getUserManager().getCurrentUser().getPassword());
				disabled.setChecked(MainActivity.getUserManager().getCurrentUser().isDisabled());
			}
	  
			change = (Button) findViewById(R.id.button3);
			favrest = (Button) findViewById(R.id.button1);
			favtype = (Button) findViewById(R.id.button2);	
			
			// Association Listeners
			
			change.setOnClickListener(new View.OnClickListener() {
				@Override	
				public void onClick(View v) {
					// password vide.
					if (password.getText().toString().equals("")) {
						Toast.makeText(getApplicationContext(), "You forgot to fill in the field for the password!", Toast.LENGTH_LONG).show();
					}
					else {
						if(! MainActivity.getUserManager().loadUser(NUMBER)) {
							Toast.makeText(getApplicationContext(), "ERROR : Can't load user.", Toast.LENGTH_LONG).show();
						}
						// password non modifie.
						if((password.getText().toString()).equals(MainActivity.getUserManager().getCurrentUser().getPassword())) {
							MainActivity.getUserManager().setDisabled(disabled.isChecked());
							Toast.makeText(getApplicationContext(), "Your new parameters have been successfully saved.", Toast.LENGTH_LONG).show();
							ListenerProfile.this.finish();
						}
						// password modifie.
						else {
							MainActivity.getUserManager().setPassword(password.getText().toString());
							MainActivity.getUserManager().setDisabled(disabled.isChecked());
							Toast.makeText(getApplicationContext(), "Your new parameters have been successfully saved.", Toast.LENGTH_LONG).show();
							ListenerProfile.this.finish();
						}
					}
				}
			});
			

			favrest.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent i = new Intent(ListenerProfile.this, ListenerProfileFavouriteRestaurants.class);
					i.putExtra(NUMBER, NUMBER);
					startActivity(i);
				}
			});
			
			
			favtype.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					
					Intent i = new Intent(ListenerProfile.this, ListenerProfileFavouriteTypeRestaurants.class);
					//i.putExtra(number.getText().toString()); //TODO
					startActivity(i);
					
				}
			});
		
		}
	}

