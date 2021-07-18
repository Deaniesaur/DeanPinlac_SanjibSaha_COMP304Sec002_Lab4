package com.centennial.deanpinlac_sanjibsaha_comp304sec002.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.centennial.deanpinlac_sanjibsaha_comp304sec002.R;
import com.centennial.deanpinlac_sanjibsaha_comp304sec002.model.Student;

import java.text.DecimalFormat;
import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder> {

    protected List<Student> localDataSet;
    protected Context myContext;

    public static class ViewHolder extends RecyclerView.ViewHolder{

        protected final TextView rowName;
        protected final TextView rowDept;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            rowName = itemView.findViewById(R.id.rowName);
            rowDept = itemView.findViewById(R.id.rowDepartment);
        }
    }

    public StudentAdapter(List<Student> students, Context context){
        localDataSet = students;
        myContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType){
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.student_row, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position){
        Student student = localDataSet.get(position);
        String name = student.getLastName() + ", " + student.getFirstName();
        viewHolder.rowName.setText(name);
        viewHolder.rowDept.setText(student.getDepartment());

//        viewHolder.buttonDelete.setOnClickListener(v -> {
//            removeAt(position);
//            ((CheckoutActivity) context).itemRemoved();
//        });
    }

    public void removeAt(int position){
        localDataSet.remove(position);
        notifyDataSetChanged();
        notifyItemChanged(position);
    }

    @Override
    public int getItemCount(){
        return localDataSet.size();
    }
}
