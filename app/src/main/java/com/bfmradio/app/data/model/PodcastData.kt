package com.bfmradio.app.data.model
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


data class PodcastData(
    @SerialName("id")
    val id: String? = null,

    @SerialName("mp3_duration")
    val mp3_duration: String? = null,

    @SerialName("mp3file_size")
    val mp3FileSize: String? = null,

    @SerialName("pagetitle")
    val pageTitle: String? = null,

    @SerialName("mp3")
    val mp3: String? = null,

    @SerialName("content")
    val content: String? = null,

    @SerialName("image")
    val image: String? = null,

    @SerialName("fbimage")
    val fbImage: String? = null,

    @SerialName("alias")
    val alias: String? = null,

    @SerialName("slug")
    val slug: String? = null,

    @SerialName("interviewtime")
    val interviewtime: String? = null,

    @SerialName("content_type")
    val contentType: String? = null,

    @SerialName("editedon")
    val editedOn: String? = null,

    @SerialName("publishedon")
    val publishedOn: String? = null,

    @SerialName("parent")
    val parent: String? = null,

    @SerialName("belt_name")
    val beltName: String? = null,

    @SerialName("hidedownload")
    val hideDownload: String? = null,

    @SerialName("hidesponsor")
    val hideSponsor: String? = null,

    @SerialName("selected_highlight")
    val selectedHighlight: String? = null,

    @SerialName("show_name")
    val showName: String? = null,

    @SerialName("delisted")
    val delisted: String? = null,

    @SerialName("producers")
    val producers: String? = null,

    @SerialName("presenters")
    val presenters: String? = null,

    @SerialName("category")
    val category: List<String>? = null,

    @SerialName("tags")
    val tags: List<String>? = null,

    @SerialName("createdon")
    val createdOn: String? = null,

    @SerialName("guest_names")
    val guestNames: List<String>? = null,

    @SerialName("salutations")
    val salutations: List<String>? = null,

    @SerialName("guest_designations")
    val guestDesignations: List<String>? = null,

    @SerialName("guest_organisations")
    val guestOrganisations: List<String>? = null,

    @SerialName("guests")
    val guests: List<String>? = null
)