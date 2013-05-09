package com.example.ztest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ListenerOwnerRestaurant extends Activity{

	static String NUMBER;
	
	private TextView number;
	
	private ListView types;
	private ListView schedule;
	private ListView places;
	private ListView payments;
	
	private EditText name;
	private EditText numberStreet;
	private EditText city;
	private EditText street;
	private EditText description;
	private EditText type;
	private EditText day;
	private EditText openHour;
	private EditText closedHour;
	private EditText typePlace;
	private EditText nbPlace;
	private EditText payment;
	
	private CheckBox disabled;
	
	private Button change;
	private Button addType;
	private Button removeType;
	private Button addSchedule;
	private Button removeSchedule;
	private Button addPlace;
	private Button removePlace;
	private Button addPayment;
	private Button removePayment;
	private Button listMeals;
	private Button listMenus;
	private Button pictures;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
        setContentView(R.layout.ownerrestaurant);
      
        Intent i = getIntent();
        NUMBER = i.getStringExtra(ListenerHomeOwner.NUMBER);
        
        number = (TextView)findViewById(R.id.textView2);
        number.setText(NUMBER);
        
        name = (EditText)findViewById(R.id.editText1);
		city = (EditText)findViewById(R.id.editText2);
		street = (EditText)findViewById(R.id.editText3);
		numberStreet = (EditText)findViewById(R.id.editText4);		
		description = (EditText)findViewById(R.id.editText6);
		disabled = (CheckBox)findViewById(R.id.checkBox1);
		change = (Button)findViewById(R.id.button2);
		
		types = (ListView)findViewById(R.id.listView1);
		type = (EditText)findViewById(R.id.editText5);
		addType = (Button)findViewById(R.id.button3);
		removeType = (Button)findViewById(R.id.button4);
		
		schedule = (ListView)findViewById(R.id.listView2);
		day = (EditText)findViewById(R.id.editText7);
		openHour = (EditText)findViewById(R.id.editText5);
		closedHour = (EditText)findViewById(R.id.editText8);
		addSchedule = (Button)findViewById(R.id.button5);
		removeSchedule = (Button)findViewById(R.id.button6);
		
		places = (ListView)findViewById(R.id.listView3);
		typePlace = (EditText)findViewById(R.id.editText9);
		nbPlace = (EditText)findViewById(R.id.editText10);
		addPlace = (Button)findViewById(R.id.button7);
		removePlace = (Button)findViewById(R.id.button8);
		
		payments = (ListView)findViewById(R.id.listView5);
		payment = (EditText)findViewById(R.id.editText11);
		addPayment = (Button)findViewById(R.id.button9);
		removePayment = (Button)findViewById(R.id.button10);
		
		if(MainActivity.getRestaurantManager().loadRestaurant(NUMBER)) {
			name.setText(MainActivity.getRestaurantManager().getCurrentRestaurant().getName());
			city.setText(MainActivity.getRestaurantManager().getCurrentRestaurant().getCity().getName());
			street.setText(MainActivity.getRestaurantManager().getCurrentRestaurant().getAddress());
			numberStreet.setText(MainActivity.getRestaurantManager().getCurrentRestaurant().getNumberAddress());
			description.setText(MainActivity.getRestaurantManager().getCurrentRestaurant().getDescription());
			disabled.setChecked(MainActivity.getRestaurantManager().getCurrentRestaurant().isAccessDisabled());
		}
		
		listMeals = (Button)findViewById(R.id.button1);
		listMenus = (Button)findViewById(R.id.button12);
		pictures = (Button)findViewById(R.id.button13);
		
		// LISTENERS
		change.setOnClickListener(new View.OnClickListener() {
			@Override	
			public void onClick(View v) {
				// Restaurant pas encore cree.
				if(! MainActivity.getRestaurantManager().loadRestaurant(NUMBER)) {
					//City currentCity = MainActivity.getCityManager().loadCity(city.getText().toString());
					//Restaurant newRestau = new Restaurant(NUMBER, name.getText().toString(), number.getText().toString(), street.getText().toString(),currentCity);
					//MainActivity.getDbManager().update(newRestau);
					Toast.makeText(getApplicationContext(), "Your restaurant has been successfully modified.", Toast.LENGTH_LONG).show();
				}
				// Restaurant deja cree.
				else {
					
				}
			}
		});
		
		listMeals.setOnClickListener(new View.OnClickListener() {
			@Override	
			public void onClick(View v) {
				//TODO liste les meals
				Intent i = new Intent(ListenerOwnerRestaurant.this, ListenerOwnerRestaurantMeals.class);
				i.putExtra(NUMBER, NUMBER);
				startActivity(i);
			}
		});
		
		listMenus.setOnClickListener(new View.OnClickListener() {
			@Override	
			public void onClick(View v) {
				//TODO liste les menus
				Intent i = new Intent(ListenerOwnerRestaurant.this, ListenerOwnerRestaurantMenus.class);
				i.putExtra(NUMBER, NUMBER);
				startActivity(i);
			}
		});
		
		pictures.setOnClickListener(new View.OnClickListener() {
			@Override	
			public void onClick(View v) {
				//TODO liste les pictures
				/*Intent i = new Intent(ListenerOwnerRestaurant.this, ListenerOwnerRestaurantPictures.class);
				i.putExtra(numbermemorylogin, number.getText().toString());
				startActivity(i);*/
			}
		});
	}
}
