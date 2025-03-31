package com.example.TestWebScrapping.util;

import java.io.*;
import java.nio.file.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zipper {
    public static void zipFiles(String sourceDir, String zipFilePath) {
        try (FileOutputStream fos = new FileOutputStream(zipFilePath);
             ZipOutputStream zos = new ZipOutputStream(fos)) {

            Files.walk(Paths.get(sourceDir))
                    .filter(path -> !Files.isDirectory(path))
                    .forEach(path -> {
                        ZipEntry zipEntry = new ZipEntry(path.getFileName().toString());
                        try {
                            zos.putNextEntry(zipEntry);
                            Files.copy(path, zos);
                            zos.closeEntry();
                        } catch (IOException e) {
                            System.out.println("Erro ao adicionar arquivo no zip: " + e.getMessage());
                        }
                    });
            System.out.println("Arquivos compactados com sucesso em: " + zipFilePath);
        } catch (IOException e) {
            System.out.println("Erro ao criar arquivo zip: " + e.getMessage());
        }
    }
}