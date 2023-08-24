package com.example.posts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.posts.api.ApiClient
import com.example.posts.api.ApiInterface
import com.example.posts.databinding.ActivityMainBinding
import com.example.posts.model.Post
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var postAdapter: PostAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerView: RecyclerView = binding.productRv
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        postAdapter = PostAdapter(mutableListOf())
        recyclerView.adapter = postAdapter
    }

    override fun onResume() {
        super.onResume()
        getPosts()
    }

    private fun getPosts() {
        val apiClient = ApiClient.buildClient(ApiInterface::class.java)
        val request = apiClient.getPosts()

        request.enqueue(object : Callback<List<Post>!>! {
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                if (response.isSuccessful) {
                    val posts = response.body()
                    if (posts != null) {
                        postAdapter.updatePosts(posts)
                        Toast.makeText(
                            baseContext,
                            "Fetched ${posts.size} posts",
                            Toast.LENGTH_LONG
                        ).show()
                    } else {
                        Toast.makeText(baseContext, "No posts found", Toast.LENGTH_LONG).show()
                    }
                } else {
                    Toast.makeText(
                        baseContext,
                        "Error: ${response.code()} ${response.message()}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                Toast.makeText(baseContext, "Error: ${t.message}", Toast.LENGTH_LONG).show()
            }
        })
    }
}
