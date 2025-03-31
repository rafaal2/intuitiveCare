package com.example.testData.util;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class writerUtil {

    public static void writeCsv(List<List<String>> data, String csvFilePath) throws IOException {
        FileWriter writer = new FileWriter(csvFilePath);
        for (List<String> row : data) {
            String line = String.join(",", escapeCsv(row));
            writer.write(line + "\n");
        }
        writer.flush();
        writer.close();
    }

    private static List<String> escapeCsv(List<String> row) {
        List<String> escaped = new ArrayList<>();
        for (String cell : row) {
            if (cell.contains(",") || cell.contains("\"")) {
                cell = cell.replace("\"", "\"\"");
                cell = "\"" + cell + "\"";
            }
            escaped.add(cell);
        }
        return escaped;
    }
}
