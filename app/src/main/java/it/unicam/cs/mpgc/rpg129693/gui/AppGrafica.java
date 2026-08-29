package it.unicam.cs.mpgc.rpg129693.gui;
import javafx.application.Application;
import javafx.stage.Stage;
public class AppGrafica extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("My Hero Academia RPG");
        primaryStage.setResizable(false);
        SceneManager.getInstance().setStage(primaryStage);
        SceneManager.getInstance().cambiaSchermata(Schermata.MENU);
    }
}
