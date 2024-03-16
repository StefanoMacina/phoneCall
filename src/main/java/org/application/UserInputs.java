package org.application;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;

@Getter @Setter @NoArgsConstructor
public class UserInputs {
    private String ACCOUNT_SID, AUTH_TOKEN, userPhone, phoneToCall;

    public void setUserPhone(String userPhone) {
        if(isPhoneValid(userPhone)) {
            this.userPhone = userPhone;
        }
    }

    public void setPhoneToCall(String phoneToCall) {
       if(isPhoneValid(phoneToCall)) {
           this.phoneToCall = phoneToCall;
       }
    }

    public boolean isPhoneValid(String phoneNumber){
        if(phoneNumber.startsWith("+")){
            for(int i = 1;i<phoneNumber.length();i++){
                if(!Character.isDigit(phoneNumber.charAt(i))){
                    System.out.printf("Phone number should include only numbers!, the character %c at position %d is not valid %n",
                            phoneNumber.charAt(i),
                            phoneNumber.indexOf(phoneNumber.charAt(i)));
                    return false;
                }
            }
            return true;
        } else {
            System.out.println("phone number have to include a prefix!");
            return false;
        }
    }
}
