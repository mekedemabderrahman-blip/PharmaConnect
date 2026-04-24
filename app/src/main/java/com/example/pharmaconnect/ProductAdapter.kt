package com.example.pharmaconnect

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.pharmaconnect.databinding.ItemProduitBinding

class ProductAdapter(private var list: List<Product>) :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    class ProductViewHolder(val binding: ItemProduitBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {

        val binding = ItemProduitBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {

        val item = list[position]
        val b = holder.binding

        b.tvProductName.text = item.name
        b.tvProductPrice.text = item.price
        b.tvPharmacyName.text = item.pharmacy
        b.tvLocation.text = item.location
        b.imgProduct.setImageResource(item.image)

        if (item.promotion > 0) {
            b.tvPromotion.visibility = android.view.View.VISIBLE
            b.tvPromotion.text = "-${item.promotion}%"
        } else {
            b.tvPromotion.visibility = android.view.View.GONE
        }

        b.root.setOnClickListener {

            val intent = Intent(b.root.context, koli::class.java)

            intent.putExtra("name", item.name)
            intent.putExtra("price", item.price)
            intent.putExtra("pharmacy", item.pharmacy)
            intent.putExtra("location", item.location)
            intent.putExtra("image", item.image)

            b.root.context.startActivity(intent)
        }
    }

    override fun getItemCount() = list.size

    fun updateList(newList: List<Product>) {

        val diffCallback = object : DiffUtil.Callback() {
            override fun getOldListSize() = list.size
            override fun getNewListSize() = newList.size

            override fun areItemsTheSame(oldPos: Int, newPos: Int): Boolean {
                return list[oldPos].name == newList[newPos].name
            }

            override fun areContentsTheSame(oldPos: Int, newPos: Int): Boolean {
                return list[oldPos] == newList[newPos]
            }
        }

        val diffResult = DiffUtil.calculateDiff(diffCallback)
        list = newList
        diffResult.dispatchUpdatesTo(this)
    }
}