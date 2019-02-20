package ru.saptan.anonym.app.injections.global

import dagger.Module

@Module(includes = [(LocalStorageModule::class), (RestModule::class)])
class RepoModule {

}