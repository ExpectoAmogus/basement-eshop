package com.eshop.productqueryservice.controller;

import com.eshop.productqueryservice.facade.ExcelFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.util.IOUtils;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/excel")
public class ExcelController {
    private final ExcelFacade excelFacade;

    @GetMapping("/template")
    public ResponseEntity<Resource> downloadEmptyTemplate() throws IOException {
        InputStream templateStream = excelFacade.generateEmptyTemplate();
        ByteArrayResource resource = new ByteArrayResource(IOUtils.toByteArray(templateStream));

        return ResponseEntity.ok()
                .contentLength(resource.contentLength())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
}
