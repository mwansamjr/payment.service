package com.paymentapp.PaymentCheckout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.paymentapp.Helpers.CollectionHelper;
import com.paymentapp.Models.PaymentRequest;
import com.paymentapp.R;

import java.math.BigDecimal;

public class Paycheckout extends AppCompatActivity {
    private EditText customerFirstName, customerLastName, email, phoneNumber;
    private Button btn;
    private final BigDecimal pay = BigDecimal.valueOf(5.00);
    private final String description = "Monthly Subscription";
    private final CollectionHelper collectionHelper = new CollectionHelper();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paycheckout);

        customerFirstName = findViewById(R.id.customerFirstName);
        customerLastName = findViewById(R.id.customerLastName);
        email = findViewById(R.id.email);
        phoneNumber = findViewById(R.id.phoneNumber);
        btn = findViewById(R.id.payButton);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                collectionHelper.generateToken(Paycheckout.this);
                collectionHelper.requestPayment(new PaymentRequest(description,customerFirstName.toString(), customerLastName.toString(), email.toString(),phoneNumber.toString(),pay), Paycheckout.this);
                collectionHelper.getPaymentStatus(Paycheckout.this);


            }
        });
        





    }
}