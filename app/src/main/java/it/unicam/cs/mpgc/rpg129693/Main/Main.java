package it.unicam.cs.mpgc.rpg129693.Main;

import it.unicam.cs.mpgc.rpg129693.classiPrincipali.Eroe;
import it.unicam.cs.mpgc.rpg129693.classiPrincipali.Villain;
import it.unicam.cs.mpgc.rpg129693.classiPrincipali.Torre;
import it.unicam.cs.mpgc.rpg129693.classiPrincipali.GestoreBattaglia;
import it.unicam.cs.mpgc.rpg129693.Data.CaricatorePersonaggi;
import java.util.Scanner;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Carichiamo l'eroe e la torre popolata direttamente dal file personaggi.json
        CaricatorePersonaggi caricatore = new CaricatorePersonaggi("/personaggi.json");
        Torre torre = caricatore.getTorre();
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== BENVENUTO NELLA TORRE DEI COMBATTIMENTI ===");
        System.out.println("Seleziona il tuo Eroe:");
        List<Eroe> eroiDisponibili = caricatore.getEroi();
        for (int i = 0; i < eroiDisponibili.size(); i++) {
            Eroe e = eroiDisponibili.get(i);
            System.out.println((i + 1) + ") " + e.getAlias() + " (Quirk: " + e.getQuirk().getNome() + ")");
        }
        
        int sceltaEroe = -1;
        while (sceltaEroe < 0 || sceltaEroe >= eroiDisponibili.size()) {
            System.out.print("Scelta (numero): ");
            if (scanner.hasNextInt()) {
                sceltaEroe = scanner.nextInt() - 1;
                if (sceltaEroe < 0 || sceltaEroe >= eroiDisponibili.size()) {
                    System.out.println("Numero non valido. Riprova.");
                }
            } else {
                scanner.next(); // Consuma input non valido
                System.out.println("Inserisci un numero valido!");
            }
        }

        Eroe eroeSelezionato = eroiDisponibili.get(sceltaEroe);
        System.out.println("\nHai selezionato: " + eroeSelezionato.getAlias() + "!");
        System.out.println("Sconfiggi tutti i Villain per completare la scalata!");

        // Loop principale dei piani della torre
        while (eroeSelezionato.eVivo() && !torre.isTorreFinita()) {
            Villain nemicoCorrente = torre.getNemicoCorrente();
            
            System.out.println("\n=================================");
            System.out.println("PIANO DELLA TORRE: " + torre.getLivelloCorrente());
            System.out.println("Prossimo avversario: " + nemicoCorrente.getAlias());
            System.out.println("=================================");

            // Istanziamo il gestore per la singola battaglia
            GestoreBattaglia scontro = new GestoreBattaglia(eroeSelezionato, nemicoCorrente);

            // Loop della singola battaglia
            while (!scontro.isFinita()) {
                System.out.println("\nStato combattenti:");
                System.out.println(eroeSelezionato.getAlias() + " -> HP: " + eroeSelezionato.getHpAttuali() + "/" + eroeSelezionato.getHpMax() + " | Stamina: " + eroeSelezionato.getStaminaAttuale() + "/" + eroeSelezionato.getStaminaMax());
                System.out.println(nemicoCorrente.getAlias() + " -> HP: " + nemicoCorrente.getHpAttuali() + "/" + nemicoCorrente.getHpMax() + " | Stamina: " + nemicoCorrente.getStaminaAttuale() + "/" + nemicoCorrente.getStaminaMax());
                System.out.println("------------------------");

                if (scontro.isTurnoEroe()) {
                    System.out.println("È il tuo turno! Scegli l'azione:");
                    System.out.println("1) Attacco Base");
                    System.out.println("2) Difesa");
                    System.out.println("3) Quirk (" + eroeSelezionato.getQuirk().getNome() + " - Costo: " + eroeSelezionato.getQuirk().getCostoStamina() + ")");
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
            if (eroeSelezionato.eVivo()) {
                System.out.println("\nHai sconfitto " + nemicoCorrente.getAlias() + "!");
                eroeSelezionato.guadagnaEsperienza(nemicoCorrente.getEsperienzaRilasciata());
                System.out.println("Livello attuale " + eroeSelezionato.getAlias() + ": " + eroeSelezionato.getLivello() + " | Esperienza: " + eroeSelezionato.getEsperienza() + "/100");
                
                // Passiamo al livello successivo della torre
                torre.avanzaLivello();
            } else {
                System.out.println("\n" + eroeSelezionato.getAlias() + " è stato sconfitto... Scontro terminato.");
                break;
            }
        }

        // Verifica completamento della torre
        if (torre.isTorreFinita() && eroeSelezionato.eVivo()) {
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
