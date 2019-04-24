package android.g6.cricspot;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreateAccountActivity extends AppCompatActivity {

    TextView nameTxt, userNameTxt, passwordTxt, ageTxt, phoneTxt, errTxt;
    EditText nameE, userNameE, passwordE, ageE, phoneE;
    String name, userName, password, age, phone;
    Button createAccountBtn;
    Player player;
    DatabaseReference dbReference;
    List<Player> playerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        /* Initialize variables */
        nameTxt = findViewById(R.id.nameTxtInCreateAccountPage);
        userNameTxt = findViewById(R.id.userNameTxtInCreateAccountPage);
        passwordTxt = findViewById(R.id.passwordTxtInCreateAccountPage);
        ageTxt = findViewById(R.id.ageTxtInCreateAccountPage);
        phoneTxt = findViewById(R.id.phoneTxtInCreateAccountPage);
        errTxt = findViewById(R.id.errorTxtInCreateAccountPage);

        nameE = findViewById(R.id.nameInCreateAccountPage);
        userNameE = findViewById(R.id.userNameInCreateAccountPage);
        passwordE = findViewById(R.id.passwordInCreateAccountPage);
        ageE = findViewById(R.id.ageInCreateAccountPage);
        phoneE = findViewById(R.id.phoneInCreateAccountPage);

        createAccountBtn = findViewById(R.id.createAccountBtnInCreateAccountPage);

        playerList = new ArrayList<>();

        /*
        1) Firebase database reference
        2) Link: https://firebase.google.com/docs/android/setup?authuser=0 */
        dbReference = FirebaseDatabase.getInstance().getReference().child("Player");
    }

    public void createAccountClickedInCreateAccountPage(View view) {

//        /* Get values from user */
//        name = nameE.getText().toString();
//        userName = userNameE.getText().toString();
//        password = passwordE.getText().toString();
//        age = ageE.getText().toString();
//        phone = phoneE.getText().toString();
//        /*TODO: Validations must take place*/
//
//        player = new Player(name, userName, password, age, phone, null);
//        /*TODO: Add this object to fire base*/
//
//        //dbReference.push().setValue(player);
//        dbReference.child(player.getName()).setValue(player);
//
//        Toast.makeText(CreateAccountActivity.this, "Data inserted", Toast.LENGTH_SHORT).show();
//
//        retrieveDataFromDatabase("Player", player.getName());

        retrieveAllDataFromDatabase();
    }

    public void retrieveDataFromDatabase(String dbMemberName, String dbChildName){
        dbReference = FirebaseDatabase.getInstance().getReference().child(dbMemberName).child(dbChildName);
        dbReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                name = dataSnapshot.child("name").getValue().toString();
                userName = dataSnapshot.child("userName").getValue().toString();
                password = dataSnapshot.child("password").getValue().toString();
                age = dataSnapshot.child("age").getValue().toString();
                phone = dataSnapshot.child("phone").getValue().toString();

                Log.d(">>>>>", "["+name+", "+userName+", "+password+", "+age+", "+phone+"]");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void retrieveAllDataFromDatabase(){
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
                    Log.d(">>>>>", player+"");
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getMessage());
            }


        });

        Log.d(">>>>>", "Ending method");
    }
}
