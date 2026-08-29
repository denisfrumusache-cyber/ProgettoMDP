package it.unicam.cs.mpgc.rpg129693.data;

import it.unicam.cs.mpgc.rpg129693.model.quirks.*;
import it.unicam.cs.mpgc.rpg129693.model.quirks.Quirk;
import java.util.Map;
import java.util.function.Supplier;

public class QuirkFactory {
    private static final Map<String, Supplier<Quirk>> REGISTRO_QUIRK = Map.of(
            "OneForAll",              OneForAll::new,
            "Esplosione",             Esplosione::new,
            "Mezzo Caldo Mezzo Freddo", MezzoCaldoMezzoFreddo::new,
            "Gecko",                  Gecko::new,
            "Crescita Muscolare",     CrescitaMuscolare::new,
            "Decadimento",            Decadimento::new
    );

    private QuirkFactory() {
        throw new UnsupportedOperationException("Questa classe non può essere istanziata");
    }

    /** @return una nuova istanza del Quirk associato al nome fornito. */
    public static Quirk creaQuirk(String nomeQuirk) {
        if (nomeQuirk == null) {
            throw new IllegalArgumentException("Il nome del quirk non può essere null");
        }
        Supplier<Quirk> costruttoreQuirk = REGISTRO_QUIRK.get(nomeQuirk);
        if (costruttoreQuirk == null) {
            throw new IllegalArgumentException("Nessun quirk registrato con il nome: " + nomeQuirk);
        }
        return costruttoreQuirk.get();
    }
}
