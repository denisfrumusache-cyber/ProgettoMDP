package it.unicam.cs.mpgc.rpg129693.Data;

import it.unicam.cs.mpgc.rpg129693.classiAstratte.Quirk;
import it.unicam.cs.mpgc.rpg129693.classiPrincipali.Eroe;
import it.unicam.cs.mpgc.rpg129693.classiPrincipali.Torre;
import it.unicam.cs.mpgc.rpg129693.classiPrincipali.Villain;
import com.google.gson.Gson;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("all")
public class CaricatorePersonaggi {
    // Record d'appoggio per mappare il JSON
    private record DatiPersonaggio(
            String tipo, String id, String nome, String alias,
            int hpMax, int staminaMax, int potenza, int velocita, int tecnica,
            String quirk, int esperienzaRilasciata, int livelloTorre
    ){}
    
    private List<Eroe> eroi;
    private Torre nemici;

    public CaricatorePersonaggi(String risorsaJson) {
        this.eroi = new ArrayList<>();
        this.nemici = new Torre();
        this.carica(risorsaJson);
    }

    private void carica(String risorsaJson){
        Gson gson = new Gson();
        InputStream stream = CaricatorePersonaggi.class.getResourceAsStream(risorsaJson);
        if (stream == null) {
            throw new RuntimeException("File di risorse JSON non trovato: " + risorsaJson);
        }

        try (Reader reader = new InputStreamReader(stream, StandardCharsets.UTF_8);
             BufferedReader bufferReader = new BufferedReader(reader)) {
             
            DatiPersonaggio[] datiPersonaggi = gson.fromJson(bufferReader, DatiPersonaggio[].class);

            for (DatiPersonaggio dato : datiPersonaggi){
                Quirk quirkPersonaggio = QuirkFactory.creaQuirk(dato.quirk());
                
                if ("Eroe".equalsIgnoreCase(dato.tipo())){
                    Eroe eroe = new Eroe(
                        dato.id(), 
                        dato.nome(), 
                        dato.alias(),
                        dato.hpMax(),
                        dato.staminaMax(),
                        dato.potenza(),
                        dato.velocita(),
                        dato.tecnica(), 
                        quirkPersonaggio
                    );
                    eroi.add(eroe);
                } else if ("Villain".equalsIgnoreCase(dato.tipo())){
                    Villain villain = new Villain(
                        dato.id(), 
                        dato.nome(), 
                        dato.alias(),
                        dato.hpMax(),
                        dato.staminaMax(),
                        dato.potenza(),
                        dato.velocita(),
                        dato.tecnica(), 
                        quirkPersonaggio,
                        dato.esperienzaRilasciata(),
                        dato.livelloTorre()
                    );
                    nemici.aggiungiNemici(villain);
                }
            }
        } catch (IOException e){
            System.out.println("Qualcosa è andato storto: " + e.getMessage());
        }
    }

    public List<Eroe> getEroi() {
        return eroi;
    }

    public Torre getTorre() {
        return nemici;
    }
}
