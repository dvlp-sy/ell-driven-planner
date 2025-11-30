//package app.config;
//
//import app.statistics.model.TaskMemento;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
//import org.springframework.data.redis.serializer.StringRedisSerializer;
//
//@Configuration
//public class RedisConfig {
//
//    @Value("${redis.host}")
//    private String redisHost;
//
//    @Value("${redis.port}")
//    private int redisPort;
//
//    @Bean("redisConnectionFactory")
//    public RedisConnectionFactory redisConnectionFactory() {
//        return new LettuceConnectionFactory(redisHost, redisPort);
//    }
//
//    @Bean("redisTemplate")
//    public RedisTemplate<String, Object> redisTemplate() {
//        RedisTemplate<String, Object> template = new RedisTemplate<>();
//        template.setConnectionFactory(redisConnectionFactory());
//        template.setKeySerializer(new StringRedisSerializer());
//        template.setValueSerializer(new GenericJackson2JsonRedisSerializer(redisObjectMapper()));
//        return template;
//    }
//
//    @Bean("taskZSetTemplate")
//    public RedisTemplate<String, TaskMemento> taskZSetTemplate() {
//        RedisTemplate<String, TaskMemento> template = new RedisTemplate<>();
//        template.setConnectionFactory(redisConnectionFactory());
//        template.setKeySerializer(new StringRedisSerializer());
//
//        // Memento 객체 직렬화
//        template.setValueSerializer(new GenericJackson2JsonRedisSerializer(redisObjectMapper()));
//
//        // ZSet 연산에 필요한 해시 값 및 기타 직렬화
//        template.setHashKeySerializer(new StringRedisSerializer());
//        template.setHashValueSerializer(new GenericJackson2JsonRedisSerializer(redisObjectMapper()));
//
//        return template;
//    }
//
//    @Bean("redisObjectMapper")
//    public ObjectMapper redisObjectMapper() {
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.registerModule(new JavaTimeModule());
//        return objectMapper;
//    }
//}
