package it.unicam.cs.mpgc.rpg129693.Model.Quirks;

import it.unicam.cs.mpgc.rpg129693.Model.Personaggio;
import it.unicam.cs.mpgc.rpg129693.Utils.CalcolaDanno;
import it.unicam.cs.mpgc.rpg129693.Utils.loggerBattaglia;

public class Decadimento extends Quirk {
    private static final int DECREMENTO_STATISTICHE = 10;
    private static final int VALORE_MINIMO_STATISTICA = 1;
    private static final int MOLTIPLICATORE_POTENZA = 3;

    public Decadimento() {
        super(
            "Decadimento",
            50,
            "Consente di disintegrare e ridurre in polvere tutto ciò che viene toccato con tutte e cinque " +
            "le dita di una mano. La corrosione si diffonde rapidamente sul corpo del bersaglio, infliggendo " +
            "gravi danni e deteriorando permanentemente le sue capacità motorie e difensive."
        );
    }

    @Override
    public void eseguiAzione(Personaggio utilizzatore, Personaggio bersaglio) {
        if (CalcolaDanno.colpoSchivato(utilizzatore, bersaglio)) {
            loggerBattaglia.scrivi(bersaglio.getAlias() + " ha schivato il tocco di " + utilizzatore.getAlias());
            return;
        }
        loggerBattaglia.scrivi(utilizzatore.getAlias() + " attiva Decadimento!");
        applicaDecadimento(utilizzatore, bersaglio);
    }

    private void applicaDecadimento(Personaggio utilizzatore, Personaggio bersaglio) {
        disintegraPersonaggio(bersaglio);
        bersaglio.riceviDanno(utilizzatore, utilizzatore.getPotenza() * MOLTIPLICATORE_POTENZA);
    }

    private static void disintegraPersonaggio(Personaggio personaggio) {
        int velocitaDecrementata = Math.max(VALORE_MINIMO_STATISTICA, personaggio.getVelocita() - DECREMENTO_STATISTICHE);
        int potenzaDecrementata = Math.max(VALORE_MINIMO_STATISTICA, personaggio.getPotenza() - DECREMENTO_STATISTICHE);
        personaggio.setVelocita(velocitaDecrementata);
        personaggio.setPotenza(potenzaDecrementata);
    }
}
