package jm.task.core.jdbc.parser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CsvParser {
    public void parse(String path){

        BufferedReader br = null;
        String line;


        try {
            br = new BufferedReader(new FileReader(path));
            while ((line = br.readLine()) != null) {

                String[] data = line.split(",");
                for (int i = 0; i < data.length; i++){
                    System.out.print(data[i] + ",");
                }
                System.out.println("/");
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("Done");
    }


}
