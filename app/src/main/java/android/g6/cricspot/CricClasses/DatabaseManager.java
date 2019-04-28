package android.g6.cricspot.CricClasses;

import android.g6.cricspot.CricObjects.Player;
import android.g6.cricspot.CricObjects.Team;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {

    //----------------------------------------------------------------------------------------------
    //-------- STATIC LIST TO ACCESS FIREBASE DATA === PLAYER --------------------------------------
    private Player player;
    private static List<Player> playersList = new ArrayList<>();

    public static List<Player> getPlayersList() {
        return playersList;
    }

    public static void setPlayersList(List<Player> playersList) {
        DatabaseManager.playersList = playersList;
    }

    private static Boolean isPlayersRetrieved;

    public static Boolean getIsPlayersRetrieved() {
        return isPlayersRetrieved;
    }

    public static void setIsPlayersRetrieved(Boolean isPlayersRetrieved) {
        DatabaseManager.isPlayersRetrieved = isPlayersRetrieved;
    }
//----------------------------------------------------------------------------------------------

    //----------------------------------------------------------------------------------------------
    //-------- STATIC LIST TO ACCESS FIREBASE DATA === TEAM ----------------------------------------
    private Team team;
    private static List<Team> teamsList = new ArrayList<>();

    public static List<Team> getTeamsList() {
        return teamsList;
    }

    public static void setTeamsList(List<Team> teamsList) {
        DatabaseManager.teamsList = teamsList;
    }

    private static Boolean isTeamsRetrieved;

    public static Boolean getIsTeamsRetrieved() {
        return isTeamsRetrieved;
    }

    public static void setIsTeamsRetrieved(Boolean isTeamsRetrieved) {
        DatabaseManager.isTeamsRetrieved = isTeamsRetrieved;
    }
//----------------------------------------------------------------------------------------------

    public DatabaseManager() {
    }

    //*********************************************************************************************
    //********************** DATABASE FUNCTIONS >>>>>>>>>> PLAYER OBJECT <<<<<<<<<<<<<*************
    //*********************************************************************************************

    public void addPlayerToFirebase(String dbMemberName, Player player){
        DatabaseReference dbReference = FirebaseDatabase.getInstance().getReference().child(dbMemberName);
        System.out.println(">>>> Adding player into fire base");
        /*dbReference.push().setValue(player);  // Add player with unknown key number*/
        dbReference.child(player.getName()).setValue(player);
    }

    /*
    * dbMemberName is the database Name. Ex: 'Player'
    * dbChildName is the player key. Ex: 'test1'
     */
    public Player retrieveOnePlayerFromDatabase(String dbMemberName, String dbChildName){

        player = null;
        DatabaseReference dbReference = FirebaseDatabase.getInstance().getReference().child(dbMemberName).child(dbChildName);
        dbReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String name = dataSnapshot.child("name").getValue().toString();
                String userName = dataSnapshot.child("userName").getValue().toString();
                String password = dataSnapshot.child("password").getValue().toString();
                String age = dataSnapshot.child("age").getValue().toString();
                String phone = dataSnapshot.child("phone").getValue().toString();
                String team = dataSnapshot.child("team").getValue().toString();

                Log.d(">>>>>", "["+name+", "+userName+", "+password+", "+age+", "+phone+
                        ", "+team+"]");
                player = new Player(name, userName, password, age, phone, team);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return player;
    }

    /*
     * Link: https://stackoverflow.com/questions/38652007/how-to-retrieve-specific-list-of-data-from-firebase
     * Run this method & access the static List
     */
    public void retrieveAllPlayersFromDatabase(String dbMemberName){
        DatabaseReference dbReference = FirebaseDatabase.getInstance().getReference().child(dbMemberName);
        Log.d(">>>>>", "Starting method");
        isPlayersRetrieved = false;

        dbReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.d(">>>>>", "On 1 st method");
                playersList.clear();
                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                    Log.d(">>>>>", "On 2 nd method");
                    player = postSnapshot.getValue(Player.class);
                    playersList.add(player);

                    // here you can access to name property like university.name
                    System.out.println(">>>>> Retrieving player -> "+ player);

                    if(!isPlayersRetrieved) {
                        isPlayersRetrieved = true;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getMessage());
            }
        });
    }

    //*********************************************************************************************
    //********************** DATABASE FUNCTIONS >>>>>>>>>> TEAM OBJECT <<<<<<<<<<<<<***************
    //*********************************************************************************************

    public void addTeamToFirebase(String dbMemberName, Team team){

        DatabaseReference dbReference = FirebaseDatabase.getInstance().getReference().child(dbMemberName);
        System.out.println(">>>> Adding data into fire base");
        /*dbReference.push().setValue(player);  // Add player with unknown key number*/
        dbReference.child(team.getName()).setValue(team);
    }

    public Team retrieveOneTeamFromDatabase(String dbMemberName, String dbChildName){

        team = null;
        DatabaseReference dbReference = FirebaseDatabase.getInstance().getReference().child(dbMemberName).child(dbChildName);
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
                Boolean isPlaying = Boolean.valueOf(dataSnapshot.child("isPlaying").getValue().toString());

                Log.d(">>>>>", "["+name+", "+location+", "+player1+", "+player2+", "
                        +player3+","+player4+", "+player5+", "+isPlaying+"]");
                team = new Team(name, location, player1, player2, player3, player4, player5, isPlaying);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return team;
    }

    /*
     * Link: https://stackoverflow.com/questions/38652007/how-to-retrieve-specific-list-of-data-from-firebase
     * Run this method & access the static List
     */
    public void retrieveAllTeamsFromDatabase(String dbMemberName){

        DatabaseReference dbReference = FirebaseDatabase.getInstance().getReference().child(dbMemberName);
        Log.d(">>>>>", "Starting method");
        isTeamsRetrieved = false;

        dbReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.d(">>>>>", "On 1 st method");
                teamsList.clear();
                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                    Log.d(">>>>>", "On 2 nd method");
                    team = postSnapshot.getValue(Team.class);
                    teamsList.add(team);

                    // here you can access to name property like university.name
                    System.out.println(">>>>> Retrieving team -> "+ team);

                    if(!isTeamsRetrieved){
                        isTeamsRetrieved = true;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getMessage());
            }
        });
    }
}
