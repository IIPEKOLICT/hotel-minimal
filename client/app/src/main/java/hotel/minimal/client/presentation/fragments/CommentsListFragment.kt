package hotel.minimal.client.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import hotel.minimal.client.databinding.CommentsListFragmentBinding
import hotel.minimal.client.presentation.interfaces.IPickHandler
import hotel.minimal.client.domain.models.Comment
import hotel.minimal.client.presentation.viewModels.CommentViewModel
import hotel.minimal.client.presentation.adapters.CommentAdapter
import hotel.minimal.client.presentation.modals.CommentModal

class CommentsListFragment : Fragment(), View.OnClickListener, IPickHandler {

    private var layout: CommentsListFragmentBinding? = null
    private val commentViewModel: CommentViewModel by activityViewModels()

    private var commentsListObserver: Observer<List<Comment>>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        layout = CommentsListFragmentBinding.inflate(inflater, container, false)
        val commentAdapter = CommentAdapter(this)

        with (layout!!) {
            commentRecyclerView.layoutManager = LinearLayoutManager(requireContext())
            commentRecyclerView.adapter = commentAdapter
        }

        layout!!.commentCreateButton.setOnClickListener(this)
        commentsListObserver = Observer { commentAdapter.update(it) }

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

    override fun onPickCard(position: Int) {
        commentViewModel.setCurrentComment(position)
        CommentModal
            .newInstance(true)
            .show(requireActivity().supportFragmentManager, null)
    }
}