package com.example.registeration;

import android.content.Context;
import android.widget.TextView;

public class Schedule {

    private String monday[] = new String[14];//0~13교시
    private String tuesday[] = new String[14];//0~13교시
    private String wednesday[] = new String[14];//0~13교시
    private String thursday[] = new String[14];//0~13교시
    private String friday[] = new String[14];//0~13교시

    Schedule(){
        for (int i = 0;i<14;i++){
            monday[i] = "";
            tuesday[i] = "";
            thursday[i] = "";
            wednesday[i] = "";
            friday[i] = "";
        }
    }

    public void addSchedule(String scheduleText){
        int temp;
        //월:[3][4][5]
        if((temp=scheduleText.indexOf("월"))>-1){
            temp +=2;
            int startPoint = temp;
            int endPoint = temp;
            for(int i  = temp; i<scheduleText.length() && scheduleText.charAt(i) !=':';i++)
            {
                if(scheduleText.charAt(i)=='[')
                {
                    startPoint = i;
                }
                if(scheduleText.charAt(i)==']'){
                    endPoint = i;
                    monday[Integer.parseInt(scheduleText.substring(startPoint+1,endPoint))]="수업";
                }
            }
        }

        if((temp=scheduleText.indexOf("화"))>-1){
            temp +=2;
            int startPoint = temp;
            int endPoint = temp;
            for(int i  = temp; i<scheduleText.length() && scheduleText.charAt(i) !=':';i++)
            {
                if(scheduleText.charAt(i)=='[')
                {
                    startPoint = i;
                }
                if(scheduleText.charAt(i)==']'){
                    endPoint = i;
                    tuesday[Integer.parseInt(scheduleText.substring(startPoint+1,endPoint))]="수업";
                }
            }
        }

        if((temp=scheduleText.indexOf("수"))>-1){
            temp +=2;
            int startPoint = temp;
            int endPoint = temp;
            for(int i  = temp; i<scheduleText.length() && scheduleText.charAt(i) !=':';i++)
            {
                if(scheduleText.charAt(i)=='[')
                {
                    startPoint = i;
                }
                if(scheduleText.charAt(i)==']'){
                    endPoint = i;
                    wednesday[Integer.parseInt(scheduleText.substring(startPoint+1,endPoint))]="수업";
                }
            }
        }

        if((temp=scheduleText.indexOf("목"))>-1){
            temp +=2;
            int startPoint = temp;
            int endPoint = temp;
            for(int i  = temp; i<scheduleText.length() && scheduleText.charAt(i) !=':';i++)
            {
                if(scheduleText.charAt(i)=='[')
                {
                    startPoint = i;
                }
                if(scheduleText.charAt(i)==']'){
                    endPoint = i;
                    wednesday[Integer.parseInt(scheduleText.substring(startPoint+1,endPoint))]="수업";
                }
            }
        }

        if((temp=scheduleText.indexOf("금"))>-1){
            temp +=2;
            int startPoint = temp;
            int endPoint = temp;
            for(int i  = temp; i<scheduleText.length() && scheduleText.charAt(i) !=':';i++)
            {
                if(scheduleText.charAt(i)=='[')
                {
                    startPoint = i;
                }
                if(scheduleText.charAt(i)==']'){
                    endPoint = i;
                    friday[Integer.parseInt(scheduleText.substring(startPoint+1,endPoint))]="수업";
                }
            }
        }
    }

    public void addSchedule(String scheduleText,String courseTitle, String courseProfessor){
        String professor;
        if(courseProfessor.equals("")){
            professor="";
        }else{
            professor = "("+courseProfessor+")";
        }
        int temp;
        //월[3][4][5]
        if((temp=scheduleText.indexOf("월"))>-1){
            temp +=2;
            int startPoint = temp;
            int endPoint = temp;
            for(int i  = temp; i<scheduleText.length() && scheduleText.charAt(i) !=':';i++)
            {
                if(scheduleText.charAt(i)=='[')
                {
                    startPoint = i;
                }
                if(scheduleText.charAt(i)==']'){
                    endPoint = i;
                    monday[Integer.parseInt(scheduleText.substring(startPoint+1,endPoint))] = courseTitle+professor;
                }
            }
        }

        if((temp=scheduleText.indexOf("화"))>-1){
            temp +=2;
            int startPoint = temp;
            int endPoint = temp;
            for(int i  = temp; i<scheduleText.length() && scheduleText.charAt(i) !=':';i++)
            {
                if(scheduleText.charAt(i)=='[')
                {
                    startPoint = i;
                }
                if(scheduleText.charAt(i)==']'){
                    endPoint = i;
                    tuesday[Integer.parseInt(scheduleText.substring(startPoint+1,endPoint))] = courseTitle+professor;
                }
            }
        }

        if((temp=scheduleText.indexOf("수"))>-1){
            temp +=2;
            int startPoint = temp;
            int endPoint = temp;
            for(int i  = temp; i<scheduleText.length() && scheduleText.charAt(i) !=':';i++)
            {
                if(scheduleText.charAt(i)=='[')
                {
                    startPoint = i;
                }
                if(scheduleText.charAt(i)==']'){
                    endPoint = i;
                    wednesday[Integer.parseInt(scheduleText.substring(startPoint+1,endPoint))] = courseTitle+professor;
                }
            }
        }

        if((temp=scheduleText.indexOf("목"))>-1){
            temp +=2;
            int startPoint = temp;
            int endPoint = temp;
            for(int i  = temp; i<scheduleText.length() && scheduleText.charAt(i) !=':';i++)
            {
                if(scheduleText.charAt(i)=='[')
                {
                    startPoint = i;
                }
                if(scheduleText.charAt(i)==']'){
                    endPoint = i;
                    thursday[Integer.parseInt(scheduleText.substring(startPoint+1,endPoint))] = courseTitle + professor;
                }
            }
        }

        if((temp=scheduleText.indexOf("금"))>-1){
            temp +=2;
            int startPoint = temp;
            int endPoint = temp;
            for(int i  = temp; i<scheduleText.length() && scheduleText.charAt(i) !=':';i++)
            {
                if(scheduleText.charAt(i)=='[')
                {
                    startPoint = i;
                }
                if(scheduleText.charAt(i)==']'){
                    endPoint = i;
                    friday[Integer.parseInt(scheduleText.substring(startPoint+1,endPoint))] = courseTitle+professor;
                }
            }
        }
    }



    public  void setting(AutoResizeTextView[] monday, AutoResizeTextView[] tuesday, AutoResizeTextView[] wednesday, AutoResizeTextView[] thursday, AutoResizeTextView[] friday, Context context){
        for(int i = 0; i<14; i++){
            if(!this.monday[i].equals("")){
                monday[i].setText(this.monday[i]);
                monday[i].setBackgroundColor (context.getResources().getColor(R.color.colorPrimary)); //해당 강의 존재시 색 변함
            }else
            {
                monday[i].setText("가나다라마바사아자차");//의미없지만 한글10글자로 맞추기
            }
            if(!this.tuesday[i].equals("")){
                tuesday[i].setText(this.tuesday[i]);
                tuesday[i].setBackgroundColor (context.getResources().getColor(R.color.colorPrimary)); //해당 강의 존재시 색 변함
            }else
            {
                tuesday[i].setText("가나다라마바사아자차");
            }
            if(!this.wednesday[i].equals("")){
                wednesday[i].setText(this.wednesday[i]);
                wednesday[i].setBackgroundColor (context.getResources().getColor(R.color.colorPrimary)); //해당 강의 존재시 색 변함
            }else
            {
                wednesday[i].setText("가나다라마바사아자차");
            }
            if(!this.thursday[i].equals("")){
                thursday[i].setText(this.thursday[i]);
                thursday[i].setBackgroundColor (context.getResources().getColor(R.color.colorPrimary)); //해당 강의 존재시 색 변함
            }else
            {
                thursday[i].setText("가나다라마바사아자차");
            }
            if(!this.friday[i].equals("")){
                friday[i].setText(this.friday[i]);
                friday[i].setBackgroundColor (context.getResources().getColor(R.color.colorPrimary)); //해당 강의 존재시 색 변함
            }else
            {
                friday[i].setText("가나다라마바사아자차");
            }
            monday[i].resizeText();
            tuesday[i].resizeText();
            wednesday[i].resizeText();
            thursday[i].resizeText();
            friday[i].resizeText();

        }
    }

    public boolean validate(String scheduleText){
         if(scheduleText.equals("")){
            return true;
        }
         int temp;
        if((temp=scheduleText.indexOf("월"))>-1){
            temp +=2;
            int startPoint = temp;
            int endPoint = temp;
            for(int i  = temp; i<scheduleText.length() && scheduleText.charAt(i) !=':';i++)
            {
                if(scheduleText.charAt(i)=='[')
                {
                    startPoint = i;
                }
                if(scheduleText.charAt(i)==']'){
                    endPoint = i;
                    if(!monday[Integer.parseInt(scheduleText.substring(startPoint+1,endPoint))].equals("")){
                        return false;
                    }
                }
            }
        }

        if((temp=scheduleText.indexOf("화"))>-1){
            temp +=2;
            int startPoint = temp;
            int endPoint = temp;
            for(int i  = temp; i<scheduleText.length() && scheduleText.charAt(i) !=':';i++)
            {
                if(scheduleText.charAt(i)=='[')
                {
                    startPoint = i;
                }
                if(scheduleText.charAt(i)==']'){
                    endPoint = i;
                    if(!tuesday[Integer.parseInt(scheduleText.substring(startPoint+1,endPoint))].equals("")){
                        return false;
                    }
                }
            }
        }

        if((temp=scheduleText.indexOf("수"))>-1){
            temp +=2;
            int startPoint = temp;
            int endPoint = temp;
            for(int i  = temp; i<scheduleText.length() && scheduleText.charAt(i) !=':';i++)
            {
                if(scheduleText.charAt(i)=='[')
                {
                    startPoint = i;
                }
                if(scheduleText.charAt(i)==']'){
                    endPoint = i;
                    if(!wednesday[Integer.parseInt(scheduleText.substring(startPoint+1,endPoint))].equals("")){
                        return false;
                    }
                }
            }
        }

        if((temp=scheduleText.indexOf("목"))>-1){
            temp +=2;
            int startPoint = temp;
            int endPoint = temp;
            for(int i  = temp; i<scheduleText.length() && scheduleText.charAt(i) !=':';i++)
            {
                if(scheduleText.charAt(i)=='[')
                {
                    startPoint = i;
                }
                if(scheduleText.charAt(i)==']'){
                    endPoint = i;
                    if(!thursday[Integer.parseInt(scheduleText.substring(startPoint+1,endPoint))].equals("")){
                        return false;
                    }
                }
            }
        }

        if((temp=scheduleText.indexOf("금"))>-1){
            temp +=2;
            int startPoint = temp;
            int endPoint = temp;
            for(int i  = temp; i<scheduleText.length() && scheduleText.charAt(i) !=':';i++)
            {
                if(scheduleText.charAt(i)=='[')
                {
                    startPoint = i;
                }
                if(scheduleText.charAt(i)==']'){
                    endPoint = i;
                    if(!friday[Integer.parseInt(scheduleText.substring(startPoint+1,endPoint))].equals("")){
                        return false;
                    }
                }
            }
        }
        return true;

    }

}
