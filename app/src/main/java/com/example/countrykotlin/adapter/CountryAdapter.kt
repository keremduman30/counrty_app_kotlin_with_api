package com.example.countrykotlin.adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.countrykotlin.R
import com.example.countrykotlin.databinding.ItemCountryRowBinding
import com.example.countrykotlin.model.Country
import com.example.countrykotlin.util.downloadFromUrl
import com.example.countrykotlin.util.placeHolderProgressbar
import com.example.countrykotlin.view.FeedFragmentDirections
import kotlinx.android.synthetic.main.fragment_details.view.*
import kotlinx.android.synthetic.main.item_country_row.view.*


class CountryAdapter(val countryList:ArrayList<Country>):RecyclerView.Adapter<CountryAdapter.CountryViewHolder>(),CountryClickListener{
    class  CountryViewHolder(var view :ItemCountryRowBinding):RecyclerView.ViewHolder(view.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
            val inflater=LayoutInflater.from(parent.context)
        //data binfind oncesi
            //val view=inflater.inflate(R.layout.item_country_row,parent,false)
            val view=DataBindingUtil.inflate<ItemCountryRowBinding>(inflater,R.layout.item_country_row,parent,false)
            return  CountryViewHolder(view);
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        /*
        Databinding oncesi
           holder.view.name.text=countryList.get(position).countryName
        holder.view.region.text=countryList.get(position).countryRegion
        holder.view.imageView.downloadFromUrl(countryList.get(position).imageUrl,placeHolderProgressbar(holder.view.context));

        holder.view.setOnClickListener {
            val action=FeedFragmentDirections.actionFeedFragmentToDetailsFragment(countryList[position].uuid)
            Navigation.findNavController(it).navigate(action)
        }
         */
//        data binding sonrası ve cok prtaik
        holder.view.countrt=countryList[position];//bukadar
        holder.view.listener=this




    }

    override fun getItemCount(): Int {
       return countryList.size;
    }

    //burda eger liste guncelemirse refresi tektiklemek lazım bunun için
    fun updateCountryList(newCountryList:List<Country>){
        countryList.clear();
        countryList.addAll(newCountryList)
        notifyDataSetChanged();//adaptoru yenilemek için kullanılr
    }

    override fun onCountryClik(v: View) {
        val action=FeedFragmentDirections.actionFeedFragmentToDetailsFragment( v.countryUuid.text.toString().toInt())
        Navigation.findNavController(v).navigate(action)
    }
}