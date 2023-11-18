package memoria;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;
import java.util.Timer;

public class Memoria {

    static JButton botaoFacil = new JButton("Fácil");
    static JButton botaoMedio = new JButton("Medio");
    static JButton botaoDificil = new JButton("Dificil");
    static JButton botaoMenu = new JButton("Menu");
    static JFrame frame = new JFrame();
    static List<Integer> cartas;
    static List<JButton> botoesAbertos = new ArrayList<>();
    static List<JLabel> labelsAbertos = new ArrayList<>();
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
            JLabel label = new JLabel();
            label.setText(String.valueOf(carta));
            label.setVisible(false);
            botao.add(label);
            botao.addActionListener(new BotaoListener(label));
            frame.add(botao);

        }

        frame.add(botaoMenu);

        JLabel contagem = new JLabel();
        contagem.setFont(new Font("Tahoma", Font.BOLD, 32));
        Timer timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {

            int i = 80;
            @Override
            public void run() {
                contagem.setText(" "+i);
                i--;

                if(i<0){
                    timer.cancel();
                    contagem.setText("Game Over");
                    JOptionPane.showMessageDialog(null, "Tempo Esgotado");
                    menu();
                }
            }
        },0,1000);
        frame.add(contagem);



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
            JLabel label = new JLabel();
            label.setText(String.valueOf(carta));
            label.setVisible(false);
            botao.add(label);
            botao.addActionListener(new BotaoListener(label));
            frame.add(botao);

        }

        frame.add(botaoMenu);
        JLabel contagem = new JLabel();
        contagem.setFont(new Font("Tahoma",Font.BOLD, 32));
        Timer timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {

            int i = 70;
            @Override
            public void run() {
                contagem.setText(" "+i);
                i--;

                if(i<0){
                    timer.cancel();

                    contagem.setText("Game Over");
                    JOptionPane.showMessageDialog(null, "Tempo Esgotado");
                    menu();
                }
            }
        },0,1000);
        frame.add(contagem);


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
            JLabel label = new JLabel();
            label.setText(String.valueOf(carta));
            label.setVisible(false);
            botao.add(label);
            botao.addActionListener(new BotaoListener(label));
            frame.add(botao);
        }

        frame.add(botaoMenu);
        JLabel contagem = new JLabel();
        contagem.setFont(new Font("Tahoma",Font.BOLD, 32));
        Timer timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {

            int i = 60;
            @Override
            public void run() {
                contagem.setText(" "+i);
                i--;

                if(i<0){
                    timer.cancel();

                    contagem.setText("Game Over");
                    JOptionPane.showMessageDialog(null, "Tempo Esgotado");
                    menu();
                }
            }
        },0,1000);
        frame.add(contagem);

    }

    private static class BotaoListener implements ActionListener {

        private final JLabel label;

        public BotaoListener(JLabel label){
            this.label = label;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton botao = (JButton) e.getSource();

            if (!labelsAbertos.contains(this.label)) {
                labelsAbertos.add(this.label);
                botoesAbertos.add(botao);

                if (botoesAbertos.size() == 2) {
                    if (labelsAbertos.get(0).getText().equals(labelsAbertos.get(1).getText())){
                        botoesAbertos.get(0).setFont(new Font("Arial", Font.BOLD, 32));
                        botoesAbertos.get(1).setFont(new Font("Arial", Font.BOLD, 32));
                        botoesAbertos.get(0).setText(this.label.getText());
                        botoesAbertos.get(1).setText(this.label.getText());
                        paresEncontrados++;
                        if (paresEncontrados == cartas.size() / 2) {
                            JOptionPane.showMessageDialog(null, "Parabéns! Você encontrou todos os pares!");
                            menu();
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "As cartas não são iguais. Tente novamente!");
                        for (JButton b : botoesAbertos) {
                            b.setText("");
                        }
                    }

                    botoesAbertos.clear();
                    labelsAbertos.clear();
                }
            }
        }
    }


}