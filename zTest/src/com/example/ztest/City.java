package com.example.ztest;
import java.util.ArrayList;

import android.location.Location;


public class City
{

	private String name; 
	private String description;
	private Location GPSLoc;
	private ArrayList<String> picture = new ArrayList<String>();

	public City(String name, Location GPSLoc, String description) 
	{
		this.name=name;
		this.description=description;
		this.GPSLoc=GPSLoc;
		this.picture=new ArrayList<String>();
	}

	public String getName() { return this.name; }
	public void setName(String name) { this.name=name; }
	public String getDescription() { return this.description; }
	public void setDescription(String description) { this.description=description; }
	public Location getGPSLoc() { return this.GPSLoc; }
	public void setGPSLoc(Location GPSLoc) { this.GPSLoc=GPSLoc; }
	public String[] getPictures() { return (String[]) this.picture.toArray(); }
	public void addPicture(String picture) { this.picture.add(picture); }
	public void removePicture(String picture) { this.picture.remove(picture); }

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		City other = (City) obj;
		if (GPSLoc == null) {
			if (other.getGPSLoc() != null)
				return false;
		} else if (!(GPSLoc.getLatitude() == other.getGPSLoc().getLatitude() && GPSLoc.getLongitude() == other.getGPSLoc().getLongitude()))
			return false;
		return true;
	}

}