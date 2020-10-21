package com.descolab.instaapp_android_clone.AddPhoto

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.descolab.instaapp_android_clone.Helper.DataConfig
import com.descolab.instaapp_android_clone.Helper.Tools
import com.descolab.instaapp_android_clone.Helper.Utils
import com.descolab.instaapp_android_clone.Model.User

import com.descolab.instaapp_android_clone.R
import id.zelory.compressor.Compressor
import kotlinx.android.synthetic.main.fragment_add_photo.*
import pl.aprilapps.easyphotopicker.DefaultCallback
import pl.aprilapps.easyphotopicker.EasyImage
import java.io.File
import java.io.FileInputStream

/**
 * A simple [Fragment] subclass.
 */
class AddPhotoFragment : Fragment(),AddPhotoContract.View {
    private var mActionListener: AddPhotoPresenter? = null
    private var filePicture1: File? = null
    private var filePicture2: File? = null
    private var filePicture3: File? = null
    private var progressDialog : ProgressDialog? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_photo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val baseURL : String = context!!.getString(R.string.base_url)
        if (DataConfig.getString(DataConfig.AVATAR).equals("-")){
            context?.let { Tools.displayImageOriginal(it,ivProfile,R.drawable.ic_placeholder) }
        }else{
            context?.let { Tools.displayImageOriginal(it,ivProfile,baseURL+DataConfig.getString(DataConfig.AVATAR)) }
        }
        mActionListener = context?.let { AddPhotoPresenter(it, this) }

        progressDialog = ProgressDialog(context)
        progressDialog?.setMessage("Loading...")
        progressDialog?.setCancelable(false)

        btnUpload1.setOnClickListener {
            EasyImage.openChooserWithGallery(this,"Choose file",1)
        }
        btnUpload2.setOnClickListener {
            EasyImage.openChooserWithGallery(this,"Choose file",2)
        }
        btnUpload3.setOnClickListener {
            EasyImage.openChooserWithGallery(this,"Choose file",3)
        }

        btnSimpan.setOnClickListener {

                if (filePicture1==null){
                    val builder = AlertDialog.Builder(activity)
                    builder.setTitle("InstaApp")
                    builder.setMessage("Upload gambar dulu ya...")
                    builder.setCancelable(false)
                    builder.setPositiveButton("OK"){dialog, which ->
                        dialog.dismiss()
                    }
                    val dialog: AlertDialog = builder.create()
                    dialog.show()
                }else{
                    mActionListener?.saveFeed(
                        etDeskripsi.text.toString(),
                        etLokasi.text.toString(),
                        DataConfig.getString(DataConfig.USER_ID),
                        (System.currentTimeMillis()/1000).toString(),
                        filePicture1,filePicture2,filePicture3)
                }

        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        EasyImage.handleActivityResult(requestCode, resultCode, data, activity, object : DefaultCallback() {
            override fun onImagePickerError(e: Exception?, source: EasyImage.ImageSource?, type: Int) {
                //Some error handling
                showProgressDialog(false)
            }

            override fun onImagePicked(imageFile: File, source: EasyImage.ImageSource, type: Int) {

                Log.d("Test", "imageFile " +imageFile.length())
                val fileSizeInKB = imageFile.length() / 1024
                // Convert the KB to MegaBytes (1 MB = 1024 KBytes)
                Log.d("Test", "fileSizeInKB " +fileSizeInKB.toString())
                val fileSizeInMB = fileSizeInKB / 1024
                if (imageFile.length() > 1024 && type ==1) {
                    Utils.applyRotationIfNeeded(imageFile)
                    var compressedImageFile = Compressor(activity)
                        .setMaxWidth(700)
                        .setMaxHeight(700)
                        .setQuality(90)
                        .setCompressFormat(Bitmap.CompressFormat.JPEG)
                        .compressToFile(imageFile)
                    filePicture1 = compressedImageFile
                    val options = BitmapFactory.Options()
                    options.inPreferredConfig = Bitmap.Config.ARGB_8888
                    val bitmap1 = BitmapFactory.decodeStream(FileInputStream(filePicture1), null, options)
                    btnUpload1.setImageBitmap(bitmap1)
                }else if (imageFile.length() > 1024 && type ==2){
                    Utils.applyRotationIfNeeded(imageFile)
                    var compressedImageFile2 = Compressor(activity)
                        .setMaxWidth(700)
                        .setMaxHeight(700)
                        .setQuality(90)
                        .setCompressFormat(Bitmap.CompressFormat.JPEG)
                        .compressToFile(imageFile)
                    filePicture2 = compressedImageFile2
                    val options = BitmapFactory.Options()
                    options.inPreferredConfig = Bitmap.Config.ARGB_8888
                    val bitmap2 = BitmapFactory.decodeStream(FileInputStream(filePicture2), null, options)
                    btnUpload2.setImageBitmap(bitmap2)
                }else if (imageFile.length() > 1024 && type ==3){
                    Utils.applyRotationIfNeeded(imageFile)
                    var compressedImageFile3 = Compressor(activity)
                        .setMaxWidth(700)
                        .setMaxHeight(700)
                        .setQuality(90)
                        .setCompressFormat(Bitmap.CompressFormat.JPEG)
                        .compressToFile(imageFile)
                    filePicture3 = compressedImageFile3
                    val options = BitmapFactory.Options()
                    options.inPreferredConfig = Bitmap.Config.ARGB_8888
                    val bitmap3 = BitmapFactory.decodeStream(FileInputStream(filePicture3), null, options)
                    btnUpload3.setImageBitmap(bitmap3)
                }else{
                    Log.d("Test", "Ukuran Foto terlalu Besar" )
                }
            }

            override fun onCanceled(source: EasyImage.ImageSource?, type: Int) {
                showProgressDialog(false)
            }
        })
    }

    override fun showProgressDialog(show: Boolean) {
        if (show) progressDialog?.show()
        else progressDialog?.dismiss()
    }

    override fun backToProfile(data: User) {
        data.getJmlPost()?.let { DataConfig.setString(DataConfig.TOTAL_POST, it) }

        val builder = AlertDialog.Builder(activity)
        builder.setTitle("InstaApp")
        builder.setMessage("Feed berhasil dibagikan")
        builder.setCancelable(false)
        builder.setPositiveButton("OK"){dialog, which ->

            etDeskripsi.text.clear()
            etLokasi.text.clear()
            btnUpload1.setImageBitmap(null)
            btnUpload2.setImageBitmap(null)
            btnUpload3.setImageBitmap(null)
            dialog.dismiss()
        }
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

}
