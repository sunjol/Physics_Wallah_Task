package com.example.pw_task;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class Teacher_Details_Adapter extends RecyclerView.Adapter<Teacher_Details_Adapter.Teacher_Details_Viewholder> {

    //Adapter for recyclerview
    private List<Teacher_Detail_Model_Class> dataList;
    private Context context;

    public Teacher_Details_Adapter(List<Teacher_Detail_Model_Class> dataList,Context context) {
        this.dataList = dataList;
        this.context=context;
    }



    @NonNull
    @Override
    public Teacher_Details_Adapter.Teacher_Details_Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.teacher_detail_list_item,parent,false);
        return new Teacher_Details_Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Teacher_Details_Adapter.Teacher_Details_Viewholder holder, int position) {
        Teacher_Detail_Model_Class teacher_detail_model_class=dataList.get(position);

        holder.textView_Name.setText(teacher_detail_model_class.getName());
        holder.textView_Subject.setText(teacher_detail_model_class.getSubjects());
        holder.textView_Qualification.setText(teacher_detail_model_class.getQualification());
        Glide.with(context).load(teacher_detail_model_class.getProfileImage()).into(holder.imageView_teacher_photo);
        holder.button_viewmore.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Toast.makeText(context, teacher_detail_model_class.getName(),Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class Teacher_Details_Viewholder extends RecyclerView.ViewHolder {

        //Create custom Viewholder
        TextView textView_Name;
        TextView textView_Subject;
        TextView textView_Qualification;
        Button button_viewmore;
        ImageView imageView_teacher_photo;

        public Teacher_Details_Viewholder(@NonNull View itemView) {
            super(itemView);

            textView_Name=itemView.findViewById(R.id.td_name);
            textView_Qualification=itemView.findViewById(R.id.td_qualifications);
            textView_Subject=itemView.findViewById(R.id.td_subject);
            button_viewmore=itemView.findViewById(R.id.td_btnViewMore);
            imageView_teacher_photo=itemView.findViewById(R.id.td_imgTeacher);

        }
    }



}
