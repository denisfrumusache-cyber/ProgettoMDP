package it.unicam.cs.mpgc.rpg129693.Utils;

import it.unicam.cs.mpgc.rpg129693.Model.Personaggio;
import java.util.Random;

public class CalcolaDanno {
    private static final Random RANDOM = new Random();
    private static final double MOLTIPLICATORE_CRITICO = 1.5;
    private static final int LIMITE_MASSIMO_SCHIVATA = 50;

    private CalcolaDanno() {
        throw new UnsupportedOperationException("Questa è una classe di utilità e non può essere istanziata");
    }

    public static int calcolaDannoEffettivo(Personaggio bersaglio, Personaggio attaccante, int danno) {
        if (bersaglio == null) throw new IllegalArgumentException("Il personaggio bersaglio non può essere null");
        if (attaccante == null) throw new IllegalArgumentException("Il personaggio attaccante non può essere null");
        if (danno < 0) throw new IllegalArgumentException("Il danno non può essere negativo");

        int dannoConCritico = applicaCritico(danno, attaccante);
        return applicaDifesa(dannoConCritico, bersaglio);
    }

    private static int applicaCritico(int danno, Personaggio attaccante) {
        boolean isCritico = RANDOM.nextInt(100) < attaccante.getTecnica();
        if (isCritico) {
            loggerBattaglia.scrivi("COLPO CRITICO!");
            return (int) (danno * MOLTIPLICATORE_CRITICO);
        }
        return danno;
    }

    private static int applicaDifesa(int danno, Personaggio bersaglio) {
        return bersaglio.isDifesaAttiva() ? danno / 2 : danno;
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
        return RANDOM.nextInt(100) < probabilitaSchivata;
    }

    public static int calcolaProbabilitaSchivata(int differenzaVelocita) {
        return Math.min(LIMITE_MASSIMO_SCHIVATA, differenzaVelocita * 2);
    }
}
