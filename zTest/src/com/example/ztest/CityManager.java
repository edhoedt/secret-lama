package com.example.ztest;

import android.location.Location;

public class CityManager {

	private City currentCity;
	
	public CityManager() {}
	
	public void addCity(String name, Location GPSLoc, String Description) { MainActivity.getDbManager().update(new City(name, GPSLoc, Description)); }
	
	/* Pas compris l'intérêt et le comment de cette fonction.  Refaite selon nouveau système ci-dessous
	 * public City getCity(String name){
		City dummy = new City(name, null, null);
		if(cities.indexOf(dummy)>=0){
			return cities.get(cities.indexOf(dummy));
		}else{
			cities.add(dummy);
			return dummy;
		}
	}*/
	
	public City getCity(String name){ return MainActivity.getDbManager().getCity(name); }

	public void selectCity(Location city)
	{
		//TODO les objets City ont une location tr�s pr�cise, et il y a tr�s
		// peu de chance que celle renvoy�e par le GPS corresponde exactement
		// il faudrait donc impl�menter la recherche de la ville la plus proche
	}
	
	public boolean loadCity(String name) {
		this.currentCity = MainActivity.getDbManager().getCity(name);
		if(this.currentCity==null) return false;
		else return true;
	}
	public City getCurrentCity() { return currentCity; }
	public City[] getCities()
	{ return (City[]) MainActivity.getDbManager().getCities().toArray(); }
	
	public void setDescription(String description)
	{
		if(this.currentCity!=null) {
			this.currentCity.setDescription(description);
			MainActivity.getDbManager().update(currentCity);
		}
	}
	
	public void setGPSLoc(Location location)
	{
		if(this.currentCity!=null) {
			this.currentCity.setGPSLoc(location);
			MainActivity.getDbManager().update(currentCity);
		}
	}
	
	public void addPicture(String path)
	{
		if(this.currentCity!=null) {
			this.currentCity.addPicture(path);
			MainActivity.getDbManager().update(currentCity);
		}
	}
	
	public void removePicture(String path)
	{
		if(this.currentCity!=null) {
			this.currentCity.removePicture(path);
			MainActivity.getDbManager().update(currentCity);
		}
	}
	
	public City[] listNearestCities(Location gps)
	{
		City[] sortedCities=getCities();
		int nbCities = sortedCities.length;
		float minDistance;
		int numberCity;
		City curCity;
		float curDistance;
		
		// pour ne devoir les calculer qu'une fois, placons toutes les distances entre les villes
		// et l'utilisateur dans un tableau
		float[] distances= new float[nbCities];
		for(int i=0;i<nbCities;i++)
			distances[i]= GPSHelper.distanceBetween(gps, sortedCities[i].getGPSLoc()); //TODO si on decide de supprimer GPSHelper : gps.distanceTo(sortedCities[i].getGPSLoc());
		
		// il y a nbCities a trier, donc il faut nbCities fois prendre la plus proche
		for(int i=0;i<nbCities-1;i++)
		{
			// il faut chercher la City la plus proche parmis les nbCities-i Cities restantes
			minDistance=distances[i];
			numberCity=i;
			for(int j=i+1;j<nbCities;j++)
			{
				if (distances[j]<minDistance)
				{
					minDistance=distances[j];
					numberCity=j;
				}
			}
			// echangeons de place la ville i et numerCity (la plus proche) dans la liste
			curCity=sortedCities[i];
			sortedCities[i]=sortedCities[numberCity];
			sortedCities[numberCity]=curCity;
			// de meme dans le tableau des distances
			curDistance=distances[i];
			distances[i]=distances[numberCity];
			distances[numberCity]=curDistance;
			
		}
		return sortedCities;
	}
	
	// public void deleteCity(String name) TODO
}