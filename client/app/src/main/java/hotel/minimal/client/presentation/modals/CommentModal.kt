package hotel.minimal.client.presentation.modals

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import hotel.minimal.client.App
import hotel.minimal.client.R
import hotel.minimal.client.databinding.CommentModalBinding
import hotel.minimal.client.domain.models.Comment
import hotel.minimal.client.presentation.viewModels.CommentViewModel
import loshica.vendor.view.LOSDialogBuilder
import javax.inject.Inject

class CommentModal : DialogFragment() {

    private var layout: CommentModalBinding? = null

    @Inject
    lateinit var commentViewModel: CommentViewModel

    private var commentObserver: Observer<Comment>? = null

    private var isEdit: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        (requireActivity().applicationContext as App).rootInjector.inject(this)
        super.onCreate(savedInstanceState)

        arguments?.let {
            isEdit = it.getBoolean(IS_EDIT)
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = LOSDialogBuilder(requireActivity())

        layout = CommentModalBinding.inflate(requireActivity().layoutInflater)

        commentObserver = Observer {
            if (isEdit) {
                layout?.commentContentInput?.setText(it.content)
            }
        }

        val titleRef: Int = if (isEdit) {
            R.string.change_comment_modal_header
        } else {
            R.string.create_comment_modal_header
        }

        val positiveButtonRef: Int = if (isEdit) R.string.change else R.string.create
        val negativeButtonRef: Int = if (isEdit) R.string.delete else R.string.cancel

        return builder
            .setView(layout!!.root)
            .setTitle(requireActivity().resources.getText(titleRef))
            .setPositiveButton(positiveButtonRef) { dialog, _ -> onSubmit(dialog) }
            .setNegativeButton(negativeButtonRef) { dialog, _ -> onDelete(dialog) }
            .create()
    }

    private fun onSubmit(dialog: DialogInterface) {
        layout?.let {
            try {
                val content: String? = it.commentContentInput.text?.toString()

                if (content == null || content.trim().isEmpty()) {
                    throw Exception()
                }

                if (isEdit) {
                    commentViewModel.updateComment(content)
                } else {
                    commentViewModel.createComment(content)
                }
            } catch (e: Exception) {
                Toast.makeText(requireContext(), "Invalid data", Toast.LENGTH_SHORT).show()
                dialog.cancel()
            }
        }
    }

    private fun onDelete(dialog: DialogInterface) {
        if (isEdit) {
            commentViewModel.deleteComment()
        }

        dialog.cancel()
    }

    override fun onStart() {
        super.onStart()
        commentObserver?.let { commentViewModel.comment.observe(this, it) }
    }

    override fun onStop() {
        super.onStop()
        commentObserver?.let { commentViewModel.comment.removeObserver(it) }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        layout = null
    }

    companion object {
        private const val IS_EDIT = "isEdit"

        @JvmStatic
        fun newInstance(isEdit: Boolean) =
            CommentModal().apply {
                arguments = Bundle().apply {
                    putBoolean(IS_EDIT, isEdit)
                }
            }
    }
}