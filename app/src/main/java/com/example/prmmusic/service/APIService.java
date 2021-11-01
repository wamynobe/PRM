package com.example.prmmusic.service;

import com.example.prmmusic.interfaces.DataService;

public class APIService {

    private static String base_url = "https://prmsinger.000webhostapp.com/Server/";


    public static DataService getService(){
        return APIRetrofitClient.getClient(base_url).create(DataService.class);
    }
}
