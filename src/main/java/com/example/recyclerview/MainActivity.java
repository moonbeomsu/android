package com.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Student> studentList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView studentRv = findViewById(R.id.student_rv);

        for(int i=0;i<1000;i++){
            Student student = new Student();
            student.setName("범수"+i);
            student.setNumber("2018261028"+ i);
            studentList.add(student);
        }
        StudentAdapter adapter = new StudentAdapter(studentList);
        studentRv.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
      //  GridLayoutManager layoutManager = new GridLayoutManager(this,2);
        studentRv.setLayoutManager(layoutManager);
    }
}