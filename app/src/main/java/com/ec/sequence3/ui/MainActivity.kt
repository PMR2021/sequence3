package com.ec.sequence3.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ec.sequence3.data.model.Post
import com.ec.sequence3.R
import com.ec.sequence3.ui.adapter.PostAdapter
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    private lateinit var postAdapter: PostAdapter

    private val viewModel by viewModels<MainViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupRecyclerView()
        loadAndDisplayPosts()
    }


    private fun loadAndDisplayPosts() {

        viewModel.loadPost()
        viewModel.posts.observe(this) { viewState ->
            when (viewState) {
                is MainViewModel.ViewState.Content -> {
                    postAdapter.show(viewState.posts)
                    showProgress(false)
                }
                MainViewModel.ViewState.Loading -> showProgress(true)
                is MainViewModel.ViewState.Error -> {
                    showProgress(false)
                    Toast.makeText(this@MainActivity, "${viewState.message} ", Toast.LENGTH_SHORT)
                        .show()
                }
            }

        }


    }

    private fun showProgress(show: Boolean) {
        val progress = findViewById<View>(R.id.progress)
        val list = findViewById<View>(R.id.list)
        progress.isVisible = show
        list.isVisible = !show
    }

    private fun setupRecyclerView() {
        val recyclerview = findViewById<RecyclerView>(R.id.list)
        postAdapter = PostAdapter(actionListener = object : PostAdapter.ActionListener {
            override fun onItemClicked(post: Post) {

                Toast.makeText(
                    this@MainActivity,
                    "Item Clicked ${post.title}",
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onImageClicked() {
                loadAndDisplayPosts()
                Toast.makeText(this@MainActivity, "Image Clicked ", Toast.LENGTH_SHORT).show()

            }

        })

        recyclerview.adapter = postAdapter
        recyclerview.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
    }


}