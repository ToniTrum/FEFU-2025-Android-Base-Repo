package models

import android.util.Log
import java.util.Date

class Repository(
    private var _name: String,
    var description: String? = null,
    private var _starCount: Int = 0,
    private var _forkCount: Int = 0,
    private var _avatar: String? = null,
    private var _languages: List<Pair<String, Float>>? = null,
    private var _createdAt: Date = Date()
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

    var languages: List<Pair<String, Float>>?
        get() = _languages
        private set(value) {
            _languages = value
        }

    var createdAt: Date
        get() = _createdAt
        private set(value) {
            _createdAt = value
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