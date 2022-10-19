package com.example.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder> {
    ArrayList<Student> mList;

    public StudentAdapter(ArrayList<Student> list){
        this.mList=list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //레이아웃을 가져와서 뷰 홀더로 감싼 다음 리턴
        View view =  LayoutInflater.from(parent.getContext()).inflate(R.layout.item_student,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
       Student student =  mList.get(position);
       holder.bind(student);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView nameTv;
        TextView numberTv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.nameTv=itemView.findViewById(R.id.name_tv);
            this.numberTv=itemView.findViewById(R.id.number_tv);
        }
        public void bind(Student student){
            this.nameTv.setText(student.getName());
            this.numberTv.setText(student.getNumber());
        }

    }
}
