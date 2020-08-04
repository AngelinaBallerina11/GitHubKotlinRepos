package cz.angelina.kotlingithub.system.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import cz.angelina.kotlingithub.R
import cz.angelina.kotlingithub.model.Repo
import kotlinx.android.synthetic.main.repo_recyclerview_item.view.*

/**
 * Recyclerview adapter for the repositories list
 * @property itemClickListener click listener which opens the details page
 */
class RepoAdapter(private val itemClickListener: (Repo) -> Unit) : RecyclerView.Adapter<RepoAdapter.RepoVH>() {

    private val repos: ArrayList<Repo> = arrayListOf()

    inner class RepoVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView = itemView.tvName
        var description: TextView = itemView.tvDescription
        val icon: ImageView = itemView.ivOwnerAvatar
    }

    fun setRepos(newRepos: List<Repo>) {
        repos.run {
            if (isNotEmpty()) clear()
            addAll(newRepos)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoVH =
        RepoVH(parent.inflate(R.layout.repo_recyclerview_item))

    override fun getItemCount(): Int = repos.size

    override fun onBindViewHolder(holder: RepoVH, position: Int) {
        val repo = repos[position]
        with(holder) {
            holder.itemView.setOnClickListener { itemClickListener(repo) }
            name.text = repo.name
            description.text = repo.description
            Picasso.with(holder.itemView.context)
                .load(repo.avatarUrl)
                .into(icon)
        }
    }
}

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}
