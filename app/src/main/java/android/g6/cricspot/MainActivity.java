package android.g6.cricspot;

import android.content.Context;
import android.content.Intent;
import android.g6.cricspot.CricClasses.DatabaseManager;
import android.g6.cricspot.CricObjects.Player;
import android.g6.cricspot.CricObjects.Team;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    final static String dbMemberNameForTeam = "Team";
    final static String dbMemberNameForPlayer = "Player";

    //----------------------------------------------------------------------------------------------
    //To store the user's name for the whole application program
    private static String user;

    public static String getUser() {
        return user;
    }

    public static void setUser(String user) {
        MainActivity.user = user;
    }
    //----------------------------------------------------------------------------------------------

    //----------------------------------------------------------------------------------------------
    //To store the user's player object for the whole application program
    private static Player userPlayerObject;

    public static Player getUserPlayerObject() {
        return userPlayerObject;
    }

    public static void setUserPlayerObject(Player userPlayerObject) {
        MainActivity.userPlayerObject = userPlayerObject;
    }
    //----------------------------------------------------------------------------------------------

    //----------------------------------------------------------------------------------------------
    //To store the user's team object for the whole application program
    private static Team userTeamObject;

    public static Team getUserTeamObject() {
        return userTeamObject;
    }

    public static void setUserTeamObject(Team userTeamObject) {
        MainActivity.userTeamObject = userTeamObject;
    }
    //----------------------------------------------------------------------------------------------

    EditText userName, password;
    Button logIn, createAccount, testBtn;
    TextView noAccount, txtErr;
    Intent intent;
    DatabaseManager dbManager = new DatabaseManager();

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
        txtErr = findViewById(R.id.txtErrInLoginPage);
        testBtn = findViewById(R.id.testerInMain);

        //Initially keep offline database of online lists -> -+- Team, Players -+-
        dbManager.retrieveAllTeamsFromDatabase(dbMemberNameForTeam);
        dbManager.retrieveAllPlayersFromDatabase(dbMemberNameForPlayer);
    }

    public void logInClickedInLoginPage(View view) {
        /* TODO: Authentication  must take place */

        if(isInternetOn()) {
            if (userName.getText().toString().equalsIgnoreCase("") ||
                    password.getText().toString().equalsIgnoreCase("")) {
                txtErr.setText(R.string.fieldsEmpty);
            } else {
                txtErr.setText("");
                intent = new Intent(MainActivity.this, UserWithoutTeamActivity.class);
                //intent.putExtra("tester", userName.getText().toString());
                setUser(userName.getText().toString());
                startActivity(intent);
            }
        }else{
            txtErr.setText(R.string.noInternet);
        }


    }

    public void createAccountClickedInLoginPage(View view) {
        /* Page redirected to Create Account page */
        if(isInternetOn()) {
            txtErr.setText("");
            intent = new Intent(MainActivity.this, CreateAccountActivity.class);
            startActivity(intent);
        }else{
            txtErr.setText(R.string.noInternet);
        }
    }

    /* To check the internet connection */
    public Boolean isInternetOn(){
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            return true;
        } else {
            return false;
        }
    }

    public void testingPages(View view) {
        intent = new Intent(MainActivity.this, UserWithTeamActivity.class);
        startActivity(intent);
    }
}
