package org.example;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Basket b = new Basket(new int[]{60, 45, 24, 340}, new String[]{"1. Хлеб", "2. Соль", "3. Вода", "4. Вино"});
        b.addToCart(3, 5);
        b.printCart();
        b.saveTxt(new File("text.txt"));
    }
}