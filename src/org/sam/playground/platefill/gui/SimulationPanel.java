package org.sam.playground.platefill.gui;


import org.sam.playground.platefill.Slice;
import org.sam.playground.platefill.SolvingListener;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SimulationPanel extends JPanel implements SolvingListener {

    private List<Slice> slices;

    SimulationPanel() {
        super(new GridBagLayout());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int step = 20;

        Color backColor = new Color(255, 255, 255);
        g.setColor(backColor); //use g2 instead
        g.fillRect(0, 0, 1400, 600);
        if (slices != null) {
            synchronized (slices) {
                for (int i = 0; i < slices.size(); i++) {
                    Slice slice = slices.get(i);

                    float red = ((float) i / slices.size());
                    float green = Math.abs(1 - ((float) i / slices.size()));
                    float blue = Math.abs((float) (i - 0.6) / slices.size());

                    Rectangle rectangle = new Rectangle(slice.getColumn() * step, slice.getRow() * step,
                            slice.getColumns() * step,
                            slice.getRows() * step);

                    try {
                        Color color = new Color(red, green, blue);
                        g.setColor(color);
                        g.fillRect((int) rectangle.getMinX(), (int) rectangle.getMinY(),
                                (int) rectangle.getWidth(), (int) rectangle.getHeight());

                        Color c1 = new Color(0,0,0);
                        g.setColor(c1);
                        g.drawRect((int) rectangle.getMinX(), (int) rectangle.getMinY(),
                                (int) rectangle.getWidth(), (int) rectangle.getHeight());


                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
            }
        }
        g.dispose();
    }

    @Override
    public void stateChanged(List<Slice> slices) {

    }

    @Override
    public void problemSolved(List<Slice> slices) {
        this.slices = slices;
        repaint();
    }


}
