/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.musicplayer.service;

import com.mpatric.mp3agic.ID3v1;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.time.DurationFormatUtils;
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
        Song song = new Song();
        try {
            Mp3File mp3File = new Mp3File(songFilePath);
            ID3v1 tags = mp3File.getId3v1Tag();
            song.setTitle(tags.getTitle());
            song.setAlbum(tags.getAlbum());
            song.setArtist(tags.getArtist());
            song.setGenre(tags.getGenreDescription());
            String songLength = DurationFormatUtils.formatDurationHMS(mp3File.getLengthInMilliseconds());
            song.setSongLength(songLength);
            song.setSongPath(songFilePath);
            
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        } catch (UnsupportedTagException | InvalidDataException ex) {
            Logger.getLogger(SongLibraryService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return songLibrary.addSong(song);
    }
    
    public List getAllSongs(){
        return songLibrary.getAllSongs();
    }
    
    public int addSong(Song song){
        return songLibrary.addSong(song);
    }
    
    public Song getSong(int songId){
        return songLibrary.getSong(songId);
    }
    
    public int deleteSong(int songId){
        return songLibrary.deleteSong(songId);
    }
    
    
   
    
}
