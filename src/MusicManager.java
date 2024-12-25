import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;public class MusicManager {
    private static Clip currentClip;
    private static Clip barkClip;
    private static Clip meowClip;
    private static Clip gameoverClip;

    // Müzik çalmayı başlatma
    public static void playMusic(String musicFilePath) {
        try {
            // Önceki müzik varsa durdur
            if (currentClip != null && currentClip.isRunning()) {
                currentClip.stop();
            }

            // Yeni müziği çalmaya başla
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File(musicFilePath));
            currentClip = AudioSystem.getClip();
            currentClip.open(audioStream);
            currentClip.loop(Clip.LOOP_CONTINUOUSLY); // Müzik sürekli döngüde çalsın
            currentClip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Bark sesini çalma
    public static void playBark() {
        try {
            if (barkClip != null && barkClip.isRunning()) {
                barkClip.stop();
            }
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File("resources/bark.wav"));
            barkClip = AudioSystem.getClip();
            barkClip.open(audioStream);
            barkClip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Meow sesini çalma
    public static void playMeow() {
        try {
            if (meowClip != null && meowClip.isRunning()) {
                meowClip.stop();
            }
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File("resources/meow.wav"));
            meowClip = AudioSystem.getClip();
            meowClip.open(audioStream);
            meowClip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // GameOver sesini çalma
    public static void playGameOver() {
        try {
            if (gameoverClip != null && gameoverClip.isRunning()) {
                gameoverClip.stop();
            }
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File("resources/gameover.wav"));
            gameoverClip = AudioSystem.getClip();
            gameoverClip.open(audioStream);
            gameoverClip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Müzik durdurma
    public static void stopMusic() {
        try {
            if (currentClip != null && currentClip.isRunning()) {
                currentClip.stop();
            }
            // Diğer sesleri de durdur
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
