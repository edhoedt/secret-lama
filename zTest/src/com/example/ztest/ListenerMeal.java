package com.example.ztest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.ListView;

public class ListenerMeal extends Activity{
	
	//MySQLiteHelper sqliteHelper = new MySQLiteHelper(this);

	static String numbermemorymeal;
	
	private CheckBox price;
	private CheckBox alphabetically;
	
	private ListView meals;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
        setContentView(R.layout.meal);
      
        Intent i = getIntent();
        numbermemorymeal = i.getStringExtra(ListenerRestaurantMeals.numbermemoryrestaurantmeals);
        
        //TODO on retrouve le meme probleme que pour la page Restaurant et menu! Il manque des éléments..
	}
}


