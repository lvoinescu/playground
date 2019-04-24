package org.sam.playground.platefill.gui;


import org.sam.playground.platefill.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

public class FoodForm extends JPanel implements ActionListener {
    private SimulationPanel simulationPanel;
    private final FoodSolver foodSolver;


    private FoodForm() {
        createUIComponents();
        Plate plate = new Plate(12, 3);
        Cook cook = new Cook(1, 5);
//        List<Sandwich> sandwiches = cook.createSandwiches(100);
        List<Sandwich> sandwiches = Arrays.asList(
                new Sandwich(2,1, SandwichType.BEEF, 1),
                new Sandwich(3,2, SandwichType.BEEF, 2),
                new Sandwich(3,4, SandwichType.BEEF, 3),
                new Sandwich(2,3, SandwichType.BEEF, 4),
                new Sandwich(5,2, SandwichType.BEEF, 5),
                new Sandwich(3,2, SandwichType.BEEF, 6),
                new Sandwich(3,4, SandwichType.BEEF, 7),
                new Sandwich(4,1, SandwichType.BEEF, 8),
                new Sandwich(2,1, SandwichType.BEEF, 9),
                new Sandwich(3,2, SandwichType.BEEF, 10),
                new Sandwich(3,4, SandwichType.BEEF, 11),
                new Sandwich(1,4, SandwichType.BEEF, 12),
                new Sandwich(4,4, SandwichType.BEEF, 13),
                new Sandwich(2,3, SandwichType.BEEF, 14),
                new Sandwich(1,2, SandwichType.BEEF, 15),
                new Sandwich(4,4, SandwichType.BEEF, 16),
                new Sandwich(2,4, SandwichType.BEEF, 17),
                new Sandwich(5,5, SandwichType.BEEF, 18)
        );

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
