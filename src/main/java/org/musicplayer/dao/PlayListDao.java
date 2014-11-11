/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.musicplayer.dao;

import org.hibernate.SessionFactory;
import org.mymusicplayer.HibernateUtil;

/**
 *
 * @author preet
 */
public class PlayListDao {
     SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
     
     public int createPlaylist(String playListName){
         
         return 0; 
     }
}
