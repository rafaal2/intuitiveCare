package com.example.TestWebScrapping.util;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Downloader {
    public static void downloadPdfs(List<String> pdfLinks, String directory) {
        try {
            Files.createDirectories(Paths.get(directory));
            for (String pdfLink : pdfLinks) {
                String fileName = pdfLink.substring(pdfLink.lastIndexOf("/") + 1);
                System.out.println("Baixando: " + pdfLink);
                URL url = new URL(pdfLink);
                try (InputStream in = url.openStream();
                     OutputStream out = new BufferedOutputStream(
                             new FileOutputStream(directory + File.separator + fileName))) {
                    byte[] buffer = new byte[4096];
                    int bytesRead;
                    while ((bytesRead = in.read(buffer)) != -1) {
                        out.write(buffer, 0, bytesRead);
                    }
                }
            }
            System.out.println("Download concluído com sucesso.");
        } catch (IOException e) {
            System.out.println("Erro no download: " + e.getMessage());
        }
    }
}

