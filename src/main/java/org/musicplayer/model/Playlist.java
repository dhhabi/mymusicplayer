/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.musicplayer.model;

import java.util.ArrayList;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author preet
 */
@Entity
public class Playlist {
    @Id
    private String playlistName;
    private ArrayList<Integer> songList = new ArrayList();
    
    public Playlist(){
        
    }
    
    public Playlist(String playlistName){
        this.playlistName = playlistName;
    }
    
    public String getPlaylistName() {
        return playlistName;
    }

    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }

    public ArrayList getSongList() {
        return songList;
    }

    public void setSongList(ArrayList songList) {
        this.songList = songList;
    }
    
}
