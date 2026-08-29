package it.unicam.cs.mpgc.rpg129693.gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SceneManager {
    private static SceneManager instance;
    private Stage stage;

    // Mappa ogni schermata al suo file .fxml
    private static final Map<Schermata, String> FXML_MAP = new HashMap<>();
    static {
        FXML_MAP.put(Schermata.MENU,               "/FXML/Menu.fxml");
        FXML_MAP.put(Schermata.SCELTA_PERSONAGGIO,  "/FXML/SceltaPersonaggio.fxml");
        FXML_MAP.put(Schermata.INTRO_STORIA,        "/FXML/IntroSchermata.fxml");
        FXML_MAP.put(Schermata.COMBATTIMENTO,       "/FXML/SchermataCombattimento.fxml");
        FXML_MAP.put(Schermata.INTERMEZZO,          "/FXML/IntraLivelli.fxml");
        FXML_MAP.put(Schermata.FINE_GIOCO,          "/FXML/FineGioco.fxml");
    }

    //Costruttore privato per evitare la creazione esterna di istanze
    private SceneManager(){}

    public static SceneManager getInstance(){
        if (instance == null){
            instance = new SceneManager();
        }
        return instance;
    }

    //Imposta lo stage all'avvio
    public void setStage(Stage stage){
        this.stage = stage;
    }


    //Cambio schermata senza passaggio di dati
    public void cambiaSchermata(Schermata schermata){
        try{
            String FxmlPath = FXML_MAP.get(schermata);
            FXMLLoader loader = new FXMLLoader(getClass().getResource(FxmlPath));
            Parent root = loader.load();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException("Errore nel caricamento della schermata: " + schermata,e );
        }
    }

    // Cambio schermata con passaggio di dati(es. Far sapere al controller del combattimento quale eroe è stato scelto)
    public FXMLLoader caricaConLoader(Schermata schermata) {
        try {
            String fxmlPath = FXML_MAP.get(schermata);
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();
            stage.setScene(new Scene(root));
            stage.show();
            return loader;
        } catch (IOException e) {
            throw new RuntimeException("Errore nel caricamento della schermata: " + schermata, e);
        }
    }
}
