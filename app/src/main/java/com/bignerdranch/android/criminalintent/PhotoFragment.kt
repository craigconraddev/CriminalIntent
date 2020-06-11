package com.bignerdranch.android.criminalintent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_photo.view.*

private const val ARG_URL = "url"
class PhotoFragment : DialogFragment() {
    private var photoUrl = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            photoUrl = requireArguments().getString(ARG_URL)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater!!.inflate(R.layout.fragment_photo, container, false)
        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
        Glide.with(v).load(photoUrl).into(v.crime_photo_large)
        return v
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
    }

    companion object {
        fun newInstance(photoUrl: String) =
            PhotoFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_URL, photoUrl)
                }
            }
    }

//    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
//        val photoFile: File = arguments?.getSerializable(ARG_FILE) as File
//        val photoView = view.findViewById(R.id.crime_photo_large) as ImageView
//        if (photoFile.exists()) {
//            val bitmap = getScaledBitmap(photoFile.path, requireActivity())
//            photoView.setImageBitmap(bitmap)
//        } else {
//            photoView.setImageDrawable(null)
//        }
//        return activity?.let {
//            val dialogView = LayoutInflater.from(it).inflate(R.layout.fragment_photo, null)
//            val builder = AlertDialog.Builder(it)
//                .setView(dialogView)
//                .setTitle("Criminal Photo")
//                .setCancelable(true)
//            builder.create()
//        } ?: throw IllegalStateException("Activity cannot be null")
//    }
//

}