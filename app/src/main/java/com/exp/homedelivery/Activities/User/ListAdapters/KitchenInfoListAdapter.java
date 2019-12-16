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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.exp.homedelivery.DataObjects.KitchenInfo;
import com.exp.homedelivery.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.List;


public class KitchenInfoListAdapter extends ArrayAdapter<KitchenInfo> {

    private final Activity context;
    private List<KitchenInfo> kitchenInfoList;

    FirebaseStorage storage = FirebaseStorage.getInstance();

    public KitchenInfoListAdapter(Activity context, List<KitchenInfo> kitchenInfoList)  {
        super(context, R.layout.homedeliveryteam, kitchenInfoList);
        this.context=context;
        this.kitchenInfoList = kitchenInfoList;
    }


    public View getView(final int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.homedeliveryteam, null,true);

        final TextView deliveryTeamName = (TextView) rowView.findViewById(R.id.deliveryTeamName);
        final TextView deliveryTeamBestMenu = (TextView) rowView.findViewById(R.id.deliveryTeamBestMenu);
        final TextView deliveryTeamMinimumPrice = (TextView) rowView.findViewById(R.id.deliveryTeamMinimumPrice);
        final TextView deliveryTeamPriceDescription = (TextView) rowView.findViewById(R.id.deliveryTeamPriceDescription);
        final ImageView deliveryTeamPicture = (ImageView) rowView.findViewById(R.id.deliveryTeamPicture);


        deliveryTeamName.setText(kitchenInfoList.get(position).get_Name());
        deliveryTeamBestMenu.setText(kitchenInfoList.get(position).get_Description());
        deliveryTeamMinimumPrice.setText(kitchenInfoList.get(position).get_Price());
        deliveryTeamPriceDescription.setText(kitchenInfoList.get(position).get_PriceDescription());

        StorageReference storageReference = storage.getReferenceFromUrl(kitchenInfoList.get(position).get_PhotoUrl());

        try {
            final File localFile = File.createTempFile("images", "jpg");
            storageReference.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                    deliveryTeamPicture.setImageBitmap(bitmap);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    Toast.makeText(context,exception.getLocalizedMessage(),Toast.LENGTH_LONG).show();
                }
            });
        } catch (IOException e ) {
            Toast.makeText(context,e.getLocalizedMessage(),Toast.LENGTH_LONG).show();
        }
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