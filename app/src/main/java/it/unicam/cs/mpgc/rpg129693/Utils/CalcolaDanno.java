package it.unicam.cs.mpgc.rpg129693.Utils;

import it.unicam.cs.mpgc.rpg129693.classiAstratte.Personaggio;
import java.util.Random;

public class CalcolaDanno {
    private static final Random RANDOM = new Random();
    private static final double MOLTIPLICATORE_CRITICO = 1.5;
    private static final int LIMITE_MASSIMO_SCHIVATA = 50;

    // Costruttore privato per impedire l'istanziazione (Utility Class)
    private CalcolaDanno() {
        throw new UnsupportedOperationException("Questa è una classe di utilità e non può essere istanziata");
    }

    public static int calcolaDannoEffettivo(Personaggio bersaglio, Personaggio attaccante, int danno) {
        if (bersaglio == null) {
            throw new IllegalArgumentException("Il personaggio bersaglio non può essere null");
        }
        if (attaccante == null) {
            throw new IllegalArgumentException("Il personaggio attaccante non può essere null");
        }
        if (danno < 0) {
            throw new IllegalArgumentException("Il danno non può essere negativo");
        }

        int dannoFinale = danno;

        // La tecnica determina la probabilità di critico
        boolean isCritico = RANDOM.nextInt(100) < attaccante.getTecnica();
        if (isCritico) {
            System.out.println("COLPO CRITICO!");
            dannoFinale = (int) (dannoFinale * MOLTIPLICATORE_CRITICO);
        }

        if (bersaglio.isDifesaAttiva()) {
            return dannoFinale / 2;
        } else {
            return dannoFinale;
        }
    }

    public static boolean colpoSchivato(Personaggio attaccante, Personaggio bersaglio) {
        if (attaccante == null || bersaglio == null) {
            throw new IllegalArgumentException("L'attaccante e il bersaglio non possono essere null.");
        }

        if (bersaglio.getVelocita() <= attaccante.getVelocita()) {
            return false;
        }

        int differenzaVelocita = bersaglio.getVelocita() - attaccante.getVelocita();
        int probabilitaSchivata = calcolaProbabilitaSchivata(differenzaVelocita);
        int valoreLancioDado = RANDOM.nextInt(100);

        return valoreLancioDado < probabilitaSchivata;
    }

    public static int calcolaProbabilitaSchivata(int differenzaVelocita) {
        return Math.min(LIMITE_MASSIMO_SCHIVATA, differenzaVelocita * 2);
    }
}
