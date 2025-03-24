package models

import android.util.Log

class Repository(
    private var _name: String,
    var description: String? = null,
    private var _starCount: Int = 0,
    private var _forkCount: Int = 0,
    private var _avatar: String? = null
) {
    var name: String
        get() = _name
        set(value) {
            if (value.isNotBlank())
                _name = value
            else
                Log.e("Repository", "Name can't be empty")
        }

    var starCount: Int
        get() = _starCount
        private set(value) {
            _starCount = value
        }

    var forkCount: Int
        get() = _forkCount
        private set(value) {
            _forkCount = value
        }

    var avatar: String?
        get() = _avatar
        private set(value) {
            _avatar = value
        }

    fun increaseStarCount() {
        _starCount++
    }

    fun decreaseStarCount() {
        if (_starCount > 0)
            _starCount--
        else
            Log.e("Repository", "The star count can't be lower than 0")
    }

    fun increaseForkCount() {
        _forkCount++
    }

    fun decreaseForkCount() {
        if (_forkCount > 0)
            _forkCount--
        else
            Log.e("Repository", "The fork count can't be lower than 0")
    }
}