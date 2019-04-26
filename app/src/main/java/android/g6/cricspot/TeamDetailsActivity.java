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

    final static String dbMemberNameForTeam = "Team";

    TextView teamName, teamLocation, txtErr;
    Button joinBtn, refreshBtn;
    ListView playerListViewer;
    ArrayAdapter<String> listAdapter;
    List<String> playersList;
    Intent intent;
    String intentString;
    DatabaseManager dbManager;
    Team team;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_details);

        teamName = findViewById(R.id.teamNameTxtInTeamDetailsPage);
        teamLocation = findViewById(R.id.locationTxtInTeamDetailsPage);
        txtErr = findViewById(R.id.txtErrInTeamDetailsPage);
        joinBtn = findViewById(R.id.joinBtnInTeamDetailsPage);
        refreshBtn = findViewById(R.id.refreshBtnInTeamDetailsPage);
        playerListViewer = findViewById(R.id.playerListInTeamDetailsPage);

        intentString = getIntent().getStringExtra("tester");

        playersList = new ArrayList<>();
        //team = null;

        DatabaseReference dbReference =
                FirebaseDatabase.getInstance().getReference().child(dbMemberNameForTeam).child(intentString);
        dbReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String name = dataSnapshot.child("name").getValue().toString();
                String location = dataSnapshot.child("location").getValue().toString();
                String player1 = dataSnapshot.child("player1").getValue().toString();
                String player2 = dataSnapshot.child("player2").getValue().toString();
                String player3 = dataSnapshot.child("player3").getValue().toString();
                String player4 = dataSnapshot.child("player4").getValue().toString();
                String player5 = dataSnapshot.child("player5").getValue().toString();
                //Boolean isPlaying = (Boolean) dataSnapshot.child("isPlaying").getValue();

                Log.d(">>>>>", "["+name+", "+location+", "+player1+", "+player2+", "
                        +player3+","+player4+", "+player5+", "+"onProcess"+"]");
                team = new Team(name, location, player1, player2, player3, player4, player5, false);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

//        System.out.println(">>>>> Team retrieved -> " + team);

        //Testing purpose
//        list = new ArrayList<>();
//        list.add("Ravi123");
//        list.add("Xnrt5");
//        list.add("IamTenX");
//        list.add("Nafli_Sarpa");
//        list.add("ScdDCdvV");

//        listAdapter = new ArrayAdapter<String>(TeamDetailsActivity.this,
//                android.R.layout.simple_list_item_1,list);
//        playerListViewer.setAdapter(listAdapter);
    }

    public void joinBtnClickedInTeamDetailsPage(View view) {
        Toast.makeText(TeamDetailsActivity.this, "You clicked "+intentString, Toast.LENGTH_LONG).show();
    }

    public void refreshClickedOnTeamDetailsPage(View view) {
        if(isInternetOn()){
            if(team != null){

                txtErr.setText("");
                refreshBtn.setVisibility(View.INVISIBLE);
                teamName.setVisibility(View.VISIBLE);
                teamLocation.setVisibility(View.VISIBLE);
                playerListViewer.setVisibility(View.VISIBLE);
                joinBtn.setVisibility(View.VISIBLE);

                teamName.setText(team.getName());
                teamLocation.setText(team.getLocation());

                System.out.println(">>>>> players -> "+team.getPlayer1());

                playersList.add(team.getPlayer1());
                playersList.add(team.getPlayer2());
                playersList.add(team.getPlayer3());
                playersList.add(team.getPlayer4());
                playersList.add(team.getPlayer5());

                listAdapter = new ArrayAdapter<String>(TeamDetailsActivity.this,
                  android.R.layout.simple_list_item_1,playersList);
                playerListViewer.setAdapter(listAdapter);

            }else{
                txtErr.setText("No items found, try refreshing the page!");
            }
        }else{
            txtErr.setText("Can not reach the Internet!");
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
