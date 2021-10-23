package castelles.com.github.androidbaseproject.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import castelles.com.github.androidbaseproject.R
import castelles.com.github.androidbaseproject.databinding.FragmentHomeBinding
import castelles.com.github.androidbaseproject.ui.adapter.MenuAdapter
import castelles.com.github.androidbaseproject.viewmodel.UserViewModel
import castelles.com.github.api.model.MenuItem
import castelles.com.github.api.model.UserResponse
import castelles.com.github.api.utils.Error
import castelles.com.github.api.utils.ErrorHandler
import castelles.com.github.api.utils.Loading
import castelles.com.github.api.utils.Success
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment: Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: UserViewModel by viewModel()

    private lateinit var adapter: MenuAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = with(FragmentHomeBinding.inflate(inflater)) {
        lifecycleOwner = viewLifecycleOwner
        binding = this
        viewModel = this@HomeFragment.viewModel
        root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViewModel()
        setAdapter()
    }

    private fun bindViewModel() {
        viewModel.apply {
            userFetcher.onEach { state ->
                when ( val handler = state.handler) {
                    is Loading -> {}
                    is Success -> doSomethingWithData(handler.result)
                    is Error<*> -> doSomethingWithError(handler.error)
                }
            }.launchIn(MainScope())
        }
    }

    private fun doSomethingWithData(result: UserResponse?) {
        // TODO ()
    }

    private fun doSomethingWithError(error: ErrorHandler<out Any?>) {
        // TODO ()
    }

    private fun setAdapter() {
        val items = mutableListOf<MenuItem>()
        items.add(
            MenuItem(
            1,
            "Lasanha vegetariana clássica",
            "Massa comum, bolonhesa de soja, molho bechamel clássico e queijo.",
                22.00,
                true,
                5
            )
        )

        items.add(
            MenuItem(
                1,
                "Lasanha vegetariana clássica",
                "Massa comum, bolonhesa de soja, molho bechamel clássico e queijo.",
                22.00,
                true,
                5
            )
        )

        adapter = MenuAdapter(items) {

        }
        adapter.setHasStableIds(true)

        binding.rcvMenu.apply {
            layoutManager = LinearLayoutManager(context, VERTICAL, false)
            adapter = this@HomeFragment.adapter
            val itemDecorator = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
            itemDecorator.setDrawable(requireContext().getDrawable(R.drawable.divider_recycler)!!)
            addItemDecoration(itemDecorator)
        }
    }

}