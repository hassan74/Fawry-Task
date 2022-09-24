package com.task.domain.usecase.errors

import com.task.data.error.Error

interface ErrorUseCase {
    fun getError(errorCode: Int): Error
}
