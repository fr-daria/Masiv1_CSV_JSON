package org.example;
import com.google.gson.Gson;

import java.io.*;
import java.util.Arrays;

public class Basket implements Serializable {

    private String[] productsInBasket;
    private int[] prices;
    private int[] count;


    public Basket(int[] priсes, String[] productsInBasket) {
        this.prices = priсes;
        this.productsInBasket = productsInBasket;
        this.count = new int[productsInBasket.length];
    }

    public void addToCart(int productNum, int amount) {
        count[productNum - 1] += amount;
    }

    public void printCart() {
        int allprise = 0;
        for (int i = 0; i < productsInBasket.length; i++) {
            if (count[i] > 0) {
                int pprise = prices[i] * count[i];
                allprise += pprise;
                System.out.println("Корзина покупок " + productsInBasket[i] + " " + prices[i] + " " + count[i] + " " + pprise);
            }
            System.out.println("Итого " + allprise);
        }
    }

    public void saveJSON(File textFile) {
        try (PrintWriter writer = new PrintWriter(textFile)) {
            Gson gson = new Gson();
            String json = gson.toJson(this);
            writer.print(json);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Basket loadFromJSONFile(File textFile) throws IOException {
        Basket b  = null;
        int i;
        try (BufferedReader ips = new BufferedReader(new FileReader(textFile))) {
            StringBuilder builder = new StringBuilder();
            String line = null;
            while ((line = ips.readLine()) != null){
                builder.append(line);
            }
            Gson gson = new Gson();
            b = gson.fromJson(builder.toString() , Basket.class);
        }
        return b;
    }

    public void saveBin(File file) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(this);
            oos.flush();
        }
    }

    public static Basket loadFromBinFile(File file) throws IOException, ClassNotFoundException {
        Basket b1 = null;
        try (FileInputStream fis = new FileInputStream(file);
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            b1 = (Basket) ois.readObject();
        }
        return b1;
    }
}
