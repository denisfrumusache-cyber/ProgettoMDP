package it.unicam.cs.mpgc.rpg129693.Main;

import it.unicam.cs.mpgc.rpg129693.classiPrincipali.Eroe;
import it.unicam.cs.mpgc.rpg129693.classiPrincipali.Villain;
import it.unicam.cs.mpgc.rpg129693.classiPrincipali.Torre;
import it.unicam.cs.mpgc.rpg129693.classiPrincipali.GestoreBattaglia;
import it.unicam.cs.mpgc.rpg129693.Quirks.OneForAll;
import it.unicam.cs.mpgc.rpg129693.Quirks.Gecko;
import it.unicam.cs.mpgc.rpg129693.Quirks.CrescitaMuscolare;
import it.unicam.cs.mpgc.rpg129693.Quirks.Decadimento;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Creazione dell'eroe del giocatore
        Eroe deku = new Eroe(
            "1",
            "Izuku Midoriya",
            "Deku",
            500, // hpMax
            50, // staminaMax
            50,  // potenza
            30,  // velocita
            40,  // tecnica
            new OneForAll()
        );

        // Creazione della Torre e inserimento dei nemici per piano
        Torre torre = new Torre();
        torre.aggiungiNemici(new Villain("v1", "Shuichi Iguchi", "Spinner", 300,25, 20, 20, 20, new Gecko(), 50, 1));
        torre.aggiungiNemici(new Villain("v2", "Goto Imasuji", "Muscular", 400, 30, 40, 30, 30, new CrescitaMuscolare(), 100, 2));
        torre.aggiungiNemici(new Villain("v3", "Tomura Shigaraki", "Shigaraki", 500, 80, 45, 35, 45, new Decadimento(), 150, 3));

        Scanner scanner = new Scanner(System.in);
        System.out.println("=== BENVENUTO NELLA TORRE DEI COMBATTIMENTI ===");
        System.out.println("Sconfiggi tutti i Villain per completare la scalata!");

        // Loop principale dei piani della torre
        while (deku.eVivo() && !torre.isTorreFinita()) {
            Villain nemicoCorrente = torre.getNemicoCorrente();
            
            System.out.println("\n=================================");
            System.out.println("PIANO DELLA TORRE: " + torre.getLivelloCorrente());
            System.out.println("Prossimo avversario: " + nemicoCorrente.getAlias());
            System.out.println("=================================");

            // Istanziamo il gestore per la singola battaglia
            GestoreBattaglia scontro = new GestoreBattaglia(deku, nemicoCorrente);

            // Loop della singola battaglia
            while (!scontro.isFinita()) {
                System.out.println("\nStato combattenti:");
                System.out.println(deku.getAlias() + " -> HP: " + deku.getHpAttuali() + "/" + deku.getHpMax() + " | Stamina: " + deku.getStaminaAttuale() + "/" + deku.getStaminaMax());
                System.out.println(nemicoCorrente.getAlias() + " -> HP: " + nemicoCorrente.getHpAttuali() + "/" + nemicoCorrente.getHpMax() + " | Stamina: " + nemicoCorrente.getStaminaAttuale() + "/" + nemicoCorrente.getStaminaMax());
                System.out.println("------------------------");

                if (scontro.isTurnoEroe()) {
                    System.out.println("È il tuo turno! Scegli l'azione:");
                    System.out.println("1) Attacco Base");
                    System.out.println("2) Difesa");
                    System.out.println("3) Quirk (" + deku.getQuirk().getNome() + " - Costo: " + deku.getQuirk().getCostoStamina() + ")");
                    System.out.print("Scelta: ");

                    if (scanner.hasNextInt()) {
                        int scelta = scanner.nextInt();
                        boolean mossaEseguita = scontro.eseguiAzioneEroe(scelta);
                        if (!mossaEseguita) {
                            System.out.println("Azione non eseguita! Controlla la stamina o inserisci una scelta valida.");
                        }
                    } else {
                        scanner.next(); // Consuma input non valido
                        System.out.println("Inserisci un numero valido!");
                    }
                } else {
                    System.out.println("\nÈ il turno di " + nemicoCorrente.getAlias() + "...");
                    scontro.eseguiAzioneVillain();
                }
            }

            // Fine della singola battaglia
            if (deku.eVivo()) {
                System.out.println("\nHai sconfitto " + nemicoCorrente.getAlias() + "!");
                deku.guadagnaEsperienza(nemicoCorrente.getEsperienzaRilasciata());
                System.out.println("Livello attuale Deku: " + deku.getLivello() + " | Esperienza: " + deku.getEsperienza() + "/100");
                
                // Passiamo al livello successivo della torre
                torre.avanzaLivello();
            } else {
                System.out.println("\nDeku è stato sconfitto... Scontro terminato.");
                break;
            }
        }

        // Verifica completamento della torre
        if (torre.isTorreFinita() && deku.eVivo()) {
            System.out.println("\n=======================================================");
            System.out.println("COMPLIMENTI! Hai completato tutti i piani della torre!");
            System.out.println("=======================================================");
        } else {
            System.out.println("\n========================");
            System.out.println("GAME OVER! La torre ti ha sconfitto.");
            System.out.println("========================");
        }
        scanner.close();
    }
}
