package ru.saptan.anonym.routing


internal class ActivitiesRouter(private var creator: IIntentCreator) : IActivitiesRouter {
    private val tag: String = this.javaClass.simpleName
}