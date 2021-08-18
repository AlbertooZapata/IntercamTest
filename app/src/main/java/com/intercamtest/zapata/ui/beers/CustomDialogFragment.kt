package com.intercamtest.zapata.ui.beers

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import coil.api.load
import com.intercamtest.zapata.R
import com.intercamtest.zapata.data.model.Beer
import com.intercamtest.zapata.databinding.FragmentCustomDialogBinding

/**
 * Created by Alberto Zapata on ago, 2021
 */
class CustomDialogFragment(val beer: Beer) : DialogFragment() {


    private var _binding: FragmentCustomDialogBinding? = null
    private val binding get() = _binding!!
    private val classTag = "myLog [${javaClass.simpleName}]"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dialog!!.window?.setBackgroundDrawableResource(R.drawable.round_corner);

        _binding = FragmentCustomDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUI()
    }

    private fun setUI() {
        Log.i(classTag, beer.toString())
        binding.imageView.load(beer.image_url)
        binding.txtName.text = beer.name
        binding.txtTagLine.text = beer.tagline
        binding.txtDescription.text = beer.description
        binding.txtDate.text = beer.first_brewed

    }

    override fun onStart() {
        super.onStart()
        val width = (resources.displayMetrics.widthPixels * 0.85).toInt()
        dialog!!.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}