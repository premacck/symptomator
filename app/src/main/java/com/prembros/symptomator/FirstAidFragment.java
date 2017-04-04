package com.prembros.symptomator;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Arrays;
import java.util.List;

import xyz.danoz.recyclerviewfastscroller.vertical.VerticalRecyclerViewFastScroller;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnFirstAidListFragmentInteractionListener}
 * interface.
 */
public class FirstAidFragment extends Fragment {

    private static final String ARG_COLUMN_COUNT = "columnCount";
    private int mColumnCount = 1;
    private OnFirstAidListFragmentInteractionListener mListener;

    public FirstAidFragment() {
    }

    //Customize parameter initialization
    @SuppressWarnings("unused")
    public static FirstAidFragment newInstance(int columnCount) {
        FirstAidFragment fragment = new FirstAidFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first_aid, container, false);
        RecyclerView list = (RecyclerView) view.findViewById(R.id.first_aid_list);
        VerticalRecyclerViewFastScroller scroller = (VerticalRecyclerViewFastScroller) view.findViewById(R.id.first_aid_scroller);
        scroller.setRecyclerView(list);
        list.addOnScrollListener(scroller.getOnScrollListener());

        // Set the list
        List<String> firstAidList = Arrays.asList(getResources().getStringArray(R.array.first_aid));
        // Set the adapter
        Context context = list.getContext();
        if (mColumnCount <= 1) {
            list.setLayoutManager(new LinearLayoutManager(context));
        } else {
            list.setLayoutManager(new GridLayoutManager(context, mColumnCount));
        }
        list.setAdapter(new MyFirstAidRecyclerViewAdapter(firstAidList, mListener));
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFirstAidListFragmentInteractionListener) {
            mListener = (OnFirstAidListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFirstAidListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    interface OnFirstAidListFragmentInteractionListener {
        void onFirstAidListFragmentInteraction(String item);
    }
}
