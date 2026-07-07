package it.unicam.cs.mpgc.rpg129693.Utils;

import it.unicam.cs.mpgc.rpg129693.classiAstratte.Personaggio;

import java.util.Random;

public class CalcolaDanno {
    private static final Random RANDOM = new Random();

    public static int calcolaDannoEffettivo(Personaggio bersaglio, Personaggio attaccante, int danno){
        if (bersaglio == null)
            throw new IllegalArgumentException
                    ("E' stato passato un personaggio bersaglio null");

        if (danno < 0)
            throw new IllegalArgumentException
                    ("Il danno deve essere maggiore di 0");
        int dannoFinale = danno;
        // La tecnica determina la probabilità di critico (es. 25 di tecnica = 25% di probabilità)
        boolean CeIlColpoCritico = RANDOM.nextInt(100) < attaccante.getTecnica();
        if (CeIlColpoCritico){
            System.out.println("COLPO CRITICO!");
            //Aumenta il danno del 50%
            dannoFinale =(int)(dannoFinale * 1.5);
        }
        if (bersaglio.isDifesaAttiva()){
            return dannoFinale/2;
        }else{
            return dannoFinale;
        }

    }
    @SuppressWarnings("all")
    public static boolean colpoSchivato(Personaggio attaccante, Personaggio bersaglio){
        if (attaccante == null || bersaglio == null) {
            throw new IllegalArgumentException("L'attaccante e il bersaglio non possono essere null.");
        }

        boolean ilBersaglioNonEPiuVeloce = bersaglio.getVelocita() <= attaccante.getVelocita();
        if (ilBersaglioNonEPiuVeloce){
            return false;
        }

        int differenzaVelocita = bersaglio.getVelocita() - attaccante.getVelocita();
        
        int probabilitaSchivata = calcolaProbabilitaSchivata(differenzaVelocita);
        int valoreLancioDado = RANDOM.nextInt(100);
        
        boolean schivataRiuscita = valoreLancioDado < probabilitaSchivata;
        return schivataRiuscita;
    }

    public static int calcolaProbabilitaSchivata(int differenzaVelocita){
        return Math.min(50, differenzaVelocita * 2);
    }


}
