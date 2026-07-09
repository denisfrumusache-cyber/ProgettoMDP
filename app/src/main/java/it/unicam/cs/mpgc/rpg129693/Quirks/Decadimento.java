package it.unicam.cs.mpgc.rpg129693.Quirks;

import it.unicam.cs.mpgc.rpg129693.Utils.CalcolaDanno;
import it.unicam.cs.mpgc.rpg129693.classiAstratte.Personaggio;
import it.unicam.cs.mpgc.rpg129693.classiAstratte.Quirk;

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
            System.out.println(bersaglio.getAlias() + " è riuscito a schivare il tocco di " + utilizzatore.getAlias());
            return;
        }
        
        System.out.println(utilizzatore.getAlias() + " tocca il bersaglio con tutte e 5 le dita e attiva Decadimento!");
        disintegraPersonaggio(bersaglio);
        
        int danno = utilizzatore.getPotenza() * MOLTIPLICATORE_POTENZA;
        bersaglio.riceviDanno(utilizzatore, danno);
        
        System.out.println("Il corpo di " + bersaglio.getAlias() + " si sta disintegrando! Potenza e Velocità ridotte di " + DECREMENTO_STATISTICHE + "!");
    }

    private static void disintegraPersonaggio(Personaggio personaggio) {
        int velocitaDecrementata = Math.max(VALORE_MINIMO_STATISTICA, personaggio.getVelocita() - DECREMENTO_STATISTICHE);
        int potenzaDecrementata = Math.max(VALORE_MINIMO_STATISTICA, personaggio.getPotenza() - DECREMENTO_STATISTICHE);
        
        personaggio.setVelocita(velocitaDecrementata);
        personaggio.setPotenza(potenzaDecrementata);
    }
}
