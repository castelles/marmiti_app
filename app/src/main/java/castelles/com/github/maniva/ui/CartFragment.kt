package castelles.com.github.maniva.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import castelles.com.github.maniva.databinding.FragmentCartBinding

class CartFragment: Fragment() {

    private lateinit var binding: FragmentCartBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = with(FragmentCartBinding.inflate(inflater)) {
        lifecycleOwner = viewLifecycleOwner
        binding = this
        root
    }
}