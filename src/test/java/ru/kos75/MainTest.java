package ru.kos75;

import org.junit.jupiter.api.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;


@DisplayName("Тест: Main")
public class MainTest {

    @Test
    @DisplayName("Проверка парсинга имени и расширения файла из xml")
    void nameFile() throws Exception {
        ArrayList<String> result = new ArrayList<>(Arrays.asList("noName1.not", "itsName.not", "noName3.not", "yes"));
        int a = 1;
        int b = 3;
        Main.nameFile(result, a, b);
        Assertions.assertEquals(Main.nameFile(result, a, b), new File("itsName.yes"));
    }
}