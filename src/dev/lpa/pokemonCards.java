package dev.lpa;

import java.util.List;

public record pokemonCards(String name, int hp, int attack1, int attack2, String image) {


    @Override
    public String toString() {
        return ("""
                POKEMON: "%s", HP: "%s", BASE ATTACK: "%s", SUPER: "%s"  """.
                formatted(name, hp, attack1, attack2));
    }

    public pokemonCards newHp(int newHp) {
        return new pokemonCards(this.name, newHp, this.attack1, this.attack2, this.image);
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public int hp() {
        return hp;
    }

    @Override
    public int attack1() {
        return attack1;
    }

    @Override
    public int attack2() {
        return attack2;
    }

    public static pokemonCards set(pokemonCards p) {

        pokemonCards pokemonCards = p;
        return pokemonCards;
    }

    public static List<pokemonCards> Deck() {

        System.out.println("-".repeat(200));
        List<pokemonCards> deck = List.of(Pokemon.getPokemonValues(Pokemon.getRandomPokemon()),
                Pokemon.getPokemonValues(Pokemon.getRandomPokemon()),
                Pokemon.getPokemonValues(Pokemon.getRandomPokemon()));
        return deck;
    }

    public static void pause() throws InterruptedException {
        Thread.sleep(1000);
    }

}