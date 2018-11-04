package thiengo.com.br.brasil_pibestados.util

import android.content.Context
import android.databinding.BindingMethod
import android.databinding.BindingMethods
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
     * que seja possível a criação de uma subclasse de Spinner.
     * */
    constructor(context: Context) : super(context) {}
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    fun setSpinnerItemSelected(
        activity: PIBActivity
    ){
        Log.i("LOG-r", "setSpinnerItemSelected")

        val listener = object: AdapterView.OnItemSelectedListener{

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                activity.updateScreen( position )
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                activity.updateScreen( 0 )
            }
        }

        this.onItemSelectedListener = listener
    }
}