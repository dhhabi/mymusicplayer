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
import org.musicplayer.model.PlayerState;
import org.musicplayer.model.Playlist;
import org.mymusicplayer.HibernateUtil;

/**
 *
 * @author preet
 */
public class PlayerStateDao {
    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    
   // Session session = sessionFactory.getCurrentSession();
     //   session.beginTransaction();
       // songId = (int)session.save(song);
       // session.getTransaction().commit();
    
    public void savePlayerState(PlayerState playerState){
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.save(playerState);
        session.getTransaction().commit();
        //return true;
    }
    
    public void deleteTableColumn(String columnName){
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        PlayerState playerState = (PlayerState) session.get(PlayerState.class, 1);
        playerState.getColumnList().remove(columnName);
        session.getTransaction().commit();
    }
    
    public void addTableColumn(String columnName){
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        PlayerState playerState = (PlayerState) session.get(PlayerState.class, 1);
        playerState.getColumnList().add(columnName);
        session.getTransaction().commit();
    }
    
    public PlayerState getPlayerState(){
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        PlayerState playerState = (PlayerState)session.get(PlayerState.class, 1);
        session.getTransaction().commit();
        if(playerState==null)
            return new PlayerState();
        else
            return playerState;
    }
    
}
