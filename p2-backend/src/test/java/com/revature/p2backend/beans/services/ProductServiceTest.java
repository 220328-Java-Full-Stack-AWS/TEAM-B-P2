package com.revature.p2backend.beans.services;

import com.revature.p2backend.beans.dao.ProductDao;

import com.revature.p2backend.entities.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.mockito.Mockito.when;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
    @MockBean
    ProductDao productDao;



    @Test
    public void saveTest(@Autowired ProductService productService){
        //arrange
        Product product = new Product("jeans test", "blue and shiny",99.99,15);
        Product productReturn = new Product("jeans test", "blue and shiny",99.99,15);

        when(productDao.save(product)).thenReturn(productReturn);


        //act
        Product productResult = productService.save(product);

        //make sure what is returned in productResult is equal to what we wanted the method to produce in productReturn
        //assert
        Assertions.assertEquals(productReturn, productResult);
    }

    @Test
    public void getProductById(@Autowired ProductService productService) throws Exception{
        //arrange:we want to arrange the parameter and return
        // with this we are making the parameter
        Integer id = 1;
        //with this we are making the return
        Product productReturn = new Product("jeans test", "blue and shiny",99.99,15);
        //with this we say when the mock object calls its method return what we want you to return
        //which in this case is addressReturn
        when(productDao.getById(id)).thenReturn(productReturn);

        //act
        //With this we are calling the function and it is returning returnedAddress
        //we wanted the return to be addressReturn
        Product productResult = productService.getByProductId(id);
        //assert: now we assert that what is returned by the function(returnedAddress)
        //is equal to what we wanted the method to return(addressReturn)
        Assertions.assertEquals(productReturn, productResult);
    }

    @Test
    public void updateTest(@Autowired ProductService productService) throws Exception{
        //arrange:we want to arrange the parameter and return
        // with this we are making the parameter
        Product product = new Product("jeans test", "blue and shiny",99.99,15);
        //with this we are making the return
        Product productReturn = new Product("jeans test", "blue and shiny",99.99,15);
        //with this we say when the mock object calls its method return what we want you to return
        //which in this case is addressReturn
        when(productDao.update(product)).thenReturn(productReturn);

        //act
        //With this we are calling the function and it is returning returnedAddress
        //we wanted the return to be addressReturn
        Product productResult = productService.update(product);
        //assert: now we assert that what is returned by the function(returnedAddress)
        //is equal to what we wanted the method to return(addressReturn)
        Assertions.assertEquals(productReturn, productResult);
    }


    @Test
    public void deleteTest(@Autowired ProductService productService) throws Exception{
        //arrange:we want to arrange the parameter and return
        // with this we are making the parameter
        Product product = new Product("jeans test", "blue and shiny",99.99,15);
        //with this we are making the return
        Product productReturn = new Product("jeans test", "blue and shiny",99.99,15);
        //with this we say when the mock object calls its method return what we want you to return
        //which in this case is addressReturn
        when(productDao.delete(product)).thenReturn(productReturn);

        //act
        //With this we are calling the function and it is returning returnedAddress
        //we wanted the return to be addressReturn
        Product productResult = productService.delete(product);;
        //assert: now we assert that what is returned by the function(returnedAddress)
        //is equal to what we wanted the method to return(addressReturn)
        Assertions.assertEquals(productReturn.toString(), productResult.toString());
    }


}
