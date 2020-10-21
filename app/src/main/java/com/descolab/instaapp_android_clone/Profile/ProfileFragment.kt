package com.descolab.instaapp_android_clone.Profile

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.descolab.instaapp_android_clone.Helper.DataConfig
import com.descolab.instaapp_android_clone.Helper.Tools
import com.descolab.instaapp_android_clone.Model.User

import com.descolab.instaapp_android_clone.R
import kotlinx.android.synthetic.main.fragment_profile.*
import pl.aprilapps.easyphotopicker.EasyImage
import java.io.File

/**
 * A simple [Fragment] subclass.
 */
class ProfileFragment : Fragment(),ProfileContract.View {
    private var progressDialog : ProgressDialog? = null
    private var filePicture1: File? = null
    private var mActionListener: ProfilePresenter? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mActionListener = context?.let { ProfilePresenter(it, this) }
        progressDialog = ProgressDialog(context)
        progressDialog?.setMessage("Loading...")
        progressDialog?.setCancelable(false)
        val baseURL : String = context!!.getString(R.string.base_url)
        if (DataConfig.getString(DataConfig.AVATAR).equals("-")){
            context?.let { Tools.displayImageOriginal(it,ivProfile,R.drawable.ic_placeholder) }
        }else{
            context?.let { Tools.displayImageOriginal(it,ivProfile,baseURL+DataConfig.getString(DataConfig.AVATAR)) }
        }
        Log.d("Cek","JmlPos " +DataConfig.getString(DataConfig.TOTAL_POST))
        tvNamaLengkap.text = DataConfig.getString(DataConfig.NAME)
        tvAlamat.text = DataConfig.getString(DataConfig.ADDRESS)
        tvJmlPost.text = DataConfig.getString(DataConfig.TOTAL_POST)





    }

    override fun showProgressDialog(show: Boolean) {
        TODO("Not yet implemented")
    }

    override fun backToProfile(data: User) {
        TODO("Not yet implemented")
    }

}
