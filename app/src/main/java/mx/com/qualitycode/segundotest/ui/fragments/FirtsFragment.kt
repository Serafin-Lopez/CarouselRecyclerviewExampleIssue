package mx.com.qualitycode.segundotest.ui.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_firts.*
import mx.com.qualitycode.segundotest.R
import mx.com.qualitycode.segundotest.models.ProfilesSuggestedModel
import mx.com.qualitycode.segundotest.ui.adapters.FollowAdapter
import mx.com.qualitycode.segundotest.ui.adapters.FollowingMainListCallback
import mx.com.qualitycode.segundotest.viewmodel.SampleViewModel


/**
 * A simple [Fragment] subclass.
 * Use the [FirtsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FirtsFragment : Fragment(), FollowingMainListCallback {


    lateinit var sampleViewModel: SampleViewModel

    lateinit var adapter : FollowAdapter


    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity?.let {
            adapter = FollowAdapter(it).apply { followingMainListCallback =  this@FirtsFragment}
            sampleViewModel = ViewModelProvider(it)[SampleViewModel::class.java]
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_firts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observerMessage()
    }

    private fun observerMessage() {

        sampleViewModel.message.value = "hi"

        sampleViewModel.message.observe(viewLifecycleOwner, Observer { messsage ->

            messsage?.let {

                recyclerViewFollowingMain.adapter = adapter
                recyclerViewFollowingMain.set3DItem(true)
                recyclerViewFollowingMain.setAlpha(true)

                val data = ArrayList<ProfilesSuggestedModel.Data>()
                data.add(ProfilesSuggestedModel.Data("1241",1,"Marco","https://images.unsplash.com/photo-1583002083815-8c6305bd56a4?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8Nnx8bW9kYXxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=500&q=60",""))
                data.add(ProfilesSuggestedModel.Data("1241",1,"Pedro","https://media.istockphoto.com/photos/attractive-young-couple-picture-id136943440?k=20&m=136943440&s=612x612&w=0&h=nDRD5wAPFAjVkgCtAQteL4-00GPucZKeIEDbZ05vRhA=",""))
                data.add(ProfilesSuggestedModel.Data("1241",1,"Maria","https://media.istockphoto.com/photos/skaters-portraits-picture-id503556270?k=20&m=503556270&s=612x612&w=0&h=wm7NORWwPSU_IukDACJn32vEQKhkcQlg8xtRztE1H3k=",""))
                data.add(ProfilesSuggestedModel.Data("1241",1,"Juan","https://media.istockphoto.com/photos/full-length-shot-of-a-diverse-group-of-businesspeople-sitting-outside-picture-id1313442839?k=20&m=1313442839&s=612x612&w=0&h=jcladRoRJ-WUjQ0Q_JqajboenZeI1ErQ0Qjpe7nitck=",""))
                data.add(ProfilesSuggestedModel.Data("1241",1,"Perez","https://media.istockphoto.com/photos/portrait-of-four-runners-resting-in-a-public-park-staircase-picture-id841585176?k=20&m=841585176&s=612x612&w=0&h=4fVZIwcBv-fiSgn-gvc69DgkqGrlMrSMbQrL6i-3Luo=",""))

                adapter.setData(data)

                Toast.makeText(context,it,Toast.LENGTH_SHORT).show()
            }

        })

    }

    override fun onSelectedFollowerProfile(following: ProfilesSuggestedModel.Data) {
        sampleViewModel.message.value = following.nickname
        replaceFragmentWith(SecondFragment())
    }

    override fun onFollowSelected(following: ProfilesSuggestedModel.Data) {

    }

    private fun replaceFragmentWith(fragment: Fragment) {
        fragmentManager?.beginTransaction()
            ?.replace(my_nav_host_fragment.id, fragment)
            ?.addToBackStack(null)
            ?.commit()
    }
}