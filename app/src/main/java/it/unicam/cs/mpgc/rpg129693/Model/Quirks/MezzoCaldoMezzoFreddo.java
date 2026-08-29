package it.unicam.cs.mpgc.rpg129693.Model.Quirks;

import it.unicam.cs.mpgc.rpg129693.Model.Personaggio;
import it.unicam.cs.mpgc.rpg129693.Utils.loggerBattaglia;

public class MezzoCaldoMezzoFreddo extends Quirk {
    private boolean usaGhiaccio = true;

    public MezzoCaldoMezzoFreddo() {
        super(
            "Mezzo Caldo Mezzo Freddo",
            20,
            "Conferisce il controllo del ghiaccio dal lato destro e del fuoco dal sinistro. Per regolare la " +
            "propria temperatura corporea, l'utilizzatore alterna attacchi di ghiaccio per congelare e rallentare " +
            "l'avversario a fiammate termiche ad altissima temperatura per incenerirlo."
        );
    }

    @Override
    public void eseguiAzione(Personaggio utilizzatore, Personaggio bersaglio) {
        if (usaGhiaccio)
            attaccaConGhiaccio(utilizzatore, bersaglio);
        else
            attaccaConFuoco(utilizzatore, bersaglio);
        usaGhiaccio = !usaGhiaccio;
    }

    private void attaccaConGhiaccio(Personaggio utilizzatore, Personaggio bersaglio) {
        loggerBattaglia.scrivi(utilizzatore.getAlias() + " genera un'ondata di ghiaccio!");
        bersaglio.riceviDanno(utilizzatore, utilizzatore.getTecnica());
        rallentaBersaglio(bersaglio);
    }

    private void rallentaBersaglio(Personaggio bersaglio) {
        bersaglio.setVelocita(Math.max(1, bersaglio.getVelocita() - 10));
    }

    private void attaccaConFuoco(Personaggio utilizzatore, Personaggio bersaglio) {
        loggerBattaglia.scrivi(utilizzatore.getAlias() + " rilascia una fiammata devastante!");
        bersaglio.riceviDanno(utilizzatore, utilizzatore.getPotenza() * 3);
    }
}
