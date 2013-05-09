package com.example.ztest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;

public class ListenerOwnerRestaurantMeals extends Activity{
	
	//MySQLiteHelper sqliteHelper = new MySQLiteHelper(this);

	static String numbermemoryrestaurantowner;
	
	private CheckBox price;
	private CheckBox alphabetically;
	
	private ListView meals;
	
	private Button addMeal;
	private Button removeMeal;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
        setContentView(R.layout.ownerrestaurantmeals);
      
        Intent i = getIntent();
        numbermemoryrestaurantowner = i.getStringExtra(ListenerOwnerRestaurant.NUMBER);
        
        price = (CheckBox)findViewById(R.id.checkBox2);
        alphabetically = (CheckBox)findViewById(R.id.checkBox1);
        
        meals = (ListView)findViewById(R.id.listView1);
        
        addMeal = (Button)findViewById(R.id.button1);
        removeMeal = (Button)findViewById(R.id.button2);
		
		addMeal.setOnClickListener(new View.OnClickListener() {
			@Override	
			public void onClick(View v) {
				Intent i = new Intent(ListenerOwnerRestaurantMeals.this, ListenerMealAdd.class);
				i.putExtra(numbermemoryrestaurantowner, numbermemoryrestaurantowner);
				startActivity(i);
			}
		});
		
		removeMeal.setOnClickListener(new View.OnClickListener() {
			@Override	
			public void onClick(View v) {
				//TODO remove le meal en question bdd+model.
				Intent i = new Intent(ListenerOwnerRestaurantMeals.this, ListenerMealRemove.class);
				i.putExtra(numbermemoryrestaurantowner, numbermemoryrestaurantowner);
				startActivity(i);
			}
		});
	}
}
