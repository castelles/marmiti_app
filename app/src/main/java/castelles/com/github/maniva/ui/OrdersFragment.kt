package castelles.com.github.maniva.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import castelles.com.github.maniva.databinding.FragmentOrdersBinding
import castelles.com.github.maniva.ui.adapter.OrderAdapter
import castelles.com.github.maniva.util.generateOrders

class OrdersFragment: Fragment() {

    private lateinit var binding: FragmentOrdersBinding
    private lateinit var adapter: OrderAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = with(FragmentOrdersBinding.inflate(inflater)) {
        lifecycleOwner = viewLifecycleOwner
        binding = this
        root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
    }

    private fun setAdapter() {
        val items = requireContext().generateOrders()
        adapter = OrderAdapter(items) {
            Log.e("Order", it.toString())
        }

        adapter.setHasStableIds(true)
        binding.rcvMenu.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = this@OrdersFragment.adapter
        }
    }
}