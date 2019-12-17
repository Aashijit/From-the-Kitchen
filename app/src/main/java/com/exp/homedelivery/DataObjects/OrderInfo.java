package com.exp.homedelivery.DataObjects;

import java.util.List;

public class OrderInfo {

    private String _KitchenName;
    private String _KitchenEmailId;
    private String _KitchenAddress;
    private String _UserName;
    private String _UserEmailId;
    private String _OrderTimestamp;
    private String _UserMobileSNumber;
    private String _KitchenMobileNumber;
    private List<MenuItems> items;

    public String get_KitchenName() {
        return _KitchenName;
    }

    public void set_KitchenName(String _KitchenName) {
        this._KitchenName = _KitchenName;
    }

    public String get_KitchenEmailId() {
        return _KitchenEmailId;
    }

    public void set_KitchenEmailId(String _KitchenEmailId) {
        this._KitchenEmailId = _KitchenEmailId;
    }

    public String get_KitchenAddress() {
        return _KitchenAddress;
    }

    public void set_KitchenAddress(String _KitchenAddress) {
        this._KitchenAddress = _KitchenAddress;
    }

    public String get_UserName() {
        return _UserName;
    }

    public void set_UserName(String _UserName) {
        this._UserName = _UserName;
    }

    public String get_UserEmailId() {
        return _UserEmailId;
    }

    public void set_UserEmailId(String _UserEmailId) {
        this._UserEmailId = _UserEmailId;
    }

    public String get_OrderTimestamp() {
        return _OrderTimestamp;
    }

    public void set_OrderTimestamp(String _OrderTimestamp) {
        this._OrderTimestamp = _OrderTimestamp;
    }

    public String get_UserMobileSNumber() {
        return _UserMobileSNumber;
    }

    public void set_UserMobileSNumber(String _UserMobileSNumber) {
        this._UserMobileSNumber = _UserMobileSNumber;
    }

    public String get_KitchenMobileNumber() {
        return _KitchenMobileNumber;
    }

    public void set_KitchenMobileNumber(String _KitchenMobileNumber) {
        this._KitchenMobileNumber = _KitchenMobileNumber;
    }

    public List<MenuItems> getItems() {
        return items;
    }

    public void setItems(List<MenuItems> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "OrderInfo{" +
                "_KitchenName='" + _KitchenName + '\'' +
                ", _KitchenEmailId='" + _KitchenEmailId + '\'' +
                ", _KitchenAddress='" + _KitchenAddress + '\'' +
                ", _UserName='" + _UserName + '\'' +
                ", _UserEmailId='" + _UserEmailId + '\'' +
                ", _OrderTimestamp='" + _OrderTimestamp + '\'' +
                ", _UserMobileSNumber='" + _UserMobileSNumber + '\'' +
                ", _KitchenMobileNumber='" + _KitchenMobileNumber + '\'' +
                ", items=" + items +
                '}';
    }
}