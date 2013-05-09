package com.example.ztest;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ExpandableListView;


public class ListenerMealRemove extends Activity{
	
	//MySQLiteHelper sqliteHelper = new MySQLiteHelper(this);

	static String numbermemoryrestaurant;	
	
	private ExpandableListView meals;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
        setContentView(R.layout.mealremove);
        	
		meals = (ExpandableListView)findViewById(R.id.expandableListView1);

		
		// Association Listeners
		
	}
}
