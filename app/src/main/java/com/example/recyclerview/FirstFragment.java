package com.example.recyclerview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerview.adapter.ListaPaisesAdapter;
import com.example.recyclerview.data.CountryCovidData;
import com.example.recyclerview.data.GetCovidDataListFromJson;
import com.example.recyclerview.databinding.FragmentFirstBinding;

import java.util.List;

public class FirstFragment extends Fragment {

    public static final String CHAVE_PAIS = "paisEscolhido";
    private FragmentFirstBinding binding;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);

        configuraLista();

        return binding.getRoot();
    }

    private void configuraLista() {
        RecyclerView listaPaisesRecycler = binding.recyclerview;
        List<CountryCovidData> dados = new GetCovidDataListFromJson().execute(requireContext().getApplicationContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        listaPaisesRecycler.setLayoutManager(layoutManager);
        ListaPaisesAdapter adapter = new ListaPaisesAdapter(getActivity(), dados);
        listaPaisesRecycler.setAdapter(adapter);
        adapter.setOnItemClickListener(this::vaiSecondFragment);
    }

    private void vaiSecondFragment(CountryCovidData pais) {
        Fragment fragment = new SecondFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        bundle.putSerializable(CHAVE_PAIS, pais);
        NavHostFragment.findNavController(FirstFragment.this).navigate(R.id.action_FirstFragment_to_SecondFragment, bundle);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}