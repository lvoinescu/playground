package org.sam.playground.platefill.gui;


import org.sam.playground.platefill.Cook;
import org.sam.playground.platefill.FoodSolverService;
import org.sam.playground.platefill.Plate;
import org.sam.playground.platefill.Sandwich;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class FoodForm extends JPanel implements ActionListener {
    private SimulationPanel simulationPanel;
    private final FoodSolverService solver;
    private final Plate plate;
    private final List<Sandwich> sandwiches;


    private FoodForm() {
        createUIComponents();
        plate = new Plate(50, 20);
        Cook cook = new Cook(2, 4);
        sandwiches = cook.createSandwiches(200);
//        sandwiches = Arrays.asList(
//                new Sandwich(1, 3, SandwichType.BEEF, 1),
//                new Sandwich(3, 3, SandwichType.BEEF, 2),
//                new Sandwich(3, 3, SandwichType.BEEF, 3),
//                new Sandwich(3, 3, SandwichType.BEEF, 4),
//                new Sandwich(3, 3, SandwichType.BEEF, 5),
//                new Sandwich(3, 3, SandwichType.BEEF, 6),
//                new Sandwich(3, 3, SandwichType.BEEF, 7),
//                new Sandwich(3, 3, SandwichType.BEEF, 8),
//                new Sandwich(3, 3, SandwichType.BEEF, 9),
//                new Sandwich(3, 3, SandwichType.BEEF, 10)
//        );

        System.out.println("Total surface is:" + sandwiches.stream()
                .map(s -> s.getHeight() * s.getHeight())
                .reduce(0, (a, b) -> a + b));
        solver = new FoodSolverService(simulationPanel);
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
        solver.solve(plate, sandwiches);
    }

    public void createUIComponents() {
        simulationPanel = new SimulationPanel();
    }

    @Override
    public void actionPerformed(ActionEvent e) {


    }
}
