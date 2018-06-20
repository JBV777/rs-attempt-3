package com.project.csci3130.dalrs;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.ArrayMap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CourseViewList extends AppCompatActivity {
    private TextView test1;
    private TextView test2;
    private DatabaseReference mReference;
    private DatabaseReference wReference;
//
    private ExpandableListView expandableListView;
    private ExpandableListViewAdapter adapter;
    private List<String> courseCS = new ArrayList<>();
    private List<String> courseSTAT = new ArrayList<>();
    private String[] groupList = new String[]{"Computer Science","Statistic"};
    //private ArrayList<String> groupListView = new ArrayList<>();
    //private String[] groupList;
    private Map<String, List<String>> courseList = new HashMap<>();


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_list_view_main);

        expandableListView = (ExpandableListView) findViewById(R.id.expandableList);
        //iniUI();
        initData();
        listView();
        //courseList.toString();
        //CourseViewList.listView();
        Intent intent = getIntent();

        adapter = new ExpandableListViewAdapter();
        expandableListView.setAdapter(adapter);

    }
    private void initData() {
        mReference = FirebaseDatabase.getInstance().getReference();
        DatabaseReference haha = mReference.child("Courses");
        //DatabaseReference haha = mReference.child("Courses").child("10001").child("CourseDep");

        haha.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                collectCourseTitles((Map<String,Object>) dataSnapshot.getValue());
                // collectCourseDep((Map<String,Object>) dataSnapshot.getValue());
                //courseList = listView();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    /*private void initData1() {
        wReference = FirebaseDatabase.getInstance().getReference();
        DatabaseReference lala = wReference.child("Courses");
        //DatabaseReference haha = mReference.child("Courses").child("10001").child("CourseDep");

        lala.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //collectCourseTitles((Map<String,Object>) dataSnapshot.getValue());
                collectCourseDep((Map<String,Object>) dataSnapshot.getValue());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }*/

    /*private void iniUI() {
        test1 = (TextView) findViewById(R.id.test1);
        test2 = (TextView) findViewById(R.id.test2);
    }*/
    /*private void collectCourseDep(Map<String,Object> department){
        for (Map.Entry<String, Object> entry : department.entrySet()) {

            Map depart = (Map) entry.getValue();

            String dep = (String) depart.get("CourseDep");

            if(dep!=null && !groupListView.contains(dep)) {
                groupListView.add(dep);
            }
        }
        groupList = (String[]) groupListView.toArray(new String[groupListView.size()]);
       // courseList.put(groupList[0],courseCS);
       // courseList.put(groupList[1],courseSTAT);

    }
    */

    private void collectCourseTitles(Map<String,Object> courses){
        int count = 0;
        String[] list =null;
        for(Map.Entry<String,Object> entry: courses.entrySet()){

            Map course = (Map) entry.getValue();
            boolean flag = false;

            String title = (String) course.get("CourseTitle");
            String dep = (String) course.get("CourseDep");

            /*if(title != null && dep.contains("Computer")){
                courseCS.add(title);
            }
            if(title !=null && dep.contains("Stat")){
                courseSTAT.add(title);
            }*/

            if(title != null && dep.contains("Computer") && !courseCS.contains(title)){
                courseCS.add(title);
            }
            if(title !=null && dep.contains("Stat") && !courseSTAT.contains(title)){
                courseSTAT.add(title);
            }

            /*if(groupList==null){
                groupList[0] = dep;
                count++;
            }
            else {
                for (int i = 0; i < count + 1; i++) {
                    if (groupList == null) {
                        break;
                    }
                    if (!groupList.equals(dep)) {
                        flag = true;
                    }
                }
                if (flag == true) {
                    groupList[count] = dep;
                    count++;

                }
            }*/
            /*for(int i = 0; i<groupList.length; i++) {
                if (title != null) {
                    if(dep.equals(groupList[i])){
                        courseView.get(i).add(title);
                    }
                }
            }*/


        }

        //test1.setText(groupList.length);
        //test2.setText(groupList.toString());
    }
    private void listView(){

        courseList.put(groupList[0],courseCS);
        courseList.put(groupList[1],courseSTAT);

    }
    /*public class ExpandableListViewAdapter extends BaseExpandableListAdapter {

        @Override
        //get one item from one groupList.
        public Object getChild(int parentPosition, int childPosition){
            return courseList.get(parentPosition).get(childPosition);
        }

        @Override
        //Get the number of groups
        public int getGroupCount(){
            return courseList.size();
        }

        @Override
        //Get the number of child elements in the specified group
        public int getChildrenCount(int parentPosition){
            return courseList.get(parentPosition).size();
        }

        @Override
        //Get the data in the specified group
        public Object getGroup(int parentPosition){
            return courseList.get(parentPosition);
        }

        @Override
        //Get the id of a child of a group
        public long getChildId(int parentPosition, int childPosition){
            return childPosition;
        }

        @Override
        //Get the id of the specified group
        public long getGroupId(int parentPosition){
            return parentPosition;
        }

        @Override
        public boolean hasStableIds(){
            return false;
        }

        @Override
        public View getGroupView(int parentPosition, boolean isExpand,
                                 View convertView, ViewGroup viewGroup){
            if(convertView == null){
                LayoutInflater inflater = (LayoutInflater)
                        getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.group_item,null);
            }
            convertView.setTag(R.layout.group_item, parentPosition);

            convertView.setTag(R.layout.child_item, -1);

            TextView textView = (TextView) convertView.findViewById(R.id.groupView);

            textView.setText(groupListView.get(parentPosition));

            return convertView;
        }

        @Override
        public View getChildView(int parentPosition, int childPosition, boolean isLastChild,
                                 View convertView, ViewGroup viewGroup){
            if(convertView == null){
                LayoutInflater inflater = (LayoutInflater)
                        getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.child_item,null);
            }
            convertView.setTag(R.layout.group_item, parentPosition);

            convertView.setTag(R.layout.child_item, childPosition);

            TextView textView = (TextView) convertView.findViewById(R.id.childView);

            textView.setText(courseList.get(parentPosition).get(childPosition));

            return convertView;
        }

        @Override
        public boolean isChildSelectable(int parentPosition, int childPosition){
            return false;  //not in interaction 1
        }
    }*/

    public class ExpandableListViewAdapter extends BaseExpandableListAdapter{

        @Override
        //get one item from one groupList.
        public Object getChild(int parentPosition, int childPosition){
            return courseList.get(groupList[parentPosition]).get(childPosition);
        }

        @Override
        //Get the number of groups
        public int getGroupCount(){
            return courseList.size();
        }

        @Override
        //Get the number of child elements in the specified group
        public int getChildrenCount(int parentPosition){
            return courseList.get(groupList[parentPosition]).size();
        }

        @Override
        //Get the data in the specified group
        public Object getGroup(int parentPosition){
            return courseList.get(groupList[parentPosition]);
        }

        @Override
        //Get the id of a child of a group
        public long getChildId(int parentPosition, int childPosition){
            return childPosition;
        }

        @Override
        //Get the id of the specified group
        public long getGroupId(int parentPosition){
            return parentPosition;
        }

        @Override
        public boolean hasStableIds(){
            return false;
        }

        @Override
        public View getGroupView(int parentPosition, boolean isExpand,
                                 View convertView, ViewGroup viewGroup){
            if(convertView == null){
                LayoutInflater inflater = (LayoutInflater)
                        getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.group_item,null);
            }
            convertView.setTag(R.layout.group_item, parentPosition);

            convertView.setTag(R.layout.child_item, -1);

            TextView textView = (TextView) convertView.findViewById(R.id.groupView);

            textView.setText(groupList[parentPosition]);

            return convertView;
        }

        @Override
        public View getChildView(int parentPosition, int childPosition, boolean isLastChild,
                                 View convertView, ViewGroup viewGroup){
            if(convertView == null){
                LayoutInflater inflater = (LayoutInflater)
                        getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.child_item,null);
            }
            convertView.setTag(R.layout.group_item, parentPosition);

            convertView.setTag(R.layout.child_item, childPosition);

            TextView textView = (TextView) convertView.findViewById(R.id.childView);

            textView.setText(courseList.get(groupList[parentPosition]).get(childPosition));

            return convertView;
        }

        @Override
        public boolean isChildSelectable(int parentPosition, int childPosition){
            return false;  //not in interaction 1
        }
    }

}
