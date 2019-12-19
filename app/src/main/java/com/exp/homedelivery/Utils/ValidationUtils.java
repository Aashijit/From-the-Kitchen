package com.exp.homedelivery.Utils;

import com.exp.homedelivery.DataObjects.MenuItems;

import java.util.List;

public class ValidationUtils
{
    public static boolean checkIfSingleMenuItemIsSelected(List<MenuItems> menuItemsList){
        for(MenuItems menuItems : menuItemsList){
            if(menuItems.isSelected()){
                return true;
            }
        }
        return false;
    }
}
