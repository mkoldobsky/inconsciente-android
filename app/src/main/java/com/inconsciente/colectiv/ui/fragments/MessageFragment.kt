package com.inconsciente.colectiv.ui.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.inconsciente.colectiv.R
import com.inconsciente.colectiv.viewmodels.MessageViewModel
import com.inconsciente.colectiv.databinding.MessageFragmentBinding

/**
 * A simple [Fragment] subclass.
 */
class MessageFragment : Fragment() {
    private val viewModel: MessageViewModel by lazy {
        ViewModelProviders.of(this).get(MessageViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {




        val binding = MessageFragmentBinding.inflate(inflater)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.menu_main, menu)
        super.onCreateOptionsMenu(menu, menuInflater)
    }

}
