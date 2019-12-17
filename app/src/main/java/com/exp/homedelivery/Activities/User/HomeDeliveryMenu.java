package com.exp.homedelivery.Activities.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.exp.homedelivery.Activities.User.ListAdapters.MenuListAdapter;
import com.exp.homedelivery.DataObjects.AccountInfo;
import com.exp.homedelivery.DataObjects.KitchenInfo;
import com.exp.homedelivery.DataObjects.MenuItems;
import com.exp.homedelivery.DataObjects.OrderInfo;
import com.exp.homedelivery.R;
import com.exp.homedelivery.Utils.Codes;
import com.exp.homedelivery.Utils.ValidationUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HomeDeliveryMenu extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseReferenceKitchenInfo = database.getReference("KitchenInfo");
    DatabaseReference databaseReferenceAccountInfo = database.getReference("AccountInfo");
    DatabaseReference databaseReferenceOrders = database.getReference("OrderInfo");
    private MenuListAdapter menuListAdapter;


    private String KitchenName;
    private String KitchenDescription;
    private String KitchenSDesc;
    private String KitchenEmailId;
    private KitchenInfo kitchenInfo;

    private TextView homeDeliveryTeamName;
    private TextView homeDeliveryTeamDescription;
    private TextView homeDeliveryTeamSchedule;
    private ListView homeDeliveryMenuList;

    List<MenuItems> orderedMenuItems = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_delivery_menu);

        firebaseAuth = FirebaseAuth.getInstance();

        homeDeliveryTeamName = (TextView) findViewById(R.id.homeDeliveryTeamName);
        homeDeliveryTeamDescription = (TextView) findViewById(R.id.homeDeliveryTeamDescription);
        homeDeliveryTeamSchedule = (TextView) findViewById(R.id.homeDeliveryTeamSchedule);
        homeDeliveryMenuList = (ListView) findViewById(R.id.homeDeliveryMenuList);

        KitchenName = getIntent().getStringExtra(Codes.KEY_KITCHEN_NAME);
        KitchenDescription = getIntent().getStringExtra(Codes.KEY_KITCHEN_DESCRIPTION);
        KitchenSDesc = getIntent().getStringExtra(Codes.KEY_KITCHEN_SMALL_DESCRIPTION);
        KitchenEmailId = getIntent().getStringExtra(Codes.KEY_KITCHEN_EMAIL_ID);


        //Fetch the menu of the kitchen
        fetchKitchenMenu();
    }

    private void fetchKitchenMenu() {

        databaseReferenceKitchenInfo.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot){

                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){

                    kitchenInfo = dataSnapshot1.getValue(KitchenInfo.class);

                    if(kitchenInfo == null)
                    {
                        Toast.makeText(getApplicationContext(),"Kitchen info Null",Toast.LENGTH_LONG).show();
                        continue;
                    }

                    if(kitchenInfo.get_EmailId().equalsIgnoreCase(KitchenEmailId)){
                           menuListAdapter = new MenuListAdapter(HomeDeliveryMenu.this,kitchenInfo.getItems());
                           homeDeliveryMenuList.setAdapter(menuListAdapter);
                           break;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),databaseError.getMessage(),Toast.LENGTH_LONG).show();
            }
        });

    }

    public void goToCheckOut(View view) {
        //Data Validation to be done -- Check if any one of the item has been selected
        if(kitchenInfo == null)
            return;

        if(!ValidationUtils.checkIfSingleMenuItemIsSelected(kitchenInfo.getItems()))
        {
            Toast.makeText(getApplicationContext(),"Please select at least one item",Toast.LENGTH_LONG).show();
            return;
        }


        for(MenuItems menuItems : kitchenInfo.getItems()){
            if(menuItems.isSelected())
                orderedMenuItems.add(menuItems);
        }

        databaseReferenceAccountInfo.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    AccountInfo accountInfo = dataSnapshot1.getValue(AccountInfo.class);

                    if(accountInfo == null){
                        Toast.makeText(getApplicationContext(),"Account Info ",Toast.LENGTH_LONG).show();
                        return;
                    }

                    if(accountInfo.get_Email().equalsIgnoreCase(firebaseAuth.getCurrentUser().getEmail())){

                        //Populate the order info
                        OrderInfo orderInfo = new OrderInfo();
                        orderInfo.set_KitchenAddress(kitchenInfo.get_Address());
                        orderInfo.set_KitchenEmailId(kitchenInfo.get_EmailId());
                        orderInfo.set_KitchenMobileNumber("");
                        orderInfo.set_KitchenName(kitchenInfo.get_Name());
                        orderInfo.set_UserEmailId(firebaseAuth.getCurrentUser().getEmail());
                        orderInfo.set_OrderTimestamp(new Date().toString());
                        orderInfo.set_UserName(accountInfo.get_Name());
                        orderInfo.set_UserMobileSNumber(accountInfo.get_MobileNumber());
                        orderInfo.setItems(orderedMenuItems);


                        String key = databaseReferenceOrders.push().getKey();

                        if(key == null){
                            Toast.makeText(getApplicationContext(),"Key is null",Toast.LENGTH_LONG).show();
                            return;
                        }

                        databaseReferenceOrders.child(key).setValue(orderInfo).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(getApplicationContext(),"Ordered Successfully !!!",Toast.LENGTH_LONG).show();

                                    Intent intent = new Intent(HomeDeliveryMenu.this, MyOrder.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        });
                        break;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),"Error : "+databaseError.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
}