package com.descolab.instaapp_android_clone.Home

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.descolab.instaapp_android_clone.Model.Feed

import com.descolab.instaapp_android_clone.R
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment(),HomeContract.View {
    private var mAdapter: HomeAdapter? = null
    private var progressDialog : ProgressDialog? = null
    private var mActionListener: HomePresenter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressDialog = ProgressDialog(context)
        progressDialog?.setMessage("Loading...")
        progressDialog?.setCancelable(false)
        mActionListener = context?.let { HomePresenter(it, this) }
        mActionListener?.loadFeed()

    }

    override fun showFeed(data: ArrayList<Feed>) {
        mAdapter = context?.let {
            HomeAdapter(it, data)
        }
        rvFeed?.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rvFeed?.setHasFixedSize(true)
        rvFeed?.adapter = mAdapter
    }

    override fun showProgressDialog(show: Boolean) {
        if (show) progressDialog?.show()
        else progressDialog?.dismiss()
    }


}
