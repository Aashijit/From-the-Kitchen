package com.exp.homedelivery.DataObjects;

public class AccountInfo {

    private String _AccountId;
    private String _Email;
    private String _HashedPassword;
    private String _MobileNumber;
    private String _Salt;
    private String _RegistrationMode;
    private String _Name;
    private String _PhotoUrl;

    public String get_AccountId() {
        return _AccountId;
    }

    public void set_AccountId(String _AccountId) {
        this._AccountId = _AccountId;
    }

    public String get_Email() {
        return _Email;
    }

    public void set_Email(String _Email) {
        this._Email = _Email;
    }

    public String get_HashedPassword() {
        return _HashedPassword;
    }

    public void set_HashedPassword(String _HashedPassword) {
        this._HashedPassword = _HashedPassword;
    }

    public String get_MobileNumber() {
        return _MobileNumber;
    }

    public void set_MobileNumber(String _MobileNumber) {
        this._MobileNumber = _MobileNumber;
    }

    public String get_Salt() {
        return _Salt;
    }

    public void set_Salt(String _Salt) {
        this._Salt = _Salt;
    }

    public String get_RegistrationMode() {
        return _RegistrationMode;
    }

    public void set_RegistrationMode(String _RegistrationMode) {
        this._RegistrationMode = _RegistrationMode;
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

    @Override
    public String toString() {
        return "AccountInfo{" +
                "_AccountId='" + _AccountId + '\'' +
                ", _Email='" + _Email + '\'' +
                ", _HashedPassword='" + _HashedPassword + '\'' +
                ", _MobileNumber='" + _MobileNumber + '\'' +
                ", _Salt='" + _Salt + '\'' +
                ", _RegistrationMode='" + _RegistrationMode + '\'' +
                ", _Name='" + _Name + '\'' +
                ", _PhotoUrl='" + _PhotoUrl + '\'' +
                '}';
    }
}