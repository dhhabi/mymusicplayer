/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.musicplayer.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.apache.commons.io.IOUtils;
import org.musicplayer.dao.SongsLibraryDao;
import org.musicplayer.model.Song;

/**
 *
 * @author preet
 */
public class SongLibraryService {
    
    private SongsLibraryDao songLibrary =  new SongsLibraryDao();
    FileInputStream fis;
    
    public int addSong(String songFilePath){
        File songFile = new File(songFilePath);
        Song song = new Song();
        try {
            fis = new FileInputStream(songFile);
            byte[] songData = IOUtils.toByteArray(fis,fis.available());
            song.setSong(songData);
            song.setTitle(songFile.getName());
            
            
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        }
        return songLibrary.addSong(song);
    }
    
    public List getAllSongs(){
        
        return songLibrary.getAllSongs();
    }
    
}
