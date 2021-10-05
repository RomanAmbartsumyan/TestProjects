package com.example.module12.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.example.module12.AutoClearedValue
import com.example.module12.R
import com.example.module12.adapters.ImageAdapter
import com.example.module12.extentions.withArguments
import com.example.module12.objects.Image

abstract class AbstractFragmentList : Fragment() {
    var images = arrayListOf<Image>()

    var imageAdapter by AutoClearedValue<ImageAdapter>()

    private var lastId: Long = 5

    private lateinit var imagesObject: ArrayList<Image>

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (savedInstanceState == null) {
            addImages()
        } else {
            with(savedInstanceState) {
                lastId = getLong(ID)
                images = getParcelableArrayList<Image>(AUTO_LIST_ID) as ArrayList<Image>
            }
        }
        initList()
        clickOnButton()
    }

    open fun clickOnButton() {}

    open fun initList() {}

    fun deleteImage(position: Int) {
        images = images.filterIndexed { index, _ -> index != position } as ArrayList<Image>
        imageAdapter.items = images
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(requireArguments().getInt(KEY_LAYOUT), container, false)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putLong(ID, lastId)
        outState.putParcelableArrayList(AUTO_LIST_ID, images)
    }

    fun addNewImage() {
        lastId++
        val image = imagesObject.random()
        image.id = lastId
        val newImage = arrayListOf(image)
        images = (newImage + images) as ArrayList<Image>
        imageAdapter.items = images
    }

    private fun addImages() {
        imagesObject = arrayListOf(
            Image(1, stringResources(R.string.tesla_image)),
            Image(2, stringResources(R.string.ford_image)),
            Image(3, stringResources(R.string.ferrari_image)),
            Image(4, stringResources(R.string.bmw_image)),
            Image(5, stringResources(R.string.kia_image))
        )
        images.addAll(imagesObject)
    }

    private fun stringResources(@StringRes string: Int): String {
        return resources.getString(string)
    }

    companion object {
        const val KEY_LAYOUT = "KEY_LAYOUT"
        const val ID = "ID"
        const val AUTO_LIST_ID = "AUTO_LIST_ID"


        fun <T : AbstractFragmentList> newInstance(@LayoutRes layout: Int, fragment: T): T {
            return fragment.withArguments {
                putInt(KEY_LAYOUT, layout)
            }
        }
    }
}
