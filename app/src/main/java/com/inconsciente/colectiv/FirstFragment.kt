package com.inconsciente.colectiv

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.inconsciente.colectiv.R
import com.inconsciente.colectiv.databinding.FragmentFirstBinding
import com.inconsciente.colectiv.databinding.GridViewItemBinding

/**
 * A simple [Fragment] subclass.
 */
class FirstFragment : Fragment() {



    private val viewModel: MarketingViewModel by lazy {
        ViewModelProviders.of(this).get(MarketingViewModel::class.java)
    }

    /**
     * Inflates the layout with Data Binding, sets its lifecycle owner to the OverviewFragment
     * to enable Data Binding to observe LiveData, and sets up the RecyclerView with an adapter.
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
       //val binding = FragmentFirstBinding.inflate(inflater)
        val binding = GridViewItemBinding.inflate(inflater)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.setLifecycleOwner(this)

        // Giving the binding access to the OverviewViewModel
        binding.viewModel = viewModel

        setHasOptionsMenu(true)
        return binding.root
    }



}
