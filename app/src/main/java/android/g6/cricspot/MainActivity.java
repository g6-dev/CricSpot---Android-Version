package android.g6.cricspot;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText userName, password;
    Button logIn, createAccount;
    TextView noAccount;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Initializing the variables */
        userName = findViewById(R.id.nameInLoginPage);
        password = findViewById(R.id.passwordInLoginPage);
        logIn = findViewById(R.id.loginInLoginPage);
        createAccount = findViewById(R.id.createAccountInLoginPage);
        noAccount = findViewById(R.id.noAccountTextInLoginPage);
    }

    public void logInClickedInLoginPage(View view) {
        /* TODO: Authentication  must take place */
        intent = new Intent(MainActivity.this, HomeActivity.class);
        startActivity(intent);
    }

    public void createAccountClickedInLoginPage(View view) {
        /* Page redirected to Create Account page */
        intent = new Intent(MainActivity.this, CreateAccountActivity.class);
        startActivity(intent);
    }
}
