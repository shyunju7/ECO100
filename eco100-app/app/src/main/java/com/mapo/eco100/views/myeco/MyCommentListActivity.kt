package com.mapo.eco100.views.myeco

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mapo.eco100.R
import com.mapo.eco100.config.NetworkSettings
import com.mapo.eco100.databinding.ActivityMyCommentListBinding
import com.mapo.eco100.entity.myeco.MyComment
import com.mapo.eco100.entity.myeco.MyCommentList
import com.mapo.eco100.service.BoardService
import retrofit2.Call
import retrofit2.Response

class MyCommentListActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private val binding by lazy { ActivityMyCommentListBinding.inflate(layoutInflater) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val adapter = MyCommentAdapter()
        recyclerView = binding.myCommentRecycler
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val service: BoardService =
            NetworkSettings.retrofit.build().create(BoardService::class.java)

        service.commentAll(1).enqueue(object : retrofit2.Callback<MyCommentList>{
            override fun onFailure(call: Call<MyCommentList>, t: Throwable) {
                Log.d("서버연결", " 실패 --------------", null)
            }

            override fun onResponse(call: Call<MyCommentList>, response: Response<MyCommentList>) {
                Log.d("서버연결", " 성공 --------------", null)
                adapter.myCommentList = response.body() as MyCommentList
                adapter.notifyDataSetChanged()
            }
        })
    }

    inner class MyCommentAdapter():RecyclerView.Adapter<ViewHolderClass>() {
        var myCommentList :MyCommentList? = null

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
            val itemView = layoutInflater.inflate(R.layout.row_myboard, null)
            val holder = ViewHolderClass(itemView)
            return holder
        }

        override fun getItemCount(): Int {
            return myCommentList?.size ?:0
        }

        override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
            val myComment = myCommentList?.get(position)

            holder.nickname.text = myComment?.writer
            holder.date.text = myComment?.date
            holder.mytitle.text = myComment?.contents
        }
    }

    class ViewHolderClass(itemView: View):RecyclerView.ViewHolder(itemView){
        val nickname = itemView.findViewById<TextView>(R.id.my_board_nickname)
        val date = itemView.findViewById<TextView>(R.id.my_board_date)
        val mytitle = itemView.findViewById<TextView>(R.id.my_board_title)
    }
}