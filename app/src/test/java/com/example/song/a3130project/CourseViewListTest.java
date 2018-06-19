package com.example.song.a3130project;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class CourseViewListTest {


    @Test
    public void testCollectCourseTitles(){
        String result = "";
        Map<String, Object> courses = new HashMap<>();
        String csci = "Computer Science";
        String stat = "Statistic";
        Object c1 = new Object();
        courses.put(csci, (Object) "CSCI3130");
        courses.put(stat, (Object) "STAT2080");
        String cs = null,st = null;

        for(Map.Entry<String, Object> entry: courses.entrySet()){


            String dep = (String) entry.getKey();
            String title = (String) entry.getValue();


            if(title != null && dep.contains("Computer")){
                cs=title;
            }
            if(title !=null && dep.contains("Stat")){
                st=title;
            }
        }
        Assert.assertEquals("CSCI3130", cs);
        Assert.assertEquals("STAT2080", st);

    }
}