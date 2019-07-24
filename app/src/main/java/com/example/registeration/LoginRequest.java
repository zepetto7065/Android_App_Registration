package com.example.registeration;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class LoginRequest extends StringRequest {

    final static private String URL = "http://zepetto92.cafe24.com/UserLogin.php";
    private Map<String,String> parameters;

    //RegisterActivity에서 값을 받아온다.
    public LoginRequest(String userID, String userPassword,Response.Listener<String> listener){
        super(Method.POST, URL,listener, null);//해당 URL에 POST방식으로
        parameters = new HashMap<>();
        parameters.put("userID",userID);
        parameters.put("userPassword",userPassword);

        Log.d("userId :::" ,userID);
        Log.d("userPassword :::" ,userPassword);

    }

    @Override
    public Map<String, String> getParams() {
        return parameters;
    }
}
