package ru.test.notifier.view.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.test.notifier.R
import ru.test.notifier.storage.StorageRepository
import ru.test.notifier.view.adapters.EventsAdapter

class EventsFragment: Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: EventsAdapter

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_events, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val storage = StorageRepository.getInstance()
        adapter = EventsAdapter(view.context)

        recyclerView = view.findViewById(R.id.rv_events)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter
        adapter.setData(storage.getAllEvents().map{ it.event })
        recyclerView.layoutManager = LinearLayoutManager(view.context)
    }
}