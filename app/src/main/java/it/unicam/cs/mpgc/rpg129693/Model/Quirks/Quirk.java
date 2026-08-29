package it.unicam.cs.mpgc.rpg129693.Model.Quirks;

import it.unicam.cs.mpgc.rpg129693.Model.Personaggio;

import java.util.Objects;

public abstract class Quirk {
    private final String nome;
    private final int costoStamina;
    private final String descrizione;

    public Quirk(String nome, int costoStamina, String descrizione) {
        this.nome = Objects.requireNonNull(nome, "E' stato passato un oggetto null al campo nome");
        if (this.nome.isBlank())
            throw new IllegalArgumentException("Il nome non puo essere una stringa vuota");

        if (costoStamina < 0)
            throw new IllegalArgumentException("Il parametro costoStamina non deve essere negativo");
        this.costoStamina = costoStamina;

        this.descrizione = Objects.requireNonNull(descrizione, "E' stato passato un oggetto null al campo descrizione");
        if (this.descrizione.isBlank())
            throw new IllegalArgumentException("La descrizione non puo essere una stringa vuota");
    }

    public abstract void eseguiAzione(Personaggio utilizzatore, Personaggio bersaglio);

    public String getNome() {
        return this.nome;
    }

    public String getDescrizione() {
        return this.descrizione;
    }

    public int getCostoStamina() {
        return this.costoStamina;
    }
}
