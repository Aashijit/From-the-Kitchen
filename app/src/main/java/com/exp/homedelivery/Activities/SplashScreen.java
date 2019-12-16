package com.exp.homedelivery.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.exp.homedelivery.Activities.Admin.AdminReceivedOrders;
import com.exp.homedelivery.Activities.User.Login;
import com.exp.homedelivery.Activities.User.MyOrder;
import com.exp.homedelivery.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashScreen extends AppCompatActivity {


    private FirebaseAuth firebaseAuth = null;
    private FirebaseUser firebaseUser = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        firebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    protected void onStart(){
        super.onStart();
        firebaseUser = firebaseAuth.getCurrentUser();
    }

    public void loginAsKitchen(View view) {

        if(firebaseUser != null){
            //Lead the user to the orders page directly
        }


    }

    public void loginAsUser(View view) {

        if(firebaseUser != null){
            //Lead the user to the orders page directly
            Intent intent = new Intent(SplashScreen.this, MyOrder.class);
            startActivity(intent);
            finish();
        }else{
            //Lead the user to the Login Screen
            //Lead the user to the Login Screen
            Intent intent = new Intent(SplashScreen.this, Login.class);
            startActivity(intent);
            finish();
        }
    }

    public void loginAsAdmin(View view) {

        if(firebaseUser != null){
            //Lead the user to the orders page directly
            Intent intent = new Intent(SplashScreen.this, AdminReceivedOrders.class);
            startActivity(intent);
            finish();
        }
        else{
            //Lead the user to the Login Screen
            Intent intent = new Intent(SplashScreen.this, Login.class);
            startActivity(intent);
            finish();
        }
    }
}