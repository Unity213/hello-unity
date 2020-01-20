package com.dh.bootio.webservice;

import javax.jws.WebMethod;
import javax.jws.WebService;


    @WebService
    public interface CallService {

        @WebMethod
        public void callSomeOne(String name);

    }

