/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.musicplayer.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Lob;

/**
 *
 * @author preet
 */
@Entity
public class RecentSongs implements Serializable {
    @Id
    private final int id;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> recentSongList;
    
    public RecentSongs(){
        this.id = 1;
        recentSongList = new LinkedList<>();
    }

    public List<String> getRecentSongList() {
        return recentSongList;
    }

    public void setRecentSongList(List<String> recentSongList) {
        this.recentSongList = recentSongList;
    }

     
    
    
}
