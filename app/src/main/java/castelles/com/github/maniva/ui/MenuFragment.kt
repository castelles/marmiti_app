package castelles.com.github.maniva.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import castelles.com.github.api.model.MenuItem
import castelles.com.github.maniva.databinding.FragmentMenuBinding
import castelles.com.github.maniva.ui.adapter.MenuAdapter
import castelles.com.github.maniva.util.generateList

class MenuFragment: Fragment() {

    private lateinit var binding: FragmentMenuBinding
    private lateinit var adapter: MenuAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = with(FragmentMenuBinding.inflate(inflater)) {
        lifecycleOwner = viewLifecycleOwner
        binding = this
        root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
    }

    private fun setAdapter() {
        val items = requireContext().generateList()

        adapter = MenuAdapter(items) {

        }
        adapter.setHasStableIds(true)

        binding.rcvMenu.apply {
            layoutManager = LinearLayoutManager(requireContext(), VERTICAL, false)
            adapter = this@MenuFragment.adapter
        }
    }
}