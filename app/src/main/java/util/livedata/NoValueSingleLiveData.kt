package util.livedata

class NoValueSingleLiveData : SingleLiveData<Unit>() {

    fun notifyObservers() = setValue(Unit)

}