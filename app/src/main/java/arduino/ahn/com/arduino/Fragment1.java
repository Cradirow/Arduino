package arduino.ahn.com.arduino;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by user on 2018-06-01.
 */

public class Fragment1 extends Fragment {

    public Fragment1(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment1, container, false);
    }
}

//    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
//    private DatabaseReference databaseHumidity = firebaseDatabase.getReference().child("Humidity");
//    private DatabaseReference databaseTemperature = firebaseDatabase.getReference().child("Temperature");
//
//    TextView textHumidity;
//    TextView textTemperature;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        textHumidity = (TextView)findViewById(R.id.text1);
//        textTemperature = (TextView)findViewById(R.id.text2);
//    }
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//
//        databaseHumidity.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                float hum = dataSnapshot.getValue(float.class);
//                textHumidity.setText(""+hum);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//
//        databaseTemperature.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                float temp = dataSnapshot.getValue(float.class);
//                textTemperature.setText(""+temp);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//    }
