package org.example;

import org.example.Basket;
import org.example.ClientLog;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ClientLog log = new ClientLog();
        Basket b = new Basket(new int[]{60, 45, 24, 340}, new String[]{"1. Хлеб", "2. Соль", "3. Вода", "4. Вино"});
        b.addToCart(3, 5);
        log.log(3,5);
        try {
            log.exportAsCSV(new File("log.csv"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        b.printCart();
        b.saveJSON(new File("text.json"));
    }
}