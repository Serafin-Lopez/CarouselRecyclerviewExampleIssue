package mx.com.qualitycode.segundotest.ui.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_second.*
import mx.com.qualitycode.segundotest.R
import mx.com.qualitycode.segundotest.viewmodel.SampleViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [SecondFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SecondFragment : Fragment() {

    lateinit var sampleViewModel: SampleViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity?.let {
            sampleViewModel = ViewModelProvider(it)[SampleViewModel::class.java]
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sampleViewModel.message.observe(viewLifecycleOwner, Observer { name ->
            name?.let {
                textName.text = it
            }
        })

    }
}