package android.g6.cricspot;

import android.content.Intent;
import android.g6.cricspot.CricClasses.TwoRowListAdapter;
import android.g6.cricspot.CricObjects.NameAndLocation;
import android.g6.cricspot.CricObjects.Player;
import android.g6.cricspot.CricObjects.Team;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class UserWithoutTeamActivity extends AppCompatActivity {

    TextView heyUserTxt, joinTeamTxt;
    Button createTeamBtn;
    ListView teamsListViewer;
    TwoRowListAdapter listAdapter;
    List<Team> teamList;
    List<NameAndLocation> nameAndLocationList;
    Intent intent;
    String intentString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_without_team);

        heyUserTxt = findViewById(R.id.heyTxtInUserWithoutTeamPage);
        joinTeamTxt = findViewById(R.id.joinTeamTxtInUserWithoutTeamPage);
        createTeamBtn = findViewById(R.id.createTeamBtnInUserWithoutTeamPage);
        teamsListViewer = findViewById(R.id.teamListInUserWithoutTeamPage);

        teamList = new ArrayList<>();
        nameAndLocationList = new ArrayList<>();

        intentString = getIntent().getStringExtra("tester");
        heyUserTxt.setText("Hey "+intentString);

        /* Hardcoded values for testing purpose */
        teamList.add(new Team("G6 Cricketers", "Colombo", "no", "no",
                "no", "no", "no", false));
        teamList.add(new Team("Black Smith", "Jaffna", "no", "no",
                "no", "no", "no", false));
        teamList.add(new Team("Moon Risers", "Trincomalee", "no", "no",
                "no", "no", "no", false));
        teamList.add(new Team("Golden Bat", "Galle", "no", "no",
                "no", "no", "no", false));
        teamList.add(new Team("Street Warriors", "Nuwara Eliya", "no", "no",
                "no", "no", "no", false));

        for (Team team: teamList){
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
