package top.ashonecoder.zcstselectcourse.config;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.HttpClientCodec;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.Netty4ClientHttpRequestFactory;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;

@Configuration
public class BeanConfig {
@Bean
public Netty4ClientHttpRequestFactory netty4ClientHttpRequestFactory(){
    return new Netty4ClientHttpRequestFactory();
}

@Bean
    public AsyncRestTemplate asyncRestTemplate(){

    AsyncRestTemplate restTemplate= new AsyncRestTemplate();
    restTemplate.setAsyncRequestFactory(this.netty4ClientHttpRequestFactory());


    return  restTemplate;
    }




@Bean
public RestTemplate restTemplate(){
    return  new RestTemplateBuilder().build();
}








}
