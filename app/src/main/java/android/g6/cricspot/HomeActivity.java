package android.g6.cricspot;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

    Button viewTeamBtn, viewPlayersBtn, signOutBtn;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        /* Initialize Variables */
        viewTeamBtn = findViewById(R.id.viewTeamBtnInHomePage);
        viewPlayersBtn = findViewById(R.id.viewPlayersBtnInHomePage);
        signOutBtn = findViewById(R.id.signOutBtnInHomePage);
    }

    public void viewTeamClickedInHomePage(View view) {
    }

    public void viewPlayersClickedInHomePage(View view) {
    }

    public void signOutClickedInHomePage(View view) {
        /* Leave home page and go back */
        intent = new Intent(HomeActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
