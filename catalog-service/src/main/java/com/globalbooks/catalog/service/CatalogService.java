package com.globalbooks.catalog.service;

import com.globalbooks.catalog.model.Book;

import javax.jws.HandlerChain;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
@HandlerChain(file = "handlers.xml")
public interface CatalogService {
    
    @WebMethod(operationName = "getBookById")
    Book getBookById(@WebParam(name = "bookId") String bookId);

    @WebMethod(operationName = "getBookPrice")
    double getBookPrice(@WebParam(name = "bookId") String bookId);
}