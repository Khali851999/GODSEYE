package com.example.godseye;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.alimuzaffar.lib.pin.PinEntryEditText;

public class otpverification extends AppCompatActivity {
    TextView resendotp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpverification);
        Toolbar toolbarotp=findViewById(R.id.toolbarotp);
        setSupportActionBar(toolbarotp);
        toolbarotp.setTitleTextColor(getResources().getColor(R.color.colorwhite));

        resendotp=findViewById(R.id.resendotp);
        resendotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//             default   snackbar
                Snackbar snack=Snackbar.make(findViewById(android.R.id.content),"OTP Resent",Snackbar.LENGTH_SHORT)
                        .setAction("VIEW", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                            }
                        });

                snack.setActionTextColor(Color.WHITE);
                snack.getView().setBackgroundResource(R.color.colorPrimaryDark);
                TextView textView = (TextView)snack.getView().findViewById(android.support.design.R.id.snackbar_text);
                textView.setTextColor(Color.WHITE);
                snack.show();
            }
        });

        final PinEntryEditText pinEntry = (PinEntryEditText) findViewById(R.id.txt_pin_entry);
        if (pinEntry != null) {
            pinEntry.setOnPinEnteredListener(new PinEntryEditText.OnPinEnteredListener() {
                @Override
                public void onPinEntered(CharSequence str) {
                    if (str.toString().equals("123456")) {
                        Toast.makeText(otpverification.this, "SUCCESS", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(otpverification.this,home.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(otpverification.this, "FAIL", Toast.LENGTH_SHORT).show();
                        pinEntry.setText(null);
                    }
                }
            });
        }
    }
}
