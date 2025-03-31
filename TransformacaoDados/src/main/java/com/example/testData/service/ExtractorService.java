package com.example.testData.service;

import org.apache.pdfbox.pdmodel.PDDocument;
import technology.tabula.ObjectExtractor;
import technology.tabula.Page;
import technology.tabula.RectangularTextContainer;
import technology.tabula.extractors.BasicExtractionAlgorithm;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import technology.tabula.Table;

public class ExtractorService {

    public List<List<String>> extractAndProcessData(String pdfPath) throws IOException {
        List<List<String>> allRows = new ArrayList<>();

        PDDocument document = PDDocument.load(new File(pdfPath));
        ObjectExtractor extractor = new ObjectExtractor(document);
        int numPages = document.getNumberOfPages();

        BasicExtractionAlgorithm bea = new BasicExtractionAlgorithm();

        for (int i = 1; i <= numPages; i++) {
            Page page = extractor.extract(i);
            List<Table> tables = bea.extract(page);
            for (Table table : tables) {
                List<List<RectangularTextContainer>> rows = table.getRows();
                for (List<RectangularTextContainer> row : rows) {
                    List<String> rowData = new ArrayList<>();
                    for (RectangularTextContainer cell : row) {
                        rowData.add(cell.getText().trim());
                    }
                    if (!rowData.isEmpty()) {
                        allRows.add(rowData);
                    }
                }
            }
        }
        document.close();

        if (!allRows.isEmpty()) {
            List<String> header = allRows.get(0);
            int odIndex = header.indexOf("OD");
            int ambIndex = header.indexOf("AMB");
            for (int i = 1; i < allRows.size(); i++) {
                List<String> row = allRows.get(i);
                if (odIndex >= 0 && odIndex < row.size()) {
                    row.set(odIndex, replaceValue(row.get(odIndex)));
                }
                if (ambIndex >= 0 && ambIndex < row.size()) {
                    row.set(ambIndex, replaceValue(row.get(ambIndex)));
                }
            }
        }
        return allRows;
    }

    private String replaceValue(String value) {
        if (value.equals("OD")) {
            return "Seg. Odontológica";
        } else if (value.equals("AMB")) {
            return "Seg. Ambulatorial";
        }
        return value;
    }
}


