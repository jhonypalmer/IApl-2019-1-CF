package br.ufg.es.iapl.feriados.external.util;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RequestUtil {

    public static HttpHeaders defaultHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        List<Charset> acceptCharset = Collections.singletonList(StandardCharsets.UTF_8);
        headers.setAcceptCharset(acceptCharset);
        List<MediaType> contentType = new ArrayList<>();
        contentType.add(MediaType.APPLICATION_JSON);

        headers.setAccept(contentType);
        return headers;
    }

    public static HttpHeaders tokenHeader(String accessToken) {
        HttpHeaders headers = defaultHeaders();
        headers.add("Token", accessToken);
        return headers;
    }

}
