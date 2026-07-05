package it.unicam.cs.mpgc.rpg129693.Utils;

import it.unicam.cs.mpgc.rpg129693.classiAstratte.Personaggio;

public class CalcolaDanno {

    public static int calcolaDannoEffettivo(Personaggio bersaglio, int danno){
        if (bersaglio == null)
            throw new IllegalArgumentException
                    ("E' stato passato un personaggio bersaglio null");

        if (danno <= 0)
            throw new IllegalArgumentException
                    ("Il danno deve essere maggiore di 0");

        if (bersaglio.isDifesaAttiva()){
            return danno/2;
        }else{
            return danno;
        }

    }

}
