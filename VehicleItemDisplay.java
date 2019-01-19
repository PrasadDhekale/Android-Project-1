package com.example.prasad.project;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import javax.microedition.khronos.egl.EGLDisplay;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link VehicleItemDisplay.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link VehicleItemDisplay#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VehicleItemDisplay extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    VehicleItem item;
    private OnFragmentInteractionListener mListener;

    public VehicleItemDisplay() {

    }

    public void setArguments(VehicleItem item){
        this.item = item;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment VehicleItemDisplay.
     */
    // TODO: Rename and change types and number of parameters
    public static VehicleItemDisplay newInstance(String param1, String param2) {
        VehicleItemDisplay fragment = new VehicleItemDisplay();
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

        View v = inflater.inflate(R.layout.fragment_vehicle_item_display,container,false);

        TextView plate_number = (TextView) v.findViewById(R.id.plate_number_value);
        TextView drop_location = (TextView) v.findViewById(R.id.drop_location);
        TextView type = (TextView) v.findViewById(R.id.vehicle_type);
        TextView documents = (TextView) v.findViewById(R.id.documents);
        TextView fine = (TextView) v.findViewById(R.id.fine);


        plate_number.setText(item.getPlate_number().toUpperCase());
        drop_location.setText(item.getDrop_location().toUpperCase());
        type.setText(item.getVehicle_type().toUpperCase());
        documents.setText(item.getDocuments().toUpperCase());
        fine.setText("Rs. " + item.getFine());
        return v;

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction();
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
        void onFragmentInteraction();
    }
}
