package com.revature.p2backend.beans.controllers;
import com.revature.p2backend.beans.services.ProductService;
import com.revature.p2backend.entities.Category;
import com.revature.p2backend.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static com.revature.p2backend.entities.Category.NECKLACES;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.OK)
    public Product persistNewProduct(@Valid @RequestBody Product newProduct) {
        return productService.save(newProduct);
    }

    //get all products
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getProdcutByPriceRange(@RequestParam("lower") String lower,
                                                @RequestParam("upper") String upper){
        return productService.getByPriceRange(Double.parseDouble(lower),Double.parseDouble(upper));
    }

    @GetMapping("/search/cat")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getProductByCategory(@RequestParam ("category") Category category){
        return productService.getByProductCategory(category);
    }

    @GetMapping("/search/name")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getByProductName(@RequestParam("product_name") String name,@RequestHeader("mode") String mode) throws Exception{
        switch(mode){
            case "exact-search":
                return productService.getByProductName(name);
            case "partial-search":
                return productService.getByProductNamePartialMatch(name);
            default:   throw new Exception("That's not a valid mode");
        }
    }

    @GetMapping("/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Product getByProductId(@PathVariable String id){
        return productService.getByProductId(Integer.parseInt(id));
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public Product updateProduct(@RequestBody Product product) {
        return productService.update(product);
    }

    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProduct(@RequestBody Product product) {
       productService.delete(product);
    }

}





