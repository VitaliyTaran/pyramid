package com.epam.geometry.data;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class DataReader {
    final static Logger logger = Logger.getLogger(DataReader.class);


    public List<String> readFile(String nameOfFileWithWay) {
        List<String> result = new ArrayList<String>();
        try {
            FileReader fileReader = new FileReader(nameOfFileWithWay);
            BufferedReader reader = new BufferedReader(fileReader);
            Scanner scanner = new Scanner(reader);
            DataParser parser = new DataParser();
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] words = parser.parse(line);
                result.addAll(Arrays.asList(words));
            }
            scanner.close();
            reader.close();
            fileReader.close();

        } catch (IOException e) {
            logger.warn(e);
        }
        return result;
    }
}