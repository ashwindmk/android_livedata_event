package com.ashwin.eventlivedata.unicast;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ashwin.eventlivedata.Constant;
import com.ashwin.eventlivedata.NextActivity;
import com.ashwin.eventlivedata.R;

public class EventFragment extends Fragment {
    private EventViewModel eventViewModel;

    public static EventFragment newInstance() {
        EventFragment fragment = new EventFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_producer, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        eventViewModel = new ViewModelProvider(requireActivity()).get(EventViewModel.class);

        eventViewModel.getCountLiveData().observe(getViewLifecycleOwner(), new UnicastObserver<Integer>() {
            @Override
            void onUpdated(Integer data) {
                Log.d(Constant.TAG, EventFragment.this.getClass().getSimpleName() + ": count: " + data);
            }
        });

        Button testButton = view.findViewById(R.id.test_button);
        testButton.setOnClickListener(v -> {
            eventViewModel.updateCount();
        });

        Button nextButton = view.findViewById(R.id.next_button);
        nextButton.setOnClickListener(v -> {
            startActivity(new Intent(requireActivity(), NextActivity.class));
        });
    }
}