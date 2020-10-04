package util.livedata

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

open class SingleLiveData<T> : MutableLiveData<T>() {

    private var lastValueChangeNotified = false

    override fun setValue(value: T) {
        lastValueChangeNotified = false
        super.setValue(value)
    }

    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        super.observe(owner) {
            if (!lastValueChangeNotified) {
                observer.onChanged(it)
                lastValueChangeNotified = true
            }
        }
    }

    override fun observeForever(observer: Observer<in T>) {
        super.observeForever {
            if (!lastValueChangeNotified) {
                observer.onChanged(it)
                lastValueChangeNotified = true
            }
        }
    }

}