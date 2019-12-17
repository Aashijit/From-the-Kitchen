package com.exp.homedelivery.Activities.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.exp.homedelivery.Activities.User.Login;
import com.exp.homedelivery.Activities.User.MyOrder;
import com.exp.homedelivery.DataObjects.AccountInfo;
import com.exp.homedelivery.R;
import com.exp.homedelivery.Utils.AuthenticationUtils;
import com.exp.homedelivery.Utils.Codes;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AdminLogin extends AppCompatActivity {

    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseReferenceAccountInfo = database.getReference("AccountInfo");

    private EditText editTextMobileNumber;
    private EditText editTextPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        editTextMobileNumber = (EditText) findViewById(R.id.adminMobileNumber);
        editTextPassword = (EditText) findViewById(R.id.adminPassword);
    }


    public void loginAdmin(View view) {

        //TODO:Validate the user

        databaseReferenceAccountInfo.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                final AccountInfo accountInfo = AuthenticationUtils.getValidUser(dataSnapshot, editTextMobileNumber.getText().toString(), editTextPassword.getText().toString());

                if (accountInfo == null) {
                    Toast.makeText(getApplicationContext(), "Invalid Phone No. or Password", Toast.LENGTH_LONG).show();
                    return;
                }

                //Login in with email id and password

                firebaseAuth.signInWithEmailAndPassword(accountInfo.get_Email(), editTextPassword.getText().toString()).addOnCompleteListener(AdminLogin.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = firebaseAuth.getCurrentUser();

                            //Check the registration mode of the user
                            if (accountInfo.get_RegistrationMode().equalsIgnoreCase(Codes.REG_MODE_ADMIN)) {
                                Intent intent = new Intent(AdminLogin.this, AdminReceivedOrders.class);
                                startActivity(intent);
                                finish();
                            } else if (accountInfo.get_RegistrationMode().equalsIgnoreCase(Codes.REG_MODE_USER)) {
                                Intent intent = new Intent(AdminLogin.this, MyOrder.class);
                                startActivity(intent);
                                finish();
                            } else {
                                //TODO: Kitchen dashboard
                            }
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(AdminLogin.this, "Authentication failed.", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "Error : " + databaseError.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}