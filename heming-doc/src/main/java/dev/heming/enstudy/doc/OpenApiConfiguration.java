package dev.heming.enstudy.doc;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description 文档配置
 * @Author Bess Croft
 * @Date 2023/8/23 22:43
 */
@Configuration
public class OpenApiConfiguration {

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("嗯学英语")
                        .description("学英语，就嗯学！")
                        .version("v0.2.5")
                        .license(new License()
                                .name("MIT license")
                                .url("https://github.com/besscroft/enstudy/blob/main/LICENSE")
                        )
                );
    }

}
