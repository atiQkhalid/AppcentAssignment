package com.example.appcentassignment.views.fragment.Curiosity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import com.example.appcentassignment.adapter.ImageItemAdapter
import com.example.appcentassignment.base.BaseFragment
import com.example.appcentassignment.databinding.FragmentCuriosityBinding
import com.example.appcentassignment.extenssions.showToastMsg
import com.example.appcentassignment.models.response.Photo

class CuriosityFragment : BaseFragment(), ImageItemAdapter.OnItemClickListener,
    CuriosityViewModel.View {

    private lateinit var binding: FragmentCuriosityBinding
    private lateinit var imageItemAdapter: ImageItemAdapter
    private val curiosityViewModel: CuriosityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentCuriosityBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        curiosityViewModel.let {
            it.attachView(this)
            it.getICuriosityItemLists()
        }

        onObserveNewsList()

        binding.rvImages.adapter = imageItemAdapter
    }

    override fun clickListener(photo: Photo) {
        showToastMsg(photo.img_src)
    }

    //once we get the data from repo, populate it with the help of the adapter, NewsAdapter()
    private fun onObserveNewsList() {
        curiosityViewModel.itemListData.observe(viewLifecycleOwner) {
            it?.let {
                imageItemAdapter.setItems(it)
            }
        }

        imageItemAdapter.let {
            binding.rvImages.apply {
                itemAnimator = DefaultItemAnimator()
                adapter = it
            }
            it?.notifyDataSetChanged()
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