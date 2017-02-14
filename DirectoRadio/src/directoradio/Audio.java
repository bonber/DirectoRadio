/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package directoradio;

import Vistas.MainView;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

/**
 *
 * @author bonber
 */
public class Audio {
    
    static boolean pausa = false;
    
    public void reproducir(String fichero){
        int index = fichero.lastIndexOf('.');
        String ext =  fichero.substring(index + 1);
        
        switch (ext) {
            case "mp3":
                reproducirMp3(fichero);
                break;
            case "wav":
                reproducirWav(fichero);
                break;
            default:
                JFrame frame = new JFrame();
                JOptionPane.showMessageDialog(frame,
    "Formato no soportado.");
        }
    }
    
    public void reproducirWav(String fichero){
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
    
    public void reproducirMp3(String fichero) {
        
        try {
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
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Audio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JavaLayerException ex) {
            Logger.getLogger(Audio.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        

     }

    public static boolean isPausa() {
        return pausa;
    }

    public static void setPausa(boolean pausa) {
        Audio.pausa = pausa;
    }
    
}
