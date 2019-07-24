package com.example.registeration;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class AddRequest extends StringRequest {

    final static private String URL = "http://zepetto92.cafe24.com/CourseAdd.php";
    private Map<String,String> parameters;

    //RegisterActivity에서 값을 받아온다.
    public AddRequest(String userID, String courseID, Response.Listener<String> listener){
        super(Method.POST, URL,listener, null);//해당 URL에 POST방식으로
        parameters = new HashMap<>();
        parameters.put("userID",userID);
        parameters.put("courseID",courseID);


    }

    @Override
    public Map<String, String> getParams() {
        return parameters;
    }

}
