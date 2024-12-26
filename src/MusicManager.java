import javax.sound.sampled.*;
import java.io.File;

public class MusicManager {
    private static Clip currentClip;
    private static Clip barkClip;
    private static Clip meowClip;
    private static Clip gameoverClip;

    public static void playMusic(String musicFilePath) {
        try {
            if (currentClip != null && currentClip.isRunning()) {
                currentClip.stop();
            }
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File(musicFilePath));
            currentClip = AudioSystem.getClip();
            currentClip.open(audioStream);
            currentClip.loop(Clip.LOOP_CONTINUOUSLY);
            currentClip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void stopMusic() {
        try {
            if (currentClip != null && currentClip.isRunning()) {
                currentClip.stop();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void playBark() {
        playSound("resources/bark.wav", barkClip);
    }

    public static void playMeow() {
        playSound("resources/meow.wav", meowClip);
    }

    public static void playGameOver() {
        playSound("resources/gameover.wav", gameoverClip);
    }

    private static void playSound(String soundFilePath, Clip soundClip) {
        try {
            if (soundClip != null && soundClip.isRunning()) {
                soundClip.stop();
            }
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File(soundFilePath));
            soundClip = AudioSystem.getClip();
            soundClip.open(audioStream);
            soundClip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void stopAllSounds() {
        try {
            if (barkClip != null && barkClip.isRunning()) {
                barkClip.stop();
            }
            if (meowClip != null && meowClip.isRunning()) {
                meowClip.stop();
            }
            if (gameoverClip != null && gameoverClip.isRunning()) {
                gameoverClip.stop();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
