package android.g6.cricspot;

import android.content.Intent;
import android.g6.cricspot.CricObjects.Team;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class UserWithTeamActivity extends AppCompatActivity {

    Button exitTeamBtn, findPlayerBtn, findMatchBtn;
    TextView teamNameTxt, teamLocationTxt, txtErr;
    ListView playerListViewer;
    Team team;
    List<String> listOfPlayers = new ArrayList<>();
    ArrayAdapter<String> listAdapter;
    Intent intent;

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

        team = MainActivity.getUserTeamObject();
        teamNameTxt.setText(team.getName());
        teamLocationTxt.setText(team.getLocation());

        listOfPlayers.add(team.getPlayer1());
        listOfPlayers.add(team.getPlayer2());
        listOfPlayers.add(team.getPlayer3());
        listOfPlayers.add(team.getPlayer4());
        listOfPlayers.add(team.getPlayer5());

        listAdapter = new ArrayAdapter<String>(UserWithTeamActivity.this,
                android.R.layout.simple_list_item_1,listOfPlayers);
        playerListViewer.setAdapter(listAdapter);
    }

    public void exitTeamIsClickedInUserWithTeamPage(View view) {
        Toast.makeText(UserWithTeamActivity.this, "Yet in maintenance", Toast.LENGTH_LONG).show();
    }

    public void findPlayerClickedInUserWithTeamPage(View view) {
        Toast.makeText(UserWithTeamActivity.this, "Yet in maintenance", Toast.LENGTH_LONG).show();
    }

    public void findMatchClickedInUserWithTeamPage(View view) {
        Toast.makeText(UserWithTeamActivity.this, "Yet in maintenance", Toast.LENGTH_LONG).show();
    }

    public void signOutClickedInUserWithTeamPage(View view) {
        intent = new Intent(UserWithTeamActivity.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        //Don't move behind till signing out! :D
    }
}
