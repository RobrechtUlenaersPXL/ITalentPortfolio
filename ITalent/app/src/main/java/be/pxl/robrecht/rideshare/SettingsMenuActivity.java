package be.pxl.robrecht.rideshare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.Locale;

public class SettingsMenuActivity extends AppCompatActivity {
private static final int numberCheck = 654321;
private SwitchCompat toggleDark;
private String imageUrl;
private Button languageSelector;
private static final String SHARED_PREFS = "sharedPreds";
private static final String DARK_MODE = "darkMode";
private static final String LANGUAGE = "language";
final int[] checkedLang = {-1};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_menu);
        languageSelector = findViewById(R.id.languageSelection);


        toggleDark = findViewById(R.id.NightModeSwitch);

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        SharedPreferences.Editor prefEditor = sharedPreferences.edit();
        boolean darkmode = sharedPreferences.getBoolean(DARK_MODE,false);
        toggleDark.setChecked(darkmode);


        toggleDark.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

                }
                prefEditor.putBoolean(DARK_MODE,isChecked);
                prefEditor.apply();

            }
        });
        languageSelector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(SettingsMenuActivity.this);
                alertDialog.setIcon(R.drawable.space_invader);
                alertDialog.setTitle(getString(R.string.choose_language));
                final String[] listItems = getResources().getStringArray(R.array.supported_languages);
                alertDialog.setSingleChoiceItems(listItems, checkedLang[0], new DialogInterface.OnClickListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        checkedLang[0] = which;
                        setLocale(listItems[which]);
                        Toast.makeText(view.getContext(), getString(R.string.changed_language),Toast.LENGTH_LONG).show();
                        dialog.dismiss();
                    }
                });

                alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog customAlertDialog = alertDialog.create();
                customAlertDialog.show();
            }
            });
    }

    private void setLocale(String lang){
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.setLocale(locale);
        this.getResources().updateConfiguration(config,getBaseContext().getResources().getDisplayMetrics());
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        SharedPreferences.Editor prefEditor = sharedPreferences.edit();
        prefEditor.putString(LANGUAGE,lang);
        prefEditor.apply();
        Intent intent = new Intent(this,RidesListActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        super.onCreateOptionsMenu(menu);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.settings_menu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.action_logout:
                AuthUI.getInstance()
                        .signOut(this)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Log.d("Logout","user logged out from rideshare app");
                                FireBaseUtil.attachListener();
                            }
                        });
                FireBaseUtil.detachListener();
                finish();
                return true;
            case R.id.action_aboutme:
                Intent intentAboutMe = new Intent(this, AboutMeActivity.class);
                startActivity(intentAboutMe);
                return true;
            case R.id.action_settings:
                Intent intent = new Intent(this, SettingsMenuActivity.class);
                startActivity(intent);
                finish();
                return true;
            case R.id.action_home:
                finish();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }


}