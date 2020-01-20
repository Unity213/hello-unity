package com.dh.bootio.webservice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;

public class TestClient {
    public static void main(String[] args) {
        URL url = null;
        try {
            url = new URL("http://localhost:8889/service/call");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        QName qName = new QName("http://webservice.bootio.dh.com/", "CallServiceImplAService");

        Service service = Service.create(url, qName);
        CallService cs = service.getPort(CallService.class);
        cs.callSomeOne("wumz");
        RestTemplate s = new RestTemplate();
        ResponseEntity<String> forEntity = s.getForEntity("", String.class);

    }
}
