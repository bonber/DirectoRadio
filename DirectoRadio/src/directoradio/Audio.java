/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package directoradio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

/**
 *
 * @author bonber
 */
public class Audio {
    
    static boolean pausa = false;
    
    public void reproducir(String fichero){
        try {
            
            // Se obtiene un Clip de sonido
            Clip sonido = AudioSystem.getClip();
            
            // Se carga con un fichero wav
            System.out.println("Reproduciendo: "+fichero);
            sonido.open(AudioSystem.getAudioInputStream(new File(fichero)));
            
            // Comienza la reproducción
            sonido.start();
            
            // Espera mientras se esté reproduciendo.
            while (sonido.isRunning())
                Thread.sleep(1000);
            
            // Se cierra el clip.
            sonido.close();
        } catch (Exception e) {
            System.out.println("" + e);
            
        }
    }
    
    public void reproducirMp3(String fichero) throws FileNotFoundException,
          InterruptedException,
          JavaLayerException{
        
        
        final Player pl = new Player(new FileInputStream(fichero));

        new Thread() {
           public void run() {
              try {
                 while (true) {
                     
                    if (!pausa) {
                       if (!pl.play(1)) {
                          break;
                       }
                    }
                 }
              } catch (Exception e) {
                 e.printStackTrace();
              }
           }
        }.start();

     }

    public static boolean isPausa() {
        return pausa;
    }

    public static void setPausa(boolean pausa) {
        Audio.pausa = pausa;
    }
    
}
