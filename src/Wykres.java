/**
 * Created by Pati
 */
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Wykres extends JPanel {

    float x1, x2, x3, x4, y1, y2, y3, y4, a1, a2, b1, b2, xPrzeciecia,
            yPrzeciecia, dx1, dx2, dy1, dy2, dx3, dx4, dy3, dy4;

    public Wykres(float x1, float x2, float x3, float x4, float y1, float y2,
                  float y3, float y4) {
        this.x1 = x1;
        this.x2 = x2;
        this.x3 = x3;
        this.x4 = x4;
        this.y1 = y1;
        this.y2 = y2;
        this.y3 = y3;
        this.y4 = y4;

        setLayout(new BorderLayout());
        JFreeChart chart = generateChart();

    }

    public void wyznaczRownowage() {

        a1 = (y1 - y2) / (x1 - x2);

        a2 = (y4 - y3) / (x4 - x3);

        b1 = y1 - a1 * x1;
        b2 = y3 - a2 * x3;

        yPrzeciecia = ((a1 * b2) - (a2 * b1)) / (a1 - a2);
        xPrzeciecia = (b2 - b1) / (a1 - a2);

        dx1 = xPrzeciecia + 5;
        dy1 = a1 * dx1 + b1;
        dx2 = xPrzeciecia - 5;
        dy2 = a1 * dx2 + b1;

        dx3 = xPrzeciecia + 5;
        dy3 = a2 * dx3 + b2;
        dx4 = xPrzeciecia - 5;
        dy4 = a2 * dx4 + b2;

    }

    public JFreeChart generateChart() {

        XYSeriesCollection dataset = new XYSeriesCollection();
        XYSeries podaŅ = new XYSeries("Linia podaŅy");
        podaŅ.add(x1, y1);
        podaŅ.add(x2, y2);
        XYSeries popyt = new XYSeries("Linia popytu");
        popyt.add(x3, y3);
        popyt.add(x4, y4);

        wyznaczRownowage();

        podaŅ.add(xPrzeciecia, yPrzeciecia);
        popyt.add(xPrzeciecia, yPrzeciecia);
        podaŅ.add(dx1, dy1);
        podaŅ.add(dx2, dy2);
        popyt.add(dx3, dy3);
        popyt.add(dx4, dy4);

        XYSeries punkt = new XYSeries("Růwnowaga rynkowa");
        punkt.add(xPrzeciecia, yPrzeciecia);

        dataset.addSeries(podaŅ);
        dataset.addSeries(punkt);
        dataset.addSeries(popyt);


        JFreeChart chart = ChartFactory.createXYLineChart(
                "Punkt růwnowagi rynkowej", "Iloúś", "Cena", dataset,
                PlotOrientation.VERTICAL, true, // includelegend
                true, // tooltips
                false // urls
        );
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.getBounds();
        add(chartPanel, BorderLayout.CENTER);

        XYPlot plot = (XYPlot) chart.getPlot();
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesLinesVisible(0, true);
        renderer.setSeriesShapesVisible(0, false);
        renderer.setSeriesLinesVisible(1, false);
        renderer.setSeriesShapesVisible(1, true);
        renderer.setSeriesLinesVisible(2, true);
        renderer.setSeriesShapesVisible(2, false);

        plot.setRenderer(renderer);
        final ChartPanel chartPane = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 300));

        return chart;
    }

}

