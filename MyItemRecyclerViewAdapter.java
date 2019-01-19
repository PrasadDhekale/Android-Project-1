package com.example.prasad.project;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prasad.project.VehicleList.OnListFragmentInteractionListener;
import com.example.prasad.project.VehicleItem;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link VehicleItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */

public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> {

    private final List<VehicleItem> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MyItemRecyclerViewAdapter(List<VehicleItem> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.plate_number.setText(mValues.get(position).getPlate_number().toUpperCase());
        holder.state.setText(mValues.get(position).getState().toUpperCase());
        holder.city.setText(mValues.get(position).getCity().toUpperCase());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener  ) {
                    if(holder.mItem != null)
                        mListener.onListFragmentInteraction(holder.mItem);
                    else
                        System.out.println();
                        System.out.println("mitem is null");
                        System.out.println();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView plate_number;
        public final TextView state;
        public final TextView city;
        public VehicleItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            plate_number = (TextView) view.findViewById(R.id.plate_number);
            state = (TextView) view.findViewById(R.id.state);
            city = (TextView) view.findViewById(R.id.city);
        }

    }
}
