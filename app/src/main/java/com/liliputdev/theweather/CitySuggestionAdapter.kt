package com.liliputdev.theweather

import android.content.Context
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Filter
import android.widget.Filter.FilterResults
import android.widget.Filterable
import androidx.annotation.NonNull
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.liliputdev.theweather.repository.retrofit.apiModel.searchByCity.ApiModelSearchedCityItem


class CitySuggestionAdapter :
    BaseQuickAdapter<ApiModelSearchedCityItem, BaseViewHolder>(R.layout.item_city_suggestion) {
    override fun convert(holder: BaseViewHolder, item: ApiModelSearchedCityItem) {
        holder.setText(R.id.textViewItemCitySuggestionTitle,"${item.name} (${item.country})")
    }
}

/*
class CitySuggestionAdapter<T>(
    context: Context?, textViewResourceId: Int,
    objects: List<T>
) : ArrayAdapter<T>(context!!, textViewResourceId, objects) {
    private val filter: Filter = KNoFilter()
    var items: List<T>
    override fun getFilter(): Filter {
        return filter
    }

    private inner class KNoFilter : Filter() {
        override fun performFiltering(arg0: CharSequence): FilterResults {
            val result = FilterResults()
            result.values = items
            result.count = items.size
            return result
        }

        override fun publishResults(arg0: CharSequence, arg1: FilterResults) {
            notifyDataSetChanged()
        }
    }

    init {
        Log.v("masood", "Adapter created $filter")
        items = objects
    }
}*/
