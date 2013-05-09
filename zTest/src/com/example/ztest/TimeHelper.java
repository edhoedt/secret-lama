package com.example.ztest;

import java.util.Calendar;

public class TimeHelper
{

	public static boolean isBetween(Calendar begin, Calendar end, Restaurant restaurant)
	{
		
		boolean bool=false;
		// convertissons begin en minute. DAY*24*60 + HOUR*60 + MIN (Sunday=1, Monday=2,...)
		int clock=((begin.get(Calendar.DAY_OF_WEEK)-1)*24*60) + (begin.get(Calendar.HOUR_OF_DAY)*60) + (begin.get(Calendar.MINUTE));
		
		int[][] schedules = restaurant.getSchedules();
		
		// Regardons si le debut est dans une tranche horaire d'ouverture du restaurant, et conservons
		// cette tranche horaire en memoire si elle existe.  Nous pourrons donc verifier ensuite si l'heure
		// de fin (si elle est precisee) est comprise dans la tranche horaire aussi
		int i=0;
		while(!bool && i < schedules[0].length)
		{
			if(schedules[0][i] <= clock && clock <= schedules[1][i])
				bool=true; // l'heure de debut est bien comprise dans les horaires d'ouverture du restaurant
			else
				i++;
		}
		
		if(end!=null && bool) // si l'heure de fin est precisee, verifions qu'elle est aussi dans la tranche horaire
		{
			// convertissons end en minutes
			clock=((end.get(Calendar.DAY_OF_WEEK)-1)*24*60) + (end.get(Calendar.HOUR_OF_DAY)*60) + (end.get(Calendar.MINUTE));

			if(!(schedules[0][i] <= clock && clock <= schedules[1][i]))
				bool=false;

			// cas particulier d'une tranche horaire qui chevauche deux semaines (ex, debut samedi fin dimanche)
			if(schedules[1][i]==10079 || schedules[1][i]==10080) //10080 minutes dans une semaine
				for(int j=0; j < schedules[0].length && !bool; j++) // cherchons s'il n'y a pas une tranche horaire qui commence au tout debut de la semaine
					if ((schedules[0][j] == 0 || schedules[0][j] == 1) && clock <= schedules[1][j])
						bool=true;
		}
		
		return bool;
			
	}
	
	
	public static Calendar addition(Calendar begin, int day, int hours, int minutes)
	{
		Calendar date = (Calendar) begin.clone();
		date.add(Calendar.DAY_OF_MONTH, day);
		date.add(Calendar.HOUR_OF_DAY, hours);
		date.add(Calendar.MINUTE, minutes);
		return date;
	}
	
	public static int[] getDayHourMin(int minutes)
	{
	int[] dayHourMin = new int[3];
	dayHourMin[0]= minutes/1440; //1440 minutes dans une journee
	dayHourMin[1]= (minutes%1440)/60;
	dayHourMin[2]= minutes%60;
	return dayHourMin;
	}

	public static String getDay(int day)
	{
	String[] days = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thurday", "Friday", "Saturday"};
	return days[day];
	}

}
