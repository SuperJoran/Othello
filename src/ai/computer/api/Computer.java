package ai.computer.api;

import ai.Zet;
import ai.computer.ObservableAI;
import ai.computer.impl.*;
import ai.heuristic.api.HeuristicCalculator;
import ai.heuristic.impl.CompleteHeuristicCalculator;
import model.Bord;
import model.Kleur;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by jorandeboever
 * on 7/10/15.
 */
public abstract class Computer extends ObservableAI {
    protected HeuristicCalculator heuristicCalculator;
    protected Kleur computerKleur;
    protected int aantalStappen;

    public Computer(Kleur computerKleur) {
        this.computerKleur = computerKleur;
        this.heuristicCalculator = new CompleteHeuristicCalculator();
        this.aantalStappen = 3;
    }

    public Computer(Computer computer) {
        this.computerKleur = computer.getKleur();
        this.aantalStappen = computer.getAantalStappen();
        this.heuristicCalculator = computer.getHeuristicCalculator();
    }

    public abstract Zet berekenZet(Bord bord);


    public HeuristicCalculator getHeuristicCalculator() {
        return heuristicCalculator;
    }

    public void setHeuristicCalculator(HeuristicCalculator heuristicCalculator) {
        this.heuristicCalculator = heuristicCalculator;
    }

    public Kleur getKleur() {
        return computerKleur;
    }

    public void setKleur(Kleur computerKleur) {
        this.computerKleur = computerKleur;
    }

    public int getAantalStappen() {
        return aantalStappen;
    }

    public void setAantalStappen(int aantalStappen) {
        this.aantalStappen = aantalStappen;
    }

    public static List<Computer> geefAlleComputers() {
        List<Computer> computers = new ArrayList<>();
        computers.add(new HeuristicComputer(Kleur.ZWART));
        computers.add(new MiniMaxComputerJoran(Kleur.ZWART));
        computers.add(new MiniMaxAlphaBetaComputerJoran(Kleur.ZWART));
        computers.add(new MiniMaxComputerJoachim(Kleur.ZWART));
        computers.add(new MiniMaxAlphaBetaComputerJoachim(Kleur.ZWART));

        return computers;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
