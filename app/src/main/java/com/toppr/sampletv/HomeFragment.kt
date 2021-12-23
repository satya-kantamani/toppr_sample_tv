package com.toppr.sampletv

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager


class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        populateSubjectCards(view)
        populateContinueVideoCards(view)
        populateContinueStoryCards(view)
    }

    private fun populateSubjectCards(view : View){
        val adapter = RecyclerAdapter(
            requireContext(),
            listOf(
                R.drawable.ic_english,
                R.drawable.ic_maths,
                R.drawable.ic_art,
                R.drawable.ic_biology,
                R.drawable.ic_chemistry,
                R.drawable.ic_english,
                R.drawable.ic_maths,
                R.drawable.ic_art,
                R.drawable.ic_biology,
                R.drawable.ic_chemistry,
            )
        )

        val recyclerView: RecyclerView = view.findViewById(R.id.subjects_recycler_view)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(
            requireContext(), LinearLayoutManager.HORIZONTAL,
            false
        )
    }

    private fun populateContinueVideoCards(view : View){
        val adapter = RecyclerAdapter(
            requireContext(),
            listOf(
                R.drawable.ic_continue_video,
                R.drawable.ic_continue_video,
                R.drawable.ic_continue_video,
                R.drawable.ic_continue_video,
                R.drawable.ic_continue_video,
                R.drawable.ic_continue_video,
                R.drawable.ic_continue_video,
                R.drawable.ic_continue_video,
            )
        )

        val recyclerView: RecyclerView = view.findViewById(R.id.continue_videos_recycler_view)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(
            requireContext(), LinearLayoutManager.HORIZONTAL,
            false
        )
    }

    private fun populateContinueStoryCards(view : View){
        val adapter = RecyclerAdapter(
            requireContext(),
            listOf(
                R.drawable.ic_story_1,
                R.drawable.ic_story_1,
                R.drawable.ic_story_1,
                R.drawable.ic_story_1,
                R.drawable.ic_story_1,
                R.drawable.ic_story_1,
                R.drawable.ic_story_1,
                R.drawable.ic_story_1,
            )
        )

        val recyclerView: RecyclerView = view.findViewById(R.id.continue_stories_recycler_view)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(
            requireContext(), LinearLayoutManager.HORIZONTAL,
            false
        )
    }
}