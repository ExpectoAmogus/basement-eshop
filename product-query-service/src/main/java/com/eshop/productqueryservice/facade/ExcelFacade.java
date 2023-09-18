package com.eshop.productqueryservice.facade;

import jakarta.servlet.http.HttpServletRequest;

import java.io.InputStream;

public interface ExcelFacade {
    InputStream generateEmptyTemplate();
}
