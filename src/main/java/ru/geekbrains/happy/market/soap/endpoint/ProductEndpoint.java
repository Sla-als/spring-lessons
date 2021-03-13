package ru.geekbrains.happy.market.soap.endpoint;

import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.geekbrains.happy.market.services.ProductService;
import ru.geekbrains.happy.market.soap.GetAllProductsRequest;
import ru.geekbrains.happy.market.soap.GetAllProductsResponse;

@Endpoint
@RequiredArgsConstructor
public class ProductEndpoint {
    private static final String NAMESPACE_URI = "http://www.geekbrains.ru/spring/ws/products";
    private final ProductService productService;

//    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getStudentByNameRequest")
//    @ResponsePayload
//    public GetProductByTitleResponse getStudentByName(@RequestPayload GetProductByTitleRequest request) {
//        GetStudentByNameResponse response = new GetStudentByNameResponse();
//        response.setStudent(studentService.getByName(request.getName()));
//        return response;
//    }

    /*
        Пример запроса: POST http://localhost:8080/ws

         <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:f="http://www.geekbrains.ru/api/products>
            <soapenv:Header/>
            <soapenv:Body>
                <f:getAllProductsRequest/>
            </soapenv:Body>
        </soapenv:Envelope>
     */

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllProductsRequest")
    @ResponsePayload
    public GetAllProductsResponse getAllStudents(@RequestPayload GetAllProductsRequest request) {
        GetAllProductsResponse response = new GetAllProductsResponse();
        productService.findAll().forEach(response.getProducts()::add);
        return response;
    }
}
