package ai.computer.impl;

import ai.Zet;
import ai.computer.ObservableAI;
import ai.computer.api.Computer;
import ai.heuristic.api.HeuristicCalculator;
import ai.heuristic.impl.CompleteHeuristicCalculator;
import model.Bord;
import model.Kleur;

/**
 * Created by JoachimDs on 13/10/2015.
 * <p>
 * Computer is zwart en speelt als tweede.
 * Max heeft als kleur zwart
 */
public class NewMiniMaxComputer  extends ObservableAI implements Computer {
    private Zet ultiemeZet;
    private int aantalStappen;
    private HeuristicCalculator calculator;
    private Kleur computerKleur;

    public NewMiniMaxComputer(Kleur kleur) {
        calculator = new CompleteHeuristicCalculator();
        computerKleur = kleur;
        aantalStappen = 3;
    }

    @Override
    public Zet berekenZet(Bord bord) {
        ultiemeZet = new Zet(9, 9);
        ultiemeZet.setWaarde(Double.NEGATIVE_INFINITY);
        miniMax(bord, Kleur.ZWART, aantalStappen);
        return ultiemeZet;
    }

    @Override
    public void setAantalStappen(int aantalStappen) {
        this.aantalStappen = aantalStappen;
    }

    @Override
    public int getAantalStappen() {
        return aantalStappen;
    }

    @Override
    public int getDuur() {
        return 0;
    }

    @Override
    public int getProgress() {
        return 0;
    }

    private double miniMax(Bord bord, Kleur kleurAanZet, int aantalStappen) {
        double kindWaarde;

        if (aantalStappen == 0 || bord.geefGeldigeZetten(kleurAanZet).size() == 0) {
            kindWaarde = calculator.getHeuristicValue(bord, computerKleur);
        } else if (kleurAanZet == computerKleur) {
            kindWaarde = Double.NEGATIVE_INFINITY;
            for (Zet zet : bord.geefGeldigeZetten(kleurAanZet)){
                Bord duplicaat = new Bord(bord);
                duplicaat.zetPion(zet, kleurAanZet);
                double kleinKindWaarde = miniMax(duplicaat, Kleur.andereKleur(kleurAanZet), aantalStappen -1);
                kindWaarde = Math.max(kleinKindWaarde, kindWaarde);

                if (aantalStappen == this.aantalStappen){
                    if (ultiemeZet.getWaarde() < kleinKindWaarde) {
                        ultiemeZet = zet;
                        ultiemeZet.setWaarde(kleinKindWaarde);
                    }
                    //iets voor prograssbar
                }
                duplicaat.setUserObject(" " + Math.round(kleinKindWaarde) + zet);
                bord.add(duplicaat);
            }
        } else {
            kindWaarde = Double.POSITIVE_INFINITY;
            for (Zet zet : bord.geefGeldigeZetten(kleurAanZet)){
                Bord duplicaat = new Bord(bord);
                duplicaat.zetPion(zet, kleurAanZet);
                double kleinKindWaarde = miniMax(duplicaat, Kleur.andereKleur(kleurAanZet), aantalStappen -1);
                kindWaarde = Math.min(kleinKindWaarde, kindWaarde);

                duplicaat.setUserObject(" " + Math.round(kleinKindWaarde) + zet);
                bord.add(duplicaat);
            }
        }

        return kindWaarde;
    }

}
