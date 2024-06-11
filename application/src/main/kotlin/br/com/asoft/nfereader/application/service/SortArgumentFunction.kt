package br.com.asoft.nfereader.application.service

import java.util.function.Function

interface SortArgumentFunction<T, R> : Function<T, R> {
}