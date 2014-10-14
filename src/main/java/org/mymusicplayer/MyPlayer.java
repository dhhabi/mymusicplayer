/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mymusicplayer;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import org.musicplayer.ui.PlayerUI;

/**
 *
 * @author preet
 */
public class MyPlayer {
    
    private FileInputStream fis;
    private BufferedInputStream bis;
    
    public Player player;
    
    public long pauseLocation;
    public long songTotalLength = 0;
    public String songLocation;
    private boolean playing;
    private boolean stoped;
    
    /**
     * Method to stop currently playing song 
     */
    public void stop(){
        if(player != null){
            pauseLocation = 0;
            //songTotalLength = 0;
            playing = false;
            stoped = true;
            player.close();
        }
    }
    
    /**
     * Method to play the song 
     * @param path path to the music file 
     */
    public void play(String path){
        
        if(!playing){
        try {
            fis = new FileInputStream(path);
            bis = new BufferedInputStream(fis);
            player = new Player(bis);
            songTotalLength = fis.available();
            songLocation = path+"";
            playing=true;
            stoped=false;
        } catch (FileNotFoundException | JavaLayerException ex) {
            Logger.getLogger(MyPlayer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MyPlayer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        new Thread(){
            @Override
            public void run( ){
                try {
                    player.play();
                    
                    // To add the loop
                    if(PlayerUI.loop == 1 && player.isComplete())
                        play(songLocation);
                    
                } catch (JavaLayerException ex) {
                    Logger.getLogger(MyPlayer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }.start();
        }
    }
    
     public void pause(){
        if(player != null){
            try {
                pauseLocation = fis.available();
                playing = false;
                player.close();
            } catch (IOException ex) {
                Logger.getLogger(MyPlayer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
     
     
     public void resume(){
        if(stoped){
            play(songLocation);
        }else if(!playing){
         try {
            fis = new FileInputStream(songLocation);
            bis = new BufferedInputStream(fis);
           
            player = new Player(bis);
            fis.skip(songTotalLength - pauseLocation);
            //fis.skip(songTotalLength - pauseLocation); 
            // songTotalLength = fis.available();
            playing = true;
            stoped=false;
            
        } catch (FileNotFoundException | JavaLayerException ex) {
            Logger.getLogger(MyPlayer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MyPlayer.class.getName()).log(Level.SEVERE, null, ex);
        }         
        new Thread(){
            @Override
            public void run( ){
                try {
                    player.play();
                } catch (JavaLayerException ex) {
                    Logger.getLogger(MyPlayer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }.start();
        }
    }
    
}
