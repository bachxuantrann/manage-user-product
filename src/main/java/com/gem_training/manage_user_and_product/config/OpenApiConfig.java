package com.gem_training.manage_user_and_product.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("User,Product Management API")
                        .version("1.0.0")
                        .description("API phục vụ quản lý sản phẩm và người dùng")
                        .contact(new Contact()
                                .name("Bach Xuan Tran")
                                .email("bxt203@gmail.com")));
    }
}
