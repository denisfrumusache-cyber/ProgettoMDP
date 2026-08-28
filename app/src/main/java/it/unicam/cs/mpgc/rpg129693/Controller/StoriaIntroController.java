package it.unicam.cs.mpgc.rpg129693.Controller;
import it.unicam.cs.mpgc.rpg129693.InterfacciaGrafica.SceneManager;
import it.unicam.cs.mpgc.rpg129693.InterfacciaGrafica.Schermata;
import it.unicam.cs.mpgc.rpg129693.classiPrincipali.Eroe;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Duration;


public class StoriaIntroController {

    @FXML private Label lblTesto;
    @FXML private Button btnContinua;

    private Eroe eroeSelezionato;

    public void setEroe(Eroe eroe) {
        this.eroeSelezionato = eroe;
    }

    @FXML
    public void initialize() {
        preparaInterfacciaIniziale();
        avviaAnimazioneTesto();
    }
    @FXML
    public void onContinuaClick() {
        navigaACombattimento();
    }

    private void preparaInterfacciaIniziale() {
        lblTesto.setOpacity(0);

        if (btnContinua != null) {
            btnContinua.setVisible(false);
        }
    }

    private void avviaAnimazioneTesto() {
        FadeTransition fadeIn = new FadeTransition(Duration.seconds(2), lblTesto);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.setOnFinished(event -> mostraBottoneContinua());
        fadeIn.play();
    }

    private void mostraBottoneContinua() {
        if (btnContinua != null) {
            btnContinua.setVisible(true);
        }
    }

    private void navigaACombattimento() {
        FXMLLoader loader = SceneManager.getInstance().caricaConLoader(Schermata.COMBATTIMENTO);
        ScenaCombattimentoController controller = loader.getController();
        controller.setDatiPartita(eroeSelezionato, null);
    }
}