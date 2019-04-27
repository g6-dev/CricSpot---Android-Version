package android.g6.cricspot;

import android.content.Context;
import android.g6.cricspot.CricObjects.Team;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class CreateTeamActivity extends AppCompatActivity {

    TextView teamNameTxt, teamLocationTxt, txtErr;
    EditText teamNameE, teamLocationE;
    Button nextBtn;

    String teamName, teamLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_team);

        teamNameTxt = findViewById(R.id.teamNameTxtInCreateTeamPage);
        teamLocationTxt = findViewById(R.id.teamLocationTxtInCreateTeamPage);
        txtErr = findViewById(R.id.txtErrInCreateTeamPage);
        teamNameE = findViewById(R.id.teamNameInCreateTeamPage);
        teamLocationE = findViewById(R.id.teamLocationInCreateTeamPage);
        nextBtn = findViewById(R.id.nextBtnInCreateTeamPage);
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

    public void nextClickedInCreateTeamPage(View view) {
        if(isInternetOn()){
            teamName = teamNameE.getText().toString();
            teamLocation = teamLocationE.getText().toString();

            if(!(teamName.equalsIgnoreCase("") || teamLocation.equalsIgnoreCase(""))){
                txtErr.setText("");
                Toast.makeText(CreateTeamActivity.this, "In maintenance", Toast.LENGTH_SHORT).show();
                List<Team> list = UserWithoutTeamActivity.getTeamList();
                for (Team team: list) {
                    System.out.println(">>>>> Testing static: "+ team.getName());
                }
            }else{
                txtErr.setText("Some fields are empty!");
            }
        }else{
            txtErr.setText("Can not reach the internet!");
        }
    }
}
