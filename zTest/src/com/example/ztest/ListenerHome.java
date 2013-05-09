package com.example.ztest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;


public class ListenerHome extends Activity{

	static String NUMBER;
	static boolean FAV; 
	
	private CheckBox favourite;
	
	private Button lookrest;
	private Button changeprofile;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
		
        Intent i = getIntent();
        NUMBER = i.getStringExtra(ListenerLogin.NUMBER);
        FAV = i.getBooleanExtra("FAV", false);
        
		favourite = (CheckBox) findViewById(R.id.checkBox1);
  
		lookrest = (Button) findViewById(R.id.button2);
		changeprofile = (Button) findViewById(R.id.button1);
		
		// Association Listeners
		
		
		lookrest.setOnClickListener(new View.OnClickListener() {
			@Override	
			public void onClick(View v) {	
				Intent i = new Intent(ListenerHome.this, ListenerFindRestaurant.class);
				i.putExtra(NUMBER, NUMBER);
				if(favourite.isChecked()) i.putExtra("FAV", true);
				else i.putExtra("FAV", false);
				startActivity(i);	
			}
		});
		

		changeprofile.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				Intent i = new Intent(ListenerHome.this, ListenerProfile.class);
				i.putExtra(NUMBER, NUMBER);
				startActivity(i);
			}
		});
		
	
	}
}
