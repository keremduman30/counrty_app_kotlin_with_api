package com.example.countrykotlin.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.example.countrykotlin.R
import com.example.countrykotlin.databinding.FragmentDetailsBinding
import com.example.countrykotlin.util.downloadFromUrl
import com.example.countrykotlin.util.placeHolderProgressbar
import com.example.countrykotlin.view_model.DetailsViewModel
import kotlinx.android.synthetic.main.fragment_details.*
import kotlinx.android.synthetic.main.fragment_details.view.*
import kotlinx.android.synthetic.main.item_country_row.view.*

class DetailsFragment : Fragment() {
    private lateinit var viewModel: DetailsViewModel
    private var countryId=0;
    private lateinit var databindind: FragmentDetailsBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //data binding oncesi
       // return inflater.inflate(R.layout.fragment_details, container, false)
        //data bindindg sonrası
        databindind= DataBindingUtil.inflate(inflater,R.layout.fragment_details,container,false)
        return  databindind.root;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(DetailsViewModel::class.java)
        arguments?.let {
            countryId=DetailsFragmentArgs.fromBundle(it).uuid
        }
        viewModel.getDataFromRoom(countryId);
        obserLiveData();

    }

    fun obserLiveData() {
        viewModel.countryLiveData.observe(viewLifecycleOwner, Observer { country ->
            country?.let {
                /*
                data binding oncesi
                   countryName.text = country.countryName
                countryRegion.text = country.countryRegion
                countryLanguage.text = country.countryLanguage
                countryCurrency.text = country.countryCurrency
                  context?.let {
                    countryImage.downloadFromUrl(country.imageUrl, placeHolderProgressbar(it))
                }
                 */
                //data binding sonrası


                databindind.selectedCountry=it;

            }

        })
    }


}