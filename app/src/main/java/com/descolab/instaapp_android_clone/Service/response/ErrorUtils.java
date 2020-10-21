package com.descolab.instaapp_android_clone.Service.response;


import com.descolab.instaapp_android_clone.Service.ApiClient;
import com.descolab.instaapp_android_clone.Service.ResponseApi;

import java.io.IOException;
import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;

public class ErrorUtils {
    public static ResponseApi parseError(Response<?> response) {
        Converter<ResponseBody, ResponseApi> converter =
                ApiClient.Companion.getClient().responseBodyConverter(ResponseApi.class, new Annotation[0]);

        ResponseApi error;

        try {
            error = converter.convert(response.errorBody());
        } catch (IOException e) {
            return new ResponseApi();
        }

        return error;
    }
}
