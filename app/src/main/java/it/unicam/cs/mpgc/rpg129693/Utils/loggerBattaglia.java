package it.unicam.cs.mpgc.rpg129693.Utils;

public class loggerBattaglia {
    private static String logAttuale = "";

    private loggerBattaglia() {
        throw new UnsupportedOperationException("Classe di utilità");
    }


    // Metodo che i personaggi usano per scrivere cosa succede
    public static void scrivi(String frase){
        logAttuale += frase + "\n";
    }

    public static String ritiraLog(){
        String testo = logAttuale;
        logAttuale = "";
        return testo;
    }
}
