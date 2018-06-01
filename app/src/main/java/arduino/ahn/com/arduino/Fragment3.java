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

public class Fragment3 extends Fragment{

    public Fragment3() {
        // Required empty public constructor
    }

    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_fragment3, container, false);

        textCO = (TextView)view.findViewById(R.id.textCO);
        textNO2 = (TextView)view.findViewById(R.id.textNO2);
        textNH3 = (TextView)view.findViewById(R.id.textNH3);

        imageView_Good = (ImageView)view.findViewById(R.id.imageViewGas);

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseCO = firebaseDatabase.getReference().child("CO");
    private DatabaseReference databaseNO2 = firebaseDatabase.getReference().child("NO2");
    private DatabaseReference databaseNH3 = firebaseDatabase.getReference().child("NH3");

    TextView textCO;
    TextView textNO2;
    TextView textNH3;

    ImageView imageView_Good;

    float CO;
    float NO2;
    float NH3;

    @Override
    public void onStart() {
        super.onStart();

        databaseCO.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                CO = dataSnapshot.getValue(float.class);
                String CO_String = String.format("%.2f", CO);
                float CO_float = Float.parseFloat(CO_String);

                textCO = (TextView)view.findViewById(R.id.textCO);
                textCO.setText(CO_float + " ppm");

                if(CO <= 9){
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

        databaseNO2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                NO2 = dataSnapshot.getValue(float.class);
                String NO2_String = String.format("%.2f", NO2);
                float NO2_float = Float.parseFloat(NO2_String);

                textNO2 = (TextView)view.findViewById(R.id.textNO2);
                textNO2.setText(NO2_float + " ppm");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("LogFragment", "loadLog:onCancelled", databaseError.toException());
            }
        });

        databaseNH3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                NH3 = dataSnapshot.getValue(float.class);
                String NH3_String = String.format("%.2f", NH3);
                float NH3_float = Float.parseFloat(NH3_String);

                textNH3 = (TextView)view.findViewById(R.id.textNH3);
                textNH3.setText(NH3_float + " ppm");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("LogFragment", "loadLog:onCancelled", databaseError.toException());
            }
        });
    }

}
