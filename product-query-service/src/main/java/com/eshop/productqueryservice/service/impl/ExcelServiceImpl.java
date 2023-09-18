package com.eshop.productqueryservice.service.impl;

import com.eshop.productqueryservice.service.ExcelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

@Service
@Slf4j
@RequiredArgsConstructor
public class ExcelServiceImpl implements ExcelService {

    @Override
    public InputStream generateEmptyTemplate() {
        try (Workbook workbook = WorkbookFactory.create(true)) {
            Sheet sheet = workbook.createSheet("Products");

            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Name");
            headerRow.createCell(1).setCellValue("Code");
            headerRow.createCell(2).setCellValue("Description");
            headerRow.createCell(3).setCellValue("Category");
            headerRow.createCell(4).setCellValue("SpecType");
            headerRow.createCell(5).setCellValue("Size");
            headerRow.createCell(6).setCellValue("Color");
            headerRow.createCell(7).setCellValue("Sex");
            headerRow.createCell(8).setCellValue("Sleeve");
            headerRow.createCell(9).setCellValue("Lifting Height");
            headerRow.createCell(10).setCellValue("Head Girth");
            headerRow.createCell(11).setCellValue("Pant Length");
            headerRow.createCell(12).setCellValue("Price");
            headerRow.createCell(13).setCellValue("Quantity");

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            return new ByteArrayInputStream(outputStream.toByteArray());
        } catch (Exception e) {
            log.error("Error generating empty template.", e);
            throw new RuntimeException("Error generating empty template.", e);
        }
    }
}
