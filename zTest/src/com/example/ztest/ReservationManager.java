package com.example.ztest;


import java.util.ArrayList;
import java.util.Calendar;

public class ReservationManager {

	private Reservation currentReservation;


	public ReservationManager() {}

	public void addReservation(Restaurant restaurant, User user, Calendar date, String seatsType, int seatsNumber)
	{
		if(isAvailable(restaurant, date, seatsType, seatsNumber))
		{
			Reservation reservation = new Reservation(restaurant.getId()+1, date, seatsType, seatsNumber, restaurant, user);
			restaurant.setId(restaurant.getId()+1);
			MainActivity.getDbManager().update(reservation);
		}
		else
			System.out.println("Plus de places disponibles"); //TODO implementer erreur-affichage
	}

	public void addMeal (Meal meal, int number)
	{ 
		if(this.currentReservation!=null) {
			this.currentReservation.addMeal(meal, number);
			MainActivity.getDbManager().update(currentReservation);}
	} 

	public void addMenu(Menu menu, int number)
	{ 
		if(this.currentReservation!=null) {
			this.currentReservation.addMenu(menu, number);
			MainActivity.getDbManager().update(currentReservation);}
	} 
	/*public Reservation[] getReservations(User user)
	{
		ArrayList<Reservation> reservationsOfUser = new ArrayList<Reservation>();
		Reservation curRes;
		for(int i=0; i< reservations.size(); i++)
		{
			curRes=reservations.get(i);
			if (user.equals(curRes.getUser()))
				reservationsOfUser.add(curRes);
		}
		return (Reservation[]) reservationsOfUser.toArray();
	}

	public Reservation[] getReservations(Restaurant restaurant, Calendar date)
	{
		ArrayList<Reservation> reservationsOfRestaurant = new ArrayList<Reservation>();
		Reservation curRes;
		if(date!=null)
		{
			for(int i=0; i< reservations.size(); i++)
			{
				curRes=reservations.get(i);
				// prenons toutes les reservation entre 1h30 avant et 1h30 apres 'date' (soit 5400000 millisecondes)
				if (restaurant.equals(curRes.getRestaurant()) && date.getTimeInMillis()-(long)5400000 < curRes.getDate().getTimeInMillis() && date.getTimeInMillis()+(long)5400000 > curRes.getDate().getTimeInMillis())
					reservationsOfRestaurant.add(curRes);
			}
		}
		else
		{
			for(int i=0; i< reservations.size(); i++)
			{
				curRes=reservations.get(i);
				if (restaurant.equals(curRes.getRestaurant()))
					reservationsOfRestaurant.add(curRes);
			}
		}

		return (Reservation[]) reservationsOfRestaurant.toArray();
	}*/

	public boolean isAvailable(Restaurant restaurant, Calendar date, String seatsType, int numberOfSeats)
	{
		// Regardons si le restaurant est ouvert
		Calendar dateEnd = TimeHelper.addition(date, 0, 1, 30); // Nous comptons 1h30 de repas
		if (!TimeHelper.isBetween(date, dateEnd, restaurant))
		{
			System.out.println("le restaurant est ferm�"); //TODO afficher a l'ecran
			return false;
		}

		// Regardons s'il y a assez de places
		//Reservation[] reservations=MainActivity.getDbManager().getReservations(restaurant, date);
		Reservation[] reservations=null; //TODO
		int placeTaken=0;
		if (seatsType!=null)
		{
			for (int i=0 ; i<reservations.length ; i++)
			{
				if(seatsType.equals(reservations[i].getSeatsType()))
					placeTaken+=reservations[i].getSeatsNumber();
			}
			if (restaurant.getSeatsNumber(seatsType)-placeTaken >= numberOfSeats)
			{
				return true;
			}
			System.out.println("il n'y a plus assez de places disponibles"); //TODO afficher a l'ecran
			return false;
		}

		else // pas de preference sur le type de place
		{
			for (int i=0 ; i<reservations.length ; i++)
				placeTaken+=reservations[i].getSeatsNumber();

			int[] seatsNumber = restaurant.getSeatsNumber();
			int totSeatsNumber=0;
			for(int i=0; i<seatsNumber.length ; i++)
				totSeatsNumber += seatsNumber[i];
			if (totSeatsNumber-placeTaken >= numberOfSeats) //TODO je peux aussi plutot implementer une fonction qui regarde s'il y a assez de place dans l'un des types de place
				return true;
			System.out.println("il n'y a plus assez de places disponibles"); //TODO afficher a l'ecran
			return false;
		}
	}

	/* Plus besoin du coup, avec le nouveau syst�me ?!
	 * public void loadAllReservations()
	{
		//TODO mettre les reservation en cache
	}*/

	// public void deleteReservation(Reservation reservation){} TODO


}