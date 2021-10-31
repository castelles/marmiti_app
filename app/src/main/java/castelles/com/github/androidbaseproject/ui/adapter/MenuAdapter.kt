package castelles.com.github.androidbaseproject.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import castelles.com.github.androidbaseproject.databinding.NewMenuItemBinding
import castelles.com.github.api.model.MenuItem

class MenuAdapter(
    val items: MutableList<MenuItem>,
    private val onClick: (MenuItem) -> Unit
): RecyclerView.Adapter<MenuAdapter.MenuItemHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuItemHolder =
        with(LayoutInflater.from(parent.context)) {
            val binding = NewMenuItemBinding.inflate(this, parent, false)
            MenuItemHolder(binding)
        }

    override fun onBindViewHolder(holder: MenuItemHolder, position: Int) {
        val item = items[position]
        holder.bind(item, onClick)
    }

    override fun getItemId(position: Int): Long = items[position].id.toLong()

    override fun getItemCount(): Int = items.size

    fun updateAdapterView() {
        notifyDataSetChanged()
    }

    inner class MenuItemHolder(
        private val binding: NewMenuItemBinding
    ): RecyclerView.ViewHolder(binding.root) {


        fun bind(_item: MenuItem, onClick: (MenuItem) -> Unit) {
            binding.apply {
                item = _item
                root.setOnClickListener { onClick(_item) }
            }
        }

    }
}