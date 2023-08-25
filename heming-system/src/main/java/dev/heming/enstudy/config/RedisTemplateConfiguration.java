package dev.heming.enstudy.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @Description redis 配置
 * @Author Bess Croft
 * @Date 2023/8/24 23:01
 */
@Configuration(proxyBeanMethods = false)
@AutoConfigureBefore(RedisAutoConfiguration.class)
public class RedisTemplateConfiguration {

    /**
     * 这里 RedisConnectionFactory 初始化时默认加载的就是 LettuceConnectionFactory
     * @param redisConnectionFactory redis 连接工厂
     * @return RedisTemplate
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();

        // 用 Jackson2JsonRedisSerializer 来序列化和反序列化 redis 的 value 值
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        // key
        redisTemplate.setKeySerializer(stringRedisSerializer);
        ObjectMapper objectMapper = new ObjectMapper();
        // 指定要序列化的域(field,get,set)，访问修饰符(public,private,protected)
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL);
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(objectMapper, Object.class);

        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer); //value
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

}
