package org.domi.utils;

import org.domi.frame.Frames;

import java.awt.*;

public class Cycle {

    public double offsetX;
    public double offsetY;

    public double freq;
    public double amp;
    public double phase;

    public double rotation;

    public Cycle child;

    public Cycle(double offsetX, double offsetY, double freq, double amp, double phase, double rotation) {
        this.offsetX = offsetX;
        this.offsetY = offsetY;

        this.freq = freq;
        this.amp = amp;
        this.phase = phase;

        this.rotation = rotation;
    }

    public Cycle(double freq, double amp, double phase, double rotation) {
        this.freq = freq;
        this.amp = amp;
        this.phase = phase;

        this.rotation = rotation;
    }

    public void addCycle(Cycle cycle) {
        this.child = cycle;
    }

    public void drawCycle(Graphics2D g2D, double xVal) {
        Cycle cycle = this;
        double posX = offsetX;
        double posY = offsetY;
        do {
            g2D.setColor(Color.WHITE);
            double radius = cycle.amp * 200; // 주기 변경
            g2D.setColor(new Color(255, 255, 255, 100));
            g2D.drawOval((int) posX - (int) radius, (int) posY - (int) radius, (int) radius * 2, (int) radius * 2);
            double angle = (xVal * cycle.freq) + cycle.phase + cycle.rotation;
            double x = radius * Math.cos(angle);
            double y = radius * Math.sin(angle);
            g2D.setColor(Color.WHITE);
            g2D.drawLine((int) posX, (int) posY, (int) (x + posX), (int) (y + posY));

            posX += x;
            posY += y;
        } while ((cycle = cycle.child) != null);

        if (rotation % Math.PI == 0) {
            Frames.valuesX.add(posX); // X축의 위치
            Frames.cycleXpos = posX;
            if (Frames.valuesX.size() > Frames.cyclesX.size()) {
                Frames.valuesX.remove(0);
            }
        } else {
            Frames.valuesY.add(posY); // Y축의 위치
            Frames.cycleYpos = posY;
            if (Frames.valuesY.size() > Frames.cyclesY.size()) {
                Frames.valuesY.remove(0);
            }
        }
    }
}