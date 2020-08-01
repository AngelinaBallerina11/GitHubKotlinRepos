package cz.angelina.kotlingithub.system

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import cz.angelina.kotlingithub.R
import cz.angelina.kotlingithub.model.Repo
import kotlinx.android.synthetic.main.main_recyclerview_item.view.*

class RepoAdapter : RecyclerView.Adapter<RepoAdapter.RepoVH>() {

    private val repos: ArrayList<Repo> = arrayListOf()

    inner class RepoVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView = itemView.tvName
        var description: TextView = itemView.tvDescription
    }

    fun setRepos(newRepos: List<Repo>) {
        repos.run {
            if (isNotEmpty()) clear()
            addAll(newRepos)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoVH =
        RepoVH(parent.inflate(R.layout.main_recyclerview_item))

    override fun getItemCount(): Int = repos.size

    override fun onBindViewHolder(holder: RepoVH, position: Int) {
        val repo = repos[position]
        holder.name.text = repo.name
        holder.description.text = repo.description
    }
}

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}
