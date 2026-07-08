package it.unicam.cs.mpgc.rpg129693.Main;

import it.unicam.cs.mpgc.rpg129693.classiPrincipali.Eroe;
import it.unicam.cs.mpgc.rpg129693.classiPrincipali.Villain;
import it.unicam.cs.mpgc.rpg129693.classiPrincipali.GestoreBattaglia;
import it.unicam.cs.mpgc.rpg129693.Quirks.OneForAll;
import it.unicam.cs.mpgc.rpg129693.Quirks.Decadimento;

public class Main {
    public static void main(String[] args) {
        // Creazione di un eroe di prova (Deku)
        Eroe deku = new Eroe(
            "1",
            "Izuku Midoriya",
            "Deku",
            500, // hpMax
            100, // staminaMax
            50,  // potenza
            30,  // velocita
            40,  // tecnica
            new OneForAll()
        );

        // Creazione di un villain di prova (Shigaraki)
        Villain shigaraki = new Villain(
            "2",
            "Tomura Shigaraki",
            "Shigaraki",
            450, // hpMax
            120, // staminaMax
            45,  // potenza
            35,  // velocita
            45,  // tecnica
            new Decadimento(),
            150, // esperienzaRilasciata
            3    // livelloTorre
        );

        // Avvio dello scontro
        GestoreBattaglia scontro = new GestoreBattaglia(deku, shigaraki);
        java.util.Scanner scanner = new java.util.Scanner(System.in);

        System.out.println("=== INIZIO BATTAGLIA ===");
        System.out.println(deku.getAlias() + " VS " + shigaraki.getAlias());
        System.out.println("========================");

        while (!scontro.isFinita()) {
            System.out.println("\nStato combattenti:");
            System.out.println(deku.getAlias() + " -> HP: " + deku.getHpAttuali() + "/" + deku.getHpMax() + " | Stamina: " + deku.getStaminaAttuale() + "/" + deku.getStaminaMax());
            System.out.println(shigaraki.getAlias() + " -> HP: " + shigaraki.getHpAttuali() + "/" + shigaraki.getHpMax() + " | Stamina: " + shigaraki.getStaminaAttuale() + "/" + shigaraki.getStaminaMax());
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
                System.out.println("\nÈ il turno di " + shigaraki.getAlias() + "...");
                scontro.eseguiAzioneVillain();
            }
        }

        System.out.println("\n=== FINE BATTAGLIA ===");
        if (deku.eVivo()) {
            System.out.println("VINCITORE: " + deku.getAlias() + "! L'eroe ha trionfato!");
        } else {
            System.out.println("VINCITORE: " + shigaraki.getAlias() + "! La giustizia ha perso...");
        }
        scanner.close();
    }
}
