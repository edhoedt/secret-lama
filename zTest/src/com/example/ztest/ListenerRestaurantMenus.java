package com.example.ztest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.ListView;

public class ListenerRestaurantMenus extends Activity{
	
	//MySQLiteHelper sqliteHelper = new MySQLiteHelper(this);

	static String numbermemoryrestaurantmenus;
	
	private CheckBox price;
	private CheckBox alphabetically;
	
	private ListView menus;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurantmenus);
      
        Intent i = getIntent();
        numbermemoryrestaurantmenus = i.getStringExtra(ListenerRestaurant.numbermemoryrestaurant);
        
        price = (CheckBox)findViewById(R.id.checkBox2);
        alphabetically = (CheckBox)findViewById(R.id.checkBox1);
        
        menus = (ListView)findViewById(R.id.listView1);
        
        //TODO afficher la page du menu en question si on clique dessus?
	}
}

