package com.example.TestWebScrapping.service;

import com.example.TestWebScrapping.util.Downloader;
import com.example.TestWebScrapping.util.Parser;
import com.example.TestWebScrapping.util.Zipper;

import java.util.List;

public class Scrapper {
    private static final String DOWNLOAD_DIR = "attachments";
    private static final String ZIP_FILE_PATH = "attachments.zip";

    public void run(String url) {
        List<String> pdfLinks = Parser.getPdfLinks(url);
        if (pdfLinks.isEmpty()) {
            System.out.println("Nenhum link PDF encontrado.");
            return;
        }
        Downloader.downloadPdfs(pdfLinks, DOWNLOAD_DIR);
        Zipper.zipFiles(DOWNLOAD_DIR, ZIP_FILE_PATH);
    }
}

