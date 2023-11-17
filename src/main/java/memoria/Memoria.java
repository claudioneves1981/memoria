package memoria;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class Memoria {

    static JButton botaoFacil = new JButton("Fácil");
    static JButton botaoMedio = new JButton("Medio");
    static JButton botaoDificil = new JButton("Dificil");
    static JButton botaoMenu = new JButton("Menu");
    static JFrame frame = new JFrame();
    static List<Integer> cartas;
    static List<JButton> botoesAbertos;
    static int paresEncontrados;


    public static void main(String[] args) {
        menu();
    }

    private static void menu() {

        frame.getContentPane().removeAll();
        frame.getContentPane().repaint();
        frame.setTitle("Jogo Memoria");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(new GridLayout(5, 4));

        botaoFacil.addActionListener(e -> iniciarFacil());
        botaoMedio.addActionListener(e -> iniciarMedio());
        botaoDificil.addActionListener(e -> iniciarDificil());
        botaoMenu.addActionListener(e -> menu());

        frame.add(botaoFacil);
        frame.add(botaoMedio);
        frame.add(botaoDificil);
        frame.setFocusable(true);
        frame.setVisible(true);
    }

    private static void iniciarFacil() {

        cartas = new ArrayList<>();
        for(int i = 1; i <=8; i++){
            cartas.add(i);
        }
        cartas.addAll(cartas);
        Collections.shuffle(cartas);
        paresEncontrados = 0;
        botoesAbertos = new ArrayList<>();

        frame.getContentPane().removeAll();
        frame.getContentPane().repaint();

        frame.setSize(600, 600);
        frame.setLayout(new GridLayout(5, 4));

        for (Integer carta : cartas) {
            JButton botao = new JButton();
            botao.setText(String.valueOf(carta));
            botao.addActionListener(new BotaoListener());
            frame.add(botao);

        }

        frame.add(botaoMenu);



    }

    private static void iniciarMedio() {

        cartas = new ArrayList<>();
        for(int i = 1; i <=18; i++){
            cartas.add(i);
        }
        cartas.addAll(cartas);
        Collections.shuffle(cartas);
        paresEncontrados = 0;
        botoesAbertos = new ArrayList<>();

        frame.getContentPane().removeAll();
        frame.getContentPane().repaint();

        frame.setSize(600, 600);
        frame.setLayout(new GridLayout(7, 6));

        for (Integer carta : cartas) {
            JButton botao = new JButton();
            botao.setText(String.valueOf(carta));
            botao.addActionListener(new BotaoListener());
            frame.add(botao);

        }

        frame.add(botaoMenu);


    }

    private static void iniciarDificil() {

        cartas = new ArrayList<>();
        for(int i = 1; i <=32; i++){
            cartas.add(i);
        }
        cartas.addAll(cartas);
        Collections.shuffle(cartas);
        paresEncontrados = 0;
        botoesAbertos = new ArrayList<>();

        frame.getContentPane().removeAll();
        frame.getContentPane().repaint();

        frame.setSize(800, 800);
        frame.setLayout(new GridLayout(9, 8));

        for (Integer carta : cartas) {
            JButton botao = new JButton();
            botao.setText(String.valueOf(carta));
            botao.addActionListener(new BotaoListener());
            frame.add(botao);
        }

        frame.add(botaoMenu);

    }

    private static class BotaoListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton botao = (JButton) e.getSource();

            if (!botoesAbertos.contains(botao)) {
                botao.setText(String.valueOf(cartas.get(botoesAbertos.size())));
                botoesAbertos.add(botao);

                if (botoesAbertos.size() == 2) {
                    if (botoesAbertos.get(0).getText().equals(botoesAbertos.get(1).getText())) {
                        paresEncontrados++;
                        if (paresEncontrados == cartas.size() / 2) {
                            JOptionPane.showMessageDialog(null, "Parabéns! Você encontrou todos os pares!");
                            System.exit(0);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "As cartas não são iguais. Tente novamente!");
                        for (JButton b : botoesAbertos) {
                            b.setText("");
                        }
                    }

                    botoesAbertos.clear();
                }
            }
        }
    }


}