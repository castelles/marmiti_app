package castelles.com.github.maniva.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import castelles.com.github.maniva.databinding.BottomSheetProductObservationBinding
import castelles.com.github.maniva.util.hideKeyboard
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class BottomSheetProductObservationLayout(
    private val onConfirm: (String) -> Unit
): BottomSheetDialogFragment() {

    private lateinit var binding: BottomSheetProductObservationBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = with(BottomSheetProductObservationBinding.inflate(inflater)) {
        isCancelable = false
        binding = this
        adaptClick()
        root
    }

    fun showField(fragmentManager: FragmentManager) {
        show(fragmentManager, TAG)
    }

    fun showSuccessLottie() {
        binding.lttSuccess.playAnimation()
    }

    private fun adaptClick() {
        binding.btnAdd.setOnClickListener {
            onConfirm(binding.tietObservation.text.toString())
        }

        binding.btnBack.setOnClickListener {
            MainScope().launch {
                requireContext().hideKeyboard()
                delay(1000)
                dismiss()
            }
        }
    }

    companion object {
        const val TAG = "ADD_OBSERVATION_LAYOUT"
    }

}