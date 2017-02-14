/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package directoradio;

import java.io.BufferedReader;
import java.io.FileReader;
import Vistas.MainView.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bonber
 */
public class Language {
   
    public Language(String lng) {  
        setLanguage(lng);
    }
    
    public void uncheckLang(){ //Vaciamos los checks
        Vistas.MainView.m_language_es.setSelected(false);
        Vistas.MainView.m_language_eus.setSelected(false);
    }
    
    public void setLanguage(String lng) {
        
        uncheckLang();
        //Leemos el fichero
        BufferedReader in;
        try {
            in = new BufferedReader(new FileReader("languages/"+lng+".lng"));
            String line;
            try {
                while((line = in.readLine()) != null)
                {
                    switch (line.split("=")[0]) {
                        case "m_language":
                            Vistas.MainView.m_language.setText(line.split("=")[1]);
                            break;
                        case "m_language_es":
                            Vistas.MainView.m_language_es.setText(line.split("=")[1]);
                            break;
                        case "m_language_eus":
                            Vistas.MainView.m_language_eus.setText(line.split("=")[1]);
                            break;
                    }
                }
                in.close();
            } catch (IOException ex) {
                Logger.getLogger(Language.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Language.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    
    
    
    
}
