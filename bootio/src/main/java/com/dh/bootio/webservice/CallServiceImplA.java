package com.dh.bootio.webservice;

import javax.jws.WebService;
import javax.xml.ws.Endpoint;

@WebService(endpointInterface="com.dh.bootio.webservice.CallService" , targetNamespace = "www.baidu.com")
public class CallServiceImplA implements CallService {
    @Override
    public void callSomeOne(String name) {
        // TODO Auto-generated method stub

        System.out.println("Hi, "+name+", I'm calling you");

    }

        public static void main(String[] args) {
            System.out.println("server starting...");
            String address = "http://localhost:8889/service/call";
            Endpoint.publish(address, new CallServiceImplA());
            System.out.println("server started.");
    }
}
