package com.example.sid24rane.blockcanteen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.sid24rane.blockcanteen.data.KeyInSharedPreferences;
import com.example.sid24rane.blockcanteen.utilities.TransactionUtils;

public class SendActivity extends AppCompatActivity {

    private Button send;
    private TextView name;
    private TextView email;
    private String receiverPublicKey;
    private EditText amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);

        init();
        fetchRecieverUserDetails(receiverPublicKey);

    }


    private void init() {

        Intent i = getIntent();
        receiverPublicKey = i.getStringExtra("publicKey");

        send = (Button) findViewById(R.id.send);
        name = (TextView) findViewById(R.id.username);
        email = (TextView) findViewById(R.id.email);
        amount = (EditText) findViewById(R.id.amount);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //String sender_pub_key = "MFYwEAYHKoZIzj0CAQYFK4EEAAoDQgAExcIsvLH3vegArqtP7wEdyly11xAcrpV4IBIUCVM+HXoPMMpNFX8hYDjOPL4IUT4swqDkrhj1gS+XWukiGpttzQ==";
                String publicKey = KeyInSharedPreferences.retrievingPublicKey(SendActivity.this);
                TransactionUtils.makeTransaction(amount.getText().toString(),publicKey,receiverPublicKey, SendActivity.this);
            }
        });
    }

    private void fetchRecieverUserDetails(String receiverPublicKey) {
        //TODO retreive user details from firebase
    }

}