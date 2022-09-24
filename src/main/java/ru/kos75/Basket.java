package ru.kos75;

import java.io.*;
import java.util.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class Basket {

    HashMap<Integer, Integer> amountProduct = new HashMap<>();
    private List<Integer> prices;
    private List<String> products;


    protected Basket(List<String> products, List<Integer> prices,
                  HashMap<Integer, Integer> amountProduct) {
        this.prices = prices;
        this.products = products;
        this.amountProduct = amountProduct;
    }

    public Basket() {

    }

    protected void addToCart(int productNum, int quantity) throws IOException {
        amountProduct.merge(productNum, quantity, Integer::sum);

    }

    protected void printCart() {
        int all = 0;
        System.out.println(products);
        for (int i = 0; i < getAmountProduct().size(); i++) {
            if (getAmountProduct().get(i) != null) {
                System.out.println(getProducts().get(i) + " " + getAmountProduct().get(i) + "кг/шт  "
                        + getPrices().get(i) + " руб. за кг/шт     всего на: " + (getAmountProduct().get(i)
                        * getPrices().get(
                        i)) + "руб.");
                all += (getAmountProduct().get(i) * getPrices().get(i));
            }
        }
        System.out.println("Всего: " + all + " руб.");

    }


    protected void saveJSon(File textFile) {
        try (Writer writer = new FileWriter(textFile)) {
            Gson gson = new Gson();
            Basket temp = new Basket(products, prices, amountProduct);
            temp.amountProduct = this.amountProduct;
            gson.toJson(temp, writer);
            System.out.println("Файл json сохранен.");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    protected void loadFromJSonFile(File textFile) throws RuntimeException {
        try (Reader reader = new FileReader(textFile)) {

            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            Basket temp = gson.fromJson(reader, Basket.class);
            this.amountProduct = temp.amountProduct;
            //System.out.println(temp);
            System.out.println("\nФайл json загружен.");
        } catch (Exception e) {
            System.out.println("Файл не найден!");

        }

    }

    protected void saveTxt(File textFile) throws IOException {
        try (PrintWriter writer = new PrintWriter(textFile)) {
            for (int i = 0; i < products.size(); i++) {
                int set;
                if (amountProduct.get(i) == null) {
                    set = 0;
                } else {
                    set = amountProduct.get(i);
                }
                writer.print(set + " ");
            }
        } catch (IOException e) {
            System.out.println("Файл не найден!");
            throw new IOException(e);

        }
    }

    protected static Basket loadFromTxtFile(File textFile) throws IOException {
        Basket basket = new Basket();
        String line = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(textFile))) {
            line = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] temp = Objects.requireNonNull(line).split(" ");
        for (int i = 0; i < temp.length; i++) {
            basket.amountProduct.put(i, (Integer.parseInt(temp[i])));
        }
        System.out.println("\n");
        basket.printCart();
        System.out.println("\nФайл txt загружен.");
        return basket;
    }


    private List<String> getProducts() {
        return products;
    }

    private HashMap<Integer, Integer> getAmountProduct(){return amountProduct;}

    private List<Integer> getPrices() {
        return prices;
    }


}




