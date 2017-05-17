package io.homo_efficio.simple_mall.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.homo_efficio.simple_mall.domain.Product;
import io.homo_efficio.simple_mall.dto.SearchResultContainerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.context.request.async.DeferredResult;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @author homo.efficio@gmail.com
 *         created on 2017-05-16
 */
@Controller
public class HomeController {

    @Autowired
    private ObjectMapper caseInsensitiveObjectMapper;

    @GetMapping("/")
    public String hello() {
        return "index";
    }

    @GetMapping("/vuejs")
    public String vuejs() {
        return "vuejs";
    }

    @GetMapping("/search/{keyword}")
    @ResponseBody
    public DeferredResult<List<Product>> search11st(@PathVariable("keyword") String keyword) {
        DeferredResult<List<Product>> df = new DeferredResult<>();

        String apiUrl = "http://apis.skplanetx.com/11st/v2/common/products?searchKeyword=" + keyword;
        AsyncRestTemplate asyncRestTemplate = new AsyncRestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("appKey", "83aeb0b1-94db-3372-9364-22a13e6b6df2");
        httpHeaders.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        httpHeaders.set("Cache-control", "no-cache");
        HttpEntity<String> stringHttpEntity = new HttpEntity<>(httpHeaders);

        ListenableFuture<ResponseEntity<String>> lFuture = asyncRestTemplate.exchange(apiUrl, HttpMethod.GET, stringHttpEntity, String.class);
        lFuture.addCallback(
                result -> {
                    try {
                        SearchResultContainerDto searchResultContainerDto =
                                caseInsensitiveObjectMapper.readValue(result.getBody(), SearchResultContainerDto.class);
                        df.setResult(searchResultContainerDto.getProductSearchResponse().getProducts().getProduct());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                },
                error -> {
                    System.out.println(error);
                }
        );
        return df;
    }

//    @GetMapping("/search/{keyword}")
//    @ResponseBody
//    public String search(@PathVariable("keyword") String keyword) {
//        String imgurApiUrl = "https://api.imgur.com/3/gallery/search/top/0/?q=" + keyword;
//        AsyncRestTemplate asyncRestTemplate = new AsyncRestTemplate();
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.set("Authorization", "Client-ID aeef7b4d97bc785");
//        HttpEntity<String> stringHttpEntity = new HttpEntity<>(httpHeaders);
//        ListenableFuture<ResponseEntity<String>> lFuture = asyncRestTemplate.exchange(imgurApiUrl, HttpMethod.GET, stringHttpEntity, String.class);
//        lFuture.addCallback(
//                result -> {
//                    System.out.println(result);
//                },
//                error -> {
//                    System.out.println(error);
//                }
//        );
//        return keyword;
//    }

    @GetMapping("/data")
    @ResponseBody
    public String data() throws JsonProcessingException {
        List<String> data = Arrays.asList("ABC", "123", "abc" );
        return caseInsensitiveObjectMapper.writeValueAsString(data);
    }
}
