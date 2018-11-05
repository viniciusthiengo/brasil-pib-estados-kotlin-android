package thiengo.com.br.brasil_pibestados.view

import android.content.Context
import android.databinding.BindingMethod
import android.databinding.BindingMethods
import android.databinding.adapters.AdapterViewBindingAdapter
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import thiengo.com.br.brasil_pibestados.PIBActivity

@BindingMethods(
    value = [
        BindingMethod(
            type = Spinner::class,
            attribute = "android:onItemSelected",
            method = "setSpinnerItemSelected"
        )
    ]
)
class SpinnerWithListener: Spinner {

    /*
     * Sobrescrevendo os dois construtores obrigatórios para
     * que seja possível a criação de uma subclasse de uma
     * visualização.
     * */
    constructor( context: Context ) : super( context ) {}
    constructor( context: Context, attrs: AttributeSet ) : super( context, attrs ) {}

    /*
     * A atividade principal do projeto entra como argumento,
     * pois é ela que contém o método de atualização dos
     * componentes visuais em tela, o método updateScreen().
     * */
    fun setSpinnerItemSelected(
            activity: PIBActivity
        ){
        Log.i("LOG-r", "setSpinnerItemSelected")

        val listener = object: AdapterView.OnItemSelectedListener{

            override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                activity.updateScreen( position )
            }

            override fun onNothingSelected( parent: AdapterView<*>? ) {
                /*
                 * Quando nada foi selecionado ainda, os dados
                 * do primeiro estado. Acre, são requisitados.
                 * */
                activity.updateScreen( 0 )
            }
        }

        this.onItemSelectedListener = listener
    }
}