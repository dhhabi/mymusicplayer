package org.mymusicplayer;

import java.io.File;
import java.io.PrintStream;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javazoom.jlgui.basicplayer.BasicController;
import javazoom.jlgui.basicplayer.BasicPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerEvent;
import javazoom.jlgui.basicplayer.BasicPlayerException;
import javazoom.jlgui.basicplayer.BasicPlayerListener;

/**
 * This class implements a simple player based on BasicPlayer. BasicPlayer is a
 * threaded class providing most features of a music player. BasicPlayer works
 * with underlying JavaSound SPIs to support multiple audio formats. Basically
 * JavaSound supports WAV, AU, AIFF audio formats. Add MP3 SPI and Vorbis SPI in
 * your CLASSPATH to play MP3 and Ogg Vorbis file.
 */
public class MusicPlayerV2 implements BasicPlayerListener {
        //private PrintStream out = null;

    BasicPlayer player = new BasicPlayer();
    // BasicPlayer is a BasicController.
    BasicController control = (BasicController) player;
    
    private String currentSong;
    
    public BasicController getControl(){
        return this.control;
    }

    /**
     * Entry point.
     *
     * @param filename
     */
    public void play(String filename) {
       // Instantiate BasicPlayer.
        currentSong = filename;
      // Register BasicPlayerTest to BasicPlayerListener events.
        // It means that this object will be notified on BasicPlayer
        // events such as : opened(...), progress(...), stateUpdated(...)
        player.addBasicPlayerListener(this);

        try {
            // Open file, or URL or Stream (shoutcast, icecast) to play.
            control.open(new File(filename));

      // control.open(new URL("http://yourshoutcastserver.com:8000"));
            // Start playback in a thread.
            control.play();

      // If you want to pause/resume/pause the played file then
            // write a Swing player and just call control.pause(),
            // control.resume() or control.stop(). 
            // Use control.seek(bytesToSkip) to seek file
            // (i.e. fast forward and rewind). seek feature will
            // work only if underlying JavaSound SPI implements
            // skip(...). True for MP3SPI and SUN SPI's
            // (WAVE, AU, AIFF).
            // Set Volume (0 to 1.0).
            control.setGain(0.85);
            // Set Pan (-1.0 to 1.0).
            control.setPan(0.0);
        } catch (BasicPlayerException e) {
            System.out.println(e.toString());
        }
    }

    /**
     * Open callback, stream is ready to play.
     *
     * properties map includes audio format dependant features such as bitrate,
     * duration, frequency, channels, number of frames, vbr flag, ...
     *
     * @param stream could be File, URL or InputStream
     * @param properties audio stream properties.
     */
    @Override
    public void opened(Object stream, Map properties) {
    // Pay attention to properties. It's useful to get duration, 
        // bitrate, channels, even tag such as ID3v2.
        //display("opened : "+properties.toString()); 
    }

    /**
     * Progress callback while playing.
     *
     * This method is called severals time per seconds while playing. properties
     * map includes audio format features such as instant bitrate, microseconds
     * position, current frame number, ...
     *
     * @param bytesread from encoded stream.
     * @param microseconds elapsed (<b>reseted after a seek !</b>).
     * @param pcmdata PCM samples.
     * @param properties audio stream parameters.
     */
    @Override
    public void progress(int bytesread, long microseconds, byte[] pcmdata, Map properties) {
    // Pay attention to properties. It depends on underlying JavaSound SPI
        // MP3SPI provides mp3.equalizer.
        //display("progress : "+properties.toString());
    }

    /**
     * Notification callback for basicplayer events such as opened, eom ...
     *
     * @param event
     */
    @Override
    public void stateUpdated(BasicPlayerEvent event) {
    // Notification of BasicPlayer states (opened, playing, end of media, ...)
        //display("stateUpdated : "+event.toString());
    }

    /**
     * A handle to the BasicPlayer, plugins may control the player through the
     * controller (play, stop, ...)
     *
     * @param controller : a handle to the player
     */
    @Override
    public void setController(BasicController controller) {
        //display("setController : "+controller);
    }

    public void stop() {
        try {
            control.stop();
        } catch (BasicPlayerException ex) {
            Logger.getLogger(MusicPlayerV2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void pause(){
        try {
            control.pause();
        } catch (BasicPlayerException ex) {
            Logger.getLogger(MusicPlayerV2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setVolume(double volume){
        try {
            control.setGain(volume);
        } catch (BasicPlayerException ex) {
            Logger.getLogger(MusicPlayerV2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void resume(){
        try {
            control.resume();
        } catch (BasicPlayerException ex) {
            Logger.getLogger(MusicPlayerV2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

}
