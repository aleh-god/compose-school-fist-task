package by.godevelopment.randomizer.ui.models

sealed interface FragmentEvent {
    data class InputMinValueChanged(val inputMin: String) : FragmentEvent
    data class InputMaxValueChanged(val inputMax: String) : FragmentEvent
    object PressRandomizeButton : FragmentEvent
    object PressReturnButton : FragmentEvent
}