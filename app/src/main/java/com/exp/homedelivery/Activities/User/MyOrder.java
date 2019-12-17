package com.exp.homedelivery.Activities.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.accounts.Account;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.exp.homedelivery.Activities.User.ListAdapters.KitchenInfoListAdapter;
import com.exp.homedelivery.DataObjects.AccountInfo;
import com.exp.homedelivery.DataObjects.KitchenInfo;
import com.exp.homedelivery.R;
import com.exp.homedelivery.Utils.Codes;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MyOrder extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseReferenceKitchenInfo = database.getReference("KitchenInfo");
    DatabaseReference databaseReferenceAccountInfo = database.getReference("AccountInfo");
    DatabaseReference databaseReferenceOrders = database.getReference("OrderInfo");

    private KitchenInfoListAdapter kitchenInfoListAdapter;

    private TextView userNameMyOrders;
    private ListView userAvailableKitchens;
    private ListView userOrders;
    private ImageView noOrderImage;
    private TextView noOrderText;
    private TextView noOrderTextDescription;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);

        firebaseAuth = FirebaseAuth.getInstance();

        userNameMyOrders = (TextView) findViewById(R.id.userNameMyOrders);
        userAvailableKitchens = (ListView) findViewById(R.id.userAvailableKitchens);
        userOrders = (ListView) findViewById(R.id.userOrders);
        noOrderImage = (ImageView) findViewById(R.id.noOrderImage);
        noOrderText = (TextView) findViewById(R.id.noOrderText);
        noOrderTextDescription = (TextView) findViewById(R.id.noOrderTextDescription);

        if(firebaseAuth.getCurrentUser() == null){
            Toast.makeText(getApplicationContext(),"Please log in again",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(MyOrder.this, Login.class);
            startActivity(intent);
            finish();
        }

        //Fetch the available kitchens
        fetchAccountInfoUser();
        fetchAvailableKitchens();
        fetchOrders();
    }

    private void fetchOrders() {

        databaseReferenceOrders.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void fetchAccountInfoUser() {
        databaseReferenceAccountInfo.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1  : dataSnapshot.getChildren()){
                    AccountInfo accountInfo = dataSnapshot1.getValue(AccountInfo.class);

                    if(accountInfo == null)
                    {
                        Toast.makeText(getApplicationContext(),"Null Account Info ",Toast.LENGTH_LONG).show();
                        continue;
                    }
                    if(accountInfo.get_Email().equalsIgnoreCase(firebaseAuth.getCurrentUser().getEmail())) {
                        userNameMyOrders.setText("Hello, " + accountInfo.get_Name());
                        break;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MyOrder.this,"Error : "+databaseError.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }

    private void fetchAvailableKitchens() {
        databaseReferenceKitchenInfo.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final List<KitchenInfo> kitchenInfoList = new ArrayList<>();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                    KitchenInfo kitchenInfo = dataSnapshot1.getValue(KitchenInfo.class);

                    if(kitchenInfo == null){
                        Toast.makeText(getApplicationContext(),"Kitchen Info is null",Toast.LENGTH_LONG).show();
                        continue;
                    }

                    Log.d(this.getClass().getSimpleName(),kitchenInfo.toString());
                    kitchenInfoList.add(kitchenInfo);
                }


                //Paint the list view
                kitchenInfoListAdapter = new KitchenInfoListAdapter(MyOrder.this,kitchenInfoList);
                userAvailableKitchens.setAdapter(kitchenInfoListAdapter);


                userAvailableKitchens.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        //Take the user to the next page
                        Intent intent = new Intent(MyOrder.this, HomeDeliveryMenu.class);
                        intent.putExtra(Codes.KEY_KITCHEN_NAME,kitchenInfoList.get(position).get_Name());
                        intent.putExtra(Codes.KEY_KITCHEN_DESCRIPTION,kitchenInfoList.get(position).get_Description());
                        intent.putExtra(Codes.KEY_KITCHEN_SMALL_DESCRIPTION,kitchenInfoList.get(position).get_CookingTime());
                        intent.putExtra(Codes.KEY_KITCHEN_EMAIL_ID,kitchenInfoList.get(position).get_EmailId());
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),"Error : "+databaseError.getMessage(),Toast.LENGTH_LONG).show();
                return;
            }
        });
    }
}