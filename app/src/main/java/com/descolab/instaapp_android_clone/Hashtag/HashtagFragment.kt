package com.descolab.instaapp_android_clone.Hashtag

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.descolab.instaapp_android_clone.R

/**
 * A simple [Fragment] subclass.
 */
class HashtagFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_hashtag, container, false)
    }

}
