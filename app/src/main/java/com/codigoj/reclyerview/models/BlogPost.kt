package com.codigoj.reclyerview.models

data class BlogPost(var title: String,
                    var body: String,
                    var image: String,
                    var username: String) {

    override fun toString(): String {
        return "BlogPost(title='$title', body='$body', image='$image', username='$username')"
    }
}