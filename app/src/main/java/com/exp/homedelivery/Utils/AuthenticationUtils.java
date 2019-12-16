package com.exp.homedelivery.Utils;

import com.exp.homedelivery.DataObjects.AccountInfo;
import com.google.firebase.database.DataSnapshot;

public class AuthenticationUtils {


    public static AccountInfo getValidUser(DataSnapshot dataSnapshot, String phoneNumber, String password){
        for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
            AccountInfo accountInfo = dataSnapshot1.getValue(AccountInfo.class);
            //TODO: Consider saving the hashed password using the Salt to ensure the security of the application
            if(accountInfo!= null){
                if(accountInfo.get_MobileNumber().equalsIgnoreCase(phoneNumber) && accountInfo.get_HashedPassword().equalsIgnoreCase(password)){
                    return accountInfo;
                }
            }
        }
        return null;
    }

}
