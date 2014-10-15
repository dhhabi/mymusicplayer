/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.musicplayer.dao;

import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.musicplayer.model.Song;
import org.mymusicplayer.HibernateUtil;

/**
 *
 * @author preet
 */
public class SongsLibraryDao {
    
    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    
    public int addSong(Song song){
        int songId=0;
        //try{
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        songId = (int)session.save(song);
        session.getTransaction().commit();
        //System.out.print(songId);
        /*}catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.toString());
            HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().rollback();
            
        }*/
        return songId;
    }
    
    public List getAllSongs(){
        
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery("select songId, title, artist, album, length, genre from Song");
        List<Object[]> songList = (List<Object[]>)query.list();
        session.getTransaction().commit();
        return songList;
    }
    
    public Song getSong(int songId){
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Song song = (Song) session.get(Song.class, songId);
        session.getTransaction().commit();
        return song;
    }
    
    public int deleteSong(int songId){
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery("delete Song where id = "+songId);
        int updatedRows = query.executeUpdate();
        session.getTransaction().commit();
        return updatedRows;
    }
}
