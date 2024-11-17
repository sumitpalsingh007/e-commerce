package com.helloworldtechconsulting.web;

import com.helloworldtechconsulting.entity.Product;
import com.helloworldtechconsulting.service.Estimation;
import com.helloworldtechconsulting.service.ProductEstimator;
import com.helloworldtechconsulting.service.ProductService;
import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.Metadata;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {

    private final ProductEstimator productEstimator;
    private final EmbeddingStoreIngestor ingestor;
    private final ProductService productService;


    @PostMapping
    public ResponseEntity<?> save(@RequestBody Product product){
        Estimation estimate = productEstimator.chat(product.getName(), product.getDescription());
        System.out.println(product.getName() + " : " + estimate.orders + " : " + estimate.reason);
        product.setOrderEstimate(Integer.valueOf(estimate.orders));
        Long id = productService.save(product);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("{\"id\":\"" + id+ "\"}");
    }

    @GetMapping
    public ResponseEntity<?> find(@RequestBody String productName){
        var product = productService.searchByProductName(productName);
        Document document = new Document(product.getName(), Metadata.metadata("product searched", "1"));
        ingestor.ingest(document);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(product);
    }
}
