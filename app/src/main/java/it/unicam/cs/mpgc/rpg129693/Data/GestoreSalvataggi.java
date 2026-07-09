package it.unicam.cs.mpgc.rpg129693.Data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.unicam.cs.mpgc.rpg129693.classiPrincipali.Eroe;
import it.unicam.cs.mpgc.rpg129693.classiPrincipali.Torre;

import java.io.*;

public class GestoreSalvataggi {
    private static final String NOME_FILE_SALVATAGGIO = "salvataggio.json";
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();


    // Salva lo stato di gioco su file usando BufferedWriter
    public static void salvaPartita(Eroe eroe, Torre torre) {
       StatoSalvataggio salvataggio = new StatoSalvataggio(eroe, torre);
       try (FileWriter fileWriter = new FileWriter(NOME_FILE_SALVATAGGIO);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)){
           String salvataggioJson = GSON.toJson(salvataggio);
           bufferedWriter.write(salvataggioJson);
           System.out.println("Salvataggio effettuato con successo");

       }catch (IOException e){
           System.out.println("Qualcosa e' andato storto durante il salvataggio:" + e.getMessage());
       }
    }

    // Carica lo stato di gioco da file usando BufferedReader
    public static StatoSalvataggio caricaPartita() {
        File file = new File(NOME_FILE_SALVATAGGIO);
        if (!file.exists()) {
            return null; // Nessun salvataggio presente
        }

        try (FileReader fileReader = new FileReader(file);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            return GSON.fromJson(bufferedReader, StatoSalvataggio.class);
        } catch (IOException e) {
            System.err.println("Errore durante il caricamento della partita: " + e.getMessage());
            return null;
        }
    }

    // Elimina il file di salvataggio (es. quando il giocatore perde o vince la torre)
    public static void eliminaSalvataggio() {
        File file = new File(NOME_FILE_SALVATAGGIO);
        if (file.exists()) {
            file.delete();
        }
    }
}
