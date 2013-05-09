package com.example.ztest;

import java.util.ArrayList;
import java.util.Calendar;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.location.Location;
import android.util.Log;

public class DbManager{

	private static final String DB_NAME = "gourmet";

	private DatabaseHelper DBHelper;
	private SQLiteDatabase db;
	private Context context;

	public DbManager(Context context)
	{
		this.context=context;
		DBHelper = new DatabaseHelper(this.context);
		db = DBHelper.getWritableDatabase();
	}

	private class DatabaseHelper extends SQLiteOpenHelper
	{

		public DatabaseHelper(Context context) {
			super(context, DB_NAME, null, 1); 
		}



		public void onCreate(SQLiteDatabase db) {
			try{
				//AgendaResto
				db.execSQL("DROP TABLE IF EXISTS \"AgendaResto\";");
				db.execSQL("CREATE TABLE \"AgendaResto\" (\"numResto\" TEXT NOT NULL REFERENCES RESTAURANT (\"num\") , \"openMinute\" INTEGER, \"closedMinute\" INTEGER, PRIMARY KEY (\"numResto\",\"openMinute\",\"closedMinute\"));");
				db.execSQL("INSERT INTO \"AgendaResto\" VALUES('010451585',0,60);");
				db.execSQL("INSERT INTO \"AgendaResto\" VALUES('010451585',540,1500);");
				db.execSQL("INSERT INTO \"AgendaResto\" VALUES('010451585',1980,2940);");
				db.execSQL("INSERT INTO \"AgendaResto\" VALUES('010451585',3420,4380);");
				db.execSQL("INSERT INTO \"AgendaResto\" VALUES('010451585',4860,5820);");
				db.execSQL("INSERT INTO \"AgendaResto\" VALUES('010451585',6300,7260);");
				db.execSQL("INSERT INTO \"AgendaResto\" VALUES('010451585',7740,8700);");
				db.execSQL("INSERT INTO \"AgendaResto\" VALUES('010451585',9180,10080);");


				//CITY
				db.execSQL("DROP TABLE IF EXISTS \"CITY\";");
				db.execSQL("CREATE TABLE \"CITY\" (\"name\" TEXT PRIMARY KEY  NOT NULL , \"descr\" TEXT, \"locationGPSlong\" DOUBLE, \"locationGPSlat\" DOUBLE);");
				db.execSQL("INSERT INTO \"CITY\" VALUES('Louvain-La-Neuve',\"Louvain-la-Neuve est une section de la commune belge d'Ottignies-Louvain-la-Neuve, situ�e en R�gion wallonne dans la province du Brabant wallon. Fond�e au d�but des ann�es 1970 � la suite de la scission de l'Universit� catholique de Louvain entre sa partie n�erlandophone et sa partie francophone, ''LLN'' est donc le si�ge principal de l'Universit� Catholique de Louvain (UCL) qui diff�re donc aujourd'hui de la Katholieke Universiteit Leuven (KUL). (from :  Wikipedia)\",50.66,4.61);");

				//FavouriteIngredient
				db.execSQL("DROP TABLE IF EXISTS \"FavouriteIngredient\";");
				db.execSQL("CREATE TABLE \"FavouriteIngredient\" (\"numUser\" TEXT NOT NULL REFERENCES USER (\"num\") , \"ingredient\" TEXT NOT NULL, PRIMARY KEY (\"numUser\", \"ingredient\"));");
				db.execSQL("INSERT INTO \"FavouriteIngredient\" VALUES('0497407447','chocolat');");
				db.execSQL("INSERT INTO \"FavouriteIngredient\" VALUES('0497407447','cr�pe');");
				db.execSQL("INSERT INTO \"FavouriteIngredient\" VALUES('0497407447','pizza');");
				db.execSQL("INSERT INTO \"FavouriteIngredient\" VALUES('0499123456','sucre');");
				db.execSQL("INSERT INTO \"FavouriteIngredient\" VALUES('0499123456',\"poussi�re d'�toile\");");
				db.execSQL("INSERT INTO \"FavouriteIngredient\" VALUES('0499123456','champignon');");
				db.execSQL("INSERT INTO \"FavouriteIngredient\" VALUES('0496660666','p�che');");
				db.execSQL("INSERT INTO \"FavouriteIngredient\" VALUES('0496660666','braise');");
				db.execSQL("INSERT INTO \"FavouriteIngredient\" VALUES('0496660666','Rhum');");
				db.execSQL("INSERT INTO \"FavouriteIngredient\" VALUES('0496660666','brochette');");
				db.execSQL("INSERT INTO \"FavouriteIngredient\" VALUES('0475696969','algues');");
				db.execSQL("INSERT INTO \"FavouriteIngredient\" VALUES('0475696969','salade');");
				db.execSQL("INSERT INTO \"FavouriteIngredient\" VALUES('0475696969','poulpe');");


				//FavouriteResto
				db.execSQL("DROP TABLE IF EXISTS \"FavouriteResto\";");
				db.execSQL("CREATE TABLE \"FavouriteResto\" (\"numUser\" TEXT NOT NULL  REFERENCES USER (\"num\"), \"numResto\" TEXT NOT NULL REFERENCES RESTAURANT (\"num\") , PRIMARY KEY (\"numUser\", \"numResto\"));");
				db.execSQL("INSERT INTO \"FavouriteResto\" VALUES('0496660666','010451585');");
				db.execSQL("INSERT INTO \"FavouriteResto\" VALUES('11','010451585');");

				//FavouriteTypeResto
				db.execSQL("DROP TABLE IF EXISTS \"FavouriteTypeResto\";");
				db.execSQL("CREATE TABLE \"FavouriteTypeResto\" (\"numUser\" TEXT NOT NULL  REFERENCES USER (\"num\"), \"typeResto\" TEXT NOT NULL REFERENCES TypeResto (\"typeResto\") , PRIMARY KEY (\"numUser\", \"typeResto\"));");
				db.execSQL("INSERT INTO \"FavouriteTypeResto\" VALUES('0497407447','Chinois');");
				db.execSQL("INSERT INTO \"FavouriteTypeResto\" VALUES('0499123456','Brasserie');");
				db.execSQL("INSERT INTO \"FavouriteTypeResto\" VALUES('0499123456','Fastfood');");
				db.execSQL("INSERT INTO \"FavouriteTypeResto\" VALUES('0496660666','Cr�perie');");
				db.execSQL("INSERT INTO \"FavouriteTypeResto\" VALUES('0475696969','Atlante');");
				db.execSQL("INSERT INTO \"FavouriteTypeResto\" VALUES('0475696969','Belge');");


				//HatedIngredient
				db.execSQL("DROP TABLE IF EXISTS \"HatedIngredient\";");
				db.execSQL("CREATE TABLE \"HatedIngredient\" (\"numUser\" TEXT NOT NULL REFERENCES USER (\"num\") , \"ingredient\" TEXT NOT NULL, PRIMARY KEY (\"numUser\", \"ingredient\"));");
				db.execSQL("INSERT INTO \"HatedIngredient\" VALUES('0497407447','th�');");
				db.execSQL("INSERT INTO \"HatedIngredient\" VALUES('0499123456','porc');");
				db.execSQL("INSERT INTO \"HatedIngredient\" VALUES('0499123456','boeuf');");
				db.execSQL("INSERT INTO \"HatedIngredient\" VALUES('0499123456','f�e');");
				db.execSQL("INSERT INTO \"HatedIngredient\" VALUES('0496660666','salade');");
				db.execSQL("INSERT INTO \"HatedIngredient\" VALUES('0496660666','eau');");
				db.execSQL("INSERT INTO \"HatedIngredient\" VALUES('0475696969','poisson');");
				db.execSQL("INSERT INTO \"HatedIngredient\" VALUES('0475696969','hu�tre');");
				db.execSQL("INSERT INTO \"HatedIngredient\" VALUES('0475696969','moule');");
				db.execSQL("INSERT INTO \"HatedIngredient\" VALUES('0475696969','aubergine');");


				//HatedResto
				db.execSQL("DROP TABLE IF EXISTS \"HatedResto\";");
				db.execSQL("CREATE TABLE \"HatedResto\" (\"numUser\" TEXT NOT NULL  REFERENCES USER (\"num\"), \"numResto\" TEXT NOT NULL REFERENCES RESTAURANT (\"num\") , PRIMARY KEY (\"numUser\", \"numResto\"));");


				//HatedTypeResto
				db.execSQL("DROP TABLE IF EXISTS \"HatedTypeResto\";");
				db.execSQL("CREATE TABLE \"HatedTypeResto\" (\"numUser\" TEXT NOT NULL  REFERENCES USER (\"num\"), \"typeResto\" TEXT NOT NULL REFERENCES TypeResto (\"typeResto\") , PRIMARY KEY (\"numUser\", \"typeResto\"));");
				db.execSQL("INSERT INTO \"HatedTypeResto\" VALUES('0497407447','Fastfood');");
				db.execSQL("INSERT INTO \"HatedTypeResto\" VALUES('0499123456','Pirate');");
				db.execSQL("INSERT INTO \"HatedTypeResto\" VALUES('0499123456','Italien');");
				db.execSQL("INSERT INTO \"HatedTypeResto\" VALUES('0496660666','Sain');");
				db.execSQL("INSERT INTO \"HatedTypeResto\" VALUES('0496660666','Belge');");


				//IngredientMeal
				db.execSQL("DROP TABLE IF EXISTS \"IngredientMeal\";");
				db.execSQL("CREATE TABLE \"IngredientMeal\" (\"nameMeal\" TEXT NOT NULL, \"numResto\" TEXT NOT NULL, \"ingredient\" TEXT NOT NULL , PRIMARY KEY (\"nameMeal\", \"numResto\", \"ingredient\"), constraint FK_IngredientMeal_MEAL FOREIGN KEY (\"nameMeal\",\"numResto\") references MEAL (\"nameMeal\", \"numResto\"));");
				db.execSQL("INSERT INTO \"IngredientMeal\" VALUES('Caf�','010451585','caf�');");
				db.execSQL("INSERT INTO \"IngredientMeal\" VALUES('Chocolat chaud classique','010451585','cacao');");
				db.execSQL("INSERT INTO \"IngredientMeal\" VALUES('Chocolat chaud classique','010451585','lait');");
				db.execSQL("INSERT INTO \"IngredientMeal\" VALUES('Chocolat chaud classique','010451585','sucre');");
				db.execSQL("INSERT INTO \"IngredientMeal\" VALUES('Cr�pe au chocolat','010451585','chocolat');");
				db.execSQL("INSERT INTO \"IngredientMeal\" VALUES('Cr�pe au chocolat','010851545','cr�pe');");
				db.execSQL("INSERT INTO \"IngredientMeal\" VALUES('Cr�pe au sucre de canne blond','010451585','sucre blond');");
				db.execSQL("INSERT INTO \"IngredientMeal\" VALUES('Cr�pe au sucre de canne blond','010851545','cr�pe');");
				db.execSQL("INSERT INTO \"IngredientMeal\" VALUES('Cr�pe au supr�me de faisan au miel et au thym','010451585','faisan');");
				db.execSQL("INSERT INTO \"IngredientMeal\" VALUES('Cr�pe au supr�me de faisan au miel et au thym','010451585','miel');");
				db.execSQL("INSERT INTO \"IngredientMeal\" VALUES('Cr�pe au supr�me de faisan au miel et au thym','010451585','sel');");
				db.execSQL("INSERT INTO \"IngredientMeal\" VALUES('Cr�pe au supr�me de faisan au miel et au thym','010451585','thym');");
				db.execSQL("INSERT INTO \"IngredientMeal\" VALUES('Cr�pe au supr�me de faisan au miel et au thym','010851545','cr�pe');");
				db.execSQL("INSERT INTO \"IngredientMeal\" VALUES(\"Cr�pe au supr�me de faisan au poivre vert, sauce � l'�chalotte\",'010451585','faisan');");
				db.execSQL("INSERT INTO \"IngredientMeal\" VALUES(\"Cr�pe au supr�me de faisan au poivre vert, sauce � l'�chalotte\",'010451585','poivre vert');");
				db.execSQL("INSERT INTO \"IngredientMeal\" VALUES(\"Cr�pe au supr�me de faisan au poivre vert, sauce � l'�chalotte\",'010451585','sel');");
				db.execSQL("INSERT INTO \"IngredientMeal\" VALUES(\"Cr�pe au supr�me de faisan au poivre vert, sauce � l'�chalotte\",'010451585','�chalotte');");
				db.execSQL("INSERT INTO \"IngredientMeal\" VALUES(\"Cr�pe au supr�me de faisan au poivre vert, sauce � l'�chalotte\",'010851545','cr�pe');");
				db.execSQL("INSERT INTO \"IngredientMeal\" VALUES('Th� nature','010451585','th�');");
				db.execSQL("INSERT INTO \"IngredientMeal\" VALUES('Salade Verte','010451585','salade');");
				db.execSQL("INSERT INTO \"IngredientMeal\" VALUES('Salade Verte','010451585','oignon');");
				db.execSQL("INSERT INTO \"IngredientMeal\" VALUES('Salade Verte','010451585','ma�s');");
				db.execSQL("INSERT INTO \"IngredientMeal\" VALUES('Salade Verte','010451585','vinaigrette � la framboise');");
				db.execSQL("INSERT INTO \"IngredientMeal\" VALUES('Salade aux haricots-beurre','010451585','salade');");
				db.execSQL("INSERT INTO \"IngredientMeal\" VALUES('Salade aux haricots-beurre','010451585','oeuf dur');");
				db.execSQL("INSERT INTO \"IngredientMeal\" VALUES('Salade aux haricots-beurre','010451585','lardons');");
				db.execSQL("INSERT INTO \"IngredientMeal\" VALUES('Salade aux haricots-beurre','010451585','tomate');");
				db.execSQL("INSERT INTO \"IngredientMeal\" VALUES('Salade aux haricots-beurre','010451585','haricot');");
				db.execSQL("INSERT INTO \"IngredientMeal\" VALUES('Salade aux haricots-beurre','010451585','mayonnaise');");
				db.execSQL("INSERT INTO \"IngredientMeal\" VALUES(\"Cr�pe � l'oeuf\",'010451585','oeuf');");
				db.execSQL("INSERT INTO \"IngredientMeal\" VALUES(\"Cr�pe � l'oeuf\",'010451585','cr�pe');");
				db.execSQL("INSERT INTO \"IngredientMeal\" VALUES(\"Cr�pe � l'oeuf aux anchois\",'010451585','cr�pe');");
				db.execSQL("INSERT INTO \"IngredientMeal\" VALUES(\"Cr�pe � l'oeuf aux anchois\",'010451585','oeuf');");
				db.execSQL("INSERT INTO \"IngredientMeal\" VALUES(\"Cr�pe � l'oeuf aux anchois\",'010451585','anchois');");
				db.execSQL("INSERT INTO \"IngredientMeal\" VALUES('Cr�pe La Vacherie','010451585','oeuf');");
				db.execSQL("INSERT INTO \"IngredientMeal\" VALUES('Cr�pe La Vacherie','010451585','cr�me de Comt�');");
				db.execSQL("INSERT INTO \"IngredientMeal\" VALUES('Cr�pe La Vacherie','010451585','Gruy�re');");
				db.execSQL("INSERT INTO \"IngredientMeal\" VALUES('Cr�pe La Vacherie','010451585','Emmenthal');");
				db.execSQL("INSERT INTO \"IngredientMeal\" VALUES('Cr�pe de Calabre','010451585','oeuf');");
				db.execSQL("INSERT INTO \"IngredientMeal\" VALUES('Cr�pe de Calabre','010451585','Pancetta');");
				db.execSQL("INSERT INTO \"IngredientMeal\" VALUES('Cr�pe de Calabre','010451585','Parmesan');");
				db.execSQL("INSERT INTO \"IngredientMeal\" VALUES('Cr�pe de Calabre','010451585','tomates s�ch�es');");
				db.execSQL("INSERT INTO \"IngredientMeal\" VALUES('Cr�pe de Calabre','010451585','olives');");
				db.execSQL("INSERT INTO \"IngredientMeal\" VALUES('Cr�pe du Lavandou','010451585','poivrons');");
				db.execSQL("INSERT INTO \"IngredientMeal\" VALUES('Cr�pe du Lavandou','010451585','courgettes');");
				db.execSQL("INSERT INTO \"IngredientMeal\" VALUES('Cr�pe du Lavandou','010451585','aubergines');");
				db.execSQL("INSERT INTO \"IngredientMeal\" VALUES('Cr�pe du Lavandou','010451585','tomates');");
				db.execSQL("INSERT INTO \"IngredientMeal\" VALUES('Cr�pe du Lavandou','010451585',\"pointe d'ail\");");
				db.execSQL("INSERT INTO \"IngredientMeal\" VALUES('Cr�pe Florentine aux �pinards','010451585',\"cr�me d'olives\");");
				db.execSQL("INSERT INTO \"IngredientMeal\" VALUES('Cr�pe Florentine aux �pinards','010451585','�pinards');");
				db.execSQL("INSERT INTO \"IngredientMeal\" VALUES('Cr�pe Florentine aux �pinards','010451585','cr�me');");
				db.execSQL("INSERT INTO \"IngredientMeal\" VALUES('Cr�pe aux choux de Bruxelles et aux ch�taignes','010451585','cr�pe');");
				db.execSQL("INSERT INTO \"IngredientMeal\" VALUES('Cr�pe aux choux de Bruxelles et aux ch�taignes','010451585','choux de Bruxelles');");
				db.execSQL("INSERT INTO \"IngredientMeal\" VALUES('Cr�pe aux choux de Bruxelles et aux ch�taignes','010451585','ch�taignes');");
				db.execSQL("INSERT INTO \"IngredientMeal\" VALUES(\"Cr�pe aux scampis � l'ail\",'010451585','cr�pe');");
				db.execSQL("INSERT INTO \"IngredientMeal\" VALUES(\"Cr�pe aux scampis � l'ail\",'010451585','scampis');");
				db.execSQL("INSERT INTO \"IngredientMeal\" VALUES(\"Cr�pe aux scampis � l'ail\",'010451585',\"ail\");");
				db.execSQL("INSERT INTO \"IngredientMeal\" VALUES('Cr�pe au filet de saumon Proven�ale','010451585','cr�pe');");
				db.execSQL("INSERT INTO \"IngredientMeal\" VALUES('Cr�pe au filet de saumon Proven�ale','010451585','saumon');");
				db.execSQL("INSERT INTO \"IngredientMeal\" VALUES('Cr�pe aux morilles � la cr�me','010451585','cr�pe');");
				db.execSQL("INSERT INTO \"IngredientMeal\" VALUES('Cr�pe aux morilles � la cr�me','010451585','morilles');");
				db.execSQL("INSERT INTO \"IngredientMeal\" VALUES('Cr�pe aux morilles � la cr�me','010451585','cr�me');");
				db.execSQL("INSERT INTO \"IngredientMeal\" VALUES('Cr�pe au boeuf Bon Enfant','010451585','cr�pe');");
				db.execSQL("INSERT INTO \"IngredientMeal\" VALUES('Cr�pe au boeuf Bon Enfant','010451585','oignons');");
				db.execSQL("INSERT INTO \"IngredientMeal\" VALUES('Cr�pe au boeuf Bon Enfant','010451585','tomates');");
				db.execSQL("INSERT INTO \"IngredientMeal\" VALUES('Cr�pe au boeuf Bon Enfant','010451585','cr�me');");
				db.execSQL("INSERT INTO \"IngredientMeal\" VALUES('Cr�pe au boeuf Bon Enfant','010451585','thym');");
				db.execSQL("INSERT INTO \"IngredientMeal\" VALUES('La brochette maison de boeuf,servie avec riz et sauce proven�ale','010451585','brochette');");
				db.execSQL("INSERT INTO \"IngredientMeal\" VALUES('La brochette maison de boeuf,servie avec riz et sauce proven�ale','010451585','boeuf');");
				db.execSQL("INSERT INTO \"IngredientMeal\" VALUES('La brochette maison de boeuf,servie avec riz et sauce proven�ale','010451585','riz');");
				db.execSQL("INSERT INTO \"IngredientMeal\" VALUES('La brochette maison de boeuf,servie avec riz et sauce proven�ale','010451585','sauce proven�ale');");
				db.execSQL("INSERT INTO \"IngredientMeal\" VALUES(\"Cr�pe � l'agneau � la Kriek\",'010451585','cr�pe');");
				db.execSQL("INSERT INTO \"IngredientMeal\" VALUES(\"Cr�pe � l'agneau � la Kriek\",'010451585','agneau');");
				db.execSQL("INSERT INTO \"IngredientMeal\" VALUES(\"Cr�pe � l'agneau � la Kriek\",'010451585','Kriek');");
				db.execSQL("INSERT INTO \"IngredientMeal\" VALUES('Cr�pe au poulet aux pistaches et au cumin','010451585','cr�pe');");
				db.execSQL("INSERT INTO \"IngredientMeal\" VALUES('Cr�pe au poulet aux pistaches et au cumin','010451585','poulet');");
				db.execSQL("INSERT INTO \"IngredientMeal\" VALUES('Cr�pe au poulet aux pistaches et au cumin','010451585','pistaches');");
				db.execSQL("INSERT INTO \"IngredientMeal\" VALUES('Cr�pe au poulet aux pistaches et au cumin','010451585','cumin');");
				db.execSQL("INSERT INTO \"IngredientMeal\" VALUES(\"Cr�pe aux aiguillettes de canard aux poires et sirop d'�rable\",'010451585','cr�pe');");
				db.execSQL("INSERT INTO \"IngredientMeal\" VALUES(\"Cr�pe aux aiguillettes de canard aux poires et sirop d'�rable\",'010451585','canard');");
				db.execSQL("INSERT INTO \"IngredientMeal\" VALUES(\"Cr�pe aux aiguillettes de canard aux poires et sirop d'�rable\",'010451585','poires');");
				db.execSQL("INSERT INTO \"IngredientMeal\" VALUES(\"Cr�pe aux aiguillettes de canard aux poires et sirop d'�rable\",'010451585',\"sirop d'�rable\");");
				db.execSQL("INSERT INTO \"IngredientMeal\" VALUES(\"Cr�pe au sirop d'�rable\",'010451585','cr�pe');");
				db.execSQL("INSERT INTO \"IngredientMeal\" VALUES(\"Cr�pe au sirop d'�rable\",'010451585',\"sirop d'�rable\");");
				db.execSQL("INSERT INTO \"IngredientMeal\" VALUES(\"Cr�pe au miel de montagne\",'010451585','cr�pe');");
				db.execSQL("INSERT INTO \"IngredientMeal\" VALUES(\"Cr�pe au miel de montagne\",'010451585','miel');");
				db.execSQL("INSERT INTO \"IngredientMeal\" VALUES(\"Cr�pe � la confiture d'orange\",'010451585','cr�pe');");
				db.execSQL("INSERT INTO \"IngredientMeal\" VALUES(\"Cr�pe � la confiture d'orange\",'010451585','confiture');");
				db.execSQL("INSERT INTO \"IngredientMeal\" VALUES(\"Cr�pe � la confiture d'orange\",'010451585','orange');");
				db.execSQL("INSERT INTO \"IngredientMeal\" VALUES('Cr�pe � la glace Tiramis�','010451585','cr�pe');");
				db.execSQL("INSERT INTO \"IngredientMeal\" VALUES('Cr�pe � la glace Tiramis�','010451585','glace');");
				db.execSQL("INSERT INTO \"IngredientMeal\" VALUES('Cr�pe � la glace Tiramis�','010451585','Tiramis�');");
				db.execSQL("INSERT INTO \"IngredientMeal\" VALUES('Cr�pe Com�die-Fran�aise','010451585','cr�pe');");
				db.execSQL("INSERT INTO \"IngredientMeal\" VALUES('Cr�pe Com�die-Fran�aise','010451585','glace vanille');");
				db.execSQL("INSERT INTO \"IngredientMeal\" VALUES('Cr�pe Com�die-Fran�aise','010451585','Grand-Marnier');");
				db.execSQL("INSERT INTO \"IngredientMeal\" VALUES('Cr�pe Ann�es 1900','010451585','cr�pe');");
				db.execSQL("INSERT INTO \"IngredientMeal\" VALUES('Cr�pe Ann�es 1900','010451585','poires');");
				db.execSQL("INSERT INTO \"IngredientMeal\" VALUES('Cr�pe Ann�es 1900','010451585','pommes');");
				db.execSQL("INSERT INTO \"IngredientMeal\" VALUES('Cr�pe Ann�es 1900','010451585','beurre');");
				db.execSQL("INSERT INTO \"IngredientMeal\" VALUES('Cr�pe Ann�es 1900','010451585','glace amande');");
				db.execSQL("INSERT INTO \"IngredientMeal\" VALUES('Cr�pe Ann�es 1900','010451585','Amaretto');");



				//MEAL
				db.execSQL("DROP TABLE IF EXISTS \"MEAL\";");
				db.execSQL("CREATE TABLE \"MEAL\" (\"name\" TEXT NOT NULL , \"numResto\" TEXT NOT NULL REFERENCES RESTAURANT (\"num\") , \"price\" DOUBLE, \"picture\" TEXT, \"available\" BOOL, PRIMARY KEY (\"name\", \"numResto\"));");
				db.execSQL("INSERT INTO \"MEAL\" VALUES(\"Cr�pe au supr�me de faisan au poivre vert, sauce � l'�chalotte\",'010451585',14.65,NULL,'true');");
				db.execSQL("INSERT INTO \"MEAL\" VALUES('Cr�pe au supr�me de faisan au miel et au thym','010451585',14.65,NULL,'true');");
				db.execSQL("INSERT INTO \"MEAL\" VALUES('Cr�pe au chocolat','010451585',2.9,NULL,'true');");
				db.execSQL("INSERT INTO \"MEAL\" VALUES('Cr�pe au sucre de canne blond','010451585',2.9,NULL,'true');");
				db.execSQL("INSERT INTO \"MEAL\" VALUES('Th� nature','010451585',2.4,NULL,'false');");
				db.execSQL("INSERT INTO \"MEAL\" VALUES('Chocolat chaud classique','010451585',2.35,NULL,'true');");
				db.execSQL("INSERT INTO \"MEAL\" VALUES('Caf�','010451585',2.35,NULL,'true');");
				db.execSQL("INSERT INTO \"MEAL\" VALUES('Salade Verte','010451585',7.90,NULL,'true');");
				db.execSQL("INSERT INTO \"MEAL\" VALUES('Salade aux haricots-beurre','010451585',13.55,NULL,'true');");
				db.execSQL("INSERT INTO \"MEAL\" VALUES(\"Cr�pe � l'oeuf\",'010451585',4.70,NULL,'true');");
				db.execSQL("INSERT INTO \"MEAL\" VALUES(\"Cr�pe � l'oeuf aux anchois\",'010451585',8.10,NULL,'true');");
				db.execSQL("INSERT INTO \"MEAL\" VALUES('Cr�pe La Vacherie','010451585',11.20,NULL,'true');");
				db.execSQL("INSERT INTO \"MEAL\" VALUES('Cr�pe de Calabre','010451585',12.10,NULL,'true');");
				db.execSQL("INSERT INTO \"MEAL\" VALUES('Cr�pe du Lavandou','010451585',11.15,NULL,'true');");
				db.execSQL("INSERT INTO \"MEAL\" VALUES('Cr�pe Florentine aux �pinards','010451585',11.95,NULL,'true');");
				db.execSQL("INSERT INTO \"MEAL\" VALUES('Cr�pe aux choux de Bruxelles et aux ch�taignes','010451585',10.45,NULL,'true');");
				db.execSQL("INSERT INTO \"MEAL\" VALUES(\"Cr�pe aux scampis � l'ail\",'010451585',17.75,NULL,'true');");
				db.execSQL("INSERT INTO \"MEAL\" VALUES('Cr�pe au filet de saumon Proven�ale','010451585',16.75,NULL,'true');");
				db.execSQL("INSERT INTO \"MEAL\" VALUES('Cr�pe aux morilles � la cr�me','010451585',22.70,NULL,'true');");
				db.execSQL("INSERT INTO \"MEAL\" VALUES('Cr�pe au boeuf Bon Enfant','010451585',17.75,NULL,'true');");
				db.execSQL("INSERT INTO \"MEAL\" VALUES('La brochette maison de boeuf,servie avec riz et sauce proven�ale','010451585',18.20,NULL,'true');");
				db.execSQL("INSERT INTO \"MEAL\" VALUES(\"Cr�pe � l'agneau � la Kriek\",'010451585',18.65,NULL,'true');");
				db.execSQL("INSERT INTO \"MEAL\" VALUES('Cr�pe au poulet aux pistaches et au cumin','010451585',18.25,NULL,'true');");
				db.execSQL("INSERT INTO \"MEAL\" VALUES(\"Cr�pe aux aiguillettes de canard aux poires et sirop d'�rable\",'010451585',19.50,NULL,'true');");
				db.execSQL("INSERT INTO \"MEAL\" VALUES(\"Cr�pe au sirop d'�rable\",'010451585',3.95,NULL,'true');");
				db.execSQL("INSERT INTO \"MEAL\" VALUES('Cr�pe au miel de montagne','010451585',3.15,NULL,'true');");
				db.execSQL("INSERT INTO \"MEAL\" VALUES(\"Cr�pe � la confiture d'orange\",'010451585',2.90,NULL,'true');");
				db.execSQL("INSERT INTO \"MEAL\" VALUES('Cr�pe � la glace Tiramis�','010451585',6.10,NULL,'true');");
				db.execSQL("INSERT INTO \"MEAL\" VALUES('Cr�pe Com�die-Fran�aise','010451585',8.95,NULL,'true');");
				db.execSQL("INSERT INTO \"MEAL\" VALUES('Cr�pe Ann�es 1900','010451585',10.60,NULL,'true');");



				//MENU
				db.execSQL("DROP TABLE IF EXISTS \"MENU\";");
				db.execSQL("CREATE TABLE \"MENU\" (\"name\" TEXT NOT NULL , \"numResto\" TEXT NOT NULL REFERENCES RESTAURANT (\"num\") , \"price\" DOUBLE, PRIMARY KEY (\"name\", \"numResto\"));");
				db.execSQL("INSERT INTO \"MENU\" VALUES('Petit-D�jeuner','010451585',4);");


				//MealReservation
				db.execSQL("DROP TABLE IF EXISTS \"MealReservation\";");
				db.execSQL("CREATE TABLE \"MealReservation\" (\"numResto\" TEXT NOT NULL , \"idRes\" INTEGER NOT NULL, \"nameMeal\" TEXT NOT NULL, \"nbMeal\" INTEGER, PRIMARY KEY (\"numResto\", \"idRes\", \"nameMeal\"), constraint FK_MenuReservation_MEAL FOREIGN KEY (nameMeal,numResto) references MEAL (nameMeal,numResto), constraint FK_MenuReservation_RESERVATION FOREIGN KEY (idRes,numResto) references RESERVATION (id,numResto));");
				db.execSQL("INSERT INTO \"MealReservation\" VALUES('010851545',1,'Cr�pe au chocolat',3);");
				db.execSQL("INSERT INTO \"MealReservation\" VALUES('010851545',1,'Cr�pe au sucre de canne blond',1);");
				db.execSQL("INSERT INTO \"MealReservation\" VALUES('010851545',2,'Cr�pe aux morilles � la cr�me',2);");
				db.execSQL("INSERT INTO \"MealReservation\" VALUES('010851545',3,'La brochette maison de boeuf,servie avec riz et sauce proven�ale',1);");


				//MenuMeal
				db.execSQL("DROP TABLE IF EXISTS \"MenuMeal\";");
				db.execSQL("CREATE TABLE \"MenuMeal\" (\"nameMenu\" TEXT NOT NULL REFERENCES MENU (\"name\") , \"numResto\" TEXT NOT NULL REFERENCES RESTAURANT (\"num\"), \"nameMeal\" TEXT NOT NULL REFERENCES MEAL (\"name\") , PRIMARY KEY (\"nameMenu\", \"numResto\", \"nameMeal\"), constraint FK_MenuMeal_MENU FOREIGN KEY (nameMenu,numResto) references MENU (nameMenu,numResto), constraint FK_MenuMeal_MEAL FOREIGN KEY (nameMeal,numResto) references MEAL (nameMeal,numResto));");
				db.execSQL("INSERT INTO \"MenuMeal\" VALUES('Petit-D�jeuner','010451585','Caf�');");
				db.execSQL("INSERT INTO \"MenuMeal\" VALUES('Petit-D�jeuner','010451585','Chocolat chaud');");
				db.execSQL("INSERT INTO \"MenuMeal\" VALUES('Petit-D�jeuner','010451585','Cr�pe au chocolat');");
				db.execSQL("INSERT INTO \"MenuMeal\" VALUES('Petit-D�jeuner','010451585','Cr�pe au sucre de canne blond');");
				db.execSQL("INSERT INTO \"MenuMeal\" VALUES('Petit-D�jeuner','010451585','Th� nature');");


				//MenuReservation
				db.execSQL("DROP TABLE IF EXISTS \"MenuReservation\";");
				db.execSQL("CREATE TABLE \"MenuReservation\" (\"numResto\" TEXT NOT NULL , \"idRes\" INTEGER NOT NULL, \"nameMenu\" TEXT NOT NULL, \"nbMenu\" INTEGER, PRIMARY KEY (\"numResto\", \"idRes\", \"nameMenu\"), constraint FK_MenuReservation_MENU FOREIGN KEY (nameMenu,numResto) references MENU (nameMenu,numResto), constraint FK_MenuReservation_RESERVATION FOREIGN KEY (idRes,numResto) references RESERVATION (id,numResto));");
				db.execSQL("INSERT INTO \"MealReservation\" VALUES('010851545',2,'Petit-D�jeuner',3);");
				db.execSQL("INSERT INTO \"MealReservation\" VALUES('010851545',4,'Petit-D�jeuner',15);");

				//ModPay
				db.execSQL("DROP TABLE IF EXISTS \"ModPay\";");
				db.execSQL("CREATE TABLE \"ModPay\" (\"numResto\" TEXT NOT NULL REFERENCES RESTAURANT (\"num\") , \"modPay\" TEXT NOT NULL , PRIMARY KEY (\"numResto\", \"modPay\"));");
				db.execSQL("INSERT INTO \"ModPay\" VALUES('010451585','American Express');");
				db.execSQL("INSERT INTO \"ModPay\" VALUES('010451585','Bancontact');");
				db.execSQL("INSERT INTO \"ModPay\" VALUES('010451585','Lunch Pass Sodexo');");
				db.execSQL("INSERT INTO \"ModPay\" VALUES('010451585','Master Card');");
				db.execSQL("INSERT INTO \"ModPay\" VALUES('010451585','Proton');");
				db.execSQL("INSERT INTO \"ModPay\" VALUES('010451585','Ticket Restaurant');");
				db.execSQL("INSERT INTO \"ModPay\" VALUES('010451585','Visa');");
				db.execSQL("INSERT INTO \"ModPay\" VALUES('010451585','liquide');");


				//PictureCity
				db.execSQL("DROP TABLE IF EXISTS \"PictureCity\";");
				db.execSQL("CREATE TABLE \"PictureCity\" (\"nameCity\" TEXT NOT NULL REFERENCES CITY (\"name\") , \"picture\" TEXT NOT NULL , PRIMARY KEY (\"nameCity\", \"picture\"));");


				//PictureResto
				db.execSQL("DROP TABLE IF EXISTS \"PictureResto\";");
				db.execSQL("CREATE TABLE \"PictureResto\" (\"numResto\" TEXT NOT NULL ,\"picture\" TEXT NOT NULL, PRIMARY KEY (\"numResto\",\"picture\") );");


				//PlacesResto
				db.execSQL("DROP TABLE IF EXISTS \"PlacesResto\";");
				db.execSQL("CREATE TABLE \"PlacesResto\" (\"numResto\" TEXT NOT NULL REFERENCES RESTAURANT (\"num\") , \"typePlace\" TEXT NOT NULL , \"nbPlace\" INTEGER, PRIMARY KEY (\"numResto\", \"typePlace\"));");
				db.execSQL("INSERT INTO \"PlacesResto\" VALUES('010451585','Terrasse',15);");
				db.execSQL("INSERT INTO \"PlacesResto\" VALUES('010451585','Int�rieur',35);");


				//RESERVATION
				db.execSQL("DROP TABLE IF EXISTS \"RESERVATION\";");
				db.execSQL("CREATE TABLE \"RESERVATION\" (\"id\" INTEGER NOT NULL , \"numResto\" TEXT NOT NULL REFERENCES RESTAURANT (\"num\") , \"numUser\" TEXT NOT NULL REFERENCES USER (\"num\"), \"dateHeure\" INTEGER, \"nbPlace\" INTEGER, \"typePlace\" TEXT, PRIMARY KEY (\"id\", \"numResto\"), constraint FK_RESERVATION_PlacesResto FOREIGN KEY (numResto,typePlace) references PlacesResto (numResto,typePlace));");
				db.execSQL("INSERT INTO \"RESERVATION\" VALUES(1,'010851545','0497407447',1367492413170,4,'Terrasse');");
				db.execSQL("INSERT INTO \"RESERVATION\" VALUES(2,'010851545','0497407447',1367582422639,5,'Intérieur');");
				db.execSQL("INSERT INTO \"RESERVATION\" VALUES(3,'010851545','0496660666',1465222017741,1,'Intérieur');");
				db.execSQL("INSERT INTO \"RESERVATION\" VALUES(4,'010851545','0475696969',1371831816713,15,'Terrasse');");


				//RESTAURANT
				db.execSQL("DROP TABLE IF EXISTS \"RESTAURANT\";");
				db.execSQL("CREATE TABLE \"RESTAURANT\" (\"num\" TEXT PRIMARY KEY  NOT NULL  UNIQUE , \"accesInvalid\" BOOL, \"descr\" TEXT, \"name\" TEXT NOT NULL , \"street\" TEXT NOT NULL, \"numberStreet\" INTEGER NOT NULL, \"nameCity\" TEXT NOT NULL REFERENCES CITY (\"name\"));");
				db.execSQL("INSERT INTO \"RESTAURANT\" VALUES('010451585','true',\"Dans notre salle au d�cor rustique ou en terrasse aux beaux jours, d�gustez l'une de nos 350 cr�pes sal�es d'inspiration fran�aise ou exotique, ou appr�ciez l'une de nos salades vari�es. Pour accompagner votre plat, nous avons � votre disposition une carte de plus de 200 bi�res artisanales belges dont toutes les trapistes et 5 bi�res au f�t. Nous pouvons aussi vous servir un cidre bien frais ou vous laisser choisir un vin de pays.En dessert, vous choisirez une cr�pe sucr�e ou flamb�e, une coupe de glace maison ou encore un milkshake que vous accompagnerez d'un caf� lointain, un th� vert parfum� ou une infusion. Nous vous accueillons et vous servons tout au long du jour de 9h du matin jusqu'� 1h de la nuit, la cuisine restant ouverte non-stop. (from : www.lacreperiebretonne.be)\",'Cr�perie Bretonne - La m�re Fillioux','Place des Braban�ons',1,'Louvain-La-Neuve');");


				//TypeResto
				db.execSQL("DROP TABLE IF EXISTS \"TypeResto\";");
				db.execSQL("CREATE TABLE \"TypeResto\" (\"numResto\" TEXT NOT NULL REFERENCES RESTAURANT (\"num\") , \"typeResto\" TEXT NOT NULL , PRIMARY KEY (\"numResto\", \"typeResto\"));");
				db.execSQL("INSERT INTO \"TypeResto\" VALUES('010451585','Cr�perie');");
				db.execSQL("INSERT INTO \"TypeResto\" VALUES('010451585','Fran�aise');");
				db.execSQL("INSERT INTO \"TypeResto\" VALUES('010451585','de brasserie');");


				//USER
				db.execSQL("DROP TABLE IF EXISTS \"USER\";");
				db.execSQL("CREATE TABLE \"USER\" (\"num\" TEXT PRIMARY KEY  NOT NULL , \"name\" TEXT NOT NULL , \"invalid\" BOOL, \"password\" TEXT NOT NULL, \"owner\" BOOL);");
				db.execSQL("INSERT INTO \"USER\" VALUES('0497407447','Nathan Magrofuoco','0', 'mypwd', '0');");
				db.execSQL("INSERT INTO \"USER\" VALUES('0499123456','Peter Pan','0', 'vole', '0');");
				db.execSQL("INSERT INTO \"USER\" VALUES('0496660666','Belz�buth Fire','0', 'horreur', '0');");
				db.execSQL("INSERT INTO \"USER\" VALUES('11','testerUser','1', '11', '0');");
				db.execSQL("INSERT INTO \"USER\" VALUES('10','testerOwner','0', '10', '1');");
			}catch(SQLException e){
				Log.i("Database", "error on creation");
			}


		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			//TODO drop all database for sqlite
			//db.execSQL("DROP DATABASE "+DB_NAME);
			onCreate(db);
		}

	}

	public boolean update(Object o){
		//--------------------------------USER----------------------------------
		if(o instanceof User){
			User u=(User) o;
			ContentValues values=new ContentValues();
			values.put("num", u.getNumber());
			values.put("password", u.getPassword());
			values.put("name", u.getName());
			values.put("invalid", u.getDisabled());
			values.put("owner", u.isOwner());
			if(db.insertWithOnConflict("USER", null, values, SQLiteDatabase.CONFLICT_REPLACE)<0) return false;
			for(int i=0;i<u.getFavIngredient().size();i++){ //TODO check if update or add instead of deleting row and rebuilding
				values.clear();
				values.put("numUser", u.getNumber());
				values.put("ingredient", u.getFavIngredient().get(i));
				if(db.insertWithOnConflict("FAVOURITEINGREDIENT", null, values, SQLiteDatabase.CONFLICT_REPLACE)<0) return false;
			}
			for(int i=0;i<u.getFavRestaurantType().size();i++){
				values.clear();
				values.put("numUser", u.getNumber());
				values.put("typeResto", u.getFavRestaurantType().get(i));
				if(db.insertWithOnConflict("FAVOURITETYPERESTO", null, values, SQLiteDatabase.CONFLICT_REPLACE)<0) return false;
			}
			for(int i=0;i<u.getFavRestaurant().size();i++){
				values.clear();
				values.put("numUser", u.getNumber());
				values.put("numResto", u.getFavRestaurant().get(i));
				if(db.insertWithOnConflict("FAVOURITERESTO", null, values, SQLiteDatabase.CONFLICT_REPLACE)<0) return false;
			}
			for(int i=0;i<u.getDislikedRestaurant().size();i++){
				values.clear();
				values.put("numUser", u.getNumber());
				values.put("numResto", u.getDislikedRestaurant().get(i));
				if(db.insertWithOnConflict("HATEDRESTO", null, values, SQLiteDatabase.CONFLICT_REPLACE)<0) return false;
			}
			for(int i=0;i<u.getDislikedIngredient().size();i++){
				values.clear();
				values.put("numUser", u.getNumber());
				values.put("ingredient", u.getDislikedIngredient().get(i));
				if(db.insertWithOnConflict("HATEDINGREDIENT", null, values, SQLiteDatabase.CONFLICT_REPLACE)<0) return false;
			}
			for(int i=0;i<u.getDislikedRestaurantType().size();i++){
				values.clear();
				values.put("numUser", u.getNumber());
				values.put("typeResto", u.getDislikedRestaurantType().get(i));
				if(db.insertWithOnConflict("HATEDTYPERESTO", null, values, SQLiteDatabase.CONFLICT_REPLACE)<0) return false;
			}
		}
		//----------------------------------RESTAURANT------------------------------
		else if(o instanceof Restaurant){
			Restaurant r=(Restaurant) o;
			ContentValues values=new ContentValues();
			values.put("num", r.getNumber());
			values.put("accesInvalid", r.isAccessDisabled());
			values.put("descr", r.getDescription());
			values.put("name", r.getName());
			values.put("street", r.getAddress());
			values.put("numberStreet", r.getNumberAddress());
			values.put("nameCity", r.getCity().getName());
			if(db.insertWithOnConflict("RESTAURANT", null, values, SQLiteDatabase.CONFLICT_REPLACE)<0) return false;
			for(int i=0;i<r.getPaymentMethods().length;i++){
				values.clear();
				values.put("modPay", r.getPaymentMethods()[i]);
				values.put("numResto", r.getNumber());
				if(db.insertWithOnConflict("ModPay", null, values, SQLiteDatabase.CONFLICT_REPLACE)<0) return false;
			}
			for(int i=0;i<r.getTypeSeats().length;i++){
				values.clear();
				values.put("typePlace", r.getTypeSeats()[i]);
				values.put("nbPlace", r.getSeatsNumber(r.getTypeSeats()[i]));
				values.put("numResto", r.getNumber());
				if(db.insertWithOnConflict("PlacesResto", null, values, SQLiteDatabase.CONFLICT_REPLACE)<0) return false;
			}
			for(int i=0;i<r.getPictures().length;i++){
				values.clear();
				values.put("picture", r.getPictures()[i]);
				values.put("numResto", r.getNumber());
				if(db.insertWithOnConflict("PictureResto", null, values, SQLiteDatabase.CONFLICT_REPLACE)<0) return false;
			}
			for(int i=0;i<r.getTypes().length;i++){
				values.clear();
				values.put("typeResto", r.getTypes()[i]);
				values.put("numResto", r.getNumber());
				if(db.insertWithOnConflict("TypeResto", null, values, SQLiteDatabase.CONFLICT_REPLACE)<0) return false;
			}
			for(int i=0;i<r.getSchedules()[0].length;i++){
				values.clear();
				values.put("openMinute", r.getSchedules()[0][i]);
				values.put("closeMinute", r.getSchedules()[1][i]);
				values.put("numResto", r.getNumber());
				if(db.insertWithOnConflict("AgendaResto", null, values, SQLiteDatabase.CONFLICT_REPLACE)<0) return false;
			}
		}
		//--------------------------------CITY----------------------------------
		else if(o instanceof City){
			City c =(City) o;
			ContentValues values=new ContentValues();
			values.put("name", c.getName());
			values.put("descr", c.getDescription());
			values.put("locationGPSlong", c.getGPSLoc().getLongitude());
			values.put("locationGPSlat", c.getGPSLoc().getLatitude());
			if(db.insertWithOnConflict("CITY", null, values, SQLiteDatabase.CONFLICT_REPLACE)<0) return false;
			for(int i=0;i<c.getPictures().length;i++){
				values.clear();
				values.put("picture", c.getPictures()[i]);
				values.put("nameCity", c.getName());
				if(db.insertWithOnConflict("PictureCity", null, values, SQLiteDatabase.CONFLICT_REPLACE)<0) return false;
			}
		}
		//-------------------------------MEAL---------------------------------
		else if(o instanceof Meal){
			Meal m =(Meal) o;
			ContentValues values=new ContentValues();
			values.put("name", m.getName());
			values.put("numResto", m.getRestaurant().getNumber());
			values.put("price", m.getPrice());
			values.put("picture", m.getPicture());
			values.put("available", m.isAvailable());
			if(db.insertWithOnConflict("MEAL", null, values, SQLiteDatabase.CONFLICT_REPLACE)<0) return false;
			for(int i=0;i<m.getIngredients().length;i++){
				values.clear();
				values.put("nameMeal", m.getName());
				values.put("numResto", m.getRestaurant().getNumber());
				values.put("ingredient", m.getIngredients()[i]);
				if(db.insertWithOnConflict("PictureMeal", null, values, SQLiteDatabase.CONFLICT_REPLACE)<0) return false;
			}
		}
		//-------------------------------MENU-----------------------------------
		else if(o instanceof Menu){
			Menu m =(Menu) o;
			ContentValues values=new ContentValues();
			values.put("name", m.getName());
			values.put("numResto", m.getRestaurant().getNumber());
			values.put("price", m.getPrice());
			if(db.insertWithOnConflict("MENU", null, values, SQLiteDatabase.CONFLICT_REPLACE)<0) return false;
			//TODO check if mandatory to update ever meal within menu (cascade update)
			// seems legit if you need to remove a dish but perhaps a bit overkill
			/*for(int i=0;i<m.getMeals().length;i++){
				update(m.getMeals()[i]);
			}*/
		}
		//------------------------------RESERVATIONS-----------------------
		else if(o instanceof Reservation){
			Reservation r =(Reservation) o;
			ContentValues values=new ContentValues();
			values.put("id", r.getId());
			values.put("numResto", r.getRestaurant().getNumber());
			values.put("numUser", r.getUser().getNumber());
			values.put("dateHeure", r.getDate().getTimeInMillis());
			values.put("nbPlace", r.getSeatsNumber());
			values.put("typePlaces", r.getSeatsType());
			if(db.insertWithOnConflict("RESERVATION", null, values, SQLiteDatabase.CONFLICT_REPLACE)<0) return false;
			for(int i=0;i<r.getMeals().length;i++){
				values.clear();
				values.put("idRes", r.getId());
				values.put("nameMeal", r.getMeals()[i].getName());
				values.put("numResto", r.getRestaurant().getNumber());
				values.put("nbMeal", r.getNumerOf(r.getMeals()[i]));
				if(db.insertWithOnConflict("MealReservation", null, values, SQLiteDatabase.CONFLICT_REPLACE)<0) return false;
			}
			for(int i=0;i<r.getMenus().length;i++){
				values.clear();
				values.put("idRes", r.getId());
				values.put("nameMenu", r.getMenus()[i].getName());
				values.put("numResto", r.getRestaurant().getNumber());
				values.put("nbMenu", r.getNumerOf(r.getMenus()[i]));
				if(db.insertWithOnConflict("MenuReservation", null, values, SQLiteDatabase.CONFLICT_REPLACE)<0) return false;
			}
		}
		return true;
	}

	public User getUser(String number) {
		Cursor c = db.rawQuery("select * from USER where NUM='"+number+"'", null);
		if(c.getCount()==0){
			c.close();
			return null;
		}
		else{
			c.moveToFirst();
			User u = new User(number,c.getString(c.getColumnIndexOrThrow("name")), c.getString(c.getColumnIndexOrThrow("password")), c.getInt(c.getColumnIndexOrThrow("owner"))>0);
			u.setDisabled(c.getInt(c.getColumnIndexOrThrow("invalid"))>0);
			//arraylist bloc
			Cursor c_favouriteingredient=db.rawQuery("select * from FAVOURITEINGREDIENT where NUMUSER='"+number+"'", null);
			if(c_favouriteingredient.getCount()>0){
				c_favouriteingredient.moveToFirst();
				while(!c_favouriteingredient.isAfterLast()){
					u.addFavoriteIngredient(c_favouriteingredient.getString(c_favouriteingredient.getColumnIndexOrThrow("ingredient")));
					c_favouriteingredient.moveToNext();
				}
			}
			c_favouriteingredient.close();
			//fin du bloc
			//arraylist bloc
			Cursor c_hatedingredient=db.rawQuery("select * from HATEDINGREDIENT where NUMUSER='"+number+"'", null);
			if(c_hatedingredient.moveToFirst()){
				while(!c_hatedingredient.isAfterLast()){
					u.addDislikedIngredient(c_hatedingredient.getString(c_hatedingredient.getColumnIndexOrThrow("ingredient")));
					c_hatedingredient.moveToNext();
				}
			}
			c_hatedingredient.close();
			//fin du bloc
			//arraylist bloc
			Cursor c_favouriteTypeResto=db.rawQuery("select * from FAVOURITETYPERESTO where NUMUSER='"+number+"'", null);
			if(c_favouriteTypeResto.moveToFirst()){
				while(!c_favouriteTypeResto.isAfterLast()){
					u.addFavoriteRestaurantType(c_favouriteTypeResto.getString(c_favouriteTypeResto.getColumnIndexOrThrow("typeResto")));
					c_favouriteTypeResto.moveToNext();
				}
			}
			c_favouriteTypeResto.close();
			//fin du bloc
			//arraylist bloc
			Cursor c_hatedTypeResto=db.rawQuery("select * from HATEDTYPERESTO where NUMUSER='"+number+"'", null);
			if(c_hatedTypeResto.moveToFirst()){
				while(!c_hatedTypeResto.isAfterLast()){
					u.addDislikedRestaurantType(c_hatedTypeResto.getString(c_hatedTypeResto.getColumnIndexOrThrow("typeResto")));
					c_hatedTypeResto.moveToNext();
				}
			}
			c_hatedTypeResto.close();
			//fin du bloc
			//arraylist bloc
			Cursor c_favouriteResto=db.rawQuery("select * from FAVOURITERESTO where NUMUSER='"+number+"'", null);
			if(c_favouriteResto.moveToFirst()){
				while(!c_favouriteResto.isAfterLast()){
					u.addFavoriteRestaurant(c_favouriteResto.getString(c_favouriteResto.getColumnIndexOrThrow("numResto")));
					c_favouriteResto.moveToNext();
				}
			}
			c_favouriteResto.close();
			//fin du bloc
			//arraylist bloc
			Cursor c_hatedResto=db.rawQuery("select * from HATEDRESTO where NUMUSER='"+number+"'", null);
			if(c_hatedResto.moveToFirst()){
				while(!c_hatedResto.isAfterLast()){
					u.addDislikedRestaurant(c_hatedResto.getString(c_hatedResto.getColumnIndexOrThrow("numResto")));
					c_hatedResto.moveToNext();
				}
			}
			c_hatedResto.close();
			//fin du bloc
			c.close();
			return u;
		}
	}


	public Restaurant getRestaurant(String number){
		Cursor c = db.rawQuery("select * from RESTAURANT where NUM='"+number+"'", null);
		if(c.getCount()==0){
			c.close();
			return null;
		}
		else{
			c.moveToFirst();
			//TODO ouh, that's dirty!
			City city= MainActivity.getCityManager().getCity(c.getString(c.getColumnIndexOrThrow("nameCity")));
			Restaurant r = new Restaurant(c.getString(c.getColumnIndexOrThrow("num")),c.getString(c.getColumnIndexOrThrow("name")),c.getString(c.getColumnIndexOrThrow("numberStreet")),c.getString(c.getColumnIndexOrThrow("street")),city);
			r.setAccessDisabled(c.getInt(c.getColumnIndexOrThrow("accesInvalid"))>0);
			r.setDescription(c.getString(c.getColumnIndexOrThrow("descr")));
			//TODO setId pour les réservations
			//arraylist bloc
			Cursor c_placesResto=db.rawQuery("select * from PLACESRESTO where NUMRESTO='"+c.getString(c.getColumnIndexOrThrow("num"))+"'",null);
			if(c_placesResto.moveToFirst()){
				while(!c_placesResto.isAfterLast()){
					r.addSeats(c_placesResto.getString(c_placesResto.getColumnIndexOrThrow("typePlace")), c_placesResto.getInt(c_placesResto.getColumnIndexOrThrow("nbPlace")));
					c_placesResto.moveToNext();
				}
			}
			c_placesResto.close();
			//arraylist bloc
			Cursor c_pictures=db.rawQuery("select * from PICTURERESTO where NUMRESTO='"+c.getString(c.getColumnIndexOrThrow("num"))+"'",null);
			if(c_pictures.moveToFirst()){
				while(!c_pictures.isAfterLast()){
					r.addPictures(c_pictures.getString(c_pictures.getColumnIndexOrThrow("picture")));
					c_pictures.moveToNext();
				}
			}
			c_pictures.close();
			//fin du bloc
			//arraylist bloc
			Cursor c_modpay=db.rawQuery("select * from MODPAY where NUMRESTO='"+c.getString(c.getColumnIndexOrThrow("num"))+"'",null);
			if(c_modpay.moveToFirst()){
				while(!c_modpay.isAfterLast()){
					r.addPaymentMethod(c_modpay.getString(c_modpay.getColumnIndexOrThrow("modPay")));
					c_modpay.moveToNext();
				}
			}
			c_modpay.close();
			//fin du bloc
			//arraylist bloc
			Cursor c_typeResto=db.rawQuery("select * from TYPERESTO where NUMRESTO='"+c.getString(c.getColumnIndexOrThrow("num"))+"'",null);
			if(c_typeResto.moveToFirst()){
				while(!c_typeResto.isAfterLast()){
					r.addTypes(c_typeResto.getString(c_typeResto.getColumnIndexOrThrow("typeResto")));
					c_typeResto.moveToNext();
				}
			}
			c_typeResto.close();
			//fin du bloc
			//arraylist bloc
			Cursor c_Schedules=db.rawQuery("select * from AGENDARESTO where NUMRESTO='"+c.getString(c.getColumnIndexOrThrow("num"))+"'",null);
			if(c_Schedules.moveToFirst()){
				while(!c_Schedules.isAfterLast()){
					r.addSchedules(c_Schedules.getInt(c_Schedules.getColumnIndexOrThrow("openMinute")), c_Schedules.getInt(c_Schedules.getColumnIndexOrThrow("closedMinute")));
					c_Schedules.moveToNext();
				}
			}
			c_Schedules.close();
			//fin du bloc
			c.close();
			return r;
		}
	}


	public ArrayList<Restaurant> getRestaurants() {return getRestaurants(null);}
	public ArrayList<Restaurant> getRestaurants(City city){
		Cursor c;
		if(city !=null)
			c = db.rawQuery("select * from RESTAURANT where NAMECITY='"+city.getName()+"'", null);
		else
			c = db.rawQuery("select * from RESTAURANT", null);
		if(c.getCount()==0){
			c.close();
			return null;
		}
		else{
			ArrayList<Restaurant> restaurants=new ArrayList<Restaurant>();
			c.moveToFirst();
			while(!c.isAfterLast()){
				Restaurant r = new Restaurant(c.getString(c.getColumnIndexOrThrow("num")),c.getString(c.getColumnIndexOrThrow("name")),c.getString(c.getColumnIndexOrThrow("numberStreet")),c.getString(c.getColumnIndexOrThrow("street")),city);
				r.setAccessDisabled(c.getInt(c.getColumnIndexOrThrow("accesInvalid"))>0);
				r.setDescription(c.getString(c.getColumnIndexOrThrow("descr")));
				//TODO setId pour les réservations
				//arraylist bloc
				Cursor c_placesResto=db.rawQuery("select * from PLACESRESTO where NUMRESTO='"+c.getString(c.getColumnIndexOrThrow("num"))+"'",null);
				if(c_placesResto.moveToFirst()){
					while(!c_placesResto.isAfterLast()){
						r.addSeats(c_placesResto.getString(c_placesResto.getColumnIndexOrThrow("typePlace")), c_placesResto.getInt(c_placesResto.getColumnIndexOrThrow("nbPlace")));
						c_placesResto.moveToNext();
					}
				}
				c_placesResto.close();
				//arraylist bloc
				Cursor c_pictures=db.rawQuery("select * from PICTURERESTO where NUMRESTO='"+c.getString(c.getColumnIndexOrThrow("num"))+"'",null);
				if(c_pictures.moveToFirst()){
					while(!c_pictures.isAfterLast()){
						r.addPictures(c_pictures.getString(c_pictures.getColumnIndexOrThrow("picture")));
						c_pictures.moveToNext();
					}
				}
				c_pictures.close();
				//fin du bloc
				//arraylist bloc
				Cursor c_modpay=db.rawQuery("select * from MODPAY where NUMRESTO='"+c.getString(c.getColumnIndexOrThrow("num"))+"'",null);
				if(c_modpay.moveToFirst()){
					while(!c_modpay.isAfterLast()){
						r.addPaymentMethod(c_modpay.getString(c_modpay.getColumnIndexOrThrow("modPay")));
						c_modpay.moveToNext();
					}
				}
				c_modpay.close();
				//fin du bloc
				//arraylist bloc
				Cursor c_typeResto=db.rawQuery("select * from TYPERESTO where NUMRESTO='"+c.getString(c.getColumnIndexOrThrow("num"))+"'",null);
				if(c_typeResto.moveToFirst()){
					while(!c_typeResto.isAfterLast()){
						r.addTypes(c_typeResto.getString(c_typeResto.getColumnIndexOrThrow("typeResto")));
						c_typeResto.moveToNext();
					}
				}
				c_typeResto.close();
				//fin du bloc
				Cursor c_schedule=db.rawQuery("select * from AGENDARESTO where NUMRESTO='"+c.getString(c.getColumnIndexOrThrow("num"))+"'",null);
				if(c_schedule.moveToFirst()){
					while(!c_schedule.isAfterLast()){
						r.addSchedules(c_schedule.getInt(c.getColumnIndexOrThrow("open")),c_schedule.getInt(c.getColumnIndexOrThrow("close")));
						c_modpay.moveToNext();
					}
				}
				c_modpay.close();
				restaurants.add(r);
				c.moveToNext();
			}
			c.close();
			return restaurants;
		}
	}

	public ArrayList<Menu> getMenus(Restaurant r){
		Cursor c;
		if(r !=null)
			c = db.rawQuery("select * from MENU where NUMRESTO='"+r.getNumber()+"'", null);
		else
			c = db.rawQuery("select * from MENU", null);
		if(c.getCount()==0){
			c.close();
			return null;
		}
		else{
			ArrayList<Menu> menus=new ArrayList<Menu>();
			c.moveToFirst();
			while(!c.isAfterLast()){
				Menu m =new Menu(c.getString(c.getColumnIndexOrThrow("name")),r,c.getDouble(c.getColumnIndexOrThrow("price")));
				//TODO doit-on ajouter la liste des plats systématiquement ou laisser le manager s'en charger?
				menus.add(m);
				c.moveToNext();
			}
			c.close();
			return menus;
		}
	}

	public ArrayList<Meal> getMeals(Restaurant r){
		Cursor c;
		if(r !=null)
			c = db.rawQuery("select * from MEAL where NUMRESTO='"+r.getNumber()+"'", null);
		else
			c = db.rawQuery("select * from MEAL", null);
		if(c.getCount()==0){
			c.close();
			return null;
		}
		else{
			ArrayList<Meal> meals=new ArrayList<Meal>();
			c.moveToFirst();
			while(!c.isAfterLast()){
				Meal m =new Meal(c.getString(c.getColumnIndexOrThrow("name")),r,c.getDouble(c.getColumnIndexOrThrow("price")),c.getInt(c.getColumnIndexOrThrow("available"))>0,c.getString(c.getColumnIndexOrThrow("picture")));
				Cursor c_ingredients = db.rawQuery("select * from INGREDIENTMEAL where NUMRESTO='"+r.getNumber()+"' and NAMEMEAL='"+c.getString(c.getColumnIndexOrThrow("name"))+"'", null);
				if(c_ingredients.moveToFirst()){
					while(!c_ingredients.isAfterLast()){
						m.addIngredient(c_ingredients.getString(c_ingredients.getColumnIndex("ingredient")));
						c_ingredients.moveToNext();
					}
					meals.add(m);
				}
				c.moveToNext();
				c_ingredients.close();
			}
			c.close();
			return meals;
		}
	}

	public ArrayList<Meal> getMeals(Menu menu){
		String numResto=menu.getRestaurant().getNumber();
		String menuName=menu.getName();
		Cursor c;
		c = db.rawQuery("select * from MENUMEAL where NUMRESTO='"+numResto+"' and MENUNAME='"+menuName+"'", null);
		if(c.getCount()==0){
			c.close();
			return null;
		}
		else{
			ArrayList<Meal> meals=new ArrayList<Meal>();
			c.moveToFirst();
			while(!c.isAfterLast()){
				Cursor c_meal=db.rawQuery("select * from MEAL where NAME='"+c.getString(c.getColumnIndexOrThrow("mealName"))+"' and NUMRESTO='"+numResto+"'", null);
				if(c_meal.moveToFirst()){
					while(!c_meal.isAfterLast()){
						Meal m =new Meal(c.getString(c_meal.getColumnIndexOrThrow("name")),menu.getRestaurant(),c_meal.getDouble(c_meal.getColumnIndexOrThrow("price")),c_meal.getInt(c_meal.getColumnIndexOrThrow("available"))>0,c_meal.getString(c_meal.getColumnIndexOrThrow("picture")));
						Cursor c_ingredients = db.rawQuery("select * from INGREDIENTMEAL where NUMRESTO='"+numResto+"' and NAMEMEAL='"+c_meal.getString(c.getColumnIndexOrThrow("name"))+"'", null);
						if(c_ingredients.moveToFirst()){
							while(!c_ingredients.isAfterLast()){
								m.addIngredient(c_ingredients.getString(c_ingredients.getColumnIndex("ingredient")));
								c_ingredients.moveToNext();
							}
						}
						c_ingredients.close();
						meals.add(m);
						c_meal.moveToNext();
					}
				}
				c_meal.close();
				c.moveToNext();
			}
			c.close();
			return meals;
		}
	}

	public ArrayList<City> getCities(){
		Cursor c = db.rawQuery("select * from CITY", null);
		if(c.getCount()==0){
			c.close();
			return null;
		}
		else{
			ArrayList<City> cities=new ArrayList<City>();
			c.moveToFirst();
			while(!c.isAfterLast()){
				String name = c.getString(c.getColumnIndexOrThrow("name"));
				Location loc = new Location(name);
				loc.setLongitude(c.getDouble(c.getColumnIndexOrThrow("locationGPSlong")));
				loc.setLatitude(c.getDouble(c.getColumnIndexOrThrow("locationGPSlat")));
				City city = new City(name,loc,c.getString(c.getColumnIndexOrThrow("descr")));
				cities.add(city);
				c.moveToNext();
			}
			c.close();
			return cities;
		}
	}

	public int getLastReservationId(Restaurant r){
		Cursor c = db.rawQuery("select ID from RESERVATION where NUMRESTO='"+r.getNumber()+"'", null);
		int lastId=0;
		if(c.moveToFirst()){
			for(int i=0;i<c.getCount();i++){
				int temp=c.getInt(c.getColumnIndexOrThrow("id"));
				if(temp>lastId){
					lastId=temp;
				}
				c.moveToNext();
			}
		}
		c.close();
		return lastId;
	}

	/////////////////////////////////////////////////////////////////////////// TODO
	// Ce que j'ai essayé de faire, il vaudrait mieux que tu vérifies. :p  //// TODO
	/////////////////////////////////////////////////////////////////////////// TODO

	public City getCity(String name){
		Cursor c = db.rawQuery("select * from CITY where NAME='"+name+"'", null);
		if(c.getCount()==0){
			c.close();
			return null;
		}
		else{
			c.moveToFirst();

			Location loc = new Location(name);
			loc.setLongitude(c.getDouble(c.getColumnIndexOrThrow("locationGPSlong")));
			loc.setLatitude(c.getDouble(c.getColumnIndexOrThrow("locationGPSlat")));
			City city = new City(c.getString(c.getColumnIndexOrThrow("name")), loc, c.getString(c.getColumnIndexOrThrow("descr")));

			//arraylist bloc
			Cursor c_pictureCity=db.rawQuery("select * from PICTURECITY where NAMECITY='"+c.getString(c.getColumnIndexOrThrow("name"))+"'",null);
			if(c_pictureCity.moveToFirst()){
				while(!c_pictureCity.isAfterLast()){
					city.addPicture(c_pictureCity.getString(c_pictureCity.getColumnIndexOrThrow("picture")));
					c_pictureCity.moveToNext();
				}
			}
			c_pictureCity.close();
			//fin du bloc
			c.close();
			return city;
		}
	}

	public Meal getMeal(String name, Restaurant rest){
		Cursor c = db.rawQuery("select * from MEAL where NUMRESTO='"+rest.getNumber()+"' and NAME='"+name+"'", null);
		if(c.getCount()==0){
			c.close();
			return null;
		}
		else{
			c.moveToFirst();
			Meal meal =new Meal(name,rest,c.getDouble(c.getColumnIndexOrThrow("price")),c.getInt(c.getColumnIndexOrThrow("available"))>0,c.getString(c.getColumnIndexOrThrow("picture")));
			//arraylist bloc
			Cursor c_ingredients = db.rawQuery("select * from INGREDIENTMEAL where NUMRESTO='"+rest.getNumber()+"' and NAMEMEAL='"+name+"'", null);
			if(c_ingredients.moveToFirst()){
				while(!c_ingredients.isAfterLast()){
					meal.addIngredient(c_ingredients.getString(c_ingredients.getColumnIndex("ingredient")));
					c_ingredients.moveToNext();
				}
			}
			c_ingredients.close();
			//fin du bloc
			c.close();
			return meal;
		}
	}

	public Menu getMenu(String name, Restaurant rest){
		Cursor c = db.rawQuery("select * from MENU where NUMRESTO='"+rest.getNumber()+"' and NAME='"+name+"'", null);
		if(c.getCount()==0){
			c.close();
			return null;
		}
		else{
			c.moveToFirst();
			Menu menu =new Menu(name,rest,c.getDouble(c.getColumnIndexOrThrow("price")));

			//arraylist bloc
			Cursor c_meals=db.rawQuery("select * from MENUMEAL where NAMEMENU='"+name+"' and NUMRESTAU='"+rest.getNumber()+"'",null);
			if(c_meals.moveToFirst()){
				while(!c_meals.isAfterLast()){
					Meal meal = MainActivity.getMenuMealManager().getMeal(name, rest);
					menu.addMeal(meal);
					c_meals.moveToNext();
				}
			}
			c_meals.close();
			//fin du bloc

			c.close();
			return menu;
		}
	}

	/*public ArrayList<Reservation> getReservations(User user) {
		Cursor c;
		if(user !=null)
			c = db.rawQuery("select * from RESERVATION where NUMUSER='"+user.getNumber()+"'", null);
		else
			c = db.rawQuery("select * from MEAL", null);
		if(c.getCount()==0){
			c.close();
			return null;
		}
		else{
			ArrayList<Meal> meals=new ArrayList<Meal>();
			c.moveToFirst();
			while(!c.isAfterLast()){
				Meal m =new Meal(c.getString(c.getColumnIndexOrThrow("name")),r,c.getDouble(c.getColumnIndexOrThrow("price")),c.getInt(c.getColumnIndexOrThrow("available"))>0,c.getString(c.getColumnIndexOrThrow("picture")));
				Cursor c_ingredients = db.rawQuery("select * from INGREDIENTMEAL where NUMRESTO='"+r.getNumber()+"' and NAMEMEAL='"+c.getString(c.getColumnIndexOrThrow("name"))+"'", null);
				if(c_ingredients.moveToFirst()){
					while(!c_ingredients.isAfterLast()){
						m.addIngredient(c_ingredients.getString(c_ingredients.getColumnIndex("ingredient")));
						c_ingredients.moveToNext();
					}
					meals.add(m);
				}
				c.moveToNext();
				c_ingredients.close();
			}
			c.close();
			return meals;
		}
	}*/

	public Reservation getReservation(int id, Restaurant rest, User user){
		Cursor c = db.rawQuery("select * from RESERVATION where ID='"+id+"' and NUMRESTO='"+rest.getNumber()+"' and NUMUSER='"+user.getNumber()+"'", null);
		if(c.getCount()==0){
			c.close();
			return null;
		}
		else{
			c.moveToFirst();
			Calendar date = Calendar.getInstance();
			date.setTimeInMillis((long) c.getInt(c.getColumnIndexOrThrow("dateHeure")));	

			Reservation reservation = new Reservation(id, date, c.getString(c.getColumnIndexOrThrow("typePlace")), c.getInt(c.getColumnIndexOrThrow("nbPlace")), rest, user);
			//arraylist bloc
			Cursor c_meals=db.rawQuery("select * from MEALRESERVATION where IDRES='"+id+"' and NUMRESTO='"+rest.getNumber()+"'", null);
			if(c_meals.moveToFirst()){
				while(!c_meals.isAfterLast()){
					Meal meal = MainActivity.getMenuMealManager().getMeal(c_meals.getString(c_meals.getColumnIndexOrThrow("nameMeal")), rest);
					reservation.addMeal(meal, c_meals.getInt(c_meals.getColumnIndexOrThrow("nbMeal")));
					c_meals.moveToNext();
				}
			}
			c_meals.close();
			//fin du bloc
			//arraylist bloc
			Cursor c_menus=db.rawQuery("select * from MENURESERVATION where IDRES='"+id+"' and NUMRESTO='"+rest.getNumber()+"'", null);
			if(c_menus.moveToFirst()){
				while(!c_menus.isAfterLast()){
					Menu menu= MainActivity.getMenuMealManager().getMenu(c_meals.getString(c_meals.getColumnIndexOrThrow("nameMenu")), rest);
					reservation.addMenu(menu, c_meals.getInt(c_meals.getColumnIndexOrThrow("nbMenu")));
					c_menus.moveToNext();
				}
			}
			c_menus.close();
			//fin du bloc
			c.close();
			return reservation;
		}
	}
}