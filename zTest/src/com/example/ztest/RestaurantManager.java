package com.example.ztest;

import java.util.ArrayList;

public class RestaurantManager
{
	private Restaurant currentRestaurant;
	public RestaurantManager() { }

	public Restaurant getCurrentRestaurant() { return currentRestaurant; }

	public boolean loadRestaurant(String resNum) {
		this.currentRestaurant = MainActivity.getDbManager().getRestaurant(resNum);
		if(this.currentRestaurant==null) return false;
		else return true;
	}
	
	public Restaurant getRestaurant (String number){
		return MainActivity.getDbManager().getRestaurant(number);}

	public Restaurant[] getRestaurant(){
		ArrayList<Restaurant> rest = MainActivity.getDbManager().getRestaurants();
		return (Restaurant[]) rest.toArray();}

	public Restaurant[] getRestaurant(City city)
	{
		ArrayList<Restaurant> rest = MainActivity.getDbManager().getRestaurants(city);
		return (Restaurant[]) rest.toArray();
	}

	public void addRestaurant(String resNumber, String resName, String numAdd, String add, City city)
	{ MainActivity.getDbManager().update(new Restaurant(resNumber,resName,numAdd,add,city)); }

	public void setNumber(String number)
	{
		if(this.currentRestaurant!=null) {
			this.currentRestaurant.setNumber(number);
			MainActivity.getDbManager().update(currentRestaurant);
		}
	}

	public void setName(String name)
	{
		if(this.currentRestaurant!=null) {
			this.currentRestaurant.setName(name);
			MainActivity.getDbManager().update(currentRestaurant);
		}
	}

	public void setDescription (String description)
	{
		if(this.currentRestaurant!=null) {
			this.currentRestaurant.setDescription(description);
			MainActivity.getDbManager().update(currentRestaurant);
		}
	}

	public void setNumberAddress(String numberAddress)
	{
		if(this.currentRestaurant!=null) {
			this.currentRestaurant.setNumberAddress(numberAddress);
			MainActivity.getDbManager().update(currentRestaurant);
		}
	}

	public void setAddress(String address)
	{
		if(this.currentRestaurant!=null) {
			this.currentRestaurant.setAddress(address);
			MainActivity.getDbManager().update(currentRestaurant);
		}
	}

	public void setCity(City city)
	{
		if(this.currentRestaurant!=null) {
			this.currentRestaurant.setCity(city);
			MainActivity.getDbManager().update(currentRestaurant);
		}
	}

	public void setAccessDisabled(Boolean accessDisabled)
	{
		if(this.currentRestaurant!=null) {
			this.currentRestaurant.setAccessDisabled(accessDisabled);
			MainActivity.getDbManager().update(currentRestaurant);
		}
	}

	public void addSeats(String type, int number)
	{
		if(this.currentRestaurant!=null) {
			this.currentRestaurant.addSeats(type, number);
			MainActivity.getDbManager().update(currentRestaurant);
		}
	}

	public void addPictures(String path)
	{
		if(this.currentRestaurant!=null) {
			this.currentRestaurant.addPictures(path);
			MainActivity.getDbManager().update(currentRestaurant);
		}
	}

	public void addPaymentMethod(String paymentMethod)
	{
		if(this.currentRestaurant!=null) {
			this.currentRestaurant.addPaymentMethod(paymentMethod);
			MainActivity.getDbManager().update(currentRestaurant);
		}
	}

	public void addTypes(String type)
	{
		if(this.currentRestaurant!=null) {
			this.currentRestaurant.addTypes(type);
			MainActivity.getDbManager().update(currentRestaurant);
		}
	}

	public void deleteSeats(String type, int number)
	{
		if(this.currentRestaurant!=null) {
			this.currentRestaurant.deleteSeats(type,number);
			MainActivity.getDbManager().update(currentRestaurant);
		}
	}

	public void removePicture(String path)
	{
		if(this.currentRestaurant!=null) {
			this.currentRestaurant.removePicture(path);
			MainActivity.getDbManager().update(currentRestaurant);
		}
	}

	public void removePaymentMethod(String paymentMethod)
	{
		if(this.currentRestaurant!=null) {
			this.currentRestaurant.removePaymentMethod(paymentMethod);
			MainActivity.getDbManager().update(currentRestaurant);
		}
	}

	public void removeType(String type)
	{
		if(this.currentRestaurant!=null) {
			this.currentRestaurant.removeType(type);
			MainActivity.getDbManager().update(currentRestaurant);
		}
	}

	public void addSchedules(int openDay, int closeDay, int openHour, int closeHour, int openMinute, int closeMinute)
	{
		if(this.currentRestaurant!=null) {
			this.currentRestaurant.addSchedules(openDay,closeDay,openHour,closeHour,openMinute,closeMinute);
			MainActivity.getDbManager().update(currentRestaurant);
		}
	}

	public void addSchedules (int openMinute, int closeMinute)
	{
		if(this.currentRestaurant!=null) {
			this.currentRestaurant.addSchedules(openMinute,closeMinute);
			MainActivity.getDbManager().update(currentRestaurant);
		}
	}

	public void removeSchedules (int openMinute, int closeMinute)
	{
		if(this.currentRestaurant!=null) {
			this.currentRestaurant.removeSchedules(openMinute,closeMinute);
			MainActivity.getDbManager().update(currentRestaurant);
		}
	}

	public void removeSchedules(int openDay, int closeDay, int openHour, int closeHour, int openMinute, int closeMinute)
	{
		if(this.currentRestaurant!=null) {
			this.currentRestaurant.removeSchedules(openDay,closeDay,openHour,closeHour,openMinute,closeMinute);
			MainActivity.getDbManager().update(currentRestaurant);
		}
	}

	public void setId(int newId)
	{
		if(this.currentRestaurant!=null) {
			this.currentRestaurant.setId(newId);
			MainActivity.getDbManager().update(currentRestaurant);
		}
	}
}