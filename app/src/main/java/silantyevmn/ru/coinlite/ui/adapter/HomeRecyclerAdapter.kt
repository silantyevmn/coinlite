package silantyevmn.ru.coinlite.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.recyclerview_home.view.*
import silantyevmn.ru.coinlite.R
import silantyevmn.ru.coinlite.mvp.model.entity.GeckoCoin

class HomeRecyclerAdapter(
    val listener: Listener
) : RecyclerView.Adapter<HomeRecyclerAdapter.ItemViewHolder>() {

    interface Listener {
        fun onItemClickById(coin: GeckoCoin)
    }

    var list: List<Any> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = list.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.recyclerview_home,
            parent,
            false
        )
        return ItemViewHolder(itemView, listener)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        if (list.get(position) is GeckoCoin) {
            val item = list.get(position) as GeckoCoin;
            holder.bind(item)
        }

    }

    class ItemViewHolder(itemView: View, val listener: HomeRecyclerAdapter.Listener) :
        RecyclerView.ViewHolder(itemView) {

        fun bind(item: Any) {
            with(itemView) {
                item as GeckoCoin
                Glide.with(itemView.context).load(item.image).into(itemView.ivCurrencyIcon)
                itemView.tvCurrencySym.text = item.symbol
                itemView.tvCurrencyName.text = item.name
                itemView.tvCurrencyMarketCap.text = item.marketCap
                itemView.tvCurrencyPrice.text = item.currentPrice.toString()
                itemView.tvCurrencySym.text = item.name

                setOnClickListener {
                    listener.onItemClickById(item)
                }
            }
        }
    }
}