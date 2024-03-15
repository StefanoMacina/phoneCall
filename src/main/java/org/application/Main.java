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
        showOptions();
        getUserInputs();
        call();
    }

    public static void showOptions(){
        String options = """
                Select one option:
                    1 - add your account details
                    2 - start program
                    Q - to quit
                """;
        System.out.println(options);
    }

    public static void getUserInputs(){

        System.out.println("Enter your twilio account sid :");
        String ACCOUNT_SID = scanner.nextLine().trim();

        System.out.println("Enter your twilio auth token :");
        String AUTH_TOKEN = scanner.nextLine().trim();

        System.out.println("Enter your twilio phone number :");
        String from = scanner.nextLine().trim();

        System.out.println("enter a valid number to call");
        String to = scanner.nextLine().trim();

        user.setUserPhone(from);
        user.setACCOUNT_SID(ACCOUNT_SID);
        user.setAUTH_TOKEN(AUTH_TOKEN);
        user.setPhoneToCall(to);
    }

    public static void call() {
        String ACCOUNT_SID = user.getACCOUNT_SID();
        String AUTH_TOKEN = user.getAUTH_TOKEN();
        String from = user.getUserPhone();
        String to = user.getPhoneToCall();

        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        try{
            System.out.printf("...im calling: %s%n",to);
            Call call = Call.creator(
                    new PhoneNumber(to),
                    new PhoneNumber(from),
                    new URI("http://demo.twilio.com/docs/voice.xml")).create();
            System.out.println(call.getSid());
        } catch (Exception e){
            if(e instanceof ApiException){
                System.out.println(String.format("The number %s is not verified yet, try with another number or verify this number", to));
            } else if(e instanceof URISyntaxException){
                System.out.println("There was an error with URI");
            }else {
                System.out.println("there was an unknow error, try again or use different inputs" + e.getMessage());
            }
        }
    }
}