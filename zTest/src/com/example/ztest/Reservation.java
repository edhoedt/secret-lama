package com.example.ztest;

import java.util.Calendar;
import java.util.HashMap;


public class Reservation
{

	private int id;
	private Calendar date;
	private final String seatsType;
	private final int seatsNumber;
	private HashMap<Meal, Integer> meals;
	private HashMap<Menu, Integer> menus;
	private Restaurant restaurant;
	private User user;

	//Constructeur
	public Reservation(int id, Calendar date, String seatsType, int seatsNumber, Restaurant restaurant, User user)
	{
		this.id=id;
		this.date=date;
		this.seatsType=seatsType;
		this.seatsNumber=seatsNumber;
		this.meals = new HashMap<Meal, Integer>();
		this.menus = new HashMap<Menu, Integer>();
		this.restaurant=restaurant;
		this.user=user;
	}
	
	public int getId() { return this.id; }
	public void setId(int newId) { this.id = newId ; } //TODO � voir si on garde.  Si besoin pour chargement en cache depuis la base de donn�e
	public Calendar getDate() { return this.date; }
	public String getSeatsType() { return this.seatsType; }
	public int getSeatsNumber() { return this.seatsNumber; }
	public Meal[] getMeals() { return (Meal[]) this.meals.keySet().toArray(); }
	public void addMeal(Meal meal, int number) { this.meals.put(meal,this.getNumerOf(meal)+number); }
	public void removeMeal(Meal meal) { this.meals.remove(meal); }
	public Menu[] getMenus() { return (Menu[]) this.menus.keySet().toArray();  }
	public void addMenu(Menu menu, int number) { this.menus.put(menu,this.getNumerOf(menu)+number); }
	public void removeMenu(Menu menu) { this.menus.remove(menu); }
	public Restaurant getRestaurant() { return this.restaurant; }
	public User getUser() { return this.user; }
	
	public int getNumerOf(Meal m){
		if(this.meals.containsKey(m))
			return this.meals.get(m);
		else return 0;
	}
	public int getNumerOf(Menu m){
		if(this.menus.containsKey(m))
			return this.menus.get(m);
		else return 0;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result
				+ ((restaurant == null) ? 0 : restaurant.hashCode());
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
		Reservation other = (Reservation) obj;
		if (id != other.getId())
			return false;
		if (restaurant == null) {
			if (other.getRestaurant() != null)
				return false;
		} else if (!restaurant.equals(other.getRestaurant()))
			return false;
		return true;
	}

}