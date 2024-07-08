package com.paymentapp.PaymentCheckout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.paymentapp.Helpers.CollectionHelper;
import com.paymentapp.LocalStorage.SharedPrefs;
import com.paymentapp.Models.PaymentRequest;
import com.paymentapp.Models.SmsRequest;
import com.paymentapp.R;

import java.math.BigDecimal;

public class Paycheckout extends AppCompatActivity {
    private EditText customerFirstName, customerLastName, email, phoneNumber;
    private Button btn;
    private final int pay = 5;
    private final String description = "Monthly Subscription";
    private final CollectionHelper collectionHelper = new CollectionHelper();
    private final String sender = "MyCompany";
    private final String message = "My Message";
    private final String recipient = "ContactNumber";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paycheckout);

        //Add a text Violation Class to validate the data coming from the edit text here ignore at your own risk
        customerFirstName = findViewById(R.id.customerFirstName);
        customerLastName = findViewById(R.id.customerLastName);
        email = findViewById(R.id.email);
        phoneNumber = findViewById(R.id.phoneNumber);
        btn = findViewById(R.id.payButton);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PaymentRequest paymentRequest = new PaymentRequest(description,customerFirstName.getText().toString(),customerLastName.getText().toString(),email.getText().toString(),phoneNumber.getText().toString(),pay);
                collectionHelper.generateToken(Paycheckout.this, paymentRequest);
//                collectionHelper.requestPayment(Paycheckout.this, new PaymentRequest(description,customerFirstName.toString(), customerLastName.toString(), email.toString(),phoneNumber.toString(),pay));
//                collectionHelper.getPaymentStatus(Paycheckout.this);
//                //Trigger This for your sms
//                collectionHelper.sendSms(Paycheckout.this, new SmsRequest(sender,message,recipient));
            }
        });
        





    }
}