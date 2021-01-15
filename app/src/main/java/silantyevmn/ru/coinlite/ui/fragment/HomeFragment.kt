package silantyevmn.ru.coinlite.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.terrakok.cicerone.Router
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.loading.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import silantyevmn.ru.coinlite.App
import silantyevmn.ru.coinlite.ui.adapter.HomeRecyclerAdapter
import silantyevmn.ru.coinlite.R
import silantyevmn.ru.coinlite.mvp.model.entity.GeckoCoin
import silantyevmn.ru.coinlite.mvp.model.image.ImageLoader
import silantyevmn.ru.coinlite.mvp.model.repo.GeckoRepo
import silantyevmn.ru.coinlite.mvp.presenter.HomePresenter
import silantyevmn.ru.coinlite.mvp.view.HomeView
import javax.inject.Inject

class HomeFragment : MvpAppCompatFragment(), HomeView, HomeRecyclerAdapter.Listener {
    lateinit var adapter:HomeRecyclerAdapter
    @Inject
    lateinit var repo: GeckoRepo

    @Inject
    lateinit var imageLoader: ImageLoader<ImageView>;

    @Inject
    lateinit var router: Router

    private val presenter by moxyPresenter {
        HomePresenter(AndroidSchedulers.mainThread(), router, repo)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        App.getComponent()?.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    override fun onPause() {
        super.onPause()
        presenter.onStop()
    }

    override fun init() {
        toolbar.title=getString(R.string.app_name)
        rcv_container_home.layoutManager = LinearLayoutManager(context)
        adapter= HomeRecyclerAdapter(this,imageLoader)
        rcv_container_home.adapter=adapter;

        btn_load.setOnClickListener {
            presenter.loadData()
        }

        btn_action_cancel.setOnClickListener {
            presenter.onStop()
        }

        swipe_home.setOnRefreshListener {
            presenter.loadData()
        }
    }

    override fun updateList(list: List<Any>) {
        adapter.list=list
    }

    override fun showLoading() {
//        loadingContainer.visibility = View.VISIBLE
        swipe_home.isRefreshing = true
    }

    override fun hideLoading() {
        loadingContainer.visibility = View.GONE
        swipe_home.isRefreshing = false
    }

    override fun showError(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
    }

    override fun onItemClickById(coin: GeckoCoin) {
        presenter.onItemClickById(coin)
    }
}