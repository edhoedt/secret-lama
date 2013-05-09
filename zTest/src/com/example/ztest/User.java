package com.example.ztest;

import java.util.ArrayList;

public class User 
{
	private String number;
	private String name;
	private String password;
	private boolean disabled;
	private boolean owner;
	private ArrayList<String> favIngredient;
	private ArrayList<String> dislikedIngredient;
	private ArrayList<String> favRestaurant;
	private ArrayList<String> dislikedRestaurant;
	private ArrayList<String> favRestaurantType;
	private ArrayList<String> dislikedRestaurantType;

	public User(String number, String name, String password, boolean owner)
	{
		this.favIngredient=new ArrayList<String>();
		this.dislikedIngredient=new ArrayList<String>();
		this.favRestaurant=new ArrayList<String>();
		this.dislikedRestaurant=new ArrayList<String>();
		this.favRestaurantType=new ArrayList<String>();
		this.dislikedRestaurantType=new ArrayList<String>();
		this.name=name;
		this.password=password;
		this.disabled=false;
		this.owner=owner;
		this.number=number;
	}

	public boolean likes(String o){ return favIngredient.contains(o) || favRestaurant.contains(o) || favRestaurantType.contains(o); }
	public boolean likes(Restaurant r){ return favRestaurant.contains(r.getNumber());}


	public boolean dislikes(String o){ return dislikedIngredient.contains(o) || dislikedRestaurant.contains(o) || dislikedRestaurantType.contains(o); }
	public boolean dislikes(Restaurant r){ return dislikedRestaurant.contains(r.getNumber());}

	public boolean isDisabled(){ return disabled; }

	//add and remove from favorites
	public void addFavoriteIngredient(String ingredient)
	{
		if(!favIngredient.contains(ingredient))
			favIngredient.add(ingredient);
	}
	public void addFavoriteRestaurant(Restaurant restaurant){
		addFavoriteRestaurant(restaurant.getNumber());
	}
	public void addFavoriteRestaurant(String nameRestaurant)
	{
		if(!favRestaurant.contains(nameRestaurant))
			favRestaurant.add(nameRestaurant);
	}
	public void addFavoriteRestaurantType(String restaurantType)
	{
		if(!favRestaurantType.contains(restaurantType))
			favRestaurantType.add(restaurantType);
	}
	public void removeFavorite(Restaurant r){
		favRestaurant.remove(r.getNumber());}
	public void removeFavorite(String o)
	{
		favIngredient.remove(o);
		favRestaurant.remove(o);
		favRestaurantType.remove(o);
	}

	//add and remove from disliked
	public void addDislikedIngredient(String ingredient)
	{
		if(!dislikedIngredient.contains(ingredient))
			dislikedIngredient.add(ingredient);
	}
	public void addDislikedRestaurant(Restaurant restaurant){
		addDislikedRestaurant(restaurant.getNumber());
	}
	public void addDislikedRestaurant(String nameRestaurant)
	{
		if(!dislikedRestaurant.contains(nameRestaurant))
			dislikedRestaurant.add(nameRestaurant);
	}
	public void addDislikedRestaurantType(String restaurantType)
	{
		if(!dislikedRestaurantType.contains(restaurantType))
			dislikedRestaurantType.add(restaurantType);
	}
	public void removeDisliked(Restaurant r){
		dislikedRestaurant.remove(r.getNumber());}
	public void removeDisliked(String o)
	{
		dislikedIngredient.remove(o);
		dislikedRestaurant.remove(o);
		dislikedRestaurantType.remove(o);
	}
	public String getNumber() { return number; }
	public void setNumber(String number) { this.number = number; }
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	public boolean getDisabled() { return disabled; }
	public void setDisabled(boolean disabled) { this.disabled = disabled; }
	public String getPassword() { return this.password; }
	public void setPassword(String password) { this.password=password; }
	public boolean isOwner() { return this.owner; }
	public void setOwner(boolean owner) { this.owner=owner; }
	
	public ArrayList<String> getFavIngredient() {
		return favIngredient;
	}

	public ArrayList<String> getDislikedIngredient() {
		return dislikedIngredient;
	}

	public ArrayList<String> getFavRestaurant() {
		return favRestaurant;
	}

	public ArrayList<String> getDislikedRestaurant() {
		return dislikedRestaurant;
	}

	public ArrayList<String> getFavRestaurantType() {
		return favRestaurantType;
	}

	public ArrayList<String> getDislikedRestaurantType() {
		return dislikedRestaurantType;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((number == null) ? 0 : number.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (number == null) 
		{
			if (other.getNumber() != null)
				return false;
		} 
		else if (!number.equals(other.getNumber()))
			return false;
		return true;
	}
}