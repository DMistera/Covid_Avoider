package com.example.covidavoider;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.covidavoider.models.User;
import com.example.covidavoider.singletons.UserService;

public class RegisterActivity extends AppCompatActivity {

    EditText usernameEditText;
    EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
    }

    public void onLogin(View view) {
        startActivity(new Intent(this, LoginActivity.class));
    }

    public void onRegister(View view) {
        User user = new User();
        user.username = usernameEditText.getText().toString();
        user.password = passwordEditText.getText().toString();
        if(user.username.length()<5){
            AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
            builder.setMessage("nick need to have at least 5 characters");
            builder.setCancelable(true);
            AlertDialog dialog = builder.create();
            dialog.show();
        }
        else if(user.password.length()<5){
            AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
            builder.setMessage("password need to have at least 5 characters");
            builder.setCancelable(true);
            AlertDialog dialog = builder.create();
            dialog.show();
        }
        else {
            UserService.getInstance().register(user);

            // Display success message
            AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
            builder.setMessage("You have created an account! Try to sign in now!");
            builder.setCancelable(true);
            builder.setPositiveButton(
                    "Ok",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                        }
                    });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }
}