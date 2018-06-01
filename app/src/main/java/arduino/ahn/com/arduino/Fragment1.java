package arduino.ahn.com.arduino;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by user on 2018-06-01.
 */

public class Fragment1 extends Fragment {

    public Fragment1(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_fragment1, container, false);

        textHumidity = (TextView)view.findViewById(R.id.textA);
        textTemperature = (TextView)view.findViewById(R.id.textB);
        imageView_Good = (ImageView)view.findViewById(R.id.imageViewTemp);
        return view;
    }

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseHumidity = firebaseDatabase.getReference().child("Humidity");
    private DatabaseReference databaseTemperature = firebaseDatabase.getReference().child("Temperature");

    TextView textHumidity;
    TextView textTemperature;

    ImageView imageView_Good;

    float hum;
    float temp;

    @Override
    public void onStart() {
        super.onStart();

        databaseHumidity.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                hum = dataSnapshot.getValue(float.class);
                textHumidity = (TextView)view.findViewById(R.id.textA);
                textHumidity.setText(hum + " %");
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("LogFragment", "loadLog:onCancelled", databaseError.toException());
            }

        });

        databaseTemperature.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                temp = dataSnapshot.getValue(float.class);
                textTemperature = (TextView)view.findViewById(R.id.textB);
                textTemperature.setText(temp + " C");

                if(temp >= 25 && temp <= 28){
                    imageView_Good.setImageResource(R.drawable.good2);
                }else{
                    imageView_Good.setImageResource(R.drawable.bad2);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("LogFragment", "loadLog:onCancelled", databaseError.toException());
            }
        });
    }
}