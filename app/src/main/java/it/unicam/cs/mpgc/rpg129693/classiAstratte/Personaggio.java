package it.unicam.cs.mpgc.rpg129693.classiAstratte;

import it.unicam.cs.mpgc.rpg129693.Utils.CalcolaDanno;

import java.util.Objects;

@SuppressWarnings("All")
public abstract class Personaggio {
    private String id;
    private String nome;
    private String alias;
    private int hpMax;
    private int hpAttuali;
    private int staminaMax;
    private int staminaAttuale;
    private int potenza;
    private int velocita;
    private int tecnica;
    private Quirk quirk;
    private boolean difesaAttiva;


    public Personaggio(String id, String nome, String alias, int hpMax, int staminaMax, int potenza, int velocita, int tecnica, Quirk quirk) {
        this.id = Objects.requireNonNull(id,"L'id non puo essere null!!");
        this.nome = Objects.requireNonNull(nome,"Il nome non puo essere null!!");
        if (this.nome.isBlank()){
            throw new IllegalArgumentException("E' stata passata una stringa vuota sul campo nome");
        }
        this.alias = Objects.requireNonNull(alias, "L'alias non puo essere null!!");
        if (hpMax <= 0 || staminaMax <= 0 || potenza < 0 || velocita < 0 || tecnica < 0){
            throw new IllegalArgumentException("Le statistiche devono avere valori positivi e validi");
        }
        this.hpMax = hpMax;
        this.hpAttuali = hpMax;
        this.staminaMax = staminaMax;
        this.staminaAttuale = staminaMax;
        this.potenza = potenza;
        this.velocita = velocita;
        this.tecnica = tecnica;
        this.quirk = Objects.requireNonNull(quirk,"Il quirk non puo essere null");
        this.difesaAttiva = false;
    }



    public String getId() {
        return id;
    }
    public void setId(String id){
        this.id = Objects.requireNonNull(id, "L'id non puo essere null!!");
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        Objects.requireNonNull(nome, "Il nome non puo essere null!!");
        if (nome.isBlank()) {
            throw new IllegalArgumentException("Il nome non puo essere vuoto");
        }
        this.nome = nome;
    }

    public String getAlias(){
        return this.alias;
    }

    public void setAlias(String alias) {
        this.alias = Objects.requireNonNull(alias, "L'alias non puo essere null!!");
    }

    public int getHpMax(){
        return this.hpMax;
    }

    public void setHpMax(int hpMax) {
        if (hpMax <= 0) {
            throw new IllegalArgumentException("Gli HP max devono essere maggiori di 0");
        }
        this.hpMax = hpMax;
    }

    public int getHpAttuali() {
        return hpAttuali;
    }
    public void setHpAttuali(int Hp){
        if (Hp < 0) {
            throw new IllegalArgumentException("Gli HP attuali non possono essere negativi");
        }
        this.hpAttuali = Hp;
    }

    public int getStaminaMax() {
        return staminaMax;
    }

    public void setStaminaMax(int staminaMax) {
        if (staminaMax <= 0) {
            throw new IllegalArgumentException("La stamina max deve essere maggiore di 0");
        }
        this.staminaMax = staminaMax;
    }

    public int getStaminaAttuale() {
        return staminaAttuale;
    }

    public void setStaminaAttuale(int staminaAttuale) {
        if (staminaAttuale < 0) {
            throw new IllegalArgumentException("La stamina attuale non può essere negativa");
        }
        this.staminaAttuale = staminaAttuale;
    }

    public int getPotenza() {
        return potenza;
    }

    public void setPotenza(int potenza) {
        if (potenza < 0) {
            throw new IllegalArgumentException("La potenza non può essere negativa");
        }
        this.potenza = potenza;
    }

    public int getVelocita(){
        return velocita;
    }

    public void setVelocita(int velocita) {
        if (velocita < 0) {
            throw new IllegalArgumentException("La velocità non può essere negativa");
        }
        this.velocita = velocita;
    }

    public int getTecnica() {
        return tecnica;
    }

    public void setTecnica(int tecnica) {
        if (tecnica < 0) {
            throw new IllegalArgumentException("La tecnica non può essere negativa");
        }
        this.tecnica = tecnica;
    }

    public Quirk getQuirk() {
        return quirk;
    }

    public void setQuirk(Quirk quirk) {
        this.quirk = Objects.requireNonNull(quirk, "Il quirk non puo essere null");
    }


    public void attaccoSpeciale(Personaggio bersaglio) {
        if (bersaglio == null) {
            throw new IllegalArgumentException("Il bersaglio non può essere null");
        }
        int costo = this.getQuirk().getCostoStamina();
        if (!this.consumaStamina(costo)) {
            throw new IllegalStateException("Stamina insufficiente per usare il Quirk!");
        }
        this.getQuirk().eseguiAzione(this, bersaglio);
    }

    public void iniziaTurno() {
        this.resettaDifesa();
        int recuperoStamina = Math.min(this.getStaminaMax(), this.getStaminaAttuale() + 10);
        this.setStaminaAttuale(recuperoStamina);
    }

    public void attaccoBase(Personaggio bersaglio){
        bersaglio.riceviDanno(this.potenza);

    }
    public void difenditi(){
        this.difesaAttiva = true;
    }

    public void riceviDanno(int danno){
        int dannoCalcolato = CalcolaDanno.calcolaDannoEffettivo(this, danno);
        this.hpAttuali -= dannoCalcolato;
        // Se gli Hp dopo il danno subito è un numero negativo, mette gli Hp a 0
        if (hpAttuali < 0){
            this.hpAttuali = 0;
        }
    }

    public boolean consumaStamina(int quantita){
        if(this.staminaAttuale < quantita){
            return false;
        }
        this.staminaAttuale -= quantita;
        return true;
    }

    public void resettaDifesa(){
        this.difesaAttiva = false;
    }

    public boolean eVivo(){
        return this.hpAttuali > 0;
    }

    public boolean isDifesaAttiva(){
        return this.difesaAttiva;
    }


}
