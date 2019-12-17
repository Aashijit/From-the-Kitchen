package com.exp.homedelivery.DataObjects;

import java.util.List;

public class KitchenInfo {

    private String _Address;
    private String _CookingTime;
    private String _DeliveryRadius;
    private String _DeliveryTime;
    private String _Description;
    private String _FoodDescription;
    private String _KitchenId;
    private String _Name;
    private String _PhotoUrl;
    private String _Price;
    private String _PriceDescription;
    private String _EmailId;
    private List<MenuItems> items;

    public String get_Address() {
        return _Address;
    }

    public void set_Address(String _Address) {
        this._Address = _Address;
    }

    public String get_CookingTime() {
        return _CookingTime;
    }

    public void set_CookingTime(String _CookingTime) {
        this._CookingTime = _CookingTime;
    }

    public String get_DeliveryRadius() {
        return _DeliveryRadius;
    }

    public void set_DeliveryRadius(String _DeliveryRadius) {
        this._DeliveryRadius = _DeliveryRadius;
    }

    public String get_DeliveryTime() {
        return _DeliveryTime;
    }

    public void set_DeliveryTime(String _DeliveryTime) {
        this._DeliveryTime = _DeliveryTime;
    }

    public String get_Description() {
        return _Description;
    }

    public void set_Description(String _Description) {
        this._Description = _Description;
    }

    public String get_FoodDescription() {
        return _FoodDescription;
    }

    public void set_FoodDescription(String _FoodDescription) {
        this._FoodDescription = _FoodDescription;
    }

    public String get_KitchenId() {
        return _KitchenId;
    }

    public void set_KitchenId(String _KitchenId) {
        this._KitchenId = _KitchenId;
    }

    public String get_Name() {
        return _Name;
    }

    public void set_Name(String _Name) {
        this._Name = _Name;
    }

    public String get_PhotoUrl() {
        return _PhotoUrl;
    }

    public void set_PhotoUrl(String _PhotoUrl) {
        this._PhotoUrl = _PhotoUrl;
    }

    public String get_Price() {
        return _Price;
    }

    public void set_Price(String _Price) {
        this._Price = _Price;
    }

    public String get_PriceDescription() {
        return _PriceDescription;
    }

    public void set_PriceDescription(String _PriceDescription) {
        this._PriceDescription = _PriceDescription;
    }

    public String get_EmailId() {
        return _EmailId;
    }

    public void set_EmailId(String _EmailId) {
        this._EmailId = _EmailId;
    }

    public List<MenuItems> getItems() {
        return items;
    }

    public void setItems(List<MenuItems> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "KitchenInfo{" +
                "_Address='" + _Address + '\'' +
                ", _CookingTime='" + _CookingTime + '\'' +
                ", _DeliveryRadius='" + _DeliveryRadius + '\'' +
                ", _DeliveryTime='" + _DeliveryTime + '\'' +
                ", _Description='" + _Description + '\'' +
                ", _FoodDescription='" + _FoodDescription + '\'' +
                ", _KitchenId='" + _KitchenId + '\'' +
                ", _Name='" + _Name + '\'' +
                ", _PhotoUrl='" + _PhotoUrl + '\'' +
                ", _Price='" + _Price + '\'' +
                ", _PriceDescription='" + _PriceDescription + '\'' +
                ", _EmailId='" + _EmailId + '\'' +
                ", items=" + items +
                '}';
    }
}