package monoalphabetic.cypher.presentation.base

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import util.livedata.NoValueSingleLiveData

open class BaseFragment(layout: Int) : Fragment(layout) {

    operator fun <T> LiveData<T>.invoke(observer: (T) -> Unit) =
        observe(viewLifecycleOwner, observer)

    operator fun NoValueSingleLiveData.invoke(observer: () -> Unit) =
        observe(viewLifecycleOwner) { observer() }

}