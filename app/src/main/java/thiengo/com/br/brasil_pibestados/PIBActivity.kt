package thiengo.com.br.brasil_pibestados

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.util.Log
import kotlinx.android.synthetic.main.activity_pib.*
import az.plainpie.animation.PieAngleAnimation
import thiengo.com.br.brasil_pibestados.data.Database
import thiengo.com.br.brasil_pibestados.databinding.ActivityPibBinding
import thiengo.com.br.brasil_pibestados.domain.State


class PIBActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityPibBinding>(
            this,
            R.layout.activity_pib
        )

        configPieChart()
    }

    private fun configPieChart(){
        /*
         * Alterando o tamanho do texto do widget.
         * */
        pv_pib.setPercentageTextSize( 35.0F )

        /*
         * Alterando o preenchimento de cor da barra que representa
         * a porcentagem atual.
         * */
        pv_pib.setPercentageBackgroundColor(
            ContextCompat.getColor( this, R.color.colorAccent )
        )
    }

    private fun pieChartNewValue( state: State ){
        val percentage = (state.pib / Database.getPibBrazil()) * 100
        Log.i("log-r", "percentage: ${percentage.toFloat()} ${String.format("%.2f", percentage)}")

        pv_pib.setInnerText( String.format("%.1f", percentage) )
        pv_pib.percentage = percentage.toFloat()

        val animation = PieAngleAnimation( pv_pib )
        animation.duration = 800 /* Duração da animação, em milissegundos. */
        pv_pib.startAnimation( animation )
    }

    fun updateScreen( index: Int ){
        val state = Database.getState( index )

        pieChartNewValue( state )

        tv_pib_state.text = state.getPibFormatted()
        tv_population_value.text = state.population
        tv_area_value.text = state.area
        tv_idh_value.text = state.idh
        tv_per_capita_income_value.text = state.perCapitaIncome
        tv_illiteracy_value.text = state.illiteracy
        tv_life_expectancy_value.text = state.lifeExpectancy
    }
}
