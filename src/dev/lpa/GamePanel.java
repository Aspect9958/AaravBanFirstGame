package dev.lpa;

import dev.Defaults;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class GamePanel extends JPanel implements ActionListener {

    static final int WIDTH = 1000;
    static final int LENGTH = 1000;
    private ImageIcon image;

    static int pokemonNumber;

    static pokemonCards storedCard1;
    static pokemonCards storedCard2;

    Timer timer;

    static boolean running = false;

     static List<pokemonCards> deck1 = new ArrayList<>();
     static List<pokemonCards> deck2 = new ArrayList<>();

     static String username1;
     static String username2;

     static boolean victory = false;
     static String winnerUsername;


    GamePanel() {
        image = new ImageIcon("forestClearing.jpg");
        this.setPreferredSize(new Dimension(WIDTH, LENGTH));
        this.setFocusable(true);
        startGame();
    }

    public void startGame() {
        running = true;
        timer = new Timer(75, this);
        timer.start();
    }

    public void setDecks(List<pokemonCards> deck, List<pokemonCards> nextDeck, String input1, String input2) {
        deck1 = deck;
        deck2 = nextDeck;
        username1 = input1;
        username2 = input2;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image.getImage(), 0, 0, getWidth(), getHeight(), this);
        printDecks(g);

        if (pokemonNumber > 0) {
            drawPokemon(g);
        }

        if (victory) {
            drawVictory(g);
        }
    }


    public void drawVictory(Graphics g) {
        g.setFont(new Font("Ink Free", Font.BOLD, 50));
        g.drawString(winnerUsername + " WON THE GAME", 200, 250);
    }

    public void printDecks(Graphics g) {

        g.setColor(Color.white);
        g.setFont(new Font("Ink Free", Font.BOLD, 30));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString(username1 + "'s Deck:", 25, 25);
        for (int i = 0; i < deck1.size(); i++) {
            int j = i * 300;
            int k = i + 1;
            g.drawString(("(" + k + ")" + deck1.get(i).name()), 80 + j, 75);
            g.setFont(new Font("Ink Free", Font.BOLD, 20));
            g.drawString("HP: " + (deck1.get(i).hp()), 50 + j, 125);
            g.drawString("Base: " + deck1.get(i).attack1(), 150 + j, 125);
            g.drawString("Super: " + deck1.get(i).attack2(), 100 + j, 150);
            g.setFont(new Font("Ink Free", Font.BOLD, 30));
            ImageIcon image = Defaults.getResizedImage("pokeball.png", 50, 50);
            g.drawImage(image.getImage(), 125 + j, 175, this);

        }
        g.drawString(username2 + "'s Deck:", 25, 975);
        for (int i = 0; i < deck2.size(); i++) {
            int j = i * 300;
            int k = i + 4;
            g.drawString(("(" + k + ")" + deck2.get(i).name()), 80 + j, 775);
            g.setFont(new Font("Ink Free", Font.BOLD, 20));
            g.drawString("HP: " + (deck2.get(i).hp()), 50 + j, 825);
            g.drawString("Base: " + deck2.get(i).attack1(), 150 + j, 825);
            g.drawString("Super: " + deck2.get(i).attack2(), 100 + j, 850);
            g.setFont(new Font("Ink Free", Font.BOLD, 30));
            ImageIcon image = Defaults.getResizedImage("pokeball.png", 50, 50);
            g.drawImage(image.getImage(), 125 + j, 875, this);
        }
    }

    public void drawPokemon(Graphics g) {

        if (pokemonNumber > 0 && pokemonNumber < 4) {

            storedCard1 = deck1.get(pokemonNumber - 1);
            ImageIcon imageIcon = Defaults.getResizedImage(storedCard1.image(), 225, 225);
            g.drawImage(imageIcon.getImage(), 350, 225, this);
            g.drawString("HP: " + storedCard1.hp(), 350, 450);

            if (storedCard2 != null && storedCard2.hp() > 0) {
                ImageIcon image = Defaults.getResizedImage(storedCard2.image(), 225, 225);
                g.drawImage(image.getImage(), 350, 500, this);
                g.drawString("HP: " + storedCard2.hp(), 350, 550);

            }

        } if (pokemonNumber > 3 && pokemonNumber < 7) {

            if (storedCard1.hp() > 0) {
                ImageIcon imageIcon = Defaults.getResizedImage(storedCard1.image(), 225, 225);
                g.drawImage(imageIcon.getImage(), 350, 225, this);
                g.drawString("HP: " + storedCard1.hp(), 350, 450);
            }

            storedCard2 = deck2.get(pokemonNumber - 4);
            ImageIcon imageIcon = Defaults.getResizedImage(storedCard2.image(), 225, 225);
            g.drawImage(imageIcon.getImage(), 350, 500, this);
            g.drawString("HP: " + storedCard2.hp(), 350, 550);
        }



    }

    public static void setVictory(boolean victory1, String winner) {
        victory =  victory1;
        winnerUsername = winner;
    }

    public static void setHp1(int hp) {
        storedCard1.newHp(hp);
    }
    public static void setHp2(int hp) {
        storedCard2.newHp(hp);
    }

    public static void setPokemonNumber(int number) {
        pokemonNumber = number;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (running) {

        }
        repaint();
    }


}
