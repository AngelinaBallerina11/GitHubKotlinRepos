package cz.angelina.kotlingithub.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import org.joda.time.DateTime

/**
 * GitHub repository business entity
 * @property id repository ID in GitHub system
 * @property name repository name
 * @property fullRepoName owner_name/repository_name
 * @property description short description of the repository
 * @property avatarUrl URL of the owner's avatar
 * @property stars number of stars given to the repository
 * @property watchers number of watchers the repository has
 * @property forks number of forks
 * @property createdAt the date and time of repository creation
 * @property updatedAt the data and time of the last update
 */
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
    val createdAt: DateTime,
    val updatedAt: DateTime
) : Parcelable
