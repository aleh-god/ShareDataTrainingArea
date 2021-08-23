package by.godevelopment.livedatalearn

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import by.godevelopment.livedatalearn.databinding.FragmentFirstBinding
import com.google.android.material.snackbar.Snackbar

class FirstFragment() : Fragment() {

    private lateinit var liveData: MutableLiveData<String>
    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.button1.setOnClickListener {
            Log.d(LOG_TAG, "Button click in Fragment 1")
            val text = binding.textInput1.text.toString()
            liveData.value = text
        }

        liveData.observe(viewLifecycleOwner, Observer {
            binding.textView1.text = it
        })

    }

    fun setLiveData(inputLiveData: MutableLiveData<String>) {
        liveData = inputLiveData
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        @JvmStatic
        fun newInstance(inputLiveData: MutableLiveData<String>) : FirstFragment {
            val frag = FirstFragment()
            frag.setLiveData(inputLiveData)
            return frag
        }

        private const val LOG_TAG = "myLogs"
    }
}