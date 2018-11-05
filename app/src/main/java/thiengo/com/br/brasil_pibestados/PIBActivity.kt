package thiengo.com.br.brasil_pibestados

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import az.plainpie.animation.PieAngleAnimation
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import kotlinx.android.synthetic.main.activity_pib.*
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
        /*
         * Obtendo a porcentagem do PIB do estado escolhido
         * em relação ao PIB nacional.
         * */
        val percentage = (state.pib / Database.getPibBrazil()) * 100

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
        animationView( tv_pib_state )

        tv_population_value.text = state.population
        animationView( tv_population_value )

        tv_area_value.text = state.area
        animationView( tv_area_value )

        tv_idh_value.text = state.idh
        animationView( tv_idh_value )

        tv_per_capita_income_value.text = state.perCapitaIncome
        animationView( tv_per_capita_income_value )

        tv_illiteracy_value.text = state.illiteracy
        animationView( tv_illiteracy_value )

        tv_life_expectancy_value.text = state.lifeExpectancy
        animationView( tv_life_expectancy_value )
    }

    private fun animationView( view: View ){
        YoYo
            .with( Techniques.FlipInX )
            .duration( 1300 )
            .playOn( view )
    }
}
