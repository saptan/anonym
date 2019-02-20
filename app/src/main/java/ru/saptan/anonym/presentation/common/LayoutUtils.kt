package ru.saptan.anonym.presentation.common

import android.support.annotation.LayoutRes

object LayoutUtils {

    /**
     * Помогает найти аннотацию в классе и его потомках
     *
     * @param clazz класс для поиска фннонации
     * @return Возвращает резульнат поиска аннотации
     */
    fun hasLayoutAnnotation(clazz: Class<*>): Boolean {
        return clazz.isAnnotationPresent(Layout::class.java) || clazz.superclass != null && hasLayoutAnnotation(clazz.superclass)
    }

    /**
     * Возвращаел LayoutRes для переданного класса. Если не находит в данном классе, то ищет в его
     * потомках, а если не находит, то возвращает 0.
     *
     * @param clazz Класс для поиска LayoutRes
     * @return Возвращает id LayoutRes
     */
    @LayoutRes
    fun getLayoutRes(clazz: Class<*>): Int {
        if (clazz.isAnnotationPresent(Layout::class.java))
            return (clazz.getAnnotation(Layout::class.java) as Layout).id
        return if (clazz.superclass != null) getLayoutRes(clazz.superclass) else 0
    }
}