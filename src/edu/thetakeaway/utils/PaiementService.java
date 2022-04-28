package edu.thetakeaway.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import com.stripe.model.Token;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PaiementService {

    Customer a = new Customer();

    public PaiementService() {
        Stripe.apiKey = "pk_test_51KtMwZLoJGI49UjwVCoz6xDZ4IDtPTsFWv9fklZEswarUZGS5eiIuQyXgHzOZcYQPlS3uJJhcbg96WLjmEjQoLn300SjfgO9Qp";

    }

    public void RetrieveCustomer() {
        try {
            Customer a = Customer.retrieve("cus_LaY4di3X6RiCh2");

            //create card
            Map<String, Object> cardParams = new HashMap<String, Object>();
            cardParams.put("number", "4000056655665556");
            cardParams.put("exp_month", "12");
            cardParams.put("exp_year", "2022");
            cardParams.put("cvc", "456");
            Map<String, Object> tokenParams = new HashMap<String, Object>();
            tokenParams.put("card", cardParams);
            Token token = Token.create(tokenParams);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            System.out.println(gson.toJson(a));
        } catch (StripeException ex) {

        }
    }

    public void payement(int prix) {

        try {
            Customer a = Customer.retrieve("cus_LaY4di3X6RiCh2");

            //charge customer
            Map<String, Object> chargeParams = new HashMap<String, Object>();
            chargeParams.put("amount", (prix) + "00");
            chargeParams.put("currency", "usd");
            chargeParams.put("customer", a.getId());
            Charge.create(chargeParams);

        } catch (StripeException ex) {

        }
    }

}
