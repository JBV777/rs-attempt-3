package com.project.csci3130.dalrs;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import com.google.firebase.database.*;

import com.google.firebase.auth.*;

import com.google.firebase.auth.FirebaseUser;

public class LoginInterfaceActivity extends AppCompatActivity

        implements NavigationView.OnNavigationItemSelectedListener {
        private DatabaseReference Ref;
        private static FirebaseUser user;
        private static FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_interface);
        setTitle("Home");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);

        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View headerview = navigationView.getHeaderView(0);
        final TextView Email = headerview.findViewById(R.id.UserEmail);
        final TextView name = headerview.findViewById(R.id.UserName);
        ///////////////////////////////////////////////////////////////////////////////////////////////////

        /*LayoutInflater inflater = getLayoutInflater();
    View NavView = inflater.inflate(R.layout.nav_header_login_interface, null);
       final TextView name = (TextView)NavView.findViewById(R.id.UserName);
       final TextView Email = (TextView)NavView.findViewById(R.id.UserEmail);*/

        Ref = FirebaseDatabase.getInstance().getReference("Users");
        DatabaseReference wtf = Ref.child(user.getUid()).child("Email");
        DatabaseReference wtf2 = Ref.child(user.getUid()).child("UserName");
        //这个地方读Email
        wtf.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value1 = dataSnapshot.getValue(String.class);
                Email.setText("  "+value1);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        //这个地方读Name

        wtf2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value2 = dataSnapshot.getValue(String.class);
                name.setText("  "+value2);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        ////////////////////////////////////////
        /*View headerview = navigationView.getHeaderView(0);
        TextView Email = headerview.findViewById(R.id.UserEmail);
        TextView name = headerview.findViewById(R.id.UserName);*/

    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login_interface, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_first_layout) {
            // Handle the camera action
            getFragmentManager().beginTransaction()
                    .replace(R.id.content_login_interface,new FirstFragment())
                    .commit();

        } else if (id == R.id.nav_second_layout) {
            getFragmentManager().beginTransaction()
                    .replace(R.id.content_login_interface,new SecondFragment())
                    .commit();

        } else if (id == R.id.nav_third_layout) {
            getFragmentManager().beginTransaction()
                    .replace(R.id.content_login_interface,new ThirdFragment())
                    .commit();


        }else if (id == R.id.nav_fourth_layout) {
            getFragmentManager().beginTransaction()
                    .replace(R.id.content_login_interface,new FourthFragment())
                    .commit();


        }else if (id == R.id.nav_fifth_layout) {
            auth.signOut();
            startActivity(new Intent(LoginInterfaceActivity.this,LoginActivity.class));
        }



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public static void setAuth(FirebaseAuth a){
        auth = a;
    }

    public static void setUser(FirebaseUser u){
        user = u;
    }
    public static FirebaseAuth getAuth(){
        return auth;
    }

    public static FirebaseUser getUser(){
        return user;
    }
}
