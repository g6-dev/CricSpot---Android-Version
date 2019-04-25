package android.g6.cricspot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

        /* Hardcoded values for testing purpose */
        teamList.add(new Team("G6 Cricketers", "Colombo", new Player(), new Player(),
                new Player(), new Player(), new Player(), false));
        teamList.add(new Team("Black Smith", "Jaffna", new Player(), new Player(),
                new Player(), new Player(), new Player(), false));
        teamList.add(new Team("Moon Risers", "Trincomalee", new Player(), new Player(),
                new Player(), new Player(), new Player(), false));
        teamList.add(new Team("Golden Bat", "Galle", new Player(), new Player(),
                new Player(), new Player(), new Player(), false));
        teamList.add(new Team("Street Warriors", "Nuwara Eliya", new Player(), new Player(),
                new Player(), new Player(), new Player(), false));

        for (Team team: teamList){
            nameAndLocationList.add(new NameAndLocation(team.getName(), team.getLocation()));
        }

        listAdapter = new TwoRowListAdapter(this, R.layout.listview_2row_activity,
                nameAndLocationList);

        teamsListViewer.setAdapter(listAdapter);
    }
}
