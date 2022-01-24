package com.example.attendance;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.SearchView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView nameList;
    ArrayList<Student> studentList;
    ArrayList<Student> searchResult;
    StudentAdapter adapter;
    CheckBox selectAll;
    int checkedState;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameList =findViewById(R.id.nameList);

        Student s1 = new Student("15L-4219 Hamza Khawaja");
        Student s2 = new Student("16L-4076 Anique Rehman ");
        Student s3 = new Student("16L-4236 Ghassan Sarfaraz");
        Student s4 = new Student("17L-4010 Uzair Ahmed");
        Student s5 = new Student("17L-4015 Abdur Rafay");
        Student s6 = new Student("17L-4022 Basim Ahmad");
        Student s7 = new Student("17L-4037 Faheem Shafi");
        Student s8 = new Student("17L-4039 Uswa Mahmood");
        Student s9 = new Student("17L-4052 Salman Saleem ");
        Student s10 = new Student("17L-4059 Amna Akram");
        Student s11 = new Student("17L-4066 Muhammad Rohan");
        Student s12 = new Student("17L-4126 Ali Affan");
        Student s13 = new Student("17L-4134 Rimsha Shakeel");
        Student s14 = new Student("17L-4140 Faham Iqbal");
        Student s15 = new Student("17L-4141 Sana Basharat");
        Student s16 = new Student("17L-4146 Waleed Bin Tariq");
        Student s17 = new Student("17L-4147 Iffat Kalsoom");
        Student s18 = new Student("17L-4158 Hamza Ishtiaq");
        Student s19 = new Student("17L-4163 Atta Mohio");
        Student s20 = new Student("17L-4167 Isra Akmal");
        Student s21 = new Student("17L-4171 Aimen Ijaz");
        Student s22 = new Student("17L-4183 Maham Shafiq");
        Student s23 = new Student("17L-4192 Abia Noor");
        Student s24 = new Student("17L-4207 Arham Hussain");
        Student s25 = new Student("17L-4222 Mairaj Muhammad");



        studentList = new ArrayList<>();

        if(savedInstanceState != null)
        {
            studentList = (ArrayList<Student>) savedInstanceState.getSerializable("studentList");
        }
        else
        {
            studentList.add(s1);
            studentList.add(s2);
            studentList.add(s3);
            studentList.add(s4);
            studentList.add(s5);
            studentList.add(s6);
            studentList.add(s7);
            studentList.add(s8);
            studentList.add(s9);
            studentList.add(s10);
            studentList.add(s11);
            studentList.add(s12);
            studentList.add(s13);
            studentList.add(s14);
            studentList.add(s15);
            studentList.add(s16);
            studentList.add(s17);
            studentList.add(s18);
            studentList.add(s19);
            studentList.add(s20);
            studentList.add(s21);
            studentList.add(s22);
            studentList.add(s23);
            studentList.add(s24);
            studentList.add(s25);
        }




        adapter = new StudentAdapter(this, R.layout.adapter_view, studentList);
        nameList.setAdapter(adapter);


        selectAll = findViewById(R.id.selectAllCheckBox);

        selectAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(savedInstanceState!=null)
                {
                    int check = savedInstanceState.getInt("checkedState");


                    if(check == 1)
                    {
                        adapter.setselectAllState(true);

                        //setting check to the other number so that on the next button press, this if condition becomes false
                        savedInstanceState.putInt("checkedState", 0);
                    }
                    else
                    {
                        adapter.setselectAllState(false);

                        //setting check to the other number so that on the next button press, this if condition becomes false
                        savedInstanceState.putInt("checkedState", 1);
                    }

                }



                adapter.changeselectAllState();
                adapter.selectAll();
                adapter.notifyDataSetChanged();

            }

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        getMenuInflater().inflate(R.menu.search_menu, menu);

        MenuItem menuItem = menu.findItem(R.id.searchBar);
        final SearchView searchView = (SearchView) menuItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                searchResult = new ArrayList<>();

                if(newText.length()!=0)
                {
                    for(Student obj: studentList)
                    {
                        if(obj.getName().contains(newText) || obj.getName().toLowerCase().contains(newText) || obj.getName().contains(newText.toLowerCase()))
                        {
                            searchResult.add(obj);
                        }

                    }
                }
                StudentAdapter adapter = (StudentAdapter) nameList.getAdapter();
                adapter.update(searchResult);
                adapter.notifyDataSetChanged();


                return false;
            }
        });


        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);


        ArrayList<Student> std = adapter.getStudents();

        outState.putSerializable("studentList", std);


        if(selectAll.isChecked())
        {
            outState.putInt("checkedState", 1);
        }
        else
        {
            outState.putInt("checkedState", 0);
        }
    }

}