package io.homo_efficio.simple_mall.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

/**
 * @author homo.efficio@gmail.com
 *         created on 2017-05-16
 */
@Controller
public class HomeController {

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping("/")
    @ResponseBody
    public String hello() {
        return "Hello Thymeleaf";
    }

    @GetMapping("/vuejs")
    public ModelAndView vuejs(ModelAndView mv) {
        mv.setViewName("vuejs");
        return mv;
    }

    @GetMapping("/data")
    @ResponseBody
    public String data() throws JsonProcessingException {
        List<String> data = Arrays.asList("ABC", "123", "abc" );
        return objectMapper.writeValueAsString(data);
    }
}
