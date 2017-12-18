package ius.smartlab.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import ius.smartlab.R;

public class ManualSettingFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Button buttonManSetting;
    private EditText temperatureManSetting;
    private EditText lightManSetting;
    private EditText doorsManSetting;
    private Long t;
    private Long l;
    private Long d;
    private OnFragmentInteractionListener mListener;
    RadioButton light_on;
    RadioButton light_off;
    RadioButton doors_locked;
    RadioButton doors_unlocked;

    public ManualSettingFragment() {
        // Required empty public constructor
    }

    public static ManualSettingFragment newInstance(String param1, String param2) {
        ManualSettingFragment fragment = new ManualSettingFragment();
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
        // Inflate the layout for this fragment
        View rootView=inflater.inflate(R.layout.fragment_manual_setting, container, false);
        buttonManSetting=(Button)rootView.findViewById(R.id.man_setting_button);
        temperatureManSetting=(EditText) rootView.findViewById(R.id.man_temperature);
        lightManSetting=(EditText) rootView.findViewById(R.id.man_light);
        doorsManSetting=(EditText) rootView.findViewById(R.id.man_doors);
        buttonManSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t=Long.parseLong(temperatureManSetting.getText().toString());
                l=Long.parseLong(lightManSetting.getText().toString());
                d=Long.parseLong(doorsManSetting.getText().toString());
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference databaseReference = database.getReference();
                databaseReference.child("Current_status").child("temperature").setValue(t);
                databaseReference.child("Current_status").child("light").setValue(l);
                databaseReference.child("Current_status").child("doors").setValue(d);

                Toast.makeText(getActivity(),"You successfully updated lab manual setting",Toast.LENGTH_LONG).show();
                //clear edittexts
                temperatureManSetting.setText("");
                lightManSetting.setText("");
                doorsManSetting.setText("");
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
