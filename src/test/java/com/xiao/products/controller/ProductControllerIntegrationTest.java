package com.xiao.products.controller;
import com.xiao.products.dto.ProductDto;
import com.xiao.products.entity.Product;
import com.xiao.products.mapper.ProductMapper;
import com.xiao.products.repository.ProductRepository;
import com.xiao.products.util.JsonUtil;
import com.xiao.products.util.ProductUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    @BeforeEach
    public void tearDown(){
        productRepository.deleteAll();
    }

    @Test
    void testCreateProduct_201() throws Exception {
        ProductDto productDto = ProductUtil.buildProductDto();

        mockMvc.perform(MockMvcRequestBuilders.post("/productss")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(JsonUtil.asJsonString(productDto))
                    .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.statusCode").value("201"))
                .andExpect(jsonPath("$.statusMsg").value("Product created successfully"));
    }

    @Test
    void testCreateAccount_400() throws Exception {
        ProductDto productDto = ProductUtil.buildProductDto();
        productDto.setName(null);

        mockMvc.perform(MockMvcRequestBuilders.post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.asJsonString(productDto))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Your Method Argument Is Not Valid"))
                .andExpect(jsonPath("$.title").value("VALIDATION ERROR"))
                .andExpect(jsonPath("$.errors.name").value("Name can't be empty"));
    }

    @Test
    void testCreateAccount_415() throws Exception {
        ProductDto productDto = ProductUtil.buildProductDto();

        mockMvc.perform(MockMvcRequestBuilders.post("/products")
                        .contentType(MediaType.APPLICATION_CBOR)
                        .content(JsonUtil.asJsonString(productDto))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(415));
    }

    @Test
    void testFindUserById_200() throws Exception {
        ProductDto userDto = ProductUtil.buildProductDto();

        Product user = productRepository.save(productMapper.productDtoToProduct(userDto));

        mockMvc.perform(MockMvcRequestBuilders.get("/products/{id}", user.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(user.getId()));
    }

    @Test
    void testFindUserById_404() throws Exception {
        Long productId = 999L;

        mockMvc.perform(MockMvcRequestBuilders.get("/products/{id}", productId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code").value("NOT_FOUND"))
                .andExpect(jsonPath("$.title").value("Resource Not Found Error"))
                .andExpect(jsonPath("$.message").value(String.format("Product not found with the given input data id : '%s'", productId)));
    }

    @Test
    void testFindAllUser_200() throws Exception {
        ProductDto productDto = ProductUtil.buildProductDto();
        ProductDto productDto1 = ProductUtil.buildProductDto();

        Product product = productRepository.save(productMapper.productDtoToProduct(productDto));
        Product product1 = productRepository.save(productMapper.productDtoToProduct(productDto1));

        mockMvc.perform(MockMvcRequestBuilders.get("/products").param("pages", "0").param("pageSize", "10")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(2)))
                .andExpect(jsonPath("$.totalElements", is(2)))
                .andExpect(jsonPath("$.totalPages", is(1)))
                .andExpect(jsonPath("$.pageable.pageNumber", is(0)))
                .andExpect(jsonPath("$.pageable.pageSize", is(10)))
                .andExpect(jsonPath("$.content[0].name", is(product.getName())))
                .andExpect(jsonPath("$.content[0].description", is(product.getDescription())))
                .andExpect(jsonPath("$.content[1].name", is(product1.getName())))
                .andExpect(jsonPath("$.content[1].description", is(product1.getDescription())));
    }

    @Test
    void testFindAllUser_200_withPageSizeIs1() throws Exception {

        Product productPage1 = productRepository.save(ProductUtil.buildProduct());
        Product productPage2 = productRepository.save(ProductUtil.buildProduct());

        mockMvc.perform(MockMvcRequestBuilders.get("/products").param("pages", "0").param("pageSize", "1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(1)))
                .andExpect(jsonPath("$.totalElements", is(2)))
                .andExpect(jsonPath("$.totalPages", is(2)))
                .andExpect(jsonPath("$.pageable.pageNumber", is(0)))
                .andExpect(jsonPath("$.pageable.pageSize", is(1)))
                .andExpect(jsonPath("$.content[0].name", is(productPage1.getName())))
                .andExpect(jsonPath("$.content[0].description", is(productPage1.getDescription())));

        mockMvc.perform(MockMvcRequestBuilders.get("/products").param("pages", "1").param("pageSize", "1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(1)))
                .andExpect(jsonPath("$.totalElements", is(2)))
                .andExpect(jsonPath("$.totalPages", is(2)))
                .andExpect(jsonPath("$.pageable.pageNumber", is(1)))
                .andExpect(jsonPath("$.pageable.pageSize", is(1)))
                .andExpect(jsonPath("$.content[0].name", is(productPage2.getName())))
                .andExpect(jsonPath("$.content[0].description", is(productPage2.getDescription())));
    }

    @Test
    void testFindAllUser_whenMissingParams() throws Exception {
        ProductDto userDto1 = ProductUtil.buildProductDto();
        ProductDto userDto2 = ProductUtil.buildProductDto();

        Product product1 = productRepository.save(productMapper.productDtoToProduct(userDto1));
        Product product2 = productRepository.save(productMapper.productDtoToProduct(userDto2));

        mockMvc.perform(MockMvcRequestBuilders.get("/products")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(2)))
                .andExpect(jsonPath("$.totalElements", is(2)))
                .andExpect(jsonPath("$.totalPages", is(1)))
                .andExpect(jsonPath("$.pageable.pageNumber", is(0)))
                .andExpect(jsonPath("$.pageable.pageSize", is(10)))
                .andExpect(jsonPath("$.content[0].name", is(product1.getName())))
                .andExpect(jsonPath("$.content[0].description", is(product1.getDescription())))
                .andExpect(jsonPath("$.content[1].name", is(product2.getName())))
                .andExpect(jsonPath("$.content[1].description", is(product2.getDescription())));
    }

    @Test
    void testFindAllUser_whenEmptyUserList() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/products")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(0)))
                .andExpect(jsonPath("$.totalElements", is(0)))
                .andExpect(jsonPath("$.totalPages", is(0)))
                .andExpect(jsonPath("$.pageable.pageNumber", is(0)))
                .andExpect(jsonPath("$.pageable.pageSize", is(10)));

    }

    @Test
    void testUpdateUser_201() throws Exception {
        Product productSaved = productRepository.save(ProductUtil.buildProduct());
        ProductDto productUpdate = ProductUtil.buildProductDto();

        mockMvc.perform(MockMvcRequestBuilders.put("/products/{id}", productSaved.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.asJsonString(productUpdate))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(productSaved.getId()))
                .andExpect(jsonPath("$.name").value(productUpdate.getName()))
                .andExpect(jsonPath("$.description").value(productUpdate.getDescription()));
    }

    @Test
    void testUpdateProduct_201_whenEmptyTwoField() throws Exception {
        Product productSaved = productRepository.save(ProductUtil.buildProduct());
        ProductDto productUpdate = ProductUtil.buildProductUpdateDto();
        productUpdate.setName(null);
        productUpdate.setDescription(null);

        mockMvc.perform(MockMvcRequestBuilders.put("/products/{id}", productSaved.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.asJsonString(productUpdate))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(productSaved.getId()))
                .andExpect(jsonPath("$.name").value(productUpdate.getName()))
                .andExpect(jsonPath("$.description").value(productSaved.getDescription()));
    }

    @Test
    void testUpdateProduct_400_EmptyAllField() throws Exception {
        Product productSaved = productRepository.save(ProductUtil.buildProduct());
        ProductDto productUpdate = ProductUtil.buildProductUpdateDto();
        productUpdate.setName(null);
        productUpdate.setDescription(null);
        productUpdate.setLeftInStock(null);

        mockMvc.perform(MockMvcRequestBuilders.put("/products/{id}", productSaved.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.asJsonString(productUpdate))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors.productUpdateDto").value("Please enter at least one piece when updating the user"));
    }

    @Test
    void testUpdateProduct_415() throws Exception {
        Product productSaved = productRepository.save(ProductUtil.buildProduct());
        ProductDto productUpdate = ProductUtil.buildProductUpdateDto();
        productUpdate.setName("Iphone 17 pro mac");

        mockMvc.perform(MockMvcRequestBuilders.put("/products/{id}", productSaved.getId())
                        .contentType(MediaType.APPLICATION_CBOR)
                        .content(JsonUtil.asJsonString(productUpdate))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(415));
    }

    @Test
    void testUpdateProduct_whenNotFoundProductId() throws Exception {
        Long productId = 999L;
        ProductDto productUpdate = ProductUtil.buildProductUpdateDto();

        mockMvc.perform(MockMvcRequestBuilders.put("/products/{id}", productId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.asJsonString(productUpdate))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.title").value("Resource Not Found Error"))
                .andExpect(jsonPath("$.code").value("NOT_FOUND"))
                .andExpect(jsonPath("$.message").value(String.format("Product not found with the given input data id : '%s'", productId)));
    }

    @Test
    void testDeleteProduct_200() throws Exception {
        Product product = ProductUtil.buildProduct();
        product.setId(55L);
        productRepository.save(product);

        mockMvc.perform(MockMvcRequestBuilders.delete("/products/{id}", product.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    void testDeleteProduct_whenNotFoundProductId() throws Exception {
        Long productId = 69L;

        mockMvc.perform(MockMvcRequestBuilders.delete("/products/{id}", productId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.title").value("Resource Not Found Error"))
                .andExpect(jsonPath("$.code").value("NOT_FOUND"))
                .andExpect(jsonPath("$.message").value(String.format("Product not found with the given input data id : '%s'", productId)));
    }
}
