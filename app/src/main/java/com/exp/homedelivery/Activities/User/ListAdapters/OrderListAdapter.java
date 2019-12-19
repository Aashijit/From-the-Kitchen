package com.exp.homedelivery.Activities.User.ListAdapters;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.exp.homedelivery.DataObjects.MenuItems;
import com.exp.homedelivery.DataObjects.OrderInfo;
import com.exp.homedelivery.R;
import com.google.firebase.storage.FirebaseStorage;

import java.util.List;

public class OrderListAdapter extends ArrayAdapter<OrderInfo> {

    private final Activity context;
    private List<OrderInfo> orderInfoList;

    FirebaseStorage storage = FirebaseStorage.getInstance();

    public OrderListAdapter(Activity context, List<OrderInfo> orderInfoList)  {
        super(context, R.layout.userorder, orderInfoList);
        this.context=context;
        this.orderInfoList = orderInfoList;
    }


    public View getView(final int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.userorder, null,true);

        final TextView kitchenName = (TextView) rowView.findViewById(R.id.kitchenName);
        final TextView kitchenOrderMenu = (TextView) rowView.findViewById(R.id.kitchenOrderMenu);
        final TextView kitchenTotalPrice = (TextView) rowView.findViewById(R.id.kitchenTotalPrice);

        kitchenName.setText(orderInfoList.get(position).get_KitchenName());

        String menu="";
        double totalPrice = 0.00;
        for(MenuItems menuItems : orderInfoList.get(position).getItems()){
            menu += menuItems.get_Name()+" ";
            totalPrice += Double.parseDouble(menuItems.get_Price());
        }

       kitchenOrderMenu.setText(menu);
       kitchenTotalPrice.setText(context.getResources().getText(R.string.Rs).toString()+totalPrice+"");


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