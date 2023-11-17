package com.example.advancedmvvmexercise.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.advancedmvvmexercise.databinding.OrderItemBinding
import com.example.advancedmvvmexercise.model.Order

class OrderAdapter(private val orders:ArrayList<Order>): RecyclerView.Adapter<OrderAdapter.OrderViewHolder>() {

    fun updateOrders(newOrders:List<Order>){
        orders.clear()
        orders.addAll(newOrders)
        notifyDataSetChanged()
    }


    inner class OrderViewHolder(val binding: OrderItemBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(order: Order){
            binding.orderId.text = order.id.toString()
            binding.product.text = order.product.toString()
            binding.user.text = order.user.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val binding = OrderItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return OrderViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return orders.size
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        holder.bind(orders[position])
    }


}