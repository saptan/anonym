package ru.saptan.anonym.presentation.common.list

interface IListItemView<D> {
    fun bind(item: D)
}