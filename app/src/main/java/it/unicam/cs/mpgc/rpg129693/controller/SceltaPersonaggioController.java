package it.unicam.cs.mpgc.rpg129693.controller;
import it.unicam.cs.mpgc.rpg129693.data.CaricatorePersonaggi;
import it.unicam.cs.mpgc.rpg129693.gui.SceneManager;
import it.unicam.cs.mpgc.rpg129693.gui.Schermata;
import it.unicam.cs.mpgc.rpg129693.model.Eroe;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import java.util.List;
public class SceltaPersonaggioController {

    @FXML private VBox cardDeku;
    @FXML private VBox cardBakugo;
    @FXML private VBox cardTotoroki;
    @FXML private Button btnInizia;

    private VBox cardSelezionata = null;
    private Eroe eroeSelezionato = null;
    private List<Eroe> eroi;


    @FXML
    public void initialize() {
        caricaEroiDisponibili();
    }

    @FXML
    public void onCardCliccata(MouseEvent event) {
        VBox cardCliccata = (VBox) event.getSource();
        impostaEroeSelezionato(cardCliccata);
        aggiornaStileInterfaccia(cardCliccata);
    }

    @FXML
    public void onIniziaClick() {
        if (eroeSelezionato == null) {
            return;
        }
        navigaAIntroStoria(eroeSelezionato);
    }

    private void caricaEroiDisponibili() {
        CaricatorePersonaggi caricatore = new CaricatorePersonaggi("/PersonaggiJSON/personaggi.json");
        eroi = caricatore.getEroi();
    }
    private void impostaEroeSelezionato(VBox card) {
        this.cardSelezionata = card;
        if (card == cardDeku) {
            eroeSelezionato = eroi.get(0);
        } else if (card == cardBakugo) {
            eroeSelezionato = eroi.get(1);
        } else if (card == cardTotoroki) {
            eroeSelezionato = eroi.get(2);
        }
    }
    private void aggiornaStileInterfaccia(VBox cardDaEvidenziare) {
        rimuoviEvidenziazioneTutteLeCard();
        applicaStileEvidenziato(cardDaEvidenziare);
        btnInizia.setDisable(false);
    }
    private void rimuoviEvidenziazioneTutteLeCard() {
        String stileBase = "-fx-border-color: gray; -fx-border-radius: 10; -fx-padding: 15; -fx-border-width: 3;";
        cardDeku.setStyle(stileBase);
        cardBakugo.setStyle(stileBase);
        cardTotoroki.setStyle(stileBase);
    }
    private void applicaStileEvidenziato(VBox card) {
        String stileEvidenziato = "-fx-border-color: gold; -fx-border-radius: 10; -fx-padding: 15; -fx-border-width: 3;";
        card.setStyle(stileEvidenziato);
    }
    private void navigaAIntroStoria(Eroe eroe) {
        FXMLLoader loader = SceneManager.getInstance().caricaConLoader(Schermata.INTRO_STORIA);
        StoriaIntroController controller = loader.getController();
        controller.setEroe(eroe);
    }
}
