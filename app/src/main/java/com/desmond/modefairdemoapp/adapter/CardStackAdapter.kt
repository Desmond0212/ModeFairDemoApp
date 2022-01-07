package com.desmond.modefairdemoapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.viewpager.widget.PagerAdapter
import com.desmond.modefairdemoapp.R

class CardStackAdapter(private var mContext: Context?, private var items: List<String>): PagerAdapter()
{
    override fun getCount(): Int
    {
        return items.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean
    {
        return view === `object`
    }

    override fun getItemPosition(`object`: Any): Int
    {
        return super.getItemPosition(`object`)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any
    {
        val layoutInflater = mContext?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val convertView: View = layoutInflater.inflate(R.layout.item_list_stack_view, null)

        container.addView(convertView)
        return convertView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any)
    {
        container.removeView(`object` as RelativeLayout?)
    }
}