package cz.angelina.kotlingithub.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import org.joda.time.DateTime

@Parcelize
data class Repo(
    val id: Int,
    val name: String,
    val fullRepoName: String,
    val description: String,
    val avatarUrl: String,
    val stars: Int = 0,
    val watchers: Int = 0,
    val forks: Int = 0,
    val size: Int = 0,
    val createdAt: DateTime,
    val updatedAt: DateTime
) : Parcelable
