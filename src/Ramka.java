/**
 * Created by Pati
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Ramka extends JFrame implements ActionListener {
    private float x1, x2, x3, x4, y1, y2, y3, y4, a1, a2, b1, b2;
    private JPanel panel1;
    private JButton przycisk2;
    private JTextField t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14;
    private JTextArea punktRownowagi;
    private JLabel l1, l2;
    private Wykres wykres;
    private JTabbedPane tabPane;

    public Ramka() {

        setTitle("Projekt");
        setSize(400, 400);

        setResizable(true);
        setLocationRelativeTo(null);
        wypelnij();

    }



    public void wypelnij() {

        panel1 = new JPanel();
        panel1.removeAll();
        panel1.setBackground(Color.white);
        GridLayout g = new GridLayout(17, 2);
        panel1.setLayout(g);

        l1 = new JLabel("WPROWADZANIE DANYCH");
        l1.setHorizontalAlignment(NORMAL);
        panel1.add(l1);

        l2 = new JLabel("Punkty dla podaŅy: ");
        panel1.add(l2);

        t3 = new JTextField("Punkt 1 (x, y)");
        t3.setEditable(false);
        panel1.add(t3);
        t5 = new JTextField();
        t5.setEditable(true);
        panel1.add(t5);
        t6 = new JTextField();
        t6.setEditable(true);
        panel1.add(t6);

        t4 = new JTextField("Punkt 2 (x, y)");
        t4.setEditable(false);
        panel1.add(t4);
        t7 = new JTextField();
        t7.setEditable(true);
        panel1.add(t7);
        t8 = new JTextField();
        t8.setEditable(true);
        panel1.add(t8);

        l1 = new JLabel("Punkty dla popytu");
        panel1.add(l1);

        t9 = new JTextField("Punkt 1 (x, y)");
        t9.setEditable(false);
        panel1.add(t9);
        t11 = new JTextField();
        t11.setEditable(true);
        panel1.add(t11);
        t12 = new JTextField();
        t12.setEditable(true);
        panel1.add(t12);

        t10 = new JTextField("Punkt 2 (x, y)");
        t10.setEditable(false);
        panel1.add(t10);
        t13 = new JTextField();
        t13.setEditable(true);
        panel1.add(t13);

        t14 = new JTextField();
        t14.setEditable(true);
        panel1.add(t14);

        przycisk2 = new JButton("WYZNACZ CEN  R”WNOWAGI RYNKOWEJ");
        panel1.add(przycisk2);
        przycisk2.addActionListener(this);
        punktRownowagi = new JTextArea();
        panel1.add(punktRownowagi);
        this.add(panel1);

        tabPane = new JTabbedPane();
        tabPane.addTab("Wprowadzanie danych", panel1);

        add(tabPane, BorderLayout.CENTER);

    }

    public void uzupelnij() {

        wykres = new Wykres(x1, x2, x3, x4, y1, y2, y3, y4);
        wykres.generateChart();
        tabPane.add("Wykres", wykres);

    }

    public void pobierzDane() {

        x1 = Float.parseFloat(t5.getText());
        y1 = Float.parseFloat(t6.getText());
        x2 = Float.parseFloat(t7.getText());
        y2 = Float.parseFloat(t8.getText());
        x3 = Float.parseFloat(t11.getText());
        y3 = Float.parseFloat(t12.getText());
        x4 = Float.parseFloat(t13.getText());
        y4 = Float.parseFloat(t14.getText());


    }

    public void wyznaczRownowage() {


        a1 = (y1 - y2) / (x1 - x2);

        a2 = (y4 - y3) / (x4 - x3);

        b1 = y1 - a1 * x1;
        b2 = y3 - a2 * x3;

        float q = ((a1 * b2) - (a2 * b1)) / (a1 - a2);

        String pr = String.valueOf(q);

        punktRownowagi.setText(pr);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object zrodlo = e.getSource();

        if (zrodlo.equals(przycisk2)) {

            pobierzDane();
            wyznaczRownowage();
            uzupelnij();

        }
    }
}

