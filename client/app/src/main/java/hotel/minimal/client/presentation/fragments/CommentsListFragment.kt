package hotel.minimal.client.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import hotel.minimal.client.App
import hotel.minimal.client.databinding.CommentsListFragmentBinding
import hotel.minimal.client.domain.models.Comment
import hotel.minimal.client.presentation.viewModels.CommentViewModel
import hotel.minimal.client.presentation.adapters.CommentAdapter
import hotel.minimal.client.presentation.modals.CommentModal
import javax.inject.Inject

class CommentsListFragment : Fragment(), View.OnClickListener {

    private var layout: CommentsListFragmentBinding? = null

    @Inject
    lateinit var commentViewModel: CommentViewModel

    private var commentsListObserver: Observer<List<Comment>>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (requireActivity().applicationContext as App).rootInjector.inject(this)
        layout = CommentsListFragmentBinding.inflate(inflater, container, false)

        val commentAdapter = CommentAdapter {
            commentViewModel.setCurrentComment(it)
            CommentModal
                .newInstance(true)
                .show(requireActivity().supportFragmentManager, null)
        }

        with (layout!!) {
            commentRecyclerView.layoutManager = LinearLayoutManager(requireContext())
            commentRecyclerView.adapter = commentAdapter
            commentRecyclerView.recycledViewPool.setMaxRecycledViews(
                CommentAdapter.VIEW_TYPE,
                CommentAdapter.MAX_POOL_SIZE
            )
        }

        layout!!.commentCreateButton.setOnClickListener(this)

        commentsListObserver = Observer {
            commentAdapter.submitList(it)
        }

        return layout?.root
    }

    override fun onClick(v: View?) {
        layout?.let {
            when(v) {
                it.commentCreateButton -> {
                    commentViewModel.setCurrentComment(null)
                    CommentModal
                        .newInstance(false)
                        .show(requireActivity().supportFragmentManager, null)
                }
                else -> {}
            }
        }
    }

    override fun onStart() {
        super.onStart()
        commentsListObserver?.let { commentViewModel.commentsList.observe(this, it) }
    }

    override fun onResume() {
        super.onResume()
        commentViewModel.loadComments()
    }

    override fun onStop() {
        super.onStop()
        commentsListObserver?.let { commentViewModel.commentsList.removeObserver(it) }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        layout = null
    }
}