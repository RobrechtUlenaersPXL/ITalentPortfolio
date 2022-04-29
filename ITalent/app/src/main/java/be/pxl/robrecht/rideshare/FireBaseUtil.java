package be.pxl.robrecht.rideshare;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.data.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import be.pxl.robrecht.rideshare.data.model.ITalentActivity;
import be.pxl.robrecht.rideshare.data.model.Ride;

public class FireBaseUtil {

    public static FirebaseDatabase mFireBaseDatabase;
    public static DatabaseReference mDatabaseReference;
    private static FireBaseUtil fireBaseUtil;
    public static ArrayList<ITalentActivity> activities;
    public static FirebaseAuth mFirebaseAuth;
    public static FirebaseAuth.AuthStateListener mAuthListener;
    public static FirebaseStorage mFirebaseStorage;
    public static StorageReference mStorageReference;
    private static final int RC_SIGN_IN = 123;
    private static Activity caller;
    private static String UserId;
    private static String UserName;
    private static String UserEmail;

    private FireBaseUtil(){};
    public static void openFireBaseReference(String fbDBName, Activity callerActivity){
        if (fireBaseUtil == null){
            fireBaseUtil = new FireBaseUtil();
            mFireBaseDatabase = FirebaseDatabase.getInstance();
            mFirebaseAuth = FirebaseAuth.getInstance();
            caller = callerActivity;
            mAuthListener = new FirebaseAuth.AuthStateListener() {
                @Override
                public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                    if(firebaseAuth.getCurrentUser() == null){
                        FireBaseUtil.signIn();
                    }

                }
            };
            connectstorage();
        }
        activities = new ArrayList<ITalentActivity>();
        mDatabaseReference = mFireBaseDatabase.getReference().child(fbDBName);
    }

    private static void signIn(){
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build());
        caller.startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .build(),RC_SIGN_IN);
    }

    public static void attachListener(){
        mFirebaseAuth.addAuthStateListener(mAuthListener);
    }
    public static void detachListener(){
        mFirebaseAuth.removeAuthStateListener(mAuthListener);
    }
    public static void connectstorage() {
        mFirebaseStorage = FirebaseStorage.getInstance();
        mStorageReference = mFirebaseStorage.getReference().child("ITalentMedia");
    }


}
