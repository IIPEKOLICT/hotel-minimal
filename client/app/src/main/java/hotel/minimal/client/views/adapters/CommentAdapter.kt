package hotel.minimal.client.views.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import loshica.client.databinding.CommentCardBinding
import hotel.minimal.client.interfaces.IPickHandler
import hotel.minimal.client.domain.models.Comment

class CommentAdapter(
    private val pickHandler: IPickHandler
) : RecyclerView.Adapter<CommentAdapter.ViewHolder>() {

    private var comments: MutableList<Comment> = mutableListOf()
    private var layout: CommentCardBinding? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        layout = CommentCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(layout!!, pickHandler)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val comment: Comment = comments[position]

        with (holder.layout) {
            commentCardContent.text = comment.content
            root.setOnClickListener(holder)
        }
    }

    override fun getItemCount(): Int = comments.size

    class ViewHolder internal constructor(
        val layout: CommentCardBinding,
        private val pickHandler: IPickHandler
    ) : RecyclerView.ViewHolder(layout.root), View.OnClickListener {

        override fun onClick(v: View?) {
            pickHandler.onPickCard(adapterPosition)
        }
    }

    fun update(newComments: List<Comment>?) {
        newComments?.let {
            comments.clear()
            comments = it.toMutableList()
            notifyDataSetChanged()
        }
    }
}