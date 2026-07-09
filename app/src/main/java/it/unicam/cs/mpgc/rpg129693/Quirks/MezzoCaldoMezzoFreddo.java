package it.unicam.cs.mpgc.rpg129693.Quirks;

import it.unicam.cs.mpgc.rpg129693.classiAstratte.Personaggio;
import it.unicam.cs.mpgc.rpg129693.classiAstratte.Quirk;
@SuppressWarnings("all")
public class MezzoCaldoMezzoFreddo extends Quirk {
private boolean usaGhiaccio = true;
    public MezzoCaldoMezzoFreddo(){
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
        if(usaGhiaccio){
            System.out.println( utilizzatore.getAlias() + " genera un'ondata di ghiaccio!");
            int dannoGhiaccio = utilizzatore.getTecnica();
            bersaglio.riceviDanno(utilizzatore,dannoGhiaccio);
            //Il ghiaccio rallenta il nemico
            int nuovaVelocita = Math.max(1, bersaglio.getVelocita()-10);
            bersaglio.setVelocita(nuovaVelocita);
        }else{
            System.out.println(utilizzatore.getAlias() + " rilascia una fiammata di Fuoco devastante!");
            // Danno molto elevato
            int dannoFuoco = utilizzatore.getPotenza() * 3;
            bersaglio.riceviDanno(utilizzatore,dannoFuoco);
        }
        usaGhiaccio = !usaGhiaccio;

    }
}
