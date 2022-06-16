package hhs.controllersAndScreens;

import hhs.proj2_klas6_groep6d.Gebruiker;
import hhs.proj2_klas6_groep6d.Main;
import hhs.proj2_klas6_groep6d.Persoon;
import hhs.proj2_klas6_groep6d.RewardsList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Observable;
import java.util.Observer;

public class BeloningScherm implements Observer {
    private static RewardsList rewardsList = new RewardsList();
    private static Persoon loggedIn;
    private static Scene scene;
    private static Stage stage;
    public void start() throws Exception {
        if(stage == null){
            stage = new Stage();
        }

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("beloningenStore.fxml"));
        scene = new Scene(fxmlLoader.load(), 700, 530);
        Text welkomText = (Text) scene.lookup("#welkomText");
        welkomText.setText("Welkom " + loggedIn.getUsername());
        Text punten = (Text) scene.lookup("#puntensaldoText");
        punten.setText(String.format("%.0f PUNTEN", loggedIn.getPunten().getAantalPunten()));

        stage.setTitle("Beloningen");
        stage.getIcons().add(new Image(Main.class.getResourceAsStream("images/logoT.png")));
        stage.setScene(scene);
        stage.show();
    }
    public void setLoggedIn(Persoon gebruiker){
        loggedIn = gebruiker;
    }

    public static Persoon getLoggedIn() {
        return loggedIn;
    }

    public static RewardsList getRewardsList() {
        return rewardsList;
    }

    @Override
    public void update(Observable o, Object arg) { //Zodra een gebruiker een beloning koopt, worden zijn/haar punten geupdate.
        Text puntensaldo = (Text) scene.lookup("#puntensaldoText");
        puntensaldo.setText(String.format("%.0f PUNTEN", loggedIn.getPunten().getAantalPunten()));
    }
}