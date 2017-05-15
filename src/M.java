
/**
 * Created by nicolo on 28/04/17.
 */
public class M extends Persona {
    /**
     * uomini morigerati, sono disposti a corteggiare la donna amata e
     * contribuiscono al pari di lei a crescere la prole;
     */

    public Popolazione popo;

    protected volatile int limiteMor=2;

    public M(Popolazione p) {
        //costruttore dei morigerati
        super();
        this.popo = p;
    }

    @Override
    public tipo getType(){
        return tipo.M ;
    }

    @Override
    public synchronized void run() {
        try {
        while (!isInterrupted() && limiteMor>0) {
            this.corteggiamento(); //morigerato va alla ricerca di una donna al mercato
            this.wait();
        }
        } catch (InterruptedException e) {
            System.out.println("problema con l accoppiamento del morigerato");
        }


        this.popo.morigerati.remove(this);
    }


    protected synchronized void sveglia(){this.notify();} //metodo che fa risvegliare il morigerato che e' stato selezionato per l'accoppiamento

    private void corteggiamento(){
        //corteggiamento del morigerato
        popo.ristorante.insert(this); //il morigerato si aggiunge alla coda per accoppiarsi
    }

    // il morigerato non necessita di un metodo accoppiamento perche' tale metodo e' gestito dalla moglie
}
