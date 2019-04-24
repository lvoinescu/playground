package org.sam.playground.platefill.gui;


import org.sam.playground.platefill.Slice;
import org.sam.playground.platefill.SolvingListener;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SimulationPanel extends JPanel implements SolvingListener {

    private List<Slice> partialSolution;
    private List<Slice> slices;

    SimulationPanel() {
        super(new GridBagLayout());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int step = 20;


        if (partialSolution != null) {
            synchronized (partialSolution) {
                for (int i = 0; i < partialSolution.size(); i++) {
                    Slice slice = partialSolution.get(i);

                    float red = ((float) slice.getNumber() / slices.size());
                    float green = Math.abs(1 - ((float) slice.getNumber() / slices.size()));
                    float blue = Math.abs((float) (slice.getNumber() - 0.6) / slices.size());

                    Rectangle rectangle = new Rectangle(slice.getColumn() * step, slice.getRow() * step,
                            slice.getColumns() * step,
                            slice.getRows() * step);

                    Color color = new Color(red, green, blue);
                    g.setColor(color);
                    g.fillRect((int) rectangle.getMinX(), (int) rectangle.getMinY(),
                            (int) rectangle.getWidth(), (int) rectangle.getHeight());

                    Color c1 = new Color(0, 0, 0);
                    g.setColor(c1);
                    g.drawRect((int) rectangle.getMinX(), (int) rectangle.getMinY(),
                            (int) rectangle.getWidth(), (int) rectangle.getHeight());

                    g.setFont(new Font("Arial", 1, 9));
                    g.drawString(String.valueOf(slice.getNumber()) + "(" + slice.getRows() + "," + slice.getColumns() + ")",
                            (int) rectangle.getMinX(), (int) rectangle.getMinY() + 10);

                }
            }
        }
        Color backColor = new Color(100, 200, 140);
        g.setColor(backColor); //use g2 instead
        g.drawRect(0, 0, 1400, 600);
        g.dispose();
    }

    @Override
    public void stateChanged(List<Slice>slices,  List<Slice> partialSolution) {
        this.partialSolution = partialSolution;
        this.slices = slices;
        repaint();
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void problemSolved(List<Slice> slices) {
        this.partialSolution = slices;
        repaint();
    }
}
