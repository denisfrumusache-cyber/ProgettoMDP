package it.unicam.cs.mpgc.rpg129693.controller;
import it.unicam.cs.mpgc.rpg129693.data.CaricatorePersonaggi;
import it.unicam.cs.mpgc.rpg129693.gui.SceneManager;
import it.unicam.cs.mpgc.rpg129693.gui.Schermata;
import it.unicam.cs.mpgc.rpg129693.utils.loggerBattaglia;
import it.unicam.cs.mpgc.rpg129693.model.Eroe;
import it.unicam.cs.mpgc.rpg129693.model.GestoreBattaglia;
import it.unicam.cs.mpgc.rpg129693.model.Torre;
import it.unicam.cs.mpgc.rpg129693.model.Villain;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;
import javafx.util.Duration;


public class ScenaCombattimentoController {

    @FXML private Label lblLivello;
    @FXML private Label lbDescrizione;
    @FXML private Button btnPausa;

    @FXML private Label lbNomeEroe;
    @FXML private ProgressBar pbHPEroe;
    @FXML private Label lbHPEroe;
    @FXML private ProgressBar pbStaminaEroe;
    @FXML private Label lbStaminaEroe;

    @FXML private Label lbNomeVillain;
    @FXML private ProgressBar pbHPVillain;
    @FXML private Label lbHPVillain;
    @FXML private ProgressBar pbStaminaVillain;
    @FXML private Label lbStaminaVillain;

    @FXML private Button btnAttaccoBase;
    @FXML private Button btnDifesa;
    @FXML private Button btnAttaccoSpeciale;

    @FXML private VBox panelPausa;
    @FXML private Button btnRiprendi;
    @FXML private Button btnEsci;


    private Eroe eroe;
    private Torre torre;
    private GestoreBattaglia battaglia;


    public void setDatiPartita(Eroe eroeSelezionato, Torre torreEsistente) {
        this.eroe = eroeSelezionato;

        if (torreEsistente == null) {
            CaricatorePersonaggi caricatore = new CaricatorePersonaggi("/PersonaggiJSON/personaggi.json");
            this.torre = caricatore.getTorre();
        } else {
            this.torre = torreEsistente;
        }

        Villain nemico = torre.getNemicoCorrente();
        this.battaglia = new GestoreBattaglia(eroe, nemico);

        lblLivello.setText("Livello: " + torre.getLivelloCorrente());
        lbNomeEroe.setText(eroe.getAlias());
        lbNomeVillain.setText(nemico.getAlias());

        lbDescrizione.setText("Inizia il combattimento!");
        if(!battaglia.isTurnoEroe()){
            lbDescrizione.setText(nemico.getAlias() + "è piu veloce e attacca per primo!");
            eseguiTurnoNemico();
        }
        aggiornaStatisticheUI();
    }

    @FXML
    public void onAttaccoBaseClick() {
        loggerBattaglia.scrivi(eroe.getAlias() + " usa Attacco Base!");
        eseguiTurno(1);

        lbDescrizione.setText(loggerBattaglia.ritiraLog());
    }
    @FXML
    public void onDifesaClick() {
        loggerBattaglia.scrivi(eroe.getAlias() + " si mette sulla difensiva!");
        eseguiTurno(2);
        lbDescrizione.setText(loggerBattaglia.ritiraLog());
    }

    @FXML
    public void onAttaccoSpecialeClick() {
        boolean successo = battaglia.eseguiAzioneEroe(3);
        if (!successo) {
            lbDescrizione.setText("Stamina insufficiente per il Quirk!");
            loggerBattaglia.ritiraLog();
            return;
        }
        lbDescrizione.setText(loggerBattaglia.ritiraLog());

        aggiornaStatisticheUI();
        if (!controllaFinePartita()) {
            eseguiTurnoNemico();
        }
    }

    private void eseguiTurno(int tipoAzioneEroe) {
        battaglia.eseguiAzioneEroe(tipoAzioneEroe);
        aggiornaStatisticheUI();
        if (!controllaFinePartita()) {
            eseguiTurnoNemico();
        }
    }

    private void eseguiTurnoNemico() {
        Villain nemico = torre.getNemicoCorrente();
        disabilitaBottoniAzione(true);
        PauseTransition pausa = new PauseTransition(Duration.seconds(1.5));
        pausa.setOnFinished(event -> {
            battaglia.eseguiAzioneVillain();
            lbDescrizione.setText(loggerBattaglia.ritiraLog());
            aggiornaStatisticheUI();

            if (!controllaFinePartita()){
                disabilitaBottoniAzione(false);
            }
        });
        pausa.play();
    }


    private void aggiornaStatisticheUI() {

        lbHPEroe.setText("HP: " + eroe.getHpAttuali() + "/" + eroe.getHpMax());
        pbHPEroe.setProgress((double) eroe.getHpAttuali() / eroe.getHpMax());

        lbStaminaEroe.setText("Stamina: " + eroe.getStaminaAttuale() + "/" + eroe.getStaminaMax());
        pbStaminaEroe.setProgress((double) eroe.getStaminaAttuale() / eroe.getStaminaMax());

        Villain nemico = torre.getNemicoCorrente();
        lbHPVillain.setText("HP: " + nemico.getHpAttuali() + "/" + nemico.getHpMax());
        pbHPVillain.setProgress((double) nemico.getHpAttuali() / nemico.getHpMax());

        lbStaminaVillain.setText("Stamina: " + nemico.getStaminaAttuale() + "/" + nemico.getStaminaMax());
        pbStaminaVillain.setProgress((double) nemico.getStaminaAttuale() / nemico.getStaminaMax());
    }
    private boolean controllaFinePartita() {
        if (!battaglia.isFinita()) {
            return false;
        }
        PauseTransition pausaFinale = new PauseTransition(Duration.seconds(2));
        pausaFinale.setOnFinished(event -> {
            if (!eroe.eVivo()) {
                navigaAFineGioco(false);
            } else {
                gestisciVittoria();
            }
        });
        pausaFinale.play();

        return true;
    }

    private void gestisciVittoria() {
        int livelloPrima = eroe.getLivello();

        Villain nemicoSconfitto = torre.getNemicoCorrente();
        eroe.guadagnaEsperienza(nemicoSconfitto.getEsperienzaRilasciata());
        
        int livelloDopo = eroe.getLivello();
        if (livelloDopo > livelloPrima){
           lbDescrizione.setText( "⭐ LEVEL UP! Sei salito al livello " + livelloDopo + "!\n"
                    + "HP Max: " + eroe.getHpMax()
                    + "  Stamina Max: " + eroe.getStaminaMax());
        }
        aggiornaStatisticheUI();
        PauseTransition pausaLevelUp = new PauseTransition(Duration.seconds(1.2));
        pausaLevelUp.setOnFinished(event -> {
            torre.avanzaLivello();
            if (torre.isTorreFinita()) {
                navigaAFineGioco(true);
            } else {
                navigaAIntermezzo();
            }
        });
        pausaLevelUp.play();
    }

    @FXML
    public void onPausaClick() {
        if (panelPausa != null) panelPausa.setVisible(true);
        disabilitaBottoniAzione(true);
    }
    @FXML
    public void onRiprendiClick() {
        if (panelPausa != null) panelPausa.setVisible(false);
        disabilitaBottoniAzione(false);
    }
    @FXML
    public void onEsciClick() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Abbandona Scontro");
        alert.setHeaderText("Non puoi salvare durante un combattimento!");
        alert.setContentText("I progressi non salvati andranno persi. Sei sicuro?");
        alert.showAndWait().ifPresent(risposta -> {
            if (risposta == ButtonType.OK) {
                SceneManager.getInstance().cambiaSchermata(Schermata.MENU);
            }
        });
    }
    private void disabilitaBottoniAzione(boolean disabilita) {
        btnAttaccoBase.setDisable(disabilita);
        btnDifesa.setDisable(disabilita);
        btnAttaccoSpeciale.setDisable(disabilita);
    }
    private void navigaAFineGioco(boolean vittoria) {
        FXMLLoader loader = SceneManager.getInstance().caricaConLoader(Schermata.FINE_GIOCO);
        FineGiocoController controller = loader.getController();
        controller.setVittoria(vittoria);
    }
    private void navigaAIntermezzo() {
        FXMLLoader loader = SceneManager.getInstance().caricaConLoader(Schermata.INTERMEZZO);
        IntraLivelliController controller = loader.getController();
        controller.setEroe(eroe);
        controller.setTorre(torre);
    }
}