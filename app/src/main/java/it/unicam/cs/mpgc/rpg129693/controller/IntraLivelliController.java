package it.unicam.cs.mpgc.rpg129693.controller;
import it.unicam.cs.mpgc.rpg129693.data.GestoreSalvataggi;
import it.unicam.cs.mpgc.rpg129693.gui.SceneManager;
import it.unicam.cs.mpgc.rpg129693.gui.Schermata;
import it.unicam.cs.mpgc.rpg129693.model.Eroe;
import it.unicam.cs.mpgc.rpg129693.model.Torre;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;


public class IntraLivelliController {
    @FXML private Button btnSalvaPartita;
    @FXML private Button btnContinuaBattaglia;
    private Eroe eroe;
    private Torre torre;


    public void setEroe(Eroe eroe) {
        this.eroe = eroe;
    }

    public void setTorre(Torre torre) {
        this.torre = torre;
    }

    @FXML
    public void onSalvaPartitaClick() {
        GestoreSalvataggi.salvaPartita(eroe, torre);
        btnSalvaPartita.setText("✓ Partita Salvata");
        btnSalvaPartita.setDisable(true);
    }
    @FXML
    public void onContinuaBattagliaClick() {
        navigaAProssimoCombattimento();
    }

    private void navigaAProssimoCombattimento() {
        FXMLLoader loader = SceneManager.getInstance().caricaConLoader(Schermata.COMBATTIMENTO);
        ScenaCombattimentoController controller = loader.getController();
        controller.setDatiPartita(eroe, torre);
    }
}