package castelles.com.github.maniva.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import castelles.com.github.maniva.databinding.FragmentProductDetailsBinding
import castelles.com.github.api.model.MenuItem
import castelles.com.github.maniva.viewmodel.ProductDetailsViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel

class ProductDetailsFragment: Fragment() {

    private lateinit var binding: FragmentProductDetailsBinding
    private val viewModel: ProductDetailsViewModel by viewModel()

    private lateinit var observationBottomSheet: BottomSheetProductObservationLayout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = with(FragmentProductDetailsBinding.inflate(inflater)) {
        lifecycleOwner = viewLifecycleOwner
        binding = this
        viewModel = this@ProductDetailsFragment.viewModel
        root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let { args ->
            val product = args.getSerializable("product") as MenuItem
            bindViewModel()
            viewModel.setProduct(product)
        }
    }


    private fun bindViewModel() {
        viewModel.apply {

            removeItemClick.observe(viewLifecycleOwner) {

            }

            addItemClick.observe(viewLifecycleOwner) {

            }

            addToCartClick.observe(viewLifecycleOwner) {
                setObservationLayout()
            }

        }
    }

    private fun setObservationLayout() {
        observationBottomSheet = BottomSheetProductObservationLayout {
            MainScope().launch {
                observationBottomSheet.apply {
                    showSuccessLottie()
                    delay(2500)
                    dismiss()
                }
            }
        }

        observationBottomSheet.showField(childFragmentManager)
    }
}