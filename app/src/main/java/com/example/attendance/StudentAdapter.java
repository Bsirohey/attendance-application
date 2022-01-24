package com.example.attendance;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class StudentAdapter extends ArrayAdapter<Student> {
    private Context myContext;
    int myResource;
    boolean selectAllState;

    ArrayList<Student> students;
    ArrayList<Student> updated;


    public StudentAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Student> objects) {
        super(context, resource, objects);
        this.myContext = context;
        this.myResource = resource;

        updated = objects;

        this.students = new ArrayList<>();
        this.students.addAll(objects);

        selectAllState = false;

    }



    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        TextView studentName;
        CheckBox checkBox;

        LayoutInflater inflater = LayoutInflater.from(myContext);
        convertView = inflater.inflate(myResource, parent, false);

        String name = getItem(position).getName();
        studentName = convertView.findViewById(R.id.textView1);
        studentName.setText(name);

        checkBox = convertView.findViewById(R.id.checkBox1);


        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updated.get(position).changeSelected();
            }
        });


        if(updated.get(position).isSelected() == true) {
            checkBox = convertView.findViewById(R.id.checkBox1);
            checkBox.setChecked(true);
        }
        else
        {
            checkBox = convertView.findViewById(R.id.checkBox1);
            checkBox.setChecked(false);
        }

        return convertView;
    }



    public void selectAll()
    {
        if(this.selectAllState == true)
        {
            for(int i=0; i<students.size(); i++)
            {
                students.get(i).setSelected(true);
            }
        }
        else
        {
            for(int i=0; i<students.size(); i++)
            {
                students.get(i).setSelected(false);
            }
        }

    }


    public void changeselectAllState() {
        this.selectAllState = !this.selectAllState;
    }

    public void setselectAllState(boolean selectAllState) {
        this.selectAllState = selectAllState;
    }

    public void update(ArrayList<Student> searchResult)
    {
        if(searchResult.size()!=0)
        {
            this.updated.clear();
            this.updated.addAll(searchResult);

            this.notifyDataSetChanged();
        }
        else
        {
            this.updated.clear();
            this.updated.addAll(students);
            this.notifyDataSetChanged();
        }
    }

    public ArrayList<Student> getStudents() {
        return students;
    }
}