package ru.kos75;

import java.io.File;
import java.io.IOException;
import java.util.*;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;


public class Main {

    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {

        ArrayList<String> result = new ArrayList<>();
        Node tempList;

        try {
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = documentBuilder.parse("shop.xml");
            Node root = document.getDocumentElement();
            NodeList list = root.getChildNodes();
            for (int i = 0; i < list.getLength(); i++) {
                Node temp = list.item(i);
                if (temp.getNodeType() != Node.TEXT_NODE) {
                    NodeList listTemp = temp.getChildNodes();
                    for (int j = 0; j < listTemp.getLength(); j++) {
                        tempList = listTemp.item(j);
                        if (tempList.getNodeType() != Node.TEXT_NODE) {
                            result.add(tempList.getChildNodes().item(0).getTextContent());
                        }
                    }
                }
            }

        } catch (ParserConfigurationException | IOException | SAXException ex) {
            ex.printStackTrace(System.out);
        }

        String inputNum;
        File log = new File(result.get(7));
        HashMap<Integer, Integer> amountProduct = new HashMap<>();
        List<String> products = List.of("Хлеб", "Мясо", "Молоко", "Крупа", "Соль");
        List<Integer> prices = List.of(35, 250, 80, 40, 30);
        Basket basket = new Basket(products, prices, amountProduct);
        ClientLog clientLog = new ClientLog();
        Scanner scanner = new Scanner(System.in);

        System.out.println("____________________\n" + "В наличии продукты:" + "\n____________________");

        for (int i = 0; i < products.size(); i++) {
            System.out.println(
                    (i + 1) + ". " + products.get(i) + " " + prices.get(i) + " кг/шт руб");
        }


        if (!Objects.equals(result.get(0), "false")) {
            File f = new File(String.valueOf(nameFile(result, 1, 2)));
            if (f.isFile()) {
                if (Objects.equals(result.get(0), "txt")) {
                    basket = Basket.loadFromTxtFile(f);
                } else {
                    basket.loadFromJSonFile(f);
                }

                System.out.println("\nКорзина загружена.");
            } else {
                System.out.println("Файла Корзины не существует! Создана новая корзина.");
                if (Objects.equals(result.get(0), "txt")) {
                    new File("basket.txt");
                } else {
                    new File("basket.json");

                }
            }
        } else {
            basket = new Basket();
            System.out.println("Загрузка корзины запрещена. Создана новая корзина.");
        }

        while (true) {
            System.out.println("Введите 'end' для завершения");

            try {
                System.out.print("\nВведите позицию покупаемого товара [1-5]: > ");

                inputNum = scanner.nextLine();
                if ("end".equals(inputNum)) {
                    break;
                }

                if (Integer.parseInt(inputNum) > products.size() || Integer.parseInt(inputNum) < 1) {
                    System.out.println("Нет такого номера товара!");
                    continue;
                }

                System.out.print("Введите количество покупаемого: > ");

                String inputLot = scanner.nextLine();
                if ("end".equals(inputLot)) {
                    break;
                }

                int productNumber = Integer.parseInt(inputNum) - 1;
                int quantity = Integer.parseInt(inputLot);

                basket.addToCart(productNumber, quantity);
                clientLog.log(productNumber, quantity);
                basket.printCart();
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        if (!Objects.equals(result.get(3), "false")) {
            File f = new File(String.valueOf(nameFile(result, 4, 5)));
            if (Objects.equals(result.get(5), "txt")) {
                basket.saveTxt(f);
            } else {
                basket.saveJSon(f);
            }

        }

        if (Objects.equals(result.get(6), "true")) {
            clientLog.exportAsCSV(new File(String.valueOf(log)));
        }

        System.out.println("\nКОРЗИНА:");
        basket.printCart();
        scanner.close();
    }

    public static File nameFile(ArrayList<String> result, int a, int b) {
        String[] parts = result.get(a).split("\\.");
        String name = parts[0];
        String fileExtension = result.get(b);
        String nameFile = String.join(".", name, fileExtension);
        return new File(nameFile);
    }
}






