package silantyevmn.ru.coinlite.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.github.terrakok.cicerone.Router
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.fragment_chart.*
import kotlinx.android.synthetic.main.fragment_chart.chartCurrency
import kotlinx.android.synthetic.main.fragment_chart.ivCurrencyDetailIcon
import kotlinx.android.synthetic.main.fragment_chart.tvATH
import kotlinx.android.synthetic.main.fragment_chart.tvAthChange
import kotlinx.android.synthetic.main.fragment_chart.tvCirculatingSupply
import kotlinx.android.synthetic.main.fragment_chart.tvDetailMarketCapRank
import kotlinx.android.synthetic.main.fragment_chart.tvMarketCapChange
import kotlinx.android.synthetic.main.fragment_chart.tvTotalSupply
import kotlinx.android.synthetic.main.fragment_chart.*
import kotlinx.android.synthetic.main.loading.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import silantyevmn.ru.coinlite.App
import silantyevmn.ru.coinlite.R
import silantyevmn.ru.coinlite.mvp.model.entity.GeckoCoin
import silantyevmn.ru.coinlite.mvp.model.entity.GeckoCoinItem
import silantyevmn.ru.coinlite.mvp.model.image.ImageLoader
import silantyevmn.ru.coinlite.mvp.model.repo.GeckoRepo
import silantyevmn.ru.coinlite.mvp.presenter.ChartPresenter
import silantyevmn.ru.coinlite.mvp.view.ChartView
import silantyevmn.ru.coinlite.ui.BackButtonListener
import silantyevmn.ru.coinlite.ui.chart.LatestChart
import javax.inject.Inject

class ChartFragment : MvpAppCompatFragment(), ChartView, BackButtonListener {

    companion object {
        const val KEY_COIN = "key_coin"
        fun getNewInstance(coin: GeckoCoin): ChartFragment {
            val fragment = ChartFragment()
            val bundle = Bundle()
            bundle.putSerializable(KEY_COIN, coin)
            fragment.setArguments(bundle)
            return fragment
        }
    }

    @Inject
    lateinit var repo: GeckoRepo

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var imageLoader: ImageLoader<ImageView>

    lateinit var chart: LatestChart

    private val presenter by moxyPresenter {
        ChartPresenter(
            AndroidSchedulers.mainThread(), router, repo, arguments?.getSerializable(
                KEY_COIN
            ) as GeckoCoin
        )
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
        return inflater.inflate(R.layout.fragment_chart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    override fun onPause() {
        presenter.compositeDisposable.clear()
        super.onPause()
    }

    override fun init() {
        presenter.loadData()
        chart= LatestChart()
        chart.initChart(chartCurrency)

        btn_action_cancel.setOnClickListener {
            presenter.onStop()
        }

        toolbar.setNavigationOnClickListener{
            presenter.onBackPressed()
        }
    }

    override fun addEntryToChart(value: Float, date: String) {
        //
    }

    override fun addEntryToChart(date: Float, value: Float) {
        chart.addEntry(value,date)
    }

    override fun showErrorMessage(error: String?) {
        showToast(error!!)
    }

    override fun refresh(coinItem: Any) {
        coinItem as GeckoCoinItem
        toolbar.title=coinItem.name
        imageLoader.loadInto(coinItem.image,ivCurrencyDetailIcon);
        tvDetailMarketCapRank.text = coinItem.marketCapRank
        tvMarketCapChange.text = coinItem.marketCapChangePercentage24h
        tvATH.text = coinItem.ath
        tvAthChange.text = coinItem.athChangePercentage
        tvCirculatingSupply.text = coinItem.circulatingSupply
        tvTotalSupply.text = coinItem.totalSupply
    }

    override fun showToast(msg: String) {
        Toast.makeText(this.context, msg, Toast.LENGTH_SHORT).show()
    }

    override fun updateChart(chartItem: List<List<Float>>) {
        chart.invalidate()
//        chartItem.forEach {
//            chart.addEntry(it[0],it[1])
//        }
    }

    override fun showLoading() {
        loadingContainer.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        loadingContainer.visibility = View.GONE
    }

    override fun onDestroyView() {
        presenter.onStop()
        super.onDestroyView()
    }

    override fun onBackPressed(): Boolean {
        presenter.onBackPressed()
        return true
    }

}
