package org.application;

public class UserInputs {
    private String ACCOUNT_SID;
    private String AUTH_TOKEN;
    private String userPhone;
    private String phoneToCall;

    public UserInputs() {
    }

    public String getACCOUNT_SID() {
        return ACCOUNT_SID;
    }

    public void setACCOUNT_SID(String ACCOUNT_SID) {
        this.ACCOUNT_SID = ACCOUNT_SID;
    }

    public String getAUTH_TOKEN() {
        return AUTH_TOKEN;
    }

    public void setAUTH_TOKEN(String AUTH_TOKEN) {
        this.AUTH_TOKEN = AUTH_TOKEN;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getPhoneToCall() {
        return phoneToCall;
    }

    public void setPhoneToCall(String phoneToCall) {
        this.phoneToCall = phoneToCall;
    }
}
