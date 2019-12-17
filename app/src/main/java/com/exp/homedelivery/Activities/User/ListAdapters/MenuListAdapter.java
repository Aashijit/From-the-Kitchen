package com.exp.homedelivery.Activities.User.ListAdapters;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.exp.homedelivery.DataObjects.KitchenInfo;
import com.exp.homedelivery.DataObjects.MenuItems;
import com.exp.homedelivery.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class MenuListAdapter extends ArrayAdapter<MenuItems> {

    private final Activity context;
    private List<MenuItems> menuItemsList;

    FirebaseStorage storage = FirebaseStorage.getInstance();

    public MenuListAdapter(Activity context, List<MenuItems> menuItemsList)  {
        super(context, R.layout.item, menuItemsList);
        this.context=context;
        this.menuItemsList = menuItemsList;
    }


    public View getView(final int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.item, null,true);

        final TextView itemName = (TextView) rowView.findViewById(R.id.itemName);
        final TextView itemDescription = (TextView) rowView.findViewById(R.id.itemDescription);
        final TextView itemPrice = (TextView) rowView.findViewById(R.id.itemPrice);
        final TextView itemPriceDescription = (TextView) rowView.findViewById(R.id.itemPriceDescription);
        final ImageView itemPicture = (ImageView) rowView.findViewById(R.id.itemPicture);
        final CheckBox itemCheckBox = (CheckBox) rowView.findViewById(R.id.itemCheckBox);


        itemName.setText(menuItemsList.get(position).get_Name());
        itemDescription.setText(menuItemsList.get(position).get_Description());
        itemPrice.setText(context.getResources().getText(R.string.Rs).toString()+menuItemsList.get(position).get_Price());
        itemPriceDescription.setText(menuItemsList.get(position).get_PriceDescription());
        itemCheckBox.setChecked(menuItemsList.get(position).isSelected());

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

        itemCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                itemCheckBox.setChecked(isChecked);
                menuItemsList.get(position).setSelected(isChecked);
            }
        });
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