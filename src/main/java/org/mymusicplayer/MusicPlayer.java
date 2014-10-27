/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mymusicplayer;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.musicplayer.dao.SongsLibraryDao;
import org.musicplayer.model.Song;
import org.musicplayer.ui.CorePlayerUI;
import org.musicplayer.ui.PlayerUI;

/**
 *
 * @author preet
 */
public class MusicPlayer {
    
    public static void main(String[] args){
    /*    Song song = new Song();
        song.setTitle("Ranjhana");
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        System.out.println(session.save(song));
        session.getTransaction().commit();
        System.out.println("saved");
        
        SongsLibraryDao sl = new SongsLibraryDao();
        List<Song> songs = sl.getAllSongs();
        for(Song s : songs){
            System.out.println(s.getTitle());
        }  */
        
        new PlayerUI(true).setVisible(true);
        
        new PlayerUI(false).setVisible(true);
        //new CorePlayerUI().setVisible(true);
    }
}
