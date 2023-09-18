package com.eshop.productcommandservice.controller;

import com.eshop.productcommandservice.facade.ExcelFacade;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.util.IOUtils;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/excel")
public class ExcelController {
    private final ExcelFacade excelFacade;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadExcelFile(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        try {
            excelFacade.processExcelFile(file.getInputStream(), request);
            return ResponseEntity.ok("File uploaded and processed successfully.");
        } catch (Exception e) {
            log.error("Error processing Excel file.", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing Excel file.");
        }
    }
}
