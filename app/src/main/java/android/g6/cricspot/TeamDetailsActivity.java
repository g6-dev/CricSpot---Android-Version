package android.g6.cricspot;

import android.content.Intent;
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

public class TeamDetailsActivity extends AppCompatActivity {

    TextView teamName, teamLocation;
    Button joinBtn;
    ListView playerListViewer;
    ArrayAdapter<String> listAdapter;
    List<String> list;
    Intent intent;
    String intentString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_details);

        teamName = findViewById(R.id.teamNameTxtInTeamDetailsPage);
        teamLocation = findViewById(R.id.locationTxtInTeamDetailsPage);
        joinBtn = findViewById(R.id.joinBtnInTeamDetailsPage);
        playerListViewer = findViewById(R.id.playerListInTeamDetailsPage);

        intentString = getIntent().getStringExtra("tester");

        list = new ArrayList<>();
        list.add("Ravi123");
        list.add("Xnrt5");
        list.add("IamTenX");
        list.add("Nafli_Sarpa");
        list.add("ScdDCdvV");

        listAdapter = new ArrayAdapter<String>(TeamDetailsActivity.this,
                android.R.layout.simple_list_item_1,list);
        playerListViewer.setAdapter(listAdapter);
    }

    public void joinBtnClickedInTeamDetailsPage(View view) {
        Toast.makeText(TeamDetailsActivity.this, "You clicked "+intentString, Toast.LENGTH_LONG).show();
    }
}
