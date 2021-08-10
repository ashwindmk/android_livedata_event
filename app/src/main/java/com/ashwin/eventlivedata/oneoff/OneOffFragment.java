package com.ashwin.eventlivedata.oneoff;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ashwin.eventlivedata.Constant;
import com.ashwin.eventlivedata.NextActivity;
import com.ashwin.eventlivedata.R;

public class OneOffFragment extends Fragment {
    private OneOffViewModel oneOffViewModel;

    public static OneOffFragment newInstance() {
        return new OneOffFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_oneoff, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        oneOffViewModel = new ViewModelProvider(requireActivity()).get(OneOffViewModel.class);

        Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onChanged(Integer n) {
                Log.d(Constant.TAG, OneOffFragment.this.getClass().getSimpleName() + ": event: " + n);
            }
        };

        Button updateButton = view.findViewById(R.id.update_button);
        updateButton.setOnClickListener(v -> {
            oneOffViewModel.updateCount();
        });

        Button addObsButton = view.findViewById(R.id.add_obs_button);
        addObsButton.setOnClickListener(v -> {
            oneOffViewModel.getLiveEvent().observe(getViewLifecycleOwner(), observer);
        });

        Button removeObsButton = view.findViewById(R.id.remove_obs_button);
        removeObsButton.setOnClickListener(v -> {
            oneOffViewModel.getLiveEvent().removeObserver(observer);
        });

        Button nextButton = view.findViewById(R.id.next_button);
        nextButton.setOnClickListener(v -> {
            startActivity(new Intent(requireActivity(), NextActivity.class));
        });
    }
}
