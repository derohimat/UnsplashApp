package net.derohimat.unsplashapi.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.fragment_main.*
import net.derohimat.unsplashapi.R
import net.derohimat.unsplashapi.viewmodel.MainViewModel

class MainFragment : Fragment() {

    lateinit var viewModel: MainViewModel
    private val imageAdapter = ImageAdapter(arrayListOf())
    private var isOnLoad: Boolean = false
    private var page: Int = 0

    companion object {
        fun newInstance(): MainFragment {
            return MainFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel =
            ViewModelProviders.of(activity as AppCompatActivity).get(MainViewModel::class.java)
        viewModel.refresh()

        rvImage.apply {
            layoutManager = GridLayoutManager(context, 3)
            setAdapter(imageAdapter)
            enableLoadMore = true
            pageSize = 12
            onRefresh = {
                if (!isOnLoad) {
                    page = 0
                    enableLoadMore = true
                    viewModel.refresh()
                }
            }
            onLoadMore = { currentItemCount, pageSize ->
                if (!isOnLoad) {
                    page++
                    viewModel.getImages(page, true)
                }
            }
            initLoading()
        }

        setupChipFilter()

        observeViewModel()
    }

    private fun setupChipFilter() {
        val filterChipListener =
            CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked) {
                    viewModel.query.value = buttonView.text.toString()
                }
            }
        chipMuslim.setOnCheckedChangeListener(filterChipListener)
        chipAwesome.setOnCheckedChangeListener(filterChipListener)
        chipHaj.setOnCheckedChangeListener(filterChipListener)
        chipIndonesia.setOnCheckedChangeListener(filterChipListener)
        chipMosque.setOnCheckedChangeListener(filterChipListener)
        chipHijab.setOnCheckedChangeListener(filterChipListener)

        chipMuslim.isChecked = true
    }

    private fun observeViewModel() {
        viewModel.images.observe(this, Observer { countries ->
            countries?.let {
                val loadMore = viewModel.loadMore.value as Boolean
                if (loadMore) {
                    imageAdapter.updateData(it)
                } else {
                    imageAdapter.addData(it)
                }
            }
        })

        viewModel.imageLoadError.observe(this, Observer { isError ->
            isError?.let { tvError.visibility = if (it) View.VISIBLE else View.GONE }
        })

        viewModel.lastPage.observe(this, Observer { isLastPage ->
            rvImage.enableLoadMore = !isLastPage
        })

        viewModel.query.observe(this, Observer {
            viewModel.refresh()
        })

        viewModel.loading.observe(this, Observer { isLoading ->
            isLoading?.let {
                isOnLoad = it
                if (it) {
                    tvError.visibility = View.GONE
                } else {
                    rvImage.completeLoading()
                }
            }
        })
    }
}
