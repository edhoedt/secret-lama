package com.example.ztest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.ListView;

public class ListenerRestaurantMeals extends Activity{
	
	//MySQLiteHelper sqliteHelper = new MySQLiteHelper(this);

	static String numbermemoryrestaurantmeals;
	
	private CheckBox price;
	private CheckBox alphabetically;
	
	private ListView meals;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurantmeals);
      
        Intent i = getIntent();
        numbermemoryrestaurantmeals = i.getStringExtra(ListenerRestaurant.numbermemoryrestaurant);
        
        price = (CheckBox)findViewById(R.id.checkBox2);
        alphabetically = (CheckBox)findViewById(R.id.checkBox1);
        
        meals = (ListView)findViewById(R.id.listView1);
        
        //TODO afficher la page du meal en question si on clique dessus?
	}
}
