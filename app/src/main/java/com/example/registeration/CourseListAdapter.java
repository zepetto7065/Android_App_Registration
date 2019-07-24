package com.example.registeration;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class CourseListAdapter extends BaseAdapter {

    private Context context;
    private List<Course> courseList;
    private Fragment parent;
    private String userID = MainActivity.userID;
    private Schedule schedule = new Schedule();
    private ArrayList<Integer> courseIDList;

    public CourseListAdapter(Context context, List<Course> courseList, Fragment parent) {
        this.context = context;
        this.courseList = courseList;
        this.parent = parent;
        schedule = new Schedule();
        courseIDList = new ArrayList<Integer>();
        new BackgroundTask().execute();
    }

    @Override
    public int getCount() {
        return courseList.size();
    }

    @Override
    public Object getItem(int i) {
        return courseList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        View v = View.inflate(context, R.layout.course, null);
        TextView courseGrade = (TextView) v.findViewById(R.id.courseGrade);
        TextView courseTitle = (TextView) v.findViewById(R.id.courseTitle);
        TextView courseCredit = (TextView) v.findViewById(R.id.courseCredit);
        TextView courseDivide = (TextView) v.findViewById(R.id.courseDivide);
        TextView coursePersonnel = (TextView) v.findViewById(R.id.coursePersonnel);
        TextView courseProfessor = (TextView) v.findViewById(R.id.courseProfessor);
        TextView courseTime = (TextView) v.findViewById(R.id.courseTime);

        if(courseList.get(i).getCourseGrade().equals("제한 없음")||courseList.get(i).getCourseGrade().equals("")){
            courseGrade.setText("모든 학년");
        }
        else
        {
            courseGrade.setText(courseList.get(i).getCourseGrade()+"학년");
        }
        courseTitle.setText(courseList.get(i).getCourseTitle());
        courseCredit.setText(courseList.get(i).getCourseCredit()+"학점");
        courseDivide.setText(courseList.get(i).getCourseDivide()+"분반");

        if(courseList.get(i).getCoursePersonnel() == 0)
        {
            coursePersonnel.setText("인원 제한 없음");
        }
        else
        {
            coursePersonnel.setText("제한 인원 : " + courseList.get(i).getCoursePersonnel()+"명");

        }
        if(courseList.get(i).getCourseProfessor().equals("")){
            courseProfessor.setText("개인 연구");
        }
        else
        {
            courseProfessor.setText(courseList.get(i).getCourseProfessor()+"교수님");
        }
        courseTime.setText(courseList.get(i).getCourseTime()+"");
        v.setTag(courseList.get(i).getCourseID());


        Button addButton = (Button) v.findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean validate = false;
                validate = schedule.validate(courseList.get(i).getCourseTime());

                if(!alreadyin(courseIDList,courseList.get(i).getCourseID())){
                    AlertDialog.Builder builder = new AlertDialog.Builder(parent.getActivity());
                    AlertDialog dialog = builder.setMessage("이미 추가한 강의입니다.").setPositiveButton("다시 시도", null).create();
                    dialog.show();
                }else if(validate == false){
                    AlertDialog.Builder builder = new AlertDialog.Builder(parent.getActivity());
                    AlertDialog dialog = builder.setMessage("시간표가 중복됩니다.").setPositiveButton("다시 시도", null).create();
                    dialog.show();
                }else{
                    Response.Listener<String> responseListener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonResponse = new JSONObject(response);
                                boolean success = jsonResponse.getBoolean("success");
                                if (success) {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(parent.getActivity());
                                    AlertDialog dialog = builder.setMessage("강의가 추가 되었습니다.").setPositiveButton("확인", null).create();
                                    dialog.show();
                                    courseIDList.add(courseList.get(i).getCourseID());
                                    schedule.addSchedule(courseList.get(i).getCourseTime());
                                } else {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(parent.getActivity());
                                    AlertDialog dialog = builder.setMessage("강의 추가에 실패하였습니다..").setPositiveButton("확인", null).create();
                                    dialog.show();
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    };
                    AddRequest addRequest = new AddRequest(userID,courseList.get(i).getCourseID() + "", responseListener);
                    RequestQueue queue = Volley.newRequestQueue(parent.getActivity());
                    queue.add(addRequest);

                }

            }
        });

        return  v;
    }

    class BackgroundTask extends AsyncTask<Void, Void, String>
    {
        String target;


        @Override
        protected void onPreExecute(){
            try {
                target = "http://zepetto92.cafe24.com/ScheduleList.php?userID="+ URLEncoder.encode(MainActivity.userID,"UTF-8");
            }catch (Exception e){
                e.printStackTrace();
            }

        }

        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url = new URL(target);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String temp;
                StringBuilder stringBuilder = new StringBuilder();

                while ((temp = bufferedReader.readLine())!=null){
                    stringBuilder.append(temp + "\n");
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();

            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        public  void onProgressUpdate(Void... values){
            super.onProgressUpdate();
        }

        @Override
        public void onPostExecute(String result){
            try {

                System.out.println("11111111111111111111111111111111111111111111111");

                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray("response");
                System.out.println("2222222222222222222222222222222222222222222222222222222:::::::::::"+jsonArray.length());

                int count = 0;
                String courseProfessor;
                String courseTime;
                int courseID;
                while(count < jsonArray.length()){
                    JSONObject object = jsonArray.getJSONObject(count);

                    courseID = object.getInt("courseID");
                    courseProfessor = object.getString("courseProfessor");
                    courseTime = object.getString("courseTime");
                    courseIDList.add(courseID);
                    System.out.println("courseTime::::::::::::::::::::::::::::::::"+courseTime);

                    schedule.addSchedule(courseTime);
                    count++;

                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    //해당 강의 중복 체크
    public  boolean alreadyin(ArrayList<Integer> courseIDList, int item)
    {
        for(int i = 0; i<courseIDList.size(); i++){
            if(courseIDList.get(i)==item){
                return false;
            }
        }
        return  true;
    }
}
