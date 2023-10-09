package jm.task.core.jdbc;

import jm.task.core.jdbc.costance.PathConst;
import jm.task.core.jdbc.parser.CsvParser;




public class Main {
    public static void main(String[] args) {

        CsvParser csvParser = new CsvParser();
//        csvParser.parse(OILPATH);
//        csvParser.parse(GOLDPATH);
        csvParser.parse(PathConst.BTCPATH.getURL());
    }
}
