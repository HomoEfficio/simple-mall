package io.homo_efficio.simple_mall.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.homo_efficio.simple_mall.domain.Member;
import io.homo_efficio.simple_mall.dto.SearchResultContainerDto;
import io.homo_efficio.simple_mall.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.context.request.async.DeferredResult;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author homo.efficio@gmail.com
 *         created on 2017-05-19
 */
@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private ObjectMapper caseInsensitiveObjectMapper;

    @Autowired
    private MemberRepository memberRepository;


    @PostMapping("/members/{userName}")
    public ResponseEntity<Member> signUp(@PathVariable("userName") String userName, HttpServletRequest request) {
        Member newMember = memberRepository.save(new Member(userName));
        return ResponseEntity.ok(newMember);
    }

    /**
     * 상품 검색
     * 11번가 상품 검색 Open API 사용
     *
     * @param keyword
     * @return
     */
    @GetMapping("/search/{keyword}")
    public DeferredResult<String> search11st(@PathVariable("keyword") String keyword) {
        DeferredResult<String> df = new DeferredResult<>();

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
                        df.setResult(caseInsensitiveObjectMapper.writeValueAsString(searchResultContainerDto.getProductSearchResponse().getProducts().getProduct()));
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
}
