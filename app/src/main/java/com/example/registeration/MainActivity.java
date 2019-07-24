package com.example.registeration;

import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView noticeListView;
    private NoticeListAdapter adapter;
    private List<Notice> noticeList;
    public static String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//화면을 세로로 고정?

        userID = getIntent().getStringExtra("userID");

        noticeListView = (ListView) findViewById(R.id.noticeListView);
        noticeList = new ArrayList<Notice>();
        adapter = new NoticeListAdapter(getApplicationContext(), noticeList); //테이터 하드코딩
        noticeListView.setAdapter(adapter); //adpter에 들어있는 data가 view 형태로 보여짐


        final Button courseButton = findViewById(R.id.courseButton);
        final Button statisticsButton = findViewById(R.id.statisticsButton);
        final Button scheduleButton = findViewById(R.id.scheduleButton);
        final LinearLayout notice = findViewById(R.id.notice);



        courseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notice.setVisibility(View.GONE);//버튼이 눌렸을떄 해당 프레그먼트가 보일 수 있도록
                courseButton.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));//클릭된 버튼은 어둡게
                statisticsButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));//클릭된 버튼은 어둡게
                scheduleButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));//클릭된 버튼은 어둡게

                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment,new CourseFragment());
                fragmentTransaction.commit();


            }
        });

        statisticsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notice.setVisibility(View.GONE);//버튼이 눌렸을떄 해당 프레그먼트가 보일 수 있도록 첫 공지를 안보이게 함
                courseButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));//클릭된 버튼은 어둡게
                statisticsButton.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));//클릭된 버튼은 어둡게
                scheduleButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));//클릭된 버튼은 어둡게

                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment,new StatisticsFragment());
                fragmentTransaction.commit();


            }
        });

        scheduleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notice.setVisibility(View.GONE);//버튼이 눌렸을떄 해당 프레그먼트가 보일 수 있도록
                courseButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));//클릭된 버튼은 어둡게
                statisticsButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));//클릭된 버튼은 어둡게
                scheduleButton.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));//클릭된 버튼은 어둡게

                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment,new ScheduleFragment());
                fragmentTransaction.commit();


            }
        });

        new BackgroundTask().execute();
    }

    class BackgroundTask extends AsyncTask<Void, Void, String>
    {
        String target;


        @Override
        protected void onPreExecute(){
            target = "http://zepetto92.cafe24.com/NoticeList.php";
        }

        @Override
        protected String doInBackground(Void... voids) {
           try {
               Log.d("진입","1111111111111111111111111111111111111111111");
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
                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray("response");
                int count = 0;
                String noticeContent, noticeName, noticeDate;
                while(count < jsonArray.length()){
                    JSONObject object = jsonArray.getJSONObject(count);

                    noticeContent = object.getString("CONTENT");
                    noticeName = object.getString("NAME");
                    noticeDate = object.getString("DATE");
                    Notice notice = new Notice(noticeContent,noticeName,noticeDate);

                    noticeList.add(notice);
                    count++;

                }
            }catch (Exception e){
                e.printStackTrace();
            }

            adapter = new NoticeListAdapter(getApplicationContext(), noticeList); //테이터 하드코딩
            noticeListView.setAdapter(adapter); //adpter에 들어있는 data가 view 형태로 보여짐
        }


    }

    //두번 누를시 종료료

   private  long lastTimeBackPressed;

    @Override
    public  void onBackPressed(){
        if(System.currentTimeMillis() - lastTimeBackPressed<1500)
        {
            finish();
            return;
        }
        Toast.makeText(this,"'뒤로' 버튼은 한 번 더 눌러 종료합니다.",Toast.LENGTH_SHORT);
        lastTimeBackPressed = System.currentTimeMillis();
    }

}
