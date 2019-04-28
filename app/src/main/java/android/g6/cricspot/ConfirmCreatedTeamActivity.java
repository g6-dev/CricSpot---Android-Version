package android.g6.cricspot;

import android.content.Context;
import android.g6.cricspot.CricClasses.DatabaseManager;
import android.g6.cricspot.CricObjects.Team;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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

public class ConfirmCreatedTeamActivity extends AppCompatActivity {

    static final String dbMemberName = "Team";

    TextView teamName, teamLocation, txtErr;
    ListView playerListViewer;
    Button createTeamBtn;

    Team team;
    List<String> playersNameList = new ArrayList<>();
    ArrayAdapter<String> listAdapter;
    DatabaseManager dbManager = new DatabaseManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_created_team);

        teamName = findViewById(R.id.teamNameTxtInConfirmCreatedTeamPage);
        teamLocation = findViewById(R.id.teamLocationTxtInConfirmCreatedTeamPage);
        txtErr = findViewById(R.id.txtErrInConfirmCreatedTeamPage);
        playerListViewer = findViewById(R.id.playerListInConfirmCreatedTeamPage);
        createTeamBtn = findViewById(R.id.createMyTeamInConfirmCreatedTeamPage);

        team = CreateTeamActivity.getThisTeam();

        System.out.println(">>>>> Is correct team -> "+team);

        playersNameList.add(team.getPlayer1());
        playersNameList.add(team.getPlayer2());
        playersNameList.add(team.getPlayer3());
        playersNameList.add(team.getPlayer4());
        playersNameList.add(team.getPlayer5());

        teamName.setText(team.getName());
        teamLocation.setText(team.getLocation());

        listAdapter = new ArrayAdapter<String>(ConfirmCreatedTeamActivity.this,
                android.R.layout.simple_list_item_1,playersNameList);
        playerListViewer.setAdapter(listAdapter);
    }

    public void createMyTeamClickedInConfirmCreatedTeamPage(View view) {
        if(isInternetOn()){
            txtErr.setText("");
            dbManager.addTeamToFirebase(dbMemberName, team);
            /* TODO: Update players profile 'team' section. */
            Toast.makeText(ConfirmCreatedTeamActivity.this, "Team is added", Toast.LENGTH_LONG).show();
            /* TODO: Create the UserWithTeamPage and link it from here. */

            //---------- SETTING USER'S TEAM OBJECT STATICALLY - IN MAIN ACTIVITY ------------------
            MainActivity.setUserTeamObject(team);
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
}
