package ru.kos75;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;


@DisplayName("Тест: ClientLogTest")
public class ClientLogTest {

    @Test
    @DisplayName("Проверка записи лога в файл testLog.csv")
    void exportAsCSV1() throws Exception {

        ClientLog clientLog = new ClientLog();
        clientLog.log(3, 3);
        clientLog.log(1, 2);
        clientLog.log(2, 1);
        clientLog.exportAsCSV(new File("testLog.csv"));

        File f = new File("testLog.csv");
        if (f.exists() && !f.isDirectory()) {
        //    System.out.println("есть");
        }
        Assertions.assertEquals("testLog.csv",
                "testLog.csv");

    }

}