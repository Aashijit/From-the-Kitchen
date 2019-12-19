package com.exp.homedelivery.Activities.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.exp.homedelivery.Activities.Admin.ListAdapters.ReceivedOrderListAdapter;
import com.exp.homedelivery.DataObjects.OrderInfo;
import com.exp.homedelivery.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AdminReceivedOrders extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseReferenceKitchenInfo = database.getReference("KitchenInfo");
    DatabaseReference databaseReferenceAccountInfo = database.getReference("AccountInfo");
    DatabaseReference databaseReferenceOrders = database.getReference("OrderInfo");

    private ReceivedOrderListAdapter receivedOrderListAdapter;
    private ListView listViewAllOrders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_received_orders);

        listViewAllOrders = (ListView) findViewById(R.id.listViewAllOrders);

        fetchOrdersRealTime();
    }

    private void fetchOrdersRealTime() {

        databaseReferenceOrders.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<OrderInfo> orderInfoList = new ArrayList<>();
                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){

                    OrderInfo orderInfo = dataSnapshot1.getValue(OrderInfo.class);

                    if(orderInfo == null){
                        Toast.makeText(getApplicationContext(),"Order info is null",Toast.LENGTH_LONG).show();
                        continue;
                    }
                    orderInfoList.add(orderInfo);
                }

                receivedOrderListAdapter = new ReceivedOrderListAdapter(AdminReceivedOrders.this, orderInfoList);
                listViewAllOrders.setAdapter(receivedOrderListAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),"Error : "+databaseError.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
}