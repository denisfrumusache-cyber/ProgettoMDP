package it.unicam.cs.mpgc.rpg129693.classiAstratte;

import it.unicam.cs.mpgc.rpg129693.Interfacce.Quirk;
import it.unicam.cs.mpgc.rpg129693.Utils.StatoAlterato;
import org.jspecify.annotations.NonNull;

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
    private StatoAlterato stato = StatoAlterato.NORMALE;
    private boolean difesaAttiva;


    public Personaggio(String id, String nome, String alias, int hpMax, int staminaMax, int potenza, int velocita, int tecnica, Quirk quirk) {
        this.id = id;
        this.nome = nome;
        this.alias = alias;
        this.hpMax = hpMax;
        this.hpAttuali = hpMax;
        this.staminaMax = staminaMax;
        this.staminaAttuale = staminaMax;
        this.potenza = potenza;
        this.velocita = velocita;
        this.tecnica = tecnica;
        this.quirk = quirk;
        this.stato = StatoAlterato.NORMALE;
        this.difesaAttiva = false;
    }



    public String getId() {
        return id;
    }
    public void setId(String id){
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAlias(){
        return this.alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public int getHpMax(){
        return this.hpMax;
    }

    public void setHpMax(int hpMax) {
        this.hpMax = hpMax;
    }

    public int getHpAttuali() {
        return hpAttuali;
    }
    public void setHpAttuali(int Hp){
        this.hpAttuali = Hp;
    }

    public int getStaminaMax() {
        return staminaMax;
    }

    public void setStaminaMax(int staminaMax) {
        this.staminaMax = staminaMax;
    }

    public int getStaminaAttuale() {
        return staminaAttuale;
    }

    public void setStaminaAttuale(int staminaAttuale) {
        this.staminaAttuale = staminaAttuale;
    }

    public int getPotenza() {
        return potenza;
    }

    public void setPotenza(int potenza) {
        this.potenza = potenza;
    }

    public int getVelocita(){
        return velocita;
    }

    public void setVelocita(int velocita) {
        this.velocita = velocita;
    }

    public int getTecnica() {
        return tecnica;
    }

    public void setTecnica(int tecnica) {
        this.tecnica = tecnica;
    }

    public Quirk getQuirk() {
        return quirk;
    }

    public void setQuirk(Quirk quirk) {
        this.quirk = quirk;
    }

    public StatoAlterato getStato() {
        return stato;
    }

    public void setStato(StatoAlterato stato) {
        this.stato = stato;
    }

    public abstract void attaccoSpeciale(Personaggio bersaglio);

    public abstract void iniziaTurno();

    public void attaccoBase(Personaggio bersaglio){
        bersaglio.riceviDanno(this.potenza);

    }
    public void difenditi(){
        this.difesaAttiva = true;
    }

    public void riceviDanno(int danno){

        if (difesaAttiva){
            this.hpAttuali -= danno/2;
        }
        this.hpAttuali -= danno;
        if (this.hpAttuali < 0){
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







}
