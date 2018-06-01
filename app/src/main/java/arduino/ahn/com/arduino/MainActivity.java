package arduino.ahn.com.arduino;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseHumidity = firebaseDatabase.getReference().child("Humidity");
    private DatabaseReference databaseTemperature = firebaseDatabase.getReference().child("Temperature");

    TextView textHumidity;
    TextView textTemperature;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textHumidity = (TextView)findViewById(R.id.text1);
        textTemperature = (TextView)findViewById(R.id.text2);
    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseHumidity.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                float hum = dataSnapshot.getValue(float.class);
                textHumidity.setText(""+hum);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        databaseTemperature.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                float temp = dataSnapshot.getValue(float.class);
                textTemperature.setText(""+temp);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
