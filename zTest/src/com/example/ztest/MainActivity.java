package com.example.ztest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {

	private static DbManager dbManager;
	private static MenuMealManager menuMealManager;
	private static ReservationManager reservationManager;
	private static UserManager userManager;
	private static CityManager cityManager;
	private static RestaurantManager restaurantManager;

	public static DbManager getDbManager() {
		return dbManager;
	}

	public static MenuMealManager getMenuMealManager() {
		return menuMealManager;
	}

	public static ReservationManager getReservationManager() {
		return reservationManager;
	}

	public static UserManager getUserManager() {
		return userManager;
	}

	public static CityManager getCityManager() {
		return cityManager;
	}
	
	public static RestaurantManager getRestaurantManager() {
		return restaurantManager;
	}

	@Override
    protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
        dbManager= new DbManager(this);
        menuMealManager= new MenuMealManager();
        reservationManager= new ReservationManager();
        userManager= new UserManager();
        cityManager= new CityManager();
        restaurantManager= new RestaurantManager();
        
        Intent i = new Intent(MainActivity.this, ListenerLogin.class);
		startActivity(i);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
