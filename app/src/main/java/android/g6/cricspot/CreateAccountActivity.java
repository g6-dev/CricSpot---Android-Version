package android.g6.cricspot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CreateAccountActivity extends AppCompatActivity {

    TextView nameTxt, userNameTxt, passwordTxt, ageTxt, phoneTxt, errTxt;
    EditText nameE, userNameE, passwordE, ageE, phoneE;
    String name, userName, password, age, phone;
    Button createAccountBtn;
    Player player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        /* Initialize variables */
        nameTxt = findViewById(R.id.nameTxtInCreateAccountPage);
        userNameTxt = findViewById(R.id.userNameTxtInCreateAccountPage);
        passwordTxt = findViewById(R.id.passwordTxtInCreateAccountPage);
        ageTxt = findViewById(R.id.ageTxtInCreateAccountPage);
        phoneTxt = findViewById(R.id.phoneTxtInCreateAccountPage);
        errTxt = findViewById(R.id.errorTxtInCreateAccountPage);

        nameE = findViewById(R.id.nameInCreateAccountPage);
        userNameE = findViewById(R.id.userNameInCreateAccountPage);
        passwordE = findViewById(R.id.passwordInCreateAccountPage);
        ageE = findViewById(R.id.ageInCreateAccountPage);
        phoneE = findViewById(R.id.phoneInCreateAccountPage);

        createAccountBtn = findViewById(R.id.createAccountBtnInCreateAccountPage);
    }

    public void createAccountClickedInCreateAccountPage(View view) {

        /* Get values from user */
        name = nameE.getText().toString();
        userName = userNameE.getText().toString();
        password = passwordE.getText().toString();
        age = ageE.getText().toString();
        phone = phoneE.getText().toString();
        /*TODO: Validations must take place*/

        player = new Player(name, userName, password, age, phone, null);
        /*TODO: Add this object to fire base*/
    }
}
