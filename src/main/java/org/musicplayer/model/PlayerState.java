/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.musicplayer.model;

import java.util.HashSet;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author preet
 */
@Entity
public class PlayerState {
    //"Id", "Title", "Artist", "Album", "Length", "Genre", "File Path", "Year", "Comments"
    
    @Id
    private int id;
    
    private HashSet<String> columnList;
    
    public PlayerState(){
        columnList = new HashSet<>();
        columnList.add("Id");
        columnList.add("Title");
        columnList.add("Artist");
        columnList.add("Album");
        columnList.add("Length");
        columnList.add("Genre");
        columnList.add("File Path");
        columnList.add("Year");
        columnList.add("Comments");
        this.id = 1;        
    }

    public HashSet<String> getColumnList() {
        return columnList;
    }

    public void setColumnList(HashSet<String> columnList) {
        this.columnList = columnList;
    }
    
    
}
