package com.desmond.modefairdemoapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.desmond.modefairdemoapp.R
import com.desmond.modefairdemoapp.model.OrderItemModel

class OrderItemListAdapter(private var mContext: Context, private val orderList: MutableList<OrderItemModel>):
    RecyclerView.Adapter<OrderItemListAdapter.ViewHolder>()
{
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OrderItemListAdapter.ViewHolder {
        val view: View? = LayoutInflater.from(parent.context).inflate(
            R.layout.item_list_order_item,
            parent,
            false
        )

        return ViewHolder(view!!)
    }

    override fun onBindViewHolder(holder: OrderItemListAdapter.ViewHolder, position: Int) {
        val currentOrderItem = orderList[position]

        holder.lblOrderItemQuantity?.text = currentOrderItem.itemQuantity
        holder.lblOrderItemName?.text = currentOrderItem.itemName
        holder.lblOrderItemPrice?.text = currentOrderItem.itemPrice
    }

    override fun getItemCount(): Int {
        return orderList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal val lblOrderItemQuantity: TextView? = itemView.findViewById(R.id.lblOrderItemQuantity)
        internal val lblOrderItemName: TextView? = itemView.findViewById(R.id.lblOrderItemName)
        internal val lblOrderItemPrice: TextView? = itemView.findViewById(R.id.lblOrderItemPrice)
    }
}