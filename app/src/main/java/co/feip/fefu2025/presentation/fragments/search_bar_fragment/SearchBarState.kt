package co.feip.fefu2025.presentation.fragments.search_bar_fragment

import co.feip.fefu2025.common.BaseState

data class SearchBarState(
    override val isLoading: Boolean = false,
    override val error: String = ""
) : BaseState