/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mymusicplayer;

import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author preet
 */
public class Test extends JFrame{
 
    private JTable table = new JTable();
    private JScrollPane scroll = new JScrollPane(table);
    private DefaultTableModel tm = new DefaultTableModel(new String[]{"a","b","c"},2);

    public Test() {
        table.setModel(tm);
        table.setDropTarget(new DropTarget(){
            @Override
            public synchronized void drop(DropTargetDropEvent dtde) {
                Point point = dtde.getLocation();
                int column = table.columnAtPoint(point);
                int row = table.rowAtPoint(point);
                // handle drop inside current tablesi
                JOptionPane.showMessageDialog(rootPane, "Inside");
                super.drop(dtde);
            }
        });
        scroll.setDropTarget(new DropTarget(){
            @Override
            public synchronized void drop(DropTargetDropEvent dtde) {
                // handle drop outside current table (e.g. add row)
                JOptionPane.showMessageDialog(rootPane, "Outside");
                super.drop(dtde);
            }
        });
        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(scroll);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(800,600);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new Test();
    }
}
