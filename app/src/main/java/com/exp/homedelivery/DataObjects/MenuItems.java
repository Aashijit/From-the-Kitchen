package com.exp.homedelivery.DataObjects;

public class MenuItems {

    private String _Description;
    private String _Name;
    private String _PhotoUrl;
    private String _Price;
    private String _PriceDescription;
    private boolean isSelected;

    public String get_Description() {
        return _Description;
    }

    public void set_Description(String _Description) {
        this._Description = _Description;
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

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    @Override
    public String toString() {
        return "MenuItems{" +
                "_Description='" + _Description + '\'' +
                ", _Name='" + _Name + '\'' +
                ", _PhotoUrl='" + _PhotoUrl + '\'' +
                ", _Price='" + _Price + '\'' +
                ", _PriceDescription='" + _PriceDescription + '\'' +
                ", isSelected=" + isSelected +
                '}';
    }
}