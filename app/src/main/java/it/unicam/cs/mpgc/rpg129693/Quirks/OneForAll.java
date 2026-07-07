package it.unicam.cs.mpgc.rpg129693.Quirks;

import it.unicam.cs.mpgc.rpg129693.classiAstratte.Personaggio;
import it.unicam.cs.mpgc.rpg129693.classiAstratte.Quirk;

public class OneForAll extends Quirk {

    public OneForAll(){
        super(
                "One For All",
                25,
                "Quirk ereditario che accumula energia fisica pura. Canalizzando il potere tramite il 'Full Cowl', " +
                        "l'utilizzatore rilascia devastanti Smash fisici (come il Detroit Smash) che aumentano di potenza " +
                        "quando la salute è critica."
        );
    }


    @Override
    public void eseguiAzione(Personaggio utilizzatore, Personaggio bersaglio){
         double percentualeHP = calcolaLaPercentualeDiHpRimasti(utilizzatore);
         int dannoBase = utilizzatore.getPotenza() * 2;
        // Sotto il 30% di vita, il danno raddoppia (100% Detroit Smash!)
         if (percentualeHP < 0.30){
             System.out.println( utilizzatore.getAlias() + " supera i suoi limiti! 100% DETROIT SMASH!");
             dannoBase *= 2;
             // Sopra il 30% fa un attacco normale
         }else{
             System.out.println( utilizzatore.getAlias() + " usa Delaware Smash!");
         }
         bersaglio.riceviDanno(utilizzatore,dannoBase);
    }

    public static double calcolaLaPercentualeDiHpRimasti(Personaggio personaggio){
        return (double)personaggio.getHpAttuali()/ personaggio.getHpMax();
    }
}
