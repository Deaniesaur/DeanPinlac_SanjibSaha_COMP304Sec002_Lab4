package com.centennial.deanpinlac_sanjibsaha_comp304sec002.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.centennial.deanpinlac_sanjibsaha_comp304sec002.R;
import com.centennial.deanpinlac_sanjibsaha_comp304sec002.ViewClassroomActivity;
import com.centennial.deanpinlac_sanjibsaha_comp304sec002.model.Classroom;

import java.util.List;

public class ClassroomAdapter extends RecyclerView.Adapter<ClassroomAdapter.ViewHolder> {

    protected List<Classroom> localDataSet;
    protected ViewClassroomActivity viewClassroomActivity;

    public static class ViewHolder extends RecyclerView.ViewHolder{

        protected final TextView rowFloor;
        protected final TextView rowAir;
        protected final ImageButton buttonEdit;
        protected final ImageButton buttonDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            rowFloor = itemView.findViewById(R.id.rowFloor);
            rowAir = itemView.findViewById(R.id.rowAirConditioned);
            buttonEdit = itemView.findViewById(R.id.rowClassroomEdit);
            buttonDelete = itemView.findViewById(R.id.rowClassroomDelete);
        }
    }

    public ClassroomAdapter(List<Classroom> Classrooms, Context context){
        localDataSet = Classrooms;
        viewClassroomActivity = (ViewClassroomActivity) context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType){
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.classroom_row, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position){
        Classroom classroom = localDataSet.get(position);
        viewHolder.rowFloor.setText(classroom.getFloor());
        viewHolder.rowAir.setText(String.valueOf(classroom.isAirConditioned()));

        viewHolder.buttonEdit.setOnClickListener(v -> {
            viewClassroomActivity.editClassroom(classroom);
        });

        viewHolder.buttonDelete.setOnClickListener(v -> {
            viewClassroomActivity.removeClassroom(classroom.getClassroomId());
        });
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
