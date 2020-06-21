package com.inconsciente.colectiv.ui.fragments
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.inconsciente.colectiv.R
import com.inconsciente.colectiv.network.MessageProperty
import com.inconsciente.colectiv.viewmodels.MessageCardRecyclerViewAdapter
import kotlinx.android.synthetic.main.message_grid_fragment.view.*

class MessageGridFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment with the ProductGrid theme
        val view = inflater.inflate(R.layout.message_grid_fragment, container, false)

        // Set up the toolbar.
        (activity as AppCompatActivity).setSupportActionBar(view.app_bar)

        // Set up the RecyclerView
        view.recycler_view.setHasFixedSize(true)
        view.recycler_view.layoutManager = GridLayoutManager(context, 2, RecyclerView.VERTICAL, false)
        val adapter = MessageCardRecyclerViewAdapter(initMessageList())
        view.recycler_view.adapter = adapter
        val largePadding = resources.getDimensionPixelSize(R.dimen.message_grid_spacing)
        val smallPadding = resources.getDimensionPixelSize(R.dimen.message_grid_spacing_small)
        //view.recycler_view.addItemDecoration(ProductGridItemDecoration(largePadding, smallPadding))

        return view;
    }

    private fun initMessageList(): List<MessageProperty> {
        return listOf<MessageProperty>(MessageProperty("título", "descipción", "https://inconsciente.s3.us-east-2.amazonaws.com/drinking-wine.JPG", ""))
    }

    override fun onCreateOptionsMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.menu_main, menu)
        super.onCreateOptionsMenu(menu, menuInflater)
    }
}