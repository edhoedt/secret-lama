package com.example.ztest;

import java.util.ArrayList;
public class Menu {

	private String name;
	private double price;
	private ArrayList<Meal> meals;
	private Restaurant restaurant;

	// Constructeur
	public Menu(String name, Restaurant restaurant, double price)
	{
		//TODO signaler aux autres que "Meal[] meals" a été retiré du constructeur
		//est-ce une bonne idée de charger entièrement la liste?
		this.name = name;
		this.price = price;
		this.restaurant=restaurant;
		meals= new ArrayList<Meal>();
	}

	//Accesseurs.
	Restaurant getRestaurant(){return this.restaurant;}
	String getName() { return this.name; }
	double getPrice() { return this.price; }
	Meal[] getMeals() { return (Meal[]) this.meals.toArray(); }

	// Modificateurs.
	void setName(String name) { this.name = name; }
	void setPrice(double price) { this.price = price; }
	void addMeal(Meal meal) { meals.add(meal); }
	void removeMeal(Meal meal)
	{
		for(int i = 0 ; i<meals.size() ; i++)
			if((meals.get(i)).equals(meal))
				meals.remove(i);
	}

	/* Redef. de la methode equals.
	 * Deux menus sont identiques si ils partagent le meme nom et les memes plats.
	 */

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((meals == null) ? 0 : meals.hashCode());
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
		Menu other = (Menu) obj;
		if (meals == null) {
			if (other.getMeals() != null)
				return false;
		} else if (other.getMeals().length==meals.size())
			for(int i = 0 ; i<meals.size() ; i++)
				if(! meals.contains(other.getMeals()[i]))
					return false;
		if (name == null) {
			if (other.getName() != null)
				return false;
		} else if (!name.equals(other.getName()))
			return false;
		return true;
	}
	
}