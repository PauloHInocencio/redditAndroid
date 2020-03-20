package com.fastnews.ui.timeline

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.paging.PagedList
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.fastnews.R
import com.fastnews.mechanism.VerifyNetworkInfo
import com.fastnews.data.model.PostData
import com.fastnews.ui.detail.DetailFragment.Companion.KEY_POST
import com.fastnews.viewmodel.PostViewModel
import kotlinx.android.synthetic.main.fragment_timeline.*


class TimelineFragment : Fragment() {

    private val viewModel: PostViewModel by lazy {
        ViewModelProviders.of(this).get(PostViewModel::class.java)
    }

    private var adapter: TimelinePageAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_timeline, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buildActionBar()
        buildTimeline()
    }

    override fun onStart() {
        super.onStart()
        verifyConnectionState()
    }

    private fun verifyConnectionState() {
        context.let {
            if (VerifyNetworkInfo.isConnected(it!!)) {
                hideNoConnectionState()
                fetchTimeline()
            } else {
                fetchTimelineOffline()
                state_without_conn_timeline.setOnClickListener {
                    verifyConnectionState()
                }

            }
        }
    }

    private fun buildActionBar() {
        (activity as AppCompatActivity).supportActionBar?.setHomeButtonEnabled(false) // disable the button
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false) // remove the left caret
        (activity as AppCompatActivity).supportActionBar?.setDisplayShowHomeEnabled(false)
        (activity as AppCompatActivity).supportActionBar?.title = resources.getString(R.string.app_name)
    }

    private fun buildTimeline() {

        adapter = TimelinePageAdapter { it, imageView ->
            onClickItem(it, imageView)
        }
        timeline_rv.layoutManager = LinearLayoutManager(context)
        timeline_rv.itemAnimator = DefaultItemAnimator()
        timeline_rv.adapter = adapter
    }

    private fun fetchTimeline() {
        showProgress()
        viewModel.getPosts().observe(this, Observer {
            if (it.size != 0 ) {
                adapter?.submitList(it)
                hideProgress()
                showPosts()
            }
        })
    }

    private fun fetchTimelineOffline() {
        showProgress()
        val liveData = viewModel.getPosts()
        liveData.observe(this, object: Observer<PagedList<PostData>> {
            override fun onChanged(list: PagedList<PostData>) {
                hideProgress()
                if (list.size != 0 ) {
                    adapter?.submitList(list)
                    hideNoConnectionState()
                    showPosts()
                } else {
                    showNoConnectionState()
                }
                liveData.removeObserver(this)
            }
        })
    }


    private fun showPosts() {
        timeline_rv.visibility = View.VISIBLE
    }

    private fun showProgress() {
        state_progress_timeline.visibility = View.VISIBLE
    }

    private fun hideProgress() {
        state_progress_timeline.visibility = View.GONE
    }

    private fun showNoConnectionState() {
        state_without_conn_timeline.visibility = View.VISIBLE
    }

    private fun hideNoConnectionState() {
        state_without_conn_timeline.visibility = View.GONE
    }

    private fun onClickItem(postData: PostData?, imageView: ImageView) {
        val extras = FragmentNavigatorExtras(
            imageView to "thumbnail"
        )
        val bundle = Bundle()
        bundle.putParcelable(KEY_POST, postData)
        findNavController().navigate(R.id.action_timeline_to_detail, bundle, null, extras)
    }
}