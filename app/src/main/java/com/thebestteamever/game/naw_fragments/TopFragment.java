package com.thebestteamever.game.naw_fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.thebestteamever.game.R;
import com.thebestteamever.game.item.ListItem;
import com.thebestteamever.game.top_adapter.TopListAdapter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TopFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TopFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TopFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public TopFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TopFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TopFragment newInstance(String param1, String param2) {
        TopFragment fragment = new TopFragment();
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
        final View rootView = inflater.inflate(R.layout.fragment_top, container, false);
        ListView listView = (ListView) rootView.findViewById(R.id.listView);
        TopListAdapter adapter = new TopListAdapter(getActivity(), initData());
        listView.setAdapter(adapter);

        return rootView;

    }

    private LinkedList<ListItem> initData() {
        LinkedList<ListItem> linkedList = new LinkedList<>();

        List<String> units = new ArrayList<>();
        List<String> dozens = new ArrayList<>();
        List<String> hundreds = new ArrayList<>();

        units.add("один");
        units.add("два");
        units.add("три");
        units.add("четыре");
        units.add("пять");
        units.add("шесть");
        units.add("семь");
        units.add("восемь");
        units.add("девять");
        units.add("десять");
        units.add("одинадцать");
        units.add("двенадцать");
        units.add("тринадцать");
        units.add("четырнадцать");
        units.add("пятнадцать");
        units.add("шестнадцать");
        units.add("семнадцать");
        units.add("восемнадцать");
        units.add("девятнадцать");

        dozens.add("двадцать");
        dozens.add("тридцать");
        dozens.add("сорок");
        dozens.add("пятьдесят");
        dozens.add("шестьдесят");
        dozens.add("семьдесят");
        dozens.add("восемьдесят");
        dozens.add("девяносто");

        hundreds.add("сто");
        hundreds.add("двести");
        hundreds.add("триста");
        hundreds.add("четыреста");
        hundreds.add("пятьсот");
        hundreds.add("шестьсот");
        hundreds.add("семьсот");
        hundreds.add("восемьсот");
        hundreds.add("девятьсот");
        hundreds.add("тысяча");

        for (int i = -1; i < 9; i++) {
            String hundred = (i < 0) ? "" : hundreds.get(i) + " ";
            for (int j = -1; j < 8; j++) {
                String dozen = (j < 0) ? "" : dozens.get(j) + " ";
                int max = (j < 0) ? 19 : 9;
                for (int k = 0; k < max; k++) {
                    String unit = units.get(k);
                    linkedList.add(new ListItem(hundred + dozen + unit));
                }
                if (j < 7) linkedList.add(new ListItem(hundred + dozens.get(j + 1)));
            }
            linkedList.add(new ListItem(hundreds.get(i + 1)));
        }

        return linkedList;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }

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
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

}
