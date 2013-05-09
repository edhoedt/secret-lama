package com.example.ztest;


public class UserManager
{
	private User currentUser;
	public UserManager() { }

	public User getCurrentUser() { return currentUser; }
	
	public boolean loadUser(String userNum) {
		this.currentUser = MainActivity.getDbManager().getUser(userNum);
		if(this.currentUser==null) return false;
		else return true;
	}
	public void addUser(String userNumber, String userName, String password, boolean owner) { MainActivity.getDbManager().update(new User(userNumber, userName, password, owner)); }

	public void setName(String name){
		if(this.currentUser!=null) {
			this.currentUser.setName(name);
			MainActivity.getDbManager().update(currentUser);
		}
	}
	
	public void setPassword(String password) {
		if(this.currentUser!=null) {
			this.currentUser.setPassword(password);
			MainActivity.getDbManager().update(currentUser);
		}
	}
	
	public void setDisabled(boolean disabled) {
		if(this.currentUser!=null) {
			this.currentUser.setDisabled(disabled);
			MainActivity.getDbManager().update(currentUser);
		}
	}
	
	public void addFavoriteIngredient(String ingredient){
		currentUser.addFavoriteIngredient(ingredient);
		MainActivity.getDbManager().update(currentUser);
	}
	public void addFavoriteRestaurant(Restaurant restaurant){
		currentUser.addFavoriteRestaurant(restaurant);
		MainActivity.getDbManager().update(currentUser);
	}
	public void addFavoriteRestaurantType(String restaurantType){
		currentUser.addFavoriteRestaurantType(restaurantType);
		MainActivity.getDbManager().update(currentUser);
	}
	public void removeFavorite(Restaurant rest){
		currentUser.removeDisliked(rest);
	}
	public void removeFavorite(String o){
		currentUser.removeFavorite(o);
		MainActivity.getDbManager().update(currentUser);
	}

	public void addDislikedIngredient(String ingredient){
		currentUser.addDislikedIngredient(ingredient);
		MainActivity.getDbManager().update(currentUser);
	}
	public void addDislikedRestaurant(Restaurant restaurant){
		currentUser.addDislikedRestaurant(restaurant);
		MainActivity.getDbManager().update(currentUser);
	}
	public void addDislikedRestaurantType(String restaurantType){
		currentUser.addDislikedRestaurantType(restaurantType);
		MainActivity.getDbManager().update(currentUser);
	}
	public void removeDisliked(Restaurant rest){
		currentUser.removeDisliked(rest);
	}
	public void removeDisliked(String o) {
		currentUser.removeDisliked(o);
		MainActivity.getDbManager().update(currentUser);
	}
}