package it.unicam.cs.mpgc.rpg129693.classiAstratte;

import java.util.Objects;
@SuppressWarnings("all")
public abstract class Quirk {
    private String nome;
    private int costoStamina;
    private String descrizione;

    public Quirk(String nome, int costoStamina,String descrizione){
        this.nome = Objects.requireNonNull
                (nome,"E' stato passato un oggetto null al campo nome");

        if (this.nome.isBlank())
            throw new IllegalArgumentException
                    ("Il nome non puo essere una stringa vuota");

        if (costoStamina <= 0)
            throw new IllegalArgumentException
                    ("Il parametro costoStamina deve essere maggiore di 0");
        this.costoStamina = costoStamina;

        this.descrizione = Objects.requireNonNull
                (descrizione,"E' stato passato un oggetto null al campo descrizione");
        if (this.descrizione.isBlank())
            throw new IllegalArgumentException
                    ("La descrizione non puo essere una stringa vuota");
    }

    public abstract void eseguiAzione(Personaggio utilizzatore, Personaggio bersaglio);

    public String getNome(){
        return this.nome;
    }

    public String getDescrizione(){
        return this.descrizione;
    }

    public int getCostoStamina(){
        return this.costoStamina;
    }


}


