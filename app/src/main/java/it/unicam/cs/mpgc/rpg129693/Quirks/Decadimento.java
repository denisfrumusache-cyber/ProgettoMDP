package it.unicam.cs.mpgc.rpg129693.Quirks;

import it.unicam.cs.mpgc.rpg129693.Utils.CalcolaDanno;
import it.unicam.cs.mpgc.rpg129693.classiAstratte.Personaggio;
import it.unicam.cs.mpgc.rpg129693.classiAstratte.Quirk;

public class Decadimento extends Quirk {
    public Decadimento(){
        super(
                "Decadimento",
                35,
                "Consente di disintegrare e ridurre in polvere tutto ciò che viene toccato con tutte e cinque " +
                        "le dita di una mano. La corrosione si diffonde rapidamente sul corpo del bersaglio, infliggendo " +
                        "gravi danni e deteriorando permanentemente le sue capacità motorie e difensive."
        );
    }

    @Override
    public void eseguiAzione(Personaggio utilizzatore, Personaggio bersaglio) {
        if (CalcolaDanno.colpoSchivato(utilizzatore,bersaglio)){
            System.out.println(bersaglio.getAlias() + "E' riuscito a schivare il tocco di " + utilizzatore.getAlias());
            return;
        }
        System.out.println( utilizzatore.getAlias() + " tocca l'eroe con tutte e 5 le dita e attiva Decadimento");
        disintegraPersonaggio(bersaglio);
        System.out.println("Il corpo di " + bersaglio.getAlias() + " si sta disintegrando! Potenza e Velocità ridotte di 10!");

    }

    private static void disintegraPersonaggio(Personaggio personaggio){
        int velocitaDecrementata = Math.max( personaggio.getVelocita() - 10,1);
        int potenzaDecrementata = Math.max(personaggio.getPotenza() -10,1);
        personaggio.setVelocita(velocitaDecrementata);
        personaggio.setPotenza(potenzaDecrementata);
    }
}
