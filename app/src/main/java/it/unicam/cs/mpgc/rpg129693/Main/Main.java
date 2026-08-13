package it.unicam.cs.mpgc.rpg129693.Main;

import it.unicam.cs.mpgc.rpg129693.InterfacciaGrafica.AppGrafica;
import javafx.application.Application;

public class Main {
    public static void main(String[] args) {
        // Avvia l'applicazione JavaFX delegando tutto ad AppGrafica
        Application.launch(AppGrafica.class, args);
    }
}
