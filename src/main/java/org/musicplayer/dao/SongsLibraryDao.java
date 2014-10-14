/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.musicplayer.dao;

import java.util.List;
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
        try{
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        songId = (int)session.save(song);
        session.getTransaction().commit();
        session.close();
        //System.out.print(songId);
        }catch(Exception ex){
            HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().rollback();
        }
        return songId;
    }
    
    public List getAllSongs(){
        
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("select songId, title, artist, album, length, genre from Song");
        List<Object[]> songList = (List<Object[]>)query.list();
        session.getTransaction().commit();
        session.close();
        return songList;
    }
}
