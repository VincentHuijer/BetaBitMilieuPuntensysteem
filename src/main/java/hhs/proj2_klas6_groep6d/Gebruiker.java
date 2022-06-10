package hhs.proj2_klas6_groep6d;

import java.util.ArrayList;
import java.util.Date;

public class Gebruiker extends Persoon {
    private static int currentId = 0;
    private ArrayList<Reis> alleReizen = new ArrayList<>();
    private String voornaam;
    private String achternaam;
    private String username;
    private String wachtwoord;
    private Adres adres; // Eventueel later nodig voor API met afstand berekenen
    private int id; // Medewerkers van betabit krijgen vanuit het bedrijf al een id. Dit onderdeel schrappen we misschien nog.
    private double co2Uitstoot;
    private int totaalAantalVerdiendePunten; // Nodig voor scorebord
    private Punten punten = new Punten();
    private double puntenSaldo;
    private double afstandVanWerkInKm; // Eventueel later nodig voor API met afstand berekenen
    private double totaalKm = 0;

    public Gebruiker(String username, String wachtwoord, String voornaam, String achternaam, int id){
        super(username, wachtwoord, voornaam, achternaam, id);
        this.voornaam = voornaam;
        this.achternaam = achternaam;
        this.id = id;
        //this.adres = adres;
        this.username = username;
        this.wachtwoord = wachtwoord;
        initialiseerGebruiker();
    }

    public double getMonthtotaalKm() {
        int month = new Date().getMonth();

        double count = 0;
        for(Reis trip: alleReizen){
            if(trip.getDate().getMonth() == month){
                count += trip.getAfstand();
            }
        }

        return count;
    }

    public String getMonthTotaalKmString(){
        return getMonthtotaalKm() + " km";
    }

    public double getMonthco2Uitstoot() {
        int month = new Date().getMonth();

        double count = 0;
        for(Reis trip: alleReizen){
            if(trip.getDate().getMonth() == month){
                count += trip.getCO2().getUitstoot();
            }
        }

        return count;
    }

    public String getMonthco2UitstootString(){
        return (getMonthco2Uitstoot() / 1000) + " kg";
    }


    public double getOldMonthco2Uitstoot() {
        int month = new Date().getMonth()-1;

        double count = 0;
        for(Reis trip: alleReizen){
            if(trip.getDate().getMonth() == month){
                count += trip.getCO2().getUitstoot();
            }
        }

        return count;
    }

    public String getOldMonthco2UitstootString(){
        return (getOldMonthco2Uitstoot() / 1000) + " kg";
    }

    public double getMonthpunten() {
        int month = new Date().getMonth();

        double count = 0;
        for(Reis trip: alleReizen){
            if(trip.getDate().getMonth() == month){
                count += trip.getPunten();
            }
        }

        return count;
    }

    public void initialiseerGebruiker(){
        //afstandVanWerkInKm eventueel uitrekenen dmv api voor afstand
        this.puntenSaldo = punten.getAantalPunten();
        this.co2Uitstoot = 0; //Hier moet nog een methode voor gemaakt worden.
    }

    public Adres getAdres() {
        return adres;
    }

    @Override
    public String getVoornaam() {
        return voornaam;
    }

    @Override
    public String getAchternaam() {
        return achternaam;
    }

    @Override
    public int getId() {
        return id;
    }

    public double getAfstandVanWerkInKm() {
        return afstandVanWerkInKm;
    }

    @Override
    public double getCo2Uitstoot() {
        double count = 0;
        for(Reis trip: alleReizen){
            count += trip.getCO2().getUitstoot();
        }

        return count;
    }

    @Override
    public double getPuntenSaldo() {
        return puntenSaldo;
    }

    public int getTotaalAantalVerdiendePunten() {
        return totaalAantalVerdiendePunten;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getWachtwoord() {
        return wachtwoord;
    }

    @Override
    public Punten getPunten() {
        return punten;
    }

    @Override
    public boolean isAdmin(){
        return false;
    }

    @Override
    public ArrayList<Reis> getAlleReizen() {
        return alleReizen;
    }
    @Override
    public void berekenTotaalKM(){
        double sum = 0;
        for(Reis reis : alleReizen){
            sum += reis.getAfstand();
        }
        this.totaalKm = sum;
    }

    public double getTotaalKm() {
        return totaalKm;
    }

    public int generateId(){
        int id = currentId;
        currentId++;
        return id;
    }
}
