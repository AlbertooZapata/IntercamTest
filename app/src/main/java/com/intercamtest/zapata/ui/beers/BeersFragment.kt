package com.intercamtest.zapata.ui.beers

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.intercamtest.zapata.data.DataSource
import com.intercamtest.zapata.data.model.Beer
import com.intercamtest.zapata.databinding.FragmentBeersBinding
import com.intercamtest.zapata.domain.beer.BeerRepoImpl
import com.intercamtest.zapata.ui.beers.viewmodel.BeerVMFactory
import com.intercamtest.zapata.ui.beers.viewmodel.BeerViewModel
import com.intercamtest.zapata.vo.Resource

/**
 * Created by Alberto Zapata on ago, 2021
 */
class BeersFragment : Fragment(), BeerAdapter.OnBeerClickListener {


    private val viewModel by viewModels<BeerViewModel> {
        BeerVMFactory(BeerRepoImpl(DataSource()))
    }
    private var _binding: FragmentBeersBinding? = null
    private val binding get() = _binding!!
    private val classTag = "myLog [${javaClass.simpleName}]"

    private var page = 0
    private var perPage = 10


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBeersBinding.inflate(inflater, container, false)
        return binding.root
    }


    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpObserver()
    }

    private fun setUpObserver() {
        viewModel.getBeers(page, perPage).observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Resource.Loading -> {
                    Log.i(classTag, "Loading source...")
                }
                is Resource.Success -> {
                    setRecyclerView(result.data)
                }
                is Resource.Failure -> {
                    Toast.makeText(requireContext(), result.exception.message, Toast.LENGTH_LONG)
                        .show()
                }
            }
        })
    }

    private fun setRecyclerView(beerList: List<Beer>) {
        binding.rvBeers.layoutManager =
            GridLayoutManager(requireContext(), 2)
        binding.rvBeers.adapter = BeerAdapter(requireContext(), beerList, this)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onItemClick(beer: Beer) {
        CustomDialogFragment(beer).show(parentFragmentManager, "MyCustomFragment")
    }

}