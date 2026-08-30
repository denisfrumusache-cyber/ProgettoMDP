package it.unicam.cs.mpgc.rpg129693.controller;

import it.unicam.cs.mpgc.rpg129693.data.CaricatorePersonaggi;
import it.unicam.cs.mpgc.rpg129693.data.GestoreSalvataggi;
import it.unicam.cs.mpgc.rpg129693.data.StatoSalvataggio;
import it.unicam.cs.mpgc.rpg129693.gui.SceneManager;
import it.unicam.cs.mpgc.rpg129693.gui.Schermata;
import it.unicam.cs.mpgc.rpg129693.model.Eroe;
import it.unicam.cs.mpgc.rpg129693.model.Torre;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

public class MenuController {
    @FXML private Button btnNuovaPartita;
    @FXML private Button btnCaricaPartita;

    @FXML
    public void onNuovaPartitaClick() {
        SceneManager.getInstance().cambiaSchermata(Schermata.SCELTA_PERSONAGGIO);
    }

    @FXML
    public void onCaricaPartitaClick() {
        StatoSalvataggio salvataggio = GestoreSalvataggi.caricaPartita();
        if (salvataggio == null) {
            mostraAvvisoNessunSalvataggio();
            return;
        }
        riprendiPartitaSalvata(salvataggio);
    }

    private void mostraAvvisoNessunSalvataggio() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Nessun Salvataggio");
        alert.setHeaderText("Non è stata trovata nessuna partita salvata.");
        alert.showAndWait();
    }

    private void riprendiPartitaSalvata(StatoSalvataggio salvataggio) {
        Eroe eroe = salvataggio.ricostruisciEroe();
        Torre torre = ricostruisciTorreDalSalvataggio(salvataggio);
        navigaAIntermezzo(eroe, torre);
    }

    private Torre ricostruisciTorreDalSalvataggio(StatoSalvataggio salvataggio) {
        CaricatorePersonaggi caricatore = new CaricatorePersonaggi("/PersonaggiJSON/personaggi.json");
        Torre torre = caricatore.getTorre();
        torre.setLivelloCorrente(salvataggio.getLivelloTorreCorrente());
        return torre;
    }

    private void navigaAIntermezzo(Eroe eroe, Torre torre) {
        FXMLLoader loader = SceneManager.getInstance().caricaConLoader(Schermata.INTERMEZZO);
        IntraLivelliController controller = loader.getController();
        controller.setEroe(eroe);
        controller.setTorre(torre);
    }
}

