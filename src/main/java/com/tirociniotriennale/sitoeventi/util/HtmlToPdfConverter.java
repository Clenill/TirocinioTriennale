package com.tirociniotriennale.sitoeventi.util;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class HtmlToPdfConverter {

    public static void convertHtmlToPdf(String htmlContent, HttpServletResponse response) {
        try {
            // Configura le proprietà del convertitore
            ConverterProperties converterProperties = new ConverterProperties();

            // Converte l'HTML in un array di byte
            ByteArrayOutputStream pdfOutputStream = new ByteArrayOutputStream();
            HtmlConverter.convertToPdf(htmlContent, pdfOutputStream, converterProperties);

            // Configura la risposta HTTP
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=dettaglio_ordine.pdf");

            // Scrive l'array di byte nel flusso di output della risposta HTTP
            response.getOutputStream().write(pdfOutputStream.toByteArray());
            response.getOutputStream().flush();
        } catch (IOException e) {
            // Gestisci l'eccezione in base alle tue esigenze
            e.printStackTrace();
        }
    }
}
