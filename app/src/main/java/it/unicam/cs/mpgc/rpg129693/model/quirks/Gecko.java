package it.unicam.cs.mpgc.rpg129693.model.quirks;

import it.unicam.cs.mpgc.rpg129693.model.Personaggio;
import it.unicam.cs.mpgc.rpg129693.utils.loggerBattaglia;

public class Gecko extends Quirk {
    public Gecko() {
        super(
            "Gecko",
            10,
            "Aumenta la difesa e rigenera leggermente la salute grazie alle capacità da lucertola."
        );
    }

    @Override
    public void eseguiAzione(Personaggio utilizzatore, Personaggio bersaglio) {
        utilizzatore.attaccoBase(bersaglio);
        difendiERigenera(utilizzatore);
    }

    private void difendiERigenera(Personaggio utilizzatore) {
        utilizzatore.difenditi();
        recuperaHp(utilizzatore);
        loggerBattaglia.scrivi(utilizzatore.getAlias() + " si rigenera e aumenta le sue difese");
    }

    private static void recuperaHp(Personaggio utilizzatore) {
        utilizzatore.setHpAttuali(Math.min(utilizzatore.getHpMax(), utilizzatore.getHpAttuali() + 25));
    }
}
