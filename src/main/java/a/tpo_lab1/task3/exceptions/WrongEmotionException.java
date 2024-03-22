package a.tpo_lab1.task3.exceptions;

public class WrongEmotionException extends Exception {
    public WrongEmotionException() {
        super("Испытвает не эту эмоцию.");
    }
}
