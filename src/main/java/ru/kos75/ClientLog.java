package ru.kos75;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

public class ClientLog {

    private List<Integer> logNum = new LinkedList<>();
    private List<Integer> logAmount = new LinkedList<>();

    public ClientLog() {
    }

    public void log(int productNumber, int quantity) {
        logNum.add(productNumber);
        logAmount.add(quantity);
    }

    public void exportAsCSV(File txtFile) {
        try (PrintWriter writer = new PrintWriter(new File(txtFile.toURI()))) {
            writer.append("productNum,amount" + "\n");
            for (int i = 0; i < logNum.size(); i++) {
                writer.append(logNum.get(i) + "," + logAmount.get(i) + "\n");

            }
            System.out.println("Лог файл сохранен.");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
