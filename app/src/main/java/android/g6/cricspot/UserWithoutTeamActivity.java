package android.g6.cricspot;

import android.content.Intent;
import android.g6.cricspot.CricClasses.DatabaseManager;
import android.g6.cricspot.CricClasses.TwoRowListAdapter;
import android.g6.cricspot.CricObjects.NameAndLocation;
import android.g6.cricspot.CricObjects.Team;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UserWithoutTeamActivity extends AppCompatActivity {

    final static String dbMemberNameForTeam = "Team";

    TextView heyUserTxt, joinTeamTxt;
    Button createTeamBtn, loadTeamsBtn;
    ListView teamsListViewer;
    TwoRowListAdapter listAdapter;
    Team team;
    List<Team> teamList;
    List<NameAndLocation> nameAndLocationList;
    Intent intent;
    String intentString;
    DatabaseManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_without_team);

        heyUserTxt = findViewById(R.id.heyTxtInUserWithoutTeamPage);
        joinTeamTxt = findViewById(R.id.joinTeamTxtInUserWithoutTeamPage);
        createTeamBtn = findViewById(R.id.createTeamBtnInUserWithoutTeamPage);
        loadTeamsBtn = findViewById(R.id.loadTeamsBtnInUserWithoutTeamPage);
        teamsListViewer = findViewById(R.id.teamListInUserWithoutTeamPage);

        teamList = new ArrayList<>();
        nameAndLocationList = new ArrayList<>();

        dbManager = new DatabaseManager();

        intentString = getIntent().getStringExtra("tester");
        heyUserTxt.setText("Hey "+intentString);

        //teamList = dbManager.retrieveAllTeamsFromDatabase(dbMemberNameForTeam);
        System.out.println(">>>>> Referencing...");
        final DatabaseReference dbReference = FirebaseDatabase.getInstance().getReference().child(dbMemberNameForTeam);
        Log.d(">>>>>", "Starting method");

        teamList.clear();
        dbReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.d(">>>>>", "On 1 st method");

                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                    Log.d(">>>>>", "On 2 nd method");
                    team = postSnapshot.getValue(Team.class);
                    teamList.add(team);

                    // here you can access to name property like university.name
                    System.out.println(">>>>> Retrieving team -> "+ team);
                }
                //dbReference.removeEventListener(this);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getMessage());
            }
        });

        /* Hardcoded values for testing purpose */
//        teamList.add(new Team("G6 Cricketers", "Colombo", "no", "no",
//                "no", "no", "no", false));
//        teamList.add(new Team("Black Smith", "Jaffna", "no", "no",
//                "no", "no", "no", false));
//        teamList.add(new Team("Moon Risers", "Trincomalee", "no", "no",
//                "no", "no", "no", false));
//        teamList.add(new Team("Golden Bat", "Galle", "no", "no",
//                "no", "no", "no", false));
//        teamList.add(new Team("Street Warriors", "Nuwara Eliya", "no", "no",
//                "no", "no", "no", false));

//        System.out.println(">>>>> teamlist size = "+teamList.size());
//        for (Team team: teamList){
//            System.out.println(">>>>> Team : "+team);
//            nameAndLocationList.add(new NameAndLocation(team.getName(), team.getLocation()));
//        }
//
//        listAdapter = new TwoRowListAdapter(this, R.layout.listview_2row_activity,
//                nameAndLocationList);
//
//        teamsListViewer.setAdapter(listAdapter);
//
//        /* ListViewer onClick Listener */
//        teamsListViewer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                String selectedItem = ((TextView) view.findViewById(R.id.row1)).getText().toString();
//
//                intent = new Intent(UserWithoutTeamActivity.this, TeamDetailsActivity.class);
//                intent.putExtra("tester", selectedItem);
//                startActivity(intent);
//                //Toast.makeText(UserWithoutTeamActivity.this, "Yet in Maintenance", Toast.LENGTH_SHORT).show();
//            }
//        });

    }

    public void createTeamClickedInUserWithoutTeamPage(View view) {
        /*TODO: Create A team */
    }

    public void loadTeamsIsClickedInUserWithoutTeamPage(View view) {
        loadTeamsBtn.setVisibility(View.INVISIBLE);
        teamsListViewer.setVisibility(View.VISIBLE);

        nameAndLocationList.clear();

        System.out.println(">>>>> team list size = "+teamList.size());
        for (Team team: teamList){
            System.out.println(">>>>> Team : "+team);
            nameAndLocationList.add(new NameAndLocation(team.getName(), team.getLocation()));
        }

        listAdapter = new TwoRowListAdapter(this, R.layout.listview_2row_activity,
                nameAndLocationList);

        teamsListViewer.setAdapter(listAdapter);

        /* ListViewer onClick Listener */
        teamsListViewer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = ((TextView) view.findViewById(R.id.row1)).getText().toString();

                intent = new Intent(UserWithoutTeamActivity.this, TeamDetailsActivity.class);
                intent.putExtra("tester", selectedItem);
                startActivity(intent);
                //Toast.makeText(UserWithoutTeamActivity.this, "Yet in Maintenance", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
