package com.example.ztest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;

public class ListenerOwnerRestaurantMenus extends Activity{
	
	//MySQLiteHelper sqliteHelper = new MySQLiteHelper(this);

	static String numbermemoryrestaurantowner;
	
	private CheckBox price;
	private CheckBox alphabetically;
	
	private ListView menus;
	
	private Button addMenu;
	private Button removeMenu;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
        setContentView(R.layout.ownerrestaurantmenus);
      
        Intent i = getIntent();
        numbermemoryrestaurantowner = i.getStringExtra(ListenerOwnerRestaurant.NUMBER);
        
        price = (CheckBox)findViewById(R.id.checkBox2);
        alphabetically = (CheckBox)findViewById(R.id.checkBox1);
        
        menus = (ListView)findViewById(R.id.listView1);
        
        addMenu = (Button)findViewById(R.id.button1);
        removeMenu = (Button)findViewById(R.id.button2);
		
		addMenu.setOnClickListener(new View.OnClickListener() {
			@Override	
			public void onClick(View v) {
				Intent i = new Intent(ListenerOwnerRestaurantMenus.this, ListenerMenuAdd.class);
				i.putExtra(numbermemoryrestaurantowner, numbermemoryrestaurantowner);
				startActivity(i);
			}
		});
		
		removeMenu.setOnClickListener(new View.OnClickListener() {
			@Override	
			public void onClick(View v) {
				//TODO remove le menu en question bdd+model.
				Intent i = new Intent(ListenerOwnerRestaurantMenus.this, ListenerMenuRemove.class);
				i.putExtra(numbermemoryrestaurantowner, numbermemoryrestaurantowner);
				startActivity(i);
			}
		});
	}
}
