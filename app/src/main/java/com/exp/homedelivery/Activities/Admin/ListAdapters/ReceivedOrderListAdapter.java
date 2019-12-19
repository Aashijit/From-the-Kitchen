package com.exp.homedelivery.Activities.Admin.ListAdapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.exp.homedelivery.DataObjects.MenuItems;
import com.exp.homedelivery.DataObjects.OrderInfo;
import com.exp.homedelivery.R;
import com.google.firebase.storage.FirebaseStorage;

import java.util.List;

public class ReceivedOrderListAdapter extends ArrayAdapter<OrderInfo> {

    private final Activity context;
    private List<OrderInfo> orderInfoList;

    FirebaseStorage storage = FirebaseStorage.getInstance();

    public ReceivedOrderListAdapter(Activity context, List<OrderInfo> orderInfoList)  {
        super(context, R.layout.adminorder, orderInfoList);
        this.context=context;
        this.orderInfoList = orderInfoList;
    }


    public View getView(final int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.adminorder, null,true);

        final TextView kitchenName = (TextView) rowView.findViewById(R.id.adminKitchenName);
        final TextView kitchenOrderMenu = (TextView) rowView.findViewById(R.id.adminKitchenOrderMenu);
        final TextView kitchenTotalPrice = (TextView) rowView.findViewById(R.id.adminKitchenTotalPrice);
        final Button button = (Button) rowView.findViewById(R.id.adminDeliveryButton);

        kitchenName.setText(orderInfoList.get(position).get_UserName());

        String menu="";
        double totalPrice = 0.00;
        for(MenuItems menuItems : orderInfoList.get(position).getItems()){
            menu += menuItems.get_Name()+" ";
            totalPrice += Double.parseDouble(menuItems.get_Price());
        }

        kitchenOrderMenu.setText(menu);
        kitchenTotalPrice.setText(context.getResources().getText(R.string.Rs).toString()+totalPrice+"");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




            }
        });

//        StorageReference storageReference = storage.getReferenceFromUrl(menuItemsList.get(position).get_PhotoUrl());
//
//        try {
//            final File localFile = File.createTempFile("images", "jpg");
//            storageReference.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
//                @Override
//                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
//                    Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
//                    itemPicture.setImageBitmap(bitmap);
//                }
//            }).addOnFailureListener(new OnFailureListener() {
//                @Override
//                public void onFailure(@NonNull Exception exception) {
//                    Toast.makeText(context,exception.getLocalizedMessage(),Toast.LENGTH_LONG).show();
//                }
//            });
//        } catch (IOException e ) {
//            Toast.makeText(context,e.getLocalizedMessage(),Toast.LENGTH_LONG).show();
//        }


        return rowView;
    }

    public void animateText(TextView textView) {
        Animation animation1 =
                AnimationUtils.loadAnimation(context,
                        R.anim.blink);
        textView.startAnimation(animation1);
    }

    public void animateButton(Button button) {
        Animation animation1 =
                AnimationUtils.loadAnimation(context,
                        R.anim.blink);
        button.startAnimation(animation1);
    }

}