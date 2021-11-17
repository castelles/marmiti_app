package castelles.com.github.maniva.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import castelles.com.github.api.model.Order
import castelles.com.github.maniva.databinding.OrderItemBinding

class OrderAdapter(
    val items: MutableList<Order>,
    private val onClick: (Order) -> Unit
): RecyclerView.Adapter<OrderAdapter.OrderItemHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderItemHolder =
        with(LayoutInflater.from(parent.context)) {
            val binding = OrderItemBinding.inflate(this, parent, false)
            OrderItemHolder(binding)
        }

    override fun onBindViewHolder(holder: OrderItemHolder, position: Int) {
        val item = items[position]
        holder.bind(item, onClick)
    }

    override fun getItemId(position: Int): Long = items[position].id.toLong()

    override fun getItemCount(): Int = items.size

    fun updateAdapter() {
        notifyDataSetChanged()
    }

    inner class OrderItemHolder(
        private val binding: OrderItemBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun bind(_item: Order, onClick: (Order) -> Unit) {
            binding.apply {
                item = _item
                root.setOnClickListener { onClick(_item) }
            }
        }
    }
}