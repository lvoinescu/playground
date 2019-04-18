package org.sam.playground.platefill.gui;


import org.sam.playground.platefill.Cook;
import org.sam.playground.platefill.FoodSolver;
import org.sam.playground.platefill.Plate;
import org.sam.playground.platefill.Sandwich;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class FoodForm extends JPanel implements ActionListener {
    private SimulationPanel simulationPanel;
    private final FoodSolver foodSolver;


    private FoodForm() {
        createUIComponents();
        Plate plate = new Plate(60, 30);
        Cook cook = new Cook(5);
        List<Sandwich> sandwiches = cook.createSandwiches(15000);

        System.out.println("Total surface is:" + sandwiches.stream()
                .map(s -> s.getHeight() * s.getHeight())
                .reduce(0, (a, b) -> a + b));
        this.foodSolver = new FoodSolver(plate, sandwiches, simulationPanel);
    }

    public static void main(String[] args) {
        JFrame jFrame = new JFrame("Food problem");
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        FoodForm foodForm = new FoodForm();
        JPanel machinePanel = foodForm.simulationPanel;
        jFrame.getContentPane().add(machinePanel);

        jFrame.pack();
        jFrame.setSize(1300, 800);
        jFrame.setVisible(true);

        foodForm.process();
    }

    private void process() {
        foodSolver.solve();
    }

    public void createUIComponents() {
        simulationPanel = new SimulationPanel();
    }

    @Override
    public void actionPerformed(ActionEvent e) {


    }
}
