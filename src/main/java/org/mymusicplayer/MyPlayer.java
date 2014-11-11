/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mymusicplayer;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
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
    
    private BufferedInputStream bis;
    private ByteArrayInputStream bais;
    
    public Player player;
    
    public long pauseLocation;
    public long songTotalLength = 0;
    public String songLocation;
    private boolean playing;
    private boolean stoped;
    
    private byte[] nowPlayingSongBytes;
    
    
    public byte[] getNowPlayingSongBytes() {
        return nowPlayingSongBytes;
    }

    public void setNowPlayingSongBytes(byte[] nowPlayingSongBytes) {
        this.nowPlayingSongBytes = nowPlayingSongBytes;
    }

    

    
    
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
    
    public void pause(){
        if(player != null){
            pauseLocation = bais.available();
            playing = false;
            player.close();
        }
    }
     
     
     public void resume(){
        if(stoped){
            play(nowPlayingSongBytes);
        }else if(!playing){
         try {
            bais = new ByteArrayInputStream(nowPlayingSongBytes);
            bis = new BufferedInputStream(bais);
           
            player = new Player(bis);
            bais.skip(songTotalLength - pauseLocation);
            //fis.skip(songTotalLength - pauseLocation); 
            // songTotalLength = fis.available();
            playing = true;
            stoped=false;
            
        } catch (JavaLayerException ex) {
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
     
     
     // Player functions using input stream 
     
     public void play(byte[] songBytes){
        
        if(!playing){
        try {
            //fis = new FileInputStream(path);
            nowPlayingSongBytes = songBytes;
            bais = new ByteArrayInputStream(songBytes);
            bis = new BufferedInputStream(bais);
            player = new Player(bis);
            songTotalLength = bais.available();
            //songLocation = path+"";
            playing=true;
            stoped=false;
        } catch (JavaLayerException ex) {
            Logger.getLogger(MyPlayer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        new Thread(){
            @Override
            public void run( ){
                try {
                    player.play();
                    
                    // To add the loop
                    if(PlayerUI.loop == 1 && player.isComplete())
                        play(nowPlayingSongBytes);
                    
                } catch (JavaLayerException ex) {
                    Logger.getLogger(MyPlayer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }.start();
        }
    }
    
}
