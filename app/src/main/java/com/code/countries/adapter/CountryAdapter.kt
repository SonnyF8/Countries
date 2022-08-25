package com.code.countries.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.code.countries.R
import com.code.countries.databinding.CountryRowBinding
import com.code.countries.repository.model.Country

class CountryAdapter(val countryData: List<Country>?):
    RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {

    var context: Context? = null

    class CountryViewHolder(val binding: CountryRowBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        context = parent.context

        return CountryViewHolder(CountryRowBinding.inflate(
            LayoutInflater.from(context), parent, false
        ))
    }

    override fun getItemCount(): Int {
        return countryData!!.size
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.binding.name.text = countryData?.get(position)?.name
        holder.binding.region.text = countryData?.get(position)?.region
        holder.binding.code.text = countryData?.get(position)?.code
        holder.binding.capital.text = countryData?.get(position)?.capital

        if (position % 2 == 0) {
            holder.itemView.setBackgroundColor(
                context!!.resources.getColor(R.color.cardview_shadow_end_color)
            )
        } else {
            holder.itemView.setBackgroundColor(
                context!!.resources.getColor(R.color.cardview_shadow_start_color)
            )
        }
    }
}
