package com.vjti.blockchain.wallet.data;

import android.util.Log;

import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;

import de.adorsys.android.securestoragelibrary.SecurePreferences;

public class DataInSharedPreferences {

    private static final String TAG = "DataInSharedPreferences";


    public static void storingData(KeyPair pair, String name, String email) {
        Log.d(TAG, "storingKeyPair() invoked");
        String pubKey = getPublicKeyAsString(pair);
        String privateKey = getPrivateKeyAsString(pair);
        SecurePreferences.setValue("fullName", name);
        SecurePreferences.setValue("emailAddress", email);
        SecurePreferences.setValue("publicKey", pubKey);
        SecurePreferences.setValue("privateKey", privateKey);
        Log.d("publicKey", SecurePreferences.getStringValue("publicKey", ""));
    }

    public static String retrievingPublicKey(){
        Log.d(TAG, "retrievingPublicKey() invoked");
        String publicKey = SecurePreferences.getStringValue("publicKey", "");
        Log.d("PublicKeyString ", publicKey);

        return publicKey;
    }

    public static String retrievingPrivateKey(){
        Log.d(TAG, "retrievingPrivateKey() invoked");
        String privateKey = SecurePreferences.getStringValue("privateKey", "");
        Log.d("PrivateKeyString", privateKey);

        return privateKey;
    }

    private static String getPublicKeyAsString(KeyPair keyPair){
        Log.d(TAG, "getPublicKeyAsString() invoked");
        PublicKey mPublicKey = keyPair.getPublic();
        String publicKey = new String(android.util.Base64.encode(mPublicKey.getEncoded(), android.util.Base64.DEFAULT));
        publicKey = publicKey.replaceAll("\\s+", "");
        Log.d(TAG,"PublicKeyString: " +  publicKey);
        return publicKey;
    }

    private static String getPrivateKeyAsString(KeyPair keyPair){
        PrivateKey mPrivateKey = keyPair.getPrivate();
        String privateKey = null;
        privateKey = new String(android.util.Base64.encode(mPrivateKey.getEncoded(), android.util.Base64.DEFAULT));
        privateKey = privateKey.replaceAll("\\s+", "");
        Log.d(TAG,"PrivateKeyString: " +  privateKey);
        return privateKey;
    }

    public static PrivateKey getPrivateKeyFromString(String key) throws GeneralSecurityException {
        String privateKeyPEM = key;
        privateKeyPEM = privateKeyPEM.replace("-----BEGIN PRIVATE KEY-----\n", "");
        privateKeyPEM = privateKeyPEM.replace("-----END PRIVATE KEY-----", "");
        privateKeyPEM = privateKeyPEM.replace("\n", "");
        byte[] encoded;
        encoded = android.util.Base64.decode(privateKeyPEM, android.util.Base64.DEFAULT);
        KeyFactory kf = KeyFactory.getInstance("EC");
        PrivateKey privKey = kf.generatePrivate(new PKCS8EncodedKeySpec(encoded));
        return privKey;
    }

}
