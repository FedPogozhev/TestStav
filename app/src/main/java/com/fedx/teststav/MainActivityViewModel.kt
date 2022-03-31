package com.fedx.teststav

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fedx.teststav.network.Character
import com.fedx.teststav.network.CharacterApi
import kotlinx.coroutines.launch

class MainActivityViewModel(): ViewModel() {
    private val _property = MutableLiveData<List<Character>>()
    val property: LiveData<List<Character>>
                    get() = _property

    init {
        getCharacters()
    }

    private fun getCharacters() {
        viewModelScope.launch {
            try{
                val character = CharacterApi.retrofitService.getAllCharacter()
                _property.value = character.results
                Log.d("MyLog", "character ${_property.value}")
            } catch (e: Exception){
                Log.d("MyLog", "error character")
            }
        }
    }
}