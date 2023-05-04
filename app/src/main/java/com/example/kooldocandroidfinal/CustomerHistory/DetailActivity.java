package com.example.kooldocandroidfinal.CustomerHistory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kooldocandroidfinal.EmployeeHistory.ServHistoryActivity;
import com.example.kooldocandroidfinal.R;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BarcodeQRCode;
import com.itextpdf.text.pdf.PdfFormXObject;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.qrcode.QRCode;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DetailActivity extends AppCompatActivity {


    TextView tvid,tvBkType,tvServType,tvAcType,tvAcBrand,tvUtType,tvNoUnit,tvServDate,tvServTime,tvServPrice,tvAdminLname,tvTechLname;
    ImageView goBack;
    Button downloadPDF;
    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        tvid= findViewById(R.id.acID);
        tvBkType= findViewById(R.id.bkType);
        tvServType= findViewById(R.id.servType);
        tvAcType= findViewById(R.id.acType);
        tvAcBrand= findViewById(R.id.acBrand);
        tvUtType= findViewById(R.id.utType);
        tvNoUnit= findViewById(R.id.noUnit);
        tvServDate= findViewById(R.id.servDate);
        tvServTime= findViewById(R.id.servTime);
        tvServPrice= findViewById(R.id.servPrice);
        tvAdminLname= findViewById(R.id.adminLastname);
        tvTechLname= findViewById(R.id.techLastname);
        goBack = findViewById(R.id.btn_back);
        downloadPDF= findViewById(R.id.btn_downloadPDF);

        Intent intent = getIntent();
        position = intent.getExtras().getInt("position");

        tvid.setText("ID: " + HistoryActivity.CostumerArrayList.get(position).getId());
        tvBkType.setText("Book Type: " + HistoryActivity.CostumerArrayList.get(position).getBookType());
        tvServType.setText("Service Type: " + HistoryActivity.CostumerArrayList.get(position).getServiceType());
        tvAcType.setText("Aircon Type: " + HistoryActivity.CostumerArrayList.get(position).getAcType());
        tvAcBrand.setText("Aircon Brand: " + HistoryActivity.CostumerArrayList.get(position).getAcBrand());
        tvUtType.setText("Unit Type: " + HistoryActivity.CostumerArrayList.get(position).getUnitType());
        tvNoUnit.setText("Number of Unit: " + HistoryActivity.CostumerArrayList.get(position).getNoUnit());
        tvServDate.setText("Service Date: " + HistoryActivity.CostumerArrayList.get(position).getServiceDate());
        tvServTime.setText("Service Time: " + HistoryActivity.CostumerArrayList.get(position).getServiceTime());
        tvServPrice.setText("Service Price: " + HistoryActivity.CostumerArrayList.get(position).getServicePrice());
        tvAdminLname.setText("Admin Last name: " + HistoryActivity.CostumerArrayList.get(position).getAdminLastName());
        tvTechLname.setText("Tech Last name: " + HistoryActivity.CostumerArrayList.get(position).getTechLastname());


        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        downloadPDF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createPDF();
            }
        });


    }

    private void createPDF() {

        String text = "THE TEXT";

        // Create a new PDF document
        Document document = new Document();
        try {
            // Create a new file for the PDF
            File file = new File(getExternalFilesDir(null), "KooldocService.pdf");
            FileOutputStream outputStream = new FileOutputStream(file);
            int bottomMargin = 20;
            LocalDateTime currentDateTime = LocalDateTime.now();
            // Format the date and time string
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDateTime = currentDateTime.format(formatter);

            //--MALI PA TONG FULL NAME PALITAN MO SA CUSTOMER ADD DATA KA SA API--//
            String passFullname = ServHistoryActivity.ServiceArrayList.get(position).getCustomerFullname();
            String passServiceType = ServHistoryActivity.ServiceArrayList.get(position).getServiceType();
            String passAirconType = ServHistoryActivity.ServiceArrayList.get(position).getAcType();
            String passServiceDate = ServHistoryActivity.ServiceArrayList.get(position).getServiceDate();
            String passServiceTime = ServHistoryActivity.ServiceArrayList.get(position).getServiceTime();
            String passNumberofUnit = ServHistoryActivity.ServiceArrayList.get(position).getNoUnit();
            String passFinalPrice = ServHistoryActivity.ServiceArrayList.get(position).getServicePrice();


            // Create a new PDF writer with the output stream
            PdfWriter.getInstance(document, outputStream);
            Font headerFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
            Font subHeadFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
            // Open the document
            document.open();

            Paragraph header = new Paragraph("KOOLDOC AIRCONDITIONING", headerFont);
            header.setAlignment(Element.ALIGN_CENTER);
            header.setSpacingAfter(bottomMargin);

            Paragraph subheader = new Paragraph("18 Manalili st Zone 2 Central Signal, Taguig City", subHeadFont);
            subheader.setAlignment(Element.ALIGN_CENTER);
            subheader.setSpacingAfter(bottomMargin);

            Paragraph DateTimeHead = new Paragraph("Date and Time", subHeadFont);
            DateTimeHead.setAlignment(Element.ALIGN_RIGHT);

            Paragraph DateTime = new Paragraph(formattedDateTime, subHeadFont);
            DateTime.setAlignment(Element.ALIGN_RIGHT);
            DateTime.setSpacingAfter(20);


            //TABLE ROWS
            PdfPTable firstRow = new PdfPTable(2);
            PdfPTable secondRow = new PdfPTable(2);
            PdfPTable thirdRow = new PdfPTable(2);
            PdfPTable fourthRow = new PdfPTable(2);
            PdfPTable fifthRow = new PdfPTable(2);
            PdfPTable sixthRow = new PdfPTable(2);
            PdfPTable seventhRow = new PdfPTable(2);
            PdfPTable eighthRow = new PdfPTable(2);
            PdfPTable ninethRow = new PdfPTable(2);


            //------------------------FIRST ROW---------------------------//
            PdfPCell Onecell = new PdfPCell(new Phrase("FULL NAME"));
            Onecell.setMinimumHeight(50f);
            PdfPCell Onecell2 = new PdfPCell(new Phrase(passFullname));
            Onecell2.setMinimumHeight(50f);

            //------------------------SECOND ROW---------------------------//
            PdfPCell Twocell = new PdfPCell(new Phrase("SERVICE ADDRESS"));
            Onecell.setMinimumHeight(50f);
            PdfPCell Twocell2 = new PdfPCell(new Phrase("18 Manalili st Zone 2 Central Signal, Taguig City"));
            Onecell2.setMinimumHeight(50f);

            //------------------------THIRD ROW---------------------------//
            PdfPCell Threecell = new PdfPCell(new Phrase("SERVICE TYPE"));
            Onecell.setMinimumHeight(50f);
            PdfPCell Threecell2 = new PdfPCell(new Phrase(passServiceType));
            Onecell2.setMinimumHeight(50f);

            //------------------------FOURTH ROW---------------------------//
            PdfPCell Fourcell = new PdfPCell(new Phrase("AIRCON TYPE"));
            Onecell.setMinimumHeight(50f);
            PdfPCell Fourcell2 = new PdfPCell(new Phrase(passAirconType));
            Onecell2.setMinimumHeight(50f);

            //------------------------FIFTH ROW---------------------------//
            PdfPCell Fivecell = new PdfPCell(new Phrase("SERVICE DATE"));
            Onecell.setMinimumHeight(50f);
            PdfPCell Fivecell2 = new PdfPCell(new Phrase(passServiceDate));
            Onecell2.setMinimumHeight(50f);

            //------------------------SIXTH ROW---------------------------//
            PdfPCell Sixcell = new PdfPCell(new Phrase("SERVICE TIME"));
            Onecell.setMinimumHeight(50f);
            PdfPCell Sixcell2 = new PdfPCell(new Phrase(passServiceTime));
            Onecell2.setMinimumHeight(50f);

            //------------------------SEVENTH ROW---------------------------//
            PdfPCell Sevencell = new PdfPCell(new Phrase("NUMBER OF UNIT"));
            Onecell.setMinimumHeight(50f);
            PdfPCell Sevencell2 = new PdfPCell(new Phrase(passNumberofUnit));
            Onecell2.setMinimumHeight(50f);

            //------------------------EIGHTH ROW---------------------------//
            PdfPCell Eightcell = new PdfPCell(new Phrase("FINAL PRICE"));
            Onecell.setMinimumHeight(50f);
            PdfPCell Eightcell2 = new PdfPCell(new Phrase(passFinalPrice));
            Onecell2.setMinimumHeight(50f);

            //------------------------NINETH ROW---------------------------//
            PdfPCell Ninecell = new PdfPCell(new Phrase("PAYMENT METHOD"));
            Onecell.setMinimumHeight(50f);
            PdfPCell Ninecell2 = new PdfPCell(new Phrase("Cash on Service"));
            Onecell2.setMinimumHeight(50f);



            //------------------------------//
            firstRow.addCell(Onecell);
            firstRow.addCell(Onecell2);
            //------------------------------//
            secondRow.addCell(Twocell);
            secondRow.addCell(Twocell2);
            //------------------------------//
            thirdRow.addCell(Threecell);
            thirdRow.addCell(Threecell2);
            //------------------------------//
            fourthRow.addCell(Fourcell);
            fourthRow.addCell(Fourcell2);
            //------------------------------//
            fifthRow.addCell(Fivecell);
            fifthRow.addCell(Fivecell2);
            //------------------------------//
            sixthRow.addCell(Sixcell);
            sixthRow.addCell(Sixcell2);
            //------------------------------//
            seventhRow.addCell(Sevencell);
            seventhRow.addCell(Sevencell2);
            //------------------------------//
            eighthRow.addCell(Eightcell);
            eighthRow.addCell(Eightcell2);
            //------------------------------//
            ninethRow.addCell(Ninecell);
            ninethRow.addCell(Ninecell2);
            //------------------------------//

            Drawable d = getDrawable(R.drawable.qrkooldoc);
            Bitmap bitmap = ((BitmapDrawable)d).getBitmap();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
            byte[] bitmapData= stream.toByteArray();


            // Add the text to the document
            document.add(header);
            document.add(subheader);
            document.add(DateTimeHead);
            document.add(DateTime);

            //-----TABLE AND DATA------//
            document.add(firstRow);
            document.add(secondRow);
            document.add(thirdRow);
            document.add(fourthRow);
            document.add(fifthRow);
            document.add(sixthRow);
            document.add(seventhRow);
            document.add(eighthRow);
            document.add(ninethRow);





            // Close the document
            document.close();

            // Launch an intent to view the PDF file
            Intent intent = new Intent(Intent.ACTION_VIEW);
            Uri uri = FileProvider.getUriForFile(this, getApplicationContext().getPackageName() + ".fileprovider", file);
            intent.setDataAndType(uri, "application/pdf");
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}