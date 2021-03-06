package view.swing;

import ai.computer.api.Computer;
import model.Spel;

import javax.swing.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by jorandeboever
 * on 10/10/15.
 */
public class ComputerWorker extends SwingWorker implements Observer {
    private Spel spel;
    private JProgressBar computerProgressBar;
    private Computer computer;

    public ComputerWorker(Spel spel, JProgressBar computerProgressBar, Computer computer) {
        this.spel = spel;
        this.computerProgressBar = computerProgressBar;
        computerProgressBar.setValue(0);
        this.computer = computer;
        Observable observable =  computer;
        observable.addObserver(this);
    }

    @Override
    protected Object doInBackground() throws Exception {
        spel.getBord().removeAllChildren();
        spel.zetPion(computer.berekenZet(spel.getBord()));

        return 1;
    }

    @Override
    public void update(Observable o, Object arg) {
        computerProgressBar.setMaximum(computer.getDuur());
        computerProgressBar.setValue(computer.getProgress());
        computerProgressBar.repaint();

    }


}
