package android.g6.cricspot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class UserWithTeamActivity extends AppCompatActivity {

    Button exitTeamBtn, findPlayerBtn, findMatchBtn;
    TextView teamNameTxt, teamLocationTxt, txtErr;
    ListView playerListViewer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_with_team);

        exitTeamBtn = findViewById(R.id.exitTeamBtnInUserWithTeamPage);
        findPlayerBtn = findViewById(R.id.findPlayerBtnInUserWithTeamPage);
        findMatchBtn = findViewById(R.id.findMatchBtnInUserWithTeamPage);
        teamNameTxt = findViewById(R.id.teamNameTxtInUserWithTeamPage);
        teamLocationTxt = findViewById(R.id.locationTxtInUserWithTeamPage);
        txtErr = findViewById(R.id.txtErrInUserWithTeamPage);
        playerListViewer = findViewById(R.id.playerListInUserWithTeamPage);

    }
}
