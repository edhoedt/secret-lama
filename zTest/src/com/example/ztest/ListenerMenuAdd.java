package com.example.ztest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;


public class ListenerMenuAdd extends Activity{
	
	//MySQLiteHelper sqliteHelper = new MySQLiteHelper(this);

	static String numbermemoryrestaurant;
		
	private EditText name;
	private EditText price;
	private EditText meal;
	
	private CheckBox available;
	
	private Button addMeal;
	private Button add;
	
	private ExpandableListView meals;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
        setContentView(R.layout.menuadd);
        
		name = (EditText)findViewById(R.id.editText1);
		price = (EditText)findViewById(R.id.editText2);
		meal = (EditText)findViewById(R.id.editText4);
		
		available = (CheckBox)findViewById(R.id.checkBox1);
		
		meals = (ExpandableListView)findViewById(R.id.expandableListView1);
  
		addMeal = (Button)findViewById(R.id.button2);
		add = (Button)findViewById(R.id.button1);
		
		// Association Listeners
		
		addMeal.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//TODO ajouter le meal a la liste
			}
		});
		
		add.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//TODO ajouter le menu.
				Intent i = new Intent(ListenerMenuAdd.this, ListenerOwnerRestaurantMeals.class);
				startActivity(i);
			}
		});
	}
}
