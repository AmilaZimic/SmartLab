package ius.smartlab.Fragments;

import android.app.DatePickerDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import ius.smartlab.Helpers.PastDataAdapter;
import ius.smartlab.POJO.GlobalVar;
import ius.smartlab.POJO.PastData;
import ius.smartlab.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PastDataFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PastDataFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PastDataFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Button temperature;
    private Button light;
    private Button doors;
    Button btnDatePicker, btnTimePicker;
    EditText txtDate, txtTime;
    private OnFragmentInteractionListener mListener;
    final ArrayList<PastData> ads = new ArrayList<PastData>();
    PastDataAdapter mAdAdapter;
    StaggeredGridLayoutManager mStaggeredLayoutManager;
    RecyclerView mRecyclerView;
    private int mYear, mMonth, mDay, mHour, mMinute;
    private static String datum;
    public PastDataFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PastDataFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PastDataFragment newInstance(String param1, String param2) {
        PastDataFragment fragment = new PastDataFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       final View rootView=inflater.inflate(R.layout.fragment_past_data, container, false);
        btnDatePicker=(Button)rootView.findViewById(R.id.btn_date);
        txtDate=(EditText)rootView.findViewById(R.id.future_date);
        btnDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                                //datum =txtDate.getText().toString();
                                ((GlobalVar) getActivity().getApplication()).setDatum(txtDate.getText().toString());
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();

            }

        });



        getDataFromDatabase(rootView);




        //SETTING THE DATA

        /*ArrayList<PastData> places = new ArrayList<>();
        // Creating ads in database should be considered
        places.add(new PastData(12.3,1,1));

        //get today's date
        Calendar c1 = Calendar.getInstance();
        SimpleDateFormat df1 = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate1 = df1.format(c1.getTime());

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference();
        for (int i = 0; i < places.size(); i++) {
            databaseReference.child("PastData/"+formattedDate1).push().setValue(places.get(i));
        }
    */

       /* FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference databaseReference = database.getReference();
        //get today's date
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate = df.format(c.getTime());

        databaseReference.child("PastData/"+formattedDate).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                for (DataSnapshot child : children) {
                    PastData r = child.getValue(PastData.class);
                    ads.add(r);
                    mAdAdapter.notifyDataSetChanged();


                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        mAdAdapter = new PastDataAdapter(ads);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        mStaggeredLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mStaggeredLayoutManager);

        mRecyclerView.setAdapter(mAdAdapter);

*/


        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private void getDataFromDatabase(View rootView){
        final GlobalVar xxx = (GlobalVar)getActivity().getApplication();
        String a= ((GlobalVar) getActivity().getApplication()).getDatum();


        if(TextUtils.isEmpty(txtDate.getText().toString())==false){
           return;
        }
        else{
            txtDate=(EditText)rootView.findViewById(R.id.future_date);
            String d=txtDate.getText().toString();


            FirebaseDatabase database = FirebaseDatabase.getInstance();
            final DatabaseReference databaseReference = database.getReference();
            //get today's date
            Calendar c = Calendar.getInstance();
            SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
            String formattedDate = df.format(c.getTime());

            databaseReference.child("PastData/"+"10-12-2017").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                    for (DataSnapshot child : children) {
                        PastData r = child.getValue(PastData.class);
                        ads.add(r);
                        mAdAdapter.notifyDataSetChanged();


                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            mAdAdapter = new PastDataAdapter(ads);

            mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
            mStaggeredLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
            mRecyclerView.setLayoutManager(mStaggeredLayoutManager);

            mRecyclerView.setAdapter(mAdAdapter);


        }



    }
}
