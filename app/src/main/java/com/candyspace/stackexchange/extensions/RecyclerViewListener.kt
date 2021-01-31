package com.candyspace.stackexchange.extensions

import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Prakash Nandi on 31/01/21.
 *
 * Recycler view item click listener extension
 */
fun <T : RecyclerView.ViewHolder> T.listen(event: (position: Int, type: Int) -> Unit): T {
    itemView.setOnClickListener {
        event.invoke(adapterPosition, itemViewType)
    }
    return this
}