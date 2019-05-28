package com.example.businessmanager.Utilities;

public class Utils
{
    private Utils(){}

    public static final String BaseUrl="http://139.59.92.232:8000/";

    public static ClientAPI getClientAPI()
    {
        return RetrofitClient.getClient(BaseUrl).create(ClientAPI.class);
    }
}
