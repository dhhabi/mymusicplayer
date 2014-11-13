/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.musicplayer.ui;

import com.mpatric.mp3agic.ID3v1;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.Timer;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javazoom.jlgui.basicplayer.BasicPlayer;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.time.DurationFormatUtils;
import org.musicplayer.dao.PlayListDao;
import org.musicplayer.dao.SongsLibraryDao;
import org.musicplayer.model.Playlist;
import org.musicplayer.model.Song;
import org.mymusicplayer.MusicPlayerV2;
import org.mymusicplayer.MyPlayerV1;

/**
 *
 * @author preet
 */
public class PlayerUI extends javax.swing.JFrame {

    /**
     * Creates new form Player
     */
    private boolean playListFlag;
    // public static MyPlayer myPlayer = new MyPlayer();
    //public static MyPlayerV1 myPlayerV1 = new MyPlayerV1();
    public static MusicPlayerV2 myPlayerV2 = new MusicPlayerV2();
    public static int loop;
    private final DefaultTableModel model = new DefaultTableModel(new String[]{"Id", "Title", "Artist", "Album", "Length", "Genre", "File Path", "Year", "Comments"}, 0);
    private TableColumn colmn;
    private int selectedRow = 0;
    private static boolean paused = false;
    public static BasicPlayer musicPlayer = new BasicPlayer();
    //private SongsLibraryDao songLibrary = new SongsLibraryDao();
    // private SongLibraryService libraryService = new SongLibraryService();
    private final SongsLibraryDao songsLibraryDao;
    private final PlayListDao playlistDao;
    private final DefaultTreeModel treeModel;
    private String currentShowingPlaylist;
    private final String playListPassed;
    private int currentSongIndex;
    private boolean shuffle = false;
    private boolean repeat = false;

    private static DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("Playlist");

    public static Timer timer = null;
    public static int timeCount = 0;
    public static JProgressBar progressBar;
    private UpdateWorker updateWorker = new UpdateWorker();

    public PlayerUI(boolean flag, String playListName) {
        initComponents();
        PlayerUI.progressBar = jProgressBar;
        treePlayList.setSelectionRow(0);
        this.currentShowingPlaylist = playListName;
        this.playlistDao = new PlayListDao();
        this.songsLibraryDao = new SongsLibraryDao();
        this.treeModel = new DefaultTreeModel(rootNode);
        this.playListPassed = playListName;

        initPlayListTree();
        treePlayList.setModel(treeModel);
        //custome
        this.playListFlag = flag;

        this.addWindowListener(new WindowListener() {

            @Override
            public void windowClosing(WindowEvent e) {
                if (playListFlag) {
                    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                } else {
                    setDefaultCloseOperation(EXIT_ON_CLOSE);
                }
            }

            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
            }

            @Override
            public void windowIconified(WindowEvent e) {
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
            }

            @Override
            public void windowActivated(WindowEvent e) {
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
            }
        });

        if (playListFlag) {
            mainPanel.setVisible(false);
            //setSize(400,500);
        }

        initTable();
        if (playListPassed.equals("library")) {
            initSongLibrary();
        } else {
            this.setTitle(playListPassed);
            showPlaylist(playlistDao.getPlaylsit(playListPassed));
        }//setLayout(new GridLayout(2,2));
        //initDragDrop();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tablePopupMenu = new javax.swing.JPopupMenu();
        menuItemAddToLibrary = new javax.swing.JMenuItem();
        menuItemDeleteFromLibrary = new javax.swing.JMenuItem();
        menuAddToPlaylist = new javax.swing.JMenu();
        popUpPlaylistTree = new javax.swing.JPopupMenu();
        menuItemOpenInNewWindow = new javax.swing.JMenuItem();
        menuItemDeletePlaylist = new javax.swing.JMenuItem();
        lblNowPlaying = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        cmdPlay = new javax.swing.JButton();
        cmdStop = new javax.swing.JButton();
        cmdPause = new javax.swing.JButton();
        cmdPrevious = new javax.swing.JButton();
        cmdNext = new javax.swing.JButton();
        mainPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        treePlayList = new javax.swing.JTree();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();
        tableScorllPan = new javax.swing.JScrollPane();
        tableLibrary = new javax.swing.JTable();
        volumeSlider = new javax.swing.JSlider();
        jLabel2 = new javax.swing.JLabel();
        jProgressBar = new javax.swing.JProgressBar();
        lblStartTimer = new javax.swing.JLabel();
        lblEndTimer = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuItemExit = new javax.swing.JMenu();
        menuItemOpen = new javax.swing.JMenuItem();
        menuItemAddSong = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        miCreatePlaylist = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        miPlay = new javax.swing.JMenuItem();
        miNext = new javax.swing.JMenuItem();
        miPrevious = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        miIncreaseVolume = new javax.swing.JMenuItem();
        miDecreaseVolume = new javax.swing.JMenuItem();
        miShuffle = new javax.swing.JCheckBoxMenuItem();
        miRepeat = new javax.swing.JCheckBoxMenuItem();

        menuItemAddToLibrary.setText("Add to Library");
        menuItemAddToLibrary.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemAddToLibraryActionPerformed(evt);
            }
        });
        tablePopupMenu.add(menuItemAddToLibrary);

        menuItemDeleteFromLibrary.setText("Delete From Library");
        menuItemDeleteFromLibrary.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemDeleteFromLibraryActionPerformed(evt);
            }
        });
        tablePopupMenu.add(menuItemDeleteFromLibrary);

        menuAddToPlaylist.setText("Add to Playlist");
        menuAddToPlaylist.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                menuAddToPlaylistMouseEntered(evt);
            }
        });
        tablePopupMenu.add(menuAddToPlaylist);

        menuItemOpenInNewWindow.setText("Open in new Window");
        menuItemOpenInNewWindow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemOpenInNewWindowActionPerformed(evt);
            }
        });
        popUpPlaylistTree.add(menuItemOpenInNewWindow);

        menuItemDeletePlaylist.setText("Delete Playlist");
        menuItemDeletePlaylist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemDeletePlaylistActionPerformed(evt);
            }
        });
        popUpPlaylistTree.add(menuItemDeletePlaylist);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Music Player");
        setBackground(new java.awt.Color(91, 64, 38));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setType(java.awt.Window.Type.UTILITY);

        lblNowPlaying.setText("**");

        jLabel1.setText("Now Playing:");

        cmdPlay.setText("Play");
        cmdPlay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdPlayActionPerformed(evt);
            }
        });

        cmdStop.setText("Stop");
        cmdStop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdStopActionPerformed(evt);
            }
        });

        cmdPause.setText("Pause");
        cmdPause.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdPauseActionPerformed(evt);
            }
        });

        cmdPrevious.setText("<<");
        cmdPrevious.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdPreviousActionPerformed(evt);
            }
        });

        cmdNext.setText(">>");
        cmdNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdNextActionPerformed(evt);
            }
        });

        treePlayList.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("Playlists");
        javax.swing.tree.DefaultMutableTreeNode treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("myPlaylist");
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("two");
        treeNode1.add(treeNode2);
        treePlayList.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        treePlayList.setFocusable(false);
        treePlayList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                treePlayListMouseClicked(evt);
            }
        });
        treePlayList.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
            public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
                treePlayListValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(treePlayList);

        treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("Library");
        jTree1.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        jTree1.setFocusable(false);
        jTree1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTree1MouseClicked(evt);
            }
        });
        jTree1.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
            public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
                jTree1ValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(jTree1);

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 437, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tableLibrary.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tableLibrary.setComponentPopupMenu(tablePopupMenu);
        tableLibrary.setFocusable(false);
        tableLibrary.setShowVerticalLines(false);
        tableLibrary.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tableLibraryMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tableLibraryMouseReleased(evt);
            }
        });
        tableScorllPan.setViewportView(tableLibrary);

        volumeSlider.setMajorTickSpacing(20);
        volumeSlider.setMinorTickSpacing(1);
        volumeSlider.setPaintTicks(true);
        volumeSlider.setToolTipText("");
        volumeSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                volumeSliderStateChanged(evt);
            }
        });
        volumeSlider.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                volumeSliderCaretPositionChanged(evt);
            }
        });

        jLabel2.setText("Vol.");

        lblStartTimer.setText("00:00 :00");

        lblEndTimer.setText("00:00:00");

        menuItemExit.setText("File");

        menuItemOpen.setText("Open");
        menuItemOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemOpenActionPerformed(evt);
            }
        });
        menuItemExit.add(menuItemOpen);

        menuItemAddSong.setText("Add Song to library");
        menuItemAddSong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemAddSongActionPerformed(evt);
            }
        });
        menuItemExit.add(menuItemAddSong);

        jMenuItem2.setText("Delete Song from library");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        menuItemExit.add(jMenuItem2);

        miCreatePlaylist.setText("Create Playlist");
        miCreatePlaylist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miCreatePlaylistActionPerformed(evt);
            }
        });
        menuItemExit.add(miCreatePlaylist);

        jMenuItem1.setText("Exit");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        menuItemExit.add(jMenuItem1);

        jMenuBar1.add(menuItemExit);

        jMenu1.setText("Controls");

        miPlay.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_SPACE, 0));
        miPlay.setText("Play");
        miPlay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miPlayActionPerformed(evt);
            }
        });
        jMenu1.add(miPlay);

        miNext.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_RIGHT, java.awt.event.InputEvent.CTRL_MASK));
        miNext.setText("Next");
        miNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miNextActionPerformed(evt);
            }
        });
        jMenu1.add(miNext);

        miPrevious.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_LEFT, java.awt.event.InputEvent.CTRL_MASK));
        miPrevious.setText("Previous");
        miPrevious.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miPreviousActionPerformed(evt);
            }
        });
        jMenu1.add(miPrevious);

        jMenu2.setText("Play Recent");
        jMenu1.add(jMenu2);

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem3.setText("Go to  Current Song");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jSeparator1.setForeground(new java.awt.Color(68, 61, 61));
        jMenu1.add(jSeparator1);

        miIncreaseVolume.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.CTRL_MASK));
        miIncreaseVolume.setText("Increase Volume");
        miIncreaseVolume.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miIncreaseVolumeActionPerformed(evt);
            }
        });
        jMenu1.add(miIncreaseVolume);

        miDecreaseVolume.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_MASK));
        miDecreaseVolume.setText("Decrease Volume");
        miDecreaseVolume.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miDecreaseVolumeActionPerformed(evt);
            }
        });
        jMenu1.add(miDecreaseVolume);

        miShuffle.setSelected(true);
        miShuffle.setText("Shuffle");
        miShuffle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miShuffleActionPerformed(evt);
            }
        });
        jMenu1.add(miShuffle);

        miRepeat.setSelected(true);
        miRepeat.setText("Repeat");
        miRepeat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miRepeatActionPerformed(evt);
            }
        });
        jMenu1.add(miRepeat);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tableScorllPan, javax.swing.GroupLayout.DEFAULT_SIZE, 777, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cmdStop, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmdPrevious)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmdPlay, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmdNext)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmdPause, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(137, 137, 137)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addGap(5, 5, 5)
                        .addComponent(lblNowPlaying, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(83, 83, 83)))
                .addComponent(volumeSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblStartTimer)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 455, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblEndTimer)
                .addGap(153, 153, 153))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tableScorllPan, javax.swing.GroupLayout.PREFERRED_SIZE, 469, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblStartTimer)
                            .addComponent(lblEndTimer, javax.swing.GroupLayout.Alignment.LEADING))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNowPlaying)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmdStop)
                            .addComponent(cmdPlay)
                            .addComponent(cmdPause)
                            .addComponent(cmdPrevious)
                            .addComponent(cmdNext)))
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(volumeSlider, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmdStopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdStopActionPerformed

        //myPlayer.stop();
        myPlayerV2.stop();

    }//GEN-LAST:event_cmdStopActionPerformed

    private void cmdPlayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdPlayActionPerformed
        //myPlayer.resume();
        if (paused) {
            myPlayerV2.resume();
            paused = false;
        } else {
            myPlayerV2.stop();
            myPlayerV2.play((String) tableLibrary.getValueAt(selectedRow, 6));
            currentSongIndex = selectedRow;
            jProgressBar.setMinimum(0);
            int duration = Integer.parseInt((String)tableLibrary.getValueAt(selectedRow, 4))/1000;
            jProgressBar.setMaximum(duration);
            jProgressBar.setValue(0);
            
            updateWorker.done();
            updateWorker = new UpdateWorker(duration);
            updateWorker.execute();
            
        }
    }//GEN-LAST:event_cmdPlayActionPerformed

    private void cmdPauseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdPauseActionPerformed

        myPlayerV2.pause();
        //myPlayer.pause();
        paused = true;
        //myPlayerV1.pausePlaying();
    }//GEN-LAST:event_cmdPauseActionPerformed

    private void tableLibraryMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableLibraryMousePressed
        JTable table = (JTable) evt.getSource();
        Point p = evt.getPoint();
        selectedRow = table.rowAtPoint(p);
        int selectedSongId = (int) tableLibrary.getValueAt(selectedRow, 0);
        //System.out.println(selectedRow);
        if (evt.getClickCount() == 2) {
            //Handle double click event
            handleTableDoubleClick(selectedSongId);
            currentSongIndex = selectedRow;

        }
    }//GEN-LAST:event_tableLibraryMousePressed

    private void tableLibraryMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableLibraryMouseReleased
        JTable table = (JTable) evt.getSource();
        Point p = evt.getPoint();
        selectedRow = table.rowAtPoint(p);
        //System.out.println(selectedRow);
    }//GEN-LAST:event_tableLibraryMouseReleased

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void menuItemOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemOpenActionPerformed
        FileNameExtensionFilter fileFilter = new FileNameExtensionFilter("Mp3 Files", "mp3", "mpeg3");
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.addChoosableFileFilter(fileFilter);
        int returnVal = fileChooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            myPlayerV2.stop();
            File songFile = fileChooser.getSelectedFile();
            //FileInputStream fis;
            //fis = new FileInputStream(songFile);
            myPlayerV2.play(songFile.getAbsolutePath());
            lblNowPlaying.setText(songFile.getName());
            //  fis.close();

        }
    }//GEN-LAST:event_menuItemOpenActionPerformed

    private void menuItemAddToLibraryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemAddToLibraryActionPerformed

        addSongToLibraryFromFileSystem();

    }//GEN-LAST:event_menuItemAddToLibraryActionPerformed

    private void menuItemDeleteFromLibraryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemDeleteFromLibraryActionPerformed

        int songId = (int) tableLibrary.getValueAt(selectedRow, 0);
        if (songId == 0) {
            JOptionPane.showMessageDialog(rootPane, "Song not added to library yet");

        } else {
            int deletedRows = songsLibraryDao.deleteSong(songId);
            if (deletedRows > 0) {
                DefaultTableModel tableModel = (DefaultTableModel) tableLibrary.getModel();
                tableModel.removeRow(selectedRow);
                JOptionPane.showMessageDialog(rootPane, "Song deleted successfully");
            }
        }


    }//GEN-LAST:event_menuItemDeleteFromLibraryActionPerformed

    private void cmdNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdNextActionPerformed
       playNext();
    }//GEN-LAST:event_cmdNextActionPerformed

    private void cmdPreviousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdPreviousActionPerformed
        playPrevious();
    }//GEN-LAST:event_cmdPreviousActionPerformed

    private void menuItemAddSongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemAddSongActionPerformed
        addSongToLibraryFromFileSystem();
    }//GEN-LAST:event_menuItemAddSongActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        int songId = (int) tableLibrary.getValueAt(selectedRow, 0);
        if (songId == 0) {
            JOptionPane.showMessageDialog(rootPane, "Song not added to library yet");

        } else {
            int deletedRows = songsLibraryDao.deleteSong(songId);
            if (deletedRows > 0) {
                DefaultTableModel tableModel = (DefaultTableModel) tableLibrary.getModel();
                tableModel.removeRow(selectedRow);
                JOptionPane.showMessageDialog(rootPane, "Song deleted successfully");
            }
        }

    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jTree1ValueChanged(javax.swing.event.TreeSelectionEvent evt) {//GEN-FIRST:event_jTree1ValueChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jTree1ValueChanged

    private void jTree1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTree1MouseClicked
        clearTable(tableLibrary);
        initSongLibrary();
        currentShowingPlaylist = "library";
    }//GEN-LAST:event_jTree1MouseClicked

    private void volumeSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_volumeSliderStateChanged
        //volumeSlider.getValue();
        myPlayerV2.setVolume((double) volumeSlider.getValue() / 100.00);
    }//GEN-LAST:event_volumeSliderStateChanged

    private void volumeSliderCaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_volumeSliderCaretPositionChanged

    }//GEN-LAST:event_volumeSliderCaretPositionChanged

    private void miCreatePlaylistActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miCreatePlaylistActionPerformed
        String playListName = JOptionPane.showInputDialog("Input Playlist name");

        if (playlistDao.isAlreadyExist(playListName)) {
            JOptionPane.showMessageDialog(rootPane, "Already Exist");
        } else {
            Playlist newPlaylist = new Playlist(playListName);
            playlistDao.createNewPlaylist(newPlaylist);
            DefaultMutableTreeNode newChildNode = new DefaultMutableTreeNode(playListName);
            //DefaultMutableTreeNode root = (DefaultMutableTreeNode) treePlayList.getModel().getRoot();
            rootNode.add(newChildNode);

            TreePath pathToChild = new TreePath(newChildNode);
            treePlayList.setSelectionPath(pathToChild);
            DefaultTreeCellRenderer renderer
                    = (DefaultTreeCellRenderer) treePlayList.getCellRenderer();
            renderer.setTextSelectionColor(Color.white);
            renderer.setBackgroundSelectionColor(Color.blue);
            renderer.setBorderSelectionColor(Color.black);

            ((DefaultTreeModel) (treePlayList.getModel())).reload();

            clearTable(tableLibrary);
            currentShowingPlaylist = playListName;

            int row = treePlayList.getRowForPath(pathToChild);
            treePlayList.setSelectionRow(row);
        }
    }//GEN-LAST:event_miCreatePlaylistActionPerformed

    private void treePlayListValueChanged(javax.swing.event.TreeSelectionEvent evt) {//GEN-FIRST:event_treePlayListValueChanged

    }//GEN-LAST:event_treePlayListValueChanged

    private void treePlayListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_treePlayListMouseClicked
        //JTree tree = (JTree) evt.getSource();
        if (SwingUtilities.isRightMouseButton(evt)) {
            TreePath path = treePlayList.getPathForLocation(evt.getX(), evt.getY());

            Rectangle pathBounds = treePlayList.getUI().getPathBounds(treePlayList, path);
            if (pathBounds != null && pathBounds.contains(evt.getX(), evt.getY())) {
                popUpPlaylistTree.show(treePlayList, pathBounds.x, pathBounds.y + pathBounds.height);
            }

            int row = treePlayList.getClosestRowForLocation(evt.getX(), evt.getY());
            treePlayList.setSelectionRow(row);
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) treePlayList.getLastSelectedPathComponent();

            Object nodeInfo = node.getUserObject();
            String selectedNode = (String) nodeInfo;
            currentShowingPlaylist = selectedNode;

        } else if (SwingUtilities.isLeftMouseButton(evt)) {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) treePlayList.getLastSelectedPathComponent();

            Object nodeInfo = node.getUserObject();
            String selectedNode = (String) nodeInfo;
            currentShowingPlaylist = selectedNode;
            if (!selectedNode.equals("Playlist")) {
                Playlist playList = playlistDao.getPlaylsit(currentShowingPlaylist);
                showPlaylist(playList);
            }

        }
    }//GEN-LAST:event_treePlayListMouseClicked

    private void menuItemOpenInNewWindowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemOpenInNewWindowActionPerformed
        new PlayerUI(true, currentShowingPlaylist).setVisible(true);
        clearTable(tableLibrary);
        initSongLibrary();
    }//GEN-LAST:event_menuItemOpenInNewWindowActionPerformed

    private void menuItemDeletePlaylistActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemDeletePlaylistActionPerformed
        deleteSelectedNode();
        int i = playlistDao.deletePlaylist(currentShowingPlaylist);
        if (i > 0) {
            clearTable(tableLibrary);
            initSongLibrary();
            JOptionPane.showMessageDialog(rootPane, "Playlist deleted successfully !");
        }
    }//GEN-LAST:event_menuItemDeletePlaylistActionPerformed

    private void menuAddToPlaylistMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuAddToPlaylistMouseEntered
        menuAddToPlaylist.removeAll();
        List<String> listOfPlaylist = playlistDao.getAllPlaylistNames();
        for (final String playlistName : listOfPlaylist) {
            JMenuItem menuItem = new JMenuItem(playlistName);
            menuAddToPlaylist.add(menuItem);
            menuItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    playlistDao.addSongToPlaylist(playlistName, (int) tableLibrary.getValueAt(selectedRow, 0));
                }
            });
        }
    }//GEN-LAST:event_menuAddToPlaylistMouseEntered

    private void miPlayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miPlayActionPerformed
        //JOptionPane.showMessageDialog(rootPane, selectedRow);
        myPlayerV2.play((String) tableLibrary.getValueAt(selectedRow, 6));
        tableLibrary.setRowSelectionInterval(selectedRow, selectedRow);
    }//GEN-LAST:event_miPlayActionPerformed

    private void miNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miNextActionPerformed
        playNext();
    }//GEN-LAST:event_miNextActionPerformed

    private void miPreviousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miPreviousActionPerformed
        playPrevious();
    }//GEN-LAST:event_miPreviousActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        tableLibrary.setRowSelectionInterval(currentSongIndex, currentSongIndex);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void miIncreaseVolumeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miIncreaseVolumeActionPerformed
        if(volumeSlider.getValue()<80)
            volumeSlider.getModel().setValue(volumeSlider.getValue() + 20);
        else
            volumeSlider.getModel().setValue(100);
        //JOptionPane.showMessageDialog(rootPane, volumeSlider.getValue());
        //volumeSlider.repaint();
    }//GEN-LAST:event_miIncreaseVolumeActionPerformed

    private void miDecreaseVolumeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miDecreaseVolumeActionPerformed
        if(volumeSlider.getValue()>20)
            volumeSlider.getModel().setValue(volumeSlider.getValue() - 20);
        else
            volumeSlider.getModel().setValue(0);
    }//GEN-LAST:event_miDecreaseVolumeActionPerformed

    private void miShuffleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miShuffleActionPerformed
       shuffle = miShuffle.isSelected();
    }//GEN-LAST:event_miShuffleActionPerformed

    private void miRepeatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miRepeatActionPerformed
        repeat = miRepeat.isSelected();
    }//GEN-LAST:event_miRepeatActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PlayerUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PlayerUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PlayerUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PlayerUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PlayerUI(false, "").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdNext;
    private javax.swing.JButton cmdPause;
    private javax.swing.JButton cmdPlay;
    private javax.swing.JButton cmdPrevious;
    private javax.swing.JButton cmdStop;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JProgressBar jProgressBar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JTree jTree1;
    private javax.swing.JLabel lblEndTimer;
    private javax.swing.JLabel lblNowPlaying;
    private javax.swing.JLabel lblStartTimer;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenu menuAddToPlaylist;
    private javax.swing.JMenuItem menuItemAddSong;
    private javax.swing.JMenuItem menuItemAddToLibrary;
    private javax.swing.JMenuItem menuItemDeleteFromLibrary;
    private javax.swing.JMenuItem menuItemDeletePlaylist;
    private javax.swing.JMenu menuItemExit;
    private javax.swing.JMenuItem menuItemOpen;
    private javax.swing.JMenuItem menuItemOpenInNewWindow;
    private javax.swing.JMenuItem miCreatePlaylist;
    private javax.swing.JMenuItem miDecreaseVolume;
    private javax.swing.JMenuItem miIncreaseVolume;
    private javax.swing.JMenuItem miNext;
    private javax.swing.JMenuItem miPlay;
    private javax.swing.JMenuItem miPrevious;
    private javax.swing.JCheckBoxMenuItem miRepeat;
    private javax.swing.JCheckBoxMenuItem miShuffle;
    private javax.swing.JPopupMenu popUpPlaylistTree;
    private javax.swing.JTable tableLibrary;
    private javax.swing.JPopupMenu tablePopupMenu;
    private javax.swing.JScrollPane tableScorllPan;
    private javax.swing.JTree treePlayList;
    private javax.swing.JSlider volumeSlider;
    // End of variables declaration//GEN-END:variables

    private void initSongLibrary() {
        currentShowingPlaylist = "library";
        List<Object[]> songs = songsLibraryDao.getAllSongs();
        for (Object[] song : songs) {
            //{songId,title,artist,album,length,genre,filePath,year,comment};
            //songId, title, artist, album, songLength, genre, songYear, comments, songPath
            Object[] rowdata = {song[0], song[1], song[2], song[3], song[4], song[5], song[8], song[6], song[7]};
            model.addRow(rowdata);
        }

    }

    private void addRowToLibraryTable(int songId, String title, String artist, String album, String length, String genre, String filePath, String year, String comment) {
        DefaultTableModel tableModel = (DefaultTableModel) tableLibrary.getModel();
        Object[] rowdata = {songId, title, artist, album, length, genre, filePath, year, comment};
        tableModel.addRow(rowdata);
    }

    private void addRowToLibraryTable(File songFile) {
        try {
            String filePath = songFile.getAbsolutePath();
            System.out.println(filePath);
            Mp3File mp3File = new Mp3File(filePath);
            ID3v1 tags = mp3File.getId3v1Tag();
            Song song = new Song();
            song.setTitle(tags.getTitle());
            song.setAlbum(tags.getAlbum());
            song.setArtist(tags.getArtist());
            song.setGenre(tags.getGenreDescription());
            song.setSongYear(tags.getYear());
            song.setComments(tags.getComment());
            song.setSongPath(filePath);
            //song.setSongLength(DurationFormatUtils.formatDurationHMS(mp3File.getLengthInMilliseconds()));
            song.setSongLength(String.valueOf(mp3File.getLengthInMilliseconds()));
//int songId = songsLibraryDao.addSong(song);  
            //new thread to save song in the library 
            //String songPath= (String)tableLibrary.getValueAt(selectedRow, 6);
            if (songsLibraryDao.isAlreadyExist(filePath)) {
                int songId = songsLibraryDao.getSongUsingFilePath(filePath);

                if (playListFlag) {

                    addRowToLibraryTable(songId, tags.getTitle(), tags.getArtist(), tags.getAlbum(), String.valueOf(mp3File.getLengthInMilliseconds()), String.valueOf(tags.getGenreDescription()), filePath, tags.getYear(), tags.getComment());
                    playlistDao.addSongToPlaylist(currentShowingPlaylist, songId);

                } else {
                    JOptionPane.showMessageDialog(rootPane, "Song Already exist " + filePath);
                }
            } else {
                int songId = songsLibraryDao.addSong(song);
                addRowToLibraryTable(songId, tags.getTitle(), tags.getArtist(), tags.getAlbum(), String.valueOf(mp3File.getLengthInMilliseconds()), String.valueOf(tags.getGenreDescription()), filePath, tags.getYear(), tags.getComment());
                if (playListFlag) {
                    if (!currentShowingPlaylist.equals("library")) {
                        playlistDao.addSongToPlaylist(currentShowingPlaylist, songId);
                    }
                }

            }
        } catch (IOException | UnsupportedTagException | InvalidDataException ex) {
            Logger.getLogger(PlayerUI.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void clearTable(JTable table) {
        DefaultTableModel dm = (DefaultTableModel) table.getModel();
        int rowCount = dm.getRowCount();
        //Remove rows one by one from the end of the table
        for (int i = rowCount - 1; i >= 0; i--) {
            dm.removeRow(i);
        }
    }

    private void initTable() {

        //model = new DefaultTableModel();
        //scroll = new JScrollPane(tableLibrary);
        tableLibrary.setModel(model);

        colmn = tableLibrary.getColumnModel().getColumn(0);
        colmn.setMinWidth(0);
        colmn.setMaxWidth(0);
        //hide lenght column
        colmn = tableLibrary.getColumnModel().getColumn(3);
        colmn.setMinWidth(0);
        colmn.setMaxWidth(0);
        colmn = tableLibrary.getColumnModel().getColumn(1);
        colmn.setPreferredWidth(150);
        colmn = tableLibrary.getColumnModel().getColumn(6);
        //colmn.setMinWidth(0);
        //colmn.setMaxWidth(0);

        tableLibrary.setDropTarget(new DropTarget() {
            @Override
            public synchronized void drop(DropTargetDropEvent dtde) {
                Point point = dtde.getLocation();
                int column = tableLibrary.columnAtPoint(point);
                int row = tableLibrary.rowAtPoint(point);

                //JOptionPane.showMessageDialog(rootPane, "Inside");
                handleDropedFile(dtde);
                super.drop(dtde);
            }
        });

        tableScorllPan.setDropTarget(new DropTarget() {
            @Override
            public synchronized void drop(DropTargetDropEvent dtde) {
                handleDropedFile(dtde);
            }
        });
    }

    private void handleDropedFile(DropTargetDropEvent dtde) {

        try {
            // handle drop outside current table
            //JOptionPane.showMessageDialog(rootPane, "Outside");
            dtde.acceptDrop(DnDConstants.ACTION_LINK);
            Transferable t = dtde.getTransferable();
            List fileList = (List) t.getTransferData(DataFlavor.javaFileListFlavor);
            int rowCount = tableLibrary.getRowCount();
            selectedRow = rowCount;
            for (Object file : fileList) {
                final File songFile = (File) file;
                new Thread(new Runnable() {

                    @Override
                    public void run() {
                        addRowToLibraryTable(songFile);
                    }
                }).start();
            }

            File firstSongFile = (File) fileList.get(0);

            /*  try ( //Play mp3 file here
             FileInputStream fis = new FileInputStream(firstSongFile)) {
             myPlayerV1.stopPlaying();
             myPlayerV1.playSong(IOUtils.toByteArray(fis));

             fis.close();
             //super.drop(dtde);
             }*/
        } catch (UnsupportedFlavorException | IOException ex) {
            Logger.getLogger(PlayerUI.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void handleTableDoubleClick(int selectedSongId) {
        File songFile = new File((String) tableLibrary.getValueAt(selectedRow, 6));
        lblNowPlaying.setText((String) tableLibrary.getValueAt(selectedRow, 1));
        myPlayerV2.play(songFile.getAbsolutePath());
    }

    private void addSongToLibraryFromFileSystem() {
        FileNameExtensionFilter fileFilter = new FileNameExtensionFilter("Mp3 Files", "mp3", "mpeg3");
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.addChoosableFileFilter(fileFilter);
        int returnVal = fileChooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            //myPlayerV1.stopPlaying();
            final File songFile = fileChooser.getSelectedFile();

            FileInputStream fis;
            try {
                fis = new FileInputStream(songFile);
                //myPlayer.play(IOUtils.toByteArray(fis));
                lblNowPlaying.setText(songFile.getName());
                fis.close();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(PlayerUI.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(PlayerUI.class.getName()).log(Level.SEVERE, null, ex);
            }

            int rowCount = tableLibrary.getRowCount();
            selectedRow = rowCount;
            addRowToLibraryTable(songFile);
            //addSongToLibrary();
        }
    }

    private void initPlayListTree() {

        List<String> listOfPlaylist = playlistDao.getAllPlaylistNames();
        for (String playlistName : listOfPlaylist) {
            DefaultMutableTreeNode playlistNameNode = new DefaultMutableTreeNode(playlistName);
            rootNode.add(playlistNameNode);
        }
        ((DefaultTreeModel) (treePlayList.getModel())).reload();
    }

    private void showPlaylist(Playlist playList) {
        currentShowingPlaylist = playList.getPlaylistName();
        ArrayList<Integer> songList = playList.getSongList();
        clearTable(tableLibrary);
        for (int i : songList) {
            Song song = songsLibraryDao.getSong(i);
            if (song != null) {
                addRowToLibraryTable(song.getSongId(), song.getTitle(), song.getArtist(), song.getAlbum(), song.getSongLength(), song.getGenre(), song.getSongPath(), song.getSongYear(), song.getComments());
            }
        }
    }

    private void deleteSelectedNode() {
        DefaultMutableTreeNode node;
        DefaultTreeModel myModel = (DefaultTreeModel) (treePlayList.getModel());
        TreePath[] paths = treePlayList.getSelectionPaths();
        for (TreePath path : paths) {
            node = (DefaultMutableTreeNode) (path.getLastPathComponent());
            myModel.removeNodeFromParent(node);
        }
    }

    private void playNext() {
         int rowCount = tableLibrary.getRowCount() - 1;

        if (selectedRow < rowCount) {
            ++selectedRow;
            handleTableDoubleClick((int) tableLibrary.getValueAt(selectedRow, 0));
            currentSongIndex = selectedRow;
        } else {
            selectedRow = 0;
            handleTableDoubleClick((int) tableLibrary.getValueAt(selectedRow, 0));
            currentSongIndex = selectedRow;
        }
        tableLibrary.setRowSelectionInterval(selectedRow, selectedRow);
    }

    private void playPrevious() {
        int rowCount = tableLibrary.getRowCount() - 1;

        if (selectedRow > 0) {
            --selectedRow;
            handleTableDoubleClick((int) tableLibrary.getValueAt(selectedRow, 0));
            currentSongIndex = selectedRow;
        } else {
            selectedRow = rowCount;
            handleTableDoubleClick((int) tableLibrary.getValueAt(selectedRow, 0));
            currentSongIndex = selectedRow;
        }

        tableLibrary.setRowSelectionInterval(selectedRow, selectedRow);
    }
    
    
    private void setCurrentSong(){
        selectedRow = tableLibrary.getSelectedRow();  
        currentSongIndex = selectedRow;
    }
    
    

    //////slider class
    private class UpdateWorker extends SwingWorker<Void, Integer> {

        private int duration;
        
        public void setDuration(int duration){
            this.duration = duration;
        }
        
        public UpdateWorker(){
            this.duration = 0;
        }

        public UpdateWorker(int duration) {
            this.duration = duration;
        }

        @Override
        protected Void doInBackground() throws Exception {
            for (int i = 0; i <= duration; i++) {
        if (!paused) {
            publish(i);
            try {
                Thread.sleep(1000);
            } catch(InterruptedException e) {
                //e.printStackTrace();
            }
        }
        while (paused) {
            try {
                Thread.sleep(50);
                continue;
            } catch(InterruptedException e) {
                //e.printStackTrace();
            }
        }
    }
    return null;
        }

        @Override
        protected void process(List<Integer> chunks) {
            progressBar.setValue(chunks.get(0));
            lblStartTimer.setText(DurationFormatUtils.formatDuration(chunks.get(0)*1000, "H:mm:ss"));
            lblEndTimer.setText(DurationFormatUtils.formatDuration((duration-chunks.get(0))*1000, "H:mm:ss"));
            if(chunks.get(0)>=duration)
                done();
        }
        
        @Override
        protected void done(){
            setCursor(null);
            duration = 0;
            lblEndTimer.setText("0:00:00");
            lblStartTimer.setText("0:00:00");
        }

    }

}
