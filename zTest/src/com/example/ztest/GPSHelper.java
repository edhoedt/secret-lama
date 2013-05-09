package com.example.ztest;

import android.location.Location;
//TODO Modification diagramme de classe

// gerer le bazard avec les getLongitute et getLatitude
public class GPSHelper
{

	// public Location getLocation() // Fonction inutile !!!

	public static float distanceBetween(Location loc, Location remoteloc) // En soit, pas necessaire: fonction existe telle quelle!
	{
		return loc.distanceTo(remoteloc);
	}
	
	
}