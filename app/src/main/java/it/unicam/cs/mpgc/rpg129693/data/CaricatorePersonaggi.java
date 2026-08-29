package it.unicam.cs.mpgc.rpg129693.data;

import it.unicam.cs.mpgc.rpg129693.model.quirks.Quirk;
import it.unicam.cs.mpgc.rpg129693.model.Eroe;
import it.unicam.cs.mpgc.rpg129693.model.Torre;
import it.unicam.cs.mpgc.rpg129693.model.Villain;
import com.google.gson.Gson;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class CaricatorePersonaggi {
    // Record d'appoggio per mappare il JSON
    private record DatiPersonaggio(
            String tipo, String id, String nome, String alias,
            int hpMax, int staminaMax, int potenza, int velocita, int tecnica,
            String quirk, int esperienzaRilasciata, int livelloTorre
    ) {}

    private List<Eroe> eroi;
    private Torre nemici;

    public CaricatorePersonaggi(String risorsaJson) {
        this.eroi = new ArrayList<>();
        this.nemici = new Torre();
        carica(risorsaJson);
    }

    private void carica(String risorsaJson) {
        DatiPersonaggio[] dati = leggiDatiJson(risorsaJson);
        for (DatiPersonaggio dato : dati) {
            registraPersonaggio(dato);
        }
    }

    private DatiPersonaggio[] leggiDatiJson(String risorsaJson) {
        InputStream stream = CaricatorePersonaggi.class.getResourceAsStream(risorsaJson);
        if (stream == null) {
            throw new RuntimeException("File di risorse JSON non trovato: " + risorsaJson);
        }
        try (Reader reader = new InputStreamReader(stream, StandardCharsets.UTF_8);
             BufferedReader bufferedReader = new BufferedReader(reader)) {
            return new Gson().fromJson(bufferedReader, DatiPersonaggio[].class);
        } catch (IOException e) {
            throw new RuntimeException("Errore durante la lettura del file JSON: " + e.getMessage(), e);
        }
    }

    private void registraPersonaggio(DatiPersonaggio dato) {
        if ("Eroe".equalsIgnoreCase(dato.tipo()))
            eroi.add(creaEroe(dato));
        else if ("Villain".equalsIgnoreCase(dato.tipo()))
            nemici.aggiungiNemici(creaVillain(dato));
    }

    private Eroe creaEroe(DatiPersonaggio dato) {
        Quirk quirk = QuirkFactory.creaQuirk(dato.quirk());
        return new Eroe(dato.id(), dato.nome(), dato.alias(), dato.hpMax(), dato.staminaMax(),
                dato.potenza(), dato.velocita(), dato.tecnica(), quirk);
    }

    private Villain creaVillain(DatiPersonaggio dato) {
        Quirk quirk = QuirkFactory.creaQuirk(dato.quirk());
        return new Villain(dato.id(), dato.nome(), dato.alias(), dato.hpMax(), dato.staminaMax(),
                dato.potenza(), dato.velocita(), dato.tecnica(), quirk,
                dato.esperienzaRilasciata(), dato.livelloTorre());
    }

    public List<Eroe> getEroi() {
        return eroi;
    }

    public Torre getTorre() {
        return nemici;
    }
}
