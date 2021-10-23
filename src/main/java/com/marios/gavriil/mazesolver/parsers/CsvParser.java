package com.marios.gavriil.mazesolver.parsers;

import com.opencsv.CSVReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class CsvParser {

    private static final String COMMA_SEPARATOR = ",";

    Logger logger = LoggerFactory.getLogger(CsvParser.class);

    public String [][] parse (String path) {
        String[][] array = null;
        try {
            FileReader fileReader = new FileReader(path);

            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            List<String[]> lines = new ArrayList<>();

            while ((line = bufferedReader.readLine())!= null){
                lines.add(line.split(COMMA_SEPARATOR));
            }

            array = new String[lines.size()][0];
            lines.toArray(array);

            bufferedReader.close();
            fileReader.close();
        }catch(IOException e) {
            logger.error(e.getMessage());
        }
        return array;
    }
}