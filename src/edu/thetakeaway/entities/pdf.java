
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.thetakeaway.entities;

import com.itextpdf.text.Document;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import edu.thetakeaway.services.ServiceFacture;
import edu.thetakeaway.services.ServiceFournisseur;
import edu.thetakeaway.services.ServiceIngrediant;
import edu.thetakeaway.utils.SharedData;

/**
 *
 * @author Amirov
 */
public class pdf {

    public void GeneratePdf(String filename) throws FileNotFoundException, DocumentException, BadElementException, IOException, InterruptedException, SQLException {
        Document document = new Document() {
        };
        PdfWriter.getInstance(document, new FileOutputStream(filename + ".pdf"));
        document.open();

        ServiceFacture m = new ServiceFacture();
        Facture rec = SharedData.selectedFacture;

        document.add(new Paragraph("Facture :"));
        document.add(new Paragraph(rec.getFournisseur().getNom()));
        document.add(new Paragraph("Fourrnisseur :"));
        document.add(new Paragraph(rec.getIngrediant().getNom()));
        document.add(new Paragraph("Quantité :"));
        document.add(new Paragraph(String.valueOf(rec.getQunatite())));
        document.add(new Paragraph("Prix unitaire :"));
        document.add(new Paragraph(String.valueOf(rec.getPrix_unitaire())));
        float a = rec.getQunatite() * rec.getPrix_unitaire();
        document.add(new Paragraph("total :"));
        document.add(new Paragraph(String.valueOf(a)));

        document.close();
        Process pro = Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + filename + ".pdf");
    }

}
