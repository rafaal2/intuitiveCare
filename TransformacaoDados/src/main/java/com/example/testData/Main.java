package com.example.testData;

import java.io.File;



import java.util.List;
import com.example.testData.service.ExtractorService;
import com.example.testData.util.writerUtil;
import com.example.testData.util.ZipperUtil;

public class Main {
    public static void main(String[] args) {
        String pdfPath = "C:\\projetos\\spring\\testeIC\\WebScrapping\\attachments\\Anexo_I_Rol_2021RN_465.2021_RN627L.2024.pdf";
        String currentDir = System.getProperty("user.dir");
        String filesDirPath = currentDir + File.separator + "files";
        File filesDir = new File(filesDirPath);
        if (!filesDir.exists()) {
            filesDir.mkdirs();
        }
        String csvFilePath = filesDirPath + File.separator + "Anexo.csv";
        String zipFilePath = currentDir + File.separator + "Teste_Rafael_Marinho.zip";

        try {
            ExtractorService pdfService = new ExtractorService();
            List<List<String>> data = pdfService.extractAndProcessData(pdfPath);
            System.out.println("Extração realizada com sucesso.");

            writerUtil.writeCsv(data, csvFilePath);
            System.out.println("CSV criado com sucesso.");

            ZipperUtil.zipFile(csvFilePath, zipFilePath);
            System.out.println("Operação realizada com sucesso.");
        } catch (Exception e) {
            System.err.println("Erro: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
