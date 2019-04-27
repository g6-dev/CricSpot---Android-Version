package android.g6.cricspot;

import android.content.Context;
import android.content.Intent;
import android.g6.cricspot.CricClasses.DatabaseManager;
import android.g6.cricspot.CricObjects.Team;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class TeamDetailsActivity extends AppCompatActivity {

    TextView teamName, teamLocation, txtErr;
    Button joinBtn;
    ListView playerListViewer;
    ArrayAdapter<String> listAdapter;
    List<Team> playersList;
    List<String> playersNameList = new ArrayList<>();
    String intentString;
    Team selectedTeam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_details);

        teamName = findViewById(R.id.teamNameTxtInTeamDetailsPage);
        teamLocation = findViewById(R.id.locationTxtInTeamDetailsPage);
        joinBtn = findViewById(R.id.joinBtnInTeamDetailsPage);
        playerListViewer = findViewById(R.id.playerListInTeamDetailsPage);
        txtErr = findViewById(R.id.txtErrInTeamDetailsPage);

        intentString = getIntent().getStringExtra("tester");

        playersList = UserWithoutTeamActivity.getTeamList();

        for (Team team: playersList){
            if(team.getName().equalsIgnoreCase(intentString)){
                selectedTeam = team;
            }
        }

        playersNameList.add(selectedTeam.getPlayer1());
        playersNameList.add(selectedTeam.getPlayer2());
        playersNameList.add(selectedTeam.getPlayer3());
        playersNameList.add(selectedTeam.getPlayer4());
        playersNameList.add(selectedTeam.getPlayer5());

        teamName.setText(selectedTeam.getName());
        teamLocation.setText(selectedTeam.getLocation());

        listAdapter = new ArrayAdapter<String>(TeamDetailsActivity.this,
                android.R.layout.simple_list_item_1,playersNameList);
        playerListViewer.setAdapter(listAdapter);
    }

    public void joinBtnClickedInTeamDetailsPage(View view) {
        if(isInternetOn()) {
            txtErr.setText("");
            Toast.makeText(TeamDetailsActivity.this, "You clicked " + intentString, Toast.LENGTH_LONG).show();
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
