/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mymusicplayer;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Control;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author preet
 */
public class MyPlayerV1 {

    private byte[] currentSongBytes;
    AudioFormat decodedFormat;
    AudioInputStream din;
    SourceDataLine line;
    private static boolean playClick = true;
    volatile boolean paused = false;
    final Object lock = new Object();

    public void playSong(byte[] songBytes) {
        AudioInputStream in = null;
        try {
            currentSongBytes = songBytes;
            ByteArrayInputStream bin = new ByteArrayInputStream(currentSongBytes);
            BufferedInputStream buffIn = new BufferedInputStream(bin);
            in = AudioSystem.getAudioInputStream(buffIn);

            AudioFormat baseFormat = in.getFormat();
            decodedFormat = new AudioFormat(
                    AudioFormat.Encoding.PCM_SIGNED, // Encoding to use
                    baseFormat.getSampleRate(), // sample rate (same as base format)
                    16, // sample size in bits (thx to Javazoom)
                    baseFormat.getChannels(), // # of Channels
                    baseFormat.getChannels() * 2, // Frame Size
                    baseFormat.getSampleRate(), // Frame Rate
                    false // Big Endian
            );

            din = AudioSystem.getAudioInputStream(decodedFormat, in);

            new Thread() {
                @Override
                public void run() {
                    rawPlay(decodedFormat, din);
                }
            }.start();

        } catch (UnsupportedAudioFileException | IOException ex) {
            Logger.getLogger(MyPlayerV1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void rawPlay(AudioFormat decodedFormat, AudioInputStream din) {
        playClick = true;
        try {
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, decodedFormat);
            line = (SourceDataLine) AudioSystem.getLine(info);
            line.open(decodedFormat);

            line.open(decodedFormat);
            byte[] data = new byte[4096];
            // Start
            line.start();

            int nBytesRead;
            synchronized (lock) {
                while ((nBytesRead = din.read(data, 0, data.length)) != -1) {
                    while (paused) {
                        if (line.isRunning()) {
                            line.stop();
                        }
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                        }
                    }

                    if (!line.isRunning()) {
                        line.start();
                    }
                    line.write(data, 0, nBytesRead);
                    //break the loop if user click stop
                    if(!playClick)
                        break;
                }
            }
            // Stop
            line.drain();
            line.stop();
            din.close();
        } catch (LineUnavailableException | IOException ex) {
            Logger.getLogger(MyPlayerV1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void pausePlaying() {
        paused = true;
    }

    public void resumePlaying() {
        synchronized (lock) {
            paused = false;
            lock.notifyAll();
        }
    }
    
    public void stopPlaying(){
            playClick = false;
            if(line!=null){
            line.drain();
            line.stop();}
    }
    
    public void adjustVolume(float volume){
       FloatControl control = (FloatControl) line.getControl(FloatControl.Type.MASTER_GAIN);
       control.setValue(volume);
    }

}
