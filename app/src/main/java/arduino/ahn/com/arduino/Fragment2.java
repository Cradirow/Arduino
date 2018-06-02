package arduino.ahn.com.arduino;

import android.graphics.Color;
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

public class Fragment2 extends Fragment {

    public Fragment2() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    View view;

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseDust = firebaseDatabase.getReference().child("Dustdensity");

    TextView textDust;
    TextView textDesc;

    float dust;

    ImageView imageView_Good;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_fragment2, container, false);

        imageView_Good = (ImageView)view.findViewById(R.id.imageDust);

        textDust = (TextView)view.findViewById(R.id.textDust);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        databaseDust.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                dust = dataSnapshot.getValue(float.class);
                String dust_String = String.format("%.2f", dust);
                float dust_float = Float.parseFloat(dust_String);

                textDust = (TextView) view.findViewById(R.id.textDust);
                textDust.setText(dust_float + "");

                textDesc = (TextView) view.findViewById(R.id.textDustDesc);
                if(dust <= 30){
                    textDesc.setText("아주 좋은 날씨입니다");
                    textDust.setBackgroundColor(Color.rgb(0,191,255));
                    imageView_Good.setImageResource(R.drawable.good2);
                }else if(dust <= 80){
                    textDesc.setText("실외활동 시 특별히 행동에 제약을 받을 필요는 없지만 몸상태에 따라 유의하여 활동하세요");
                    textDust.setBackgroundColor(Color.rgb(154,254,46));
                    imageView_Good.setImageResource(R.drawable.good2);
                }else if(dust <= 150){
                    textDesc.setText("장시간 또는 무리한 실외활동 자제, 특히 눈이 아픈 증상이 있거나, 기침이나 목의 통증으로 불편한 사람은 실외활동을 피해야 합니다");
                    textDust.setBackgroundColor(Color.rgb(255,191,0));
                    imageView_Good.setImageResource(R.drawable.bad2);
                }else{
                    textDesc.setText("장시간 또는 무리한 실외활동 제한, 목의 통증과 기침 등의 증상이 있는 사람은 실외활동을 피해야 합니다");
                    textDust.setBackgroundColor(Color.rgb(223,1,1));
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
