package com.eshop.productservice.facade.impl;

import com.eshop.productservice.facade.ExcelFacade;
import com.eshop.productservice.facade.ProductFacade;
import com.eshop.productservice.models.dto.ProductCategoryDto;
import com.eshop.productservice.models.dto.ProductRequest;
import com.eshop.productservice.models.dto.ProductSpecDto;
import com.eshop.productservice.models.dto.specDtos.BodyDto;
import com.eshop.productservice.models.dto.specDtos.BootsDto;
import com.eshop.productservice.models.dto.specDtos.HeadDto;
import com.eshop.productservice.models.dto.specDtos.PantsDto;
import com.eshop.productservice.service.ExcelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Iterator;

@Service
@Slf4j
@RequiredArgsConstructor
public class ExcelFacadeImpl implements ExcelFacade {
    private final ProductFacade productFacade;
    private final ExcelService excelService;
    @Override
    public void processExcelFile(InputStream excelFileStream) {
        try (Workbook workbook = WorkbookFactory.create(excelFileStream)) {
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();

            // Skip header row
            if (rowIterator.hasNext()) {
                rowIterator.next();
            }

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                ProductRequest productRequest = createProductRequestFromRow(row);
                productFacade.createProduct(productRequest);
            }
        } catch (Exception e) {
            log.error("Error processing Excel file.", e);
            throw new RuntimeException("Error processing Excel file.", e);
        }
    }

    private ProductRequest createProductRequestFromRow(Row row) {
        String name = getCellValue(row.getCell(0));
        String code = getCellValue(row.getCell(1));
        String description = getCellValue(row.getCell(2));
        ProductCategoryDto category = new ProductCategoryDto(
                null,
                getCellValue(row.getCell(3)),
                null
        );
        String specType = getCellValue(row.getCell(4));
        ProductSpecDto spec = createProductSpecDto(specType, row);
        BigDecimal price = new BigDecimal(getCellValue(row.getCell(12)));
        Integer quantity = Integer.parseInt(getCellValue(row.getCell(13)));

        return new ProductRequest(
                name,
                code,
                description,
                category,
                spec,
                price,
                quantity
        );
    }

    private String getCellValue(Cell cell) {
        if (cell == null) {
            return "";
        }
        return cell.getStringCellValue();
    }

    private ProductSpecDto createProductSpecDto(String specType, Row row) {
        return switch (specType) {
            case "BODY" -> BodyDto.builder()
                    .size(getCellValue(row.getCell(5)))
                    .color(getCellValue(row.getCell(6)))
                    .sex(getCellValue(row.getCell(7)))
                    .sleeve(getCellValue(row.getCell(8)))
                    .build();
            case "BOOTS" -> BootsDto.builder()
                    .size(getCellValue(row.getCell(5)))
                    .color(getCellValue(row.getCell(6)))
                    .sex(getCellValue(row.getCell(7)))
                    .liftingHeight(Long.parseLong(getCellValue(row.getCell(9))))
                    .build();
            case "HEAD" -> HeadDto.builder()
                    .size(getCellValue(row.getCell(5)))
                    .color(getCellValue(row.getCell(6)))
                    .sex(getCellValue(row.getCell(7)))
                    .headGirth(Long.parseLong(getCellValue(row.getCell(10))))
                    .build();
            case "PANTS" -> PantsDto.builder()
                    .size(getCellValue(row.getCell(5)))
                    .color(getCellValue(row.getCell(6)))
                    .sex(getCellValue(row.getCell(7)))
                    .pantLength(Long.parseLong(getCellValue(row.getCell(11))))
                    .build();
            default -> throw new IllegalArgumentException("Invalid spec type: " + specType);
        };
    }

    @Override
    public InputStream generateEmptyTemplate() {
        return excelService.generateEmptyTemplate();
    }
}
