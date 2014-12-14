/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.musicplayer.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.musicplayer.model.RecentSongs;
import org.mymusicplayer.HibernateUtil;
import sun.swing.FilePane;

/**
 *
 * @author preet
 */
public class RecentSongsDao {
     SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    
   // Session session = sessionFactory.getCurrentSession();
     //   session.beginTransaction();
       // songId = (int)session.save(song);
       // session.getTransaction().commit();
    
    public void addRecentSong(String songFilePath){
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        RecentSongs recentSongs = (RecentSongs) session.get(RecentSongs.class, 1);
        session.getTransaction().commit();
        //////////
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<String> rsongList = recentSongs.getRecentSongList();
        if(rsongList.size()<10){
            rsongList.add(songFilePath);
        }else{
            rsongList.remove(1);
            rsongList.add(songFilePath);
        }
        session.update(recentSongs);
        session.getTransaction().commit();
    }
    
    public RecentSongs getRecentSongs(){
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        RecentSongs recentSongs = (RecentSongs) session.get(RecentSongs.class, 1);
        session.getTransaction().commit();
        return recentSongs;
    }
    
    public void initRecentSong(){
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.save(new RecentSongs());
        session.getTransaction().commit();
    }
    
    
}
