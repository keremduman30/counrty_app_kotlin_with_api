package com.example.countrykotlin.view

import android.opengl.Visibility
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.countrykotlin.R
import com.example.countrykotlin.adapter.CountryAdapter
import com.example.countrykotlin.databinding.FragmentDetailsBinding
import com.example.countrykotlin.view_model.FeedViewModel
import kotlinx.android.synthetic.main.fragment_feed.*


class FeedFragment : Fragment() {
    private lateinit var viewModel: FeedViewModel
    private var countryAdapter = CountryAdapter(arrayListOf());


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_feed, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(FeedViewModel::class.java)
        viewModel.refreshData();

        countryList.layoutManager = LinearLayoutManager(context)
        countryList.adapter = countryAdapter;

        refresh.setOnRefreshListener {
            countryList.visibility=View.GONE
            errorText.visibility=View.GONE
            viewModel.refreshFromApi();
            refresh.isRefreshing=false
            countryLoading.visibility=View.GONE


        }

        obserLiveData();


    }

    fun obserLiveData() {
        //2019 itibariyle andorid artk owner a this degil kendi owner ı kullanmamızı tavsiye ediyor
        viewModel.countries.observe(viewLifecycleOwner, Observer { countries ->
            countries?.let {
                countryList.visibility = View.VISIBLE;
                countryAdapter.updateCountryList(countries)
            }
        });
        viewModel.countryError.observe(viewLifecycleOwner, Observer { error ->
            error?.let {
                if (error) {
                    errorText.visibility = View.VISIBLE
                } else errorText.visibility = View.GONE
            }
        });

        viewModel.countryLoading.observe(viewLifecycleOwner, Observer { loading ->
            loading?.let {
                if (loading) {
                    countryLoading.visibility = View.VISIBLE
                    countryList.visibility = View.GONE
                    errorText.visibility = View.GONE
                } else countryLoading.visibility = View.GONE
            }


        })


    }


}