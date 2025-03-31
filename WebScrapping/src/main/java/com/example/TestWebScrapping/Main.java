package com.example.TestWebScrapping;

import com.example.TestWebScrapping.service.Scrapper;

public class Main {
    public static void main(String[] args) {
        String url = "https://www.gov.br/ans/pt-br/acesso-a-informacao/participacao-da-sociedade/atualizacao-do-rol-de-procedimentos";
        Scrapper service = new Scrapper();
        service.run(url);
    }
}
