package android.g6.cricspot;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Initializing the variables */
        userName = findViewById(R.id.nameInHomePage);
        password = findViewById(R.id.passwordInHomePage);
        logIn = findViewById(R.id.loginInHomePage);
        createAccount = findViewById(R.id.createAccountInHomePage);
        noAccount = findViewById(R.id.noAccountTextInHomePage);
    }

    public void logInClickedInHomePage(View view) {
    }

    public void createAccountClickedInHomePage(View view) {
    }
}
