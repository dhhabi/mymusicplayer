/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.musicplayer.ui;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import org.mymusicplayer.MyPlayer;

/**
 *
 * @author preet
 */
public class CorePlayerUI extends JFrame implements ActionListener{
    
    
    private boolean playListFlag;
    MyPlayer myPlayer = new MyPlayer();
    public static int loop;
    private final DefaultTableModel model = new DefaultTableModel(new String[]{"Id","Title","Artist","Album","Length","Genre","Year","Comments"},0);
    private TableColumn colmn;
    private static int selectedRow=0;
    JTable tableLibrary;
    
    public CorePlayerUI(){
        super("MyTunes");
        setSize(560, 600);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(2, 2));
        tableLibrary = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(tableLibrary);
        add(scrollPane);
        initJtable();
        tableLibrary.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("button Clicked");
    }
    

    
private void initJtable(){
    
   
      //tableLibrary.setModel(model);
          
      colmn = tableLibrary.getColumnModel().getColumn(0);
      colmn.setMinWidth(0);
      colmn.setMaxWidth(0);
      colmn = tableLibrary.getColumnModel().getColumn(1);
      colmn.setPreferredWidth(150);
      colmn = tableLibrary.getColumnModel().getColumn(6);
      colmn.setMinWidth(0);
      colmn.setMaxWidth(0);
      Object[] rowData = {"hello"};
      model.addRow(rowData);
      //tableLibrary.setVisible(true);
}

//End of JFrame Class
}


