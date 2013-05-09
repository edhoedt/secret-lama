package com.example.ztest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class ListenerHomeOwner extends Activity{
	
	private DbManager db;

	static String NUMBER;

	private Button addchangerest;
	private Button changeprofile;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
        setContentView(R.layout.homeowner);
        db = new DbManager(this);
      
        Intent i = getIntent();
        NUMBER = i.getStringExtra(ListenerLogin.NUMBER);
        
		addchangerest = (Button) findViewById(R.id.button4);
		changeprofile = (Button) findViewById(R.id.button2);	
		
		// Association Listeners
		
		addchangerest.setOnClickListener(new View.OnClickListener() {
			@Override	
			public void onClick(View v) {
				Intent i = new Intent(ListenerHomeOwner.this, ListenerOwnerRestaurant.class);
				i.putExtra(NUMBER, NUMBER);
				startActivity(i);
			}
		});

		changeprofile.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(ListenerHomeOwner.this, ListenerProfileOwner.class);
				i.putExtra(NUMBER, NUMBER);
				startActivity(i);			
				
			}
		});
	}
}
