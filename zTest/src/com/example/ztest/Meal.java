package com.example.ztest;

import java.util.ArrayList;
public class Meal
{

	private String name;
	private double price;
	private boolean available;
	private String picture;
	private ArrayList<String> ingredients;
	private Restaurant restaurant;

	// Constructeur.
	public Meal(String name, Restaurant restaurant, double price, boolean available, String picture)
	{
		//TODO signaler aux autres que igredients a été retiré du constructeur
		//trop compliqué à charger depuis la base de données avant la création de l'objet
		this.name = name;
		this.price = price;
		this.available = available;
		this.picture = picture;
		this.restaurant=restaurant;
		this.ingredients=new ArrayList<String>();
	}

	//Accesseurs.
	String getName() { return this.name; }
	Restaurant getRestaurant(){return this.restaurant;}
	double getPrice() { return this.price; }
	boolean isAvailable() { return this.available; }
	String getPicture() { return this.picture; }
	String[] getIngredients() { return (String[]) this.ingredients.toArray(); }

	// Modificateurs.
	void setName(String name) { this.name = name; }
	void setPrice(double price) { this.price = price; }
	void setAvailable(boolean available) { this.available = available; }
	void setPicture (String path) { this.picture = path; }
	void addIngredient(String ingredient) { ingredients.add(ingredient); }
	void removeIngredient(String ingredient) { ingredients.remove(ingredient); }

	/* Redef. de la methode equals.
	 * Deux plats sont identiques si ils partagent le meme nom et les memes ingredients.
	 */

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((ingredients == null) ? 0 : ingredients.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Meal other = (Meal) obj;
		if (ingredients == null) {
			if (other.getIngredients() != null)
				return false;
		} else if (other.getIngredients().length==ingredients.size())
			for(int i = 0 ; i<(getIngredients()).length ; i++) 
			if(! ingredients.contains(other.getIngredients()[i])) 
				return false;
		if (name == null) {
			if (other.getName() != null)
				return false;
		} else if (!name.equals(other.getName()))
			return false;
		return true;
	}
	
}