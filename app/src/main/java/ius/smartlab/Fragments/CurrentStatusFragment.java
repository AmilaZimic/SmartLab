package ius.smartlab.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

import ius.smartlab.R;

public class CurrentStatusFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    TextView curr_num_of_people;
    TextView current_temperature;
    TextView current_light_status;
    TextView door_status;
    Button mancontroller;

    private OnFragmentInteractionListener mListener;

    public CurrentStatusFragment() {
        // Required empty public constructor
    }

    public static CurrentStatusFragment newInstance(String param1, String param2) {
        CurrentStatusFragment fragment = new CurrentStatusFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.fragment_current_setting, container, false);
        curr_num_of_people=(TextView) rootView.findViewById(R.id.curr_num_of_people);
        current_temperature=(TextView) rootView.findViewById(R.id.current_temperature);
        current_light_status=(TextView)rootView.findViewById(R.id.current_light_status);
        door_status=(TextView)rootView.findViewById(R.id.doors_status);
        mancontroller=(Button)rootView.findViewById(R.id.mancontroller);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference databaseReference = database.getReference();

        databaseReference.child("Current_status").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Map<Long, Long> val01 = (Map<Long, Long>) dataSnapshot.getValue();
                final  Long curr_time=val01.get("curr_time");

               /*final String a=datee.toString();
                 final String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
                 FirebaseDatabase.getInstance().getReference().child("Future_setting")
                        .addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                    databaseReference.child("Current_status").child("temperature")
                                            .setValue(dataSnapshot.child(curr_time).child("light").getValue());

                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {
                                }
                            });
                */

                Map<Long, Long> val1 = (Map<Long, Long>) dataSnapshot.getValue();
                Long light=val1.get("light");
                if(light==1)
                    current_light_status.setText("ON"); //set light status
                else
                    current_light_status.setText("OFF"); //set light status

                Map<Long, Long> val2 = (Map<Long, Long>) dataSnapshot.getValue();
                Long temperature=(long)val2.get("temperature");

                current_temperature.setText(temperature.toString()); //set temperature
               // current_temperature.setText("33");

                Map<Long, Long> val3 = (Map<Long, Long>) dataSnapshot.getValue();
                Long num_of_people=val3.get("num_of_people");

                curr_num_of_people.setText(num_of_people.toString()); //set current number of peopel

                Map<Long, Long> val4 = (Map<Long, Long>) dataSnapshot.getValue();
                Long doors=val4.get("doors");

                if(doors==1)
                    door_status.setText("UNLOCKED"); //set light status
                else
                    door_status.setText("LOCKED"); //set light status
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mancontroller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fragmentManager2 = getFragmentManager();
                FragmentTransaction fragmentTransaction2 = fragmentManager2.beginTransaction();
                ManualSettingFragment manualSettingFragment = new ManualSettingFragment();
                fragmentTransaction2.replace(R.id.fragment_container, manualSettingFragment);
                fragmentTransaction2.commit();
            }
        });

        return rootView;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

}
