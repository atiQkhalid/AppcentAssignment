package com.example.appcentassignment.views.fragment.spirit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.R
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import com.example.appcentassignment.adapter.ImageItemAdapter
import com.example.appcentassignment.base.BaseFragment
import com.example.appcentassignment.databinding.FragmentRecyclerviewBinding
import com.example.appcentassignment.extenssions.showToastMsg
import com.example.appcentassignment.models.response.Photo


class SpiritFragment : BaseFragment(), ImageItemAdapter.OnItemClickListener,
    SpiritViewModel.View {

    private lateinit var binding: FragmentRecyclerviewBinding
    private var imageItemAdapter: ImageItemAdapter? = null
    private val spiritViewModel: SpiritViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentRecyclerviewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fragmentTitle.text = "Spirit"

        spiritViewModel.let {
            it.attachView(this)
            it.getSpiritItemList()
        }

        onObserveNewsList()

        imageItemAdapter = ImageItemAdapter(this)
        imageItemAdapter.let {
            binding.rvImages.apply {
                itemAnimator = DefaultItemAnimator()
                adapter = it
            }
        }
    }

    override fun clickListener(photo: Photo) {
        showToastMsg(photo.img_src)
    }

    //once we get the data from repo, populate it with the help of the adapter, NewsAdapter()
    private fun onObserveNewsList() {
        spiritViewModel.photoItemList.observe(viewLifecycleOwner) {
            it?.let {
                imageItemAdapter?.setItems(it)
            }
        }

        spiritViewModel.cameraList.observe(viewLifecycleOwner) {
            it?.let {
                val spinnerAdapter = ArrayAdapter(
                    mainActivity, R.layout.simple_spinner_item, it
                )

                spinnerAdapter.setDropDownViewResource(
                    R.layout.simple_spinner_dropdown_item
                )
                binding.spItem.adapter = spinnerAdapter
            }
        }
    }

    override fun onUpdateResponse(message: String) {
        showToastMsg(message)
    }

    override fun showProgressBar() {
        progressDialog.show()
    }

    override fun dismissProgressBar() {
        progressDialog.dismiss()
    }
}