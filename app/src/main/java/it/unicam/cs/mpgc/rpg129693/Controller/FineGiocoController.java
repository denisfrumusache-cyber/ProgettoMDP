
package it.unicam.cs.mpgc.rpg129693.Controller;
import it.unicam.cs.mpgc.rpg129693.Data.GestoreSalvataggi;
import it.unicam.cs.mpgc.rpg129693.Gui.SceneManager;
import it.unicam.cs.mpgc.rpg129693.Gui.Schermata;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
public class FineGiocoController {
    @FXML private Label lblTitolo;
    @FXML private Label lblSottotitolo;
    @FXML private Button btnMenuPrincipale;

    public void setVittoria(boolean vittoria) {
        GestoreSalvataggi.eliminaSalvataggio();

        if(vittoria){
            mostraSchermataVittoria();
        } else {
            mostraSchermataSconfitta();
        }
    }

    @FXML
    public void onMenuPrincipaleClick() {
        SceneManager.getInstance().cambiaSchermata(Schermata.MENU);
    }

    private void mostraSchermataVittoria() {
        lblTitolo.setText("Hai salvato il mondo!");
        lblSottotitolo.setText("L'Unione dei Villain è stata sconfitta definitivamente.");
        lblTitolo.setStyle("-fx-text-fill: gold;");
        lblSottotitolo.setStyle("-fx-text-fill: gold;");
    }
    private void mostraSchermataSconfitta() {
        lblTitolo.setText("Il mondo è caduto...");
        lblSottotitolo.setText("I Villain hanno trionfato. Gli eroi sono caduti.");
        lblTitolo.setStyle("-fx-text-fill: red;");
        lblSottotitolo.setStyle("-fx-text-fill: red;");
    }
}
