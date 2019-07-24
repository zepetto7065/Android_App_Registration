package com.example.registeration;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {

    final static private String URL = "http://zepetto92.cafe24.com/UserRegister.php";
    private Map<String,String> parameters;

    //RegisterActivity에서 값을 받아온다.
    public  RegisterRequest(String userID, String userPassword, String userGender, String userNationality, String userEmail, Response.Listener<String> listener){
        super(Method.POST, URL,listener, null);//해당 URL에 POST방식으로
        parameters = new HashMap<>();
        parameters.put("userID",userID);
        parameters.put("userPassword",userPassword); 
        parameters.put("userGender",userGender);
        parameters.put("userNationality",userNationality);
        parameters.put("userEmail",userEmail);
        Log.d("userId :::" ,userID);
        Log.d("userPassWord :::" ,userPassword);
        Log.d("userGender :::" ,userGender);
        Log.d("userNationality :::" ,userNationality);
        Log.d("userEmail :::" ,userEmail);

    }

    @Override
    public Map<String, String> getParams() {
        return parameters;
    }

}
