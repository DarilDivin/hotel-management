package view.utils;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import model.Consommation;
import model.Controllers.SejourController;
import model.Sejour;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PDFGenerator {
    public static void generateFacture(Sejour sejour, String filePath) {
        try {
            Document document = new Document(PageSize.A4);
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();

            // En-tête
            Font titleFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
            Font normalFont = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL);
            Font headerFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);

            Paragraph title = new Paragraph("Facture", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            title.setSpacingAfter(20);
            document.add(title);

            // Informations du séjour
            document.add(new Paragraph("Informations du séjour:", headerFont));
            document.add(new Paragraph("Client: " + sejour.getClient().getNom() + " " + sejour.getClient().getPrenom(), normalFont));
            document.add(new Paragraph("Chambre: " + sejour.getChambre().getNumero(), normalFont));
            document.add(new Paragraph("Date début: " + formatDate(sejour.getDateDebut()), normalFont));
            document.add(new Paragraph("Date fin: " + formatDate(sejour.getDateFin()), normalFont));
            document.add(Chunk.NEWLINE);

            // Tableau des consommations
            PdfPTable table = new PdfPTable(3); // 3 colonnes
            table.setWidthPercentage(100);
            table.setSpacingBefore(10f);
            table.setSpacingAfter(10f);

            // Définition des largeurs relatives des colonnes
            float[] columnWidths = {20f, 40f, 40f};
            table.setWidths(columnWidths);

            // En-têtes du tableau
            addTableHeader(table);

            // Contenu du tableau
            double total = 0;
            for (Consommation conso : SejourController.getConsommations(sejour)) {
                PdfPCell quantiteCell = new PdfPCell(new Phrase(String.valueOf(conso.getQuantite())));
                quantiteCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(quantiteCell);

                PdfPCell prixCell = new PdfPCell(new Phrase(String.format("%.2f €", (float) conso.getProduit().getPrix())));
                prixCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.addCell(prixCell);

                double montant = conso.getQuantite() * conso.getProduit().getPrix();
                PdfPCell montantCell = new PdfPCell(new Phrase(String.format("%.2f €", montant)));
                montantCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.addCell(montantCell);

                total += montant;
            }

            document.add(table);

            // Total
            Paragraph totalParagraph = new Paragraph("Total: " + String.format("%.2f €", total), headerFont);
            totalParagraph.setAlignment(Element.ALIGN_RIGHT);
            document.add(totalParagraph);

            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void addTableHeader(PdfPTable table) {
        Font headerFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
        String[] headers = {"Quantité", "Prix unitaire", "Montant"};
        for (String header : headers) {
            PdfPCell cell = new PdfPCell(new Phrase(header, headerFont));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell.setPadding(5f);
            table.addCell(cell);
        }
    }

    private static String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(date);
    }
}