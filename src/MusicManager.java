import javax.sound.sampled.*;
import java.io.File;

public class MusicManager {
    private static Clip currentClip;
    private static Clip barkClip;
    private static Clip meowClip;
    private static Clip gameoverClip;
    private static Clip catdogfightClip;

    public static void playGameMusic() {
        playMusic("resources/gameMusic.wav");
    }

    public static void playDefaultMusic() {
        playMusic("resources/music.wav");
    }

    public static void playMusic(String musicFilePath) {
        try {
            // Stop the previous music if needed
            if (currentClip != null) {
                stopMusic();
            }

            AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File(musicFilePath));
            currentClip = AudioSystem.getClip();
            currentClip.open(audioStream);
            currentClip.loop(Clip.LOOP_CONTINUOUSLY); // Loop indefinitely
            currentClip.start();
        } catch (Exception e) {
            System.err.println("Error playing music: " + musicFilePath);
            e.printStackTrace();
        }
    }

    public static void stopMusic() {
        try {
            if (currentClip != null && currentClip.isRunning()) {
                currentClip.stop();
                currentClip.close();
                currentClip = null; // Reset the clip
            }
        } catch (Exception e) {
            System.err.println("Error stopping music.");
            e.printStackTrace();
        }
    }


    public static void playBark() {
        barkClip = playSound("resources/bark.wav", barkClip);
    }

    public static void playMeow() {
        meowClip = playSound("resources/meow.wav", meowClip);
    }

    public static void playGameOver() {
        gameoverClip = playSound("resources/gameover.wav", gameoverClip);
    }

    public static void playCatDogFight() {
        catdogfightClip = playSound("resources/catdogfight.wav", catdogfightClip);
    }

    private static Clip playSound(String soundFilePath, Clip existingClip) {
        try {
            if (existingClip != null && existingClip.isRunning()) {
                existingClip.stop();
                existingClip.close();
            }

            AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File(soundFilePath));
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
            return clip;
        } catch (Exception e) {
            System.err.println("Error playing sound: " + soundFilePath);
            e.printStackTrace();
            return null;
        }
    }

    public static void stopAllSounds() {
        try {
            if (barkClip != null && barkClip.isRunning()) {
                barkClip.stop();
                barkClip.close();
            }

            if (meowClip != null && meowClip.isRunning()) {
                meowClip.stop();
                meowClip.close();
            }

            if (gameoverClip != null && gameoverClip.isRunning()) {
                gameoverClip.stop();
                gameoverClip.close();
            }

            if (catdogfightClip != null && catdogfightClip.isRunning()) {
                catdogfightClip.stop();
                catdogfightClip.close();
            }

        } catch (Exception e) {
            System.err.println("Error stopping all sounds.");
            e.printStackTrace();
        }
    }
}
