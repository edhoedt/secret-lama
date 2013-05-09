package com.example.ztest;

import java.util.ArrayList;

public class MenuMealManager
{
	private Menu currentMenu;
	private Meal currentMeal;
	
	public Menu getCurrentMenu() { return currentMenu; }

	public boolean loadMenu(String name, Restaurant rest) 
	{
		this.currentMenu = MainActivity.getDbManager().getMenu(name, rest);
		if(this.currentMenu==null) return false;
		else return true;
	}

	public Meal getCurrentMeal() { return currentMeal; }

	public boolean loadMeal(String name, Restaurant rest) 
	{
		this.currentMeal = MainActivity.getDbManager().getMeal(name, rest);
		if(this.currentMeal==null) return false;
		else return true;
	}

	public Meal getMeal(String name, Restaurant rest){
		return MainActivity.getDbManager().getMeal(name, rest);}
	
	public Menu getMenu(String name, Restaurant rest){
		return MainActivity.getDbManager().getMenu(name, rest);}
	
	void addMenu(String name, Restaurant restaurant, double price)
	{MainActivity.getDbManager().update(new Menu(name, restaurant, price));}

	void deleteMenu(Menu menu) {} //TODO

	void addMeal(String name, Restaurant restaurant, double price, boolean available, String picture)
	{MainActivity.getDbManager().update(new Meal(name, restaurant, price, available, picture));}

	// void deleteMeal (Meal meal) {} TODO

	/* plus nécessaire il me semble, mais je ne suis pas sûr alors je le laisse là pour ne pas oublier
	 * void loadCard () {} //TODO
	 * void unloadCard () {} */
	
	void addMealToMenu (Meal meal)
	{
		if(currentMenu != null){
			currentMenu.addMeal(meal);
			MainActivity.getDbManager().update(currentMenu);}
	}
	void removeMealToMenu(Meal meal)
	{
		if(currentMenu != null){
			currentMenu.removeMeal(meal);
			MainActivity.getDbManager().update(currentMenu);}
	}
	void setNameMenu(String name)
	{
		if(currentMenu != null){
			currentMenu.setName(name);
			MainActivity.getDbManager().update(currentMenu);}
	}
	void setPriceMenu(double price)
	{
		if(currentMenu != null){
			currentMenu.setPrice(price);
			MainActivity.getDbManager().update(currentMenu);}
	}
	void setNameMeal(String name)
	{
		if(currentMeal != null){
			currentMeal.setName(name);
			MainActivity.getDbManager().update(currentMeal);}
	}
	void setPriceMeal(double price)
	{
		if(currentMeal != null){
			currentMeal.setPrice(price);
			MainActivity.getDbManager().update(currentMeal);}
	}
	void addIngredient(String ingredient)
	{
		if(currentMeal != null){
			currentMeal.addIngredient(ingredient);
			MainActivity.getDbManager().update(currentMeal);}
	}
	void removeIngredient(String ingredient)
	{
		if(currentMeal != null){
			currentMeal.removeIngredient(ingredient);
			MainActivity.getDbManager().update(currentMeal);}
	}
	void setAvailable(boolean available)
	{
		if(currentMeal != null){
			currentMeal.setAvailable(available);
			MainActivity.getDbManager().update(currentMeal);}
	}
	void setPicture (String path)
	{
		if(currentMeal != null){
			currentMeal.setPicture(path);
			MainActivity.getDbManager().update(currentMeal);}
	}
}