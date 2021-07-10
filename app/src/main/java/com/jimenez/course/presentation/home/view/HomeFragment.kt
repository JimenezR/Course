package com.jimenez.course.presentation.home.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.GridLayout
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.jimenez.course.R
import com.jimenez.course.databinding.FragmentHomeBinding
import com.jimenez.course.presentation.core.base.BaseFragment
import com.jimenez.course.presentation.core.callbacks.OnItemClickListener
import com.jimenez.course.presentation.home.adapters.ColorAdapter
import com.jimenez.course.presentation.home.model.Color
import com.jimenez.course.presentation.home.viewModel.HomeViewModel
import com.jimenez.course.presentation.utils.extensionFuntion.customAlertDialog
import com.jimenez.course.presentation.utils.extensionFuntion.presentShortSnackBar

class HomeFragment : BaseFragment(), OnItemClickListener<Color> {

    private var fragmentHomeBinding: FragmentHomeBinding? = null

    override fun setUp(arguments: Bundle?) {
        super.setUp(arguments)
        arguments?.let { bundle ->
            val welcome = bundle.getString("welcome")
            //fragmentHomeBinding?.root?.presentShortSnackBar(welcome.toString())
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        fragmentHomeBinding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_home,
                container,
                false
            )

        fragmentHomeBinding?.homeViewModel =
            ViewModelProvider(
                this
            ).get(HomeViewModel::class.java)

        return fragmentHomeBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragmentHomeBinding?.homeViewModel?.colorList?.observe(viewLifecycleOwner, { colors ->
            if (colors.isNotEmpty()) {
                fragmentHomeBinding?.rvColors?.apply {
                    layoutManager = GridLayoutManager(context, 3)
                    adapter = ColorAdapter(colors, this@HomeFragment)
                }
            } else {
                activity?.customAlertDialog(
                    title = "Alerta",
                    message = "No se encontraron datos",
                    positive = "Aceptar"
                )
            }
        })

        fragmentHomeBinding?.colorItem?.setTextTitle("Prueba")

    }

    override fun onItemClick(item: Color, type: String?) {
        if (type == null) {
            Toast.makeText(activity, "El color es: ${item.name}", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(activity, "El color a eliminar es: ${item.name}", Toast.LENGTH_SHORT)
                .show()
        }
    }


}