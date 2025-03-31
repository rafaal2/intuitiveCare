package com.example.TestWebScrapping.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Parser {
    public static List<String> getPdfLinks(String url) {
        List<String> pdfLinks = new ArrayList<>();
        try {
            Document doc = Jsoup.connect(url).get();
            Elements links = doc.select("a[href]");
            for (Element link : links) {
                String href = link.attr("abs:href");
                if (href.endsWith(".pdf") && (href.contains("Anexo_I") || href.contains("Anexo_II"))) {
                    pdfLinks.add(href);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao acessar a página: " + e.getMessage());
        }
        return pdfLinks;
    }
}

