package io.github.harryjhin.infra.event

import org.springframework.context.ApplicationEventPublisher

object Events {

    private lateinit var publisher: ApplicationEventPublisher

    internal fun setPublisher(publisher: ApplicationEventPublisher) {
        Events.publisher = publisher
    }

    fun raise(event: Any) {
        publisher.publishEvent(event)
    }
}