package com.example.appcentassignment.views.fragment.opportunity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import com.example.appcentassignment.R
import com.example.appcentassignment.adapter.ImageItemAdapter
import com.example.appcentassignment.base.BaseFragment
import com.example.appcentassignment.databinding.FragmentRecyclerviewBinding
import com.example.appcentassignment.extenssions.showToastMsg
import com.example.appcentassignment.models.response.Photo
import com.example.appcentassignment.utils.DialogUtils.Companion.showPopup

class OpportunityFragment : BaseFragment(), ImageItemAdapter.OnItemClickListener,
    OpportunityViewModel.View {

    private lateinit var binding: FragmentRecyclerviewBinding
    private var imageItemAdapter: ImageItemAdapter? = null
    private val opportunityViewModel: OpportunityViewModel by viewModels()

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

        binding.fragmentTitle.text = "Opportunity"

        opportunityViewModel.let {
            it.attachView(this)
            it.getOpportunityItemList()
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
        showPopup(photo, this.layoutInflater.inflate(R.layout.popup, null))
    }

    //once we get the data from repo, populate it with the help of the adapter, NewsAdapter()
    private fun onObserveNewsList() {
        opportunityViewModel.photoItemList.observe(viewLifecycleOwner) {
            it?.let {
                imageItemAdapter?.setItems(it)
            }
        }

        opportunityViewModel.cameraList.observe(viewLifecycleOwner) {
            it?.let {
                val spinnerAdapter = ArrayAdapter(
                    mainActivity, android.R.layout.simple_spinner_item, it

                )

                spinnerAdapter.setDropDownViewResource(
                    android.R.layout.simple_spinner_dropdown_item
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