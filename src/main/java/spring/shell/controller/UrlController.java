package spring.shell.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import spring.shell.entity.Url;
import spring.shell.service.UrlService;

import java.io.IOException;

@RestController
@RequestMapping("/panda")
@RequiredArgsConstructor
public class UrlController {
    private final UrlService service;

    @GetMapping("/{code}")
    public void redirectTo(@PathVariable String code, HttpServletResponse response) throws IOException {
        Url url = service.getByCode(code);
        response.sendRedirect(url.getUrl());
    }
}
