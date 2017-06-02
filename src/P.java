import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by nicolo on 28/04/17.
 */
public class P extends Persona {
    /**
     * donne prudenti, accettano un compagno con cui fare un figlio solo
     * dopo un congruo periodo di corteggiamento;
     */

    static AtomicInteger count=new AtomicInteger(0);

    public Popolazione popo;

    //probabilita' di avere un figlio
    protected double fertilita= 0.95 ;

    public P(Popolazione p) {

        //costruttore delle prudenti
        super();
        this.popo = p;
    }

    @Override
    public tipo getType(){
        return tipo.P ;
    }

    @Override
    public void run() {
        try {
            if(isInterrupted()){
                this.popo.prudenti.remove(this);
                return;
            }
            M marito = corteggiamento();
            if(marito !=null) {
                while (fertilita > 0.0) { //la coppia si riproduce finchè la moglie è fertile
                    accoppiamento(marito);
                    sleep(50);
                }
                //la prudente e il marito morigerato muoiono insieme dopo aver cresciuto i propri figli
                marito.sveglia();

            }
        }catch(InterruptedException e){
            //System.out.println("problema prudente, interruzione coda");
        }
        // ... e muore lei
        this.popo.prudenti.remove(this);
    }

    public M corteggiamento() throws InterruptedException{
        //corteggiamento della prudente
        Persona spasimante;
        int tentativi=4;
        while(tentativi>=0) {
            spasimante = popo.ballo.exctract();
            if (spasimante.getType() == tipo.A) {
                ((A)spasimante).ultimaDonna= tipo.P;
                ((A) spasimante).sveglia();
                tentativi--;
            } else {
                return (M) spasimante;
            }
        }
        return null;
    }



    public void accoppiamento(M m) {
        // si stabilisce se la donna prudente concepira' un nuovo figlio
        double random = new Random().nextDouble();
        if (random >= fertilita) {   //significa che la donna non concepira' bambini da qui in avanti
            fertilita = 0.0;
            return;
        }

        //a questo punto sara' generato un figlio
        Persona figlio = ((new Random().nextDouble() < 0.5) ? new M(this.popo) : new P(this.popo)); // scelta del sesso del nascituro
        if (figlio.getType() == tipo.P) {
            figlio.setName("P"+P.count.incrementAndGet());
            popo.prudenti.add((P) figlio); //aggiunge il figlio alla popolazione
        }
        else {
            figlio.setName("M"+M.count.incrementAndGet());
            popo.morigerati.add((M) figlio);
        }
        //System.out.println(this.getName()+" si accoppia con "+m.getName()+", e nasce "+figlio.getName());  //da rimuovere
        figlio.start();   // nasce il figlio
        this.contentezza += (popo.a - popo.b / 2 - popo.c);  // aggiorniamo il valore di contentezza della prudente
        m.contentezza += (popo.a - popo.b / 2 - popo.c);  // aggiorniamo il valore di contentezza del morigerato
        fertilita -= 0.21; // aggiorniamo la probabilita' che la prudente abbia un altro figlio
    }
}