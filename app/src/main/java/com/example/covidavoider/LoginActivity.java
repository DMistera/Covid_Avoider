package com.example.covidavoider;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import com.example.covidavoider.HistoryActivity;
import com.example.covidavoider.MainActivity;
import com.example.covidavoider.R;
import com.example.covidavoider.SettingsActivity;
import com.example.covidavoider.singletons.UserService;

public class LoginActivity extends AppCompatActivity {

    EditText usernameEditText;
    EditText passwordEditText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
    }

    public void onLogin(View view) {
        String userName = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        if(UserService.getInstance().login(userName, password)) {
            startActivity(new Intent(this, MapActivity.class));
        }
        else {
            // Display error message
            AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
            builder.setMessage("Invalid username or password! Try again!");
            builder.setCancelable(true);
            builder.setPositiveButton(
                    "Ok",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();;
                        }
                    });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }

    public void onRegister(View view) {
        startActivity(new Intent(this, RegisterActivity.class));
    }


}