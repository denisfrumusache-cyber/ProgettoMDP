package it.unicam.cs.mpgc.rpg129693.Controller;
import it.unicam.cs.mpgc.rpg129693.Data.GestoreSalvataggi;
import it.unicam.cs.mpgc.rpg129693.InterfacciaGrafica.SceneManager;
import it.unicam.cs.mpgc.rpg129693.InterfacciaGrafica.Schermata;
import it.unicam.cs.mpgc.rpg129693.classiPrincipali.Eroe;
import it.unicam.cs.mpgc.rpg129693.classiPrincipali.Torre;
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