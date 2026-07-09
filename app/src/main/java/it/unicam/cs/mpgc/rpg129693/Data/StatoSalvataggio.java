package it.unicam.cs.mpgc.rpg129693.Data;

import it.unicam.cs.mpgc.rpg129693.classiPrincipali.Eroe;
import it.unicam.cs.mpgc.rpg129693.classiPrincipali.Torre;
import it.unicam.cs.mpgc.rpg129693.classiAstratte.Quirk;

public class StatoSalvataggio {
    private String id;
    private String nome;
    private String alias;
    private int livello;
    private int esperienza;
    private int sogliaLivello;
    private int hpMax;
    private int hpAttuali;
    private int staminaMax;
    private int staminaAttuale;
    private int potenza;
    private int velocita;
    private int tecnica;
    private String nomeQuirk;
    private int livelloTorreCorrente;

    // Costruttore vuoto richiesto da Gson
    public StatoSalvataggio() {}

    // Crea un'istantanea dello stato del gioco
    public StatoSalvataggio(Eroe eroe, Torre torre) {
        this.id = eroe.getId();
        this.nome = eroe.getNome();
        this.alias = eroe.getAlias();
        this.livello = eroe.getLivello();
        this.esperienza = eroe.getEsperienza();
        this.sogliaLivello = eroe.getSogliaLivello();
        this.hpMax = eroe.getHpMax();
        this.hpAttuali = eroe.getHpAttuali();
        this.staminaMax = eroe.getStaminaMax();
        this.staminaAttuale = eroe.getStaminaAttuale();
        this.potenza = eroe.getPotenza();
        this.velocita = eroe.getVelocita();
        this.tecnica = eroe.getTecnica();
        this.nomeQuirk = eroe.getQuirk().getNome();
        this.livelloTorreCorrente = torre.getLivelloCorrente();
    }

    // Ricostruisce l'oggetto Eroe a partire dallo stato salvato
    public Eroe ricostruisciEroe() {
        Quirk quirk = QuirkFactory.creaQuirk(this.nomeQuirk);
        Eroe eroe = new Eroe(
            this.id, 
            this.nome, 
            this.alias, 
            this.hpMax, 
            this.staminaMax, 
            this.potenza, 
            this.velocita, 
            this.tecnica, 
            quirk
        );
        eroe.setLivello(this.livello);
        eroe.setEsperienza(this.esperienza);
        eroe.setSogliaLivello(this.sogliaLivello);
        eroe.setHpAttuali(this.hpAttuali);
        eroe.setStaminaAttuale(this.staminaAttuale);
        return eroe;
    }

    public int getLivelloTorreCorrente() {
        return this.livelloTorreCorrente;
    }
}
