package com.toppr.sampletv

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.KeyEvent.*
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.toppr.sampletv.adapter.StoryAdapter
import com.toppr.sampletv.data.StoryData
import com.toppr.sampletv.utils.HiglightImageLayoutManager
import java.lang.ref.WeakReference

class StoryActivity : AppCompatActivity() {

    lateinit var parent: ConstraintLayout
    lateinit var recyclerView: RecyclerView
    lateinit var ivPrevious: AppCompatImageView
    lateinit var ivNext: AppCompatImageView
    lateinit var clTitleOverlay: ConstraintLayout
    lateinit var viewviewPageNo: AppCompatTextView

    var currentPosition: Int = 0

    private val storyAdapter by lazy {
        StoryAdapter(this){
            //do nothing
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_story)
        recyclerView = findViewById<RecyclerView>(R.id.rv_story)
        parent = findViewById<ConstraintLayout>(R.id.parent)
        ivPrevious = findViewById<AppCompatImageView>(R.id.iv_previous)
        clTitleOverlay = findViewById(R.id.cl_title_overlay)
        ivNext = findViewById(R.id.iv_next)
        viewviewPageNo = findViewById(R.id.tv_current_page_count)
        ivNext.isSelected = true
        recyclerView.layoutManager = HiglightImageLayoutManager(this)
        val pagerSnapHelper = PagerSnapHelper()
        recyclerView.adapter = storyAdapter
        pagerSnapHelper.attachToRecyclerView(recyclerView)

        submitList()
        setCurrentlyViewingPageNo()
        findViewById<AppCompatTextView>(R.id.tv_totoal_pages).text =
            " / ${storyAdapter.itemCount.toString()}"
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        return when (keyCode) {
            KEYCODE_DPAD_LEFT -> {
                if (currentPosition > 0) {
                    currentPosition -= 1
                    recyclerView?.smoothScrollToPosition(currentPosition)
                    setCurrentlyViewingPageNo()
                    setNavigationBtnsVisibility()
                    submitList()
                }
                true
            }
            KEYCODE_DPAD_RIGHT -> {
                if (currentPosition < (storyAdapter.itemCount - 1)) {
                    currentPosition += 1
                    recyclerView?.smoothScrollToPosition(currentPosition)
                    setCurrentlyViewingPageNo()
                    setNavigationBtnsVisibility()
                    submitList()
                }
                true
            }
            KEYCODE_DPAD_CENTER -> {
                if (clTitleOverlay.isVisible) {
                    clTitleOverlay.visibility = View.GONE
                } else {
                    clTitleOverlay.visibility = View.VISIBLE
                }
                true
            }
            KEYCODE_BACK -> {
                finish()
                true
            }
            else -> {
                false
            }
        }
    }

    private fun submitList(){
        val list = getListOfStories()
        list.get(currentPosition).isSelected = true
        storyAdapter.setData(list)
    }

    private fun setNavigationBtnsVisibility() {
        ivPrevious.isSelected = currentPosition > 0
        ivNext.isSelected = currentPosition < ( storyAdapter.itemCount - 1)
    }

    private fun getListOfStories(): ArrayList<StoryData> {
        return arrayListOf(
            StoryData(R.drawable.ic_temp_story, false),
            StoryData(R.drawable.ic_temp_story, false),
            StoryData(R.drawable.ic_temp_story, false),
            StoryData(R.drawable.ic_temp_story, false),
            StoryData(R.drawable.ic_temp_story, false),
            StoryData(R.drawable.ic_temp_story, false),
            StoryData(R.drawable.ic_temp_story, false),
            StoryData(R.drawable.ic_temp_story, false),
            StoryData(R.drawable.ic_temp_story, false),
            StoryData(R.drawable.ic_temp_story, false),
            StoryData(R.drawable.ic_temp_story, false)
        )
    }

    private fun setCurrentlyViewingPageNo() {
        viewviewPageNo.text = (currentPosition + 1).toString()
    }

    companion object {
        fun startActivity(weakReference: WeakReference<Context>) {
            val intent = Intent(weakReference.get(), StoryActivity::class.java)
            weakReference.get()?.startActivity(intent)
        }
    }
}