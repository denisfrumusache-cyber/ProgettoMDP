package it.unicam.cs.mpgc.rpg129693.Model.Quirks;

import it.unicam.cs.mpgc.rpg129693.Model.Personaggio;
import it.unicam.cs.mpgc.rpg129693.Utils.loggerBattaglia;

public class OneForAll extends Quirk {

    public OneForAll() {
        super(
            "OneForAll",
            25,
            "Quirk ereditario che accumula energia fisica pura. Canalizzando il potere tramite il 'Full Cowl', " +
            "l'utilizzatore rilascia devastanti Smash fisici (come il Detroit Smash) che aumentano di potenza " +
            "quando la salute è critica."
        );
    }

    @Override
    public void eseguiAzione(Personaggio utilizzatore, Personaggio bersaglio) {
        if (percentualeHp(utilizzatore) < 0.30)
            eseguiSmashPotenziato(utilizzatore, bersaglio);
        else
            eseguiSmashNormale(utilizzatore, bersaglio);
    }

    private void eseguiSmashPotenziato(Personaggio utilizzatore, Personaggio bersaglio) {
        loggerBattaglia.scrivi(utilizzatore.getAlias() + " usa United States of SMASH!");
        bersaglio.riceviDanno(utilizzatore, utilizzatore.getPotenza() * 4);
    }

    private void eseguiSmashNormale(Personaggio utilizzatore, Personaggio bersaglio) {
        loggerBattaglia.scrivi(utilizzatore.getAlias() + " usa Delaware Smash!");
        bersaglio.riceviDanno(utilizzatore, utilizzatore.getPotenza() * 2);
    }

    private static double percentualeHp(Personaggio personaggio) {
        return (double) personaggio.getHpAttuali() / personaggio.getHpMax();
    }
}
