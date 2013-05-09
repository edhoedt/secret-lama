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


public class ListenerMealAdd extends Activity{
	
	//MySQLiteHelper sqliteHelper = new MySQLiteHelper(this);

	static String numbermemoryrestaurant;
		
	private EditText name;
	private EditText price;
	private EditText ingredient;
	
	private CheckBox available;
	
	private Button addIngredient;
	private Button add;
	
	private ExpandableListView ingredients;
	
	private ImageView picture;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
        setContentView(R.layout.mealadd);
        
		name = (EditText)findViewById(R.id.editText1);
		price = (EditText)findViewById(R.id.editText2);
		ingredient = (EditText)findViewById(R.id.editText4);
		
		available = (CheckBox)findViewById(R.id.checkBox1);
		
		ingredients = (ExpandableListView)findViewById(R.id.expandableListView1);
		
		picture = (ImageView)findViewById(R.id.imageView1);
  
		addIngredient = (Button)findViewById(R.id.button2);
		add = (Button)findViewById(R.id.button1);
		
		// Association Listeners
		
		addIngredient.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//TODO ajouter l'ingredient a la liste
			}
		});
		
		add.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//TODO ajouter le meal.
				Intent i = new Intent(ListenerMealAdd.this, ListenerOwnerRestaurantMeals.class);
				startActivity(i);
			}
		});
	}
}
