package com.example.ztest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ExpandableListView;

public class ListenerFindRestaurant extends Activity{
	
	//MySQLiteHelper sqliteHelper = new MySQLiteHelper(this);

	static String numbermemoryfindrestaurant;
	
	private EditText city;
	
	private CheckBox favouriteTypes;
	private CheckBox unlikedTypes;
	private CheckBox disabled;
	private CheckBox proximity;
	private CheckBox alphabetically;
	
	private Button search;
	
	private ExpandableListView listRestaurants;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
        setContentView(R.layout.findrestaurant);
      
        Intent i = getIntent();
        numbermemoryfindrestaurant = i.getStringExtra(ListenerHome.NUMBER);
        
        city = (EditText)findViewById(R.id.editText1);
        favouriteTypes = (CheckBox)findViewById(R.id.checkBox3);
        unlikedTypes = (CheckBox)findViewById(R.id.checkBox2);
        disabled = (CheckBox)findViewById(R.id.checkBox1);
        proximity = (CheckBox)findViewById(R.id.checkBox4);
        alphabetically = (CheckBox)findViewById(R.id.checkBox5);
        search = (Button)findViewById(R.id.button1);
        listRestaurants = (ExpandableListView)findViewById(R.id.expandableListView1);
		
		search.setOnClickListener(new View.OnClickListener() {
			@Override	
			public void onClick(View v) {
				//TODO refresh/search la liste des restaurants!
			}
		});
		
		//TODO un listener pour choisir un resto ? Dans le expandableListView?
	}
}

