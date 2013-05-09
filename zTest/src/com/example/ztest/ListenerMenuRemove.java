package com.example.ztest;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ExpandableListView;


public class ListenerMenuRemove extends Activity{
	
	//MySQLiteHelper sqliteHelper = new MySQLiteHelper(this);

	static String numbermemoryrestaurant;
				
	private ExpandableListView menus;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
        setContentView(R.layout.menuremove);
        		
		menus = (ExpandableListView)findViewById(R.id.expandableListView1);
				
		// Association Listeners
		

	}
}
