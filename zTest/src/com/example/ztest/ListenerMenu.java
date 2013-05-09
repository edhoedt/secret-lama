package com.example.ztest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.ListView;

public class ListenerMenu extends Activity{
	
	//MySQLiteHelper sqliteHelper = new MySQLiteHelper(this);

	static String numbermemorymenu;
	
	private CheckBox price;
	private CheckBox alphabetically;
	
	private ListView menus;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
      
        Intent i = getIntent();
        numbermemorymenu = i.getStringExtra(ListenerRestaurantMenus.numbermemoryrestaurantmenus);
        
        //TODO on retrouve le meme probleme que pour la page Restaurant! Il manque des éléments..
	}
}

