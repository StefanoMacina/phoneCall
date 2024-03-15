package org.application;

import com.twilio.Twilio;
import com.twilio.exception.ApiException;
import com.twilio.rest.api.v2010.account.Call;
import com.twilio.type.PhoneNumber;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);
    public static UserInputs user = new UserInputs();
    public static void main(String[] args)  {

        boolean flag = true;
        while(flag){
            showOptions();
            String selected = scanner.nextLine().trim();
            switch (selected){
                case "1" -> {
                    System.out.println("ENTER YOUR TWILIO DETAILS");
                    getUserInputs();
                }
                case "2" -> {
                    getPhoneToCall();
                    call(user.getPhoneToCall());
                }
                case "3" -> {
                    showUserDetails();
                }
                case "q","Q" -> {
                    System.out.println("QUIT");
                    flag = false;
                }
                default -> System.out.printf("invalid key : %s%n", selected);
            }
        }
    }

    public static void showOptions(){
        String options = """
                Select one option:
                    1 - add your account details
                    2 - start program
                    3 - show your details
                    Q - to quit
                """;
        System.out.println(options);
    }

    public static void getUserInputs(){
        System.out.println("twilio account sid :");
        String ACCOUNT_SID = scanner.nextLine().trim();

        System.out.println("twilio auth token :");
        String AUTH_TOKEN = scanner.nextLine().trim();

        System.out.println("twilio phone number :");
        String from = scanner.nextLine().trim();

        System.out.println("TWILIO DETAILS ADDEDD SUCCESSFULLY");

        user.setUserPhone(from);
        user.setACCOUNT_SID(ACCOUNT_SID);
        user.setAUTH_TOKEN(AUTH_TOKEN);
    }

    public static void showUserDetails(){
        System.out.println("ACCOUNT_SID : " + user.getACCOUNT_SID() + "\n" +
                            "AUTH_TOKEN : " + user.getAUTH_TOKEN() + "\n" +
                            "TWILIO PHONE NUMBER : " + user.getUserPhone() + "\n");
    }

    public static void getPhoneToCall(){
        if(user.getACCOUNT_SID().isBlank()){
            System.out.println("SET YOUR TWILIO ACCOUNT_SID FIRST");
        } else if(user.getAUTH_TOKEN().isBlank()){
            System.out.println("SET YOUR TWILIO ACCOUNT_TOKEN FIRST");
        } else if(user.getUserPhone().isBlank()){
            System.out.println("SET YOUR TWILIO PHONE NUMBER FIRST");
        } else {
            System.out.println("Enter phone number to call");
            user.setPhoneToCall(scanner.nextLine().trim());
        }
    }



    public static void call(String phoneNumberToCall) {
        String ACCOUNT_SID = user.getACCOUNT_SID();
        String AUTH_TOKEN = user.getAUTH_TOKEN();
        String from = user.getUserPhone();

        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        try{
            System.out.printf("trying to call: %s%n",phoneNumberToCall);
            Call call = Call.creator(
                    new PhoneNumber(phoneNumberToCall),
                    new PhoneNumber(from),
                    new URI("http://demo.twilio.com/docs/voice.xml")).create();
            System.out.println(call.getSid());
        } catch (Exception e){
            if(e instanceof ApiException){
                System.out.printf("The number %s is not verified yet, try with another number or verify this number%n", phoneNumberToCall);
            } else if(e instanceof URISyntaxException){
                System.out.println("There was an error with URI");
            }else {
                System.out.println("there was an unknow error, try again or use different inputs" + e.getMessage());
            }
        }
    }
}