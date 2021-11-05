package castelles.com.github.maniva.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import castelles.com.github.maniva.databinding.FragmentProductDetailsBinding
import castelles.com.github.api.model.MenuItem

class ProductDetailsFragment: Fragment() {

    private lateinit var binding: FragmentProductDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = with(FragmentProductDetailsBinding.inflate(inflater)) {
        lifecycleOwner = viewLifecycleOwner
        binding = this
        root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let { args ->
            val product = args.getSerializable("product") as MenuItem
            binding.item = product
//            binding
//                .imvProduct
//                .setImageDrawable(requireContext().getDrawable(product.image))
        }
    }
}