/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.musicplayer.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.musicplayer.model.Playlist;
import org.musicplayer.model.Song;
import org.mymusicplayer.HibernateUtil;

/**
 *
 * @author preet
 */
public class PlayListDao {
     SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
     
     public int createNewPlaylist(Playlist playList){
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.save(playList);
        session.getTransaction().commit();
        return 1;
     }
     
     
     public Playlist getPlaylsit(String playlistName){
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Playlist playlist = (Playlist)session.get(Playlist.class, playlistName);
        session.getTransaction().commit();
        return playlist;
     }
     
     public List getAllPlaylistNames(){
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery("select playlistName from Playlist");
        List<Object[]> allPlaylist = (List<Object[]>)query.list();
        session.getTransaction().commit();
        return allPlaylist;
     }
     
     public boolean isAlreadyExist(String playlistName){
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery("select playlistName from Playlist where PLAYLISTNAME='"+playlistName+"'");
        List<Object[]> allPlaylist = (List<Object[]>)query.list();
        boolean items = allPlaylist.isEmpty();
        session.getTransaction().commit();
        return !items;
     }
     
     public void addSongToPlaylist(String playlistName,int songId){
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Playlist playlist = (Playlist) session.get(Playlist.class, playlistName);
        playlist.getSongList().add(songId);
        session.getTransaction().commit();
     }
     
     
      public int deletePlaylist(String playlistName){
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery("delete Playlist where PLAYLISTNAME ='"+playlistName+"'");
        int updatedRows = query.executeUpdate();
        session.getTransaction().commit();
        return updatedRows;
    }
     
}
