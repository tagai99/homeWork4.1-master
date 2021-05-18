package com.geektech;

import java.util.Random;

public class Main {

    public static String[] heroesNames = {"Sonya", "Sub Zero",
            "Reptile", "Radon"};
    public static int[] heroesHealth = {300, 280, 250, 400};
    public static int[] heroesDamage = {20, 15, 25, 0};

    public static String bossName = "Shao Kahn";
    public static int bossHealth = 800;
    public static int bossDamage = 50;
    public static int roundNumber = 0;
    public static String superDamageHero = "";


    public static void main(String[] args) {
        // write your code here

        printStatistics();
        System.out.println("_____Mortal Kombat started____");

        while (!isGameFinished()) {
            round();
        }
    }

    public static boolean isGameFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!! " +
                    "Mortal Kombat finished ");
            return true;
        }

        boolean allHeroesDead = true;

        for (int heroHealth : heroesHealth) {
            if (heroHealth > 0) {
                allHeroesDead = false;
                break;
            }
        }

        if (allHeroesDead) {
            System.out.println(bossName + " Won!!!" +
                    "Mortal Kombat finished");
        }
        return allHeroesDead;
    }

    public static void round() {
        roundNumber++;
        System.out.println("______Round " + roundNumber + "______");
        bossDamage();
        addHealth();
        superDamageHero = getHeroForDamageBossDefence();
        heroesDamage();
        printStatistics();
    }

    public static void addHealth() {
        Random random = new Random();
        int num = random.nextInt(5) * 10;
        System.out.println("generated number is " + num);
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] <= 100 && heroesHealth[i] > 0) {
                heroesHealth[i] = heroesHealth[i] + num;
            }
        }
    }

    public static void bossDamage() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                heroesHealth[i] = heroesHealth[i] - bossDamage;
            }
        }
    }

    public static void heroesDamage() {
        Random random = new Random();

        int coeff = random.nextInt(9) + 2;
        for (int i = 0; i < heroesDamage.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                if (superDamageHero == heroesNames[i]) {
                    bossHealth = bossHealth - heroesDamage[i] * coeff;
                    System.out.println("Super damage hero = " +
                            superDamageHero + " " +
                            (heroesDamage[i] * coeff));
                } else {
                    bossHealth = bossHealth - heroesDamage[i];
                }
            }
            if (heroesHealth[i] < 0) {
                heroesHealth[i] = 0;
            }
            if (bossHealth < 0) {
                bossHealth = 0;
            }
        }
    }

    public static String getHeroForDamageBossDefence() {
        Random random = new Random();
        int randomIndex = random.nextInt(heroesNames.length);
        return heroesNames[randomIndex];
    }

    public static void printStatistics() {
        System.out.println(bossName + " = health " + bossHealth +
                " damage [" + bossDamage + "]");
        for (int i = 0; i < heroesNames.length; i++) {
            System.out.println(heroesNames[i] + " = health " +
                    heroesHealth[i] + " damage [" +
                    heroesDamage[i] + "]");
        }
    }
}
