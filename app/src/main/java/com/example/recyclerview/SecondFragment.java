package com.example.recyclerview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.recyclerview.data.CountryCovidData;
import com.example.recyclerview.databinding.FragmentSecondBinding;

public class SecondFragment extends Fragment {

    public static final String CHAVE_PAIS = "paisEscolhido";
    private FragmentSecondBinding binding;

    TextView recuperados_total, casos_total, casos_ativos, casos_novos, mortes_total, mortes_novas, ultima_atualizacao, recuperados, casos, mortes;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentSecondBinding.inflate(inflater, container, false);

        assert getArguments() != null;
        CountryCovidData dados = (CountryCovidData) getArguments().getSerializable(CHAVE_PAIS);
        declaraCampos(dados);
        testaCampos(dados);

        return binding.getRoot();

    }

    private void testaCampos(CountryCovidData dados) {
        testaRecuperados(dados);
        testaCasos(dados);
        testaMortes(dados);
        testaAtualizacao(dados);
    }

    private void testaAtualizacao(CountryCovidData dados) {
        if (dados.getLastUpdate() == null || dados.getLastUpdate().equals("")){
            ultima_atualizacao.setVisibility(View.GONE);
        }
    }

    private void testaMortes(CountryCovidData dados) {
        if (dados.getTotalDeathsText() == null || dados.getTotalDeathsText().equals("")){
            mortes_total.setVisibility(View.GONE);
        }

        if (dados.getNewDeathsText() == null || dados.getNewDeathsText().equals("")){
            mortes_novas.setVisibility(View.GONE);
        }

        testaTituloMortes(dados);
    }

    private void testaTituloMortes(CountryCovidData dados) {
        if ((dados.getNewDeathsText() == null || dados.getNewDeathsText().equals(""))
                && (dados.getTotalDeathsText() == null || dados.getTotalDeathsText().equals(""))){
            mortes.setVisibility(View.GONE);
        }
    }

    private void testaCasos(CountryCovidData dados) {
        if (dados.getTotalCasesText() == null || dados.getTotalCasesText().equals("")){
            casos_total.setVisibility(View.GONE);
        }

        if (dados.getActiveCasesText() == null || dados.getActiveCasesText().equals("")){
            casos_ativos.setVisibility(View.GONE);
        }

        if (dados.getNewCasesText() == null || dados.getNewCasesText().equals("")){
            casos_novos.setVisibility(View.GONE);
        }

        testaTituloCasos(dados);
    }

    private void testaTituloCasos(CountryCovidData dados) {
        if ( (dados.getTotalCasesText() == null || dados.getTotalCasesText().equals(""))
                && (dados.getActiveCasesText() == null || dados.getActiveCasesText().equals(""))
                && (dados.getNewCasesText() == null || dados.getNewCasesText().equals("")) ){
            casos.setVisibility(View.GONE);
        }
    }

    private void testaRecuperados(CountryCovidData dados) {
        if (dados.getTotalRecoveredText() == null || dados.getTotalRecoveredText().equals("")){
            recuperados_total.setVisibility(View.GONE);
            recuperados.setVisibility(View.GONE);
        }
    }

    private void declaraCampos(CountryCovidData dados) {
        mostraNomePais(dados);

        mostraRecuperadosTotal(dados);
        mostraRecuperadosTitulo();

        mostraCasosTotal(dados);
        mostraCasosAtivos(dados);
        MostraCasosNovos(dados);
        MostraCasosTitulo();

        MostraMortesTotal(dados);
        MostraMortesNovas(dados);
        MostraMortesTitulo();

        MostraDataAtualizacao(dados);
    }

    private void MostraDataAtualizacao(CountryCovidData dados) {
        binding.dataAtualizacao.setText(dados.getLastUpdate());
        ultima_atualizacao = binding.ultimaAtualizaAo;
    }

    private void MostraMortesTitulo() {
        mortes = binding.mortes;
    }

    private void MostraMortesNovas(CountryCovidData dados) {
        binding.qntMortesNovas.setText(dados.getNewDeathsText());
        mortes_novas = binding.mortesNovas;
    }

    private void MostraMortesTotal(CountryCovidData dados) {
        binding.qntMortesTotal.setText(dados.getTotalDeathsText());
        mortes_total = binding.mortesTotal;
    }

    private void MostraCasosTitulo() {
        casos = binding.casos;
    }

    private void MostraCasosNovos(CountryCovidData dados) {
        binding.qntCasosNovos.setText(dados.getNewCasesText());
        casos_novos = binding.casosNovos;
    }

    private void mostraCasosAtivos(CountryCovidData dados) {
        binding.qntCasosAtivos.setText(dados.getActiveCasesText());
        casos_ativos = binding.casosAtivos;
    }

    private void mostraCasosTotal(CountryCovidData dados) {
        binding.qntCasosTotal.setText(dados.getTotalCasesText());
        casos_total = binding.casosTotal;
    }

    private void mostraRecuperadosTitulo() {
        recuperados = binding.recuperados;
    }

    private void mostraRecuperadosTotal(CountryCovidData dados) {
        binding.qntRecuperadosTotal.setText(dados.getTotalRecoveredText());
        recuperados_total = binding.recuperadosTotal;
    }

    private void mostraNomePais(CountryCovidData dados) {
        binding.paisNome.setText(dados.getCountryText());
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