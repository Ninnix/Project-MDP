import java.util.concurrent.LinkedBlockingQueue;


/**
 * Created by nicolo on 29/04/17.
 */
public class Test {
    public static void main(String[] args) throws InvalidPopulationException {
        Popolazione p1 = new Popolazione(15, 20 ,3,45, 45, 45, 45);
        p1.start();
    }
}
