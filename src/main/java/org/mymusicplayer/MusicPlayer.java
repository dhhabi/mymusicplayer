/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mymusicplayer;


import org.musicplayer.ui.PlayerUI;

/**
 *
 * @author preet
 */
public class MusicPlayer {
    
    public static void main(String[] args){
    
        //new PlayerUI(true).setVisible(true);
        
        new PlayerUI(false,"library").setVisible(true);
       
    }
}
