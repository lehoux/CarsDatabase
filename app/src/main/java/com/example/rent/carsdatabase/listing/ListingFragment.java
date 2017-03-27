package com.example.rent.carsdatabase.listing;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rent.carsdatabase.MotoDbOpenHelper;
import com.example.rent.carsdatabase.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by RENT on 2017-03-27.
 */

public class ListingFragment extends Fragment {

    private static final String QUERY_KEY = "query_key";

        @BindView(R.id.recycler_view)
        RecyclerView recyclerView;

        private Unbinder unbinder;

        private MotoDbOpenHelper openHelper;

    public static Fragment getInstance(String query) {
        ListingFragment fragment = new ListingFragment();
        Bundle arguments = new Bundle();
        arguments.putString(QUERY_KEY, query);
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        openHelper = new MotoDbOpenHelper(getActivity());

    }

    @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_listing, container, false);


        }

        @Override
        public void onViewCreated(View view, Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            unbinder = ButterKnife.bind(this, view);
            String query = getArguments().getString(QUERY_KEY);
            RecyclerViewCursorAdapter recyclerViewCursorAdapter = new RecyclerViewCursorAdapter();
            recyclerView.setAdapter(recyclerViewCursorAdapter);
            recyclerViewCursorAdapter.setCursor(openHelper.searchQuery(query));
        }

        @Override
        public void onDestroy() {
            super.onDestroy();
            unbinder.unbind();
        }
    }

