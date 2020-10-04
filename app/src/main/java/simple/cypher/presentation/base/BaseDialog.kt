package simple.cypher.presentation.base

import androidx.fragment.app.DialogFragment
import androidx.lifecycle.LiveData
import util.livedata.NoValueSingleLiveData

open class BaseDialog : DialogFragment() {

    operator fun <T> LiveData<T>.invoke(observer: (T) -> Unit) =
        observe(viewLifecycleOwner, observer)

    operator fun NoValueSingleLiveData.invoke(observer: () -> Unit) =
        observe(viewLifecycleOwner) { observer() }

}