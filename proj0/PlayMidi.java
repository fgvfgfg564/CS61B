import java.io.*;
import javax.sound.midi.*;


public class PlayMidi implements Runnable
{
    File sound;
    Sequence seq;
    Sequencer midi;
    Thread runner;
    public PlayMidi(String f)
    {
        try
        {
            sound = new File(f);
            seq = MidiSystem.getSequence(sound);
            midi = MidiSystem.getSequencer();
            midi.open();
            midi.setSequence(seq);
        }
        catch (Exception ex)
        {
        }
    }

    public void run()
    {
        try
        {
            while(true)
            {
                if(!midi.isRunning())midi.start();
                Thread.sleep(1000);
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
    public void playMidi()
    {
        try
        {
            //midi.start();
            if(runner == null)
            {
                runner = new Thread(this);
                runner.start();
            }
        }
        catch (Exception ex)
        {
        }
    }
    public void stopMidi()
    {
        try
        {
            runner.interrupt();
            runner = null;
            midi.stop();
        }
        catch (Exception ex)
        {
        }
    }
}