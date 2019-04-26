package android.g6.cricspot.CricClasses;

import android.g6.cricspot.CricObjects.Player;
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

    Player player;
    List<Player> playerList = new ArrayList<>();

    public DatabaseManager() {
    }

    public void addToFirebase(DatabaseReference dbReference, Player player){
        System.out.println(">>>> Adding data into fire base");
        /*dbReference.push().setValue(player);  // Add player with unknown key number*/
        dbReference.child(player.getName()).setValue(player);
    }

    public Player retrieveDataFromDatabase(DatabaseReference dbReference, String dbMemberName, String dbChildName){

        player = null;
        dbReference = FirebaseDatabase.getInstance().getReference().child(dbMemberName).child(dbChildName);
        dbReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String name = dataSnapshot.child("name").getValue().toString();
                String userName = dataSnapshot.child("userName").getValue().toString();
                String password = dataSnapshot.child("password").getValue().toString();
                String age = dataSnapshot.child("age").getValue().toString();
                String phone = dataSnapshot.child("phone").getValue().toString();

                Log.d(">>>>>", "["+name+", "+userName+", "+password+", "+age+", "+phone+"]");
                player = new Player(name, userName, password, age, phone, null);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return player;
    }


    /*
     * Link: https://stackoverflow.com/questions/38652007/how-to-retrieve-specific-list-of-data-from-firebase
     */
    public List<Player> retrieveAllDataFromDatabase(DatabaseReference dbReference){
        dbReference = FirebaseDatabase.getInstance().getReference().child("Player");
        Log.d(">>>>>", "Starting method");

        dbReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.d(">>>>>", "On 1 st method");
                playerList.clear();
                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                    Log.d(">>>>>", "On 2 nd method");
                    Player player = postSnapshot.getValue(Player.class);
                    playerList.add(player);

                    // here you can access to name property like university.name
                    System.out.println(">>>>> Retrieving player -> "+ player);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getMessage());
            }
        });

        Log.d(">>>>>", "Ending method");
        return playerList;
    }
}
