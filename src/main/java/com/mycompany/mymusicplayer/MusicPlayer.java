/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mymusicplayer;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.musicplayer.model.Song;

/**
 *
 * @author preet
 */
public class MusicPlayer {
    
    public static void main(String[] args){
       Song song = new Song();
        song.setSinger("Preet");
        song.setTitle("Ranjhana");
        
        
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(song);
        session.getTransaction().commit();
        System.out.print("saved");
    }
}
