package com.example.ztest;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;


public class ListenerProfileFavouriteRestaurants extends Activity {

	static String NUMBER;
	
	private Spinner pickRest;
	
	private Button add;
	private Button remove;
	
	private ListView favRestaurants;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.profilefavouriterestaurants);
        
        Intent i = getIntent();
        NUMBER = i.getStringExtra(ListenerHome.NUMBER);
		
		pickRest = (Spinner)findViewById(R.id.spinner);
		add = (Button) findViewById(R.id.button1);
		
		favRestaurants = (ListView)findViewById(R.id.listView1);
		if(MainActivity.getUserManager().loadUser(NUMBER)) {
			Log.i("test","reach1");
			ArrayList<String> listFavRest = MainActivity.getUserManager().getCurrentUser().getFavRestaurant();
			String[] nameRestaurants = new String[listFavRest.size()];
			for(int j=0;j<listFavRest.size();j++) {
				nameRestaurants[j]= MainActivity.getRestaurantManager().getRestaurant(listFavRest.get(j)).getName();
			}
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nameRestaurants); 
			favRestaurants.setAdapter(adapter);
		}
		remove = (Button) findViewById(R.id.button4);
		
		// Association Listeners

		add.setOnClickListener(new View.OnClickListener() {
			@Override	
			public void onClick(View v) {
					
			}
		});
		
		remove.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				if (remove.getText().toString().equals("")) {
					Toast.makeText(getApplicationContext(), "You forgot to fill in the field!", Toast.LENGTH_LONG).show();
				}
				
			}
		});
		
	
	}
}
