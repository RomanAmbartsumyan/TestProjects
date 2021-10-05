package com.example.module12.fragments

import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.module12.AutoClearedValue
import com.example.module12.R
import com.example.module12.adapters.AutoAdapter
import com.example.module12.dialogs.Dialog
import com.example.module12.fragments.interfaces.AutoInterface
import com.example.module12.objects.Auto
import jp.wasabeef.recyclerview.animators.LandingAnimator
import kotlinx.android.synthetic.main.fragment_list.*

class AutoFragmentList : Fragment(R.layout.fragment_list),
    AutoInterface {
    private var autos = arrayListOf<Auto>()

    private var autoAdapter by AutoClearedValue<AutoAdapter>()

    private var isAutoListEmpty = false

    private var lastId: Long = 5

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (savedInstanceState == null) {
            addCars()
        } else {
            with(savedInstanceState) {
                autos = getParcelableArrayList<Auto>(AUTO_LIST) as ArrayList<Auto>
                isAutoListEmpty = getBoolean(IS_AUTO_LIST_IS_EMPTY)
                lastId = getLong(ID)
            }
            if (isAutoListEmpty) {
                textViewEmptyList.visibility = View.VISIBLE
            }
        }
        initList()
        addFab.setOnClickListener {
            Dialog().show(childFragmentManager, "tagDialog")
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList(AUTO_LIST, autos)
        outState.putBoolean(IS_AUTO_LIST_IS_EMPTY, isAutoListEmpty)
        outState.putLong(ID, lastId)
    }

    private fun addCars() {
        val cars = arrayListOf(
            Auto.UsualCar(
                id = 1,
                image = stringResources(R.string.ford_image),
                brand = stringResources(R.string.ford),
                model = stringResources(R.string.ford_model)
            ),
            Auto.ElectroCar(
                id = 2,
                image = stringResources(R.string.tesla_image),
                brand = stringResources(R.string.tesla),
                model = stringResources(R.string.tesla_model),
                type = stringResources(R.string.electro_car)
            ),
            Auto.UsualCar(
                id = 3,
                image = stringResources(R.string.ferrari_image),
                brand = stringResources(R.string.ferrari),
                model = stringResources(R.string.ferrari_model)
            ),
            Auto.UsualCar(
                id = 4,
                image = stringResources(R.string.kia_image),
                brand = stringResources(R.string.kia),
                model = stringResources(R.string.kia_model)
            ),
            Auto.UsualCar(
                id = 5,
                image = stringResources(R.string.bmw_image),
                brand = stringResources(R.string.bmw),
                model = stringResources(R.string.bmw_model)
            )
        )
        autos.addAll(cars)

    }


    private fun stringResources(@StringRes string: Int): String {
        return resources.getString(string)
    }

    private fun initList() {
        autoAdapter = AutoAdapter { position -> deleteCar(position) }
        with(carList) {
            autoAdapter.items = autos
            adapter = autoAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            itemAnimator = LandingAnimator()
        }
    }

    private fun deleteCar(position: Int) {
        autos = autos.filterIndexed { index, _ -> index != position } as ArrayList<Auto>
        autoAdapter.items = autos
        if (autos.isEmpty()) {
            textViewEmptyList.visibility = View.VISIBLE
            isAutoListEmpty = true
        }
    }

    override fun addNewAuto(brand: String, model: String, isElectroCar: Boolean) {
        textViewEmptyList.visibility = View.GONE
        isAutoListEmpty = false

        val images = listOf(
            getString(R.string.bmw_image),
            getString(R.string.ferrari_image),
            getString(R.string.kia_image),
            getString(R.string.ford_image)
        )

        lastId++

        if (isElectroCar) {
            val car = Auto.ElectroCar(
                lastId,
                getString(R.string.tesla_image),
                brand,
                model,
                stringResources(R.string.electro_car)
            )
            autos = (arrayListOf(car) + autos) as ArrayList<Auto>
            autoAdapter.items = autos
        } else {
            val car = Auto.UsualCar(lastId, images.random(), brand, model)
            autos = (arrayListOf(car) + autos) as ArrayList<Auto>
            autoAdapter.items = autos
            carList.scrollToPosition(0)
        }
    }

    companion object {
        const val AUTO_LIST = "AUTO_LIST"
        const val IS_AUTO_LIST_IS_EMPTY = "IS_AUTO_LIST_IS_EMPTY"
        const val ID = "ID"
    }
}