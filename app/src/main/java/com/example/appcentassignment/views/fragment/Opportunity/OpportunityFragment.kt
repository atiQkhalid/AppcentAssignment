package com.example.appcentassignment.views.fragment.Opportunity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import com.example.appcentassignment.adapter.ImageItemAdapter
import com.example.appcentassignment.base.BaseFragment
import com.example.appcentassignment.databinding.FragmentOpportunityBinding
import com.example.appcentassignment.extenssions.showToastMsg
import com.example.appcentassignment.models.response.Photo

class OpportunityFragment : BaseFragment(), ImageItemAdapter.OnItemClickListener,
    OpportunityViewModel.View {

    private lateinit var binding: FragmentOpportunityBinding
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
        binding = FragmentOpportunityBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
        showToastMsg(photo.img_src)
    }

    //once we get the data from repo, populate it with the help of the adapter, NewsAdapter()
    private fun onObserveNewsList() {
        opportunityViewModel.itemList.observe(viewLifecycleOwner) {
            it?.let {
                imageItemAdapter?.setItems(it)
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