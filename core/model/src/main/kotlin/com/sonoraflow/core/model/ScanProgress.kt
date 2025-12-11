package com.sonoraflow.core.model

data class ScanProgress(
    val isScanning: Boolean = false,
    val filesFound: Int = 0,
    val currentFile: String = "",
    val error: String? = null
)
