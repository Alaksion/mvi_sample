package com.example.uistate

import kotlinx.coroutines.CoroutineDispatcher

internal class SampleException : Throwable(message = "This is a sample error")

internal data class SampleState(
    val text: String = "",
    val number: Int = 10,
    val boolean: Boolean = true
)

internal class FakeService {
    fun doSomething(value: Int) {
        print(value)
    }
}

internal class SampleUiViewModel(
    dispatcher: CoroutineDispatcher,
    private val fakeService: FakeService
) : UiStateViewModel<SampleState>(dispatcher = dispatcher, initialState = SampleState()) {

    var shouldThrowException = false

    fun updateStateOnce(
        text: String,
        number: Int,
        boolean: Boolean,
        showLoading: Boolean
    ) {
        setState(
            showLoading = showLoading,
            block = {
                if (shouldThrowException) throw SampleException()

                it.copy(
                    text = text,
                    number = number,
                    boolean = boolean
                )
            }
        )
    }

    fun updateStateMultipleTimes(
        showLoading: Boolean
    ) {
        stateUpdater(
            showLoading = showLoading,
            block = { manager ->

                if (shouldThrowException) throw SampleException()

                manager.update { currentState ->
                    currentState.copy(
                        text = currentState.text + "a",
                        number = currentState.number + 1
                    )
                }

                manager.update { currentState ->
                    currentState.copy(
                        text = currentState.text + "a",
                        number = currentState.number + 1
                    )
                }

                manager.update { currentState ->
                    currentState.copy(
                        text = currentState.text + "a",
                        number = currentState.number + 1
                    )
                }
            }
        )
    }

    fun runSomethingSuspend() {
        runSuspendCatching {
            if (shouldThrowException) throw SampleException()
            fakeService.doSomething(stateData.number)
        }
    }

}