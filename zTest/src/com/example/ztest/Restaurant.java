package com.example.ztest;

import java.util.ArrayList;
import java.lang.Integer;
public class Restaurant {

	private String number;
	private String name;
	private String description;
	private String numberAddress;
	private String address;
	private City city;
	private boolean accessDisabled;
	private ArrayList<String> seatsType = new ArrayList<String>();
	private ArrayList<Integer> seatsNumber = new ArrayList<Integer>();
	private ArrayList<String> pictures = new ArrayList<String>();
	private ArrayList<String> paymentMethods = new ArrayList<String>();
	private ArrayList<String> types = new ArrayList<String>();
	private ArrayList<Integer> openSchedule = new ArrayList<Integer>();
	private ArrayList<Integer> closeSchedule = new ArrayList<Integer>();
	private int id=0; // un int est suffisant pour tenir plus de 500 ans a raison de plus de 5000 reservations par jour et par restaurant

	//Constructeur
	public Restaurant(String number, String name, String numberAddress, String address, City city) {
		this.number = number;
		this.name = name;
		this.numberAddress = numberAddress;
		this.address = address;
		this.city = city;
		this.accessDisabled=false;
	}

	// Accesseurs.
	String getNumber() { return this.number; }
	String getName() { return this.name; }
	String getDescription() { return this.description; }
	String getNumberAddress() { return this.numberAddress; }
	String getAddress() { return this.address; }
	City getCity() { return this.city; }
	boolean isAccessDisabled() { return this.accessDisabled; }
	String[] getTypeSeats() { return (String[]) this.seatsType.toArray(); }
	int[] getSeatsNumber() 
	{ 
		int length = seatsNumber.size();
		int[] seatsNum = new int[length];
		for(int i=0 ; i<length ; i++)
			seatsNum[i]=seatsNumber.get(i).intValue();
		return seatsNum;

	}
	int getSeatsNumber(String seatsType)
	{
		int seatsNum=0;
		for(int i=0 ; i<this.seatsType.size() ; i++)
			if(seatsType.equals(this.seatsType.get(i))) {
				seatsNum=seatsNumber.get(i).intValue();
				i=this.seatsType.size();}
		return seatsNum;
	}
	String[] getPictures() { return (String[]) this.pictures.toArray(); }
	String[] getPaymentMethods() { return (String[]) this.paymentMethods.toArray(); }
	String[] getTypes() { return (String[]) this.types.toArray(); }
	int[][] getSchedules()
	{
		int size = openSchedule.size() ;
		int[][] schedules = new int[2][size];
		for (int i=0; i<size ;i++)
		{
			schedules[0][i]=openSchedule.get(i).intValue();
			schedules[1][i]=closeSchedule.get(i).intValue();
		}
		return schedules;
	}
	int getId() { return this.id; }

	// Modificateurs.
	void setNumber(String number) { this.number = number; }
	void setName(String name) { this.name = name; }
	void setDescription(String description) { this.description = description; }
	void setNumberAddress(String numberAddress) { this.numberAddress = numberAddress; }
	void setAddress(String address) { this.address = address; }
	void setCity(City city) { this.city = city; }
	void setAccessDisabled(Boolean accessDisabled) { this.accessDisabled = accessDisabled; }
	void addSeats(String type, int number)
	{ 
		int indexOf = seatsType.indexOf(type);
		if(indexOf<0)
		{
			this.seatsType.add(type); 
			this.seatsNumber.add(number);
		}
		else
		{
			seatsNumber.set(indexOf, number);
		}
	}
	void addPictures(String path)
	{ 
		if (pictures.size()==5)
			System.out.println("il y a d�j� le maximum de photos (5)"); //TODO � afficher
		else
			pictures.add(path); 
	}
	void addPaymentMethod(String paymentMethod) { paymentMethods.add(paymentMethod); }
	void addTypes(String type) { this.types.add(type); }
	void deleteSeats(String type, int number)
	{
		int indexOf = seatsType.indexOf(type);
		if(indexOf>=0)
		{
			seatsType.remove(indexOf);
			seatsNumber.remove(indexOf);
		}
	}
	void removePicture(String path) { pictures.remove(path); }
	void removePaymentMethod(String paymentMethod) { paymentMethods.remove(paymentMethod); }
	void removeType(String type) { types.remove(type); }

	void addSchedules(int openDay, int closeDay, int openHour, int closeHour, int openMinute, int closeMinute)
	{
		// convertissons en minutes. DAY*24*60 + HOUR*60 + MIN (Sunday=1, Monday=2,...)
		int openInMinutes = (openDay-1)*24*60 + openHour*60 + openMinute;
		int closeInMinutes = (closeDay-1)*24*60 + closeHour*60 + closeMinute;
		if (openInMinutes >= closeInMinutes) // Si la fermeture est avant l'ouverture, c'est que la tranche horaire contient la nuit
		{										// du samedi au dimanche, il faut donc decomposer en deux tranches horaires
			addSchedules(openDay, 6, openHour, 24, openMinute, 0); // celle de fin de semaine
			addSchedules(0, closeDay, 0, closeHour, 0, closeMinute); // celle de debut de semaine
		}

		// convertissons-les en Integer, que nous utiliserons pour les ajouter aux ArrayList
		Integer openInt = Integer.valueOf(openInMinutes);
		Integer closeInt = Integer.valueOf(closeInMinutes);

		// Regardons si la nouvelle tranche horaire n'en chevauche/englobe pas une autre ni n'est elle-meme englobee
		for (int i=0; i<openSchedule.size(); i++)
		{
			// prenons le debut et la fin de la tranche horaire i
			int openSi = openSchedule.get(i).intValue();
			int closeSi = closeSchedule.get(i).intValue();

			if (openInMinutes < openSi ) 	// si elle (nouvelle tranche horaire) commence avant la tranche horaire i...
			{
				for (int j=i ; j<openSchedule.size() ; j++)
				{
					// prenons le debut et la fin de la tranche horaire j
					int openSj = openSchedule.get(j).intValue();
					int closeSj = closeSchedule.get(j).intValue();

					if (closeInMinutes < openSj) { 			// ...et qu'elle se termine avant la tranche horaire j,
						openSchedule.add(j, openInt);				//  on ajoute une nouvelle tranche horaire.
						closeSchedule.add(j, closeInt);
						j=openSchedule.size();} 					//on quitte la boucle j
					else if (closeInMinutes <= closeSj) {	// ...et qu'elle se termine dans la tranche horaire j,
						openSchedule.set(j, openInt);				// on change le debut de la tranche horaire j
						j=openSchedule.size();} 			   		//on quitte la boucle j
					else {									// ...et qu'elle se termine apres la tranche horaire j,
						openSchedule.remove(j); 					// on supprime la tranche horaire j (puisqu'elle est englobee par la nouvelle)
						closeSchedule.remove(j);
						if(j!=openSchedule.size()-1) {					// si j est la derniere tranche horaire,
							openSchedule.add(openInt);					// on ajoute la nouvelle la nouvelle tranche
							closeSchedule.add(closeInt);}
					}
				}
				i=openSchedule.size();			// on sort de la boucle i
			}

			else if (openInMinutes<=closeSi)  // si elle commence dans la tranche horaire i
			{
				if(!(closeInMinutes <= closeSi))				// et qu'elle ne se termine pas dans la tranche horaire i,
				{												     // auquel cas nous ne faisons rien (puisqu'elle est englobee par une autre) ...
					for (int j=i+1 ; j<openSchedule.size() ; j++)
					{
						// prenons le debut et la fin de la tranche horaire j
						int openSj = openSchedule.get(j).intValue();
						int closeSj = closeSchedule.get(j).intValue();

						if (closeInMinutes < openSj) {			// ...mais qu'elle se termine avant la tranche horaire j,
							closeSchedule.set(i, closeInt);			    // on change la fin de la tranche horaire i
							j=openSchedule.size();} 			    	//on quitte la boucle j
						else if (closeInMinutes <= closeSj) {	// ...mais qu'elle se termine dans la tranche horaire j,
							closeSchedule.set(i, closeSchedule.get(j)); // on change la fin de la tranche horaire i par la fin de j
							openSchedule.remove(j); 					// et on supprime la tranche horaire j
							closeSchedule.remove(j);
							j=openSchedule.size();}						//on quitte la boucle j
						else {									// ...et qu'elle se termine apres la tranche horaire j,
							openSchedule.remove(j); 					// on supprime la tranche horaire j (puisqu'elle est englobee par la nouvelle)
							closeSchedule.remove(j);
							if(j==openSchedule.size()-1)					// si j est la derniere tranche horaire,
								closeSchedule.set(i, closeInt);					// on change la fin de la tranche horaire i
						}
					}
				}
				i=openSchedule.size();		// on sort de la boucle i
			}
			else if (i==openSchedule.size()-1)	// si elle commence apres la derniere tranche horaire
			{
				openSchedule.add(openInt);					// on ajoute la nouvelle tranche horaire
				closeSchedule.add(closeInt);
			}
		}
	}

	void addSchedules (int openMinute, int closeMinute)
	{
		int openDay = openMinute/(60*24) ;
		int closeDay = closeMinute/(60*24) ;
		int openHour = (openMinute % (60*24)) / 60 ;
		int closeHour = (closeMinute % (60*24)) / 60 ;
		int openMin = openMinute % 60 ;
		int closeMin = closeMinute % 60 ;
		addSchedules(openDay, closeDay, openHour, closeHour, openMin, closeMin);
	}
	
	void removeSchedules (int openMinute, int closeMinute)
	{
		int openDay = openMinute/(60*24) ;
		int closeDay = closeMinute/(60*24) ;
		int openHour = (openMinute % (60*24)) / 60 ;
		int closeHour = (closeMinute % (60*24)) / 60 ;
		int openMin = openMinute % 60 ;
		int closeMin = closeMinute % 60 ;
		removeSchedules(openDay, closeDay, openHour, closeHour, openMin, closeMin);
	}
	
	void removeSchedules (int openDay, int closeDay, int openHour, int closeHour, int openMinute, int closeMinute)
	{
		// convertissons en minutes. DAY*24*60 + HOUR*60 + MIN (Sunday=1, Monday=2,...)
		int openInMinutes = (openDay-1)*24*60 + openHour*60 + openMinute;
		int closeInMinutes = (closeDay-1)*24*60 + closeHour*60 + closeMinute;
		if (openInMinutes >= closeInMinutes)// Si la fermeture est avant l'ouverture, c'est que la tranche horaire contient la nuit
		{										// du samedi au dimanche, il faut donc decomposer en deux tranches horaires
			removeSchedules(openDay, 6, openHour, 24, openMinute, 0); // celle de fin de semaine
			removeSchedules(0, closeDay, 0, closeHour, 0, closeMinute); // celle de debut de semaine
		}


		// convertissons-les en Integer, que nous utiliserons pour les ajouter aux ArrayList
		Integer openInt = Integer.valueOf(openInMinutes);
		Integer closeInt = Integer.valueOf(closeInMinutes);

		// Regardons si la tranche horaire de fermeture n'en chevauche/englobe pas une d'ouverture
		for (int i=0; i<openSchedule.size(); i++)
		{
			// prenons le debut et la fin de la tranche horaire i
			int openSi = openSchedule.get(i).intValue();
			int closeSi = closeSchedule.get(i).intValue();

			if (openInMinutes < openSi ) 	// si elle (tranche horaire de fermeture) commence avant la tranche horaire i...
			{
				for (int j=i ; j<openSchedule.size() ; j++)
				{
					// prenons le debut et la fin de la tranche horaire j
					int openSj = openSchedule.get(j).intValue();
					int closeSj = closeSchedule.get(j).intValue();

					if (closeInMinutes < openSj)  			// ...et qu'elle se termine avant la tranche horaire j,
						j=openSchedule.size(); 						//on quitte la boucle j
					else if (closeInMinutes <= closeSj) {	// ...et qu'elle se termine dans la tranche horaire j,
						openSchedule.set(j, closeInt);				// on change le debut de la tranche horaire j
						j=openSchedule.size();} 			   		//on quitte la boucle j
					else {									// ...et qu'elle se termine apres la tranche horaire j,
						openSchedule.remove(j); 					// on supprime la tranche horaire j
						closeSchedule.remove(j);}
				}
				i=openSchedule.size();			// on sort de la boucle i
			}

			else if (openInMinutes<=closeSi)  // si elle commence dans la tranche horaire i
			{
				if(closeInMinutes <= closeSi)				// et qu'elle se termine pas dans la tranche horaire i,
				{
					closeSchedule.add(i+1, closeSchedule.get(i));// on ajoute une tranche horaire i+1 
					openSchedule.add(i+1, closeInt);
					closeSchedule.set(i, openInt);			// on change la fin de la tranche horaire i,
				}
				else										// et qu'elle ne se termine pas dans la tranche horaire i,...
				{
					closeSchedule.set(i, openInt);  		// on change la fin de la tranche horaire i
					for (int j=i+1 ; j<openSchedule.size() ; j++)
					{
						// prenons le debut et la fin de la tranche horaire j
						int openSj = openSchedule.get(j).intValue();
						int closeSj = closeSchedule.get(j).intValue();

						if (closeInMinutes < openSj) {			// ...mais qu'elle se termine avant la tranche horaire j,
							j=openSchedule.size();} 			    	//on quitte la boucle j
						else if (closeInMinutes <= closeSj) {	// ...mais qu'elle se termine dans la tranche horaire j,
							openSchedule.set(i, closeInt);				 // on change le debut de la tranche horaire j
							j=openSchedule.size();}						//on quitte la boucle j
						else {									// ...mais qu'elle se termine apres la tranche horaire j,
							openSchedule.remove(j); 					// on supprime la tranche horaire j
							closeSchedule.remove(j);
						}
					}
				}
				i=openSchedule.size();		// on sort de la boucle i
			}
		}
	}

	void setId(int newId) { id = newId; }

	/* Redef. de la methode equals et hashCode
	 * Deux restos sont identiques si ils partagent le meme numero de telephone.
	 */

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((number == null) ? 0 : number.hashCode());
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
		Restaurant other = (Restaurant) obj;
		if (number == null) {
			if (other.getNumber() != null)
				return false;
		} else if (!number.equals(other.getNumber()))
			return false;
		return true;
	}


}