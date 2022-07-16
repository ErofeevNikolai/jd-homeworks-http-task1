package ru.netology;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHeaders;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;


import java.io.IOException;
import java.util.List;

public class Main {
    public static final String REMOTE_SERVICE_URI = "https://raw.githubusercontent.com/netology-code/jd-homeworks/master/http/task1/cats";
    public static ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) {
        try (
                CloseableHttpClient httpClient = HttpClientBuilder.create()
                        .setUserAgent("My Test Service")
                        .setDefaultRequestConfig(RequestConfig.custom()
                                .setConnectTimeout(5000)    // время в миллисекундах которое мы ожидаем ответа оть сервера.
                                .setSocketTimeout(30000)    // максимальное время ожидания получения данных
                                .setRedirectsEnabled(false) // возможность следовать редиректу в ответе(перенаправления на другой сайт)
                                .build())                   // собираем requestConfig
                        .build();                           // собираем клиент
        ) {

            // создание объекта запроса с произвольными заголовками
            HttpGet request = new HttpGet(REMOTE_SERVICE_URI);
            request.setHeader(HttpHeaders.ACCEPT, ContentType.APPLICATION_JSON.getMimeType());

            // отправка запроса
            CloseableHttpResponse response = httpClient.execute(request);

            // преобразовываем Json список в объекты класса Cat
            List<Cat> cats = mapper.readValue(
                    response.getEntity().getContent(),
                    new TypeReference<>() {
                    }
            );

            // выводим на экран, предварительно отсортировав элементы больше >0 и не равные null
            cats.stream()
                    .filter(value -> value.getUpvotes() != null && value.getUpvotes() > 0)
                    .forEach(System.out::println);

        } catch (IOException e) {
            System.out.println(e);
        }
    }

}

