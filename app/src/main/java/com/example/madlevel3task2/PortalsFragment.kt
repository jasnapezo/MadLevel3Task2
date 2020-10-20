package com.example.madlevel3task2

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabsIntent
import androidx.fragment.app.setFragmentResultListener
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.fragment_portal.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class PortalsFragment : Fragment() {

    private var portals = arrayListOf<Portal>()
    private var portalAdapter = PortalAdapter(portals) { portal: Portal ->
        itemClicked(portal)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_portal, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // recyclerview
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        recyclerView.adapter = portalAdapter

        observeAddPortalResult()
    }

    private fun observeAddPortalResult() {
        setFragmentResultListener(REQ_PORTAL_KEY) { _, bundle ->
            bundle.getParcelable<Portal>(BUNDLE_PORTAL_KEY)?.let {
                portals.add(it)
                portalAdapter.notifyDataSetChanged()
            } ?: Log.e("PortalsFragment", "Request triggered, but portal text is empty")
        }
    }

    private fun itemClicked(portal: Portal) {
        val builder = CustomTabsIntent.Builder()

        val customTabsIntent = builder.build()

        customTabsIntent.launchUrl(requireContext(), Uri.parse(portal.url))
    }
}