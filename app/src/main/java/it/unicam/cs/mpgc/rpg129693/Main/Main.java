package it.unicam.cs.mpgc.rpg129693.Main;

import it.unicam.cs.mpgc.rpg129693.Data.CaricatorePersonaggi;
import it.unicam.cs.mpgc.rpg129693.classiPrincipali.Eroe;
import it.unicam.cs.mpgc.rpg129693.classiPrincipali.GestoreBattaglia;
import it.unicam.cs.mpgc.rpg129693.classiPrincipali.Torre;
import it.unicam.cs.mpgc.rpg129693.classiPrincipali.Villain;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Benvenuto in MHA RPG (Console Test)");

        CaricatorePersonaggi caricatore = new CaricatorePersonaggi("/PerosonaggiJSON/personaggi.json");
        List<Eroe> eroi = caricatore.getEroi();
        Torre torre = caricatore.getTorre();

        System.out.println("Scegli un eroe:");
        for (int i = 0; i < eroi.size(); i++) {
            System.out.println((i+1) + ") " + eroi.get(i).getNome() + " (" + eroi.get(i).getAlias() + ")");
        }
        
        int scelta = scanner.nextInt();
        Eroe eroeSelezionato = eroi.get(scelta - 1);

        while (!torre.isTorreFinita() && eroeSelezionato.eVivo()) {
            Villain nemico = torre.getNemicoCorrente();
            System.out.println("\n--- PIANO " + torre.getLivelloCorrente() + " ---");
            System.out.println("Affronti " + nemico.getNome() + "!");

            GestoreBattaglia battaglia = new GestoreBattaglia(eroeSelezionato, nemico);

            while (!battaglia.isFinita()) {
                System.out.println("\nHP Eroe: " + eroeSelezionato.getHpAttuali() + "/" + eroeSelezionato.getHpMax() + 
                                   " | STM: " + eroeSelezionato.getStaminaAttuale() + "/" + eroeSelezionato.getStaminaMax());
                System.out.println("HP Villain: " + nemico.getHpAttuali() + "/" + nemico.getHpMax() + 
                                   " | STM: " + nemico.getStaminaAttuale() + "/" + nemico.getStaminaMax());

                if (battaglia.isTurnoEroe()) {
                    System.out.println("\nÈ il tuo turno!");
                    System.out.println("1) Attacco Base");
                    System.out.println("2) Difenditi");
                    System.out.println("3) Usa Quirk (" + eroeSelezionato.getQuirk().getNome() + ")");
                    int azione = scanner.nextInt();
                    
                    boolean riuscita = battaglia.eseguiAzioneEroe(azione);
                    if (!riuscita) {
                        System.out.println("Azione non valida o stamina insufficiente.");
                    }
                } else {
                    System.out.println("\nTurno del nemico...");
                    battaglia.eseguiAzioneVillain();
                }
            }

            if (!eroeSelezionato.eVivo()) {
                System.out.println("Sei stato sconfitto!");
            } else {
                System.out.println("Hai sconfitto " + nemico.getNome() + "!");
                eroeSelezionato.guadagnaEsperienza(nemico.getEsperienzaRilasciata());
                torre.avanzaLivello();
            }
        }

        if (torre.isTorreFinita()) {
            System.out.println("Hai completato la torre! Vittoria!");
        }
        scanner.close();
    }
}
