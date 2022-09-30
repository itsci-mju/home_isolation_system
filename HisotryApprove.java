package com.example.miniproject;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.miniproject.Class.Medicine_Request;
import com.example.miniproject.Class.MyAdapter_ApproveMedicine;
import com.example.miniproject.Class.MyAdapter_HistoryApprove;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

import lombok.val;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HisotryApprove#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HisotryApprove extends Fragment {


    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    MyAdapter_HistoryApprove myAdapter_historyApprove;



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HisotryApprove() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HisotryApprove.
     */
    // TODO: Rename and change types and number of parameters
    public static HisotryApprove newInstance(String param1, String param2) {
        HisotryApprove fragment = new HisotryApprove();
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
        val v =  inflater.inflate(R.layout.fragment_hisotry_approve, container, false);
        databaseReference = FirebaseDatabase.getInstance("https://projecthomeisolation-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Login/Requst_medicine");
        databaseReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                HashMap<String, Medicine_Request> lstMedicine_Request = new HashMap<>();

                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    Medicine_Request medicine_request = snapshot1.getValue(Medicine_Request.class);
                    if (medicine_request.getStatus() == 2 || medicine_request.getStatus() == 3 ) {
                        lstMedicine_Request.put(snapshot1.getKey(), medicine_request);
                    }

                }
                myAdapter_historyApprove = new MyAdapter_HistoryApprove(getContext(), lstMedicine_Request);
                recyclerView = (RecyclerView) v.findViewById(R.id.RecyclerViewHistory);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                recyclerView.setAdapter(myAdapter_historyApprove);
                myAdapter_historyApprove.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }


        });



        return v;
    }
}