package android.g6.cricspot;

import android.g6.cricspot.CricClasses.DatabaseManager;
import android.g6.cricspot.CricClasses.TwoRowListAdapter;
import android.g6.cricspot.CricObjects.NameAndLocation;
import android.g6.cricspot.CricObjects.Player;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class FindPlayerActivity extends AppCompatActivity {

    TextView txt;
    ListView listView;
    List<Player> listOfAllPlayers = new ArrayList<>();
    List<NameAndLocation> playersNameLocationList = new ArrayList<>();
    TwoRowListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_player);

        txt = findViewById(R.id.txtInFindPlayersPage);
        listView = findViewById(R.id.listViewerInFindPlayerPage);

        listOfAllPlayers.clear();
        listOfAllPlayers = DatabaseManager.getPlayersList();

        playersNameLocationList.clear();
        for (Player player: listOfAllPlayers){
            playersNameLocationList.add(new NameAndLocation(player.getUserName(), "colombo"));
        }

        listAdapter = new TwoRowListAdapter(FindPlayerActivity.this, R.layout.listview_2row_activity,
                playersNameLocationList);
        listView.setAdapter(listAdapter);
    }
}
