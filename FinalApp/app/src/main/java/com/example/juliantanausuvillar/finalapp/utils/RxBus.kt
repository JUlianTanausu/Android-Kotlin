package com.example.juliantanausuvillar.finalapp.utils

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject



object RxBus {

    private val publisher = PublishSubject.create<Any>()

    fun publish(event: Any){
        publisher.onNext(event)
    }


    // T, no sabes de k tipo
    fun <T>listen(eventType: Class<T>): Observable<T> = publisher.ofType(eventType)
}