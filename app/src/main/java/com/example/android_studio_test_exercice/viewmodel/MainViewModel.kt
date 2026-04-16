package com.example.android_studio_test_exercice.viewmodel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.state.ToggleableState
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel {
    // Atributs
    private val _estatSwitch: MutableLiveData<Boolean>
    public val estatSwitch: LiveData<Boolean>

    private val _esVegetaria: MutableLiveData<Boolean>
    public val esVegetaria: LiveData<Boolean>

    private val _esVega: MutableLiveData<Boolean>
    public val esVega: LiveData<Boolean>

    private val _esCarnivor: MutableLiveData<Boolean>
    public val esCarnivor: LiveData<Boolean>

    private val _triStateStatus: MutableLiveData<ToggleableState>
    public val triStateStatus: LiveData<ToggleableState>

    private val _selectedOption: MutableLiveData<String>
    public val selectedOption: LiveData<String>

    /**
     * Constructor de la classe HelloViewModel
     * que inicialitzen els atributs
     */
    constructor() : super() {
        this._estatSwitch = MutableLiveData<Boolean>(true)
        this.estatSwitch = this._estatSwitch

        this._esVegetaria = MutableLiveData<Boolean>(false)
        this.esVegetaria = this._esVegetaria

        this._esVega = MutableLiveData<Boolean>(false)
        this.esVega = this._esVega

        this._esCarnivor = MutableLiveData<Boolean>(true)
        this.esCarnivor = this._esCarnivor

        this._triStateStatus = MutableLiveData<ToggleableState>(ToggleableState.Off)
        this.triStateStatus = this._triStateStatus

        this._selectedOption = MutableLiveData<String>("Messi")
        this.selectedOption = this._selectedOption
    }

    fun toggleEstatSwitch(){
        this._estatSwitch.value = !(this._estatSwitch.value)!!
    }

    fun toggleEsCarnivor(){
        this._esCarnivor.value = !(this._esCarnivor.value)!!
    }

    fun toggleTriStateStatus(){
        when(this._triStateStatus.value){
            ToggleableState.On -> setTriStateStatus(ToggleableState.Off)
            ToggleableState.Off -> setTriStateStatus(ToggleableState.Indeterminate)
            ToggleableState.Indeterminate -> setTriStateStatus(ToggleableState.On)
            null -> setTriStateStatus(ToggleableState.On)
        }
    }

    private fun setTriStateStatus(triState: ToggleableState){
        this._triStateStatus.value = triState
    }
}