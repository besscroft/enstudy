package dev.heming.enstudy;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@Slf4j
@EnableCaching
@MapperScan("dev.heming.enstudy.mapper")
@SpringBootApplication(scanBasePackages = "dev.heming.enstudy")
public class HemingSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(HemingSystemApplication.class, args);
        log.info("(♥◠‿◠)ﾉﾞ  嗯学英语服务启动成功   ლ(´ڡ`ლ)ﾞ  \n");
    }

}
