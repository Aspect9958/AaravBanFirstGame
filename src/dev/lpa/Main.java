package dev.lpa;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.*;
import java.util.List;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        GamePanel panel = new GamePanel();
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter USERNAME #1:");
        String username1 = sc.nextLine().toUpperCase();
        System.out.println("Enter USERNAME #2");
        String username2 = sc.nextLine().toUpperCase();
        System.out.println("-".repeat(200));


        System.out.println(username1 + "'s DECK:" + " ".repeat(150) + username2 + "'s DECK:");
        List<pokemonCards> deck1 = new java.util.ArrayList<>(pokemonCards.Deck());
        List<pokemonCards> deck2 = new java.util.ArrayList<>(pokemonCards.Deck());
        printDecks(deck1, deck2);
        panel.setDecks(deck1, deck2, username1, username2);
        new GameFrame();

        System.out.println("-".repeat(250));
        System.out.println("BATTLE HAS STARTED between " + username1 + " and " + username2 + "!");
        System.out.println("-".repeat(250));
        Turns(username1, username2, deck1, deck2);
    }

    public static void Turns(String username1, String username2, List<pokemonCards> deck1, List<pokemonCards> deck2) {
        Scanner sc = new Scanner(System.in);
        GamePanel panel = new GamePanel();

        pokemonCards pokemon1;
        pokemonCards pokemon2;
        String attack1;
        String attack2;

        System.out.println("-".repeat(100));
        System.out.println("TURN " + 1);
        System.out.println("-".repeat(250));
        System.out.println(username1 + " pick pokemon to send out:");
        int firstPokemon1 = sc.nextInt() - 1;
        GamePanel.setPokemonNumber(firstPokemon1 + 1);
        pokemon1 = deck1.get(firstPokemon1);
        System.out.println("-".repeat(250));
        System.out.println(username1 + " sent out " + pokemon1.name());
        System.out.println("-".repeat(250));

        System.out.println(username2 + " pick pokemon to send out: ");
        int firstPokemon2 = sc.nextInt() - 4;
        GamePanel.setPokemonNumber(firstPokemon2 + 4);
        pokemon2 = deck2.get(firstPokemon2);
        System.out.println("-".repeat(250));
        System.out.println(username2 + " sent out " + pokemon2.name());
        System.out.println("-".repeat(250));


        for (int i = 2; i < 50; i++) {

            System.out.println("-".repeat(250));
            System.out.println("TURN " + i);
            System.out.println("-".repeat(250));

            System.out.println(username1 + " Enter which attack you want to do (b) base (s) super: ");
            attack1 = sc.next();
            pokemon2 = pokemon2.newHp(attackType(attack1, pokemon1, pokemon2, 1));
            System.out.println(pokemon2.name() + " took " + (attack1.equalsIgnoreCase("B") ? pokemon1.attack1() : pokemon1.attack2()) +
                    " Damage from " + pokemon1.name());
            System.out.println("-".repeat(250));

            if (pokemon2.hp() <= 0) {

                deck2.set(firstPokemon2, new pokemonCards("DEAD", 0, 0, 0, "pokeball.png"));
                System.out.println("-".repeat(250));
                printDecks(deck1, deck2);
                panel.setDecks(deck1, deck2, username1, username2);
                System.out.println("-".repeat(250));
                firstPokemon2 = pokemonDeath(pokemon2, username2) - 4;
                pokemon2 = deck2.get(firstPokemon2);
                System.out.println(username2 + " sent out " + pokemon2.name());

                if (victory(deck2, username1)) {
                    System.out.println("-".repeat(250));
                    System.out.println();
                    System.out.println(username1 + " WON THE BATTLE");
                    System.out.println();
                    System.out.println("-".repeat(250));
                    GamePanel.setVictory(true, username1);
                    break;
                }

            }

            System.out.println(username2 + " Enter which attack you want to do (b) base (s) super: ");
            attack2 = sc.next();
            pokemon1 = pokemon1.newHp(attackType(attack2, pokemon1, pokemon2, 2));
            System.out.println(pokemon1.name() + " took " + (attack2.equalsIgnoreCase("B") ? pokemon2.attack1() : pokemon2.attack2()) + " Damage from " + pokemon2.name());
            System.out.println("-".repeat(250));

            if (pokemon1.hp() <= 0) {

                deck1.set(firstPokemon1, new pokemonCards("DEAD", 0, 0, 0, "pokeball.png"));
                System.out.println("-".repeat(250));
                printDecks(deck1, deck2);
                panel.setDecks(deck1, deck2, username1, username2);
                System.out.println("-".repeat(250));
                firstPokemon1 = pokemonDeath(pokemon1, username1) - 1;
                pokemon1 = deck1.get(firstPokemon1);
                System.out.println(username1 + " sent out " + pokemon1.name());

                if (victory(deck1, username2)) {
                    System.out.println("-".repeat(250));
                    System.out.println();
                    System.out.println(username2 + " WON THE BATTLE");
                    System.out.println();
                    System.out.println("-".repeat(250));
                    GamePanel.setVictory(true, username2);
                    break;
                }
            }

        }
    }

    public static boolean victory(List<pokemonCards> deck, String userName) {

        int v = 1;
        for (int i = 0; i < deck.size(); i++) {
            if (deck.get(i).equals(new pokemonCards("DEAD", 0, 0, 0, "pokeball.png"))) {
                v++;
            }
        }

        if (v == 4) {
            return true;
        }
        return false;
    }

    public static int pokemonDeath(pokemonCards pokemon, String username) {

        Random random = new Random();
        Scanner sc = new Scanner(System.in);
        String[] deaths = {"Died by gang nigger rape", "Died by fentynal overdose", "Died by indian smell"};

        System.out.println(pokemon.name() + " " + deaths[random.nextInt(0, 2)]);
        System.out.println("-".repeat(250));

        System.out.println(username + " Enter new pokemon number:");
        int number = sc.nextInt();
        GamePanel.setPokemonNumber(number);
        return number;
    }

    public static int attackType(String attack, pokemonCards first, pokemonCards second, int userNameNumber) {

        int hp1;
        int hp2;
        int hp3;
        int hp4;

        if (userNameNumber == 1) {

            if (attack.equalsIgnoreCase("B")) {

                hp1 = second.hp() - first.attack1();
                GamePanel.setHp2(hp1);
                return hp1;

            } else if (attack.equalsIgnoreCase("S")) {

                hp2 = second.hp() - first.attack2();
                GamePanel.setHp2(hp2);
                return hp2;
            }


        } else if (userNameNumber == 2) {

            if (attack.equalsIgnoreCase("B")) {

                hp3 = first.hp() - second.attack1();
                GamePanel.setHp1(hp3);
                return hp3;

            } else if (attack.equalsIgnoreCase("S")) {

                hp4 = first.hp() - second.attack2();
                GamePanel.setHp1(hp4);
                return hp4;
            }
        }
        return 0;
    }

    public static void printDecks(List<?> deck1, List<?> deck2) {

        for (int i = 0; i < deck2.size(); i++) {

            int k = i + 1;
            int j = i + 4;

            System.out.println("(" + k + ")" + deck1.get(i) + " ".repeat(50) + "(" + j + ")" + deck2.get(i));
        }
    }

    public class MyKeyAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            switch (e.getKeyCode()) {

                case KeyEvent.VK_1 :
                case KeyEvent.VK_2 :
                case KeyEvent.VK_3 :
                case KeyEvent.VK_4 :
                case KeyEvent.VK_5 :
                case KeyEvent.VK_6 :

            }
        }
    }
}
