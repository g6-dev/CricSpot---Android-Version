package android.g6.cricspot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ViewTeamActivity extends AppCompatActivity {

    Button createTeamBtn, myTeamBtn;
    TextView heyTxt, joinAnotherTxt;
    ListView listViewer;
    List<Team> teamList;
    List<NameAndLocation> nameAndLocationList;
    TwoRowListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_team);

        /* Initialize values */
        createTeamBtn = findViewById(R.id.createTeamBtnInViewTeamPage);
        myTeamBtn = findViewById(R.id.myTeamBtnInViewTeamPage);
        heyTxt = findViewById(R.id.heyTxtInViewTeamPage);
        joinAnotherTxt = findViewById(R.id.joinTeamTxtInViewTeamPage);
        listViewer = findViewById(R.id.teamListInViewTeamPage);

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

        listViewer.setAdapter(listAdapter);
    }

    public void createTeamIsClickedInViewTeamPage(View view) {
    }

    public void myTeamClickedInViewTeamPage(View view) {
    }
}
